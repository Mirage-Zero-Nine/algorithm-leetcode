package Solution.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (nums.length == 0) {
            return out;
        }

        Arrays.sort(nums);
        backtracking(nums, new ArrayList<>(), out);

        return out;
    }

    /**
     * Backtracking to find all possible permutations.
     * The end point is when current temporary list reaching the size of given array.
     *
     * @param nums given array
     * @param tmp  temporary list
     * @param res  output list
     */
    private void backtracking(int[] nums, List<Integer> tmp, List<List<Integer>> res) {

        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
        } else {

            for (int num : nums) {      // backtracking each element
                if (!tmp.contains(num)) {
                    tmp.add(num);
                    backtracking(nums, tmp, res);
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
}