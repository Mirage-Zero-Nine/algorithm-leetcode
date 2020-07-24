package Solution.Map;

import java.util.Collections;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
 * It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 * Words in the list of banned words are given in lowercase, and free of punctuation.
 * Words in the paragraph are not case sensitive.
 * The answer is in lowercase.
 * Note:
 * 1. 1 <= paragraph.length <= 1000.
 * 2. 0 <= banned.length <= 100.
 * 3. 1 <= banned[i].length <= 10.
 * 4. The answer is unique in lowercase (even if its occurrences in paragraph may have uppercase symbols)
 * 5. Paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * 6. There are no hyphens or hyphenated words.
 * 7. Words only consist of letters, never apostrophes or other punctuation symbols.
 *
 * @author BorisMirage
 * Time: 2020/03/14 09:18
 * Created with IntelliJ IDEA
 */

public class MostCommonWord_819 {
    /**
     * Split the given string first, then use a tree map to count word frequency.
     * Use a hash set to check excluded words.
     * Tree map can sort words in alphabet order, which is actually unnecessary in this problem.
     *
     * @param paragraph given string
     * @param banned    banned words
     * @return the most frequent word that is not in the list of banned words
     */
    public String mostCommonWord(String paragraph, String[] banned) {

        /* Corner case */
        if (paragraph == null || paragraph.length() == 0) {
            return paragraph;
        }

        String[] array = paragraph.replaceAll("\\W+", " ").toLowerCase().split("\\s+");
        HashSet<String> set = new HashSet<>();
        TreeMap<String, Integer> map = new TreeMap<>();

        Collections.addAll(set, banned);

        for (String s : array) {
            if (!set.contains(s)) {
                map.put(s, map.getOrDefault(s.toLowerCase(), 0) + 1);
            }
        }

        int count = Integer.MIN_VALUE;
        String out = "";
        for (String s : map.keySet()) {
            if (map.get(s) > count) {
                count = map.get(s);
                out = s;
            }
        }

        return out;
    }
}
