package solution.array;

/**
 * Given two integer arrays startTime and endTime and given an integer queryTime.
 * The ith student started doing their homework at the time startTime[i] and finished it at time endTime[i].
 * Return the number of students doing their homework at time queryTime.
 * More formally, return the number of students where queryTime lays in the interval [startTime[i], endTime[i]].
 *
 * @author BorisMirage
 * Time: 2020/05/19 14:58
 * Created with IntelliJ IDEA
 */

public class BusyStudent_1450 {
    /**
     * Simply traverse the array and check with each student. If the query time is between the start and end, add one.
     *
     * @param startTime start time array
     * @param endTime   end time array
     * @param queryTime target time
     * @return the number of students where queryTime lays in the interval [startTime[i], endTime[i]]
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {

        /* Corner case */
        if (startTime == null || startTime.length == 0 || endTime == null || endTime.length == 0 || queryTime < 0) {
            throw new IllegalArgumentException("Invalid input! ");
        }

        int out = 0;
        for (int i = 0; i < startTime.length; i++) {
            int start = startTime[i], end = endTime[i];
            if (start <= queryTime && end >= queryTime) {
                out++;
            }
        }

        return out;
    }
}
