package solution.math;

import java.util.LinkedList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * @author BorisMirage
 * Time: 2018/08/09 12:51
 * Created with IntelliJ IDEA
 */

public class Combine_77 {
    /**
     * Two approaches. First approach is using backtracking.
     * The other approach is to use the property of combination.
     * Using combination based on recursion is faster, where C(n, k) = C(n - 1, k - 1) + C(n - 1, k).
     *
     * @param n n numbers could be in combination, from 1 to n
     * @param k each combination length
     * @return list contains all combinations
     */
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> output = new LinkedList<>();

        /* Corner case */
        if (k == 0 || n < k) {      // C(n, k), n must be equal or larger than k, and k should be larger then 0
            return output;          // also, recursion ends here
        }

        output = combine(n - 1, k - 1);     // C(n - 1, k - 1)

        if (output.isEmpty()) {
            output.add(new LinkedList<>());     // add combination list during recursion
        }

        for (List<Integer> list : output) {
            list.add(n);
        }

        output.addAll(combine(n - 1, k));       // the other part, C(n - 1, k)

        return output;
    }
}