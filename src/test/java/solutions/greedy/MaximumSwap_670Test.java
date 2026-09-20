package solutions.greedy;

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
    @Test public void testAdditional123() { assertEquals(321, test.maximumSwap(123)); }
    @Test public void testAdditional2730() { assertEquals(7230, test.maximumSwap(2730)); }
    @Test public void testAdditional98368() { assertEquals(98863, test.maximumSwap(98368)); }
    @Test public void testAdditional109090() { assertEquals(909010, test.maximumSwap(109090)); }
    @Test public void testAdditional1993() { assertEquals(9913, test.maximumSwap(1993)); }
    @Test public void testAdditional9970() { assertEquals(9970, test.maximumSwap(9970)); }
    @Test public void testAdditional100() { assertEquals(100, test.maximumSwap(100)); }
    @Test public void testAdditional983() { assertEquals(983, test.maximumSwap(983)); }
    @Test public void testAdditional115() { assertEquals(511, test.maximumSwap(115)); }
    @Test public void testAdditional9836() { assertEquals(9863, test.maximumSwap(9836)); }
}
