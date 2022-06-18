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
     * Use backtracking to traverse each possibility.
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

        backtracking(output, new ArrayList<>(), nums);

        return output;
    }

    /**
     * Backtracking to find all possible permutations.
     * The end point is when current temporary list reaching the size of given array.
     *
     * @param output output list
     * @param temp   temporary list
     * @param nums   array to find all permutations
     */
    private void backtracking(List<List<Integer>> output, List<Integer> temp, int[] nums) {

        if (temp.size() == nums.length) {       // end point
            output.add(new ArrayList<>(temp));
            return;
        }

        for (int n : nums) {
            if (!temp.contains(n)) {
                temp.add(n);
                backtracking(output, temp, nums);
                temp.remove(temp.size() - 1);
            }
        }
    }
}