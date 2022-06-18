package solution.twopointers;

/**
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * Do not allocate extra space for another array
 * It must be done by modifying the input array in-place with O(1) extra memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Note:
 * 1. Input array is passed in by reference, modification to it will be known to the caller as well.
 *
 * @author BorisMirage
 * Time: 2019/06/19 18:32
 * Created with IntelliJ IDEA
 */

public class RemoveElement_27 {
    /**
     * Two pointers.
     *
     * @param nums input int array
     * @param val  int that will be removed in array
     * @return nums length after this process
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length < 2) {
            return nums.length;
        }

        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != val) {
                fast++;
            } else {
                nums[slow++] = nums[fast++];
            }
        }

        return slow;
    }
}
