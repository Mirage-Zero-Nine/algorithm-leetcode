package solution.array;

import java.util.Arrays;

/**
 * You are given a 2D array grid of size 2 x n, where grid[r][c] represents the number of points at position (r, c).
 * Two robots are playing a game on this matrix. Both robots initially start at (0, 0) and want to reach (1, n-1).
 * Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).
 * At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from the cells on its path.
 * For all cells (r, c) traversed on the path, grid[r][c] is set to 0.
 * Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path.
 * Note that their paths may intersect with one another.
 * The first robot wants to minimize the number of points collected by the second robot.
 * In contrast, the second robot wants to maximize the number of points it collects.
 * If both robots play optimally, return the number of points collected by the second robot.
 *
 * @author BorisMirage
 * Time: 2021/10/09 19:19
 * Created with IntelliJ IDEA
 */

public class GridGame_2017 {
    /**
     * Prefix sum.
     * The robot can only go right or down each time, and the points at start point only gives to first robot.
     * Hence, the second robot can only go right or go down now to get the max point.
     * If the robot goes down, then it may collect the points from 0 to where 1st robot go down.
     * If the robot goes right, then it may collect the points from next position to the end of the array.
     * Hence, at each point, find the max between the remaining points of top, and the points collected from bottom.
     * This will be the points first robot collect.
     * The min value will be the points to second robot.
     *
     * @param grid given 2D matrix
     * @return the number of points collected by the second robot
     */
    public long gridGame(int[][] grid) {
        long topSum = Arrays.stream(grid[0])
                .asLongStream()
                .sum();
        long bottomSum = 0, score = Long.MAX_VALUE;

        for (int i = 0; i < grid[0].length; i++) {
            topSum -= grid[0][i];
            score = Math.min(score, Math.max(topSum, bottomSum));
            bottomSum += grid[1][i];
        }

        return score;
    }
}
