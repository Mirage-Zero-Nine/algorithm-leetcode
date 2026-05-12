package solution.intervals;

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
}
