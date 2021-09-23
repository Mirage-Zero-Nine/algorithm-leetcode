package Solution.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
 * To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland.
 * These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
 * land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1).
 * Find the coordinates of the top left and bottom right corner of each group of farmland.
 * A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
 * Return a 2D array containing the 4-length arrays described above for each group of farmland in land.
 * If there are no groups of farmland, return an empty array. You may return the answer in any order.
 *
 * @author BorisMirage
 * Time: 2021/09/22 23:11
 * Created with IntelliJ IDEA
 */

public class FindFarmland_1992 {
    /**
     * DFS to find the bottom-right coord of each farmland.
     * Based on the DFS starts at top left of the farmland, always search right and down to find new farmland.
     * Meanwhile, return the coords of current position in each DFS.
     * The bottom right coord can be found by simply comparing the largest index during DFS.
     *
     * @param land matrix land where a 0 represents forested land and a 1 represents farmland
     * @return 2D array containing the 4-length arrays described above for each group of farmland in land
     */
    public int[][] findFarmland(int[][] land) {
        List<int[]> output = new ArrayList<>();

        /* Corner case */
        if (land == null | land.length == 0 || land[0] == null || land[0].length == 0) {
            return new int[0][0];
        }

        int length = land.length;
        int width = land[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (land[i][j] == 1) {
                    int[] max = dfs(i, j, land);
                    output.add(new int[]{i, j, max[0], max[1]});
                }
            }
        }

        return output.toArray(new int[0][]);
    }

    /**
     * DFS to find all the farmland.
     *
     * @param i    index i
     * @param j    index j
     * @param land matrix land where a 0 represents forested land and a 1 represents farmland
     * @return bottom right coord of farmland
     */
    private int[] dfs(int i, int j, int[][] land) {
        int length = land.length;
        int width = land[0].length;

        if (i >= length || j >= width || land[i][j] == 0) {
            return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        }
        land[i][j] = 0;

        int[] right = dfs(i + 1, j, land);
        int[] bottom = dfs(i, j + 1, land);
        int[] max = new int[]{i, j};
        max[0] = Math.max(right[0], max[0]);
        max[1] = Math.max(bottom[1], max[1]);

        return max;
    }
}
