package solution.array;

import java.util.Arrays;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Return 0 if the array contains less than 2 elements.
 *
 * @author BorisMirage
 * Time: 2019/08/27 13:28
 * Created with IntelliJ IDEA
 */

public class MaximumGap_164 {
    /**
     * Bucket sort.
     * Put n - 2 numbers (remove max and min in array) into n - 1 buckets.
     * The bucket size is (int) Math.ceil((double) (max - min) / (nums.length - 1)), which will be larger than max gap.
     * In this way, each bucket will be at most 2 elements.
     * Max gap can only be found from previous bucket max to current bucket min.
     * If meet empty bucket, pass it until find next non-empty bucket.
     * Initially, the previous bucket max is the min value in array.
     * Finally, the next bucket min is the max value in array.
     *
     * @param nums given array
     * @return the maximum difference between the successive elements in its sorted form
     */
    public int maximumGap(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return 0;
        }
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        int bucketLength = (int) Math.ceil((double) (max - min) / (nums.length - 1));     // ceiling of the integer division
        int[] bucketMin = new int[nums.length - 1], bucketMax = new int[nums.length - 1];
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        Arrays.fill(bucketMin, Integer.MAX_VALUE);

        for (int i : nums) {
            if (i != min && i != max) {
                int bucketIndex = (i - min) / bucketLength;       // get bucket index

                bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], i);
                bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], i);
            }
        }
        int pre = min;
        int out = Integer.MIN_VALUE;

        for (int i = 0; i < bucketMax.length; i++) {
            if (bucketMin[i] != Integer.MAX_VALUE) {
                out = Math.max(out, bucketMin[i] - pre);
                pre = bucketMax[i];
            }
        }

        out = Math.max(out, max - pre);

        return out;
    }
}
