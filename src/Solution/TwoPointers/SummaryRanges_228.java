package Solution.TwoPointers;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * @author BorisMirage
 * Time: 2020/05/14 20:32
 * Created with IntelliJ IDEA
 */

public class SummaryRanges_228 {
    /**
     * Two pointers. The two pointers will point at the start and the end of each ranges.
     * Each time, the later pointer move forward to check if the next value is included in current range.
     * If there is no valid range, the the later pointer will not move forward.
     * Otherwise, a range will be found, add to final output list.
     *
     * @param nums given array
     * @return each start and the end of the range, or single integer if the element is discrete
     */
    public List<String> summaryRanges(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }

        List<String> out = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1) {
                i++;
            }

            out.add((start != nums[i]) ? (start + "->" + nums[i]) : (start + ""));
        }

        return out;
    }
}
