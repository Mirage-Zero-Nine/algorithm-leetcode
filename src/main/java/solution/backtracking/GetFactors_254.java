package solution.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * @author BorisMirage
 * Time: 2020/03/28 15:04
 * Created with IntelliJ IDEA
 */

public class GetFactors_254 {
    /**
     * Backtracking.
     *
     * @param n given n
     * @return all possible combinations of its factors
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (n < 4) {
            return out;
        }

        backtracking(out, new LinkedList<>(), n, 2);

        return out;
    }

    /**
     * Backtracking to find all factors.
     * Note that if a factor is found, then add the other factor to the list after the current branch of backtracking.
     * In this way to narrow down the search from n to sqrt(n).
     *
     * @param out    output list
     * @param tmp    temporary list stores combination of factors
     * @param target target value
     * @param start  start value
     */
    private void backtracking(List<List<Integer>> out, List<Integer> tmp, int target, int start) {

        for (int i = start; i * i <= target; i++) {
            if (target % i == 0) {
                tmp.add(i);     // first factor
                backtracking(out, tmp, target / i, i);
                tmp.add(target / i);        // second factor
                out.add(new LinkedList<>(tmp));
                tmp.remove(tmp.size() - 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new GetFactors_254().getFactors(12));
    }
}
