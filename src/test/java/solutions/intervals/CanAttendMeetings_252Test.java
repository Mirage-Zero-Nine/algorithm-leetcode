package solutions.intervals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanAttendMeetings_252Test {
    private final CanAttendMeetings_252 solver = new CanAttendMeetings_252();

    @Test public void testNoOverlap() {
        assertTrue(solver.canAttendMeetings(new int[][]{{0, 5}, {5, 10}, {15, 20}}));
    }

    @Test public void testOverlap() {
        assertFalse(solver.canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
    }

    @Test public void testEmpty() {
        assertTrue(solver.canAttendMeetings(new int[][]{}));
    }

    @Test public void testSingle() {
        assertTrue(solver.canAttendMeetings(new int[][]{{1, 10}}));
    }

    @Test public void testUnsortedNoOverlap() {
        assertTrue(solver.canAttendMeetings(new int[][]{{5, 10}, {0, 5}, {15, 20}}));
    }

    @Test public void testNull() {
        assertTrue(solver.canAttendMeetings(null));
    }

    @Test public void testTwoOverlapping() {
        assertFalse(solver.canAttendMeetings(new int[][]{{1, 5}, {3, 7}}));
    }

    @Test public void testAdjacentExact() {
        // [0,5] and [5,10]: temp[1]=5 > intervals[i][0]=5 is false, so no overlap
        assertTrue(solver.canAttendMeetings(new int[][]{{0, 5}, {5, 10}}));
    }

    @Test public void testAllSameStart() {
        assertFalse(solver.canAttendMeetings(new int[][]{{1, 3}, {1, 5}, {1, 7}}));
    }

    @Test public void testNestedIntervals() {
        assertFalse(solver.canAttendMeetings(new int[][]{{1, 10}, {2, 3}, {4, 5}}));
    }

    @Test public void testGiantNoOverlap() {
        int size = 10000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{i * 2, i * 2 + 1};
        }
        assertTrue(solver.canAttendMeetings(intervals));
    }
}
