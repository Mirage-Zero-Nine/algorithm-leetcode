package solution.intervals;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinMeetingRooms_253Test {
    private final MinMeetingRooms_253 solver = new MinMeetingRooms_253();

    @Test public void testBasicPoints() {
        assertEquals(2, solver.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
    }

    @Test public void testNoOverlap() {
        assertEquals(1, solver.minMeetingRooms(new int[][]{{7, 10}, {2, 4}}));
    }

    @Test public void testEmpty() {
        assertEquals(0, solver.minMeetingRooms(new int[][]{}));
    }

    @Test public void testNull() {
        assertEquals(0, solver.minMeetingRooms(null));
    }

    @Test public void testHeapApproach() {
        assertEquals(2, solver.heap(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
    }

    @Test public void testTwoPointers() {
        assertEquals(2, solver.twoPointers(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
    }

    @Test public void testComplexCase() {
        // 3 overlapping meetings
        assertEquals(3, solver.minMeetingRooms(new int[][]{{26, 29}, {19, 26}, {19, 28}, {4, 19}, {4, 25}}));
    }

    @Test public void testSingleMeeting() {
        assertEquals(1, solver.minMeetingRooms(new int[][]{{1, 5}}));
    }

    @Test public void testAdjacentMeetings() {
        // End at same time as next start: no overlap per comparator (end before start)
        assertEquals(1, solver.minMeetingRooms(new int[][]{{0, 5}, {5, 10}, {10, 15}}));
    }

    @Test public void testAllOverlapping() {
        assertEquals(4, solver.minMeetingRooms(new int[][]{{0, 10}, {1, 11}, {2, 12}, {3, 13}}));
    }

    @Test public void testGiantCase() {
        int size = 5000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{0, 100}; // all overlap
        }
        assertEquals(size, solver.minMeetingRooms(intervals));
    }

    @Test public void testAllMeetingsAtSameTime() {
        // 5 meetings all [1,10] -> need 5 rooms
        assertEquals(5, solver.minMeetingRooms(new int[][]{{1,10},{1,10},{1,10},{1,10},{1,10}}));
    }

    @Test public void testSequentialNonOverlapping() {
        // All sequential, no overlap -> 1 room
        assertEquals(1, solver.minMeetingRooms(new int[][]{{0,1},{2,3},{4,5},{6,7},{8,9}}));
    }

    @Test public void testTouchingBoundaryDoesNotOverlap() {
        // [a,b] and [b,c] should NOT need 2 rooms per implementation (end processed before start at same time)
        assertEquals(1, solver.minMeetingRooms(new int[][]{{0,5},{5,10},{10,15},{15,20}}));
    }

    @Test public void testSameStartDifferentEnds() {
        // All start at 0 but end at different times -> all overlap at time 0
        assertEquals(5, solver.minMeetingRooms(new int[][]{{0,1},{0,2},{0,3},{0,4},{0,5}}));
    }

    @Test public void testSameEndDifferentStarts() {
        // All end at 10 but start at different times -> all overlap at time 9
        assertEquals(5, solver.minMeetingRooms(new int[][]{{1,10},{3,10},{5,10},{7,10},{9,10}}));
    }

    @Test public void testWideSpreadOfTimes() {
        // Meetings spread far apart -> no overlap
        assertEquals(1, solver.minMeetingRooms(new int[][]{{0,1},{1000000,1000001},{2000000,2000001}}));
    }

    @Test public void testRandom1000MeetingsSeed42() {
        // Generate 1000 random meetings, cross-check against sweep-line reference
        Random rng = new Random(42L);
        int n = 1000;
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            int start = rng.nextInt(100000);
            int end = start + 1 + rng.nextInt(1000);
            intervals[i] = new int[]{start, end};
        }

        // Sweep-line reference using TreeMap
        TreeMap<Integer, Integer> timeline = new TreeMap<>();
        for (int[] iv : intervals) {
            timeline.merge(iv[0], 1, Integer::sum);
            timeline.merge(iv[1], -1, Integer::sum);
        }
        int expected = 0, running = 0;
        for (int delta : timeline.values()) {
            running += delta;
            expected = Math.max(expected, running);
        }

        assertEquals(expected, solver.minMeetingRooms(intervals));
    }

    @Test public void testPropertyResultBounds() {
        // Property: 1 <= result <= n for non-empty input
        int[][] intervals = {{1,5},{2,6},{3,7},{8,12},{9,13}};
        int result = solver.minMeetingRooms(intervals);
        assertTrue(result >= 1, "Result must be >= 1 for non-empty input");
        assertTrue(result <= intervals.length, "Result must be <= number of meetings");
    }

    @Test public void testPropertyEmptyResultZero() {
        // Property: result == 0 for empty input
        assertEquals(0, solver.minMeetingRooms(new int[][]{}));
        assertEquals(0, solver.minMeetingRooms(null));
    }
}
