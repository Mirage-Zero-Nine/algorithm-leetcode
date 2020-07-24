package Solution.UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs.
 * Determine if two sentences are similar.
 * For example:
 * words1 = ["great", "acting", "skills"]
 * words2 = ["fine", "drama", "talent"]
 * pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * Then words1 and words2 are similar.
 * Note that the similarity relation is transitive.
 * For example, if "great" and "good" are similar, "fine" and "good" are similar, then "great" and "fine" are similar.
 * Similarity is symmetric.
 * For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 * Also, a word is always similar with itself.
 * For example:
 * words1 = ["great"]
 * words2 = ["great"]
 * pairs = []
 * They are similar, even though there are no specified similar word pairs.
 * Finally, sentences can only be similar if they have the same number of words.
 * So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * Note:
 * 1. The length of words1 and words2 will not exceed 1000.
 * 2. The length of pairs will not exceed 2000.
 * 3. The length of each pairs[i] will be 2.
 * 4. The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 *
 * @author BorisMirage
 * Time: 2020/04/19 15:54
 * Created with IntelliJ IDEA
 */

public class AreSentencesSimilarTwo_737 {
    /**
     * The relationship is transitive. Therefore, the intuitive approach is union find.
     * Union-find to find the root of word. If root word is same, then they are similar.
     *
     * @param words1 first sentence
     * @param words2 second sentence
     * @param pairs  similarity
     * @return if two sentences are similar
     */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {

        /* Corner case */
        if (words1.length != words2.length) {
            return false;
        }

        Map<String, String> m = new HashMap<>();

        for (List<String> pair : pairs) {
            String parent1 = find(m, pair.get(0));      // find parent of current string
            String parent2 = find(m, pair.get(1));

            if (!parent1.equals(parent2)) {     //  build new relationship
                m.put(parent1, parent2);
            }
        }

        for (int i = 0; i < words1.length; i++) {
            if (!words1[i].equals(words2[i]) && !find(m, words1[i]).equals(find(m, words2[i]))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Union-find style of finding the parent of given string.
     * If string is not found in map, return the string itself.
     *
     * @param m relationship map
     * @param s string
     * @return parent of string
     */
    private String find(Map<String, String> m, String s) {
        if (!m.containsKey(s)) {
            m.put(s, s);
        }

        return s.equals(m.get(s)) ? s : find(m, m.get(s));
    }
}

