package solutions.intervals;

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
     * Sorts intervals by their start, then scans them from left to right. The
     * variables {@code start} and {@code end} describe the one merged interval
     * that is still waiting to be added to the output. Because sorting puts
     * later starts at or after earlier starts, an interval with
     * {@code interval[0] > end} is separated by a gap and the waiting interval
     * is complete. Otherwise the intervals overlap or touch, so {@code end} is
     * extended to the larger end value. Taking the larger value is needed when
     * a new interval is contained inside the waiting interval or extends it.
     * After the scan, the waiting interval is added because no later interval
     * remains to reveal a gap.
     *
     * <p>Sorting and scanning take {@code O(n log n)} time for {@code n}
     * intervals. The output can contain up to {@code n} newly allocated rows,
     * and the object-array sort may use temporary storage, so auxiliary space
     * is {@code O(n)}. Sorting reorders the caller's outer array, but does not
     * modify the two-number rows inside it.</p>
     *
     * @param intervals intervals to merge; valid rows contain {@code [start, end]}
     * @return sorted, pairwise-disjoint merged intervals, or an empty matrix for
     * the documented empty-input cases
     */
    public int[][] merge(int[][] intervals) {
        // The documented fallback for an empty or unusable first row is an empty matrix.
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return new int[0][0];
        }

        // Sorting makes starts nondecreasing, so the next interval cannot begin
        // before the interval currently being built.
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> output = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] > end) {
                // A strict gap means this interval cannot overlap or touch the
                // current one, so the current interval can be finalized now.
                output.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            } else {
                // Starts at end still touch and must be merged. Keep the
                // farther end so a contained interval cannot shorten the result.
                end = Math.max(end, interval[1]);
            }
        }

        // Earlier intervals are finalized when a later gap is found. The last
        // waiting interval has no later item to trigger that branch.
        output.add(new int[]{start, end});
        return output.toArray(new int[output.size()][]);
    }
}
