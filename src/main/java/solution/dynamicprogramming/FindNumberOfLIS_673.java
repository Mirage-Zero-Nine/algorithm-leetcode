package solution.dynamicprogramming;

/**
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 *
 * @author BorisMirage
 * Time: 2019/07/13 17:54
 * Created with IntelliJ IDEA
 */

public class FindNumberOfLIS_673 {
    /**
     * Dynamic programming.
     * One array save the length of the LIS ends with nums[i], one array count number of the LIS ends with nums[i].
     * If nums[i] > nums[j] (j < i):
     * 1. length[i] < length[j] + 1: count[i] = count[j], length[i] = length[j] + 1;
     * 2. length[i] == length[j] + 1: count[i] += count[j]；
     * 3. length[i] > length[j]: stay same (included in other LIS)
     *
     * @param nums given array
     * @return number of longest increasing subsequence
     */
    public int findNumberOfLIS(int[] nums) {

        /* Corner case */
        if (nums.length == 0) {
            return 0;
        }

        int[] length = new int[nums.length];        // length of the LIS ends with nums[i]
        int[] count = new int[nums.length];         // count number of the LIS ends with nums[i]
        int maxLength = 0, maxNumber = 0;

        for (int i = 0; i < nums.length; i++) {
            length[i] = 1;
            count[i] = 1;

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {     // find a increasing subsequence

                    /*
                     * If length of LIS to nums[j] + 1 == length of LIS to nums[i], then nums[i] can be added to LIS to j.
                     * And # of LIS in count[j] can be added to count[i].
                     * Otherwise, if length[j] + 1 > length[i], then a new length of LIS is found.
                     * Then, update length[i] to length[j] + 1.
                     * count[i] will be replaced with count[j]. */
                    if (length[j] + 1 == length[i]) {
                        count[i] += count[j];
                    } else if (length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1;
                        count[i] = count[j];        // now there is a new LIS and replace counter with previous one
                    }
                }
            }

            if (maxLength == length[i]) {       // if no new longest LIS
                maxNumber += count[i];
            } else if (maxLength < length[i]) {     // if there is a new longest LIS
                maxLength = length[i];      // update max length
                maxNumber = count[i];       // new max number of LIS
            }
        }

        return maxNumber;
    }
}
