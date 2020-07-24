package Solution.Backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive.
 * Return all possible letter combinations that the number could represent.
 * The combination has same length as the digit length.
 *
 * @author BorisMirage
 * Time: 2018/06/06 18:29
 * Created with IntelliJ IDEA
 */

public class LetterCombinations_17 {
    /**
     * Backtracking.
     *
     * @param digits input digits
     * @return string array list that contains all possible combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> output = new LinkedList<>();

        /* Corner case */
        if (digits.length() == 0) {
            return output;
        }
        for (int i = 0; i < digits.length(); i++) {
            if (digits.charAt(i) - '0' < 2) {
                return output;      // no possible letter combinations when '1' or '0' exists
            }
        }

        backtracking(digits, output, "");
        return output;
    }

    /**
     * Recursively find all combinations of digit letters.
     *
     * @param digit   input digit
     * @param output  each step's result
     * @param current each step's combination string
     */
    private void backtracking(String digit, List<String> output, String current) {
        String[] keymap = new String[]{" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digit.length() == current.length()) {
            output.add(current);
        } else {
            String letters = keymap[digit.charAt(current.length()) - '0'];

            for (int i = 0; i < letters.length(); i++) {
                backtracking(digit, output, current + letters.charAt(i));
            }
        }
    }
}
