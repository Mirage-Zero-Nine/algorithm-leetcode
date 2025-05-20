package solution.array;

/**
 * You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
 * For each queries[i]:
 * - Select a subset of indices within the range [li, ri] in nums.
 * - Decrement the values at the selected indices by 1.
 * A Zero Array is an array where all elements are equal to 0.
 * Return true if it is possible to transform nums into a Zero Array after processing all the queries sequentially, otherwise return false.
 *
 * @author BorisMirage
 * Time: 2025/05/20 15:46
 * Created with IntelliJ IDEA
 */

public class IsZeroArray_3355 {
    /**
     * Construct a difference array which stores the difference in value between nums[i] and nums[i-1].
     * For each query, update the diffArr[q[0]] with minus 1, diffArr[q[1]+1] with add one, which marks the boundary of range being updated.
     * Finally, calculate the prefix sum for the diffArr which represents the value being subtracted for each element in given array.
     *
     * @param nums    The integer array to be checked.
     * @param queries A 2D integer array representing the queries to apply. Each query is of the form [start, end].
     * @return True if the array `nums` can be made all zeros after applying the queries, false otherwise.
     * Returns false if at any point after applying updates, any element in `nums` becomes greater than 0.
     */
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int[] diffArr = new int[nums.length + 1];
        for (int[] q : queries) {
            diffArr[q[0]]--; // left boundary: every element will minus one
            diffArr[q[1] + 1]++;  // right boundary: elements after that will stay the same. add 1 to correctly calculate the prefix sum
        }

        for (int i = 1; i < diffArr.length; i++) {
            // calculating the prefix sum
            // this is the difference that each element will need to be applied
            diffArr[i] = diffArr[i - 1] + diffArr[i];
            nums[i - 1] += diffArr[i - 1];

            // after applying the difference, if the value is larger than 0, this means it's not able to transfer to 0
            if (nums[i - 1] > 0) {
                return false;
            }
        }

        return true;
    }
}
