package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SplitArraySameAverage805Test {

    private final SplitArraySameAverage_805 test = new SplitArraySameAverage_805();

    @Test
    public void testHappyCases() {
        assertTrue(test.splitArraySameAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        assertFalse(test.splitArraySameAverage(new int[]{3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.splitArraySameAverage(new int[]{1}));
        assertFalse(test.splitArraySameAverage(new int[]{1, 7, 8, 10}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.splitArraySameAverage(new int[]{4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5}));
    }
}
