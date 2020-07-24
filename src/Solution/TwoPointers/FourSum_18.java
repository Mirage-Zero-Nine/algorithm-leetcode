package Solution.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array and an integer target, find if there exist four elements such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * The solution set must not contain duplicate quadruplets.
 *
 * @author BorisMirage
 * Time: 2018/06/11 19:47
 * Created with IntelliJ IDEA
 */

public class FourSum_18 {
    /**
     * Similar to 3 sum and 3 sum closest, the only difference is to modify the bound when searching array
     *
     * @param nums   input integer array
     * @param target target sum
     * @return linked list that contains all unique quadruplets
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (nums.length < 4) {
            return out;     // if array contains less that 4 elements, directly return empty list
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {     // avoid duplication
                for (int j = i + 1; j < nums.length - 2; j++) {

                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        int sum = target - nums[i] - nums[j];
                        int leftIndex = j + 1;
                        int rightIndex = nums.length - 1;

                        while (leftIndex < rightIndex) {

                            if (nums[leftIndex] + nums[rightIndex] < sum) {
                                leftIndex++;
                            } else if (nums[leftIndex] + nums[rightIndex] > sum) {
                                rightIndex--;
                            } else {

                                List<Integer> findList = new LinkedList<>();
                                findList.add(nums[i]);
                                findList.add(nums[j]);
                                findList.add(nums[leftIndex]);
                                findList.add(nums[rightIndex]);
                                out.add(findList);

                                leftIndex++;
                                rightIndex--;

                                while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex - 1]) {      // avoid duplicate
                                    leftIndex++;
                                }
                                while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex + 1]) {
                                    rightIndex--;
                                }
                            }
                        }
                    }
                }
            }
        }
        return out;
    }

    /**
     * Backtracking solution.
     *
     * @param nums   input integer array
     * @param target target sum
     * @return linked list that contains all unique quadruplets
     */
    public List<List<Integer>> fourSumBacktracking(int[] nums, int target) {
        List<List<Integer>> out = new ArrayList<>();

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return out;
        }

        Arrays.sort(nums);
        backtracking(out, new ArrayList<>(), nums, 4, target, 0);

        return out;
    }

    /**
     * Backtracking.
     * Each round, remove one element in array and target is subtracted by this element.
     * If all k elements have been selected, check if current target equals to 0.
     * One optimize: if target is smaller than current element, it can not be the answer.
     *
     * @param out      final result
     * @param tmp      temporary list
     * @param arr      given array
     * @param k        k elements required
     * @param target   target number
     * @param position start position
     */
    private void backtracking(List<List<Integer>> out, List<Integer> tmp, int[] arr, int k, int target, int position) {

        if (k <= 0) {
            if (k == 0 && target == 0) {
                out.add(new ArrayList<>(tmp));
            }
            return;
        }

        for (int i = position; i < arr.length && (arr[i] < 0 || arr[i] <= target); i++) {
            if (i == position || arr[i] != arr[i - 1]) {
                tmp.add(arr[i]);
                backtracking(out, tmp, arr, k - 1, target - arr[i], i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
