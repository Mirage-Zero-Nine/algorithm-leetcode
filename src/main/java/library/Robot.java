package library;

/**
 * 4 given APIs for Robot interface. Robot can move forward, turn left or turn right. Each turn it made is 90 degrees.
 *
 * @author BorisMirage
 * Time: 2019/06/04 15:43
 * Created with IntelliJ IDEA
 */

public interface Robot {
    /**
     * Returns true if next cell is open and robot moves into the cell.
     * Return false if next cell is obstacle and robot stays on the current cell.
     *
     * @return returns true if next cell is open , false if next cell is obstacle
     */
    boolean move();

    /**
     * Robot will stay on the same cell after calling turnLeft.
     * Each turn will be 90 degrees.
     */
    void turnLeft();

    /**
     * Robot will stay on the same cell after calling turnRight.
     * Each turn will be 90 degrees.
     */
    void turnRight();

    /**
     * Clean the current cell.
     */
    void clean();
}

