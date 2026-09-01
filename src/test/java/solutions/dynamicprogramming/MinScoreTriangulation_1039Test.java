package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinScoreTriangulation_1039Test {

    private final MinScoreTriangulation_1039 test = new MinScoreTriangulation_1039();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minScoreTriangulation(new int[]{1, 2, 3}));
        assertEquals(144, test.minScoreTriangulation(new int[]{3, 7, 4, 5}));
    }

    @Test
    public void testKnownCases() {
        assertEquals(13, test.minScoreTriangulation(new int[]{1, 3, 1, 4, 1, 5}));
        assertEquals(3, test.minScoreTriangulation(new int[]{1, 1, 1, 2}));
        assertEquals(3, test.minScoreTriangulation(new int[]{1, 1, 1, 1, 1}));
        assertEquals(375, test.minScoreTriangulation(new int[]{5, 5, 5, 5, 5}));
        assertEquals(16, test.minScoreTriangulation(new int[]{1, 2, 3, 1, 2, 3}));
    }

    @Test
    public void testExhaustiveSmallPolygonsAgainstBruteForce() {
        // Exhaust every value sequence for n = 3..6 using values 1..4.
        for (int n = 3; n <= 6; n++) {
            assertAllValueSequences(n, 4, new int[n], 0);
        }

        // Add a seventh vertex while keeping the Cartesian product small.
        assertAllValueSequences(7, 3, new int[7], 0);
    }

    private void assertAllValueSequences(int n, int maximumValue, int[] values, int index) {
        if (index == n) {
            assertEquals(bruteForceMinimum(values), test.minScoreTriangulation(values),
                    "values=" + java.util.Arrays.toString(values));
            return;
        }

        for (int value = 1; value <= maximumValue; value++) {
            values[index] = value;
            assertAllValueSequences(n, maximumValue, values, index + 1);
        }
    }

    /**
     * Independent exhaustive oracle: enumerate every possible diagonal choice
     * recursively, without memoization or use of the solution implementation.
     */
    private int bruteForceMinimum(int[] values) {
        return bruteForceMinimum(values, 0, values.length - 1);
    }

    private int bruteForceMinimum(int[] values, int first, int last) {
        if (last - first < 2) {
            return 0;
        }

        int minimum = Integer.MAX_VALUE;
        for (int middle = first + 1; middle < last; middle++) {
            int score = bruteForceMinimum(values, first, middle)
                    + bruteForceMinimum(values, middle, last)
                    + values[first] * values[middle] * values[last];
            minimum = Math.min(minimum, score);
        }
        return minimum;
    }
}
