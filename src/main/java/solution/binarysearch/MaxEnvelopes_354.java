package solution.binarysearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another iff both the width and height of one envelope is greater than the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Note:
 * 1. Rotation is not allowed.
 *
 * @author BorisMirage
 * Time: 2019/06/23 16:17
 * Created with IntelliJ IDEA
 */

public class MaxEnvelopes_354 {
    /**
     * Sort the array. Ascend on width and descend on height if width are same.
     * Find the longest increasing subsequence based on height.
     *
     * @param envelopes given list with width and height
     * @return maximum number of envelopes can be filled into
     */
    public int maxEnvelopes(int[][] envelopes) {

        /* Corner case */
        if (envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, new EnvelopeComparator());

        int[] tails = new int[envelopes.length];

        int size = 0;

        for (int[] envelope : envelopes) {
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < envelope[1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = envelope[1];
            if (left == size) {
                size++;
            }
        }
        return size;
    }

    /**
     * Sort envelops descending on height when they have same width.
     */
    static class EnvelopeComparator implements Comparator<int[]> {
        public int compare(int[] e1, int[] e2) {
            return e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0];
        }
    }
}
