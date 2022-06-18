package solution.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n.
 * Only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Note:
 * 1. All numbers will be positive integers.
 * 2. The solution set must not contain duplicate combinations.
 *
 * @author BorisMirage
 * Time: 2019/06/03 15:29
 * Created with IntelliJ IDEA
 */

public class CombinationSum3_216 {
    /**
     * Backtracking.
     *
     * @param k size of each combination
     * @param n target sum of each combination
     * @return all possible combinations of k numbers that sum is n
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> output = new ArrayList<>();
        if (k < 1 || n < 1) {
            return output;
        }

        backtracking(1, k, n, output, new ArrayList<>());

        return output;
    }

    /**
     * Backtracking. Note that i can be only from 1 to 9.
     * If the temp list size is not matched to given condition, it should not be added to result.
     *
     * @param current start position in array from 1 to 9
     * @param k       size of each combination
     * @param n       target sum
     * @param output  output list
     * @param temp    temp list
     */
    private void backtracking(int current, int k, int n, List<List<Integer>> output, List<Integer> temp) {
        if (temp.size() == k && n == 0) {
            output.add(new ArrayList<>(temp));      // end point
        } else {
            for (int i = current; i < 10; i++) {
                temp.add(i);
                backtracking(i + 1, k, n - i, output, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
