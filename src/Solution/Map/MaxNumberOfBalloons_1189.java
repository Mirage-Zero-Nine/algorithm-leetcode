package Solution.Map;

import java.util.HashMap;

/**
 * Given a string text, use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 * @author BorisMirage
 * Time: 2019/09/15 10:12
 * Created with IntelliJ IDEA
 */

public class MaxNumberOfBalloons_1189 {
    /**
     * Straight forward approach. Use a hash map to store the char and its appearance.
     *
     * @param text given text
     * @return maximum number of instances that can be formed
     */
    public int maxNumberOfBalloons(String text) {
        int out = 0;

        /* Corner case */
        if (text.length() < 7) {
            return out;
        }

        HashMap<Character, Integer> m = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            m.put(text.charAt(i), m.getOrDefault(text.charAt(i), 0) + 1);
        }

        if (!m.containsKey('b') || !m.containsKey('a') || !m.containsKey('l') || !m.containsKey('o') || !m.containsKey('n')) {
            return out;
        }

        out = Math.min(m.get('b'), m.get('a'));
        out = Math.min(out, m.get('l') / 2);
        out = Math.min(out, m.get('o') / 2);
        out = Math.min(m.get('n'), out);

        return out;
    }
}
