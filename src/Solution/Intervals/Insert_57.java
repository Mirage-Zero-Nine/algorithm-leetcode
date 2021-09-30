package Solution.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * It may be assumed that the intervals were initially sorted according to their start times.
 * Only one interval will be given.
 *
 * @author BorisMirage
 * Time: 2018/07/10 00:02
 * Created with IntelliJ IDEA
 */

public class Insert_57 {
    /**
     * The input interval is sorted. Therefore, starts at the first element in intervals.
     * There are three conditions:
     * 1. new interval is later than current interval: directly add current interval to output
     * 2. new interval is at front of interval: directly add current interval to output and set interval to current one
     * 3. overlap: merge two intervals and wait for later interval to check if there is more overlap
     * Finally, after the iteration, add final temp value into list.
     *
     * @param intervals   interval list
     * @param newInterval new interval to be inserted
     * @return new array with input interval inserted
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        /* Corner case */
        if (intervals == null || newInterval == null || newInterval.length == 0) {
            return intervals;
        }

        List<int[]> out = new ArrayList<>();
        int[] temp = newInterval;
        for (int[] i : intervals) {

            if (temp[0] > i[1]) { // case 1: not reached yet (start > end)
                out.add(i);
            } else if (temp[1] < i[0]) { // case 2: new interval is added
                out.add(temp);
                temp = i; // replaced with new sorted non-overlapped interval, since all intervals need to be added
            } else { // there is overlap, merge two intervals by finding minimum start and maximum end
                temp = new int[]{Math.min(temp[0], i[0]), Math.max(temp[1], i[1])};
            }
        }

        out.add(temp);

        return out.toArray(new int[out.size()][]);
    }

    public static void main(String[] args) {
        Insert_57 test = new Insert_57();
        System.out.println(Arrays.deepToString(test.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
    }
}
