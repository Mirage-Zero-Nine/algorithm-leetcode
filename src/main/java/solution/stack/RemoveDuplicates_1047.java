package solution.stack;

/**
 * You are given a string s consisting of lowercase English letters.
 * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 * We repeatedly make duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 * @author BorisMirage
 * Time: 2022/11/11 14:20
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicates_1047 {
    /**
     * Keep a stack (or an array) to track the chars in string.
     * If the top of the stack is same as current char, pop it.
     *
     * @param s given string
     * @return the final string after all such duplicate removals have been made
     */
    public String removeDuplicates(String s) {
        char[] array = new char[s.length()];
        int top = 0;

        for (int i = 0; i < s.length(); i++) {
            if (top == 0 || s.charAt(i) != array[top - 1]) {
                array[top] = s.charAt(i);
                top++;
            } else {
                while (top != 0 && array[top - 1] == s.charAt(i)) {
                    top--;
                }
            }
        }

        return String.valueOf(array, 0, top);
    }
}
