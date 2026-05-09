package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link BusyStudent_1450}.
 */
public class BusyStudent_1450Test {

    private final BusyStudent_1450 solver = new BusyStudent_1450();

    @Test
    public void testSingleStudentActive() {
        int[] startTime = {4};
        int[] endTime = {4};
        int queryTime = 4;

        assertEquals(1, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testSingleStudentInactive() {
        int[] startTime = {4};
        int[] endTime = {4};
        int queryTime = 5;

        assertEquals(0, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testMultipleStudentsOneActive() {
        int[] startTime = {4, 2, 1};
        int[] endTime = {4, 3, 2};
        int queryTime = 4;

        assertEquals(1, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testMultipleStudentsAllActive() {
        int[] startTime = {1, 2, 3};
        int[] endTime = {3, 4, 5};
        int queryTime = 3;

        assertEquals(3, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testMultipleStudentsNoneActive() {
        int[] startTime = {1, 2, 3};
        int[] endTime = {2, 3, 4};
        int queryTime = 5;

        assertEquals(0, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testQueryAtStartBoundary() {
        int[] startTime = {1, 5, 10};
        int[] endTime = {3, 7, 12};
        int queryTime = 5;

        assertEquals(1, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testQueryAtEndBoundary() {
        int[] startTime = {1, 5, 10};
        int[] endTime = {3, 7, 12};
        int queryTime = 3;

        assertEquals(1, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testMultipleActiveStudents() {
        int[] startTime = {0, 0, 0};
        int[] endTime = {10, 10, 10};
        int queryTime = 5;

        assertEquals(3, solver.busyStudent(startTime, endTime, queryTime));
    }

    @Test
    public void testThrowsOnNullStartTime() {
        assertThrows(IllegalArgumentException.class, () -> solver.busyStudent(null, new int[]{1}, 1));
    }

    @Test
    public void testThrowsOnNullEndTime() {
        assertThrows(IllegalArgumentException.class, () -> solver.busyStudent(new int[]{1}, null, 1));
    }

    @Test
    public void testThrowsOnEmptyStartTime() {
        assertThrows(IllegalArgumentException.class, () -> solver.busyStudent(new int[]{}, new int[]{1}, 1));
    }

    @Test
    public void testThrowsOnNegativeQueryTime() {
        assertThrows(IllegalArgumentException.class, () -> solver.busyStudent(new int[]{1}, new int[]{2}, -1));
    }
}
