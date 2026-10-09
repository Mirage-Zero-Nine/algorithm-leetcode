package solutions.treemap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 * @author BorisMirage
 * Time: 2021/09/25 18:37
 * Created with IntelliJ IDEA
 */

public class FrequencySort_451 {
    /**
     * Counts each Java {@code char} in a hash map, orders the distinct
     * characters by decreasing count in a max heap, and appends each polled
     * character as one complete run. Because the heap always yields a
     * greatest remaining frequency, every run is placed after only runs with
     * at least as high a frequency; equal-frequency ties may be ordered
     * arbitrarily. The input string is immutable, and each call creates fresh
     * map, heap, and output state. This method is applicable to Java UTF-16
     * code units: supplementary code points are counted as their two surrogate
     * {@code char} values. For the intentionally supported null and empty
     * extension, it returns the same reference unchanged.
     *
     * <p>For n input code units and d distinct code units, counting takes O(n)
     * time and heap construction/polling takes O(d log d) time. The map and
     * heap require O(d) storage; the temporary {@code char[]} from
     * {@link String#toCharArray()}, the {@link StringBuilder}, and the returned
     * text together require O(n) text storage, so total extra storage is
     * O(n + d) including the result-building storage.</p>
     *
     * @param s input string whose characters should be grouped by frequency
     * @return a permutation of {@code s} with character frequencies in
     *         non-increasing order, or the same value/reference for null or
     *         empty input
     */
    public String frequencySort(String s) {
        // corner cases
        if (s == null || s.isEmpty()) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> pq = map.entrySet().stream()
                .collect(Collectors.toCollection(
                        () -> new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue())
                ));

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            var entry = pq.poll();
            // Polling removes the largest remaining frequency, so appending its
            // complete run preserves the heap's non-increasing order invariant.
            sb.repeat(entry.getKey(), entry.getValue());
        }

        return sb.toString();
    }
}
