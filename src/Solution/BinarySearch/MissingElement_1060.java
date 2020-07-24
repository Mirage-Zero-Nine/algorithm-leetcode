package Solution.BinarySearch;

/**
 * Given a sorted array A of unique numbers, find Kth missing number starting from the leftmost number of the array.
 *
 * @author BorisMirage
 * Time: 2019/08/13 09:10
 * Created with IntelliJ IDEA
 */

public class MissingElement_1060 {
    /**
     * Binary search.
     * To find the kth missing element is actually to find the ith element in array that kth is in nums[i, i+1].
     * Use binary search to narrow the range of array.
     * If left part of sub array has more than k missing #, narrow search range to left part.
     * If left part has k' missing # and k' <= k, turn to search right part and reduce k to k - k'.
     * Finally, find the num[i] that is next to kth missing #.
     *
     * @param nums given array
     * @param k    kth missing #
     * @return K-th missing number starting from the leftmost number of the array
     */
    public int missingElement(int[] nums, int k) {
        int n = nums.length - 1;
        int totalMissingInArray = nums[n] - nums[0] + 1 - nums.length;

        /* Corner case */
        if (totalMissingInArray < k) {      // if missing # is less than k, then first missing # is out of array
            return nums[n] + k - totalMissingInArray;
        }

        int left = 0, right = nums.length - 1;

        while (left < right - 1) {      // mid exists in at least 3 elements
            int mid = left + (right - left) / 2;
            int missingInRange = nums[mid] - nums[left] - (mid - left);     // count missing # under current subarray

            if (missingInRange >= k) {      // narrow search range if current subarray has at least k missing #
                right = mid;
            } else {
                left = mid;
                k -= missingInRange;
            }
        }

        return nums[left] + k;      // if gap is large between final elements
    }

    public static void main(String[] args) {
        System.out.println(new MissingElement_1060().missingElement(new int[]{1, 3, 5, 7, 9}, 2));
        System.out.println(new MissingElement_1060().missingElement(new int[]{1, 100, 1000, 100000}, 50));        // answer: 51
        System.out.println(new MissingElement_1060().missingElement(new int[]{746421, 1033196, 1647541, 4775111, 7769817, 8030384}, 10));        // answer: 746431
    }
}
