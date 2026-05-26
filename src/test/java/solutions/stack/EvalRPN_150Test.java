package solutions.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvalRPN_150Test {
    private final EvalRPN_150 e = new EvalRPN_150();

    // --- Existing tests ---
    @Test public void testSingleNumber() { assertEquals(5, e.evalRPN(new String[]{"5"})); }
    @Test public void testAdd() { assertEquals(3, e.evalRPN(new String[]{"2", "1", "+"})); }
    @Test public void testSubtract() { assertEquals(1, e.evalRPN(new String[]{"3", "2", "-"})); }
    @Test public void testMultiply() { assertEquals(6, e.evalRPN(new String[]{"2", "3", "*"})); }
    @Test public void testDivide() { assertEquals(3, e.evalRPN(new String[]{"10", "3", "/"})); }
    @Test public void testDivideTruncate() { assertEquals(-2, e.evalRPN(new String[]{"7", "-3", "/"})); }
    @Test public void testComplex() { assertEquals(9, e.evalRPN(new String[]{"2", "1", "+", "3", "*"})); }
    @Test public void testComplex2() { assertEquals(0, e.evalRPN(new String[]{"4", "13", "5", "+", "/"})); }
    @Test public void testNegativeResult() { assertEquals(0, e.evalRPN(new String[]{"1", "-1", "+"})); }
    @Test public void testEmpty() { assertEquals(0, e.evalRPN(new String[]{})); }

    // --- New tests ---

    @Test
    public void testSingleNegativeNumber() {
        assertEquals(-42, e.evalRPN(new String[]{"-42"}));
    }

    @Test
    public void testDivisionTruncatesTowardZeroPositiveDividedByLargeNegative() {
        // 6 / -132 = 0 (truncates toward zero, not -1)
        assertEquals(0, e.evalRPN(new String[]{"6", "-132", "/"}));
    }

    @Test
    public void testSubtractionOrder() {
        // 5 3 - means 5 - 3 = 2, NOT 3 - 5
        assertEquals(2, e.evalRPN(new String[]{"5", "3", "-"}));
    }

    @Test
    public void testDivisionOrder() {
        // 6 3 / means 6 / 3 = 2, NOT 3 / 6
        assertEquals(2, e.evalRPN(new String[]{"6", "3", "/"}));
    }

    @Test
    public void testAllFourOperators() {
        // ((2 + 3) * 4 - 6) / 7 = (20 - 6) / 7 = 14 / 7 = 2
        assertEquals(2, e.evalRPN(new String[]{"2", "3", "+", "4", "*", "6", "-", "7", "/"}));
    }

    @Test
    public void testNegativeResultFromSubtraction() {
        // 3 - 10 = -7
        assertEquals(-7, e.evalRPN(new String[]{"3", "10", "-"}));
    }

    @Test
    public void testLargeValueIntMaxBoundary() {
        // INT_MAX + 0 = INT_MAX
        assertEquals(Integer.MAX_VALUE, e.evalRPN(new String[]{String.valueOf(Integer.MAX_VALUE), "0", "+"}));
    }

    @Test
    public void testLargeValueIntMinBoundary() {
        // INT_MIN + 0 = INT_MIN
        assertEquals(Integer.MIN_VALUE, e.evalRPN(new String[]{String.valueOf(Integer.MIN_VALUE), "0", "+"}));
    }

    @Test
    public void testDeeplyNestedExpression() {
        // Build a long RPN: 1 1 + 1 + 1 + ... (100 ones added together = 100)
        List<String> tokens = new ArrayList<>();
        tokens.add("1");
        for (int i = 1; i < 100; i++) {
            tokens.add("1");
            tokens.add("+");
        }
        // Total tokens: 1 + 99*2 = 199 tokens
        String[] arr = tokens.toArray(new String[0]);
        assertEquals(100, e.evalRPN(arr));
    }

    @Test
    public void testRandomRPNWithSeed42() {
        // Generate random valid RPN expression using tree generation with seed 42
        Random rand = new Random(42L);
        List<String> tokens = new ArrayList<>();
        String[] ops = {"+", "-", "*"};  // avoid division to prevent divide-by-zero
        int depth = 50; // 50 operations -> 101 tokens

        // Build RPN by simulating a stack: push numbers, occasionally apply ops
        Stack<Integer> valueStack = new Stack<>();
        for (int i = 0; i < depth; i++) {
            int a = rand.nextInt(201) - 100; // [-100, 100]
            int b = rand.nextInt(201) - 100;
            tokens.add(String.valueOf(a));
            tokens.add(String.valueOf(b));
            String op = ops[rand.nextInt(ops.length)];
            tokens.add(op);
        }
        // Reduce all remaining values on stack with addition
        for (int i = 1; i < depth; i++) {
            tokens.add("+");
        }

        String[] arr = tokens.toArray(new String[0]);
        // Compute expected using reference implementation (same logic as source)
        int expected = referenceEvalRPN(arr);
        assertEquals(expected, e.evalRPN(arr));
    }

    /** Reference implementation for oracle-based testing */
    private int referenceEvalRPN(String[] tokens) {
        Stack<Integer> s = new Stack<>();
        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                s.push(Integer.parseInt(token));
            } else {
                int o1 = s.pop(), o2 = s.pop();
                switch (token) {
                    case "+": s.push(o2 + o1); break;
                    case "-": s.push(o2 - o1); break;
                    case "*": s.push(o2 * o1); break;
                    case "/": s.push(o2 / o1); break;
                }
            }
        }
        return s.pop();
    }
}
