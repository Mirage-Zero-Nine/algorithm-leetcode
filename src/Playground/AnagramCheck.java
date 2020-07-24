package Playground;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * @author BorisMirage
 * Time: 2018/06/26 16:12
 * Created with IntelliJ IDEA
 */

public class AnagramCheck {
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

    public boolean anagramCheck(String s, String t) {

        /* Corner case*/
        if (s.length() != t.length()) {
            return false;
        }
        if (s.length() == 1) {
            return s.charAt(0) == t.charAt(0);
        }

        /* Array index stands for 26 letter's ASCII, and array value stands for their appear time*/
        int[] store = new int[26];

        /* +1 if char appeared in s, -1 otherwise. In this way to record each char's appearance */
        for (int i = 0; i < s.length(); i++) {
            store[s.charAt(i) - 97] += 1;
            store[t.charAt(i) - 97] -= 1;
        }

        for (int aSArray : store) {
            if (aSArray != 0) {
                return false;
            }
        }
        return true;
    }
}
