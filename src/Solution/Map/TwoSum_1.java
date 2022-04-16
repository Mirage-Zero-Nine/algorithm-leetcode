package Solution.Map;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * Each input would have exactly one solution, and you may not use the same element twice.
 *
 * @author BorisMirage
 * Time: 2019/06/18 10:10
 * Created with IntelliJ IDEA
 */

public class TwoSum_1 {
    /**
     * Use a hash map to store the element in array.
     * The key of the hash map is elements in the array, the value under each key is the element’s index.
     * Traverse the array, if found an element that is equals to target - nums[i], then return the i and mapped index.
     * Otherwise, put the nums[i] and i into hash map.
     *
     * @param nums   given array
     * @param target target number
     * @return indices of the two numbers such that they add up to a specific target
     */
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(target - nums[i])) {
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
        }

        return null;
    }

    public static void main(String[] args) {
        TwoSum_1 test = new TwoSum_1();
        int[] testArray = {3, 5, 6, 8, 7};
        int num = 8;
        System.out.println(Arrays.toString(test.twoSum(testArray, num)));
    }
}
