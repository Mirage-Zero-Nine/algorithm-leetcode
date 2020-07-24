package Solution.Others;

import java.util.ArrayList;
import java.util.List;

/**
 * Flip a string only contains "+" and "-".
 * Can only flip two consecutive "++" into "--".
 * Write a function to compute all possible states of the string after one flip.
 *
 * @author BorisMirage
 * Time: 2019/06/07 18:48
 * Created with IntelliJ IDEA
 */

public class GeneratePossibleNextMoves_293 {
    /**
     * When find capable position, split string and add result to output list.
     *
     * @param s given string
     * @return all possible states of the string after one flip
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> out = new ArrayList<>();

        /* Corner case */
        if (s.length() < 2) {
            return out;
        }

        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                out.add((i == 1 ? "" : s.substring(0, i - 1)) + "--" + s.substring(i + 1));

            }
        }
        return out;
    }
}
