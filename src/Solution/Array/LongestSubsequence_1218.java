package Solution.Array;

import java.util.HashMap;

/**
 * Given an integer array arr and an integer difference.
 * Return the length of the longest subsequence in it which is an arithmetic sequence with certain given difference.
 *
 * @author BorisMirage
 * Time: 2019/10/05 19:34
 * Created with IntelliJ IDEA
 */

public class LongestSubsequence_1218 {
    /**
     * Use a hash map to store the element, and value is the length of longest subsequence with given difference.
     *
     * @param arr        given array
     * @param difference given difference
     * @return length of the longest subsequence in it which is an arithmetic sequence with certain given differenc
     */
    public int longestSubsequence(int[] arr, int difference) {
        int out = 1, n = arr.length;

        HashMap<Integer, Integer> m = new HashMap<>();      // key: element in array, value:
        m.put(arr[0], 1);
        for (int i = 1; i < n; i++) {

            /*
             * Each time, find if previous array contains element that with a certain difference.
             * If so, compare it with current longest length.
             * Then put the value and new count back to hash map.
             * If it is first time find an element, set value to 1. */
            out = Math.max(out, m.getOrDefault(arr[i] - difference, 1));
            m.put(arr[i], m.getOrDefault(arr[i] - difference, 0) + 1);
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubsequence_1218().longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));
    }
}
