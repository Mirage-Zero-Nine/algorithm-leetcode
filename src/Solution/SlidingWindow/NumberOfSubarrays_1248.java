package Solution.SlidingWindow;

/**
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 *
 * @author BorisMirage
 * Time: 2020/02/26 10:38
 * Created with IntelliJ IDEA
 */

public class NumberOfSubarrays_1248 {
    /**
     * Sliding window.
     * If one substring contains K odd numbers, then the # of subarray depends on even numbers.
     * After finding one subarray with K odd numbers, shrink the window at left side first.
     * Each even number found represents a subarray. Assume M subarray was found.
     * Then try to enlarge window at right side.
     * At right side, each even number represents M subarray, since previous M subarray can append this even number.
     * That is to say, at left side, M even numbers represents M subarray, and at right side, it is N * M subarrays.
     *
     * @param nums given array
     * @param k    number of odd number should exist in subarray
     * @return the number of nice sub-arrays
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0, index = 0, out = 0;

        for (int num : nums) {
            if (num % 2 == 1) {     // even number only enlarge window size
                count = 0;      // if find a new odd number, then reset count
                k--;
            }

            while (k == 0) {
                k += nums[index++] & 1;       // check if current element is odd
                count++;
            }

            /*
             * If later of target sub array contains even number, then each even number adds # of target subarray.
             * That is, # of even number * previous count. */
            out += count;
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfSubarrays_1248().numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));     // 16
        System.out.println(new NumberOfSubarrays_1248().numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 3));     // 0
    }
}
