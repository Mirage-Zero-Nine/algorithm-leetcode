package solution.binarysearch;

import java.util.Arrays;

/**
 * Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s.
 * For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
 * Now, given string arrays queries and words.
 * Return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
 *
 * @author BorisMirage
 * Time: 2019/08/24 20:01
 * Created with IntelliJ IDEA
 */

public class NumSmallerByFrequency_1170 {
    /**
     * Use binary search to find how many strings in words has higher score.
     *
     * @param queries given queries
     * @param words   given words
     * @return each answer[i] is the number of words such that f(queries[i]) < f(W)
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] q = new int[queries.length], w = new int[words.length];

        for (int i = 0; i < q.length; i++) {
            q[i] = countScore(queries[i]);
        }
        for (int i = 0; i < w.length; i++) {
            w[i] = countScore(words[i]);
        }

        Arrays.sort(w);
        int[] out = new int[q.length];
        for (int i = 0; i < q.length; i++) {
            int l = 0, r = w.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (w[mid] <= q[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            out[i] = w.length - l;
        }
        return out;
    }

    /**
     * Count score of words.
     *
     * @param str given words
     * @return score of words
     */
    private int countScore(String str) {
        int[] arr = new int[26];
        for (char ch : str.toCharArray()) {
            arr[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return arr[i];
            }
        }
        return 0;
    }
}
