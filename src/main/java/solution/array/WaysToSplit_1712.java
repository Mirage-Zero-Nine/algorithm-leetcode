package solution.array;

/**
 * A split of an integer array is good if:
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums.
 * As the number may be too large, return it modulo 109 + 7.
 *
 * @author BorisMirage
 * Time: 2021/10/26 21:18
 * Created with IntelliJ IDEA
 */

public class WaysToSplit_1712 {
    /**
     * Prefix sum and two pointers.
     * First, calculate the prefix sum for the array.
     * Then keep two pointers. For each point of p0, find the minimum (p1) and maximum (p2) boundaries of:
     * 1. prefixSum[i] <= prefixSum[j] - prefixSum[i] -> 2 * prefixSum[p0] > prefixSum[p1]
     * 2. prefixSum[p2] - prefixSum[p0] <= prefixSum[length - 1] - prefixSum[p2]
     * These conditions derive from the property of good array.
     * For each point at p0, the range between p1 and p2 are the number of ways to split array.
     *
     * @param nums given array
     * @return the number of good ways to split nums
     */
    public int waysToSplit(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length < 3) {
            return 0;
        }

        int length = nums.length, out = 0, MOD = 1000000007;
        int[] prefixSum = new int[length];
        prefixSum[0] = nums[0];

        for (int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        int p1 = 0, p2 = 0;

        for (int p0 = 0; p0 < length - 2; p0++) {
            while (p1 <= p0 || (p1 < length - 1 && 2 * prefixSum[p0] > prefixSum[p1])) { // p1 should be larger than p0
                p1++;
            }
            while (p2 < p1 || (p2 < length - 1 && prefixSum[p2] - prefixSum[p0] <= prefixSum[length - 1] - prefixSum[p2])) {
                p2++;
            }

            out = (out + p2 - p1) % MOD;
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new WaysToSplit_1712().waysToSplit(new int[]{1, 1, 1}));  // 1
        System.out.println(new WaysToSplit_1712().waysToSplit(new int[]{1, 2, 2, 2, 5, 0})); // 3
        System.out.println(new WaysToSplit_1712().waysToSplit(new int[]{3, 2, 1})); // 0
    }
}
