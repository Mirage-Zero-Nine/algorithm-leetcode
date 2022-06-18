package solution.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than n/3 times.
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * @author BorisMirage
 * Time: 2019/07/01 10:38
 * Created with IntelliJ IDEA
 */

public class MajorityElement_229 {
    /**
     * The essential concepts is to keep a counter for the majority number X.
     * If find a number Y that is not X, the current counter should deduce 1.
     * The reason is that if there is 5 X and 4 Y, there would be one (5-4) more X than Y.
     * This could be explained as "4 X being paired out by 4 Y".
     *
     * @param nums given array
     * @return find all elements that appear more than n/3 times
     */
    public List<Integer> majorityElement(int[] nums) {

        List<Integer> res = new ArrayList<>();

        /* Corner case */
        if (nums.length == 0) {
            return res;
        }

        int num1 = nums[0], num2 = nums[0], count1 = 1, count2 = 0;

        for (int val : nums) {
            if (val == num1) {
                count1++;
            } else if (val == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = val;
                count1++;
            } else if (count2 == 0) {
                num2 = val;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int val : nums) {
            if (val == num1) {
                count1++;
            } else if (val == num2) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            res.add(num1);
        }
        if (count2 > nums.length / 3) {
            res.add(num2);
        }
        return res;
    }
}
