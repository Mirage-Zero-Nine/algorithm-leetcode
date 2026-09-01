package solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of distinct integers, returns all possible permutations.
 *
 * @author BorisMirage
 * Time: 2018/06/24 15:15
 * Created with IntelliJ IDEA
 */
public class Permute_46 {
    /**
     * Generates every permutation of {@code nums}.
     *
     * <p>At each recursion level, the stream visits every input value. The
     * {@code set::add} predicate both selects an unused value and marks it as
     * used. After the recursive call returns, the value is removed from both
     * the set and the temporary permutation so that the next branch starts
     * with the previous state restored.</p>
     *
     * <p>The input array is not modified. For {@code n} distinct values, the
     * algorithm produces {@code n!} permutations. Its time complexity is
     * {@code O(n * n!)} because each result contains {@code n} values; the
     * auxiliary recursion/set space is {@code O(n)}, excluding the returned
     * output of size {@code O(n * n!)}.</p>
     *
     * @param nums the distinct values to permute; may be {@code null}
     * @return all permutations, or an empty list when {@code nums} is null or empty
     */
    public List<List<Integer>> permute(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> output = new ArrayList<>();
        backtracking(nums, new HashSet<>(), new ArrayList<>(), output);
        return output;
    }

    /**
     * Explores all choices for the next position in the current permutation.
     *
     * <p>Each recursive invocation represents one depth in the decision tree:
     * {@code tmp} contains the values selected so far and {@code set} records
     * those same values as used. The stream examines every value in
     * {@code nums}; {@code set::add} accepts a value only when it has not
     * already been selected, and marks it as used at the same time.</p>
     *
     * <p>When {@code set.size()} equals {@code nums.length}, the current list
     * is complete. A new list is stored in {@code output} because {@code tmp}
     * is mutable and is shared by all recursive branches. After exploring a
     * branch, the selected value is removed from both {@code set} and
     * {@code tmp}; this restores the state needed to explore the next branch.</p>
     *
     * <p>The input values must be distinct because {@code set} tracks values,
     * not positions. With duplicate values, two different input positions
     * would be treated as the same choice.</p>
     *
     * @param nums   input values
     * @param set    values already present in {@code tmp}
     * @param tmp    current partial permutation
     * @param output accumulated complete permutations
     */
    private void backtracking(int[] nums, Set<Integer> set, List<Integer> tmp, List<List<Integer>> output) {
        if (set.size() == nums.length) {
            output.add(new ArrayList<>(tmp));
            return;
        }

        Arrays.stream(nums)
                // avoid previously used values
                .filter(set::add)
                .forEach(n -> {
                    tmp.add(n);
                    backtracking(nums, set, tmp, output);

                    // restore both pieces of mutable state before trying the next value
                    set.remove(n);
                    tmp.removeLast();
                });
    }
}