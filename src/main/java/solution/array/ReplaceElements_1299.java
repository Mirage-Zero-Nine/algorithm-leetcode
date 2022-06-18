package solution.array;

/**
 * Given an array arr, replace every element in that array with the greatest element among the elements to its right.
 * Replace the last element with -1.
 * After doing so, return the array.
 *
 * @author BorisMirage
 * Time: 2020/03/16 13:25
 * Created with IntelliJ IDEA
 */

public class ReplaceElements_1299 {
    /**
     * Traverse from right to left. Initially, the value to fill into array is -1.
     *
     * @param arr given array
     * @return replaced array
     */
    public int[] replaceElements(int[] arr) {
        int replace = -1;

        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = replace;
            replace = Math.max(tmp, arr[i]);
        }

        return arr;
    }
}
