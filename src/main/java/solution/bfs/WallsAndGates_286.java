package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A m x n 2D grid initialized with these three possible values was given.
 * 1 - Obstacle.
 * 0 - Gate.
 * INF - Infinity, an empty room.
 * The value 2^31 - 1 = 2147483647 is used to represent INF - the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate.
 * If it is impossible to reach a gate, it should be filled with INF.
 *
 * @author BorisMirage
 * Time: 2019/05/05 15:33
 * Created with IntelliJ IDEA
 */

public class WallsAndGates_286 {
    /**
     * Find each cell that is 0. BFS based on each 0.
     *
     * @param rooms 2D int array
     */
    public void wallsAndGates(int[][] rooms) {

        /* Corner case */
        if (rooms == null) {
            return;
        }
        if (rooms.length == 0) {
            return;
        }

        int[] directions = new int[]{1, -1, 0, 0, 0, 0, 1, -1};
        int row = rooms.length;
        int col = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            for (int m = 0; m < 4; m++) {
                int xx = tmp[0] + directions[m];
                int yy = tmp[1] + directions[m + 4];
                if (xx >= 0 && xx < row && yy >= 0 && yy < col && rooms[xx][yy] != -1 && (rooms[xx][yy] == Integer.MAX_VALUE || rooms[xx][yy] >= rooms[tmp[0]][tmp[1]] + 1)) {
                    rooms[xx][yy] = rooms[tmp[0]][tmp[1]] + 1;
                    q.add(new int[]{xx, yy});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] r = {{2147483647, -1, 0, 2147483647}, {2147483647, 2147483647, 2147483647, -1}, {2147483647, -1, 2147483647, -1}, {0, -1, 2147483647, 2147483647}};
        WallsAndGates_286 test = new WallsAndGates_286();
        test.wallsAndGates(r);
    }
}

