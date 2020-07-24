package Solution.Array;

/**
 * The values were in arithmetic progression: arr[i+1] - arr[i] are all equal for every 0 <= i < arr.length - 1.
 * Then, a value from arr was removed that was not the first or last value in the array.
 * Return the removed value.
 *
 * @author BorisMirage
 * Time: 2019/10/20 11:15
 * Created with IntelliJ IDEA
 */

public class MissingNumber_1228 {
    /**
     * The corner case is that each element in array may be same.
     * In this case, return the first (or last, whatever) element array.
     * Otherwise, the difference in array is Math.min(Math.abs(arr[0] - arr[1]), Math.abs(arr[n - 1] - arr[n - 2])).
     * The reason to select min is that if the difference is not same, the larger difference contains the difference.
     * Since the missing value will enlarge the difference, not shrink.
     *
     * @param arr given array
     * @return the removed value
     */
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int diff = Math.min(Math.abs(arr[0] - arr[1]), Math.abs(arr[n - 1] - arr[n - 2]));
        int tmp;
        for (int i = 1; i < n; i++) {
            tmp = Math.abs(arr[i] - arr[i - 1]);
            if (tmp > diff) {
                return arr[i - 1] + ((i == n - 1) ? arr[1] - arr[0] : arr[i + 1] - arr[i]);
            }
        }

        return arr[0];
    }
}
