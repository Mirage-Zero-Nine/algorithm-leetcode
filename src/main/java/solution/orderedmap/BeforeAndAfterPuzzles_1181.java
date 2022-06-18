package solution.orderedmap;

import java.util.*;

/**
 * Given a list of phrases, generate a list of Before and After puzzles.
 * A phrase is a string that consists of lowercase English letters and spaces only.
 * No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.
 * Phrases can be formed by merging two phrases where the last word of the one phrase is same as the first word of the other phrase.
 * Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j.
 * Note that the order of matching two phrases matters, we want to consider both orders.
 * You should return a list of distinct strings sorted lexicographically.
 *
 * @author BorisMirage
 * Time: 2019/09/08 16:42
 * Created with IntelliJ IDEA
 */

public class BeforeAndAfterPuzzles_1181 {
    /**
     * Use 2 HashMap's to store first and last words as keys respectively, and the corresponding phrases as the values.
     * Use the 3rd HashMap to check if there are duplicate phrases - a sole one can NOT create a puzzle.
     * Use a TreeSet to guarantee distinct and ascending result.
     *
     * @param phrases given phrases
     * @return a list of distinct strings sorted lexicographically
     */
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, Set<String>> head = new HashMap<>(), tail = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (String p : phrases) {
            String[] w = p.split(" ");
            head.computeIfAbsent(w[0], s -> new HashSet<>()).add(p);
            tail.computeIfAbsent(w[w.length - 1], s -> new HashSet<>()).add(p);
            count.put(p, 1 + count.getOrDefault(p, 0));
        }

        TreeSet<String> ans = new TreeSet<>();
        for (String end : tail.keySet()) {
            for (String p : head.getOrDefault(end, Collections.emptySet())) {
                for (String t : tail.get(end)) {
                    if (!t.equals(p) || count.get(p) > 1) {
                        ans.add(t + p.substring(end.length()));
                    }
                }
            }
        }
        return new ArrayList<>(ans);
    }
}
