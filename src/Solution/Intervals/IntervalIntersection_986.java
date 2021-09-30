package Solution.Intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * Return the intersection of these two interval lists.
 *
 * @author BorisMirage
 * Time: 2019/07/25 17:31
 * Created with IntelliJ IDEA
 */

public class IntervalIntersection_986 {
    /**
     * Two pointers, one point at A and one point at B.
     * If there is interval, add result to list, otherwise move pointers.
     * If end of overlapped interval is equal to the interval in list A, then move pointer in list A.
     * Otherwise, move pointer at list B.
     *
     * @param firstList  first array
     * @param secondList second array
     * @return intersection of these two interval lists
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int p1 = 0, p2 = 0, start, end, m = firstList.length, n = secondList.length;
        List<int[]> out = new ArrayList<>();

        while (p1 < m && p2 < n) {
            int[] first = firstList[p1], second = secondList[p2];
            start = Math.max(first[0], second[0]); // find if there is an overlap
            end = Math.min(first[1], second[1]);

            if (start <= end) {
                out.add(new int[]{start, end});
            }

            if (end == first[1]) {
                p1++;
            } else {
                p2++;
            }
        }

        return out.toArray(new int[out.size()][]);
    }
}
