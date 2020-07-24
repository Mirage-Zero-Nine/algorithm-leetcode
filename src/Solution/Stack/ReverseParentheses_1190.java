package Solution.Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a string s that consists of lower case English letters and brackets.
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * Your result should not contain any bracket.
 *
 * @author BorisMirage
 * Time: 2019/09/15 11:13
 * Created with IntelliJ IDEA
 */

public class ReverseParentheses_1190 {
    /**
     * Use a deque (stack) to temporary store the string.
     * Each time, if current char in string is ')', pop out all chars before first '(' into a queue.
     * Then push them back to stack.
     * Finally, pop out all chars in stack and reverse the result.
     *
     * @param s given string
     * @return strings in each pair of matching parentheses (without bracket), starting from the innermost one
     */
    public String reverseParentheses(String s) {

        /* Corner case */
        if (s.length() == 0) {
            return "";
        }

        Deque<Character> dq = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                Queue<Character> q = new LinkedList<>();

                while (!dq.isEmpty() && dq.peek() != '(') {
                    q.add(dq.pop());
                }

                if (!dq.isEmpty()) {
                    dq.pop();
                }

                while (!q.isEmpty()) {
                    dq.push(q.remove());
                }
            } else {
                dq.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!dq.isEmpty()) {
            sb.append(dq.pollLast());
        }

        return sb.toString();
    }
}
