package solutions.binarysearch;

/**
 * Given an array of integers nums which is sorted in ascending order, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * @author BorisMirage
 * Time: 2019/06/02 23:37
 * Created with IntelliJ IDEA
 */

public class Search_704 {
    /**
     * Returns the index of {@code target} in a sorted ascending array, or {@code -1} when the
     * target is absent.
     *
     * <p>Invariant: if the target occurs, its index remains within the inclusive range
     * {@code [left, right]}. After checking {@code mid}, the target cannot be at that index, so
     * the update removes {@code mid} from the next candidate range. The loop uses {@code <=} to
     * inspect the final one-element range; when no candidate remains, the target is absent.
     *
     * <p>Complexity: {@code O(log n)} time and {@code O(1)} auxiliary space. The input array is
     * read-only and is not modified. For {@code null} or empty input, the method returns
     * {@code -1}.
     *
     * @param nums   sorted ascending array of distinct integers; may be {@code null}
     * @param target value to locate
     * @return the index of {@code target}, or {@code -1} if it is absent
     */
    public int search(int[] nums, int target) {
        // corner cases
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        // [left, right] is an inclusive range containing every possible target index.
        while (left <= right) {
            // Subtraction before division avoids overflowing left + right for large indices.
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                // mid was checked and is not the target, so discard it and everything to its right.
                right = mid - 1;
            } else {
                // mid was checked and is not the target, so discard it and everything to its left.
                left = mid + 1;
            }
        }

        return -1;
    }
}
