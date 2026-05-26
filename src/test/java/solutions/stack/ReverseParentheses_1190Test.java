package solutions.stack;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReverseParentheses_1190Test {
    private final ReverseParentheses_1190 solver = new ReverseParentheses_1190();

    @Test public void testExample1() {
        assertEquals("dcba", solver.reverseParentheses("(abcd)"));
    }

    @Test public void testExample2() {
        assertEquals("iloveu", solver.reverseParentheses("(u(love)i)"));
    }

    @Test public void testExample3() {
        assertEquals("leetcode", solver.reverseParentheses("(ed(et(oc))el)"));
    }

    @Test public void testNoParens() {
        assertEquals("abc", solver.reverseParentheses("abc"));
    }

    @Test public void testEmpty() {
        assertEquals("", solver.reverseParentheses(""));
    }

    @Test public void testNested() {
        assertEquals("apmnolkjihgfedcbq", solver.reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }

    @Test public void testSingleChar() {
        assertEquals("a", solver.reverseParentheses("(a)"));
    }

    @Test public void testDoubleReverse() {
        // ((ab)) -> reverse "ab" -> "ba", then reverse "ba" -> "ab"
        assertEquals("ab", solver.reverseParentheses("((ab))"));
    }

    @Test public void testAdjacentParens() {
        // (ab)(cd) -> "ba" + "dc" = "badc"
        assertEquals("badc", solver.reverseParentheses("(ab)(cd)"));
    }

    @Test public void testEmptyParens() {
        assertEquals("", solver.reverseParentheses("()"));
    }

    @Test public void testGiant() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < 1000; i++) sb.append('a');
        sb.append(")");
        String result = solver.reverseParentheses(sb.toString());
        assertEquals(1000, result.length());
        assertEquals('a', result.charAt(0));
    }

    // --- NEW TESTS ---

    @Test public void testNestedEmpty() {
        // (()) -> inner () produces "", outer reverses "" -> ""
        assertEquals("", solver.reverseParentheses("(())"));
    }

    @Test public void testTripleNestedEmpty() {
        // ((())) -> all empty
        assertEquals("", solver.reverseParentheses("((()))"));
    }

    @Test public void testDeeplyNested() {
        // (a(b(c(d(e))))) - 5 levels deep, cross-checked with reference impl
        assertEquals("bdeca", solver.reverseParentheses("(a(b(c(d(e)))))"));
    }

    @Test public void testMultipleTopLevelGroupsWithText() {
        // x(ab)y(cd)z -> x + "ba" + y + "dc" + z = "xbaydc z"
        assertEquals("xbaydcz", solver.reverseParentheses("x(ab)y(cd)z"));
    }

    @Test public void testThreeAdjacentGroups() {
        // (ab)(cd)(ef) -> "ba" + "dc" + "fe" = "badcfe"
        assertEquals("badcfe", solver.reverseParentheses("(ab)(cd)(ef)"));
    }

    @Test public void testNestedWithSurroundingText() {
        // ta(is(si))ht -> ta + reverse("is" + reverse("si") + "") = ta + reverse("is" + "is") = ta + reverse("isis") = ta + "sisi" + ht
        // Wait: ta(is(si))ht
        // Inner: (si) -> "is", string becomes ta(isis)ht
        // Outer: (isis) -> "sisi", string becomes "tasisiht"
        assertEquals("tasisiht", solver.reverseParentheses("ta(is(si))ht"));
    }

    @Test public void testSixLevelsDeep() {
        // ((((((abcdef)))))) - even number of reversals = original
        assertEquals("abcdef", solver.reverseParentheses("((((((abcdef))))))"));
    }

    @Test public void testFiveLevelsDeep() {
        // (((((abcdef))))) - odd number of reversals = reversed
        assertEquals("fedcba", solver.reverseParentheses("(((((abcdef)))))"));
    }

    @Test public void testLongWithManyGroups() {
        // Cross-check with a recursive reference implementation using seed 42
        Random rng = new Random(42L);
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            input.append((char) ('a' + rng.nextInt(26)));
            input.append('(');
            for (int j = 0; j < 3; j++) {
                input.append((char) ('a' + rng.nextInt(26)));
            }
            input.append(')');
        }
        String s = input.toString();
        String expected = referenceReverse(s);
        assertEquals(expected, solver.reverseParentheses(s));
    }

    @Test public void testLongNestedChain() {
        // Build a(b(c(d...z)...)) with 26 letters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append((char) ('a' + i));
            sb.append('(');
        }
        sb.append('!');
        for (int i = 0; i < 26; i++) {
            sb.append(')');
        }
        String s = sb.toString();
        String expected = referenceReverse(s);
        assertEquals(expected, solver.reverseParentheses(s));
    }

    /** Recursive reference implementation for cross-checking. */
    private String referenceReverse(String s) {
        Deque<StringBuilder> stack = new LinkedList<>();
        stack.push(new StringBuilder());
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                StringBuilder top = stack.pop();
                stack.peek().append(top.reverse());
            } else {
                stack.peek().append(c);
            }
        }
        return stack.peek().toString();
    }
}
