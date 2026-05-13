package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsPowerOfTwo_231Test {

    private final IsPowerOfTwo_231 test = new IsPowerOfTwo_231();

    @Test
    public void testHappyCases() {
        assertTrue(test.isPowerOfTwo(1));
        assertTrue(test.isPowerOfTwo(16));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isPowerOfTwo(0));
        assertFalse(test.isPowerOfTwo(-1));
        assertFalse(test.isPowerOfTwo(3));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isPowerOfTwo(1073741824));
        assertFalse(test.isPowerOfTwo(1000000000));
    }

    @Test
    public void testPowerOfTwo_2() {
        assertTrue(test.isPowerOfTwo(2));
    }

    @Test
    public void testPowerOfTwo_4() {
        assertTrue(test.isPowerOfTwo(4));
    }

    @Test
    public void testPowerOfTwo_8() {
        assertTrue(test.isPowerOfTwo(8));
    }

    @Test
    public void testPowerOfTwo_64() {
        assertTrue(test.isPowerOfTwo(64));
    }

    @Test
    public void testPowerOfTwo_512() {
        assertTrue(test.isPowerOfTwo(512));
    }

    @Test
    public void testNegativePowerOfTwo() {
        assertFalse(test.isPowerOfTwo(-16));
        assertFalse(test.isPowerOfTwo(-1024));
        assertFalse(test.isPowerOfTwo(Integer.MIN_VALUE));
    }

    @Test
    public void testNonPowerOfTwo() {
        assertFalse(test.isPowerOfTwo(6));
        assertFalse(test.isPowerOfTwo(10));
        assertFalse(test.isPowerOfTwo(15));
    }

    @Test
    public void testEdgeIntMaxValue() {
        assertFalse(test.isPowerOfTwo(Integer.MAX_VALUE));
    }

    @Test
    public void testGiantAllPowersOfTwo() {
        for (int i = 0; i < 31; i++) {
            assertTrue(test.isPowerOfTwo(1 << i));
        }
    }

    @Test
    public void testNonPowersNearPowersOfTwo() {
        assertFalse(test.isPowerOfTwo(31));
        assertFalse(test.isPowerOfTwo(33));
        assertFalse(test.isPowerOfTwo(63));
        assertFalse(test.isPowerOfTwo(65));
    }
}
