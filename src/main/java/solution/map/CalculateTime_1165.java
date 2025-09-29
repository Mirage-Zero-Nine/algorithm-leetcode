package solution.map;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
     * Calculates the total time required to type a given word on a custom keyboard layout.
     *
     * @param keyboard a 26‑character string representing the order of keys on the keyboard. The first character is at index 0.
     * @param word     the string to be typed using the keyboard. It consists only of characters present in {@code keyboard}.
     * @return the sum of absolute differences between successive key positions, i.e., the total time taken to type {@code word}.
     */
    public int calculateTime(String keyboard, String word) {

        // corner case
        if (keyboard == null || keyboard.length() != 26 || word == null || word.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> map = IntStream.range(0, keyboard.length())
                .boxed()
                .collect(Collectors.toMap(
                        keyboard::charAt, i -> i));

        int output = 0, cursor = 0;
        for (int i = 0; i < word.length(); i++) {
            int currentPosition = map.get(word.charAt(i));
            output += Math.abs(currentPosition - cursor);
            cursor = currentPosition;
        }

        return output;
    }
}
