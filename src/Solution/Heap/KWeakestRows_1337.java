package Solution.Heap;

import java.util.PriorityQueue;

/**
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians).
 * Return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
 * Row i is weaker than row j, if the # of soldiers in row i is less than row j, or they have the same number but i < j.
 * Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.
 *
 * @author BorisMirage
 * Time: 2020/02/07 09:57
 * Created with IntelliJ IDEA
 */

public class KWeakestRows_1337 {
    /**
     * Top K frequent problem. Use max heap to find top K target.
     * Note that if two rows contains same number of 1, then the row with smaller index is "weaker".
     *
     * @param mat given matrix
     * @param k   k frequent element
     * @return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        int[] out = new int[k];

        for (int i = 0; i < mat.length; i++) {
            pq.offer(new int[]{count(mat[i]), i});
            if (pq.size() > k) {
                pq.poll();
            }
        }

        while (k > 0) {
            out[--k] = pq.poll()[1];
        }

        return out;
    }

    /**
     * Use binary search to count # of 1 in each row.
     * The reason is that all 1 starts at left of row.
     *
     * @param row each row in given matrix
     * @return # of 1 in each row
     */
    private int count(int[] row) {
        int start = 0;
        int end = row.length;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (row[mid] == 1) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}
