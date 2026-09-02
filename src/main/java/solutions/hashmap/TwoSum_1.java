package solutions.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * Each input would have exactly one solution, and you may not use the same element twice.
 *
 * @author BorisMirage
 * Time: 2019/06/18 10:10
 * Created with IntelliJ IDEA
 */
public class TwoSum_1 {
    /**
     * Finds the unique pair of indices whose values add up to {@code target}.
     *
     * <p>The map stores values encountered earlier and their indices. For the
     * current value, only its complement ({@code target - nums[i]}) needs to be
     * looked up. The lookup happens before inserting the current value, which
     * prevents the same element from being used twice.
     *
     * <p>The {@code null} return is only a defensive fallback for callers that
     * violate the LeetCode precondition that a solution exists.
     *
     * <p>Time complexity is {@code O(n)} and auxiliary space complexity is
     * {@code O(n)}, where {@code n} is the length of {@code nums}.
     *
     * @param nums   array of integers
     * @param target required sum of the two values
     * @return the two matching indices, in either order; {@code null} only when
     * the input does not satisfy the problem's guarantee
     * @see <a href="https://leetcode.com/problems/two-sum/">LeetCode 1: Two Sum</a>
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // A previously seen complement completes the pair with nums[i].
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            // Store the current value only after checking its complement to avoid being paired with itself.
            map.put(nums[i], i);
        }
        return null;
    }
}
