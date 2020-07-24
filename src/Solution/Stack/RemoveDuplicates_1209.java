package Solution.Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a string s.
 * A k duplicate removal consists of finding k adjacent same letters from s and removing them, then concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 *
 * @author BorisMirage
 * Time: 2019/09/29 15:28
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicates_1209 {
    /**
     * Use two stacks during the traverse.
     * First stack store the char of string.
     * Second stack store the same char count under current ith char in s.
     * If the last count in stack is equal to k, then remove k chars in both stack.
     * Finally, the stack stores the final result.
     *
     * @param s given string
     * @param k k duplicate removals
     * @return final string after all such duplicate removals have been made
     */
    public String removeDuplicates(String s, int k) {
        Deque<Character> dq = new LinkedList<>();      // final string
        Stack<Integer> stack = new Stack<>();         // count of duplicated char
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peekLast() == s.charAt(i)) {
                stack.push(stack.peek() + 1);
            } else {
                stack.push(1);
            }
            dq.addLast(s.charAt(i));

            if (stack.peek() == k) {
                for (int j = 0; j < k; j++) {
                    dq.pollLast();
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        n = dq.size();

        for (int i = 0; i < n; i++) {
            sb.append(dq.pollFirst());
        }

        return sb.toString();
    }
}
