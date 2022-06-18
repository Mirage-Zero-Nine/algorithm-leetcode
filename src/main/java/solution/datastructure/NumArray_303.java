package solution.datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * @author BorisMirage
 * Time: 2020/04/04 11:09
 * Created with IntelliJ IDEA
 */

public class NumArray_303 {
    private final Map<Integer, Integer> m;

    /**
     * Prefix sum.
     *
     * @param nums given array
     */
    public NumArray_303(int[] nums) {
        m = new HashMap<>();
        int end = nums.length, prefix = 0;
        m.put(-1, 0);
        for (int i = 0; i < end; i++) {
            prefix += nums[i];
            m.put(i, prefix);
        }
    }

    /**
     * Find the sum of the elements between indices i and j (i ≤ j), inclusive.
     *
     * @param i start index
     * @param j end index
     * @return sum of the elements between indices i and j (i ≤ j), inclusive
     */
    public int sumRange(int i, int j) {

        return m.get(j) - m.get(i - 1);
    }
}
