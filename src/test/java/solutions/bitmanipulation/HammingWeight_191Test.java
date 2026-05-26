package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
}
