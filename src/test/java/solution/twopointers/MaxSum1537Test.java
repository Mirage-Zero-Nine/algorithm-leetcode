package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxSum1537Test {

    private final MaxSum_1537 test = new MaxSum_1537();

    @Test
    public void testHappyCases() {
        assertEquals(30, test.maxSum(new int[]{2, 4, 5, 8, 10}, new int[]{4, 6, 8, 9}));
        assertEquals(109, test.maxSum(new int[]{1, 3, 5, 7, 9}, new int[]{3, 5, 100}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.maxSum(new int[]{1}, new int[]{2}));
        assertEquals(3, test.maxSum(new int[]{1, 2}, new int[]{3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(81, test.maxSum(new int[]{1, 4, 5, 8, 9, 11, 19, 20}, new int[]{2, 3, 4, 11, 12}));
    }
}
