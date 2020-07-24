package Solution.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a array of integers that might contain duplicates int.
 * Return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * @author BorisMirage
 * Time: 2018/08/09 13:57
 * Created with IntelliJ IDEA
 */

public class SubsetsWithDup_90 {
    /**
     * Iterative solution that simulates backtracking.
     *
     * @param nums given array
     * @return list that contains all subsets
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> out = new LinkedList<>();

        if (nums == null || nums.length < 2) {
            return out;
        }

        Arrays.sort(nums);
        out.add(new LinkedList<>());

        int n = nums.length, start, size = 0;

        for (int i = 0; i < n; i++) {
            start = (i == 0 || nums[i] != nums[i - 1]) ? 0 : size;      // avoid duplicated elements
            size = out.size();

            for (int j = start; j < size; j++) {
                List<Integer> tmp = new LinkedList<>(out.get(j));
                tmp.add(nums[i]);
                out.add(tmp);
            }
        }

        return out;
    }

    /**
     * Backtracking.
     *
     * @param nums input int array
     * @return list that contains all subsets
     */
    public List<List<Integer>> backtracking(int[] nums) {
        List<List<Integer>> out = new LinkedList<>();
        Arrays.sort(nums);
        backtracking(out, new ArrayList<>(), nums, 0);
        return out;
    }

    /**
     * Basic backtracking.
     * Pass all duplicated elements except first appeared one.
     *
     * @param out   output list
     * @param temp  temp list
     * @param nums  int array
     * @param start each recursion start position
     */
    private void backtracking(List<List<Integer>> out, List<Integer> temp, int[] nums, int start) {
        out.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {

            if (i == start || nums[i] != nums[i - 1]) {     // avoid duplicate subsets
                temp.add(nums[i]);
                backtracking(out, temp, nums, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new SubsetsWithDup_90().backtracking(new int[]{1, 2, 2, 2, 2}));
    }
}
