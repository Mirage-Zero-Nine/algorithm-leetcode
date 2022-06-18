package solution.dfs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.
 * For example, 321 is a Stepping Number while 421 is not.
 * Given two integers low and high.
 * Find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.
 *
 * @author BorisMirage
 * Time: 2019/10/09 16:01
 * Created with IntelliJ IDEA
 */

public class CountSteppingNumbers_1215 {
    /**
     * DFS to find each available result.
     *
     * @param low  low bound
     * @param high high bound
     * @return sorted list of all the Stepping Numbers in the range [low, high] inclusive
     */
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> out = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            dfs(low, high, out, i);
        }
        Collections.sort(out);
        return out;
    }

    /**
     * DFS search to find all correct answer.
     * Use long for temporary value during DFS.
     * During the traverse, temp value may overflow and hence become negative, which will never end the DFS recursion.
     *
     * @param low  low bound
     * @param high high bound
     * @param out  output list
     * @param tmp  temp value during DFS, use long to avoid TLE caused by overflow
     */
    private void dfs(int low, int high, List<Integer> out, long tmp) {

        if (tmp >= low && tmp <= high) {
            out.add((int) tmp);
        }

        if (tmp > high || tmp == 0) {
            return;
        }

        if (tmp % 10 == 0) {
            dfs(low, high, out, tmp % 10 + 1 + tmp * 10);
        } else if (tmp % 10 == 9) {
            dfs(low, high, out, tmp % 10 - 1 + tmp * 10);
        } else {
            dfs(low, high, out, tmp % 10 + 1 + tmp * 10);
            dfs(low, high, out, tmp % 10 - 1 + tmp * 10);
        }
    }
}
