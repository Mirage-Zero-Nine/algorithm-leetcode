package Solution.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.
 *
 * @author BorisMirage
 * Time: 2020/02/25 11:34
 * Created with IntelliJ IDEA
 */

public class TilingRectangle_1240 {
    private int min = Integer.MAX_VALUE;
    private final Map<Long, Integer> m = new HashMap<>();

    /**
     * Memorized backtracking.
     *
     * @param m rectangle length
     * @param n rectangle width
     * @return minimum number of integer-sided squares that tile the rectangle
     */
    public int tilingRectangle(int m, int n) {
        if (n == m) {
            return 1;
        }

        if (m > n) {
            int tmp = n;
            n = m;
            m = tmp;
        }

        backtracking(n, m, new int[n + 1], 0);

        return min;
    }

    /**
     * Memorized backtracking.
     *
     * @param m      rectangle length
     * @param n      rectangle width
     * @param height height
     * @param count counting current number of rectangle
     */
    private void backtracking(int m, int n, int[] height, int count) {

        if (count >= min) {     // pruning
            return;
        }

        boolean isFull = true;

        int pos = -1, minHeight = Integer.MAX_VALUE;

        for (int i = 1; i <= m; i++) {
            if (height[i] < n) {
                isFull = false;
            }
            if (height[i] < minHeight) {
                pos = i;
                minHeight = height[i];
            }
        }

        if (isFull) {
            min = Math.min(count, min);
            return;
        }

        long key = 0, base = n + 1;
        for (int i = 1; i <= m; i++) {
            key += height[i] * base;
            base *= n + 1;
        }

        if (this.m.containsKey(key) && this.m.get(key) <= count) {
            return;
        }

        this.m.put(key, count);

        int end = pos;
        while (end + 1 <= m && height[end + 1] == height[pos] && (end + 1 - pos + 1 + minHeight) <= n) {
            end++;
        }
        for (int j = end; j >= pos; j--) {
            int currentHeight = j - pos + 1;

            int[] next = Arrays.copyOf(height, m + 1);
            for (int k = pos; k <= j; k++) {
                next[k] += currentHeight;
            }
            backtracking(m, n, next, count + 1);
        }
    }
}
