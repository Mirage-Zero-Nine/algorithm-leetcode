package Solution.Backtracking;

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
     * This boolean array is to record if current element is used (for uniqueness).
     *
     * @param nums input number collection
     * @return all possible unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);
        backtracking(nums, new ArrayList<>(), res, new boolean[nums.length]);
        return res;

    }

    private void backtracking(int[] nums, List<Integer> cache, List<List<Integer>> res, boolean[] use) {

        /* Exit point */
        if (cache.size() == nums.length) {
            res.add(new ArrayList<>(cache));
        } else {
            for (int i = 0; i < nums.length; i++) {

                if (use[i] || i > 0 && nums[i] == nums[i - 1] && !use[i - 1]) {     // avoid reuse element in array
                    continue;
                }

                /* Add element to list and mark current element as used */
                cache.add(nums[i]);
                use[i] = true;
                backtracking(nums, cache, res, use);
                use[i] = false;
                cache.remove(cache.size() - 1);
            }
        }
    }
}
