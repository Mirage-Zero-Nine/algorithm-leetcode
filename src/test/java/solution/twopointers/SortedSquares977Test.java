package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SortedSquares977Test {

    private final SortedSquares_977 test = new SortedSquares_977();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 1, 9, 16, 100}, test.sortedSquares(new int[]{-4, -1, 0, 3, 10}));
        assertArrayEquals(new int[]{4, 9, 9, 49, 121}, test.sortedSquares(new int[]{-7, -3, 2, 3, 11}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{1}, test.sortedSquares(new int[]{-1}));
        assertArrayEquals(new int[]{0, 1}, test.sortedSquares(new int[]{-1, 0}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{1, 4, 9, 16, 25}, test.sortedSquares(new int[]{-5, -4, -3, -2, -1}));
    }
}
