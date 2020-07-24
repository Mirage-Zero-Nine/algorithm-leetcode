package Solution.Stack;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * @author BorisMirage
 * Time: 2018/08/27 1:00 PM
 * Created with IntelliJ IDEA
 */

public class MaximalRectangle_85 {
    /**
     * Regarded it as a similar problem of max histogram problem.
     * In each line update the current "histogram" in a int array, when meet '0' simply reset current column to 0.
     * Find max "histogram" of each line and finally find max rectangle.
     *
     * @param matrix input int matrix
     * @return max area
     */
    public int maximalRectangle(char[][] matrix) {

        /* Corner case */
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[] row = new int[matrix[0].length];

        for (int i = 0; i < row.length; i++) {      // search in first line
            if (matrix[0][i] == '1') {
                row[i] += 1;
            }
        }
        int max = largestRectangleArea(row);

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    row[j] += 1;
                } else {
                    row[j] = 0;
                }
            }
            max = Math.max(max, largestRectangleArea(row));     // find max in other rows
        }

        return max;
    }

    /**
     * Traverse all elements in array.
     * Push elements in stack if it is increasing order.
     * If not, pop top of stack and regard current index position as smallest bar. Calculate area.
     * After each calculation, move index -1, since there is a possibility that current top is larger than smallest.
     *
     * @param heights input histogram's bar height array
     * @return area of largest rectangle
     */
    private int largestRectangleArea(int[] heights) {
        Stack<Integer> temp = new Stack<>();
        int maxArea = 0;
        int h;

        for (int i = 0; i <= heights.length; i++) {

            h = (i == heights.length ? 0 : heights[i]);     // set each calculation's bar height. If out of bound, set 0 to pop all elements in stack.
            if (temp.empty() || h > heights[temp.peek()]) {

                temp.push(i);       // push index into stack to record position

            } else {
                int top = temp.pop();
                maxArea = Math.max(maxArea, heights[top] * (temp.empty() ? i : i - 1 - temp.peek()));

                i--;        // i - 1 so that the highest bar can be pushed to the end of stack
            }
        }

        return maxArea;
    }
}
