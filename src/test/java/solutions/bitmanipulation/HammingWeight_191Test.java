package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class HammingWeight_191Test {
    private final HammingWeight_191 solver = new HammingWeight_191();

    @Test public void testBasic() {
        assertEquals(3, solver.hammingWeight(11));
    }

    @Test public void testZero() {
        assertEquals(0, solver.hammingWeight(0));
    }

    @Test public void testOne() {
        assertEquals(1, solver.hammingWeight(1));
    }

    @Test public void testPowerOfTwo() {
        assertEquals(1, solver.hammingWeight(128));
    }

    @Test public void testAllOnes() {
        assertEquals(5, solver.hammingWeight(0b11111));
    }

    @Test public void testMaxInt() {
        assertEquals(31, solver.hammingWeight(Integer.MAX_VALUE));
    }

    @Test public void testNegativeOne() {
        // -1 in two's complement is all 32 bits set
        assertEquals(32, solver.hammingWeight(-1));
    }

    @Test public void testMinValue() {
        // Integer.MIN_VALUE = 0x80000000, only sign bit set
        assertEquals(1, solver.hammingWeight(Integer.MIN_VALUE));
    }

    @Test public void testAlternatingBits() {
        // 0b10101010101010101010101010101010 = 16 ones
        assertEquals(16, solver.hammingWeight(0xAAAAAAAA));
    }

    @Test public void testLargePositive() {
        // 1023 = 0b1111111111, 10 ones
        assertEquals(10, solver.hammingWeight(1023));
    }

    @Test public void testLeetCodeMaximumExample() {
        // 2147483645 = 0b01111111111111111111111111111101.
        assertEquals(30, solver.hammingWeight(2_147_483_645));
    }

    @Test public void testSignBitWithLowBit() {
        // The unsigned 32-bit representation is 10000000000000000000000000000001.
        assertEquals(2, solver.hammingWeight(0x80000001));
    }

    @Test public void testSeveralSignBitsAndNoLowBits() {
        assertEquals(3, solver.hammingWeight(0xE0000000));
        assertEquals(2, solver.hammingWeight(0xC0000000));
        assertEquals(1, solver.hammingWeight(0x80000000));
    }

    @Test public void testHighAndLowSparseMask() {
        assertEquals(3, solver.hammingWeight(0x80010001));
        assertEquals(2, solver.hammingWeight(0x00010001));
    }

    @Test public void testAlternatingNibbles() {
        assertEquals(16, solver.hammingWeight(0x33333333));
        assertEquals(16, solver.hammingWeight(0xCCCCCCCC));
    }

    @Test public void testRepeatedCallsDoNotRetainState() {
        assertEquals(0, solver.hammingWeight(0));
        assertEquals(32, solver.hammingWeight(-1));
        assertEquals(1, solver.hammingWeight(Integer.MIN_VALUE));
        assertEquals(3, solver.hammingWeight(11));
        assertEquals(0, solver.hammingWeight(0));
    }

    /**
     * Iterable sweep 0..255 cross-checked against {@link Integer#bitCount}
     * (the JDK's intrinsic, used as the trusted oracle).
     */
    @ParameterizedTest(name = "hammingWeight({0})")
    @MethodSource("zeroToTwoFiftyFive")
    public void testEveryValueFromZeroToTwoFiftyFive(int input) {
        assertEquals(Integer.bitCount(input), solver.hammingWeight(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> zeroToTwoFiftyFive() {
        return IntStream.rangeClosed(0, 255)
                .mapToObj(i -> arguments(i));
    }

    /**
     * Each single-bit value 2^k for k in [0,31] has exactly one set bit.
     * Note: 2^31 is Integer.MIN_VALUE (negative); the JDK uses
     * unsigned-shift semantics so its bit count is still 1.
     */
    @ParameterizedTest(name = "single bit at position {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 23, 24, 30, 31})
    public void testSingleBitValues(int bitPosition) {
        int value = (bitPosition == 31) ? Integer.MIN_VALUE : (1 << bitPosition);
        assertEquals(1, solver.hammingWeight(value));
        assertEquals(Integer.bitCount(value), solver.hammingWeight(value));
    }

    /**
     * Spot-check selected large/edge values against the JDK oracle.
     */
    @ParameterizedTest(name = "hammingWeight({0})")
    @ValueSource(ints = {
            0, 1, -1, 2, -2,
            Integer.MAX_VALUE, Integer.MIN_VALUE,
            0x55555555, 0xAAAAAAAA,
            0x0F0F0F0F, 0xF0F0F0F0,
            0x12345678, 0x87654321,
            999_999_999, -999_999_999
    })
    public void testEdgeValuesAgainstJdkOracle(int input) {
        assertEquals(Integer.bitCount(input), solver.hammingWeight(input));
    }

    @Test public void testSeededUnsignedBitPatterns() {
        java.util.Random random = new java.util.Random(1910906L);
        for (int i = 0; i < 10000; i++) {
            int value = random.nextInt();
            assertEquals(unsignedBitCountOracle(value), solver.hammingWeight(value),
                    "value=0x" + Integer.toHexString(value));
        }
    }

    /**
     * Exhaust every 16-bit unsigned pattern.  The oracle widens the signed Java
     * value before counting, so this also checks that leading zeroes are ignored
     * without relying on the implementation's shift loop.
     */
    @Test public void testEverySixteenBitPattern() {
        for (int value = 0; value <= 0xFFFF; value++) {
            assertEquals(unsignedBitCountOracle(value), solver.hammingWeight(value),
                    "value=0x" + Integer.toHexString(value));
        }
    }

    @Test public void testComplementPairsHaveThirtyTwoBitsTogether() {
        int[] values = {0, 1, 2, 0x0000FFFF, 0x12345678, 0x55555555,
                Integer.MAX_VALUE, Integer.MIN_VALUE, 0x80000001, -1};
        for (int value : values) {
            assertEquals(32, solver.hammingWeight(value) + solver.hammingWeight(~value),
                    "value=0x" + Integer.toHexString(value));
        }
    }

    @Test public void testEveryPrefixOfSetBits() {
        for (int width = 0; width <= 32; width++) {
            int value = width == 32 ? -1 : (width == 0 ? 0 : (1 << width) - 1);
            assertEquals(width, solver.hammingWeight(value),
                    "width=" + width + ", value=0x" + Integer.toHexString(value));
        }
    }

    @Test public void testEveryOneClearedBit() {
        for (int bit = 0; bit < 32; bit++) assertEquals(31, solver.hammingWeight(~(1 << bit)));
    }

    @Test public void testAllContiguousBitRuns() {
        for (int start = 0; start < 32; start++) {
            int value = 0;
            for (int end = start; end < 32; end++) {
                value |= 1 << end;
                assertEquals(end - start + 1, solver.hammingWeight(value));
            }
        }
    }

    private static int unsignedBitCountOracle(int value) {
        return BigInteger.valueOf(Integer.toUnsignedLong(value)).bitCount();
    }
}
