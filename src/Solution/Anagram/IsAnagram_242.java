package Solution.Anagram;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * @author BorisMirage
 * Time: 2018/06/26 11:39
 * Created with IntelliJ IDEA
 */

public class IsAnagram_242 {
    /**
     * Use an array to store each char's appearance in string in alphabet order.
     * If one char appeared in first string, then +1 in array's represented position.
     * If one char appeared in second string, -1 in array's represented position.
     * If two strings are anagram, all chars will appear exact same times. Hence, all elements in array should be 0.
     * Finally, check each element in array, if there is one element in array is not 0, return false.
     *
     * @param s input string
     * @param t input string
     * @return whether they are anagram.
     */

    public boolean isAnagram(String s, String t) {

        /* Corner case */
        if (s.length() != t.length()) {
            return false;
        }

        int[] store = new int[26];

        for (int i = 0; i < s.length(); i++) {
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }

        for (int i : store) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}
