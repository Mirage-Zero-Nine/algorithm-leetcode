package Solution.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target).
 * Find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Note:
 * 1. All numbers (including target) will be positive integers.
 * 2. The solution set must not contain duplicate combinations.
 *
 * @author BorisMirage
 * Time: 2018/06/22 21:56
 * Created with IntelliJ IDEA
 */

public class CombinationSum_39 {
    /**
     * Use backtracking to find all combinations via traversing all elements in candidate array.
     *
     * @param candidates input candidate numbers
     * @param target     target numbers
     * @return result int list
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (candidates.length < 1) {
            return out;
        }

        Arrays.sort(candidates); // sort to avoid duplication during backtracking

        backtracking(out, new ArrayList<>(), candidates, target, 0);
        return out;
    }

    /**
     * Backtracking to find all combination that the sum is target.
     *
     * @param output     result array list
     * @param temp       temp array list, if combination is found it will be added to result
     * @param candidates input candidates
     * @param remain     current input int, compare to target to check whether current combination correct
     * @param start      start position in array for current traversal
     */
    private void backtracking(List<List<Integer>> output, List<Integer> temp, int[] candidates, int remain, int start) {

        if (remain == 0) {
            output.add(new ArrayList<>(temp));
        }
        if (remain > -1) {       // if remain < 0, then current int can not be the result

            for (int i = start; i < candidates.length; i++) {
                temp.add(candidates[i]);
                backtracking(output, temp, candidates, remain - candidates[i], i);
                temp.remove(temp.size() - 1);      // if it is not a possible combination, remove and continue
            }
        }
    }

    public static void main(String[] args) {
        CombinationSum_39 combinationSumTest = new CombinationSum_39();
        System.out.println(combinationSumTest.combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSumTest.combinationSum(new int[]{2, 3, 5}, 8));
    }
}
