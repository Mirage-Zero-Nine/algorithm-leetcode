package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsUgly_263Test {

    private final IsUgly_263 test = new IsUgly_263();

    @Test
    public void testHappyCases() {
        assertTrue(test.isUgly(6));
        assertTrue(test.isUgly(8));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isUgly(14));
        assertFalse(test.isUgly(0));
        assertFalse(test.isUgly(-1));
        assertTrue(test.isUgly(1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isUgly(2160));
        assertFalse(test.isUgly(2161));
    }

    @Test
    public void testPowersOfTwo() {
        assertTrue(test.isUgly(2));
        assertTrue(test.isUgly(4));
        assertTrue(test.isUgly(16));
        assertTrue(test.isUgly(1024));
    }

    @Test
    public void testPowersOfThree() {
        assertTrue(test.isUgly(3));
        assertTrue(test.isUgly(9));
        assertTrue(test.isUgly(27));
    }

    @Test
    public void testPowersOfFive() {
        assertTrue(test.isUgly(5));
        assertTrue(test.isUgly(25));
        assertTrue(test.isUgly(125));
    }

    @Test
    public void testCombinations() {
        assertTrue(test.isUgly(30));   // 2*3*5
        assertTrue(test.isUgly(60));   // 2^2*3*5
        assertTrue(test.isUgly(90));   // 2*3^2*5
    }

    @Test
    public void testNotUglyPrimes() {
        assertFalse(test.isUgly(7));
        assertFalse(test.isUgly(11));
        assertFalse(test.isUgly(13));
    }

    @Test
    public void testNegativeNumbers() {
        assertFalse(test.isUgly(-2));
        assertFalse(test.isUgly(-6));
        assertFalse(test.isUgly(Integer.MIN_VALUE));
    }

    @Test
    public void testNotUglyComposites() {
        assertFalse(test.isUgly(14));  // 2*7
        assertFalse(test.isUgly(21));  // 3*7
        assertFalse(test.isUgly(35));  // 5*7
    }

    @Test
    public void testGiantUglyNumber() {
        // 2^30 = 1073741824
        assertTrue(test.isUgly(1073741824));
        // 5^13 = 1220703125
        assertTrue(test.isUgly(1220703125));
    }
}
