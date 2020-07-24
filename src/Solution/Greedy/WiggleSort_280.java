package Solution.Greedy;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * @author BorisMirage
 * Time: 2020/03/28 09:10
 * Created with IntelliJ IDEA
 */

public class WiggleSort_280 {
    /**
     * Assume nums[0, i - 1] is sorted, then the ith element is based on its index.
     * If i is odd, then nums[i - 1] should not be larger than nums[i].
     * If nums[i - 1] > nums[i], then swap nums[i - 1] and nums[i].
     * Since nums[0, i - 1] is sorted, nums[i - 2] >= nums[i - 1]. If nums[i - 1] > nums[i], then nums[i - 2] > nums[i].
     * If i is even, then nums[i - 1] >= nums[i].
     * The idea is same, if nums[i - 1] < nums[i], then swap them.
     *
     * @param nums given array
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1 && (nums[i - 1] > nums[i])) {     // odd element, nums[i - 1] <= nums[i]
                swap(nums, i, i - 1);
            } else if (i % 2 == 0 && nums[i - 1] < nums[i]) {              // even element
                swap(nums, i, i - 1);
            }
        }
    }

    /**
     * Swap two elements in array
     *
     * @param array given array
     * @param i     swap index
     * @param j     swap index
     */
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
