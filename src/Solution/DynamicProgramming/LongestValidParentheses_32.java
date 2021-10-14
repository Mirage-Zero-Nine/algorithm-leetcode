package Solution.DynamicProgramming;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')'.
 * Find the length of the longest valid (well-formed) parentheses substring.
 *
 * @author BorisMirage
 * Time: 2018/06/21 13:19
 * Created with IntelliJ IDEA
 */

public class LongestValidParentheses_32 {
    /**
     * Traverse given string for two times, set two counter "left" and "right" to count "(", ")" respectively.
     * First from left to right, if left equals to right and none of them are 0, then one valid length is found.
     * Compare current length to max length. If it is larger, then switch it. Then reset both counters.
     * When first traverse completed, traverse string from right to left with same process.
     * The second traverse is to avoid that right parenthesis is more than left parenthesis in given string.
     * For example, when string is )))))()(), then the max counter will never be triggered.
     * Final max length is the result.
     * Analysis:
     * Time complexity: O(n). Single traversal of string to fill dp array is done.
     * Space complexity: O(1). Three local variables are used.
     *
     * @param s input string
     * @return length of the longest valid (well-formed) parentheses substring
     */
    public int longestValidParentheses(String s) {

        /* Corner case */
        if (s.length() < 2) {
            return 0;
        }

        return Math.max(helper(s, 0, s.length(), '('), helper(s, -s.length() + 1, 0, ')'));
    }

    /**
     * Find length of the longest valid parentheses substring by traversing given string.
     * Use a for loop to traverse string.
     * Therefore, if starts from left to right, start index is 0, end index is string's length.
     * If traverse from right to left, start index is -(string's length + 1), since for loop is fixed.
     * Ending index under this situation is 0.
     *
     * @param s     given string
     * @param start start index of string
     * @param end   end index of string
     * @param c     left parenthesis
     * @return length of longest valid parentheses substring
     */
    private int helper(String s, int start, int end, char c) {
        int left = 0, right = 0, max = 0;

        for (int i = start; i < end; i++) {
            if (s.charAt(Math.abs(i)) == c) {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(left + right, max);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        return max;
    }

    /**
     * Another solution of this problem that uses stack to store left parentheses' position.
     * If encounter right parenthesis, pop stack's top index and compare i - index to max length.
     * If stack is empty and encounter right parenthesis, push current index into stack as next valid length's start.
     * Analysis:
     * Time complexity: O(n). Single traversal of string to fill dp array is done.
     * Space complexity: O(n). Stack at most store string's length
     *
     * @param s input parentheses string
     * @return longest valid parentheses length
     */
    public int longestValidParenthesesStack(String s) {

        /* Corner case */
        if (s.length() < 2) {
            return 0;
        }

        Stack<Integer> leftStack = new Stack<>();       // stack that store left parentheses

        leftStack.push(-1);     // -1 can be regarded as “extended” start position

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftStack.push(i);
            } else {
                leftStack.pop();
                if (leftStack.size() == 0) {
                    leftStack.push(i);      // start position for next valid parentheses length counting
                } else {
                    maxLength = Math.max(maxLength, i - leftStack.peek());
                }
            }
        }

        return maxLength;
    }

    /**
     * DP solution.
     * State transition:
     * If s.charAt(i) == ')', then there may be possible valid pair.
     * If s.charAt(i - 1) == '(', then dp[i] = dp[i - 2] + 2 (required to check if i - 2 is out of boundary).
     * Otherwise, if s.charAt(i - dp[i - 1] - 1) == '(', which shows current ')' matches a '('.
     * dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2
     * If i - dp[i - 1] <= 1, then start position of current pair is start of string, dp[i] = dp[i - 1] + 2.
     * Analysis:
     * Time complexity: O(n). Single traversal of string to fill dp array is done.
     * Space complexity: O(n). dp array of size n is used.
     *
     * @param s input string
     * @return longest valid parentheses length
     */
    public int longestValidParenthesesDP(String s) {

        int[] dp = new int[s.length()];        // save temp max
        int maxParentheses = 0;

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) == ')') {       // if current char is ')', then there may be valid pair
                if (s.charAt(i - 1) == '(') {       // add a new pair

                    dp[i] = (i > 1) ? dp[i - 2] + 2 : 2;

                } else if (i > dp[i - 1] && s.charAt(i - dp[i - 1] - 1) == '(') {

                    /*
                     * i - dp[i - 1] - 1: find first '('.
                     * If i - dp[i - 1] - 1 > 0, then there may be previous valid pair. Add to dp[i].
                     * Otherwise, if i - dp[i - 1] - 1 == 0, then the valid pair starts from start.
                     * Under this condition, dp[i - 1] + 2 will include all valid pair from start. */
                    dp[i] = (i - dp[i - 1] > 1) ? dp[i - 1] + dp[i - dp[i - 1] - 2] + 2 : dp[i - 1] + 2;
                }
                maxParentheses = Math.max(maxParentheses, dp[i]);
            }
        }
        return maxParentheses;
    }
}
