package Solution.DFS;

/**
 * There is a ball in a maze with empty spaces and walls.
 * The ball can go through empty spaces by rolling up, down, left and right, but it won't stop until hitting a wall.
 * When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination, determine whether the ball could stop at the destination.
 * The maze is represented by a binary 2D array, 1 means the wall and 0 means the empty space.
 * The start and destination coordinates are represented by row and column indexes.
 * Note:
 * 1. There is only one ball and one destination in the maze.
 * 2. Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * 3. The border of the maze are all walls.
 * 4. The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 *
 * @author BorisMirage
 * Time: 2018/10/06 10:24
 * Created with IntelliJ IDEA
 */

public class HasPath_490 {
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * DFS with recursion.
     *
     * @param maze        maze data
     * @param start       start point
     * @param destination end point
     * @return whether the ball could stop at the destination
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        /* Corner case */
        if (maze.length == 0 || maze[0].length == 0) {
            return false;
        }

        return dfs(maze, start, destination, new boolean[maze.length][maze[0].length]);
    }

    /**
     * DFS recursion.
     *
     * @param maze      maze data
     * @param cur       current point
     * @param end       end point
     * @param isVisited is current point has been visited before
     * @return true if could stop at the current location
     */
    private boolean dfs(int[][] maze, int[] cur, int[] end, boolean[][] isVisited) {

        /* End point */
        if (cur[0] == end[0] && cur[1] == end[1]) {
            return true;
        }
        int x = cur[0];
        int y = cur[1];

        /* If this point has been visited before or out of bound */
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1 || isVisited[x][y]) {
            return false;
        }

        /* If current point is available, mark this point */
        isVisited[x][y] = true;

        for (int[] direction : directions) {
            int xx = x;
            int yy = y;

            /* Simulation of "rolling" process */
            while (xx > -1 && yy > -1 && xx < maze.length && yy < maze[0].length && maze[xx][yy] == 0) {
                xx += direction[0];
                yy += direction[1];
            }
            if (dfs(maze, new int[]{xx - direction[0], yy - direction[1]}, end, isVisited)) {
                return true;
            }
        }
        return false;
    }
}
