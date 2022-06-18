package solution.dynamicprogramming;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * @author BorisMirage
 * Time: 2019/06/23 14:42
 * Created with IntelliJ IDEA
 */

public class LengthOfLIS_300 {
    /**
     * Dynamic programming with 1D array.
     * The 1D array stores the previous LIS by its length and the minimum value of LIS.
     * Traverse the array, and use binary search to find correct position that tails[i - 1] < x <= tails[i].
     * If it is larger than all tails, append it, increase size.
     *
     * @param nums given array
     * @return length of longest increasing sub sequence
     */
    public int lengthOfLIS(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return nums.length;
        }

        /*
         * Index is the length of LIS in array.
         * Values under corresponding index is the minimum value of last element in each length of LIS.
         * Size is the length in tails[] filled with numbers (length of LIS found). */
        int[] tails = new int[nums.length + 1];
        int size = 0;
        tails[size++] = nums[0];

        for (int num : nums) {
            if (num > tails[size - 1]) {        // new LIS is found
                tails[size++] = num;
            } else {
                tails[find(tails, size, num)] = num;
            }
        }

        return size;
    }

    /**
     * Binary search to find correct LIS tail that it should be inserted to.
     * Each element should be inserted to position where tails[i - 1] < x <= tails[i].
     * If current element is the largest one, then the size will increase 1, as new LIS is found.
     *
     * @param tails array contains smallest value under each length of increasing subsequence
     * @param size  size of longest LIS
     * @param num   current number to be inserted
     * @return insert position of current element
     */
    private int find(int[] tails, int size, int num) {
        int left = 0, right = size;

        while (left <= right) {

            int mid = left + (right - left) / 2;
            if (tails[mid] == num) {
                return mid;
            }

            if (tails[mid] < num) {     // tails[mid] < x, move to right sub array
                left = mid + 1;
            } else {                    // otherwise, move to left sub array
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLIS_300().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(new LengthOfLIS_300().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 4}));
    }
}
