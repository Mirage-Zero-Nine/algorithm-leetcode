package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

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
            assertTrue(result[i] >= result[i - 1]);
        }
        assertArrayEquals(new int[]{0}, new int[]{result[0]});
    }

    @Test
    public void testLargeNegativeValues() {
        assertArrayEquals(new int[]{100, 10000, 1000000}, test.sortedSquares(new int[]{-1000, -100, -10}));
    }

    @Test
    public void testEmptyArray() {
        assertArrayEquals(new int[]{}, test.sortedSquares(new int[]{}));
    }

    @Test
    public void testSingleNegativeElement() {
        assertArrayEquals(new int[]{25}, test.sortedSquares(new int[]{-5}));
    }

    @Test
    public void testAllNonNegativeSquaredInOrder() {
        assertArrayEquals(new int[]{0, 1, 4, 9, 16, 25}, test.sortedSquares(new int[]{0, 1, 2, 3, 4, 5}));
    }

    @Test
    public void testAllNonPositiveReversedSquares() {
        assertArrayEquals(new int[]{1, 4, 9, 16, 25}, test.sortedSquares(new int[]{-5, -4, -3, -2, -1}));
    }

    @Test
    public void testMixNegativesStartPositivesEnd() {
        assertArrayEquals(new int[]{1, 4, 9, 16, 25, 36}, test.sortedSquares(new int[]{-6, -4, -1, 2, 3, 5}));
    }

    @Test
    public void testAllSameValue() {
        assertArrayEquals(new int[]{9, 9, 9, 9}, test.sortedSquares(new int[]{-3, -3, -3, -3}));
        assertArrayEquals(new int[]{4, 4, 4}, test.sortedSquares(new int[]{2, 2, 2}));
    }

    @Test
    public void testNegativesAndPositivesSameMagnitudes() {
        assertArrayEquals(new int[]{1, 1, 4, 4, 9, 9, 16, 16}, test.sortedSquares(new int[]{-4, -3, -2, -1, 1, 2, 3, 4}));
    }

    @Test
    public void testLargeArray1000SortedCrossCheckBruteForce() {
        Random rng = new Random(42L);
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = rng.nextInt(2001) - 1000; // range [-1000, 1000]
        }
        Arrays.sort(nums);

        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) {
            expected[i] = nums[i] * nums[i];
        }
        Arrays.sort(expected);

        assertArrayEquals(expected, test.sortedSquares(nums));
    }

    @Test
    public void testPropertyResultIsSortedAscending() {
        int[][] inputs = {
            {-10, -5, -3, 0, 2, 7, 11},
            {-100, -50, -1, 0, 1, 50, 100},
            {-1, 0, 1}
        };
        for (int[] nums : inputs) {
            int[] result = test.sortedSquares(nums);
            for (int i = 1; i < result.length; i++) {
                assertTrue(result[i] >= result[i - 1],
                    "Result not sorted at index " + i + " for input " + Arrays.toString(nums));
            }
        }
    }

    @Test
    public void testPropertyMultisetOfSquaresMatches() {
        int[] nums = {-7, -3, -1, 0, 2, 4, 8};
        int[] result = test.sortedSquares(nums);
        int[] expectedSquares = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            expectedSquares[i] = nums[i] * nums[i];
        }
        Arrays.sort(expectedSquares);
        Arrays.sort(result);
        assertArrayEquals(expectedSquares, result);
        assertEquals(nums.length, result.length);
    }
}
