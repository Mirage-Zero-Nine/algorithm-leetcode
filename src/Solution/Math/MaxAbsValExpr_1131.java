package Solution.Math;

/**
 * Given two arrays of integers with equal lengths, return the maximum value of:
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 * @author BorisMirage
 * Time: 2019/08/18 13:55
 * Created with IntelliJ IDEA
 */

public class MaxAbsValExpr_1131 {
    /**
     * Set 4 "corner points" as infinity points.
     * The max Manhattan distance between two points can be found by |AI| - |BI|, where I is four infinity point.
     * |AI| - |BI| <= |AB|
     *
     * @param arr1 given first array
     * @param arr2 given second array
     * @return maximum value of |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
     */
    public int maxAbsValExpr(int[] arr1, int[] arr2) {

        int max = 0, n = arr1.length;
        int inf = (int) Math.pow(10, 7);
        int[][] corner = new int[][]{{inf, inf}, {-inf, inf}, {-inf, -inf}, {inf, -inf}};       // four infinity point

        for (int[] ints : corner) {
            int min = Math.abs(ints[0] - arr1[0]) + Math.abs(ints[1] - arr2[0]);

            for (int j = 0; j < n; j++) {
                int current = Math.abs(ints[0] - arr1[j]) + Math.abs(ints[1] - arr2[j]) + j;
                max = Math.max(Math.abs(min - current), max);
                min = Math.min(current, min);       // if there would be a larger distance
            }
        }

        return max;
    }
}
