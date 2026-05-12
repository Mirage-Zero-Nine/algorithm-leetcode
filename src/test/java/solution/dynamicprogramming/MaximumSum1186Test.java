package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSum1186Test {

    private final MaximumSum_1186 test = new MaximumSum_1186();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.maximumSum(new int[]{1, -2, 0, 3}));
        assertEquals(3, test.maximumSum(new int[]{1, -2, -2, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maximumSum(new int[]{-1, -1, -1, -1}));
        assertEquals(1, test.maximumSum(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(14, test.maximumSum(new int[]{1, 2, 3, -4, 5, -6, 7}));
    }
}
