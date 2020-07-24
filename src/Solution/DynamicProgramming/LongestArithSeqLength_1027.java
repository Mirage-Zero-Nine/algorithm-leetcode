package Solution.DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 *
 * @author BorisMirage
 * Time: 2019/09/19 19:06
 * Created with IntelliJ IDEA
 */

public class LongestArithSeqLength_1027 {
    private int max;

    /**
     * Backtracking with pruning.
     *
     * @param A given array
     * @return length of the longest arithmetic subsequence in A
     */
    public int longestArithSeqLength(int[] A) {
        int n = A.length;

        /* Corner case */
        if (n == 2) {
            return 2;
        }

        HashMap<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!m.containsKey(A[i])) {
                m.put(A[i], new LinkedList<>());
            }
            m.get(A[i]).add(i);
        }

        boolean[] isVisited = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            isVisited[i] = true;
            for (int j = i + 1; j < n; j++) {
                isVisited[j] = true;
                backtracking(m, isVisited, A[i] - A[j], j, A, 2);
                isVisited[j] = false;
            }
        }

        return max;
    }

    /**
     * Backtracking with pruning to find the longest arithmetic subsequence in A.
     *
     * @param m         hash map stores elements in array and its index
     * @param isVisited records visited elements
     * @param diff      difference under current searching
     * @param index     index of current element
     * @param arr       given array
     * @param l         length of current arithmetic subsequence
     */
    private void backtracking(HashMap<Integer, List<Integer>> m, boolean[] isVisited, int diff, int index, int[] arr, int l) {
        max = Math.max(l, max);

        if (arr.length - index + l < max) {     // pruning: if current subarray can not be the longest, end searching
            return;
        }

        if (m.containsKey(arr[index] - diff)) {

            List<Integer> list = m.get(arr[index] - diff);

            for (int i : list) {
                if (!isVisited[i] && i > index) {
                    isVisited[i] = true;
                    backtracking(m, isVisited, diff, i, arr, l + 1);
                    isVisited[i] = false;
                }
            }
        }
    }

    /**
     * Dynamic programming approach.
     * State transition:
     * dp[i][j]: longest arithmetic subarray with common difference i, from 0 to j
     * dp[i][j] = max(dp[k][j], dp[i][j]), where k from 0 to j.
     *
     * @param A given array
     * @return length of the longest arithmetic subsequence in A
     */
    public int dp(int[] A) {
        int max = 2, n = A.length;
        List<HashMap<Integer, Integer>> dp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dp.add(new HashMap<>());
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                int diff = A[j] - A[i];
                dp.get(j).put(diff, dp.get(i).getOrDefault(diff, 1) + 1);
                max = Math.max(max, dp.get(j).get(diff));

            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestArithSeqLength_1027().longestArithSeqLength(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));
        System.out.println(new LongestArithSeqLength_1027().longestArithSeqLength(new int[]{9, 4, 7, 2, 10}));
        System.out.println(new LongestArithSeqLength_1027().longestArithSeqLength(new int[]{3, 6, 9, 12}));
    }
}
