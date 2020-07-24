package Solution.DynamicProgramming;

/**
 * Given n, find many structurally unique BST's (binary search trees) that store values 1 ... n?
 * (n >= 1)
 *
 * @author BorisMirage
 * Time: 2018/10/12 10:19
 * Created with IntelliJ IDEA
 */

public class NumTrees_96 {
    /**
     * Dynamic programming.
     * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
     * G(n) is the actual function we need to calculate in order to solve the problem
     * G(0) = 1, G(1) = 1.
     * F(i, n), 1 <= i <= n: the number of unique BST, where i is the root of BST, and the sequence ranges from 1 to n.
     * F(i, n) = G(i - 1) * G(n - i)
     * => G(n) = G(0) * G(n - 1) + G(1) * G(n - 2) + ... + G(n - 1) * G(0)
     *
     * @param n given n
     * @return number of unique BST
     */
    public int numTrees(int n) {

        /* Corner case */
        if (n < 3) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {      // first loop: all root in (0, n)
            for (int j = 1; j <= i; j++) {      // second loop: (0, i)
                dp[i] = dp[i] + dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }
}
