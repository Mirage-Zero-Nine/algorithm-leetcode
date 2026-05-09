package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindNumberOfLIS673Test {

    private final FindNumberOfLIS_673 test = new FindNumberOfLIS_673();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        assertEquals(5, test.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findNumberOfLIS(new int[]{}));
        assertEquals(1, test.findNumberOfLIS(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
    }
}
