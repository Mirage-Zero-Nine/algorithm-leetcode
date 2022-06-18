package solution.binarysearch;

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
 * Time: 2020/02/06 15:28
 * Created with IntelliJ IDEA
 */

public class MinEatingSpeed_875 {
    /**
     * Binary search to reach the min K.
     * Min value is 1, and max value is 10^9, select middle of min and max initially.
     * If the middle value is available for all bananas, then narrow the max value to middle, and recalculate middle.
     * Finally, return the start value, which is the smaller one.
     *
     * @param piles given array
     * @param H     time before guards going back
     * @return the minimum integer K such that she can eat all the bananas within H hours
     */
    public int minEatingSpeed(int[] piles, int H) {

        int start = 1, end = 100000000;     // max pile[i] is 10^9

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (check(piles, H, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    /**
     * Check if all bananas can be finished before time.
     *
     * @param piles given array
     * @param h     time before guards going back
     * @param mid   # of bananas eat per hour
     * @return if all bananas can be finished before time
     */
    private boolean check(int[] piles, int h, int mid) {
        int hours = 0;
        for (int n : piles) {
            hours += (n - 1) / mid + 1;
        }

        return hours <= h;
    }
}
