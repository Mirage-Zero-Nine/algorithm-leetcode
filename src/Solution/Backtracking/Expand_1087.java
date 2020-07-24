package Solution.Backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A string S represents a list of words.
 * Each letter in the word has 1 or more options.
 * If there is one option, the letter is represented as is.
 * If there is more than one option, then curly braces delimit the options.
 * For example, "{a,b,c}" represents options ["a", "b", "c"].
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * Return all words that can be formed in this manner, in lexicographical order.
 *
 * @author BorisMirage
 * Time: 2019/07/17 14:34
 * Created with IntelliJ IDEA
 */

public class Expand_1087 {
    /**
     * Backtracking.
     * Note that the string requires reformatting to divide chars can be backtracking.
     *
     * @param s given string
     * @return all words that can be formed in lexicographical order
     */
    public String[] expand(String s) {

        List<String> res = new ArrayList<>();

        backtracking(s, 0, new StringBuilder(), res);

        String[] out = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            out[i] = res.get(i);
        }
        return out;
    }

    /**
     * Backtracking to find all possible combinations.
     *
     * @param s     given string
     * @param index start index
     * @param sb    string builder
     * @param temp  output list
     */
    private void backtracking(String s, int index, StringBuilder sb, List<String> temp) {
        if (index == s.length()) {
            if (sb.length() > 0) {
                temp.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int position = sb.length();

        if (c == '{') {
            List<Character> charList = new ArrayList<>();
            int endIndex = index + 1;
            while (endIndex < s.length() && s.charAt(endIndex) != '}') {
                if (Character.isLetter(s.charAt(endIndex))) {
                    charList.add(s.charAt(endIndex));
                }
                endIndex++;
            }

            Collections.sort(charList);
            for (char d : charList) {
                sb.append(d);
                backtracking(s, endIndex + 1, sb, temp);
                sb.setLength(position);     // remove last char
            }

        } else if (Character.isLetter(c)) {     // if current char is not in {}
            sb.append(s.charAt(index));
            backtracking(s, index + 1, sb, temp);
        }
    }
}
