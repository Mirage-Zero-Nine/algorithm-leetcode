package Solution.Stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain parentheses (, ), plus + or minus -, non-negative integers and empty spaces " ".
 *
 * @author BorisMirage
 * Time: 2019/06/26 11:13
 * Created with IntelliJ IDEA
 */

public class Calculate_224 {
    /**
     * Use a stack to store calculate result of each operator and operand.
     * Normally, parse current integer and calculate it with previous sum and sign.
     * If reaches brackets, then consider the expression within brackets is a new expression.
     * Push the sign before bracket pair for final evaluation for whole expression within brackets.
     * Push a new initially sum 0 into stack, since the expression within brackets is a new expression.
     * Due to sign is pushed into stack, when reaches the right part of bracket, pop out sign for calculation.
     * After the bracket is end, pop the previous sum in bracket and before bracket to calculate the result.
     *
     * @param s given expression string
     * @return calculation result of s
     */
    public int calculate(String s) {

        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        int sign = 1, num;      // initially, sign will always be positive

        for (int i = 0; i < s.length(); i++) {

            if (Character.isDigit(s.charAt(i))) {
                num = s.charAt(i) - '0';
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {      // calculate current numbers
                    num = num * 10 + (s.charAt(++i) - '0');
                }

                stack.push(stack.pop() + sign * num);      // calculate previous sum and current integer with sign
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {        // calculate the sum in brackets, push the initially sum 0
                stack.push(sign);
                stack.push(0);        // bracket should be considered as a new calculation, and push a new init 0
                sign = 1;                   // initial operator in brackets is positive
            } else if (s.charAt(i) == ')') {

                /*
                 * Pop out three items to calculate sum within current bracket pair.
                 * First item: current sum within brackets.
                 * Second item: sign of whole bracket pair.
                 * Third item: previous sum outside of bracket pair. */
                stack.push(stack.pop() * stack.pop() + stack.pop());
            }
        }

        return stack.pop();     // top of stack is always the result of current expression
    }

    public static void main(String[] args) {
        Calculate_224 test = new Calculate_224();
        System.out.println(test.calculate("(11+(42 + 15 + 2)-3)+(6+8)"));       // 81
        System.out.println(test.calculate("2147483647"));
    }
}
