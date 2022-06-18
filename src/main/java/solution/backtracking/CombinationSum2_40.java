package solution.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target).
 * Find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used ONCE in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * @author BorisMirage
 * Time: 2018/06/23 20:02
 * Created with IntelliJ IDEA
 */

public class CombinationSum2_40 {
    /**
     * Use backtracking to find all combinations via traversing all elements in candidate array.
     *
     * @param candidates input candidate numbers
     * @param target     target numbers
     * @return result int list
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if (candidates.length < 1) {
            return res;
        }

        Arrays.sort(candidates);        // avoid duplication
//        System.out.println(Arrays.toString(candidates));

        if (candidates[0] > target) {       // if min value in array is larger than target, return empty list
            return res;
        }

        backtracking(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    /**
     * Backtracking to find all combination that the sum is target.
     *
     * @param result     result array list
     * @param temp       temp array list, if combination is found it will be added to result
     * @param candidates input candidates
     * @param remain     current input int, compare to target to check whether current combination correct
     * @param start      start position in array for current traversal
     */
    private void backtracking(List<List<Integer>> result, List<Integer> temp, int[] candidates, int remain, int start) {

        if (remain == 0) {
            result.add(new ArrayList<>(temp));      // end point
        }
        if (remain > -1) {
            for (int i = start; i < candidates.length; i++) {

                /* Remove duplicate elements in output */
                if (i == start || candidates[i] != candidates[i - 1]) {
                    temp.add(candidates[i]);
                    backtracking(result, temp, candidates, remain - candidates[i], i + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {

        /* Combination Sum 2 */
        CombinationSum2_40 combinationSum2Test = new CombinationSum2_40();
        int[] candidate = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2Test.combinationSum2(candidate, 8));
        candidate = new int[]{2, 5, 2, 1, 2};
        System.out.println(combinationSum2Test.combinationSum2(candidate, 10));
        candidate = new int[]{2, 5, 2, 1, 2};
        System.out.println(combinationSum2Test.combinationSum2(candidate, 5));
    }
}
