package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SortedSquares_977Test {

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

    @Test
    public void testAllPositive() {
        assertArrayEquals(new int[]{1, 4, 9, 16, 25}, test.sortedSquares(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testSingleZero() {
        assertArrayEquals(new int[]{0}, test.sortedSquares(new int[]{0}));
    }

    @Test
    public void testSinglePositive() {
        assertArrayEquals(new int[]{9}, test.sortedSquares(new int[]{3}));
    }

    @Test
    public void testMixedWithZero() {
        assertArrayEquals(new int[]{0, 1, 4, 9}, test.sortedSquares(new int[]{-3, -2, 0, 1}));
    }

    @Test
    public void testSymmetric() {
        assertArrayEquals(new int[]{1, 1, 4, 4, 9, 9}, test.sortedSquares(new int[]{-3, -2, -1, 1, 2, 3}));
    }

    @Test
    public void testTwoElements() {
        assertArrayEquals(new int[]{1, 4}, test.sortedSquares(new int[]{-2, 1}));
    }

    @Test
    public void testGiantCase() {
        int size = 10000;
        int[] input = new int[size];
        for (int i = 0; i < size; i++) {
            input[i] = i - 5000;
        }
        int[] result = test.sortedSquares(input);
        // Verify sorted order
        for (int i = 1; i < result.length; i++) {
            assert result[i] >= result[i - 1];
        }
        assertArrayEquals(new int[]{0}, new int[]{result[0]});
    }

    @Test
    public void testLargeNegativeValues() {
        assertArrayEquals(new int[]{100, 10000, 1000000}, test.sortedSquares(new int[]{-1000, -100, -10}));
    }
}
