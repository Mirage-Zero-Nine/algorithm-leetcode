package solution.twopointers;

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * Given a sequence of words written in the alien language, and the order of the alphabet.
 * Return true if and only if the given words are sorted lexicographically in this alien language.
 *
 * @author BorisMirage
 * Time: 2021/11/02 15:20
 * Created with IntelliJ IDEA
 */

public class IsAlienSorted_953 {
    /**
     * Map the alien language order first. Then check if the given words array disobey the order.
     *
     * @param words given words array
     * @param order order of alien language
     * @return if the given words are sorted lexicographically in this alien language
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] mapping = new int[26];
        for (int i = 0; i < order.length(); i++) {
            mapping[order.charAt(i) - 'a'] = i;
        }

        for (int i = 1; i < words.length; i++) {
            if (!compare(words[i - 1], words[i], mapping)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Two pointers to traverse the strings.
     * Find the first different char in both strings. Then check the order.
     * Finally, if no different char found, then check the length.
     * 1. If they have same length, return true.
     * 2. If first string is longer than second string, return false.
     *
     * @param s1      first string
     * @param s2      second string
     * @param mapping order mapping
     * @return if two words are keeping lexicographical order in map
     */
    private boolean compare(String s1, String s2, int[] mapping) {

        for (int i = 0, j = 0; i < s1.length() && j < s2.length(); i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return mapping[s1.charAt(i) - 'a'] < mapping[s2.charAt(j) - 'a'];
            }
        }

        return s1.length() <= s2.length();
    }
}
