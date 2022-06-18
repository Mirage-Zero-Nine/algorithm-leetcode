package solution.dfs;

import library.Robot;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a robot cleaner in a room modeled as a grid.
 * The input is only given to initialize the room and the robot's position. This robot works as "blindfolded".
 * Each cell in the grid can be empty or blocked.
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 * Robot can only use the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 * Notes:
 * 1. The robot's initial position will always be in an accessible cell.
 * 2. The initial direction of the robot will be facing up.
 * 3. All accessible cells are connected. All cells marked as 1 will be accessible by the robot.
 * 4. Assume all four edges of the grid are all surrounded by wall.
 *
 * @author BorisMirage
 * Time: 2019/06/04 15:44
 * Created with IntelliJ IDEA
 */

public class CleanRoom_489 {
    /**
     * DFS searching, move until not reachable and
     * The way this robot works is that it will move in one direction unless call the turnRight()/turnLeft() to turn.
     *
     * @param robot robot instance with API
     */
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, visited, 0, 0, 0);
    }

    /**
     * DFS searching until all cells in given grid has been visited.
     * If current cell was not visited, then clean current cell and moving toward same direction.
     * If DFS is completed (i.e., cell has been visited or cell is not reachable), then robot move one step back.
     * After move back, change direction and repeat this process.
     * If next cell based on current direction is not reachable, then change direction and keep doing DFS.
     *
     * @param robot   robot object
     * @param visited set store coords has been visited before
     * @param i       coord x
     * @param j       coord y
     * @param toward  direction towarding
     */
    private void dfs(Robot robot, Set<String> visited, int i, int j, int toward) {

        int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        String coord = i + "-" + j;       // record current coord to see if this location has been visited before

        if (visited.contains(coord)) {
            return;
        }
        visited.add(coord);
        robot.clean();

        for (int k = 0; k < 4; k++) {
            if (robot.move()) {     // robot.move() checks availability of next cell based on current direction
                int x = i + d[toward][0];
                int y = j + d[toward][1];
                dfs(robot, visited, x, y, toward);

                /* One step back after DFS is completed */
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnLeft();       // try a new direction if next cell is not reachable
            toward = (toward + 1) % 4;
        }
    }
}
