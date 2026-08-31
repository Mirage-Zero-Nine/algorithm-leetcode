package solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates all possible subsets (the power set) of an array of distinct integers.
 *
 * @author BorisMirage
 * Time: 2018/08/09 13:44
 * Created with IntelliJ IDEA
 */

public class Subsets_78 {

    /**
     * Generates the power set using recursive backtracking.
     *
     * <p>The empty array has one subset—the empty subset. A {@code null} input returns an
     * empty result.</p>
     *
     * @param nums input array of distinct integers
     * @return all subsets of {@code nums}; an empty array returns a list containing the empty subset
     */
    public List<List<Integer>> subsets(int[] nums) {
        // corner case
        if (nums == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> output = new ArrayList<>();
        backtracking(new ArrayList<>(), output, nums, 0);
        return output;
    }

    /**
     * Enumerates every subset that can be formed from {@code nums[start]} onward.
     *
     * <p>{@code current} is the candidate subset for this recursion level. Every candidate
     * is added to the result before exploring further choices, so the empty subset and all
     * intermediate subsets are included. For each possible next element, the method follows
     * the backtracking pattern: choose the element, explore recursively from the next index,
     * and unchoose the element to restore the caller's state.</p>
     *
     * <p>A copy of {@code current} is stored because the same mutable list is reused while
     * the recursion explores other branches.</p>
     *
     * @param current current candidate subset; it is restored before this method returns
     * @param output  result list to which defensive copies of candidates are added
     * @param arr     input array of distinct integers
     * @param start   index of the first element that may be selected
     */
    private void backtracking(List<Integer> current, List<List<Integer>> output,
                              int[] arr, int start) {
        // every path is a valid subset
        output.add(new ArrayList<>(current));

        for (int i = start; i < arr.length; i++) {
            // include arr[i] in the current subset.
            current.add(arr[i]);

            // only later elements may be selected, so elements are never reused.
            backtracking(current, output, arr, i + 1);

            // restore current before exploring the next sibling branch.
            current.removeLast();
        }
    }

    /**
     * Generates the power set using one bit per input element.
     *
     * <p>For a mask, bit {@code j} is set when {@code nums[j]} belongs to that subset. There
     * are {@code 2^n} masks for an input of length {@code n}.</p>
     *
     * @param nums input array of distinct integers
     * @return all subsets of {@code nums}; an empty array returns a list containing the
     * empty subset
     * @throws IllegalArgumentException if {@code nums} is too large for an {@code int} mask
     */
    public List<List<Integer>> subsetsBit(int[] nums) {
        // corner case
        if (nums == null) {
            return new ArrayList<>();
        }

        int n = nums.length;
        if (n >= Integer.SIZE - 1) {
            throw new IllegalArgumentException("Input is too large for an int bit mask");
        }

        List<List<Integer>> output = new ArrayList<>();
        int subsetCount = 1 << n;

        for (int mask = 0; mask < subsetCount; mask++) {
            List<Integer> subset = new ArrayList<>();

            for (int index = 0; index < n; index++) {
                if ((mask & (1 << index)) != 0) {
                    subset.add(nums[index]);
                }
            }
            output.add(subset);
        }
        return output;
    }
}
