package Solution.BinarySearch;

/**
 * Given an unsorted array, return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note:
 * 1. Algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * @author BorisMirage
 * Time: 2019/06/23 15:30
 * Created with IntelliJ IDEA
 */

public class IncreasingTriplet_334 {
    /**
     * Same as LIS problem, simply limit the tail array size to 3.
     *
     * @param nums given array
     * @return whether an increasing subsequence of length 3 exists or not in the array
     */
    public boolean increasingTriplet(int[] nums) {

        /* Corner case */
        if (nums.length < 3) {
            return false;
        }

        int[] tails = new int[3];
        int size = 0;

        for (int num : nums) {
            int left = 0, right = size;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            tails[left] = num;

            if (left == size) {
                size++;
                if (left == 2) {        // found a increasing triplet
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Traverse approach.
     * The problem is to find 3 elements in array, i < j < k.
     * Hence, keep 2 ints and traverse the array.
     * One int is the current smallest element in array, the other one is the second smallest element in array.
     * During the traversal, update those ints and if found an element larger than second smallest element, return true.
     * Time complexity: O(n).
     * Space complexity: O(n).
     *
     * @param nums given array
     * @return whether an increasing subsequence of length 3 exists or not in the array
     */
    public boolean traverse(int[] nums) {

        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n <= small) {
                small = n;
            } else if (n <= big) {
                big = n;
            } else {
                return true;        // return if found a number larger than both
            }
        }

        return false;
    }
}
