package solution.array;

/**
 * You are given a 0-indexed array of distinct integers nums.
 * There is an element in nums that has the lowest value and an element that has the highest value.
 * We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.
 * A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.
 * Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.
 *
 * @author BorisMirage
 * Time: 2021/11/27 23:29
 * Created with IntelliJ IDEA
 */

public class MinimumDeletions_2091 {
    /**
     * Find the min and max values' index in the array.
     * The result only related to the number of elements removed from array, so make sure the minIndex < maxIndex.
     * (minIndex > maxIndex works as well, just need to make sure there is an order).
     * Then there are 3 cases:
     * 1. Remove from both left and right of the array.
     * 2. Remove only from the left side of the array.
     * 3. Remove only from the right side of the array.
     * Return the minimum value among the 3 cases.
     *
     * @param nums given array
     * @return the minimum number of deletions to remove both the minimum and maximum element from the array
     */
    public int minimumDeletions(int[] nums) {

        /* Corner case */
        if (nums.length == 1) {
            return 1;
        }

        int minIndex = 0, maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }

        if (minIndex > maxIndex) { // the exact index is irrelevant, so make sure minIndex < maxIndex
            int tmp = minIndex;
            minIndex = maxIndex;
            maxIndex = tmp;
        }

        return Math.min(Math.min(minIndex + 1 + nums.length - maxIndex, maxIndex + 1), nums.length - minIndex);
    }
}
