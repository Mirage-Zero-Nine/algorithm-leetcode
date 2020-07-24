package Solution.Stack;

import java.util.Stack;

/**
 * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression.
 * Assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F.
 * Note:
 * 1. The length of the given string is ≤ 10000.
 * 2. Each number will contain only one digit.
 * 3. The conditional expressions group right-to-left (as usual in most languages).
 * 4. The condition will always be either T or F. That is, the condition will never be a digit.
 * 5. The result of the expression will always evaluate to either a digit 0-9, T or F.
 *
 * @author BorisMirage
 * Time: 2019/06/26 18:48
 * Created with IntelliJ IDEA
 */

public class ParseTernary_439 {
    /**
     * Use a stack to store each char in string.
     * Top of stack will always store values from closest ternary expressions.
     * Hence, pop out last four elements in stack and push current ternary expression result will finally return result.
     *
     * @param expression given expression string
     * @return result of the expression
     */
    public String parseTernary(String expression) {
        if (expression.length() < 6) {
            return "";
        }

        Stack<Character> temp = new Stack<>();

        for (int i = expression.length() - 1; i > -1; i--) {
            if (!temp.isEmpty() && temp.peek() == '?') {

                temp.pop();     // pop out '?'
                char a = temp.pop();
                temp.pop();     // pop out ':'
                char b = temp.pop();

                /*
                 * Each time, select current ternary expression's result.
                 * Then push this result back to stack.
                 * Top of stack will always be the closest value for current ternary expressions.
                 * Therefore, if multiple ternary expressions are combined together, stack will always return the closest two. */
                if (expression.charAt(i) == 'T') {
                    temp.push(a);
                } else if (expression.charAt(i) == 'F') {
                    temp.push(b);
                }
            } else {
                temp.push(expression.charAt(i));
            }
        }
        return temp.pop().toString();
    }

    public static void main(String[] args) {
        ParseTernary_439 test = new ParseTernary_439();
        System.out.println(test.parseTernary("F?T?1:2:F?4:5"));
    }
}
