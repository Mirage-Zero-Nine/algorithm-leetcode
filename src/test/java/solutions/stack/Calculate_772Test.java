package solutions.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Calculate_772Test {
    private final Calculate_772 c = new Calculate_772();

    @Test public void testNegativePrefix() { assertEquals(0, c.calculate("-1+4*3/3/3")); }
    @Test public void testNegInParens() { assertEquals(8, c.calculate("1 - (-7)")); }
    @Test public void testComplex() { assertEquals(-12, c.calculate("(2+6* 3+5- (3*14/7+2)*5)+3")); }
    @Test public void testDoubleNeg() { assertEquals(3, c.calculate("2-(5-6)")); }
    @Test public void testSingleNumber() { assertEquals(42, c.calculate("42")); }
    @Test public void testAdd() { assertEquals(5, c.calculate("2+3")); }
    @Test public void testPrecedence() { assertEquals(14, c.calculate("2+3*4")); }
    @Test public void testSpaces() { assertEquals(5, c.calculate(" 2 + 3 ")); }
    @Test public void testEmpty() { assertEquals(0, c.calculate("")); }
    @Test public void testNull() { assertEquals(0, c.calculate(null)); }

    /**
     * The deque implementation is a separate public approach, so keep a table of expressions
     * that verifies it against the two-stack implementation and the expected result.
     */
    @Test
    public void testBothApproachesOnCoreExpressions() {
        assertBoth("0", 0);
        assertBoth("00042", 42);
        assertBoth("2+3", 5);
        assertBoth("21-14", 7);
        assertBoth("6*7", 42);
        assertBoth("11/2", 5);
        assertBoth("2+3*4", 14);
        assertBoth("2*3+4", 10);
        assertBoth("20/5*2", 8);
        assertBoth("20/5/2", 2);
        assertBoth("10-3-2", 5);
    }

    @Test
    public void testBothApproachesWithParentheses() {
        assertBoth("(2+3)*4", 20);
        assertBoth("2*(3+4)", 14);
        assertBoth("((1+2)*3)", 9);
        assertBoth("(2+3)*(4-1)", 15);
        assertBoth("1+(2*(3+(4*5)))", 47);
        assertBoth("20/(5*2)", 2);
        assertBoth("10-(3-2)", 9);
        assertBoth("1-((0-2)*3)", 7);
    }

    @Test
    public void testBothApproachesWithWhitespace() {
        assertBoth(" 2 + 3 ", 5);
        assertBoth("1 + ( 2 * 3 )", 7);
        assertBoth("( 10 - 4 ) / 2", 3);
        assertBoth("2 * ( 3 + 4 * 2 )", 22);
    }

    @Test
    public void testBothApproachesWithNegativeResults() {
        assertBoth("-1+4*3/3/3", 0);
        assertBoth("1-(-7)", 8);
        assertBoth("-(1+2)", -3);
        assertBoth("(0-7)/2", -3);
        assertBoth("7/(0-2)", -3);
        assertBoth("0-2*3", -6);
        assertBoth("2*(3-8)", -10);
    }

    @Test
    public void testBothApproachesRespectIntegerDivisionTowardZero() {
        assertBoth("7/2", 3);
        assertBoth("(0-7)/2", -3);
        assertBoth("7/(0-2)", -3);
        assertBoth("8/3/2", 1);
        assertBoth("14/3*2", 8);
    }

    @Test
    public void testBothApproachesOnComplexExpressions() {
        assertBoth("(2+6*3+5-(3*14/7+2)*5)+3", -12);
        assertBoth("1+(2*(3+4))-5*2", 5);
        assertBoth("((12/3)+(4*5)-(6-2))*2", 40);
        assertBoth("0-(2+(3*(4-10)))", 16);
    }

    @Test
    public void testBothApproachesOnBoundarySizedValues() {
        assertBoth("2147483647", Integer.MAX_VALUE);
        assertBoth("0-2147483647", -Integer.MAX_VALUE);
        assertBoth("2147483647-2147483647", 0);
        assertBoth("46340*46340", 2_147_395_600);
        assertBoth("2147483640+7", Integer.MAX_VALUE - 0);
    }

    @Test
    public void testBothApproachesOnRepeatedOperations() {
        StringBuilder expression = new StringBuilder("1");
        for (int i = 0; i < 999; i++) {
            expression.append("+1");
        }

        assertBoth(expression.toString(), 1_000);
    }

    @Test
    public void testBothApproachesOnLongNestedExpression() {
        StringBuilder expression = new StringBuilder("1");
        for (int i = 0; i < 100; i++) {
            expression.insert(0, "(1+");
            expression.append(')');
        }

        assertBoth(expression.toString(), 101);
    }

    @Test
    public void testCalculateHandlesEmptyInput() {
        assertEquals(0, c.calculate(""));
    }

    @Test
    public void testCalculateHandlesLeadingWhitespaceBeforeUnaryMinus() {
        // The two-stack approach explicitly supports a leading unary minus after spaces.
        assertEquals(-1, c.calculate(" -1"));
        assertEquals(8, c.calculate("1 - ( -7)"));
    }

    @Test
    public void testCalculateHandlesDifferentParenthesizedUnaryMinusSpacing() {
        assertEquals(-7, c.calculate("( -7)"));
        assertEquals(-7, c.calculate("(   -   7)"));
        assertEquals(6, c.calculate("1 - ( - 5)"));
    }

    @Test
    public void testZeroAndLeadingZeros() {
        assertBoth("0+0*0", 0);
        assertBoth("00012+003", 15);
        assertBoth("10*(0-0)", 0);
    }

    @Test
    public void testOperatorsWithAlreadyComputedParenthesizedValues() {
        assertBoth("(1+2)+(3+4)", 10);
        assertBoth("(1+2)*(3+4)", 21);
        assertBoth("(1+2)/(3-2)", 3);
        assertBoth("(8-3)*(2+1)", 15);
    }

    private void assertBoth(String expression, int expected) {
        assertEquals(expected, c.calculate(expression), "calculate: " + expression);
        assertEquals(expected, c.calculateDeque(expression), "calculateDeque: " + expression);
    }
}
