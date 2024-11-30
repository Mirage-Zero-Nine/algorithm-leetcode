package solution.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
 * The score you get by erasing the subarray is equal to the sum of its elements.
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * @author BorisMirage
 * Time: 2024/11/30 11:36
 * Created with IntelliJ IDEA
 */

public class MaximumUniqueSubarray_1695 {
    /**
     * Sliding window.
     * Changing condition:
     * Find the later one between current position or the next one of the duplicated element.
     *
     * @param nums given array
     * @return the maximum score you can get by erasing exactly one subarray
     * @see LengthOfLongestSubstring_3
     */
    public int maximumUniqueSubarray(int[] nums) {

        // corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE, currentSum = 0, start = 0;
        Map<Integer, Integer> map = new HashMap<>();

        // if an element is found that was seen earlier within the current window
        // move the start point to the last seen position of that element
        // or keep it in place if the last seen position is earlier
        // ensuring no duplicate elements in the window
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {

                // calculating the new sum of current window without duplicated element
                int newStart = Math.max(start, map.get(nums[i]) + 1);
                while (start < newStart) {
                    currentSum -= nums[start++];
                }

                start = newStart;

            }
            currentSum += nums[i];
            map.put(nums[i], i);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
