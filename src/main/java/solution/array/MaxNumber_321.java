package solution.array;

import java.util.Arrays;

/**
 * You are given two integer arrays nums1 and nums2 of lengths m and n respectively.
 * nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 * Create the maximum number of length k <= m + n from digits of the two numbers.
 * The relative order of the digits from the same array must be preserved.
 * Return an array of the k digits representing the answer.
 *
 * @author BorisMirage
 * Time: 2021/09/26 23:39
 * Created with IntelliJ IDEA
 */

public class MaxNumber_321 {
    /**
     * Divided into 2 sub-problems:
     * 1. Find the max number in one array with k digit, and preserve the order.
     * 2. Find the max number in two arrays with all digits in these two arrays, and preserve the order.
     * For the first sub-problem, keep a monotone stack for the increase subsequence.
     * If the length of increase subsequence is smaller than k, add all digits to the stack.
     * For the second sub-problem, poll out one larger digit each time from the two arrays.
     * If the digits are the same, find the next different digit and compare them.
     * Divide the k digits to two parts, size from 0 to k, and the other part with size from k to 0.
     * Each time, try to find the max number for each array.
     * e.g.: find max number from nums1 using i digits, and find max number from nums2 using k - i digits.
     * Then find the max number by using the two max number above.
     * Find the max number among all arrays in previous step, return it.
     *
     * @param nums1 first array
     * @param nums2 second array
     * @param k     max number length
     * @return an array of the k digits representing the maximum number
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] out = new int[k];

        for (int i = Math.max(0, k - m); i <= k && i <= n; i++) {
            int[] max1 = findMaxNumber(nums1, i);
            int[] max2 = findMaxNumber(nums2, k - i);
            int[] temp = findMaxNumber(max1, max2);
            out = compare(temp, out);
        }

        return out;
    }

    /**
     * Create the maximum number of length k from digits of the given array.
     * The relative order of the digits from the same array must be preserved.
     *
     * @param nums given array
     * @param k    length of max number
     * @return an array of the k digits representing the maximum number
     */
    private int[] findMaxNumber(int[] nums, int k) {
        int[] out = new int[k];
        int index = 0, length = nums.length;
        for (int i = 0; i < length; i++) {
            /*
             * Case we do not need to pop stack:
             * 1. k is larger than mono stack length.
             * 2. stack is empty.
             * 3. top of the stack is smaller than current element.*/
            while (length - i + index > k && index > 0 && out[index - 1] < nums[i]) {
                index--;
            }
            if (index < k) { // if we still need digit
                out[index++] = nums[i];
            }
        }

        return out;
    }

    /**
     * Find the max number of two arrays using all digits from the two arrays.
     *
     * @param nums1 first array
     * @param nums2 second array
     * @return the max number of two arrays using all digits from the two arrays
     */
    private int[] findMaxNumber(int[] nums1, int[] nums2) { // max number m+n
        int length1 = nums1.length, length2 = nums2.length;

        int[] out = new int[length1 + length2];
        int p1 = 0, p2 = 0;

        for (int i = 0; i < out.length; i++) {
            int temp1 = p1, temp2 = p2;
            while (temp1 < length1 && temp2 < length2 && nums1[temp1] == nums2[temp2]) {
                temp1++;
                temp2++;
            }
            /*
             * If reaches the end of array, select the other one.
             * Otherwise, select the element which follows larger digit. */
            out[i] = ((temp2 == length2) || ((temp1 < length1) && nums1[temp1] > nums2[temp2])) ? nums1[p1++] : nums2[p2++];
        }

        return out;
    }

    /**
     * Compare two arrays and choose a larger one.
     *
     * @param nums1 first array
     * @param nums2 second array
     * @return array that made up a larger number
     */
    private int[] compare(int[] nums1, int[] nums2) { // compare two numbers
        int p1 = 0, p2 = 0;
        int m = nums1.length, n = nums2.length;
        while (p1 < m && p2 < n && nums1[p1] == nums2[p2]) {
            p1++;
            p2++;
        }

        return (p2 == n || (p1 < m && nums1[p1] > nums2[p2])) ? nums1 : nums2;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 4, 6, 5};
        int[] nums2 = new int[]{9, 1, 2, 5, 8, 3};
        System.out.println(Arrays.toString(new MaxNumber_321().maxNumber(nums1, nums2, 5))); // [9,8,6,5,3]
        nums1 = new int[]{6, 7};
        nums2 = new int[]{6, 0, 4};
        System.out.println(Arrays.toString(new MaxNumber_321().maxNumber(nums1, nums2, 5))); // [6, 7, 6, 0, 4]
    }
}
