package solution.intervals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntervalIntersection_986Test {
    private final IntervalIntersection_986 solver = new IntervalIntersection_986();

    @Test public void testBasic() {
        int[][] first = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] second = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] expected = {{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testNoIntersection() {
        int[][] first = {{1, 3}};
        int[][] second = {{5, 10}};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testFullOverlap() {
        int[][] first = {{1, 10}};
        int[][] second = {{2, 5}};
        int[][] expected = {{2, 5}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testEmptyFirst() {
        int[][] first = {};
        int[][] second = {{1, 2}};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testEndpointOverlap() {
        int[][] first = {{1, 5}};
        int[][] second = {{5, 10}};
        int[][] expected = {{5, 5}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testEmptySecond() {
        int[][] first = {{1, 2}};
        int[][] second = {};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testBothEmpty() {
        int[][] first = {};
        int[][] second = {};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testIdenticalIntervals() {
        int[][] first = {{1, 5}, {10, 15}};
        int[][] second = {{1, 5}, {10, 15}};
        int[][] expected = {{1, 5}, {10, 15}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testSinglePointIntervals() {
        int[][] first = {{3, 3}, {7, 7}};
        int[][] second = {{3, 5}, {6, 8}};
        int[][] expected = {{3, 3}, {7, 7}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testMultipleOverlapsWithOneInterval() {
        int[][] first = {{0, 100}};
        int[][] second = {{1, 2}, {5, 10}, {20, 30}};
        int[][] expected = {{1, 2}, {5, 10}, {20, 30}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testGiantCase() {
        int size = 1000;
        int[][] first = new int[size][2];
        int[][] second = new int[size][2];
        for (int i = 0; i < size; i++) {
            first[i] = new int[]{i * 4, i * 4 + 2};
            second[i] = new int[]{i * 4 + 1, i * 4 + 3};
        }
        int[][] result = solver.intervalIntersection(first, second);
        // Each pair overlaps at [i*4+1, i*4+2]
        for (int i = 0; i < size; i++) {
            assertArrayEquals(new int[]{i * 4 + 1, i * 4 + 2}, result[i]);
        }
    }

    @Test public void testDisjointMultipleIntervals() {
        int[][] first = {{1, 2}, {5, 6}, {9, 10}};
        int[][] second = {{3, 4}, {7, 8}, {11, 12}};
        assertArrayEquals(new int[][]{}, solver.intervalIntersection(first, second));
    }

    @Test public void testNegativeCoordinates() {
        int[][] first = {{-10, -5}, {-3, -1}, {2, 4}};
        int[][] second = {{-7, -2}, {0, 3}};
        int[][] expected = {{-7, -5}, {-3, -2}, {2, 3}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testLargeCoordinatesNearIntMax() {
        int max = Integer.MAX_VALUE;
        int[][] first = {{max - 100, max - 50}, {max - 10, max}};
        int[][] second = {{max - 80, max - 40}, {max - 5, max}};
        int[][] expected = {{max - 80, max - 50}, {max - 5, max}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testMultipleIntersectionsPerInterval() {
        // second has one large interval that intersects multiple in first
        int[][] first = {{1, 3}, {5, 7}, {9, 11}, {13, 15}};
        int[][] second = {{2, 14}};
        int[][] expected = {{2, 3}, {5, 7}, {9, 11}, {13, 14}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testOneListContainedInsideOther() {
        // every interval in second is contained inside an interval in first
        int[][] first = {{0, 20}, {30, 50}};
        int[][] second = {{2, 5}, {8, 10}, {32, 35}, {40, 45}};
        int[][] expected = {{2, 5}, {8, 10}, {32, 35}, {40, 45}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testSinglePointTouchingBoundaries() {
        // intervals touch only at single points
        int[][] first = {{1, 3}, {5, 7}, {9, 11}};
        int[][] second = {{3, 5}, {7, 9}};
        int[][] expected = {{3, 3}, {5, 5}, {7, 7}, {9, 9}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testRandom1000IntervalsBruteForce() {
        Random rng = new Random(42L);
        int[][] first = generateSortedDisjoint(1000, rng);
        int[][] second = generateSortedDisjoint(1000, rng);

        int[][] actual = solver.intervalIntersection(first, second);
        int[][] expected = bruteForceIntersection(first, second);
        assertArrayEquals(expected, actual);
    }

    @Test public void testResultSortedAndNonOverlapping() {
        Random rng = new Random(42L);
        int[][] first = generateSortedDisjoint(500, rng);
        int[][] second = generateSortedDisjoint(500, rng);

        int[][] result = solver.intervalIntersection(first, second);
        for (int i = 0; i < result.length; i++) {
            assertTrue(result[i][0] <= result[i][1], "Each interval must have start <= end");
            if (i > 0) {
                assertTrue(result[i][0] > result[i - 1][1], "Result must be sorted and non-overlapping");
            }
        }
    }

    private int[][] generateSortedDisjoint(int count, Random rng) {
        int[][] intervals = new int[count][2];
        int cursor = rng.nextInt(100);
        for (int i = 0; i < count; i++) {
            int start = cursor;
            int end = start + 1 + rng.nextInt(20);
            intervals[i] = new int[]{start, end};
            cursor = end + 1 + rng.nextInt(10);
        }
        return intervals;
    }

    private int[][] bruteForceIntersection(int[][] a, int[][] b) {
        List<int[]> result = new ArrayList<>();
        for (int[] ai : a) {
            for (int[] bi : b) {
                int start = Math.max(ai[0], bi[0]);
                int end = Math.min(ai[1], bi[1]);
                if (start <= end) {
                    result.add(new int[]{start, end});
                }
            }
        }
        result.sort((x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]);
        return result.toArray(new int[0][]);
    }
}
