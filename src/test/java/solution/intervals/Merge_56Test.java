package solution.intervals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
}
