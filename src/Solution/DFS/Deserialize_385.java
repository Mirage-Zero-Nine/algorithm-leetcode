package Solution.DFS;

import Lib.NestedInteger;

import java.util.Stack;

/**
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Note: You may assume that the string is well-formed:
 * 1. String is non-empty.
 * 2. String does not contain white spaces.
 * 3. String contains only digits 0-9, [, - ,, ].
 *
 * @author BorisMirage
 * Time: 2020/04/10 21:36
 * Created with IntelliJ IDEA
 */

public class Deserialize_385 {
    /**
     * Keep a stack to store previous uncompleted value.
     * The nest integer can either contains a integer, or contains a list with other nested integer.
     *
     * @param s given string
     * @return deserialized nested integer
     */
    public NestedInteger deserialize(String s) {

        /* Corner case */
        if (s.isEmpty()) {
            return null;
        }
        if (s.charAt(0) != '[') {       // only contains digit
            return new NestedInteger(Integer.valueOf(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger out = null;
        int l = 0;      // start of a number substring;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                if (out != null) {
                    stack.push(out);
                }
                out = new NestedInteger();
                l = i + 1;
            } else if (ch == ']') {
                String num = s.substring(l, i);
                if (!num.isEmpty()) {
                    out.add(new NestedInteger(Integer.valueOf(num)));
                }
                if (!stack.isEmpty()) {
                    NestedInteger pop = stack.pop();
                    pop.add(out);
                    out = pop;
                }
                l = i + 1;
            } else if (ch == ',') {
                if (s.charAt(i - 1) != ']') {
                    String num = s.substring(l, i);
                    out.add(new NestedInteger(Integer.valueOf(num)));
                }
                l = i + 1;
            }
        }

        return out;
    }
}
