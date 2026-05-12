package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxSubArray53Test {

    private final MaxSubArray_53 test = new MaxSubArray_53();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        assertEquals(1, test.maxSubArray(new int[]{1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxSubArray(new int[]{-2, -1}));
        assertEquals(0, test.maxSubArray(null));
    }

    @Test
    public void testLargeCase() {
        assertEquals(23, test.maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }
}
