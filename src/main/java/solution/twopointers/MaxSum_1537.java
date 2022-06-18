package solution.twopointers;

/**
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 * A valid path is defined as follows:
 * - Choose array nums1 or nums2 to traverse (from index-0).
 * - Traverse the current array from left to right.
 * - If it reading any value that is present in nums1 and nums2 you are allowed to change your path to the other array.
 * (Only one repeated value is considered in the valid path).
 * Score is defined as the sum of uniques values in a valid path.
 * Return the maximum score you can obtain of all possible valid paths.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2020/08/02 09:31
 * Created with IntelliJ IDEA
 */

public class MaxSum_1537 {
    /**
     * Two pointers. Note that the array is sorted.
     * If there are no common point in both arrays, then there are only two paths.
     * If there is a common point, then there will be a selection.
     * Hence, calculate the sum of path before the common point.
     * When the common point reached, add the larger path sum to final result and start again for new path sum.
     *
     * @param nums1 first array
     * @param nums2 second array
     * @return the maximum score can obtain of all possible valid paths
     */
    public int maxSum(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, index1 = 0, index2 = 0;
        long sum1 = 0, sum2 = 0, out = 0, mod = (long) 1e9 + 7;     // mod path sum at the end

        while (index1 < m || index2 < n) {
            if (index1 < m && (index2 == n || nums1[index1] < nums2[index2])) {     // array is sorted
                sum1 += nums1[index1++];
            } else if (index2 < n && (index1 == m || nums1[index1] > nums2[index2])) {
                sum2 += nums2[index2++];
            } else {
                out += Math.max(sum1, sum2) + nums1[index1];
                index1++;       // skip the common value
                index2++;
                sum1 = 0;       // reset path sum for next path
                sum2 = 0;
            }
        }

        return (int) ((out + Math.max(sum1, sum2)) % mod);
    }
}
