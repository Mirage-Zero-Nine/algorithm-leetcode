package Solution.DynamicProgramming;

/**
 * Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
 * Suppose you triangulate the polygon into N-2 triangles.
 * For each triangle, the value of that triangle is the product of the labels of the vertices.
 * And the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 *
 * @author BorisMirage
 * Time: 2019/09/19 22:22
 * Created with IntelliJ IDEA
 */

public class MinScoreTriangulation_1039 {
    /**
     * Bottom-up dynamic programming.
     * Divide polygon into triangles can be regarded as calculate sum of vertices' product.
     * The recursive problem can be converted into, find a point that make score from A[i] to A[j] minimum.
     * And then for the selected point k from previous problem, find a point that make score from A[i] to A[k] minimum.
     * Bottom-up state transition:
     * dp[i][j]: the minimum score to triangulate from A[i] to A[j].
     * dp[i][j] = min(dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]), where i < k < j, and 2 < i + j < A.length.
     *
     * @param A given array
     * @return smallest possible total score that you can achieve with some triangulation of the polygon
     */
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];

        for (int length = 2; length < n; length++) {      // length at least should be 2
            for (int i = 0; i + length < n; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
