package Solution.DynamicProgramming;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct positive integers.
 * Find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 * Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 *
 * @author BorisMirage
 * Time: 2019/09/11 14:12
 * Created with IntelliJ IDEA
 */

public class LargestDivisibleSubset_368 {
    /**
     * Similar to LIS problem.
     * Each time, check if nums[i] % nums[j] == 0, where 0 <= j < i.
     * If nums[i] % nums[j] == 0, then check if subset ends in j is larger than current subset.
     * If it is larger than current subset, replaced with current subset.
     * To reconstruct the max subset, use a pointer array that stores the pointers point to previous element in subset.
     *
     * @param nums given int array
     * @return largest subset such that every pair (Si, Sj) in this subset satisfies Si % Sj = 0 or Sj % Si = 0
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {

        List<Integer> out = new LinkedList<>();

        /* Corner case */
        if (nums.length < 2) {
            if (nums.length == 1) {
                out.add(nums[0]);
            }
            return out;
        }

        int n = nums.length;
        Arrays.sort(nums);
        int[] previous = new int[n];        // pointer array save pointers point to previous subset elements in nums
        int[] count = new int[n];
        int max = 0, p = -1;

        for (int i = 0; i < n; i++) {
            count[i] = 1;       // the smallest pair is the element it self
            previous[i] = -1;      // link to previous element in current subset

            for (int j = i - 1; j >= 0; j--) {

                /*
                 * If nums[i] % nums[j] == 0, then pair under subset nums[j] will be a valid subset to nums[i].
                 * To find the max subset, check if previous subset has same or more elements. */
                if (nums[i] % nums[j] == 0 && count[j] + 1 > count[i]) {
                    count[i] = count[j] + 1;
                    previous[i] = j;        //
                }
            }

            if (count[i] > max) {
                max = count[i];
                p = i;      // if found larger subset, set pointer to current ending element of subset
            }
        }

        while (p != -1) {       // use pointer stored previously to reconstruct the subset
            out.add(nums[p]);
            p = previous[p];
        }
        Collections.reverse(out);

        return out;
    }
}
