package Solution.Stack;

import java.util.Stack;

/**
 * Given two strings S and T.
 * Return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * @author BorisMirage
 * Time: 2020/02/21 11:17
 * Created with IntelliJ IDEA
 */

public class BackspaceCompare_844 {
    /**
     * Use a stack to pop out characters if meet '#'.
     *
     * @param S first string
     * @param T second string
     * @return if they are equal when both are typed into empty text editors. # means a backspace character
     */
    public boolean backspaceCompare(String S, String T) {

        /* Corner case */
        if (S == null || T == null) {
            return false;
        }

        return parser(S).equals(parser(T));
    }

    /**
     * If meet a '#', then check if stack is empty. If stack is not empty, then pop out a character.
     *
     * @param s given string
     * @return parsed string
     */
    private String parser(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '#') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        return stack.toString();
    }
}
