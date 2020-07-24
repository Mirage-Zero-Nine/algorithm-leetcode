package Solution.SlidingWindow;

/**
 * Given a string that consists of only uppercase English letters.
 * Replace any letter in the string with another letter at most k times.
 * Find the length of a longest substring containing all repeating letters after performing the above operations.
 *
 * @author BorisMirage
 * Time: 2019/06/18 17:18
 * Created with IntelliJ IDEA
 */

public class CharacterReplacement_424 {
    /**
     * Sliding window.
     * The window can only extend its size when the count of the new char exceeds the historical max count.
     * Therefore, count each char's appearance during the traverse.
     * Only consider if the max count exceeds the historical max count.
     *
     * @param s given string
     * @param k limit of replacing operations
     * @return longest substring containing all repeating letters
     */
    public int characterReplacement(String s, int k) {

        /* Corner case */
        if (s.length() < 1) {
            return 0;
        }
        if (k > s.length()) {       // directly replace all char in string
            return s.length();
        }

        int[] count = new int[26];      // store char appearance
        int max = 0, window = 0, start = 0;

        for (int i = 0; i < s.length(); i++) {

            /*
             * During traverse, the max possible window is the most occurred char + k.
             * k is the largest number of possible replacement. And the char count is under current window size.
             * The initially window size is 1. To extend the window size, there are two conditions:
             * 1. There are replacement operations left for different char.
             * 2. Find a new char that is either the current most occurred char, or new max count.
             * If window size is larger than max possible size (max char + k), then the window size should be narrowed.
             * When narrowing the window size, reduce char count that will be excluded from window . */
            max = Math.max(max, ++count[s.charAt(i) - 'A']);    // resize window if new max count found
            while (i - start + 1 > k + max) {                   // narrow window
                count[s.charAt(start++) - 'A']--;               // remove char that is excluded from window
            }

            window = Math.max(window, i - start + 1);           // find max window size
        }

        return window;
    }

    public static void main(String[] args) {
        CharacterReplacement_424 test = new CharacterReplacement_424();
        System.out.println(test.characterReplacement("AABABBA", 2));        // 4
        System.out.println(test.characterReplacement("AABABBA", 1));        // 4
    }
}
