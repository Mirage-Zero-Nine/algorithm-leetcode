package solutions.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * @author BorisMirage
 * Time: 2019/06/24 13:41
 * Created with IntelliJ IDEA
 */

public class FindKthLargest_215 {

    /**
     * Keeps only the largest {@code k} values in a min heap.
     *
     * <p>The smallest heap member is always the kth largest value seen so
     * far. Each input value is inserted, and once the heap exceeds size
     * {@code k}, its minimum is removed because that value cannot belong to
     * the largest {@code k}. The final minimum is therefore the kth largest
     * value. The method leaves {@code nums} unchanged and uses {@code O(n log
     * k)} time and {@code O(k)} auxiliary space. For the class's invalid-input
     * extension, the guard or empty-heap result returns {@code -1}.</p>
     *
     * @param nums array whose kth largest value is requested
     * @param k    one-based rank from the largest value
     * @return the kth largest value, or {@code -1} for documented invalid
     * input
     */
    public int findKthLargest(int[] nums, int k) {
        // corner cases
        if (nums == null || nums.length < k) {
            return -1;
        }

        Queue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(nums).forEach(n -> {
            pq.add(n);
            if (pq.size() > k) {
                pq.poll();
            }
        });

        return pq.isEmpty() ? -1 : pq.poll();
    }

    /**
     * Iterative in-place quickselect.
     *
     * <p>The desired position in descending order is {@code k - 1}; for
     * example, {@code k == 3} means the value that would be at index {@code 2}
     * if the array were sorted from largest to smallest. Full sorting is
     * unnecessary because each partition tells us which side contains that
     * one target position. Every partition creates three contiguous regions:
     * values greater than the pivot, values equal to it, and values less than
     * it. The target's region is retained and the other regions are discarded.
     * Grouping all equal values together makes duplicate-heavy inputs finish
     * in one pass instead of shrinking by only one element per pass.</p>
     *
     * <p>The pivot is the middle value among the first, middle, and last
     * <em>values</em> in the current range; it is only a comparison value and
     * is not assumed to have its final rank. This median-of-three choice avoids
     * predictable endpoint-pivot behavior on sorted inputs while retaining
     * deterministic selection. The usual behavior is linear time,
     * and auxiliary space is {@code O(1)}; specially arranged inputs can
     * still take {@code O(n^2)} time because the pivot is deterministic.
     * Partitioning mutates {@code nums}.</p>
     *
     * <p>For example, with {@code [7, 2, 9, 5, 7]} and {@code k == 3}, the
     * target index is {@code 2}. The first, middle, and last values are
     * {@code 7, 9, 7}, so the pivot is {@code 7}. One partition produces
     * {@code [9] | [7, 7] | [2, 5]} (the order within a region is unspecified),
     * and index {@code 2} is in the equal block, so the answer is {@code 7}.</p>
     *
     * @param nums array whose kth largest value is requested
     * @param k    one-based rank from the largest value
     * @return the kth largest value, or {@code -1} for documented invalid
     * input
     */
    public int findKthLargestPartition(int[] nums, int k) {
        // corner cases
        if (nums == null || k <= 0 || k > nums.length) {
            return -1;
        }

        int target = k - 1, left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            // Use the middle value among three samples as the pivot. The
            // overflow-safe middle index and local names make clear that
            // these are values being compared, not positions being sorted.
            int firstValue = nums[left], middleValue = nums[middle], lastValue = nums[right];
            int pivot;
            if (firstValue < middleValue) {
                pivot = middleValue < lastValue
                        ? middleValue
                        : Math.max(firstValue, lastValue);
            } else {
                pivot = firstValue < lastValue
                        ? firstValue
                        : Math.max(middleValue, lastValue);
            }
            int greater = left, current = left, less = right;

            // Four regions are maintained while current scans the active range:
            // [left, greater)  contains values greater than the pivot;
            // [greater, current) contains values equal to the pivot;
            // [current, less] is still unknown; and (less, right] contains
            // values less than the pivot. The scan continues while current <= less.
            while (current <= less) {
                if (nums[current] > pivot) {
                    // Move this value before the equal block. The value at
                    // greater was already classified as equal, so swapping it
                    // into current lets both boundaries advance safely.
                    swap(nums, greater++, current++);
                } else if (nums[current] < pivot) {
                    // Move this value after the unknown block. The value
                    // swapped in from less has not been inspected, so current
                    // stays put and only the less boundary moves.
                    swap(nums, current, less--);
                } else {
                    // An equal value already belongs in the middle block.
                    current++;
                }
            }

            if (target < greater) {
                // The target is in the greater-than-pivot block. Its absolute
                // descending index is unchanged, so search only this prefix.
                right = greater - 1;
            } else if (target > less) {
                // The target is in the less-than-pivot block. Discard the
                // prefix and translate nothing because target is absolute.
                left = less + 1;
            } else {
                // The nonempty [greater, less] block contains only pivot values.
                // Every index in it has the same answer, so selection is done.
                return pivot;
            }
        }

        // A valid request always falls into one of the partition regions.
        throw new IllegalStateException("Quickselect did not locate a valid rank");
    }

    /**
     * Swaps two positions during in-place partitioning.
     */
    private void swap(int[] nums, int first, int second) {
        int temporary = nums[first];
        nums[first] = nums[second];
        nums[second] = temporary;
    }
}
