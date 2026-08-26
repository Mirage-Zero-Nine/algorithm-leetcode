package solutions.hashmap;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 *
 * @author BorisMirage
 * Time: 2019/09/14 10:23
 * Created with IntelliJ IDEA
 */

public class CanPermutePalindrome_266 {
    /**
     * Find if s contains more than 2 odd char.
     *
     * @param s given string
     * @return if a permutation of the string could form a palindrome
     */
    public boolean canPermutePalindrome(String s) {

        /* Corner case */
        if (s.length() < 2) {
            return true;
        }

        int[] count = new int[256];
        int odd = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
            odd = (count[s.charAt(i)] % 2 == 1) ? odd + 1 : odd - 1;
        }

        return odd < 2;
    }

}
