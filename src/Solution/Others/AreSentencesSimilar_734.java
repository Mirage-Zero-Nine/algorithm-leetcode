package Solution.Others;

import java.util.*;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs.
 * Determine if two sentences are similar.
 * For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are:
 * pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]]
 * Note that the similarity relation is not transitive.
 * e.g., if "great", "fine" are similar, and "fine", "good" are similar, "great", "good" are not necessarily similar.
 * However, similarity is symmetric.
 * For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 * Also, a word is always similar with itself.
 * For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar.
 * Finally, sentences can only be similar if they have the same number of words.
 * So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 *
 * @author BorisMirage
 * Time: 2020/04/17 19:04
 * Created with IntelliJ IDEA
 */

public class AreSentencesSimilar_734 {
    /**
     * Definition of similar:
     * 1. Same word.
     * 2. Mapping relationship.
     * Keep a hash map, the key of hash map is the words in pair, and the value is all words that is similar to the key.
     * Then traverse the two given sentence.
     * If words in sentences are not same, and they don't have a mapping relationship, then they are not similar.
     *
     * @param words1 first sentence
     * @param words2 second sentence
     * @param pairs  similarity
     * @return if two sentences are similar
     */
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {

        /* Corner case */
        if (words1.length != words2.length) {
            return false;
        }

        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> p : pairs) {
            map.putIfAbsent(p.get(0), new HashSet<>());
            map.putIfAbsent(p.get(1), new HashSet<>());
            map.get(p.get(0)).add(p.get(1));
            map.get(p.get(1)).add(p.get(0));
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            if (!map.containsKey(words1[i]) || !map.get(words1[i]).contains(words2[i])) {
                return false;
            }
        }

        return true;
    }
}
