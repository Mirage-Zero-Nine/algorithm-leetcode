package solution.intervals;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 * Return a sorted list of intervals after all such removals.
 *
 * @author BorisMirage
 * Time: 2019/12/02 17:17
 * Created with IntelliJ IDEA
 */

public class RemoveInterval_1272 {
    /**
     * Similar to insert intervals. The intervals are sorted.
     * Therefore, if remove interval has no overlapped, directly add current interval to output.
     * Otherwise, check the remove interval boundary, and find if there is interval that should not be removed.
     *
     * @param intervals   given intervals
     * @param toBeRemoved interval to be removed
     * @return intervals after remove
     */
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> out = new LinkedList<>();

        if (intervals == null) {
            return out;
        }

        int[] tmp = toBeRemoved;

        for (int[] i : intervals) {
            if (tmp[0] > i[1]) {
                helper(out, i[0], i[1]);
            } else if (tmp[1] < i[0]) {
                helper(out, i[0], i[1]);
                tmp = i;
            } else {
                if (tmp[0] > i[0]) {            // [interval[0], removed[0]] should be added
                    helper(out, i[0], tmp[0]);
                }
                if (tmp[1] < i[1]) {            // [interval[1], removed[1]] should be added
                    helper(out, tmp[1], i[1]);
                }
            }
        }

        return out;
    }

    /**
     * Add interval to list.
     *
     * @param out output list
     * @param i   left boundary of interval
     * @param j   right boundary of interval
     */
    private void helper(List<List<Integer>> out, int i, int j) {
        out.add(new LinkedList<Integer>() {{
            add(i);
            add(j);
        }});
    }
}
