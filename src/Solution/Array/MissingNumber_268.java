package Solution.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * Only one int is missing in int series.
 * If array contains elements from 0 to n-1, then return n.
 * Solution 1: Use HashSet.
 * Solution 2: Use bit manipulate.
 *
 * @author BorisMirage
 * Time: 2018/07/06 13:03
 * Created with IntelliJ IDEA
 */

public class MissingNumber_268 {
    /**
     * Use index mapping to mapping each occurred element from 0 to n by mark each element into negative.
     * To avoid 0, the mapping should minus 1 to assure each element in array after mapping is negative.
     * Then check which element in array is not mapped.
     *
     * @param nums input int array
     * @return missing number
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0 && (Math.abs(nums[i]) - 1) < n) { // avoid out of bound exception
                int index = Math.abs(nums[i]) - 1; // index of element to be marked to negative
                nums[index] = -nums[index] - 1;
            } else if (nums[i] >= 0 && nums[i] < n) {
                int index = nums[i]; // index of element to be marked to negative
                nums[index] = -nums[index] - 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i;
            }
        }

        return nums.length;
    }

    /**
     * Bit manipulation.
     * Only one element is missing in 0, 1, 2, ..., n. And XOR operation has a principle a ^ b ^ b = a.
     * Hence, if array has all elements from 0 to n-1, then the XOR operation will finally back to initial value itself.
     * i.e., output ^ 1 ^ 1 ^ 2 ^ 2 ... ^ (n - 1) ^ (n - 1) = output.
     * If the missing int is in range [0, n], then output = output ^ missingIndex ^ n.
     * Since output initially is equal to n, finally output will be missing index, which is missing number.
     *
     * @param nums input int array
     * @return missing number
     */
    public int missingNumberBitManipulation(int[] nums) {

        int output = nums.length;
        for (int i = 0; i < nums.length; i++) {
            output = output ^ i ^ nums[i];
        }
        return output;
    }

    /**
     * Put elements into set and traverse from 0 to n to find missing number.
     *
     * @param nums input int array
     * @return missing number
     */
    public int naiveSolution(int[] nums) {

        /* Corner case */
        if (nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * Use the binary search to find the missing one.
     *
     * @param nums input int array
     * @return missing number
     */
    public int binarySearch(int[] nums) {
        Arrays.sort(nums);

        int left = 0, right = nums.length, mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (nums[mid] > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(new MissingNumber_268().missingNumber(new int[]{3, 0, 1})); // 2
        System.out.println(new MissingNumber_268().missingNumber(new int[]{1, 2, 0})); // 3
        System.out.println(new MissingNumber_268().missingNumber(new int[]{1, 2})); // 0
        System.out.println(new MissingNumber_268().missingNumber(new int[]{2, 0})); // 1
        System.out.println(new MissingNumber_268().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1})); // 8
    }
}
