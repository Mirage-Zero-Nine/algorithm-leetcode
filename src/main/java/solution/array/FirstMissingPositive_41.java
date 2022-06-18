package solution.array;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Note:
 * Algorithm should run in O(n) time and uses constant extra space.
 *
 * @author BorisMirage
 * Time: 2018/06/23 20:35
 * Created with IntelliJ IDEA
 */

public class FirstMissingPositive_41 {
    /**
     * Traverse array three times.
     * Smallest missing positive integer can only be in [1, n + 1] (all-inclusive).
     * First, replace all non-positive numbers to array's length + 1, so that in second traverse these numbers can be excluded.
     * In second traversal, find nums[i] such that its absolute value is smaller than array's length.
     * Mark its representative index's value to negative.
     * i.e., abs(nums[i]) = 5, then nums[4] = - nums[4]
     * The reason to use absolute value is to save while loop compare to swapping method.
     *
     * @param nums input int array
     * @return smallest missing positive int
     */
    public int firstMissingPositive(int[] nums) {

        /* Corner case */
        if (nums.length == 0) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = (nums[i] < 1) ? nums.length + 1 : nums[i];      // replace all negative value to n + 1
        }

        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) <= nums.length) {
                nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);       // mark index as "presented"
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    public static void main(String[] args) {

        FirstMissingPositive_41 firstMissingPositiveTest = new FirstMissingPositive_41();
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{2, 3, 6, 7}));       // 1
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{3, 4, -1, 1}));       // 2
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));       // 1
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{1, 2, 0}));       // 3
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{2, 5, 2, 1, 2, 3}));       // 4
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{3, 4, 0, 1}));       // 2
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{1, 2, 3, 4, 5, 6, 7}));       // 8
        System.out.println(firstMissingPositiveTest.firstMissingPositive(new int[]{7, 10, 12, 11, 9, 6, 5, 1, 2, 3}));       // 4
    }
}
