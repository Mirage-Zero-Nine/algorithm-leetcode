package solution.intervals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
