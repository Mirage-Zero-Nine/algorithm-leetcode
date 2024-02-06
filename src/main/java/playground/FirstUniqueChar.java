package playground;


/**
 * Return the index of first unique character (1-based) in the given string.
 *
 * @author BorisMirage
 * Time: 2023/04/24 19:53
 * Created with IntelliJ IDEA
 */

public class FirstUniqueChar {
    /**
     * Scan the string twice, first time count the char frequency for the string.
     * The second time check the frequency of each char started from the beginning of the string.
     *
     * @param s given string
     * @return the index of first unique character (1-based)
     */
    public static int firstUniqueChar(String s) {

        if (s.isEmpty()) {
            return -1;
        }

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {

                // 1-based index
                return i + 1;
            }
        }

        return -1;
    }
}
