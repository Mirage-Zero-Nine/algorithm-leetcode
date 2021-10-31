package Solution.Map;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of non-negative integers nums.
 * The degree of this array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 * @author BorisMirage
 * Time: 2021/10/31 11:23
 * Created with IntelliJ IDEA
 */

public class FindShortestSubArray_697 {
    /**
     * Two hash maps. One stores the frequency of each element in array.
     * The other one stores the first appearance of each element in array.
     * If a new most frequent element was found, update the degree and the min distance.
     * If an element with same largest frequency in array was found, update min distance with the one has smaller length.
     *
     * @param nums given array
     * @return the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        Map<Integer, Integer> index = new HashMap<>();

        int degree = 1, min = nums.length;
        for (int i = 0; i < nums.length; i++) {
            frequency.put(nums[i], frequency.getOrDefault(nums[i], 0) + 1);
            if (!index.containsKey(nums[i])) {
                index.put(nums[i], i); // put first appeared position
            }
            if (frequency.get(nums[i]) > degree) { // if new most frequent element was found
                degree = frequency.get(nums[i]);   // update the degree of array
                min = i - index.get(nums[i]) + 1;
            } else if (frequency.get(nums[i]) == degree) {
                min = Math.min(i - index.get(nums[i]) + 1, min);
            }
        }

        return min;
    }
}
