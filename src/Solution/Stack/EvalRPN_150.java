package Solution.Stack;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * @author BorisMirage
 * Time: 2019/10/10 14:00
 * Created with IntelliJ IDEA
 */

public class EvalRPN_150 {
    /**
     * If current element is int, push it into stack.
     * If current value is operator, pop out last two element and calculate. Then push result into stack.
     * Finally, return the first element in stack (there should be only one element in stack).
     *
     * @param tokens given RPN expression
     * @return evaluated value
     */
    public int evalRPN(String[] tokens) {
        int n = tokens.length;

        if (n == 0) {
            return 0;
        }
        Stack<Integer> s = new Stack<>();

        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                s.push(Integer.parseInt(token));
            } else {
                int o1 = s.pop();
                int o2 = s.pop();
                int val;
                switch (token) {
                    case "/":
                        val = o2 / o1;
                        break;
                    case "-":
                        val = o2 - o1;
                        break;
                    case "*":
                        val = o2 * o1;
                        break;
                    default:
                        val = o2 + o1;
                        break;
                }

                s.push(val);
            }
        }

        return s.pop();
    }
}
