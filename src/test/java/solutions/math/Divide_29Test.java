package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Divide_29Test {

    private final Divide_29 test = new Divide_29();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.divide(10, 3));
        assertEquals(-2, test.divide(7, -3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(Integer.MAX_VALUE, test.divide(Integer.MIN_VALUE, -1));
        assertEquals(0, test.divide(0, 1));
        assertEquals(1, test.divide(1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1073741823, test.divide(Integer.MAX_VALUE, 2));
        assertEquals(-1073741824, test.divide(Integer.MIN_VALUE, 2));
    }

    @Test
    public void testDivideByOne() {
        assertEquals(100, test.divide(100, 1));
        assertEquals(-100, test.divide(-100, 1));
    }

    @Test
    public void testDivideByItself() {
        assertEquals(1, test.divide(7, 7));
        assertEquals(1, test.divide(-7, -7));
    }

    @Test
    public void testNegativeDividend() {
        assertEquals(-3, test.divide(-10, 3));
        assertEquals(-5, test.divide(-25, 5));
    }

    @Test
    public void testNegativeDivisor() {
        assertEquals(-5, test.divide(25, -5));
    }

    @Test
    public void testBothNegative() {
        assertEquals(3, test.divide(-10, -3));
    }

    @Test
    public void testDividendSmallerThanDivisor() {
        assertEquals(0, test.divide(1, 2));
        assertEquals(0, test.divide(3, 7));
    }

    @Test
    public void testMinValueDividedByPositive() {
        assertEquals(-1073741824, test.divide(Integer.MIN_VALUE, 2));
    }

    @Test
    public void testGiantRepeatedDivision() {
        assertEquals(Integer.MAX_VALUE / 3, test.divide(Integer.MAX_VALUE, 3));
        assertEquals(Integer.MAX_VALUE / 7, test.divide(Integer.MAX_VALUE, 7));
        assertEquals(Integer.MAX_VALUE / 100, test.divide(Integer.MAX_VALUE, 100));
    }
}
