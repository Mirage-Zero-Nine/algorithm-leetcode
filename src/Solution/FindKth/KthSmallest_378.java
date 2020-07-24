package Solution.FindKth;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order.
 * Find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * @author BorisMirage
 * Time: 2019/06/25 15:01
 * Created with IntelliJ IDEA
 */

public class KthSmallest_378 {
    /**
     * Binary search.
     * Start the Binary Search with start = matrix[0][0] and end = matrix[n-1][n-1].
     * Find median of the start and the end. This median is NOT necessarily an element in the matrix.
     * Count all the numbers smaller than or equal to median in the matrix. It can be done in O(N).
     * While counting, keep tracking two values:
     * “smallest number greater than the middle” (n1) and “biggest number less than or equal to the middle” (n2).
     * These two numbers will be used to adjust the "number range" for the Binary Search in the next iteration.
     * If count == K, n1 will be required as it is the “biggest number less than or equal to the middle”.
     * If count < K, update start = n2 to search in the higher part of the matrix.
     * If the count is greater than ‘K’, update end = n1 to search in the lower part of the matrix in the next iteration.
     *
     * @param matrix given matrix
     * @param k      kth smallest element
     * @return kth smallest element in the sorted order
     */
    public int kthSmallest(int[][] matrix, int k) {
        int start = matrix[0][0], end = matrix[matrix.length - 1][matrix.length - 1];

        while (start < end) {
            int mid = start + (end - start) / 2;
            int[] pair = {matrix[0][0], matrix[matrix.length - 1][matrix.length - 1]};
            int count = countLessEqual(matrix, mid, pair);      // pair will update to closest smaller to median and floor value of median

            /*
             * Narrow the search range if # of smaller elements are not k.
             * If # of smaller elements < k, then search range is narrowed to closest smaller element to median - end
             * If # of smaller elements > k, then search range is narrowed to start - floor value of median */
            if (count == k) {
                return pair[0];
            } else if (count < k) {
                start = pair[1];        // closest smaller element to median
            } else {
                end = pair[0];
            }
        }
        return start;
    }

    /**
     * Find closest smaller to median and floor value of median.
     *
     * @param matrix given matrix
     * @param mid    median of given range
     * @param pair   previous pair of closest smaller to median and floor value of median
     * @return count of # that is smaller than median
     */
    private int countLessEqual(int[][] matrix, int mid, int[] pair) {
        int count = 0;
        int n = matrix.length, row = n - 1, column = 0;

        while (row >= 0 && column < n) {
            if (matrix[row][column] > mid) {
                pair[1] = Math.min(pair[1], matrix[row--][column]);
            } else {
                pair[0] = Math.max(pair[0], matrix[row][column++]);
                count += row + 1;       // end of row is the largest element in row
            }
        }
        return count;
    }
}
