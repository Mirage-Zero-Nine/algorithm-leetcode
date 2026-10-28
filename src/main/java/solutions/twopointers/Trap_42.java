package solutions.twopointers;

import java.util.ArrayDeque;
import java.util.Deque;

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
     * Counts water in horizontal layers using a stack of bar indices whose heights are
     * non-increasing from bottom to top. A taller incoming bar closes a depression: pop
     * its bottom, then use the new stack top as the left wall and the incoming bar as
     * the right wall. Their shorter height limits the water level. The layer's height
     * is that level minus the popped height; its width is the distance between the walls,
     * excluding the walls themselves.
     *
     * <p>Earlier pops have already accounted for any deeper pockets within that width.
     * Updating the bottom after each pop therefore counts only a new layer, never the
     * same water twice. For example, {@code [4, 0, 2, 0, 4]} first fills two pockets to
     * height 2 (2 + 2 units), then adds a layer of height 2 across all three interior
     * positions (6 units). The input is not modified, and no state is retained between calls.
     *
     * <p>Time complexity: O(n), where n is the number of bars; each index is pushed once
     * and popped at most once, including the amortized cost of growing the deque.
     * <p>Auxiliary space complexity: O(n), for the stack when heights never increase.
     *
     * @param height non-negative bar heights; the problem allows 1..20,000 bars with
     *               heights at most 100,000. Null and empty arrays are also supported.
     * @return trapped water in unit squares; zero for null or fewer than three bars.
     *         The problem's maximum total, 1,999,800,000, fits in an int.
     */
    public int trap(int[] height) {
        // corner cases
        if (height == null || height.length < 3) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0, bottom, left;

        for (int i = 0; i < height.length; i++) {
            // The current bar can close layers above every shorter bar on the stack.
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                bottom = stack.pop();
                // Without a remaining left wall this layer drains away, so add nothing.
                if (!stack.isEmpty()) {
                    left = stack.peek();
                    // Count only the layer above bottom; deeper pockets were counted earlier.
                    // min(left wall, right wall) prevents overflow over the shorter wall.
                    max += (Math.min(height[i], height[left]) - height[bottom]) * (i - left - 1);
                }
            }
            // Equal heights may remain; a pop with an equally tall left wall adds zero water.
            stack.push(i);
        }

        return max;
    }

    /**
     * Counts water one position at a time while moving inward from both ends. At any
     * position, water reaches the shorter of the tallest bars to its left and right.
     * {@code leftMax} and {@code rightMax} remember the tallest bars already processed
     * on each side, avoiding the stack's storage of individual boundaries.
     *
     * <p>Process the shorter current endpoint. If it sets a new maximum on its side,
     * it holds no water, and the opposite endpoint is already at least as tall. This
     * establishes a far wall for lower bars reached later from that side. If it is
     * below its side's maximum, that established far wall makes the side's maximum
     * the limiting water level, so their difference is the exact water at this position.
     * The same reasoning applies from either end; ties can be processed from the left.
     * Every step settles one position, and the final meeting bar needs no water.
     * The input is not modified, and no state is retained between calls.
     *
     * <p>Time complexity: O(n), where n is the number of bars; one pointer moves inward
     * on each iteration, so at most n - 1 positions are processed.
     * <p>Auxiliary space complexity: O(1), using only pointers, maxima, and a running total.
     *
     * @param height non-negative bar heights; the problem allows 1..20,000 bars with
     *               heights at most 100,000. Null and empty arrays are also supported.
     * @return trapped water in unit squares; zero for null or fewer than three bars.
     *         The problem's maximum total, 1,999,800,000, fits in an int.
     */
    public int trapTwoPointers(int[] height) {
        // Fewer than three bars cannot surround any water.
        if (height == null || height.length < 3) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        while (left < right) {
            // Settle the shorter endpoint; a taller unknown middle bar cannot lower its water level.
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    // A new left wall holds no water; height[right] supplies a wall at least as tall.
                    leftMax = height[left];
                } else {
                    // A right wall reaching leftMax is already established, so only leftMax limits us.
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    // Symmetrically, height[left] supports this new right maximum.
                    rightMax = height[right];
                } else {
                    // This column is below a known right wall, with a sufficiently tall wall to its left.
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        // A global maximum survives until the pointers meet, so the final bar holds no water.
        return water;
    }
}
