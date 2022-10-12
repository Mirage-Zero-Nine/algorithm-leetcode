package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/11 20:51
 * Created with IntelliJ IDEA
 */

public class NearestExit1926Test {

    private final NearestExit_1926 test = new NearestExit_1926();

    @Test
    public void test() {
        char[][] maze = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
        assertEquals(1, test.nearestExit(maze, new int[]{1, 2}));
        maze = new char[][]{{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
        assertEquals(2, test.nearestExit(maze, new int[]{1, 0}));
    }

    @Test
    public void testInvalid() {
        char[][] maze = {{'.', '+'}};
        assertEquals(-1, test.nearestExit(maze, new int[]{0, 0}));
    }
}