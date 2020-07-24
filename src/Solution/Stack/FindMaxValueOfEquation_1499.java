package Solution.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array points containing the coordinates of points on a 2D plane, sorted by the x-values.
 * points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.
 * Find the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.
 * It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 *
 * @author BorisMirage
 * Time: 2020/07/03 14:52
 * Created with IntelliJ IDEA
 */

public class FindMaxValueOfEquation_1499 {
    /**
     * Monotone deque.
     * The equation is yi + yj + |xi - xj|.
     * Since the given array has been sorted based on xi, it can be converted to yi - xi + yj + xj.
     * Then it can be converted into find the max value of yi - xi, which is inside one point itself.
     * The left side of deque keeps the points that xj - xi < k.
     * The array is sorted based on x and in ascending order, xj is always larger than xi.
     * The right side of deque keeps the points that has largest yi - xi.
     * When iterate the given array, poll out the left side first, then find the current value of equation.
     * Then poll out the right side based on rule.
     *
     * @param points given points array
     * @param k      limit of |xi - xj|
     * @return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= length
     */
    public int findMaxValueOfEquation(int[][] points, int k) {

        Deque<int[]> dq = new LinkedList<>();       // given array is sorted, hence dq is sorted by x
        int out = Integer.MIN_VALUE;

        for (int[] p : points) {
            while (!dq.isEmpty() && p[0] - dq.peekFirst()[0] > k) {     // xj - xi < k, since xj always larger than xi
                dq.pollFirst();
            }

            if (!dq.isEmpty()) {
                int tmp = p[1] - p[0];      // current max yi - xi
                out = Math.max(out, p[0] + p[1] + dq.peekFirst()[1] - dq.peekFirst()[0]);
                while (!dq.isEmpty() && dq.peekLast()[1] - dq.peekLast()[0] <= tmp) {       // remove all smaller points
                    dq.pollLast();
                }
            }

            dq.addLast(p);
        }

        return out;
    }
}
