package solution.array;

/**
 * You are given a integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
 * - 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * - 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * - 0, if none of the previous conditions holds.
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 *
 * @author BorisMirage
 * Time: 2021/10/09 19:41
 * Created with IntelliJ IDEA
 */

public class SumOfBeauties_2012 {
    /**
     * Compute running max from left and running min from right in order to check if nums[j] < nums[i] < nums[k].
     * If nums[i] < the min from the right, then it should be smaller than every value to the right.
     * If nums[i] > the max from left, then it's larger than all value from left.
     * In this case, this element scores 2.
     * Otherwise, if nums[i - 1] < nums[i] && nums[i] < nums[i + 1], then scores 1.
     *
     * @param nums given array
     * @return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
     */
    public int sumOfBeauties(int[] nums) {
        int sum = 0, n = nums.length;
        int[] minFromRight = new int[n + 1];
        int maxFromLeft = nums[0];
        minFromRight[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; --i) {
            minFromRight[i] = Math.min(nums[i], minFromRight[i + 1]);
        }
        for (int i = 1; i < nums.length - 1; ++i) {
            if (maxFromLeft < nums[i] && nums[i] < minFromRight[i + 1]) { // fits condition 2
                sum += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) { // only fits condition 1
                sum++;
            }
            maxFromLeft = Math.max(maxFromLeft, nums[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        SumOfBeauties_2012 sumOfBeauties_2012 = new SumOfBeauties_2012();
        System.out.println(sumOfBeauties_2012.sumOfBeauties(new int[]{2, 4, 6, 4}));
    }
}
