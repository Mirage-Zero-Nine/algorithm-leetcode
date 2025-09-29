package solution.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a paragraph and a list of banned words, return the most frequent word not in the list of banned words.
 * It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 * Words in the list of banned words are given in lowercase, and free of punctuation.
 * Words in the paragraph are not case-sensitive.
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
     * Finds the most common non-banned word in a given paragraph. The method returns the word that
     * appears the most times in the paragraph, excluding any words that are present in the banned list.
     * The comparison is case-insensitive.
     * The method processes the paragraph by extracting alphabetic words, converting them to lowercase,
     * and counting their occurrences. Any words found in the banned list are ignored. If there is a tie
     * between multiple words, the one that appears first in the paragraph is returned.
     *
     * @param paragraph The input paragraph, which is a string containing words separated by spaces and punctuation.
     *                  It can be a mixture of uppercase and lowercase letters.
     *                  If the paragraph is empty or null, the method will return an empty string.
     * @param banned    An array of words that should be excluded from the count. The comparison is case-insensitive.
     *                  If the banned array is null, it will be treated as an empty list.
     * @return The most common word in lowercase in the paragraph that is not in the banned list.
     */
    public String mostCommonWord(String paragraph, String[] banned) {

        // corner case
        if (paragraph == null || paragraph.isEmpty() || banned == null) {
            return "";
        }

        Set<String> bannedSet = Arrays.stream(banned)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Map<String, Integer> map = new HashMap<>();
        int max = 0, end = 0;
        String output = null;

        while (end < paragraph.length()) {
            while (end < paragraph.length() && !Character.isAlphabetic(paragraph.charAt(end))) {
                end++;
            }
            StringBuilder sb = new StringBuilder();
            while (end < paragraph.length() && Character.isAlphabetic(paragraph.charAt(end))) {
                sb.append(paragraph.charAt(end++));
            }
            String currentWord = sb.toString().toLowerCase();

            if (!bannedSet.contains(currentWord)) {
                map.put(currentWord, map.getOrDefault(currentWord, 0) + 1);
                if (max < map.get(currentWord)) {
                    max = map.get(currentWord);
                    output = currentWord;
                }
            }
        }

        return Objects.requireNonNull(output).toLowerCase();
    }
}
