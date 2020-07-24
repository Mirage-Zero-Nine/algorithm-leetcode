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
        if (words.length == 1) {
            return 1;
        }

        Arrays.sort(words, new Comparator<String>() {       // sort list by length
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        int max = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            int temp = 1;
            for (int i = 0; i < s.length(); i++) {
                String tmp1 = s.substring(0, i) + s.substring(i + 1);
                if (map.containsKey(tmp1)) {
                    temp = Math.max(map.get(tmp1) + 1, temp);
                }
            }
            map.put(s, temp);
            max = Math.max(temp, max);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestStrChain_1048 test = new LongestStrChain_1048();
        System.out.println(test.longestStrChain(new String[]{"bdca", "bda", "ba", "a", "b"}));
    }
}
