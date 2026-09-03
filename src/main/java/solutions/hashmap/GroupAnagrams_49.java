package solutions.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * strs[i] consists of lowercase English letters.
 *
 * @author BorisMirage
 */
public class GroupAnagrams_49 {
    /**
     * Groups the supplied strings so that anagrams are in the same list.
     *
     * <p>{@link Collectors#groupingBy} uses the lambda expression as a
     * classifier function. The classifier converts each string to its
     * frequency signature; {@code groupingBy} then uses that signature as a
     * map key and adds the original string to the corresponding value list.</p>
     *
     * <p>For each string, the algorithm performs two passes:</p>
     * <ol>
     *     <li>Count each character in a 26-element frequency array.</li>
     *     <li>Build a deterministic string key from the non-zero counts.</li>
     * </ol>
     *
     * <p>Let {@code S} be the total number of characters across all strings and
     * {@code N} be the number of strings. The time complexity is
     * {@code O(S + 26N)}, which is {@code O(S + N)} because the alphabet size
     * is constant. The auxiliary space used by the grouping map is
     * {@code O(N)} for the fixed 26-character alphabet, excluding the returned
     * result.</p>
     *
     * @param strs strings to group; each string must contain only lowercase
     *             English letters
     * @return the grouped anagrams, in any order; an empty list for a
     * {@code null} or empty input array
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(s -> {
                    // The index represents a character: 0 -> 'a', 1 -> 'b', etc.
                    int[] counts = new int[26];

                    // Count the occurrences of every character in this string.
                    IntStream.range(0, s.length())
                            .forEach(i -> counts[s.charAt(i) - 'a']++);

                    StringBuilder sb = new StringBuilder();
                    // Build the signature in alphabetical order. Casting to char
                    // is important: without it, i + 'a' would append 97, 98, ...
                    // instead of appending the characters 'a', 'b', ... .
                    IntStream.range(0, counts.length)
                            .filter(i -> counts[i] != 0)
                            .forEach(i -> sb.append((char) ('a' + i)).append(counts[i]));

                    return sb.toString();
                })).values()
        );
    }
}
