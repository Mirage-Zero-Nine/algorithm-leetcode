package Solution.Array;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * @author BorisMirage
 * Time: 2019/10/14 10:41
 * Created with IntelliJ IDEA
 */

public class FindDisappearedNumbers_448 {
    /**
     * Traverse the array, use the array element as index and mark the corresponding value to negative.
     *
     * @param nums given array
     * @return all the elements of [1, n] inclusive that do not appear in this array
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> out = new LinkedList<>();
        if (nums == null) {
            return out;
        }
        int n = nums.length;

        if (n == 0 || n == 1) {
            return out;
        }

        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                out.add(i + 1);
            }
        }

        return out;
    }
}
