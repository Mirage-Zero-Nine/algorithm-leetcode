package solutions.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * @author BorisMirage
 * Time: 2018/08/09 12:51
 * Created with IntelliJ IDEA
 */

public class Combine_77 {
    /**
     * Generates combinations with depth-first backtracking.
     *
     * @param n numbers are chosen from the range {@code [1, n]}
     * @param k number of values in each combination
     * @return all {@code k}-element combinations, or an empty list when the input cannot produce a combination
     */
    public List<List<Integer>> combine(int n, int k) {
        // corner case
        if (k == 0 || k < 0 || n < k) {
            return new ArrayList<>();
        }

        List<List<Integer>> output = new ArrayList<>();
        backtracking(1, n, k, new ArrayList<>(), output);
        return output;
    }

    /**
     * Builds combinations recursively by choosing one candidate for the next
     * position and then exploring the remaining suffix of the input range.
     *
     * <p>The recursion maintains this invariant: {@code tmp} is a strictly
     * increasing valid prefix, and every next value must be in
     * {@code [start, end]}. Passing {@code i + 1} to the next call prevents a
     * value from being reused and keeps each combination ordered.</p>
     *
     * <p>When {@code tmp} reaches size {@code k}, a copy is stored in
     * {@code output}. The copy is necessary because {@code tmp} is shared and
     * restored after each recursive branch. After a branch returns,
     * {@code tmp.removeLast()} restores the prefix so the next candidate starts
     * from the same state.</p>
     *
     * <p>If the current prefix has size {@code t}, it needs {@code k - t}
     * values in total. After choosing {@code i}, {@code end - i} values remain,
     * so a viable choice must satisfy:</p>
     *
     * <pre>{@code
     * end - i >= k - (t + 1)
     * i <= end - (k - t) + 1
     * }</pre>
     *
     * <p>The upper bound in {@code max} applies this inequality to avoid
     * exploring prefixes that cannot be completed.</p>
     *
     * @param start  smallest value that may be chosen for the next position
     * @param end    largest value available in the input range
     * @param k      target combination size
     * @param tmp    mutable prefix of the combination currently being built
     * @param output accumulator for completed combinations
     */
    private void backtracking(int start, int end, int k, List<Integer> tmp, List<List<Integer>> output) {
        if (tmp.size() == k) {
            output.add(new ArrayList<>(tmp));
            return;
        }

        // Let t be the number of values already selected (current tmo list size).
        // We need k - t values in total, including the next value i.
        // After selecting i, the values available for the rest of the combination are i + 1 through end;
        // there are end - i of them. To keep i viable, that range must contain the remaining k - (t + 1) values:
        //
        //     end - i >= k - (t + 1)
        //     i <= end - (k - t) + 1
        //
        // max is therefore the largest i that can still lead to a complete combination.
        // Values greater than max are skipped because they would leave too few numbers for the remaining positions.
        int max = end - (k - tmp.size()) + 1;
        IntStream.rangeClosed(start, max).forEach(i -> {
            tmp.add(i);
            backtracking(i + 1, end, k, tmp, output);
            tmp.removeLast();
        });
    }

    /**
     * Generates combinations using the recurrence for the binomial coefficient:
     *
     * <pre>{@code
     * C(n, k) = C(n - 1, k - 1) + C(n - 1, k)
     * }</pre>
     *
     * <p>Every combination either contains {@code n} or does not contain
     * {@code n}. The first recursive branch creates combinations containing
     * {@code n}; the second creates combinations that exclude it.</p>
     *
     * <p>This approach makes the mathematical structure explicit, but it usually
     * does more allocation and repeated subproblem work than {@link #combine(int,
     * int)}. Both approaches have to materialize the same output.</p>
     *
     * @param n numbers are chosen from the range {@code [1, n]}
     * @param k number of values in each combination
     * @return all {@code k}-element combinations, or an empty list when the
     * input cannot produce a combination
     */
    public List<List<Integer>> combineMath(int n, int k) {
        // corner case
        if (k == 0 || k < 0 || n < k) {
            return new ArrayList<>();
        }

        return combineByRecurrence(n, k);
    }

    /**
     * Recursive implementation of {@code C(n, k) = C(n - 1, k - 1) + C(n - 1, k)}.
     * The {@code k == 0} result is one empty combination, which is the identity
     * needed by the branch that adds {@code n} to a combination.
     */
    private List<List<Integer>> combineByRecurrence(int n, int k) {
        if (k == 0) {
            List<List<Integer>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }

        if (n < k || n == 0) {
            return new ArrayList<>();
        }

        // Combinations that contain n come from choosing k - 1 values from 1..n-1.
        List<List<Integer>> withN = combineByRecurrence(n - 1, k - 1);
        for (List<Integer> combination : withN) {
            combination.add(n);
        }

        // Combinations that do not contain n come from choosing k values from 1..n-1.
        withN.addAll(combineByRecurrence(n - 1, k));
        return withN;
    }
}