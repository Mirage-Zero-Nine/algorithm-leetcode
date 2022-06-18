package solution.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible UNIQUE permutations.
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 *
 * @author BorisMirage
 * Time: 2018/06/24 16:36
 * Created with IntelliJ IDEA
 */

public class PermuteUnique_47 {
    /**
     * Backtracking.
     * The difference between permute problem is that one boolean array is added during backtracking process.
     * To avoid duplicated selection, keep a boolean array to store visited
     * This boolean array is to record if current element is used (for uniqueness).
     *
     * @param nums input number collection
     * @return all possible unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return output;
        }

        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        backtracking(output, new ArrayList<>(), nums, visited);

        return output;

    }

    /**
     * Backtracking to find all permutations.
     * Iterate all the available non-duplicate elements in the array to fill current position in permutations.
     * Keep a boolean array to store the status of current iteration that whether an element is selected or not.
     *
     * @param output  output list
     * @param temp    list to store each single permutation
     * @param nums    array to find all permutations
     * @param visited boolean array to store the status if current element is visited
     */
    private void backtracking(List<List<Integer>> output, List<Integer> temp, int[] nums, boolean[] visited) {

        if (temp.size() == nums.length) {
            output.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            /*
             * Conditions of selection:
             * 1. Current element is not visited.
             * 2. Under 1, if:
             *  a. index is 0, or:
             *  b. a non-duplicated number, or at the start of new duplicated number sequence */
            if (!visited[i] && ((i == 0) || (nums[i] != nums[i - 1] || visited[i - 1]))) {
                temp.add(nums[i]);
                visited[i] = true;
                backtracking(output, temp, nums, visited);
                temp.remove(temp.size() - 1);
                visited[i] = false;
            }
        }
    }
}
