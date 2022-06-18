package solution.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 *
 * @author BorisMirage
 * Time: 2019/06/04 11:16
 * Created with IntelliJ IDEA
 */

public class RemoveInvalidParentheses_301 {
    /**
     * Iterate all chars in given string.
     * If any invalid pair was found in string, backtrack to find all possible valid parentheses.
     * Finally, reverse string and do it again.
     *
     * @param s given parentheses pairs
     * @return all possible results.
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> out = new ArrayList<>();
        checkInvalid(out, s, 0, 0, '(', ')');
        return out;
    }

    /**
     * If found invalid parenthesis, then use backtracking to find all possible substrings.
     *
     * @param output  output list
     * @param s       parentheses string
     * @param valid   start of valid parentheses string, initially starts at 0
     * @param invalid start of invalid parenthesis right part, initially starts at 0
     * @param open    left part of parenthesis
     * @param close   right part of parenthesis
     *                when traverse from end to beginning, open and close will be swapped
     */
    private void checkInvalid(List<String> output, String s, int valid, int invalid, char open, char close) {

        int left = 0, right = 0;

        for (int i = valid; i < s.length(); i++) {      // start at valid position

            if (s.charAt(i) == open) {
                left++;
            } else if (s.charAt(i) == close) {
                right++;
            }

            if (right > left) {     // if right is more than left, then current parenthesis is invalid pair
                for (int j = invalid; j <= i; j++) {      // find all possible position to remove one invalid pair

                    /*
                     * Each time, remove only one invalid parenthesis and pass it without this char into backtracking.
                     * Hence, avoid duplicated close parenthesis.
                     * This problem requires all valid output, therefore, use backtracking to find all.
                     * From s(0, i), right part is more than left part by 1.
                     * Try to remove one right part each time to make s(0, i) valid.
                     * The next iteration starts at valid part, since it is not necessary to check valid part of string.
                     * If later there exist invalid pair, repeat this process starts at previous removing position. */
                    if (s.charAt(j) == close && (j == invalid || s.charAt(j - 1) != close)) {     // avoid duplication
                        checkInvalid(output, s.substring(0, j) + s.substring(j + 1), i, j, open, close);
                    }
                }
                return;     // if current string is invalid, it should not be added to result
            }
        }

        /*
         * Check reversed string in each iteration.
         * This is to check if left parenthesis is more than right parenthesis. */
        String reversed = new StringBuilder(s).reverse().toString();
        if (open == '(') {
            checkInvalid(output, reversed, 0, 0, ')', '(');
        } else {
            output.add(reversed);       // if s is valid pairs both directions, then add it to final output
        }
    }
}
