package Solution.TwoPointers;

/**
 * Given a string, reverse the order of characters in each word within a sentence with initial word order.
 *
 * @author BorisMirage
 * Time: 2020/04/05 16:01
 * Created with IntelliJ IDEA
 */

public class ReverseWords_557 {
    /**
     * Find each word and reverse it.
     *
     * @param s given string
     * @return string reversed the order of characters in each word
     */
    public String reverseWords(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] arr = s.toCharArray();

        int slow = 0, fast = 0;

        while (fast < s.length()) {
            while (fast < s.length() && arr[fast] != ' ') {
                fast++;
            }

            reverse(arr, slow, fast - 1);
            fast++;
            slow = fast;
        }

        return new String(arr);
    }

    /**
     * Reverse words in array.
     *
     * @param arr char array
     * @param i   start index
     * @param j   end index
     */
    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
