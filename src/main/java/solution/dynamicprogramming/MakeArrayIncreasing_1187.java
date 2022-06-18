package solution.dynamicprogramming;

import java.util.*;

/**
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 * @author BorisMirage
 * Time: 2019/09/07 19:55
 * Created with IntelliJ IDEA
 */

public class MakeArrayIncreasing_1187 {
    /**
     * Dynamic programming.
     * State transition:
     * dp[i][j]: the min value can be put in arr1[j] when i replacements was taken.
     * arr1 has to be increasing, hence, dp[i][j - 1] should be larger than dp[i][j].
     * Then, if there is a smaller value from previous array, replace dp[i][j] with it.
     *
     * @param arr1 first array
     * @param arr2 array contains elements can be replaced into arr1
     * @return minimum number of operations (possibly zero) needed to make arr1 strictly increasing
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {

        int n = arr1.length;

        /* Corner case */
        if (n < 2) {
            return n - 1;
        }

        TreeSet<Integer> ts = new TreeSet<>();
        for (int value : arr2) {
            ts.add(value);
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int[] a : dp) {
            Arrays.fill(a, Integer.MAX_VALUE);
        }
        dp[0][0] = Integer.MIN_VALUE;       // dp[1][0] is arr[0]

        for (int j = 1; j <= n; j++) {          // arr1[0] to arr1[n]
            for (int i = 0; i <= j; i++) {      // replacements taken, at most i (arr[0, j])

                /*
                 * dp[i][j]: the min value can be put in arr1[j] when i replacements was taken
                 * arr1 has to be increasing, hence, dp[i][j - 1] should be larger than dp[i][j].
                 * Then, if there is a smaller value from previous array, replace dp[i][j] with it. */
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }
                if (i > 0 && ts.higher(dp[i - 1][j - 1]) != null) {
                    dp[i][j] = Math.min(dp[i][j], ts.higher(dp[i - 1][j - 1]));
                }
                if (j == n && dp[i][j] != Integer.MAX_VALUE) {
                    return i;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 3, 6, 7};
        int[] b = new int[]{1, 3, 2, 4};
        System.out.println(new MakeArrayIncreasing_1187().makeArrayIncreasing(a, b));        // 1

        a = new int[]{1, 5, 3, 6, 7};
        b = new int[]{4, 3, 1};
        System.out.println(new MakeArrayIncreasing_1187().makeArrayIncreasing(a, b));        // 2

        a = new int[]{1, 5, 3, 6, 7};
        b = new int[]{1, 6, 3, 3};
        System.out.println(new MakeArrayIncreasing_1187().makeArrayIncreasing(a, b));        // -1

        a = new int[]{0, 11, 6, 1, 4, 3};
        b = new int[]{5, 4, 11, 10, 1, 0};
        System.out.println(new MakeArrayIncreasing_1187().makeArrayIncreasing(a, b));        // 5

        a = new int[]{5, 16, 19, 2, 1, 12, 7, 14, 5, 16};
        b = new int[]{6, 17, 4, 3, 6, 13, 4, 3, 18, 17, 16, 7, 14, 1, 16};
        System.out.println(new MakeArrayIncreasing_1187().makeArrayIncreasing(a, b));        // 8

        a = new int[]{23, 10, 9, 12, 3, 14, 21, 16, 7, 10, 17, 12};
        b = new int[]{6, 5, 0, 15, 2, 17, 4, 11, 6, 5, 8, 15, 10, 1, 20, 11, 14, 13, 8};
//        Arrays.sort(b);
//        System.out.println(Arrays.toString(b));
        System.out.println(new MakeArrayIncreasing_1187().makeArrayIncreasing(a, b));        // 11
    }
}
