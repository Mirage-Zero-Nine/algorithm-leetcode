package solutions.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi].
 * Return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Note that intervals which only touch at a point are non-overlapping.
 * For example, [1, 2] and [2, 3] are non-overlapping.
 * The published input has at most 100,000 intervals, with
 * {@code -10,000 <= starti < endi <= 10,000} for every interval.
 *
 * @author BorisMirage
 * Time: 2026/10/08 22:43
 * Created with IntelliJ IDEA
 */

public class EraseOverlapIntervals_435 {
    /**
     * Sorts by start and greedily keeps the interval with the earliest end when
     * intervals overlap, leaving the most room for later intervals. Intervals
     * that meet at an endpoint are compatible, so {@code [1, 2]} and
     * {@code [2, 3]} do not overlap. Returns the number removed.
     *
     * <p>The outer array is sorted in place; copy it first to preserve its order.
     * {@code null} and empty arrays return {@code 0}. Time is {@code O(n log n)};
     * the scan uses {@code O(1)} extra space and sorting may use {@code O(n)}.</p>
     *
     * @param intervals intervals to inspect; the outer array is sorted in place
     * @return the minimum number of intervals to remove
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        // corner cases
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Processing intervals by start makes each conflict local to the
        // interval currently retained as the rightmost non-overlapping one.
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int end = intervals[0][1], count = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] < end) {
                // The intervals overlap. Removing the one with the later end
                // preserves the greatest amount of room for future intervals.
                count++;
                end = Math.min(end, interval[1]);
            } else {
                // Touching endpoints are allowed, so interval[0] == end is
                // handled here as a non-overlap.
                end = interval[1];
            }
        }

        return count;
    }
}
