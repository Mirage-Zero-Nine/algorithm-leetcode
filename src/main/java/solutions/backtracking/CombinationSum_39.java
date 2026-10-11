package solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
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
     * Sorts the candidates and explores combinations from left to right. At a recursion level,
     * {@code start} is the first candidate index that may be selected. Passing the selected index
     * back into the recursive call permits unlimited reuse of that value; advancing past it is
     * what prevents generating a second ordering of the same multiset (for example, {@code [2, 3]}
     * after {@code [3, 2]}). Because the array is sorted, a candidate larger than the remaining
     * sum also means every later candidate is too large, so the loop can stop immediately.
     * Reaching zero records a copy of the current path, while removing the last value afterward
     * restores the path for the next branch.
     *
     * <p>Sorting costs O(n log n). The search visits the feasible decision-tree nodes and spends
     * O(k) time copying each output of length k; its auxiliary recursion/path space is O(target / m)
     * where m is the smallest candidate (excluding the returned combinations). The sort mutates
     * {@code candidates}; the method keeps no state between calls.</p>
     *
     * @param candidates distinct positive candidate values
     * @param target     desired sum
     * @return all unique combinations that sum to {@code target}
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // corner cases
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        // Sorting makes the early break safe: once a candidate exceeds the remaining sum,
        // every later candidate is also too large, so none can complete this path.
        // Moving forward through the sorted candidates also keeps each combination in ascending order.
        Arrays.sort(candidates);
        List<List<Integer>> output = new ArrayList<>();
        backtracking(candidates, target, 0, new ArrayList<>(), output);
        return output;
    }

    /**
     * Enumerates the suffix beginning at {@code start} while preserving the invariant that the
     * current path is nondecreasing and never exceeds the target. A copied path is necessary
     * because backtracking reuses and mutates the same list for sibling branches.
     */
    private void backtracking(int[] numbers, int remaining, int start, List<Integer> current, List<List<Integer>> output) {
        if (remaining == 0) {
            output.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < numbers.length; i++) {
            if (numbers[i] > remaining) {
                // Sorting makes the rest of this suffix impossible as well.
                break;
            }
            current.add(numbers[i]);
            backtracking(numbers, remaining - numbers[i], i, current, output);
            current.removeLast();
        }
    }
}
