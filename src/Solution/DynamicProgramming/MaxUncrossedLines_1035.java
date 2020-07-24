package Solution.DynamicProgramming;

/**
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
 * 1. A[i] == B[j];
 * 2. The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints.
 * Each number can only belong to one connecting line.
 * Return the maximum number of connecting lines we can draw in this way.
 *
 * @author BorisMirage
 * Time: 2020/05/26 13:30
 * Created with IntelliJ IDEA
 */

public class MaxUncrossedLines_1035 {
    /**
     * Variant of LCS problem.
     * The LCS problem select common subsequence with order. The order of subsequence is the "uncrossed" of lines.
     * If there are two elements equal, then a new line can be drawn.
     * However, there is no intersection is allowed between the lines.
     * Therefore, dp[i][j] = dp[i - 1][j - 1], which is the max lines that removing ith and jth element.
     *
     * @param A first array
     * @param B second array
     * @return the maximum number of connecting lines we can draw in this way
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                /*
                 * If there are two elements equal, then a new line can be drawn.
                 * However, there is no intersection is allowed between the lines.
                 * Therefore, dp[i][j] = dp[i - 1][j - 1], which is the max lines that removing ith and jth element.
                 * If A[i] != B[j], then no new line can be drawn. */
                dp[i][j] = (A[i - 1] == B[j - 1]) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }
}
