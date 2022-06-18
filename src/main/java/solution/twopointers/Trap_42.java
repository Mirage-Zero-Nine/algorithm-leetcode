package solution.twopointers;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1.
 * Compute how much water it is able to trap after raining.
 *
 * @author BorisMirage
 * Time: 2018/06/27 14:18
 * Created with IntelliJ IDEA
 */

public class Trap_42 {
    /**
     * Two pointers solution. Starts at the beginning and the end of given array.
     * First, find the correct start position for two pointers: find first decreasing/increasing element.
     * From left to right, only decreasing subarray will be possible to trap water, and same as right to left.
     * Then, there must be some place trapping water, since for both left and right, a decreasing subarray is found.
     * Find the amount of trapping water by calculate the difference in decreasing subarray.
     * If current decreasing subarray ends, keep previous steps until two pointers meet.
     *
     * @param height int array of elevation map
     * @return trap space
     */
    public int trap(int[] height) {

        /* Corner case */
        if (height.length < 3) {
            return 0;
        }

        int max = 0, left = 0, right = height.length - 1;

        while (left < right && height[left] <= height[left + 1]) {       // find first non-increasing element
            left++;
        }
        while (left < right && height[right] <= height[right - 1]) {    // find first non-decreasing element
            right--;
        }

        if (left >= right) {        // if no valid "pit" can be found
            return 0;
        }

        while (left < right) {

            int leftHeight = height[left], rightHeight = height[right];

            /*
             * The previous stop condition assure that there must be water within left and right.
             * And also, height[left + 1] will surely be smaller than height[left], and so to right.
             * Therefore, the decreasing subarray next to left can trap water, and so to right.
             * Once the end of decreasing subarray is found, restart to find decreasing subarray. */
            if (leftHeight < rightHeight) {

                while (left < right && leftHeight > height[left + 1]) {
                    max += leftHeight - height[left++];
                }
                left++;
            } else {

                while (left < right && rightHeight > height[right - 1]) {
                    max += rightHeight - height[right--];
                }
                right--;
            }
        }

        return max;
    }

    /**
     * Use a stack to store the decreasing subarray index.
     * The idea of this solution is to calculate area level by level vertically.
     * The width of water trapped depends on distance from left to right, so stack save index rather than height.
     * If the decreasing subarray ends, find each area based on end of decreasing array.
     * Each decreasing element can trap width * height amount of water.
     * Width is the distance from bottom to stack pop out element (and that's the reason stack stores index).
     * Height is the difference from left bound to bottom.
     * Bottom will update at each level, to avoid duplicated calculation.
     *
     * @param height int array of elevation map
     * @return max trap space
     */
    public int stack(int[] height) {

        /* Corner case */
        if (height == null || height.length < 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int max = 0, left, bottom;

        for (int i = 0; i < height.length; i++) {

            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {      // find each "level" trapped water
                bottom = stack.pop();       // update bottom to calculate new "level"
                if (stack.isEmpty()) {
                    break;
                }
                left = stack.peek();
                max += (Math.min(height[i], height[left]) - height[bottom]) * (i - left - 1);
            }

            stack.push(i);      // push non-increasing or first element index (next height is unknown) into stack
        }

        return max;
    }

    public static void main(String[] args) {
        Trap_42 trapTest = new Trap_42();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapTest.trap(height));
    }
}
