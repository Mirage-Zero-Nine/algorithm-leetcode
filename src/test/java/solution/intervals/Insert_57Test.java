package solution.intervals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Insert_57Test {
    private final Insert_57 solver = new Insert_57();

    @Test public void testNoOverlap() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[][] expected = {{1, 3}, {4, 5}, {6, 9}};
        // Wait - [4,5] needs no merge with [1,3] or [6,9]
        // But inserting [4,5] into [{1,3},{6,9}] produces [[1,3],[4,5],[6,9]]
        assertArrayEquals(expected, solver.insert(intervals, new int[]{4, 5}));
    }

    @Test public void testOverlap() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] expected = {{1, 2}, {3, 10}, {12, 16}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{4, 8}));
    }

    @Test public void testEmpty() {
        int[][] intervals = {};
        int[][] expected = {{5, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{5, 7}));
    }

    @Test public void testInsertAtBeginning() {
        int[][] intervals = {{3, 5}, {6, 7}};
        int[][] expected = {{1, 2}, {3, 5}, {6, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{1, 2}));
    }

    @Test public void testInsertAtEnd() {
        int[][] intervals = {{1, 2}, {3, 4}};
        int[][] expected = {{1, 2}, {3, 4}, {5, 6}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{5, 6}));
    }

    @Test public void testMergeAll() {
        int[][] intervals = {{1, 3}, {4, 6}, {7, 9}};
        int[][] expected = {{0, 10}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{0, 10}));
    }

    @Test public void testNullIntervals() {
        assertArrayEquals(null, solver.insert(null, new int[]{1, 2}));
    }

    @Test public void testNullNewInterval() {
        int[][] intervals = {{1, 2}};
        assertArrayEquals(intervals, solver.insert(intervals, null));
    }

    @Test public void testEmptyNewInterval() {
        int[][] intervals = {{1, 2}};
        assertArrayEquals(intervals, solver.insert(intervals, new int[]{}));
    }

    @Test public void testTouchingBoundary() {
        int[][] intervals = {{1, 5}, {6, 10}};
        int[][] expected = {{1, 10}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{5, 6}));
    }

    @Test public void testGiantCase() {
        int size = 10000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{i * 3, i * 3 + 1};
        }
        // Insert interval that doesn't overlap with any
        int[][] result = solver.insert(intervals, new int[]{size * 3, size * 3 + 1});
        // Should have size+1 intervals
        assertArrayEquals(new int[]{size * 3, size * 3 + 1}, result[size]);
    }
}
