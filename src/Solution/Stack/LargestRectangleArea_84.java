package Solution.Stack;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1.
 * Find the area of largest rectangle in the histogram.
 *
 * @author BorisMirage
 * Time: 2018/08/16 14:33
 * Created with IntelliJ IDEA
 */

public class LargestRectangleArea_84 {
    /**
     * Monotone stack problem.
     * Traverse all elements in array and find each increasing subarray.
     * If increasing subarray is ended, then calculate rectangle size starts from largest element in subarray.
     * The initially length of rectangle is 1 (itself).
     * With the pop of stack, length increased by 1 each time until value at top of stack is smaller than current element.
     * For each deceasing area, simply count the area with itself and compared with max.
     * Note that if stack is empty, current element is the smallest element in current subarray.
     * The area length of this rectangle is [0,i].
     *
     * @param heights input histogram's bar height array
     * @return area of largest rectangle
     */
    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();       // store index of increasing array
        int max = 0;

        for (int i = 0; i <= heights.length; i++) {

            int h = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && h < heights[stack.peek()]) {      // increasing subarray stopped

                /*
                 * Stack only store index, therefore, heights[s.pop()] stands for the length of current height.
                 * The other part of area calculation is the length of rectangle.
                 * The length is between current bar and the max bar in increasing subarray.
                 * If stack is empty, then current element is the smallest element in [0,i], and length will be i.
                 * i - 1 is to move i to previous max value in previous increasing subarray.
                 * If stack is empty, current element is the smallest element in current subarray.
                 * The area length of this rectangle is [0,i]. */
                int area = heights[stack.pop()] * (i - 1 - (stack.isEmpty() ? -1 : stack.peek()));
                max = Math.max(max, area);
            }
            stack.push(i);
        }
        return max;
    }

    /**
     * Slow yet plain brute force solution.
     * Traverse all elements in array.
     * In each round, find all elements that is larger than current bar. Calculate max area based on current bar.
     *
     * @param heights input histogram's bar height array
     * @return area of largest rectangle
     */
    public int largestRectangleAreaSlow(int[] heights) {

        if (heights.length == 0) {
            return 0;
        }

        int maxArea = heights[0];

        for (int i = 0; i < heights.length; i++) {
            int l = i - 1;
            int r = i + 1;

            while (l >= 0 && heights[i] <= heights[l]) {
                l--;
            }

            while (r < heights.length && heights[i] <= heights[r]) {
                r++;
            }

            int temp = heights[i] * (r - l - 1);
            maxArea = Integer.max(maxArea, temp);
        }

        return maxArea;
    }


    public static void main(String[] args) {
        LargestRectangleArea_84 largestRectangleAreaTest = new LargestRectangleArea_84();
        int[] arr = {10000, 2, 3, 4, 5};
        System.out.println(largestRectangleAreaTest.largestRectangleArea(arr));
    }

}
