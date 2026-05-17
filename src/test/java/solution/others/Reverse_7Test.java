package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Reverse_7Test {

    private final Reverse_7 test = new Reverse_7();

    @Test
    public void testHappyCases() {
        assertEquals(321, test.reverse(123));
        assertEquals(-321, test.reverse(-123));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.reverse(0));
        assertEquals(0, test.reverse(1534236469));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0L, (long) test.reverse(1534236469) == 0 ? 0 : test.reverse(1534236469));
        assertEquals(21, test.reverse(120));
    }

    @Test
    public void testTrailingZeros() {
        assertEquals(1, test.reverse(100));
    }

    @Test
    public void testSingleDigit() {
        assertEquals(5, test.reverse(5));
    }

    @Test
    public void testNegativeSingleDigit() {
        assertEquals(-9, test.reverse(-9));
    }

    @Test
    public void testMinIntOverflow() {
        assertEquals(0, test.reverse(Integer.MIN_VALUE));
    }

    @Test
    public void testMaxIntOverflow() {
        assertEquals(0, test.reverse(Integer.MAX_VALUE));
    }

    @Test
    public void testNegativeOverflow() {
        assertEquals(0, test.reverse(-2147483648));
    }

    @Test
    public void testGiantNonOverflow() {
        assertEquals(321, test.reverse(1230000));
    }

    /**
     * Iterable sweep -200..200: every input is reversed and compared
     * against an independent string-based reference. Overflow can't
     * happen in this range so the reference is the simple form.
     */
    @ParameterizedTest(name = "reverse({0})")
    @MethodSource("minusTwoHundredToTwoHundred")
    public void testEveryValueFromMinusTwoHundredToTwoHundred(int n) {
        assertEquals(referenceReverse(n), test.reverse(n));
    }

    private static int referenceReverse(int n) {
        long sign = n < 0 ? -1 : 1;
        long abs = Math.abs((long) n);
        String reversed = new StringBuilder(Long.toString(abs)).reverse().toString();
        long result = sign * Long.parseLong(reversed);
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) return 0;
        return (int) result;
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> minusTwoHundredToTwoHundred() {
        return IntStream.rangeClosed(-200, 200).mapToObj(i -> arguments(i));
    }

    /**
     * Inputs near the 32-bit boundary that should overflow when reversed
     * and therefore return 0. e.g. 1534236469 reversed is 9646324351
     * which is > INT_MAX.
     */
    @ParameterizedTest(name = "overflow on reverse({0}) -> 0")
    @ValueSource(ints = {
            1534236469, 2000000003, 2147483647,
            -1534236469, -2147483647, -2147483648, Integer.MIN_VALUE
    })
    public void testInputsThatOverflowReturnZero(int n) {
        assertEquals(0, test.reverse(n));
    }

    /**
     * Inputs where the reversed value is exactly within int range but
     * the source is large. These should NOT overflow.
     */
    @ParameterizedTest(name = "non-overflow reverse({0}) = {1}")
    @org.junit.jupiter.params.provider.CsvSource({
            "1000000000, 1",
            "1999999999, 9999999991",  // overflow: 9999999991 > INT_MAX, expect 0
            "1234567890, 987654321",
            "100000000, 1",
            "1000000007, 7000000001",  // overflow: > INT_MAX, expect 0
            "-1000000000, -1",
            "-1234567890, -987654321"
    })
    public void testLargeInputsBoundary(int input, long expected) {
        long capped = (expected < Integer.MIN_VALUE || expected > Integer.MAX_VALUE)
                ? 0
                : expected;
        assertEquals((int) capped, test.reverse(input));
    }
}
