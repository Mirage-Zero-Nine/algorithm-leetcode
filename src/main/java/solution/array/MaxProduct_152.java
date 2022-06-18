package solution.array;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * @author BorisMirage
 * Time: 2019/02/21 09:54
 * Created with IntelliJ IDEA
 */

public class MaxProduct_152 {
    /**
     * Find max subarray, and find max min subarray, in case next int is negative and min becomes max.
     *
     * @param nums given array
     * @return max subarray product
     */
    public int maxProduct(int[] nums) {

        /* Corner case */
        if (nums.length == 0) {
            return 0;
        }

        /*
         * Store the previous max and previous min.
         * Max value is product of both positive number or both negative number.
         * Min value is the product of one positive number and one negative number.
         * During the traverse, next value in array may turn max into min, and min into max. */
        int preMax = nums[0], preMin = nums[0], max = nums[0];
        int tempMax, tempMin;       // max value and min value

        for (int i = 1; i < nums.length; i++) {

            tempMax = Math.max(Math.max(preMax * nums[i], preMin * nums[i]), nums[i]);      // find current max value
            tempMin = Math.min(Math.min(preMax * nums[i], preMin * nums[i]), nums[i]);      // find min subarray

            max = Math.max(tempMax, max);       // previous min value may become max
            preMax = tempMax;
            preMin = tempMin;
        }

        return max;
    }
}
