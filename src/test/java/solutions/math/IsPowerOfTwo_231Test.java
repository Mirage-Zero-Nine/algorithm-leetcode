package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

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

    /**
     * Iterable sweep 0..1023 against an independent reference. Each input
     * must agree with {@link Integer#bitCount(int)} == 1 (the only positive
     * powers of two have exactly one set bit). Catches regressions where
     * the bit-trick happens to give the right answer for small powers but
     * fails near boundaries.
     */
    @ParameterizedTest(name = "isPowerOfTwo({0})")
    @MethodSource("zeroToOneThousandTwentyThree")
    public void testEveryValueFromZeroToOneThousandTwentyThree(int input, boolean expected) {
        assertEquals(expected, test.isPowerOfTwo(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> zeroToOneThousandTwentyThree() {
        return IntStream.rangeClosed(0, 1023)
                .mapToObj(i -> arguments(i, i > 0 && Integer.bitCount(i) == 1));
    }

    /**
     * All 31 positive powers of two in int range are accepted; their
     * neighbours (n-1, n+1) are rejected unless they also happen to be a
     * power of two (n-1 of 2 is 1, also a power; n+1 of 1 is 2, also a
     * power). The reference handles these edge cases.
     */
    @ParameterizedTest(name = "power-of-two boundary 2^{0}")
    @MethodSource("allPowerExponents")
    public void testEachPowerOfTwoAndItsNeighbours(int exp) {
        int p = 1 << exp;
        assertTrue(test.isPowerOfTwo(p), "2^" + exp + " = " + p);
        assertEquals(p - 1 > 0 && Integer.bitCount(p - 1) == 1,
                test.isPowerOfTwo(p - 1));
        assertEquals(p + 1 > 0 && Integer.bitCount(p + 1) == 1,
                test.isPowerOfTwo(p + 1));
    }

    private static Stream<Integer> allPowerExponents() {
        return IntStream.rangeClosed(0, 30).boxed();
    }
}
