package solution.twopointers;

/**
 * Given a sorted array nums.
 * Remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array.
 * It must be done by modifying the input array in-place with O(1) extra memory.
 *
 * @author BorisMirage
 * Time: 2018/06/13 18:00
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicates_26 {
    /**
     * Two pointers.
     * First pointer is the "i" in for loop. It represent the position in traverse.
     * The other pointer points to the last element in array that is non-duplicate.
     * When more than 2 same int is found, the second pointer stop moving until next non-duplicate int is found.
     * Then next element in nums is that non-duplicate int.
     *
     * @param nums input sorted int array
     * @return length of array after removing duplicate elements
     */
    public int removeDuplicates(int[] nums) {

        /* Non-duplicate int pointer */
        int slow = 0, fast = 0;

        while (fast < nums.length) {
            if (fast == 0 || nums[fast] != nums[fast - 1]) {
                nums[slow++] = nums[fast];        // swap elements when reaches a new non-duplicate element
            }
            fast++;
        }
        return slow;
    }
}