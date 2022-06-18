package solution.twopointers;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note:
 * 1. The container can not be slant.
 * 2. n is at least 2.
 *
 * @author BorisMirage
 * Time: 2019/06/18 21:01
 * Created with IntelliJ IDEA
 */

public class MaxArea_11 {
    /**
     * Two pointers.
     * The size of area is depend on smaller value in array.
     * Therefore, use two pointers, one at left, one at right.
     * Each time move the pointer that has smaller value to toward center and compare value.
     *
     * @param height given array
     * @return maximum area
     */
    public int maxArea(int[] height) {

        /* Corner case */
        if (height == null || height.length < 1) {
            return 0;
        }

        int max = 0, left = 0, right = height.length - 1;

        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));

            if (height[left] > height[right]) {     // area size is depend on smaller one
                right--;                            // if left larger than right, move right
            } else {
                left++;                             // otherwise, move left
            }
        }

        return max;
    }
}
