package solution.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/18 17:51
 * Created with IntelliJ IDEA
 */

public class BusyStudent1450Test {
    private final BusyStudent_1450 test = new BusyStudent_1450();

    @Test
    public void test() {
        int[] startTime = {1, 2, 3};
        int[] endTime = {3, 2, 7};
        int queryTime = 4;
        int expected = 1;
        int result = test.busyStudent(startTime, endTime, queryTime);
        assertEquals(expected, result);

        int[] startTime2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] endTime2 = {10, 10, 10, 10, 10, 10, 10, 10, 10};
        int queryTime2 = 5;
        int expected2 = 5;
        int result2 = test.busyStudent(startTime2, endTime2, queryTime2);
        assertEquals(expected2, result2);

        int[] startTime3 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] endTime3 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int queryTime3 = 1;
        int expected3 = 9;
        int result3 = test.busyStudent(startTime3, endTime3, queryTime3);
        assertEquals(expected3, result3);
    }
}