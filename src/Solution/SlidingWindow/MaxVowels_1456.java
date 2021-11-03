package Solution.SlidingWindow;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a string s and an integer k. Return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are (a, e, i, o, u).
 *
 * @author BorisMirage
 * Time: 2020/05/24 11:28
 * Created with IntelliJ IDEA
 */

public class MaxVowels_1456 {
    /**
     * Basic sliding window problem.
     * Set a window with size of k.
     * Each time, move the window forward by one char, check if the new added char and removed char is vowel.
     *
     * @param s given string
     * @param k substring size
     * @return the maximum number of vowel letters in any substring of s with length k
     */
    public int maxVowels(String s, int k) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> vowel = Stream.of('a', 'e', 'i', 'o', 'u')
                .collect(Collectors.toSet());

        int max = 0, current = 0;
        for (int i = 0; i < s.length(); i++) {

            if (vowel.contains(s.charAt(i))) {                      // new added char
                current++;
            }
            if (i >= k && vowel.contains(s.charAt(i - k))) {        // removed char
                current--;
            }
            max = Math.max(max, current);
        }

        return max;
    }
}
