package Solution.DynamicProgramming;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * Note:
 * 1. s could be empty and contains only lowercase letters a-z.
 * 2. p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 * @author BorisMirage
 * Time: 2018/10/11 10:20
 * Created with IntelliJ IDEA
 */

public class IsMatch_44 {
    /**
     * Dynamic programming with 2D table.
     * State transition:
     * dp[i][j] = dp[i - 1][j - 1], if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')
     * dp[i][j] = dp[i][j - 1] || dp[i - 1][j], if p.charAt(j) == '*'
     * dp[i][j - 1]: matches empty subsequence (current pattern is not counted)
     * dp[i - 1][j]: matches any sequence 1 ~ n times (if previous string is matched, then current char is matched)
     *
     * @param s string
     * @param p pattern string
     * @return if string is matched to pattern
     */
    public boolean isMatch(String s, String p) {

        /* Corner case */
        if (s == null || p == null) {
            return false;
        }

        int l1 = s.length(), l2 = p.length();
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        dp[0][0] = true;        // empty string matches empty string

        for (int i = 1; i <= l2; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1];        // '*' can match empty substring
            }
        }

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {

                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {

                    /*
                     * dp[i][j - 1]: matches empty subsequence.
                     * dp[i - 1][j]: matches any sequence 1 ~ n times. */
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[l1][l2];
    }

    /**
     * Use DFS to find if s is matched to p.
     * The branch of DFS is same as in DP solution.
     * If p.charAt(j) == s.charAt(i) || p.charAt(j) == '.', then check if p(j + 1), s(i + 1) is matched.
     * If p.charAt(j) == '*', check if p(j + 1) or s(i + 1) is matched.
     * The meaning of p(j + 1), s(i + 1) is same as in DP solution.
     *
     * @param s string
     * @param p pattern string
     * @return if string is matched to pattern
     */
    public boolean dfsImplementation(String s, String p) {

        /* Corner case */
        if (p == null || s == null) {
            return false;
        }

        int l1 = s.length(), l2 = p.length();

        /*
         * mem[i][j]: whether string (0, i) and pattern(0, j) is matched.
         * -1: not matched
         * 1: matched
         * 0: don't know */
        int[][] mem = new int[l1 + 1][l2 + 1];

        /*
         * 1. last cell (mem[l1][l2]) should be true (end point of both string and pattern reaches the end)
         * 2. If last char in pattern is '*', then it can match all subsequence as long as previous part is matched. */
        for (int i = l2; i >= 0 && (i == l2 || (i < l2 && p.charAt(i) == '*')); i--) {
            mem[l1][i] = 1;
        }

        return dfs(s, p, 0, 0, mem);
    }

    /**
     * DFS searching with a 2D int table to accomplish pruning.
     * Branch of DFS:
     * If p.charAt(j) == s.charAt(i) || p.charAt(j) == '.', then check if p(j + 1), s(i + 1) is matched.
     * If p.charAt(j) == '*', check if p(j + 1) or s(i + 1) is matched.
     * mem[i][j]: whether string (0, i) and pattern(0, j) is matched.
     * -1: not matched
     * 1: matched
     * 0: don't know
     *
     * @param s   string
     * @param p   pattern string
     * @param i   current index of string
     * @param j   current index of pattern
     * @param mem 2D boolean array to store the previous result
     * @return if string is matched to pattern
     */
    private boolean dfs(String s, String p, int i, int j, int[][] mem) {

        if (mem[i][j] != 0) {      // reuse previous result
            return mem[i][j] == 1;
        }

        if (j == p.length()) {      // avoid overflow
            return false;
        }

        if (p.charAt(j) == '*') {
            if ((i < s.length() && dfs(s, p, i + 1, j, mem)) || dfs(s, p, i, j + 1, mem)) {
                mem[i][j] = 1;
                return true;
            }
        } else if (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
            mem[i][j] = dfs(s, p, i + 1, j + 1, mem) ? 1 : -1;
            return mem[i][j] == 1;
        }

        mem[i][j] = -1;

        return false;
    }

    /**
     * Two pointers & greedy.
     * If two characters are same or pattern is '?', then move both pointer forward.
     * Otherwise, if pattern is '*', mark the position of '*' and character in string, then move pattern index forward.
     * Initially, always assume matches an empty subsequence. Check if current character matches to pattern.
     * If not, then try to match as much character as possible. This is the subsequence matched by '*'.
     * Finally, pass all possible '*' in the end of pattern and check if the index can reach the end of pattern.
     *
     * @param s string
     * @param p pattern string
     * @return if string is matched to pattern
     */
    public boolean isMatchGreedy(String s, String p) {

        /* Corner case */
        if (p.equals("*") || (s.length() == 0 && p.length() == 0)) {
            return true;
        }

        int p1 = 0, p2 = 0, star = -1, subsequence = 0;

        while (p1 < s.length()) {
            if (p2 < p.length() && (s.charAt(p1) == p.charAt(p2) || p.charAt(p2) == '?')) {
                p1++;
                p2++;
            } else if (p2 < p.length() && p.charAt(p2) == '*') {        // initially, assumes matching empty subsequence
                star = p2++;            // mark the position of '*'
                subsequence = p1;       // mark the start position of potential subsequence
            } else if (star != -1) {     // if current char in string is not matched to pattern and there is a'*'
                p2 = star + 1;             // find the next char in pattern to solid the end of subsequence
                p1 = ++subsequence;        // move index in string to next character
            } else {
                return false;
            }
        }

        while (p2 < p.length() && p.charAt(p2) == '*') {
            p2++;
        }

        return p2 == p.length();
    }

    public static void main(String[] args) {
        IsMatch_44 test = new IsMatch_44();
        System.out.println(test.dfsImplementation("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba", "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));           // true
        System.out.println(test.dfsImplementation("ab", "?*"));           // true
        System.out.println(test.dfsImplementation("aa", "*"));           // true
        System.out.println(test.dfsImplementation("cb", "?a"));           // false
        System.out.println(test.dfsImplementation("aab", "c*a*b"));       // false
        System.out.println(test.dfsImplementation("adceb", "*a*b"));      // true
        System.out.println(test.dfsImplementation("acdcb", "a*c?b"));     // false
        System.out.println(test.dfsImplementation("aa", "*a"));           // true
        System.out.println(test.dfsImplementation("", ""));           // true
    }
}
