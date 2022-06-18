package solution.others;

import java.util.Arrays;

/**
 * Given an input string, reverse the string word by word.
 * Note:
 * 1. A word is defined as a sequence of non-space characters.
 * 2. Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * 3. You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * @author BorisMirage
 * Time: 2019/07/05 19:46
 * Created with IntelliJ IDEA
 */

public class ReverseWords_151 {
    /**
     * Spilt given string by regex.
     *
     * @param s given string
     * @return reversed words
     */
    public String reverseWords(String s) {
        if (s.length() < 1) {
            return s;
        }

        String[] temp = s.split(" +");
        if (temp.length == 0) {
            return "";
        }

        StringBuilder out = new StringBuilder();
        for (int i = temp.length - 1; i > -1; i--) {
            if (temp[i].length() != 0) {
                out.append(temp[i]).append(" ");
            }
        }
        return out.substring(0, out.toString().length() - 1);
    }

    /**
     * Use pointers to remove spaces.
     *
     * @param s given string
     * @return reversed words
     */
    public String twoPointers(String s) {

        char[] array = s.toCharArray();
        int length = 0;

        for (int j = 0; j < array.length; j++) {
            if (array[j] != ' ' || (length > 0 && array[length - 1] != ' ')) {
                array[length++] = array[j];
            }
        }

        if (length > 0 && array[length - 1] == ' ') {
            length--;
        }

        array = Arrays.copyOf(array, length);
        reverse(array, 0, array.length - 1);

        for (int i = 0, j = 0; j <= array.length; j++) {
            if (j == array.length || array[j] == ' ') {
                reverse(array, i, j - 1);
                i = j + 1;
            }
        }
        return new String(array);
    }

    /**
     * Reverse array with start index and ending index.
     *
     * @param s given array
     * @param i start index
     * @param j end index
     */
    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            char tmp = s[i];
            s[i++] = s[j];
            s[j--] = tmp;
        }
    }

    public static void main(String[] args) {
        ReverseWords_151 test = new ReverseWords_151();
        System.out.println("Output is: " + test.reverseWords(" "));
        System.out.println("Output is: " + test.reverseWords("a"));
        System.out.println("Output is: " + test.reverseWords(""));
        System.out.println("Output is: " + test.reverseWords("  hello world!  "));
    }
}
