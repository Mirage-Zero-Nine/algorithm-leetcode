package Solution.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Given a list of words, each word consists of English lowercase letters.
 * word1 is a predecessor of word2 if and only if add exactly one letter anywhere in word1 to make it equal to word2.
 * For example, "abc" is a predecessor of "abac".
 * A word chain is a sequence of words where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3.
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 * @author BorisMirage
 * Time: 2019/07/27 10:44
 * Created with IntelliJ IDEA
 */

public class LongestStrChain_1048 {
    /**
     * Dynamic programming with hash map.
     * Each time, if a word is a predecessor of other word in map, find longer length and replace it.
     * Finally, return max length.
     *
     * @param words given words list
     * @return longest possible length of a word chain with words chosen from the given list of words
     */
    public int longestStrChain(String[] words) {

        /* Corner case */
        if (words.length == 1) {
            return 1;
        }

        Arrays.sort(words, Comparator.comparingInt(String::length));

        HashMap<String, Integer> map = new HashMap<>();
        int max = 1;

        for (String w : words) {
            int chainLength = 1; // string chain starts at 1

            for (int i = 0; i < w.length(); i++) {
                String predecessor = w.substring(0, i) + w.substring(i + 1); // remove one character to build predecessor
                if (map.containsKey(predecessor)) { // if this predecessor contains in words list
                    chainLength = Math.max(chainLength, map.get(predecessor) + 1); // chain length + 1
                }

                map.put(w, chainLength);
                max = Math.max(max, chainLength);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestStrChain_1048 test = new LongestStrChain_1048();
        System.out.println(test.longestStrChain(new String[]{"bdca", "bda", "ba", "a", "b"}));
    }
}
