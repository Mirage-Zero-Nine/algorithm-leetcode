package solutions.twopointers;

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
     * Returns the largest area of water that can be enclosed by two lines.
     *
     * <p>When the pointers are at {@code left} and {@code right}, the current candidate is
     * {@code min(height[left], height[right]) * (right - left)}.  Advancing the shorter pointer
     * preserves every possibility that could improve the area: advancing the taller pointer would
     * retain or lower the limiting height while shrinking the width.  The pointers meet after
     * considering all potentially optimal pairs.</p>
     *
     * @param height non-negative line heights, normally containing at least two elements
     * @return the maximum enclosed area, or {@code 0} for {@code null} or fewer than two heights
     * @implNote Runs in {@code O(n)} time and uses {@code O(1)} auxiliary space.  The input array
     * is read only and is not modified.
     */
    public int maxArea(int[] height) {
        // corner case
        if (height == null || height.length < 2) {
            return 0;
        }

        int max = 0, left = 0, right = height.length - 1;
        while (left < right) {
            max = Math.max(Math.min(height[left], height[right]) * (right - left), max);

            // The shorter line limits this pair.  Moving the taller line only loses width while
            // leaving the limiting height no higher, so an optimum cannot require that move.
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
