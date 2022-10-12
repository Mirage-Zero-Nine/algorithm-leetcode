package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
 * You are also given the entrance of the maze, where denotes the cell you are initially standing at.
 * In one step, you can move one cell up, down, left, or right.
 * You cannot step into a cell with a wall, and you cannot step outside the maze.
 * Your goal is to find the nearest exit from the entrance.
 * An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 *
 * @author BorisMirage
 * Time: 2022/10/11 20:50
 * Created with IntelliJ IDEA
 */

public class NearestExit_1926 {
    /**
     * Simple BFS.
     * The exit is any of the row or column reaches the first or last row/column.
     * Note that if the start position is at the "exit", it doesn't count.
     *
     * @param maze     given 2D char array
     * @param entrance initial position
     * @return steps of the shortest path from the entrance to the nearest exit, or -1 if no such path exists
     */
    public int nearestExit(char[][] maze, int[] entrance) {

        int row = maze.length, column = maze[0].length, startX = entrance[0], startY = entrance[1];

        /* Corner case */
        if (startX < 0 || startX > row || startY < 0 || startY > column) {
            return -1;
        }

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(entrance);
        maze[startX][startY] = 'x';

        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                if (step != 0 && (current[0] == 0 || current[0] == row - 1 || current[1] == 0 || current[1] == column - 1)) {
                    return step;
                }

                for (int[] d : directions) {
                    int nextX = d[0] + current[0], nextY = d[1] + current[1];
                    if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < column && maze[nextX][nextY] == '.') {
                        queue.add(new int[]{nextX, nextY});
                        maze[nextX][nextY] = 'x';
                    }
                }
            }
            step++;
        }

        return -1;
    }
}
