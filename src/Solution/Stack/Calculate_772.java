package Solution.Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain parentheses, the plus + or minus sign -, non-negative integers and empty spaces .
 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces .
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * @author BorisMirage
 * Time: 2019/06/26 15:01
 * Created with IntelliJ IDEA
 */

public class Calculate_772 {
    /**
     * Keep two stacks, one stores the operand and one stores the operators.
     * Basic idea is to calculate two operands with one operator.
     * If current operator has same or lower priority to previous operator (e.g., * and +), then directly calculate.
     * This is the calculation order from left to right.
     * If current operator has higher priority, then push it to stack and finally calculate it from right to left.
     * When meeting parenthesis, push the left half first. Then calculate possible pairs from left to right.
     * When meets the right part of parenthesis, pop out the operators and operands, calculate from right to left.
     *
     * @param s expression string
     * @return calculation result of s
     */
    public int calculate(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> nums = new Stack<>();        // stores numbers
        Stack<Character> ops = new Stack<>();       // stores operators (including parentheses)
        int tmp, current = 0;

        nums.push(0);
        while (current < s.length() && s.charAt(current) == ' ') {
            current++;
        }

        if (s.charAt(current) == '-') {
            ops.push('-');
            current++;
        }

        while (current < s.length()) {
            while (current < s.length() && s.charAt(current) == ' ') {
                current++;
            }

            if (current == s.length()) {
                break;
            }
            char c = s.charAt(current);

            if (Character.isDigit(c)) {
                tmp = 0;
                while (current < s.length() && Character.isDigit(s.charAt(current))) {
                    tmp = tmp * 10 + (s.charAt(current) - '0');
                    current++;
                }
                nums.push(tmp);
            } else if (c == '(') {
                ops.push('(');
                current++;
                while (current < s.length() && s.charAt(current) == ' ') {
                    current++;
                }
                if (s.charAt(current) == '-') {
                    nums.push(0);
                }
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                }
                current++;
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {

                /*
                 * If current operator has same or lower priority, then calculate directly from left to right.
                 * Otherwise, push to stack and wait for calculation from right to left. */
                while (!ops.isEmpty() && precedence(c, ops.peek())) {
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                }

                ops.push(c);
                current++;
            }
        }

        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }

        return nums.pop();
    }

    /**
     * Calculation.
     *
     * @param operator operator
     * @param second   second operand
     * @param first    first operand
     * @return calculation result
     */
    private static int operation(char operator, int second, int first) {
        switch (operator) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;       // assume b is not 0
        }

        return 0;
    }

    /**
     * Check current operator and previous operator.
     * For example, 5 * 3 + 2 can directly calculated from left to right.
     * But 5 + 3 * 2 should be calculated from right to left.
     * If current operator has same or lower priority, then return true and directly calculate the result.
     * Otherwise, return false and wait for later calculation, which is from right to left.
     *
     * @param current  current operator
     * @param previous previous operator
     * @return if current operator can directly use
     */
    private static boolean precedence(char current, char previous) {

        if (previous == '(' || previous == ')') {
            return false;
        }

        return (current != '*' && current != '/') || (previous != '+' && previous != '-');
    }

    /**
     * Use a deque to store operands and operators.
     *
     * @param s expression string
     * @return calculation result of s
     */
    public int calculateDeque(String s) {
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;

        Deque<Integer> stack = new ArrayDeque<>();      // stack to simulate recursion

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';

                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }

                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '(') {

                /* Preserve current calculation status.
                 * Then reset them for next calculation.*/
                stack.offerFirst(l1);
                stack.offerFirst(o1);
                stack.offerFirst(l2);
                stack.offerFirst(o2);
                l1 = 0;
                o1 = 1;
                l2 = 1;
                o2 = 1;

            } else if (c == ')') {

                /*
                 * First preserve the result of current calculation.
                 * Then restore previous calculation status. */
                int num = l1 + o1 * l2;
                if (stack.size() > 0) {
                    o2 = stack.poll();
                }
                if (stack.size() > 0) {
                    l2 = stack.poll();
                }
                if (stack.size() > 0) {
                    o1 = stack.poll();
                }
                if (stack.size() > 0) {
                    l1 = stack.poll();
                }

                l2 = (o2 == 1 ? l2 * num : l2 / num);       // previous calculation status is now in effect

            } else if (c == '*' || c == '/') {
                o2 = (c == '*' ? 1 : -1);

            } else if (c == '+' || c == '-') {
                if (c == '-' && (i == 0 || s.charAt(i - 1) == '(')) {
                    o1 = -1;
                    continue;
                }
                l1 = l1 + o1 * l2;
                o1 = (c == '+' ? 1 : -1);

                l2 = 1;
                o2 = 1;
            }
        }

        return (l1 + o1 * l2);
    }

    public static void main(String[] args) {
        System.out.println(new Calculate_772().calculate("-1+4*3/3/3"));                            // 0
        System.out.println(new Calculate_772().calculate("1 - (-7)"));                              // 8
        System.out.println(new Calculate_772().calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));            // -12
        System.out.println(new Calculate_772().calculate("2-(5-6)"));                               // 3
    }
}
