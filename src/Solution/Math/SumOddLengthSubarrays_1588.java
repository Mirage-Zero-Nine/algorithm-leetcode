package Solution.Math;

/**
 * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
 * A subarray is a contiguous subsequence of the array.
 * Return the sum of all odd-length subarrays of arr.
 *
 * @author BorisMirage
 * Time: 2020/09/19 10:34
 * Created with IntelliJ IDEA
 */

public class SumOddLengthSubarrays_1588 {
    /**
     * Each element will be added for ((length - 1) * (index + 1) + 1) / 2 times.
     *
     * @param arr given array
     * @return the sum of all odd-length subarrays of arr
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int out = 0;
        for (int i = 0; i < arr.length; i++) {
            out += ((((arr.length - i) * (i + 1)) + 1) / 2) * arr[i];
        }

        return out;
    }
}
