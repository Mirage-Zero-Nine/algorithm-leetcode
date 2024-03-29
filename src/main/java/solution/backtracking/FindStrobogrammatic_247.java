package solution.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 *
 * @author BorisMirage
 * Time: 2018/10/12 10:48
 * Created with IntelliJ IDEA
 */

public class FindStrobogrammatic_247 {
    /**
     * Backtracking to find all the combinations.
     *
     * @param n given n
     * @return all strobogrammatic number
     */
    public List<String> findStrobogrammatic(int n) {
        return recursion(n, n);
    }

    /**
     * Find all strobogrammatic number.
     *
     * @param c current length in num string
     * @param n given n
     * @return all strobogrammatic number under given c
     */
    private List<String> recursion(int c, int n) {

        /* For even n */
        if (c == 0) {
            return new ArrayList<>(Collections.singletonList(""));
        }

        /* For odd n */
        if (c == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        }

        List<String> l = recursion(c - 2, n);
        List<String> output = new ArrayList<>();

        for (String s : l) {

            /* As result output string center
             * "0" is invalid when it is put in first and last */
            if (c != n) {
                output.add("0" + s + "0");
            }

            output.add("1" + s + "1");
            output.add("6" + s + "9");
            output.add("8" + s + "8");
            output.add("9" + s + "6");
        }
        return output;
    }
}
