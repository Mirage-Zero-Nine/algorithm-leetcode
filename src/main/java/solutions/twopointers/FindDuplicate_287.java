package solutions.twopointers;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive).
 * Prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * 1. Do not modify the array (assume the array is read only).
 * 2. Use only constant, O(1) extra space.
 * 3. Runtime complexity should be less than O(n2).
 * 4. There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * @author BorisMirage
 * Time: 2019/07/15 16:28
 * Created with IntelliJ IDEA
 */

public class FindDuplicate_287 {
    /**
     * Treat each value as the next pointer in a functional graph. Floyd's
     * tortoise-and-hare cycle detection finds the duplicate without changing
     * the read-only input array.
     *
     * @param nums given array
     * @return duplicate number in array
     */
    public int findDuplicate(int[] nums) {

        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
