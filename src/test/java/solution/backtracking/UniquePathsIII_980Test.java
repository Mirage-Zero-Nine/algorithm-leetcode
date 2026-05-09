package solution.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UniquePathsIII_980Test {
    private final UniquePathsIII_980 solution = new UniquePathsIII_980();

    @Test
    void testBasic() {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        assertEquals(2, solution.uniquePathsIII(grid));
    }

    @Test
    void testSmallGrid() {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,0,2}};
        assertEquals(4, solution.uniquePathsIII(grid));
    }

    @Test
    void testWithObstacles() {
        int[][] grid = {{0,1},{2,0}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }

    @Test
    void testSinglePath() {
        int[][] grid = {{1,2}};
        assertEquals(1, solution.uniquePathsIII(grid));
    }

    @Test
    void testNoPath() {
        int[][] grid = {{1,-1},{-1,2}};
        assertEquals(0, solution.uniquePathsIII(grid));
    }
}
