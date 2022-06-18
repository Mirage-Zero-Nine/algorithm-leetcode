package solution.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * @author BorisMirage
 * Time: 2019/09/14 14:03
 * Created with IntelliJ IDEA
 */

public class NumIslands2_305 {
    /**
     * Union find.
     * Each time, when convert a new water cell to land, check its neighbor.
     * If this neighbor is not connected to current island, union it and reduce island number by 1.
     * Note that avoid the duplication that a cell is repeatedly converted to land.
     *
     * @param m         m rows
     * @param n         n columns
     * @param positions list of positions to operate
     * @return count the number of islands after each addLand operation
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> out = new ArrayList<>();

        /* Corner case */
        if (m <= 0 || n <= 0) {
            return out;
        }

        int[] d = new int[]{1, -1, 0, 0, 0, 0, 1, -1};
        int[] root = new int[m * n];
        Arrays.fill(root, -1);
        int count = 0;

        for (int[] p : positions) {
            int r = n * p[0] + p[1];        // new land
            if (root[r] == -1) {        // avoid duplication
                root[r] = r;
                count++;

                for (int i = 0; i < 4; i++) {
                    int xx = d[i] + p[0], yy = d[i + 4] + p[1];
                    int id = n * xx + yy;
                    if (xx >= 0 && xx < m && yy >= 0 && yy < n && root[id] != -1) {
                        int f = find(root, id);
                        if (r != f) {       // find island next to current island
                            root[r] = f;
                            r = f;          // reset root for next neighbor
                            count--;
                        }
                    }
                }
            }

            out.add(count);
        }

        return out;
    }

    /**
     * Find root of given id.
     *
     * @param root array stores root of each node
     * @param id   find id
     * @return root of current node
     */
    public int find(int[] root, int id) {
        while (id != root[id]) {
            root[id] = root[root[id]];
            id = root[id];
        }
        return id;
    }
}
