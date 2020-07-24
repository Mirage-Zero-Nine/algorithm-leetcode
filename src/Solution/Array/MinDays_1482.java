package Solution.Array;

/**
 * Given an integer array bloomDay, an integer m and an integer k.
 * We need to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and can be used in one bouquet.
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden.
 * If it is impossible to make m bouquets return -1.
 * Constraints:
 * 1. bloomDay.length == n
 * 2. 1 <= n <= 10^5
 * 3. 1 <= bloomDay[i] <= 10^9
 * 4. 1 <= m <= 10^6
 * 5. 1 <= k <= n
 *
 * @author BorisMirage
 * Time: 2020/06/13 20:50
 * Created with IntelliJ IDEA
 */

public class MinDays_1482 {
    /**
     * Binary search to search the minimum waiting days.
     * The left bound is 1, right bound is the max possible value in array.
     * Each time, try to find if current mid value can make m bouquets.
     * To find the number of bouquets can be made in current array, simply traverse the array and count.
     *
     * @param bloomDay given array
     * @param m        m bouquets required
     * @param k        adjacent flowers for each bouquet
     * @return the minimum number of days required to be able to make m bouquets
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        /* Corner case */
        if (n < m * k) {
            return -1;
        }

        int left = 1, right = 1000000000;

        while (left < right) {
            int mid = left + (right - left) / 2;        // possible waiting days
            int bouquets = 0, consecutive = 0;

            for (int i = 0; i < n; i++) {
                if (bloomDay[i] > mid) {        // if current flower requires waiting
                    consecutive = 0;
                } else if (++consecutive >= k) {        // if k consecutive flowers has been found
                    bouquets++;
                    consecutive = 0;
                }
            }

            if (bouquets < m) {
                left = mid + 1;     // need more days
            } else {
                right = mid;        // could be the final required days or could wait for less days
            }
        }

        return left;
    }
}
