package Solution.Map;

import java.util.TreeSet;

/**
 * Given an array of integers and an integer k.
 * Find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j].
 * And the absolute difference between i and j is at most k.
 *
 * @author BorisMirage
 * Time: 2019/06/13 15:35
 * Created with IntelliJ IDEA
 */

public class ContainsNearbyDuplicate_219 {
    /**
     * Use TreeSet to save and sort value in array.
     * If found same one in TreeSet, then return true.
     * The reason of using a TreeSet is that it can directly return the closest number to given number.
     *
     * @param nums given array
     * @param k    max absolute difference between i and j
     * @return whether there are two distinct indices i and j in array
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        if (k < 1 || nums.length < 1) {
            return false;
        }

        TreeSet<Integer> m = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            Integer floor = m.floor(nums[i]);
            if (floor != null && floor == nums[i]) {
                return true;
            }
            m.add(nums[i]);
            if (i >= k) {
                m.remove(nums[i - k]);
            }
        }
        return false;
    }
}
