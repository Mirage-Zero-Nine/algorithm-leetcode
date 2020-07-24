package Solution.Others;

/**
 * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
 * An array A is a zigzag array if either:
 * Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * Return the minimum number of moves to transform the given array nums into a zigzag array.
 *
 * @author BorisMirage
 * Time: 2019/08/03 19:35
 * Created with IntelliJ IDEA
 */

public class MovesToMakeZigzag_1144 {
    /**
     * Either make A[even] smaller or make A[odd] smaller.
     *
     * @param nums given array
     * @return minimum number of moves to transform the given array nums into a zigzag array
     */
    public int movesToMakeZigzag(int[] nums) {
        int odd = 0, even = 0;
        int n = nums.length;
        int left, right;
        for (int i = 0; i < n; ++i) {
            left = i > 0 ? nums[i - 1] : 1000;
            right = i + 1 < n ? nums[i + 1] : 1000;
            if (i % 2 == 0) {       // odd or even
                even += Math.max(0, nums[i] - Math.min(left, right) + 1);       // make even element smaller
            } else {
                odd += Math.max(0, nums[i] - Math.min(left, right) + 1);        // make odd element smaller
            }

        }
        return Math.min(odd, even);
    }
}
