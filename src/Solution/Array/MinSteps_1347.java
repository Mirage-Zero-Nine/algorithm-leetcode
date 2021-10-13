package Solution.Array;

import java.util.Arrays;

/**
 * Given two equal-size strings s and t.
 * In one step you can choose any character of t and replace it with another character.
 * Return the minimum number of steps to make t an anagram of s.
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 *
 * @author BorisMirage
 * Time: 2021/10/12 17:14
 * Created with IntelliJ IDEA
 */

public class MinSteps_1347 {
    /**
     * Count the frequency of chars in both string.
     * Assume in first string, add 1 for each char. In second string, subtract 1 for each char.
     * If there is a char appeared in string s but not in t, then there will be another char in t not in s.
     * This is due to the reason that both string has same length.
     * Hence, the number of steps to make t an anagram of s equals to the sum of positive number of frequency.
     *
     * @param s first string
     * @param t second string
     * @return the minimum number of steps to make t an anagram of s
     */
    public int minSteps(String s, String t) {

        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        return Arrays.stream(count).filter(i -> i > 0).sum();
    }
}
