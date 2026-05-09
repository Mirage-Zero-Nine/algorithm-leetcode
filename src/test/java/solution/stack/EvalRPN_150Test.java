package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EvalRPN_150Test {
    private final EvalRPN_150 e = new EvalRPN_150();

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
}
