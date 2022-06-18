package solution.others;

/**
 * You are given an array of strings words and a string chars.
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 * Return the sum of lengths of all good strings in words.
 *
 * @author BorisMirage
 * Time: 2020/03/24 10:12
 * Created with IntelliJ IDEA
 */

public class CountCharacters_1160 {
    /**
     * Count character frequency and check if string can be formed by given characters.
     *
     * @param words given words
     * @param chars characters can be used to form words
     * @return the sum of lengths of all good strings in words
     */
    public int countCharacters(String[] words, String chars) {

        /* Corner case */
        if (words == null || words.length == 0) {
            return 0;
        }

        int[] count = new int[26];
        int out = 0;
        for (int i = 0; i < chars.length(); i++) {
            count[chars.charAt(i) - 'a']++;
        }

        for (String s : words) {
            int[] clone = count.clone();
            if (isValid(s, clone)) {
                out += s.length();
            }
        }

        return out;
    }

    /**
     * Check if given string is valid.
     *
     * @param s     given string
     * @param count characters can be used to form words
     * @return if given string is valid
     */
    private boolean isValid(String s, int[] count) {
        for (int i = 0; i < s.length(); i++) {
            if (--count[s.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
