package solution.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FindShortestWay_499Test {
    private final FindShortestWay_499 solution = new FindShortestWay_499();

    @Test
    void testBasic() {
        int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int[] ball = {4,3};
        int[] hole = {0,1};
        String result = solution.findShortestWay(maze, ball, hole);
        assertEquals("lul", result);
    }

    @Test
    void testNoPath() {
        int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int[] ball = {4,3};
        int[] hole = {3,0};
        String result = solution.findShortestWay(maze, ball, hole);
        assertEquals("impossible", result);
    }

    @Test
    void testSimple() {
        int[][] maze = {{0,0},{0,0}};
        int[] ball = {0,0};
        int[] hole = {1,1};
        String result = solution.findShortestWay(maze, ball, hole);
        assertEquals("dr", result);
    }

    @Test
    void testSamePosition() {
        int[][] maze = {{0,0},{0,0}};
        int[] ball = {0,0};
        int[] hole = {0,0};
        String result = solution.findShortestWay(maze, ball, hole);
        assertEquals("", result);
    }

    @Test
    void testLargerMaze() {
        int[][] maze = {{0,0,0},{0,0,0},{0,0,0}};
        int[] ball = {0,0};
        int[] hole = {2,2};
        String result = solution.findShortestWay(maze, ball, hole);
        assertEquals("dr", result);
    }

    @Test
    void testHoleOnPath() {
        int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int[] ball = {4,3};
        int[] hole = {0,1};
        assertEquals("lul", solution.findShortestWay(maze, ball, hole));
    }

    @Test
    void testSurroundedByWalls() {
        int[][] maze = {{1,1,1},{1,0,1},{1,1,1}};
        int[] ball = {1,1};
        int[] hole = {1,1};
        assertEquals("", solution.findShortestWay(maze, ball, hole));
    }

    @Test
    void testHorizontalRoll() {
        int[][] maze = {{0,0,0,0,0}};
        int[] ball = {0,0};
        int[] hole = {0,4};
        assertEquals("r", solution.findShortestWay(maze, ball, hole));
    }

    @Test
    void testVerticalRoll() {
        int[][] maze = {{0},{0},{0},{0},{0}};
        int[] ball = {0,0};
        int[] hole = {4,0};
        assertEquals("d", solution.findShortestWay(maze, ball, hole));
    }

    @Test
    void testGiantMaze() {
        int[][] maze = new int[10][10]; // all zeros
        int[] ball = {0,0};
        int[] hole = {9,9};
        String result = solution.findShortestWay(maze, ball, hole);
        assertNotNull(result);
        assertFalse(result.equals("impossible"));
    }
}
