package solutions.binarysearch;

import java.util.Arrays;

/**
 * Koko loves to eat bananas.
 * There are N piles of bananas, the i-th pile has piles[i] bananas. The guards have gone and will come back in H hours.
 * Koko can decide her bananas-per-hour eating speed of K.
 * Each hour, she chooses some pile of bananas, and eats K bananas from that pile.
 * If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
 * Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
 * Return the minimum integer K such that she can eat all the bananas within H hours.
 * Note:
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * @author BorisMirage
 */

public class MinEatingSpeed_875 {
    /**
     * Returns the smallest speed that finishes all piles within the deadline.
     *
     * <p>For a fixed speed, a pile of size {@code n} takes
     * {@code ceil(n / speed)} hours. If a speed is feasible, every larger
     * speed is feasible too; therefore binary search discards the upper half
     * after a feasible midpoint and discards the lower half otherwise.</p>
     *
     * <p>Complexity: {@code O(n log m)} time and {@code O(1)} auxiliary space, where
     * {@code n = piles.length} and {@code m = max(piles)}.</p>
     *
     * @param piles banana counts, one positive count per pile
     * @param h     maximum number of hours available
     * @return the minimum feasible positive eating speed
     */
    public int minEatingSpeed(int[] piles, int h) {
        // corner case
        if (piles.length == 1) {
            return (piles[0] - 1) / h + 1;
        }
        
        int left = 1, right = Arrays.stream(piles).max().orElseThrow();

        // Invariant: the answer is always in [left, right], and left is >= 1.
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinishInTime(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Tests whether every pile can be consumed within the target number of
     * hours at the supplied speed.
     *
     * <p>Each pile is independent because Koko may work on only one pile per
     * hour, so the total is the sum of the per-pile ceilings. The arithmetic
     * expression {@code (n - 1) / speed + 1} computes that ceiling using
     * integer arithmetic. Because the method returns as soon as the running total exceeds
     * {@code targetHours}, the total before each addition is at most {@code 10^9}; adding one
     * pile's at-most-{@code 10^9} hours remains below {@link Integer#MAX_VALUE} under the
     * problem constraints.</p>
     *
     * <p>Complexity: {@code O(n)} time in the worst case and {@code O(1)} auxiliary space.</p>
     *
     * @param piles       banana counts
     * @param targetHours maximum permitted hours
     * @param speed       candidate positive eating speed
     * @return whether the candidate speed is feasible
     */
    private boolean canFinishInTime(int[] piles, int targetHours, int speed) {
        int time = 0;
        for (int n : piles) {
            // Ceiling division is required because any partial pile still uses an hour.
            time += (n - 1) / speed + 1;
            if (time > targetHours) {
                return false;
            }
        }

        return time <= targetHours;
    }
}
