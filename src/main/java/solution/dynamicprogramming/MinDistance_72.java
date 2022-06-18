package solution.dynamicprogramming;

import java.util.Arrays;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * 3 operations permitted on a word:
 * 1. Insert a character
 * 2. Delete a character
 * 3. Replace a character
 *
 * @author BorisMirage
 * Time: 2018/10/03 11:08
 * Created with IntelliJ IDEA
 */

public class MinDistance_72 {
    /**
     * Dynamic programming with a 2D array.
     * State transition:
     * dp[i][j] = dp[i - 1][j - 1], if word1.charAt(i - 1) == word2.charAt(j - 1)
     * dp[i + 1][j + 1] = min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]) + 1, otherwise.
     *
     * @param word1 string 1
     * @param word2 target converted string
     * @return min edit distance
     */
    public int minDistance(String word1, String word2) {

        /* Corner case */
        if (word1 == null || word2 == null) {
            return 0;
        }
        if (word1.equals(word2)) {
            return 0;
        }

        int l1 = word1.length(), l2 = word2.length();

        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 1; i < l1 + 1; i++) {
            dp[i][0] = i;       // at most l1 times deletion
        }

        for (int i = 1; i < l2 + 1; i++) {
            dp[0][i] = i;       // at most l2 times insertion
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {

                    /*
                     * dp[i - 1][j]: delete char in word1, i -> i + 1
                     * dp[i][j - 1]: insert char to word1, j -> j + 1
                     * dp[i - 1][j - 1]: replace */
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[l1][l2];
    }

    /**
     * Use DFS to find the min edit distance, with a 2D int array as pruning (without that would cause TLE).
     *
     * @param word1 string 1
     * @param word2 target converted string
     * @return min edit distance
     */
    public int dfsImpl(String word1, String word2) {

        int n1 = word1.length(), n2 = word2.length();

        /* Corner case */
        if (n1 == 0 || n2 == 0) {
            return word1.length() | word2.length();     // if one is empty, then the min distance is the other word
        }
        int[][] mem = new int[n1 + 1][n2 + 1];

        for (int[] m : mem) {
            Arrays.fill(m, -1);
        }

        return dfs(word1, word2, n1, n2, mem);
    }

    /**
     * DFS search.
     * The branch of searching is to check if current character is matched.
     * If matched, then both string move one char forward.
     * Otherwise, three branches: insert, update, delete.
     *
     * @param s1  first string
     * @param s2  target string
     * @param i   index of s1
     * @param j   index of s2
     * @param mem memorization array
     * @return min edit distance
     */
    private int dfs(String s1, String s2, int i, int j, int[][] mem) {
        if (i == 0 || j == 0) {
            return j + i;
        }

        if (mem[i][j] != -1) {
            return mem[i][j];
        }

        int insert = dfs(s1, s2, i, j - 1, mem) + 1;
        int delete = dfs(s1, s2, i - 1, j, mem) + 1;

        /*
         * If two chars are matched, then the cost will not change.
         * But both pointer will moving forward (same as replacement)
         * Otherwise, replace operation will cost one. */
        int replace = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? dfs(s1, s2, i - 1, j - 1, mem) : dfs(s1, s2, i - 1, j - 1, mem) + 1;

        int min = Math.min(insert, Math.min(delete, replace));
        mem[i][j] = min;

        return min;
    }

    public static void main(String[] args) {
        MinDistance_72 test = new MinDistance_72();
        long startTime = System.nanoTime();
        System.out.println(test.dfsImpl("zoologicoarchaeologist", "zoogeologist"));     // 10
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

        startTime = System.nanoTime();
        System.out.println(test.dfsImpl("dinitrophenylhydrazine", "acetylphenylhydrazine"));     // 6
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
