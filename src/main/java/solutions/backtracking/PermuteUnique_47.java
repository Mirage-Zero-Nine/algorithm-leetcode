package solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Generates all unique permutations of an array that may contain duplicates.
 *
 * <p>The input is sorted so equal values are adjacent. Backtracking then
 * tracks used array indices with {@code visited}. Indices are tracked instead
 * of values because equal values still need to be selected the required number
 * of times.</p>
 *
 * <p>Equal values are interchangeable. Therefore, at any one recursion depth,
 * the first unused copy of a value must be selected before a later copy. The
 * duplicate-selection rule in {@link #backtracking(int[], boolean[], List,
 * List)} enforces this ordering and prevents duplicate result lists.</p>
 */
public class PermuteUnique_47 {
    /**
     * Returns every distinct permutation of {@code nums}.
     *
     * <p>The array is sorted in place, so this method may modify the caller's
     * input. For an input with value frequencies {@code c1, c2, ...}, the
     * number of results is {@code n! / (c1! * c2! * ...)}. The worst-case time
     * complexity is {@code O(n * n!)} and the recursion uses {@code O(n)}
     * auxiliary space, excluding the returned output.</p>
     *
     * @param nums values to permute; may be {@code null}
     * @return all unique permutations, or an empty list when {@code nums} is
     * {@code null} or empty
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // corner cases
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        // Sorting places equal values next to each other so the duplicate
        // condition in backtracking can make a local, depth-specific decision.
        Arrays.sort(nums);

        List<List<Integer>> output = new ArrayList<>();
        backtracking(nums, new boolean[nums.length], new ArrayList<>(), output);
        return output;
    }

    /**
     * Builds a permutation one position at a time.
     *
     * <p>The filter condition has two independent parts:</p>
     * <ul>
     *     <li>{@code !visited[i]}: this array index is not already in the
     *     current permutation.</li>
     *     <li>{@code i == 0 || nums[i] != nums[i - 1] || visited[i - 1]}:
     *     select the current value when it is the first value, differs from
     *     its predecessor, or its equal predecessor has already been used in
     *     the current prefix. Because {@code ||} is evaluated left to right
     *     with short-circuiting, {@code i == 0} also prevents access to index
     *     {@code -1}.</li>
     * </ul>
     *
     * <p>For sorted {@code [1a, 1b, 2]}, this prevents selecting {@code 1b}
     * before {@code 1a} at the same depth, because that would duplicate the
     * branch beginning with {@code 1a}. Once {@code 1a} is selected,
     * {@code visited[i - 1]} is true and {@code 1b} is allowed at the next
     * depth, so valid permutations are preserved.</p>
     *
     * @param nums    sorted input values
     * @param visited whether each input index is already in {@code tmp}
     * @param tmp     current partial permutation
     * @param output  accumulated unique permutations
     */
    private void backtracking(int[] nums, boolean[] visited, List<Integer> tmp, List<List<Integer>> output) {
        if (tmp.size() == nums.length) {
            output.add(new ArrayList<>(tmp));
            return;
        }

        IntStream.range(0, nums.length)
                // Do not choose an index twice. For adjacent equal values,
                // require the previous equal index to be used first. The
                // left-to-right short-circuit order makes i == 0 safe and
                // removes duplicate branches without removing valid results.
                .filter(i -> !visited[i] && ((i == 0) || nums[i] != nums[i - 1] || visited[i - 1]))
                .forEach(i -> {
                    visited[i] = true;
                    tmp.add(nums[i]);
                    backtracking(nums, visited, tmp, output);

                    // Undo the choice before considering the next index.
                    visited[i] = false;
                    tmp.removeLast();
                });
    }
}
