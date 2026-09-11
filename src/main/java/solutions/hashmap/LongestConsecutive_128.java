package solutions.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Finds the length of the longest sequence of consecutive integers in an unsorted array.
 *
 * @author BorisMirage
 * Time: 2019/07/06 12:11
 * Created with IntelliJ IDEA
 */

public class LongestConsecutive_128 {
    /**
     * Returns the length of the longest consecutive sequence in {@code nums}.
     *
     * <p>Think of each consecutive sequence as a segment with a length written at both ends.
     * For a new value {@code n}, the entries for {@code n - 1} and {@code n + 1} identify the
     * segments immediately to its left and right. The new segment therefore has length
     * {@code leftLength + 1 + rightLength}. Its two outer ends are then updated with that
     * length, so a later value can merge with the whole segment in constant time.
     *
     * <p>Only the ends need the current length: a future value can join a segment only by
     * appearing immediately before its left end or immediately after its right end. The map
     * still records every value, including interior values, so duplicates are ignored even
     * after two segments have been joined.
     *
     * <p>Each input value performs a constant number of hash-map operations, so the expected
     * time complexity is {@code O(n)} and the space complexity is {@code O(n)}.
     *
     * @param nums an unsorted array of integers; an empty or {@code null} array has length 0
     * @return the length of the longest consecutive sequence
     */
    public int longestConsecutive(int[] nums) {
        // corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            // A sequence contains values, not occurrences. Ignore a repeated value entirely.
            if (map.containsKey(n)) {
                continue;
            }

            /*
             * If n - 1 exists, it must be the right end of the sequence on the left:
             * otherwise n would already be present inside that sequence.
             * The same reasoning applies to n + 1 and the sequence on the right.
             * Therefore, these two lookups return the exact lengths that n needs to join.
             */
            int left = map.getOrDefault(n - 1, 0), right = map.getOrDefault(n + 1, 0);
            int length = left + right + 1;

            max = Math.max(max, length);

            /*
             * For example, inserting 5 between [1, 2, 3, 4] and [6, 7] creates one segment
             * of length 7. Store that length at 1 and 7 because those are the only positions
             * from which a future value can extend the segment. Store 5 as well so a duplicate
             * 5 is recognized later; its stored length is irrelevant once it is interior.
             */
            map.put(n, length);
            map.put(n - left, length);
            map.put(n + right, length);
        }

        return max;
    }
}
