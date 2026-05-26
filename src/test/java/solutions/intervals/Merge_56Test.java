package solutions.intervals;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Merge_56Test {
    private final Merge_56 solver = new Merge_56();

    @Test public void testBasic() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testMergeAll() {
        int[][] intervals = {{1, 4}, {4, 5}};
        int[][] expected = {{1, 5}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testSingle() {
        int[][] intervals = {{1, 2}};
        int[][] expected = {{1, 2}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testUnsorted() {
        int[][] intervals = {{15, 18}, {2, 6}, {1, 3}, {8, 10}};
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testNested() {
        int[][] intervals = {{1, 10}, {2, 4}, {6, 9}};
        int[][] expected = {{1, 10}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testNoOverlap() {
        int[][] intervals = {{1, 2}, {4, 5}, {7, 8}};
        int[][] expected = {{1, 2}, {4, 5}, {7, 8}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testAllMergeIntoOne() {
        int[][] intervals = {{1, 5}, {2, 7}, {3, 10}, {4, 12}};
        int[][] expected = {{1, 12}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testAdjacentTouching() {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}};
        int[][] expected = {{1, 4}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testSameIntervals() {
        int[][] intervals = {{1, 5}, {1, 5}, {1, 5}};
        int[][] expected = {{1, 5}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testSinglePointIntervals() {
        int[][] intervals = {{1, 1}, {2, 2}, {3, 3}};
        int[][] expected = {{1, 1}, {2, 2}, {3, 3}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testGiantCase() {
        int size = 10000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{i, i + 2}; // all overlap
        }
        int[][] result = solver.merge(intervals);
        assertArrayEquals(new int[][]{{0, size + 1}}, result);
    }

    @Test public void testEmptyInput() {
        assertArrayEquals(new int[][]{}, solver.merge(new int[][]{}));
    }

    @Test public void testReverseSorted() {
        int[][] intervals = {{10, 15}, {5, 8}, {1, 3}};
        int[][] expected = {{1, 3}, {5, 8}, {10, 15}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testContainedInterval() {
        int[][] intervals = {{1, 10}, {2, 5}};
        int[][] expected = {{1, 10}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testNegativeCoordinates() {
        int[][] intervals = {{-10, -3}, {-5, 0}, {1, 5}};
        int[][] expected = {{-10, 0}, {1, 5}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testLargeCoordinates() {
        int[][] intervals = {{Integer.MAX_VALUE - 10, Integer.MAX_VALUE}, {0, 1}, {Integer.MAX_VALUE - 5, Integer.MAX_VALUE}};
        int[][] expected = {{0, 1}, {Integer.MAX_VALUE - 10, Integer.MAX_VALUE}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testAllIdenticalIntervals() {
        int[][] intervals = {{3, 7}, {3, 7}, {3, 7}, {3, 7}, {3, 7}};
        int[][] expected = {{3, 7}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testTouchingIntervalsAreMerged() {
        // impl merges when end == next start (i[0] > end is false)
        int[][] intervals = {{1, 3}, {3, 5}, {5, 7}};
        int[][] expected = {{1, 7}};
        assertArrayEquals(expected, solver.merge(intervals));
    }

    @Test public void testRandomLargeListProperties() {
        Random rng = new Random(42L);
        int n = 1000;
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = rng.nextInt(10000) - 5000;
            int b = a + rng.nextInt(100);
            intervals[i] = new int[]{a, b};
        }

        int[][] merged = solver.merge(intervals);

        // merged length <= input length
        assertTrue(merged.length <= n);
        assertTrue(merged.length >= 1);

        // merged intervals are sorted and non-overlapping
        for (int i = 0; i < merged.length - 1; i++) {
            assertTrue(merged[i][0] <= merged[i][1]);
            assertTrue(merged[i][1] < merged[i + 1][0], "Merged intervals should not overlap or touch");
        }

        // every original interval is contained in some merged interval
        for (int[] orig : intervals) {
            boolean contained = false;
            for (int[] m : merged) {
                if (m[0] <= orig[0] && orig[1] <= m[1]) {
                    contained = true;
                    break;
                }
            }
            assertTrue(contained, "Original interval [" + orig[0] + "," + orig[1] + "] not contained in any merged interval");
        }
    }

    @Test public void testIdempotency() {
        int[][] intervals = {{5, 10}, {1, 3}, {2, 6}, {8, 12}, {15, 20}};
        int[][] once = solver.merge(intervals);
        int[][] twice = solver.merge(once.clone());
        assertArrayEquals(once, twice);
    }
}
