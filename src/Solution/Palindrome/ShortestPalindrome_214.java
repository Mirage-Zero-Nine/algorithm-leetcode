package Solution.Palindrome;

/**
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
 * Return the shortest palindrome you can find by performing this transformation.
 *
 * @author BorisMirage
 * Time: 2021/11/13 12:05
 * Created with IntelliJ IDEA
 */

public class ShortestPalindrome_214 {
    /**
     * Implement KMP to build up a look-up table that records the match result of prefix and postfix.
     *
     * @param s given string
     * @return the shortest palindrome you can find by performing this transformation
     */
    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse();
        int[] table = getTable(temp);

        // get the maximum palindrome part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse() + s;
    }

    /**
     * Build up the look-up table by implementing KMP.
     * Value in the table means the max length of matching substring that exists in both prefix and postfix.
     * In the prefix this substring should start from 0, while in the postfix this substring should end at current index.
     *
     * @param s given string
     * @return look-up table
     */
    public int[] getTable(String s) {
        int[] table = new int[s.length()]; // build look up table

        int index = 0; // points to matched char in prefix part
        for (int i = 1; i < s.length(); i++) { // skip index 0, do not match a string with itself
            if (s.charAt(index) == s.charAt(i)) {
                table[i] = table[i - 1] + 1; // extend match in prefix and postfix
                index++;
            } else { // match failed, try to match a shorter substring

                /*
                 * Shorten the match string length.
                 * Jump to the prefix part that used to match postfix ended at i - 1. */
                index = table[i - 1];

                while (index > 0 && s.charAt(index) != s.charAt(i)) {
                    index = table[index - 1]; // try to shorten the match string length until we revert to the beginning of match (index 1)
                }

                if (s.charAt(index) == s.charAt(i)) { // check char match, either found a match char or reached boundary
                    index++; // if matched, extend one char
                }

                table[i] = index;
            }

        }

        return table;
    }
}
