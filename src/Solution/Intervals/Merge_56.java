package Solution.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * @author BorisMirage
 * Time: 2018/06/30 17:19
 * Created with IntelliJ IDEA
 */

public class Merge_56 {
    /**
     * First, sort the array by each interval's first element (interval start).
     * After sorting, interval[i][0] <= interval[i + 1][0].
     * If interval[i + 1][0] > interval[i][1], then there is no overlap. Add previous interval to result.
     * Otherwise, there is an overlap. interval[i + 1][0] will be merged into previous interval.
     * The end of current interval depends on max(interval[i][1], interval[i + 1][1]).
     * Note that there is a final interval which should be added to result.
     *
     * @param intervals input Interval list
     * @return merged result
     */
    public int[][] merge(int[][] intervals) {

        /* Corner case */
        if (intervals.length <= 1) {
            return intervals;
        }

        List<int[]> out = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));     // sort intervals by start
        int start = intervals[0][0], end = intervals[0][1];

        for (int[] i : intervals) {

            if (i[0] > end) {               // if next interval's start is larger than previous end, then no overlap
                out.add(new int[]{start, end});     // previous overlap is ended, add to output
                start = i[0];                       // new interval's start and end
                end = i[1];
            } else {                        // otherwise, there is an overlap
                end = Math.max(end, i[1]);  // find the end of current overlap based on larger ending value
            }
        }

        out.add(new int[]{start, end});     // add last interval

        return out.toArray(new int[out.size()][]);
    }

    public static void main(String[] args) {
        Merge_56 test = new Merge_56();
        System.out.println(Arrays.deepToString(test.merge(new int[][]{{15, 18}, {2, 6}, {1, 3}, {8, 10}})));
    }
}

