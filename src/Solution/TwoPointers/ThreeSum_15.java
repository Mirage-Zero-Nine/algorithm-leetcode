package Solution.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * The solution set must not contain duplicate triplets.
 *
 * @author BorisMirage
 * Time: 2019/06/19 11:07
 * Created with IntelliJ IDEA
 */

public class ThreeSum_15 {
    /**
     * Sort the array first, then start from the smallest (or largest) element.
     * When one element is selected, start from the next element and the last element in this array.
     * Compare the sum of these two elements, if it is smaller than -nums[i], then move the left element to its right.
     * If the sum is larger than 0, then move the right element to its left.
     * Move two pointers toward center.
     *
     * @param nums given array
     * @return all unique triplets in the array which gives the sum of zero
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> out = new ArrayList<>();

        /* Corner case */
        if (nums.length < 3) {
            return out;
        }
        Arrays.sort(nums);      // sort array
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return out;     // if all elements are positive, then it is not possible to have a three sum equals 0
        }

        for (int i = 0; nums[i] < 1 && i < nums.length - 2; i++) {      // if current value > 0, then no possible triplet

            if (i == 0 || nums[i] != nums[i - 1]) {     // avoid duplicate
                int p1 = i + 1, p2 = nums.length - 1;

                while (p1 < p2) {      // move two points toward center

                    if (nums[p1] + nums[p2] < -nums[i]) {
                        p1++;     // make sum larger
                    } else if (nums[p1] + nums[p2] > -nums[i]) {
                        p2--;        // make sum smaller
                    } else {

                        List<Integer> temp = new ArrayList<>();     // find one triplet
                        temp.add(nums[i]);
                        temp.add(nums[p1]);
                        temp.add(nums[p2]);
                        out.add(temp);
                        p1++;
                        p2--;

                        while (p1 < p2 && nums[p1] == nums[p1 - 1]) {       // avoid duplication
                            p1++;
                        }
                        while (p1 < p2 && nums[p2] == nums[p2 + 1]) {
                            p2--;
                        }
                    }
                }
            }
        }

        return out;
    }

    /**
     * A more generalize approach using backtracking. However, it will cause TLE.
     *
     * @param nums given array
     * @return all unique triplets in the array which gives the sum of zero
     */
    public List<List<Integer>> threeSumBacktracking(int[] nums) {

        List<List<Integer>> out = new ArrayList<>();

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return out;
        }

        Arrays.sort(nums);
        backtracking(out, new ArrayList<>(), nums, 3, 0, 0);

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

        for (int i = position; i < arr.length; i++) {
            if (arr[i] > 0 && target <= 0) {
                return;
            }
            if (i == position || arr[i] != arr[i - 1]) {
                tmp.add(arr[i]);
                backtracking(out, tmp, arr, k - 1, target - arr[i], i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {

        /* Three sum test*/
        int[] test = {0, -4, -1, -4, -2, -3, 2};
        ThreeSum_15 threeSumTest = new ThreeSum_15();
        System.out.println(threeSumTest.threeSum(test));
    }
}
