package solution.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string which contains only lowercase letters.
 * Remove duplicate letters so that every letter appear once and only once.
 * Make sure your result is the smallest in lexicographical order among all possible results.
 *
 * @author BorisMirage
 * Time: 2019/06/28 14:25
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicateLetters_316 {
    /**
     * The output string should follow these two rules:
     * 1. No duplicated character.
     * 2. The smaller character in lexicographical order should be removed later, if it contains duplicated.
     * The solution is using stack or deque.
     * First, iterate the array to count each char's appearance. Then iterate this array again.
     * If found a char in stack, pass this char, and in this way to remove all duplicated character.
     * Otherwise, pop out all characters that is larger than current character AND contains duplicate.
     * Use a boolean array to record duplicated character. When pop out character, mark popped character as unvisited.
     * Add current character to stack and continue this process until the whole string is traversed.
     *
     * @param s given string
     * @return string without duplicated letter and be smallest in lexicographical order
     */
    public String removeDuplicateLetters(String s) {

        /* Corner case */
        if (s.length() == 0) {
            return "";
        }

        Deque<Character> dq = new LinkedList<>();       // use deque instead of stack for better string forming
        boolean[] isVisited = new boolean[26];
        int[] count = new int[26];                      // count char appearance
        char[] arr = s.toCharArray();

        for (char c : arr) {
            count[c - 'a']++;
        }

        for (char c : arr) {
            count[c - 'a']--;

            if (!isVisited[c - 'a']) {      // pass chars already in stack and delete duplicated char

                /*
                 * Pop out elements that:
                 * 1. Larger than current char (to assure result smallest lexicographical order)
                 * 2. This popped char will be found in later of string (count[c] > 0)
                 * Both conditions should be met.
                 * Pop out all characters that meet conditions above. They will be added in to stack later. */
                while (!dq.isEmpty() && dq.peekLast() > c && count[dq.peekLast() - 'a'] > 0) {
                    isVisited[dq.pollLast() - 'a'] = false;     // reset visited mark, so that they will be added later
                }
                dq.addLast(c);
                isVisited[c - 'a'] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.pollFirst());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters_316().removeDuplicateLetters("cbaacdcbc"));        // acdb
    }
}
