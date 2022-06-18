package solution.stack;

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
     * Use stack to solve this problem. While char is left part of parentheses, push its right part to stack.
     * When char is right part, pop the top of stack and compare.
     *
     * @param s input parentheses
     * @return boolean value that whether this input string is valid
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                default:
                    if (stack.isEmpty() || stack.pop() != c) {      // open brackets must be closed by same type of it
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();     // including corner case
    }

    public static void main(String[] args) {

        /* Parentheses Test */
        IsValid_20 isValidTest = new IsValid_20();
        System.out.println(isValidTest.isValid("()[]{}"));
    }
}
