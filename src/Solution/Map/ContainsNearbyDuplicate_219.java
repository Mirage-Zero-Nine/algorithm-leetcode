package Solution.Map;

import java.util.HashSet;

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
     * Sliding window with a hash set.
     * Use a hash set to store the elements during the traverse.
     * Also keep a window of size k. Remove elements from hash set after window passed.
     * Since nums[i] and nums[j] are required within a distance of k, instead of exactly k, hash set would suffice.
     * Because if there is a duplicated element within k distance, it would be found right away.
     *
     * @param nums given array
     * @param k    max absolute difference between i and j
     * @return whether there are two distinct indices i and j in array and nums[i] = nums[j]
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        /* Corner case */
        if (k < 1 || nums.length < 1) {
            return false;
        }

        HashSet<Integer> s = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (s.contains(nums[i])) {
                return true;
            }
            s.add(nums[i]);
            if (i >= k) {
                s.remove(nums[i - k]);
            }
        }

        return false;
    }
}
