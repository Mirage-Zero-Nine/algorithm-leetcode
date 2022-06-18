package solution.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 *
 * @author BorisMirage
 * Time: 2020/10/03 09:33
 * Created with IntelliJ IDEA
 */

public class FindDuplicates_442 {
    /**
     * Traverse the array, mark the nums[nums[i]] to negative at each element.
     * If the nums[nums[i]] is negative, then it has been visited, add to output list.
     *
     * @param nums given array
     * @return all the elements that appear twice in this array
     */
    public List<Integer> findDuplicates(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                out.add(Math.abs(nums[i]));
            }
            nums[index] *= -1;
        }

        return out;
    }
}
