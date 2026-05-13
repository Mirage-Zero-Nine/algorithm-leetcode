package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSwap_670Test {

    private final MaximumSwap_670 test = new MaximumSwap_670();

    @Test
    public void testHappyCases() {
        assertEquals(7236, test.maximumSwap(2736));
        assertEquals(9973, test.maximumSwap(9973));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(9, test.maximumSwap(9));
        assertEquals(10, test.maximumSwap(10));
    }

    @Test
    public void testLargeCase() {
        assertEquals(98368, test.maximumSwap(38968));
    }

    @Test
    public void testSingleDigit() {
        assertEquals(0, test.maximumSwap(0));
        assertEquals(5, test.maximumSwap(5));
    }

    @Test
    public void testAlreadyMax() {
        assertEquals(9876, test.maximumSwap(9876));
    }

    @Test
    public void testAllSameDigits() {
        assertEquals(1111, test.maximumSwap(1111));
    }

    @Test
    public void testSwapFirstAndLast() {
        assertEquals(9231, test.maximumSwap(1239));
    }

    @Test
    public void testDuplicateMaxDigit() {
        // 1993 -> swap 1 with last 9 -> 9913
        assertEquals(9913, test.maximumSwap(1993));
    }

    @Test
    public void testTwoDigits() {
        assertEquals(21, test.maximumSwap(12));
    }

    @Test
    public void testGiantNumber() {
        assertEquals(99104234, test.maximumSwap(19904234));
    }
}
