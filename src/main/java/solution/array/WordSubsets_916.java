package solution.array;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given two string arrays words1 and words2.
 * A string b is a subset of string a if every letter in b occurs in a including multiplicity.
 * For example, "wrr" is a subset of "warrior" but is not a subset of "world".
 * A string a from words1 is universal if for every string b in words2, b is a subset of a.
 * Return an array of all the universal strings in words1. You may return the answer in any order.
 *
 * @author BorisMirage
 * Time: 2021/11/01 23:34
 * Created with IntelliJ IDEA
 */

public class WordSubsets_916 {
    /**
     * The order of char is not relevant when considering subset.
     * Hence, count the char frequency for each word in second list.
     * Note that only preserve the highest frequency in a single word for each char.
     * For example, ["ec","oc","ceo"] is actually the same as ["ceo"], since "ec" and "oc" are both subset of "eco".
     * Then traverse the first array.
     * If the char frequency in a word is larger than every char in the words2, this word is an universal string.
     *
     * @param words1 first word array
     * @param words2 second word array
     * @return list of all the universal strings in words1
     */
    public List<String> wordSubsets(String[] words1, String[] words2) {

        /* Corner case */
        if (words1 == null || words1.length == 0 || words2 == null || words2.length == 0) {
            return new ArrayList<>();
        }

        List<String> out = new ArrayList<>();
        int[] frequencyCount = new int[26];
        for (String s : words2) {
            int[] currentWord = countCharFrequency(s);
            for (int i = 0; i < 26; i++) {
                frequencyCount[i] = Math.max(frequencyCount[i], currentWord[i]);
            }
        }

        for (String s : words1) {
            int[] currentWordFrequencyCount = countCharFrequency(s);
            int i = 0;
            while (i < 26) {
                if (currentWordFrequencyCount[i] < frequencyCount[i]) {
                    break;
                }
                i++;
            }
            if (i == 26) {
                out.add(s);
            }
        }

        return out;
    }

    /**
     * Count frequency for each char in the given string.
     *
     * @param s given string
     * @return frequency count in an integer array
     */
    private int[] countCharFrequency(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        return count;
    }
}
