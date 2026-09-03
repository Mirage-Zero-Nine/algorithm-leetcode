package solutions.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * The solution set must not contain duplicate triplets.
 *
 * @author BorisMirage
 */
public class ThreeSum_15 {
    /**
     * Finds every unique triplet of values in {@code nums} whose sum is zero.
     *
     * <p>After sorting, the method fixes one value at a time. For a fixed
     * {@code nums[i]}, {@link #twoPointers(int[], int, List)} searches
     * the remaining suffix with two pointers:</p>
     *
     * <ul>
     *     <li>If the pair sum is too small, move {@code p1} right to increase it.</li>
     *     <li>If the pair sum is too large, move {@code p2} left to decrease it.</li>
     *     <li>If the sum is zero, record the triplet and move both pointers.</li>
     * </ul>
     *
     * <p>Because the array is sorted, duplicate anchor values and duplicate
     * matching pointer values can be skipped safely. Once the anchor becomes
     * positive, no later anchor can participate in a zero-sum triplet.</p>
     *
     * <p>For an input of length {@code n}, sorting costs {@code O(n log n)} and
     * the two-pointer scans cost {@code O(n^2)}, for a total of {@code O(n^2)}
     * time. The extra working space is {@code O(1)}, excluding the returned
     * triplets. Sorting reorders the input array in place.</p>
     *
     * @param nums input values; the array may be reordered by sorting
     * @return all unique zero-sum triplets, in any order
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // corner case
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        // making sure the array is monotonic to be able to skip duplicate triplet
        Arrays.sort(nums);

        // corner case: triplet cannot exist if every value is positive or every value is negative.
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> output = new ArrayList<>();

        // Only non-positive values can be the first value of a zero-sum
        // triplet. Since nums is sorted, takeWhile stops at the first positive
        // anchor; the filter removes duplicate anchor values.
        IntStream.range(0, nums.length - 2)
                .takeWhile(i -> nums[i] <= 0)
                .filter(i -> i == 0 || nums[i] != nums[i - 1])
                .forEach(i -> twoPointers(nums, i, output));

        return output;
    }

    /**
     * Finds all pairs in the suffix after {@code index} that complete the
     * anchor value to a zero-sum triplet.
     *
     * <p>The suffix is sorted. Therefore, increasing {@code p1} can only
     * increase the pair sum, and decreasing {@code p2} can only decrease it.
     * Each pointer moves inward at most {@code n} times, making this helper
     * {@code O(n)} for one anchor.</p>
     *
     * <p>After a match, both pointers move and all equal neighboring values are
     * skipped. This prevents duplicate value triplets when the input contains
     * repeated numbers.</p>
     *
     * @param nums   sorted input values
     * @param start  index of the fixed anchor value
     * @param output accumulator for the discovered triplets
     */
    private void twoPointers(int[] nums, int start, List<List<Integer>> output) {
        int current = nums[start];
        int left = start + 1, right = nums.length - 1;

        while (left < right) {
            long pairSum = (long) nums[left] + nums[right];

            // The triplet is kept in non-decreasing order: current <= left <= right.
            if (pairSum + current == 0) {
                output.add(List.of(current, nums[left++], nums[right--]));

                // Skip duplicate values after recording a match. The pointer
                // increments above ensure these comparisons use the new values.
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (pairSum > Math.abs(current)) {
                // The pair is too large, so move right to a smaller value.
                right--;
            } else {
                // The pair is too small, so move left to a larger value.
                left++;
            }
        }
    }
}
