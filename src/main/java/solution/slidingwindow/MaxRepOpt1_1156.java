package solution.slidingwindow;

/**
 * Given a string text, we are allowed to swap two of the characters in the string (only can swap 1 time).
 * Find the length of the longest substring with repeated characters.
 *
 * @author BorisMirage
 * Time: 2019/08/10 20:16
 * Created with IntelliJ IDEA
 */

public class MaxRepOpt1_1156 {
    /**
     * Sliding window. First traverse the whole string to count each char appearance.
     * Then traverse the string again, each time find the max window starting at current char.
     * The max possible window is the char occurred time in string.
     * Since there is only one swap is allowed, the window should stop extending when find a different char.
     *
     * @param s given string
     * @return length of the longest substring with repeated characters
     */
    public int maxRepOpt1_1156(String s) {

        /* Corner case */
        if (s.length() < 1) {
            return 0;
        }

        int n = s.length();
        int[] arr = new int[26];
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;       // count char appearance
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int j = i, window = 0;      // each time, reset the window
            boolean canContinue = true;

            /*
             * Each time, reset the window and find the longest substring starting at current char.
             * Window can extend only when these conditions are fulfilled:
             * 1. At most one different char (compare to s.charAt(i)) is found.
             * 2. Window size is larger than s.charAt(i) occurrence. */
            while (j < s.length() && (s.charAt(i) == s.charAt(j) || canContinue) && window < arr[s.charAt(i) - 'a']) {
                if (s.charAt(j++) != s.charAt(i)) {       // found a different char, swap and end window extension
                    canContinue = false;
                }
                window++;
            }
            max = Math.max(max, window);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxRepOpt1_1156().maxRepOpt1_1156("aaabaaa"));
    }
}
