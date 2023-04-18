package solution.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of DISTINCT integers, return all possible permutations.
 *
 * @author BorisMirage
 * Time: 2018/06/24 15:15
 * Created with IntelliJ IDEA
 */

public class Permute_46 {
    /**
     * Basic backtracking.
     * Iterate all possible combination by implementing backtracking array.
     * Keep an int array to record visited position to avoid duplication.
     *
     * @param nums input array
     * @return all possible permutations
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> output = new ArrayList<>();

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return output;
        }

        int[] visited = new int[nums.length];   // int array to record which element was used
        backtracking(output, new ArrayList<>(), visited, nums);

        return output;
    }

    /**
     * Backtracking to find all possible permutations.
     * The base case is when current temporary list is the same size of the given array.
     *
     * @param output  output list
     * @param tmp     temporary list
     * @param visited int array to record which element was used
     * @param nums    array to find all permutations
     */
    private void backtracking(List<List<Integer>> output, List<Integer> tmp, int[] visited, int[] nums) {

        if (tmp.size() == visited.length) {
            output.add(new ArrayList<>(tmp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    tmp.add(nums[i]);
                    backtracking(output, tmp, visited, nums);
                    tmp.remove(tmp.size() - 1);
                    visited[i] = 0;
                }
            }
        }
    }
}