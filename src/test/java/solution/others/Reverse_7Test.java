package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}
