package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds unique combinations of exactly {@code k} values whose sum equals a
 * target.
 *
 * <p>This is a generalized backtracking solution. Sorting the input allows the
 * recursion to select values from left to right, skip duplicate values at the
 * same depth, and stop early when a positive value is already too large for
 * the remaining target.</p>
 *
 * <p>The implementation returns unique combinations by value. For example,
 * repeated input values may be used more than once when enough copies exist,
 * but the same value combination is returned only once.</p>
 *
 * @author BorisMirage
 */
public class KSum {
    /**
     * Finds unique combinations of exactly {@code k} input values that sum to
     * {@code target}.
     *
     * <p>The input is sorted before the search. The recursive search maintains
     * a prefix of selected values and only considers later indices, so an input
     * element cannot be selected twice. At each recursion depth, equal values
     * are skipped to avoid duplicate combinations.</p>
     *
     * <p>If {@code k <= 0} or {@code k > nums.length}, this method returns an
     * empty list. Sorting reorders the supplied array in place.</p>
     *
     * <p>For a fixed {@code k}, the worst-case time complexity is
     * {@code O(n^k)} after sorting, or {@code O(n log n + n^k)} including the
     * sort. The recursion path uses {@code O(k)} extra space, excluding the
     * returned combinations. Since the number of valid combinations can itself
     * be large, output storage is not included in the auxiliary-space bound.</p>
     *
     * @param nums   input values; the array may be reordered by sorting
     * @param k      number of values required in each combination
     * @param target required sum of each combination
     * @return unique combinations of exactly {@code k} values whose sum is
     * {@code target}; an empty list when no combination exists or the
     * input arguments are outside the supported range
     */
    public List<List<Integer>> kSum(int[] nums, int k, int target) {

        List<List<Integer>> out = new ArrayList<>();

        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return out;
        }

        // Sorting provides two properties used by backtracking: equal values
        // become adjacent for duplicate skipping, and positive values appear
        // after all negative values for pruning.
        Arrays.sort(nums);
        backtracking(out, new ArrayList<>(), nums, k, (long) target, 0);

        return out;
    }

    /**
     * Recursively selects values for one candidate combination.
     *
     * <p>{@code tmp} stores the values selected so far. {@code k} records how
     * many more values are required, {@code target} records the remaining sum,
     * and {@code position} is the first index that may be selected. Passing
     * {@code i + 1} to the next call ensures combinations use distinct input
     * positions and are generated in sorted order.</p>
     *
     * <p>The loop stops before the final {@code k - 1} positions when there are
     * not enough values left to complete a combination. The positive-target
     * condition is a safe pruning rule because {@code arr} is sorted.</p>
     *
     * @param out      accumulated combinations
     * @param tmp      values selected for the current combination
     * @param arr      sorted input values
     * @param k        number of values still required
     * @param target   sum still required
     * @param position first index that may be selected
     */
    private void backtracking(
            List<List<Integer>> out,
            List<Integer> tmp,
            int[] arr,
            int k,
            long target,
            int position
    ) {

        if (k == 0) {
            // A combination is complete only when all required values were
            // selected and the remaining target is exactly zero.
            if (target == 0) {
                out.add(new ArrayList<>(tmp));
            }
            return;
        }

        // There are not enough remaining positions to select k values.
        if (arr.length - position < k) {
            return;
        }

        for (int i = position; i <= arr.length - k; i++) {
            // Because arr is sorted, every later value is also positive and at
            // least arr[i]. If arr[i] is already greater than target, no later
            // combination can reach the target without reusing an earlier
            // negative value.
            if (arr[i] > 0 && arr[i] > target) {
                break;
            }

            // Equal values at one recursion depth lead to the same value
            // combination. Equal values at deeper levels remain available,
            // allowing combinations such as [0, 0, 0].
            if (i == position || arr[i] != arr[i - 1]) {
                tmp.add(arr[i]);
                backtracking(out, tmp, arr, k - 1, target - arr[i], i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
