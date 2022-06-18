package solution.stack;

import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Remove the minimum number of parentheses in any positions, resulting string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 * @author BorisMirage
 * Time: 2019/11/07 14:27
 * Created with IntelliJ IDEA
 */
public class MinRemoveToMakeValid_1249 {
    /**
     * Use a stack to store all left parentheses index, if current char is right parenthesis, pop stack.
     * If stack is empty, then current right parenthesis is invalid. Mark it as invalid.
     * After traverse, if the stack is not empty, pop all elements in stack and mark corresponding position as invalid.
     *
     * @param s given string
     * @return valid string
     */
    public String minRemoveToMakeValid(String s) {

        /* Corner case */
        if (s == null || s.length() < 2) {
            return "";
        }

        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (arr[i] == ')') {
                if (stack.isEmpty()) {
                    arr[i] = '*';
                } else {
                    stack.pop();
                }
            } else if (arr[i] == '(') {
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            arr[stack.pop()] = '*';
        }

        StringBuilder sb = new StringBuilder();

        for (char c : arr) {
            if (c != '*') {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
