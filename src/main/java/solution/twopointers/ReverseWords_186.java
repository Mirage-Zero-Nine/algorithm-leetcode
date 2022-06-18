package solution.twopointers;

/**
 * @author BorisMirage
 * Time: 2020/03/24 09:21
 * Created with IntelliJ IDEA
 */

public class ReverseWords_186 {
    /**
     * Swap the whole array, then swap each word.
     * For example:
     * (the sky is blue)
     * => (eulb si yks eht)
     * => (blue is sky the)
     *
     * @param s given array
     */
    public void reverseWords(char[] s) {

        /* Corner case */
        if (s == null || s.length <= 1) {
            return;
        }

        int n = s.length, fast = 0, slow = 0;
        swap(s, 0, n - 1);      // swap the whole array

        while (fast < n) {
            while (fast < n - 1 && s[fast + 1] != ' ') {
                fast++;
            }
            swap(s, slow, fast);           // swap each word
            fast += 2;
            slow = fast;
        }
    }

    /**
     * Swap part of array based on start and end index.
     *
     * @param s     given array
     * @param start start index
     * @param end   end index
     */
    private void swap(char[] s, int start, int end) {

        while (start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        char[] tmp = new char[]{'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'};
        ReverseWords_186 test = new ReverseWords_186();
        test.reverseWords(tmp);

        System.out.println(tmp);
    }
}
