package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TwoSumLessThanK1099Test {

    private final TwoSumLessThanK_1099 test = new TwoSumLessThanK_1099();

    @Test
    public void testHappyCases() {
        assertEquals(58, test.twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60));
        assertEquals(-1, test.twoSumLessThanK(new int[]{10, 20, 30}, 15));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.twoSumLessThanK(new int[]{10, 20, 30}, 5));
        assertEquals(2, test.twoSumLessThanK(new int[]{1, 1}, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.twoSumLessThanK(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 10));
    }
}
