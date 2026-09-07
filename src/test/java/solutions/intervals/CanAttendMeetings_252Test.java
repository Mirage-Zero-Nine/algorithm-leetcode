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
@Test
    public void testEveryPairOfPositiveLengthSmallIntervals() {
        for (int a = 0; a < 6; a++) for (int b = a + 1; b <= 6; b++)
            for (int c = 0; c < 6; c++) for (int d = c + 1; d <= 6; d++) {
                boolean expected = b <= c || d <= a;
                org.junit.jupiter.api.Assertions.assertEquals(expected,
                        solver.canAttendMeetings(new int[][]{{a, b}, {c, d}}),
                        java.util.Arrays.toString(new int[]{a, b, c, d}));
            }
    }

    @Test
    public void testOverlapOnlyAfterLongUnsortedPrefix() {
        int[][] meetings = new int[1001][2];
        for (int i = 0; i < 1000; i++) meetings[i] = new int[]{3 * (999 - i), 3 * (999 - i) + 2};
        meetings[1000] = new int[]{2998, 3001};
        assertFalse(solver.canAttendMeetings(meetings));
    }

    @Test
    public void testExtremeNonnegativeTimesRemainOrdered() {
        assertTrue(solver.canAttendMeetings(
                new int[][]{{Integer.MAX_VALUE - 1, Integer.MAX_VALUE}, {0, 1}, {1, 2}}));
    }
}
