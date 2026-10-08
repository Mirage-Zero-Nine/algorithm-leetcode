package solutions.intervals;

import java.util.ArrayList;
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
     * Initializes a pending interval from {@code newInterval}, then scans the
     * already sorted rows once: emit rows before it, merge overlaps or
     * touching rows, and finalize it when a later row starts after its end.
     * The inputs stay unchanged, the result shares unchanged prefix rows,
     * {@code null} intervals return empty by extension, and no state is kept.
     *
     * <p>Time complexity: O(n), where n is the number of existing intervals.
     * The scan examines each existing row once.</p>
     *
     * <p>Auxiliary space complexity: O(n), for the temporary output-reference
     * list; this excludes the O(n) array returned to the caller. Existing rows
     * are reused only for the emitted prefix; merged and tail rows are new
     * two-element arrays.</p>
     *
     * @param intervals   sorted, non-overlapping existing intervals
     * @param newInterval interval to insert
     * @return the intervals after insertion and merging
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // corner case
        if (intervals == null) {
            return new int[0][0];
        }

        List<int[]> output = new ArrayList<>();
        int start = newInterval[0], end = newInterval[1];

        for (int[] interval : intervals) {
            if (interval[1] < start) {
                // This row is completely before the pending interval. Because
                // the input rows are sorted and pairwise disjoint, it and all
                // earlier rows are permanently finalized.
                output.add(interval);
            } else if (interval[0] > end) {
                // The current interval is complete. Strict comparisons make
                // endpoint-touching rows merge as required for closed ranges.
                output.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            } else {
                // The ranges overlap or touch, so expand the pending bounds.
                // Math.min is needed because newInterval may begin inside an
                // existing row; Merge_56 can initialize from the first row.
                start = Math.min(interval[0], start);
                end = Math.max(interval[1], end);
            }
        }

        // The last pending interval has no later row to finalize it.
        output.add(new int[]{start, end});
        return output.toArray(new int[output.size()][]);
    }
}
