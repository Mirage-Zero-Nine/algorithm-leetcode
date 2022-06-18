package solution.twopointers;

/**
 * Given a sorted array nums, remove the duplicates in-place.
 * Duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array.
 * It must be done by modifying the input array in-place with O(1) extra memory.
 *
 * @author BorisMirage
 * Time: 2018/08/14 13:49
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicates_80 {
    /**
     * Similar to RemoveDuplicates_26, simply modify pointer moving condition.
     * Two int as pointers.
     * First pointer is the "i" in for loop. It represent the position in traverse.
     * The other pointer points to the last element in array that is non-duplicate.
     * When more than 2 same int is found, the second pointer stop moving until next non-duplicate int is found.
     * Then next element in nums is that non-duplicate int.
     *
     * @param nums input sorted int array
     * @return length of array that removed duplicate element
     */
    public int removeDuplicates(int[] nums) {

        /* Non-duplicate int pointer */
        int n = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i < 2 || nums[i] > nums[n - 2]) {       // the array is sorted
                nums[n++] = nums[i];
            }
        }
//        System.out.println(Arrays.toString(nums));
        return n;
    }
}
