package solutions.binarysearch;

/**
 * Searches for a target in an ascending array of distinct integers.
 *
 * <p>The method uses binary search over an inclusive candidate range. At each iteration it
 * checks the midpoint and discards the half that cannot contain the target. The problem contract
 * supplies a sorted array, while this implementation also returns {@code -1} for {@code null}
 * input as a defensive behavior.
 *
 * <p>Under the problem constraints, {@code 1 <= nums.length <= 10^4}, the values are distinct,
 * and each array value and target is between {@code -10^4} and {@code 10^4}, inclusive.
 * The comparison-based implementation also works for any sorted array of Java {@code int}
 * values, including values outside those problem bounds.
 *
 * <p>For an array of length {@code n}, the algorithm runs in {@code O(log n)} time and uses
 * {@code O(1)} auxiliary space. The input array is not modified.
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
        // A null or empty array has no candidate index.
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
