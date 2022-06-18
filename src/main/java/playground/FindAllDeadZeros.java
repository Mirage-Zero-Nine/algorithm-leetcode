package playground;

/**
 * Given a binary matrix that only contains 1s and 0s, flip a 0 in matrix to 1, find the number of dead 0s.
 * The dead 0 is the 0 that can not reach boundary of matrix (out of matrix) via 0.
 * It is guarantee that before the flip, there is no dead 0 on given matrix.
 * There are four neighbors for each cell, up, down, left and right.
 *
 * @author BorisMirage
 * Time: 2020/03/08 14:26
 * Created with IntelliJ IDEA
 */

public class FindAllDeadZeros {
    /**
     * DFS.
     * Starts at the 4 neighbor of the given cell. If current cell is 0, then start DFS.
     * If current cell finally proved to be dead 0, then add number of 0 reached into result.
     * Otherwise, no dead 0 found on current branch.
     *
     * @param matrix given matrix
     * @param i      given cell row
     * @param j      given cell column
     * @return number of dead 0
     */
    public int findAllDeadZeros(int[][] matrix, int i, int j) {

        /* Corner case */
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException("Error...");
        }

        int row = matrix.length, column = matrix[0].length, dead = 0;
        int[][] status = new int[row][column];        // unvisited: 0, dead: 1, visited & active: 2
        int[] count = new int[1];
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] d : direction) {
            int xx = i + d[0];
            int yy = j + d[1];

            if (xx >= 0 && xx < matrix.length && yy >= 0 && yy < matrix[0].length && matrix[xx][yy] == 0) {

                if (dfs(matrix, status, count, xx, yy) == 1) {      // if there is one alive 0, then every 0 on this branch is active
                    dead += count[0];   // if all 0s are dead 0s, then add number of 0 in this path to result
                }
                count = new int[1];     // reset counter, since each branch is individual
            }
        }

        return dead;
    }

    /**
     * DFS to check if current 0 is dead 0.
     * If the next step move out of the matrix, then current 0 is active 0.
     * If any of the neighbor of current 0 is active 0, then current 0 is active 0.
     * If every neighbor of current 0 is dead 0, then current 0 is dead 0.
     * There exists a chance that a branch of current 0 is regarded dead (actually not, if there is an active neighbor).
     * To handle this case, if every 0 is dead 0 then it will return 1, only count dead 0 under this situation.
     *
     * @param matrix given matrix
     * @param status status of cell, unvisited: 0; visited: 1
     * @param count  count number of dead 0 (avoid global variable)
     * @param i      current row number
     * @param j      current column number
     * @return if current 0 is dead 0
     */
    private int dfs(int[][] matrix, int[][] status, int[] count, int i, int j) {

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};      // D U R L

        if (status[i][j] > 0) {
            return status[i][j];
        }

        status[i][j] = 1;

        for (int[] d : directions) {
            int xx = i + d[0];
            int yy = j + d[1];
            if (xx < 0 || xx >= matrix.length || yy < 0 || yy >= matrix[0].length || (matrix[xx][yy] == 0 && status[xx][yy] == 0 && dfs(matrix, status, count, xx, yy) == 2)) {
                return 2;       // if a 0 is active on current branch, then this 0 is active
            }
        }

        count[0]++;
        return 1;       // current 0 is assume dead
    }

    public static void main(String[] args) {
        FindAllDeadZeros test = new FindAllDeadZeros();
        int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 0}
        };
        System.out.println(test.findAllDeadZeros(matrix, 1, 2));     // 2
        matrix = new int[][]{
                {0, 0, 1, 1, 1, 0},     // 0
                {1, 0, 0, 0, 0, 1},     // 1
                {1, 0, 1, 1, 0, 1},     // 2
                {1, 0, 1, 1, 0, 1},     // 3
                {1, 0, 0, 0, 0, 1},     // 4
                {0, 1, 1, 1, 1, 0},     // 5
        };
        System.out.println(test.findAllDeadZeros(matrix, 5, 3));     // 0

        matrix = new int[][]{
                {0, 1, 1, 1, 1, 0},     // 0
                {1, 0, 0, 0, 0, 1},     // 1
                {1, 0, 1, 1, 0, 1},     // 2
                {1, 0, 0, 0, 0, 1},     // 3
                {1, 0, 0, 0, 0, 1},     // 4
                {0, 1, 1, 1, 1, 0},     // 5
        };
        System.out.println(test.findAllDeadZeros(matrix, 5, 3));     // 14

        matrix = new int[][]{
                {0, 0, 0, 0, 0, 0},     // 0
                {1, 0, 0, 0, 0, 1},     // 1
                {1, 0, 1, 1, 0, 1},     // 2
                {1, 0, 1, 1, 0, 1},     // 3
                {1, 0, 0, 0, 0, 1},     // 4
                {0, 1, 1, 1, 1, 0},     // 5
        };
        System.out.println(test.findAllDeadZeros(matrix, 0, 0));     // 0
    }
}
