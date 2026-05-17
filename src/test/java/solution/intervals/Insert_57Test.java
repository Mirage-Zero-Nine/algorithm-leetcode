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

    @Test public void testNewIntervalFillsGapMergingNeighbors() {
        // [1,3] gap [5,7] -> insert [3,5] merges all into [1,7]
        int[][] intervals = {{1, 3}, {5, 7}};
        int[][] expected = {{1, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{3, 5}));
    }

    @Test public void testNewIntervalContainedInsideExisting() {
        // [1,10] already covers [3,5], result unchanged
        int[][] intervals = {{1, 10}, {15, 20}};
        int[][] expected = {{1, 10}, {15, 20}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{3, 5}));
    }

    @Test public void testExistingContainedInsideNew() {
        // New [0,20] swallows [3,5],[7,9],[11,13]
        int[][] intervals = {{3, 5}, {7, 9}, {11, 13}};
        int[][] expected = {{0, 20}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{0, 20}));
    }

    @Test public void testOverlapsMultipleConsecutive() {
        // New [2,14] overlaps [1,3],[5,8],[10,12],[14,16]
        int[][] intervals = {{1, 3}, {5, 8}, {10, 12}, {14, 16}};
        int[][] expected = {{1, 16}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{2, 14}));
    }

    @Test public void testTouchingBoundaryPrepend() {
        // New [0,1] touches [1,3] -> merge to [0,3]
        int[][] intervals = {{1, 3}, {5, 7}};
        int[][] expected = {{0, 3}, {5, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{0, 1}));
    }

    @Test public void testNegativeCoordinates() {
        int[][] intervals = {{-10, -5}, {-3, -1}, {2, 4}};
        int[][] expected = {{-10, -5}, {-4, -1}, {2, 4}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{-4, -2}));
    }

    @Test public void testLarge1000IntervalsInsertMiddle() {
        int size = 1000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{i * 4, i * 4 + 1}; // gaps of 2 between intervals
        }
        // Insert in middle merging intervals 400-600 (indices)
        int[] newInterval = new int[]{400 * 4, 600 * 4 + 1};
        int[][] result = solver.insert(intervals, newInterval);
        // 400 before + 1 merged + 399 after = 800
        assert result.length == 800;
        assertArrayEquals(new int[]{1600, 2401}, result[400]);
    }

    @Test public void testSingleIntervalSameAsNew() {
        // Inserting exact duplicate
        int[][] intervals = {{2, 5}};
        int[][] expected = {{2, 5}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{2, 5}));
    }

    @Test public void testNewIntervalSpansEntireRange() {
        int[][] intervals = {{-100, -50}, {0, 10}, {20, 30}, {40, 50}};
        int[][] expected = {{-200, 200}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{-200, 200}));
    }
}
