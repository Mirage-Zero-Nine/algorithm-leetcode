package solution.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * @author BorisMirage
 * Time: 2018/06/13 10:21
 * Created with IntelliJ IDEA
 */

public class GenerateParenthesis_22 {
    /**
     * Backtracking.
     *
     * @param n pairs of parentheses
     * @return linked list that contains all combinations of parentheses
     */

    public List<String> generateParenthesis(int n) {
        List<String> output = new LinkedList<>();

        /* Corner case */
        if (n == 0) {
            return output;
        }

        backtracking(output, "", 0, 0, 2 * n, n);
        return output;
    }

    /**
     * Backtracking to generate all parentheses.
     * Construct pairs of parentheses by adding left part first, then insert right part by backtracking.
     * Finally, if string length reaches the pair limit, add it to final result.
     *
     * @param output output list
     * @param s      current string
     * @param left   left length
     * @param right  right length
     * @param length max length of n pairs of parentheses (2 * n)
     * @param pairs  n pairs
     */
    private void backtracking(List<String> output, String s, int left, int right, int length, int pairs) {
        if (s.length() == length) {
            output.add(s);      // endpoint
        }
        if (left < pairs) {     // each parenthesis pair has a left and a right
            backtracking(output, s + "(", left + 1, right, length, pairs);
        }
        if (right < left) {     // if there is a left, then there must contains a right
            backtracking(output, s + ")", left, right + 1, length, pairs);
        }
    }

    public static void main(String[] args) {

        /* Generate Parentheses Test */
        GenerateParenthesis_22 generateParenthesisTest = new GenerateParenthesis_22();
        System.out.println(generateParenthesisTest.generateParenthesis(3));
    }
}
