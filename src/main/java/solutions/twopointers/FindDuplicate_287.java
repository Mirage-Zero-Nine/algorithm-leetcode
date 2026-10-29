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
     * Uses Floyd's tortoise-and-hare algorithm to find the cycle entrance in the
     * graph represented by the array, without changing the read-only input.
     * Treating {@code nums[i]} as the next node from node {@code i} makes the
     * duplicate the cycle entrance for the walk starting at node 0: two indexes
     * point to that value, so this walk eventually enters the cycle there.
     * <p>
     * For example, in {@code [1, 3, 4, 2, 2]},
     * following values gives {@code 0 -> 1 -> 3 -> 2 -> 4 -> 2}; the repeated
     * value is the entrance at {@code 2}. The first loop makes a one-step
     * pointer and a two-step pointer meet inside that cycle. Resetting one
     * pointer to node {@code 0} works because if the slow pointer takes {@code t}
     * steps to meet the fast pointer, the fast pointer has taken {@code 2t};
     * therefore {@code t} is a whole number of cycle lengths. If the entrance
     * is {@code a} steps from node 0, then advancing the meeting pointer another
     * {@code a} steps reaches that entrance, so equal one-step walks meet there.
     *
     * @param nums array containing values from 1 through {@code nums.length - 1}
     *             with exactly one value repeated; null or empty input returns -1
     * @return the repeated value
     * <p>
     * Time complexity: O(n), where n is the number of values in the array.
     * Auxiliary space: O(1).
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // The two pointers start at different speeds. Every array lookup is a
        // valid next-node lookup under the problem's value-range contract.
        int slow = nums[0], fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // Meeting proves that both pointers are in the cycle, but not yet at
        // its entrance. From node 0 and from the meeting node, equal-speed
        // walks reach that entrance at the same time modulo the cycle length.
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
