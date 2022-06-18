package solution.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value
 * Return all possibilities to add binary operators (not unary) +, -, or * between the digits evaluate to target value.
 *
 * @author BorisMirage
 * Time: 2019/06/03 16:04
 * Created with IntelliJ IDEA
 */

public class AddOperators_282 {
    /**
     * Backtracking problem.
     * Multiplication has a higher priority compare to plus and minus during the backtracking process.
     *
     * @param num    given array
     * @param target target number
     * @return all equations that result equals to target
     */
    public List<String> addOperators(String num, int target) {
        List<String> output = new ArrayList<>();

        /* Corner case */
        if (num.length() < 1) {
            return output;
        }

        backtracking(output, target, "", num, 0, 0, 0);
        return output;
    }

    /**
     * Backtracking to find all possible solutions.
     * Multiplication has a higher priority compare to plus and minus.
     *
     * @param l          output list
     * @param t          target number
     * @param expression number expression
     * @param num        remain num string
     * @param s          start position for iteration
     * @param eval       evaluate result based on binary operation
     * @param previous   evaluation from previous iteration
     */
    private void backtracking(List<String> l, int t, String expression, String num, int s, long eval, long previous) {

        if (s == num.length() && eval == t) {
            l.add(expression);
        }

        for (int i = s; i < num.length(); i++) {
            if (i != s && num.charAt(s) == '0') {
                break;      // valid number can not starts with '0' except for 0 itself
            }

            long val = Long.parseLong(num.substring(s, i + 1));     // current parse value
            if (s == 0) {       // init state
                backtracking(l, t, expression + val, num, i + 1, val, val);
            } else {
                backtracking(l, t, expression + "+" + val, num, i + 1, eval + val, val);
                backtracking(l, t, expression + "-" + val, num, i + 1, eval - val, -val);

                /* Multiplication has a higher priority compare to plus and minus */
                backtracking(l, t, expression + "*" + val, num, i + 1, eval - previous + previous * val, previous * val);
            }
        }
    }

    public static void main(String[] args) {
        AddOperators_282 test = new AddOperators_282();
        System.out.println(test.addOperators("234", 24));
        System.out.println(test.addOperators("6", 6));
        System.out.println(test.addOperators("123", 6));
        System.out.println(test.addOperators("232", 8));
        System.out.println(test.addOperators("1050", 6));
        System.out.println(test.addOperators("00", 0));
    }
}
