package Solution.Math;

/**
 * Given an array of integers arr. Return the number of sub-arrays with odd sum.
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2020/07/25 22:30
 * Created with IntelliJ IDEA
 */

public class NumOfSubarrays_1524 {
    /**
     * Prefix sum and count number of subarray.
     * If current prefix sum is odd, then all the subarray with odd sum can add this elements.
     * If current prefix sum is even, then all the subarray with even sum can add this elements.
     *
     * @param arr given array
     * @return the number of sub-arrays with odd sum
     */
    public int numOfSubarrays(int[] arr) {
        int current = 0, out = 0, mod = (int) 1e9 + 7;
        int[] count = new int[]{1, 0};       // count[0]: number of even prefix sum, 0 is even

        for (int n : arr) {
            current ^= n & 1;       // check current element is even or odd, and check current prefix sum is even or odd
            out = (out + count[1 - current]) % mod;
            count[current]++;
        }

        return out;
    }
}
