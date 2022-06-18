package solution.others;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 * 1. First letter is in upper case.
 * 2. Each word in text are separated by a single space.
 * Rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
 * If two words have the same length, arrange them in their original order.
 * Return the new text following the format shown above.
 *
 * @author BorisMirage
 * Time: 2020/05/18 14:42
 * Created with IntelliJ IDEA
 */

public class ArrangeWords_1451 {
    /**
     * Simply sort the array based on word length.
     * A quick note:
     * If the element is primitive type, quick sort is applied, which won't maintain the order since it is not stable.
     * If the element is not primitive type, then merge sort is applied and it will maintain the order in collection.
     *
     * @param text given text
     * @return the new text following the format
     */
    public String arrangeWords(String text) {
        String[] arr = text.toLowerCase().split(" ");
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        StringBuilder sb = new StringBuilder();

        for (String w : arr) {
            for (Character c : w.toCharArray()) {
                sb.append(c);
            }
            sb.append(' ');
        }

        sb.deleteCharAt(sb.length() - 1);
        String tmp = sb.toString();

        return Character.toUpperCase(tmp.charAt(0)) + tmp.substring(1);
    }
}
