package solution.slidingwindow;

/**
 * There are several cards arranged in a row.
 * Each card has an associated number of points The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 * @author BorisMirage
 * Time: 2020/04/27 17:04
 * Created with IntelliJ IDEA
 */

public class MaxScore_1423 {
    /**
     * Sliding window problem.
     * Each time, the available card is at the end of array.
     * Therefore, form a circular array and the range of available cards is fixed.
     * The range is from n - k to n + k, where n is the length of array.
     * The n - k represented all cards are taken from right, and n + k represents all cards taken from left.
     * The window size is k, move the window from n - k to n + k, and find the max sum.
     *
     * @param cardPoints given array
     * @param k          select k cards
     * @return the maximum score can be obtained
     */
    public int maxScore(int[] cardPoints, int k) {

        int out = 0, score = 0, n = cardPoints.length;
        int start = n - k;      // start at the last kth element

        for (int i = start; i < n + k; ++i) {       // the valid subarray is from n - k to n + k
            score += cardPoints[i % n];

            if (i - start >= k) {       // check window size, if window size exceed k then shrink the window
                score -= cardPoints[(i - k) % n];
            }
            out = Math.max(score, out);
        }

        return out;
    }
}
