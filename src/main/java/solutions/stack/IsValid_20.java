package solutions.stack;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * @author BorisMirage
 * Time: 2018/06/12 17:52
 * Created with IntelliJ IDEA
 */

public class IsValid_20 {
    /**
     * Checks whether every opening bracket in {@code s} is closed by the same bracket type
     * and whether brackets close in last-opened, first-closed order. The empty string is valid.
     *
     * <p>The stack stores the closing bracket expected for each opening bracket. When a closing
     * bracket arrives, it must equal the stack's top expectation; an empty stack or a different
     * bracket proves that the prefix cannot be completed into a valid sequence. After the scan,
     * an empty stack means every opening bracket was matched. For a string of length {@code n},
     * this takes O(n) time and O(n) auxiliary space in the worst case. The input string is not
     * modified.</p>
     *
     * @param s a string containing only parentheses, square brackets, and curly braces
     * @return {@code true} when the brackets are correctly matched and nested; otherwise
     * {@code false}
     */
    public boolean isValid(String s) {
        // corner cases
        if (s == null || s.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // Record the only closing character that can correctly match this opening one.
            if (c == '{') {
                stack.push('}');
            } else if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else {
                // A closer must match the most recent unmatched opener, preserving nesting order.
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        // Any remaining expectation belongs to an opener that never received its closer.
        return stack.isEmpty();
    }
}
