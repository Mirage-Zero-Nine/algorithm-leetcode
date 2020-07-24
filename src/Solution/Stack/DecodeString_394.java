package Solution.Stack;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 *
 * @author BorisMirage
 * Time: 2019/06/26 18:02
 * Created with IntelliJ IDEA
 */

public class DecodeString_394 {
    /**
     * Use two stacks to store the repeat time and previous substring when a # of repeat time is found.
     * When a ']' is found, concatenate repeat substring to previous non-repeat substring.
     * The concatenated string will be the next repeating part, or becomes the final result if no repetition is found.
     *
     * @param s given encoded string
     * @return decoded string
     */
    public String decodeString(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return s;
        }

        Stack<Integer> repeat = new Stack<>();      // count of repeating string clip
        Stack<String> tmp = new Stack<>();          // temporary string clip

        String out = "";
        int i = 0;

        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int digit = 0;
                while (Character.isDigit(s.charAt(i))) {
                    digit = 10 * digit + (s.charAt(i++) - '0');      // convert char to digit
                }
                repeat.push(digit);
            } else if (s.charAt(i) == '[') {
                tmp.push(out);      // string clip must comes after repeat count
                out = "";
                i++;
            } else if (s.charAt(i) == ']') {
                StringBuilder sb = new StringBuilder(tmp.pop());     // add previous string clip to the beginning
                int count = repeat.pop();
                while (count-- > 0) {
                    sb.append(out);     // add repeating string clip
                }
                out = sb.toString();
                i++;
            } else {
                out += s.charAt(i++);       // adding character to the end of current string clip
            }
        }

        return out;
    }

    public static void main(String[] args) {
        DecodeString_394 test = new DecodeString_394();
        System.out.println(test.decodeString("3[a2[c]]"));      // accaccacc
    }
}
