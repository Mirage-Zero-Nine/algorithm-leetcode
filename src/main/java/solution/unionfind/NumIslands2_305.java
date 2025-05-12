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
     * Use Union-Find to track connected components for the number of islands after each position is added.
     *
     * @param m         The number of rows in the grid.
     * @param n         The number of columns in the grid.
     * @param positions A 2D array representing the positions to add to the grid.
     * @return A list of integers, where each element represents the number of islands after adding the corresponding position.
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> output = new ArrayList<>();

        // corner case
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0].length == 0) {
            return output;
        }

        // union find: root of each position
        int[] root = new int[m * n];
        Arrays.fill(root, -1);
        int count = 0;
        int[] direction = {0, 1, 0, -1, 0};

        for (int[] p : positions) {
            int currentPosition = n * p[0] + p[1];  // convert 2D coordinates to 1D index
            if (root[currentPosition] == -1) {
                root[currentPosition] = currentPosition;
                count++;

                for (int i = 0; i < 4; i++) {
                    int nx = p[0] + direction[i], ny = p[1] + direction[i + 1];
                    int newPosition = n * nx + ny;

                    // if the adjacent cell is part of an existing island, merge these 2 islands and reduce the count
                    // they are already connected if they already have the same root, merging them would not reduce the number of islands
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && root[newPosition] != -1) {
                        int rootOfCurrentPosition = find(currentPosition, root), rootOfNewPosition = find(newPosition, root);
                        if (rootOfCurrentPosition != rootOfNewPosition) {
                            // merge the two islands by setting the root of one island to the root of the other (union)
                            root[rootOfCurrentPosition] = rootOfNewPosition;
                            count--;
                        }
                    }
                }
            }
            output.add(count);
        }
        return output;
    }

    /**
     * Finds the root of the set containing 'value' with path compression.
     *
     * @param value the element to find the root for.
     * @param root  the parent array representing the disjoint-set structure
     * @return the root of the set 'value' belongs to.
     */
    private int find(int value, int[] root) {

        // find root of given position
        while (root[value] != value) {
            root[value] = root[root[value]];
            value = root[value];
        }
        return value;
    }
}
