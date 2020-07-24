package Solution.Map;

/**
 * There is a special keyboard with all keys in a single row.
 * Given a string keyboard of length 26 indicating the layout of the keyboard, initially your finger is at index 0.
 * To type a character, you have to move your finger to the index of the desired character.
 * The time taken to move your finger from index i to index j is |i - j|.
 * You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.
 *
 * @author BorisMirage
 * Time: 2019/08/26 15:28
 * Created with IntelliJ IDEA
 */

public class CalculateTime_1165 {
    /**
     * Use a char array to record the mapping relation.
     *
     * @param keyboard given string representing keyboard
     * @param word     given word
     * @return how much time it takes to type it with one finger
     */
    public int calculateTime(String keyboard, String word) {
        if (word.length() == 0) {
            return 0;
        }

        int[] arr = new int[26];
        for (int i = 0; i < 26; i++) {
            arr[keyboard.charAt(i) - 'a'] = i;
        }

        int total = 0, pre = 0;

        for (int i = 0; i < word.length(); i++) {
            int tmp = arr[word.charAt(i) - 'a'];
            if (i == 0) {
                total += tmp;
                pre = total;
            } else {
                total = total + Math.abs(pre - tmp);
                pre = tmp;
            }
        }

        return total;
    }
}
