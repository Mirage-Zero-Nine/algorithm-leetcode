package Solution.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function to generate the generalized abbreviations of a word.
 *
 * @author BorisMirage
 * Time: 2019/06/04 13:16
 * Created with IntelliJ IDEA
 */

public class GenerateAbbreviations_320 {
    /**
     * Backtracking to generate abbreviation.
     *
     * @param word given word
     * @return all generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
        List<String> out = new ArrayList<>();

        /* Corner case */
        if (word.length() < 1) {
            out.add(word);
            return out;
        }

        backtracking(out, word, "", 0, 0);      // initial count should be 0 to add original word into result
        return out;
    }

    /**
     * Backtracking.
     * There are two calling during the process, which is different from normal backtracking:
     * 1. Replace one more char in abbreviation, such as 1 -> 2.
     * 2. Moving abbreviation position forward, such as 1ord -> w1rd.
     *
     * @param out      output list
     * @param word     given word
     * @param current  current string
     * @param position start position
     * @param count    how many chars will be replaced
     */
    private void backtracking(List<String> out, String word, String current, int position, int count) {
        if (position == word.length()) {        // end point
            if (count > 0) {
                current = current + count;      // avoid adding "0" to result
            }
            out.add(current);
        } else {
            backtracking(out, word, current, position + 1, count + 1);       // one more char replacement
            backtracking(out, word, current + (count > 0 ? count : "") + word.charAt(position), position + 1, 0);        // next position replacement
        }
    }

    public static void main(String[] args) {
        GenerateAbbreviations_320 test = new GenerateAbbreviations_320();
        System.out.println(test.generateAbbreviations("abc"));
    }
}
