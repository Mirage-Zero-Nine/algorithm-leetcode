package solution.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a array of distinct integers.
 * Return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * @author BorisMirage
 * Time: 2018/08/09 13:44
 * Created with IntelliJ IDEA
 */

public class Subsets_78 {
    /**
     * Bit manipulation.
     * Index in array can be represented as a bit map, select ith element can be represented as 1, otherwise 0.
     * Therefore, the whole array can be represented as a 2^n - 1 int, that each bit represent the element in array.
     * Then loop from 0 to 1 << n, each 1 represent the jth element is selected into current subset.
     *
     * @param nums input int array
     * @return list that contains all subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> out = new LinkedList<>();
        int n = nums.length;

        for (int i = 0; i < 1 << n; i++) {
            List<Integer> tmp = new LinkedList<>();

            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    tmp.add(nums[j]);
                }
            }

            out.add(tmp);
        }

        return out;
    }

    /**
     * Normal backtracking.
     *
     * @param nums input int array
     * @return list that contains all subsets
     */
    public List<List<Integer>> backtracking(int[] nums) {
        List<List<Integer>> output = new LinkedList<>();

        if (nums.length == 0) {
            return output;
        }

        Arrays.sort(nums);      // avoid duplication
        backtracking(output, new LinkedList<>(), nums, 0);
        return output;
    }

    /**
     * Basic backtracking.
     * Each iteration starts at next int from previous iteration.
     *
     * @param output output list
     * @param temp   temp list
     * @param nums   int array
     * @param k      each recursion start position
     */
    private void backtracking(List<List<Integer>> output, List<Integer> temp, int[] nums, int k) {
        output.add(new ArrayList<>(temp));     // every subset should be added to final result
        for (int i = k; i < nums.length; i++) {
            temp.add(nums[i]);
            backtracking(output, temp, nums, i + 1);
            temp.remove(temp.size() - 1);       // avoid duplication

        }
    }

    public static void main(String[] args) {
        Subsets_78 test = new Subsets_78();
        System.out.println(test.subsets(new int[]{1, 2, 3, 4, 5}));
    }
}
