package Solution.Array;

/**
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 * Return the maximum sub-array sum in the modified array.
 * Note that the length of the sub-array can be 0 and its sum in that case is 0.
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2019/09/14 19:42
 * Created with IntelliJ IDEA
 */

public class KConcatenationMaxSum_1191 {
    /**
     * Two conditions:
     * 1. Array sum is larger than 0, then the max sum could be in (k - 2) * sum + maxPrefix + maxSuffix
     * 2. Array sum <= 0, then the max sum can be found in given array itself.
     * The max prefix sum is from last element to first element (continuously).
     * The max suffix sum is from first element to last element (continuously).
     *
     * @param arr given array
     * @param k   repeating array k times
     * @return maximum sub-array sum in the modified array
     */
    public int kConcatenationMaxSum(int[] arr, int k) {
        int mod = (int) Math.pow(10, 9) + 7;

        long tmp = 0, oneMax = 0;
        for (int i : arr) {
            tmp = tmp > 0 ? (tmp + i) : i;
            oneMax = Math.max(tmp, oneMax);
        }
        oneMax %= mod;

        if (k == 1) {
            return (int) oneMax;
        }

        long sum = 0;
        for (int i : arr) {
            sum += i;
        }

        tmp = 0;
        long prefixMax = Long.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            tmp = (tmp + arr[i]);
            prefixMax = Math.max(tmp, prefixMax);
        }

        tmp = 0;
        long suffixMax = Long.MIN_VALUE;
        for (int i : arr) {
            tmp += i;
            suffixMax = Math.max(suffixMax, tmp);
        }

        /*
         * Two conditions:
         * 1. Array sum is larger than 0, then the max sum could be in (k - 2) * sum + maxPrefix + maxSuffix.
         * 2. Array sum <= 0, then the max sum can be found in given array itself. */
        long s1 = ((sum * (k - 2)) + prefixMax + suffixMax);
        long s2 = (prefixMax + suffixMax);

        return (sum > 0) ? (int) (Math.max(s1, oneMax) % mod) : (int) (Math.max(s2, oneMax) % mod);
    }

    public static void main(String[] args) {
        System.out.println(new KConcatenationMaxSum_1191().kConcatenationMaxSum(new int[]{1, 2}, 3));
        System.out.println(new KConcatenationMaxSum_1191().kConcatenationMaxSum(new int[]{1, -2, 1}, 5));
        System.out.println(new KConcatenationMaxSum_1191().kConcatenationMaxSum(new int[]{-1, -2, -3}, 7));
        System.out.println(new KConcatenationMaxSum_1191().kConcatenationMaxSum(new int[]{-5, -2, 0, 0, 3, 9, -2, -5, 4}, 5));       // 20
    }
}
