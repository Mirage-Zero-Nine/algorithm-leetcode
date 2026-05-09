package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link GridGame_2017}.
 */
public class GridGame_2017Test {

    private final GridGame_2017 solver = new GridGame_2017();

    @Test
    public void testLeetCodeExample1() {
        // grid = [[2,5],[4,3]]
        // Robot1 goes down at col 0: path (0,0)→(1,0)→(1,1), collects 2+4+3=9
        // grid becomes [[0,5],[0,0]]
        // Robot2 goes down at col 1: path (0,0)→(0,1)→(1,1), collects 0+5+0=5
        // Or robot2 goes down at col 0: path (0,0)→(1,0)→(1,1), collects 0+0+0=0
        // Robot2 maximizes: 5
        // Robot1 goes down at col 1: path (0,0)→(0,1)→(1,1), collects 2+5+3=10
        // grid becomes [[0,0],[4,0]]
        // Robot2 goes down at col 0: path (0,0)→(1,0)→(1,1), collects 0+4+0=4
        // Robot1 minimizes: min(5, 4) = 4
        int[][] grid = {{2, 5}, {4, 3}};
        assertEquals(4L, solver.gridGame(grid));
    }

    @Test
    public void testLeetCodeExample2() {
        // grid = [[3,3,1],[8,5,2]]
        int[][] grid = {{3, 3, 1}, {8, 5, 2}};
        assertEquals(4L, solver.gridGame(grid));
    }

    @Test
    public void testLeetCodeExample3() {
        // grid = [[1,3,1,15],[1,3,3,1]]
        int[][] grid = {{1, 3, 1, 15}, {1, 3, 3, 1}};
        assertEquals(7L, solver.gridGame(grid));
    }

    @Test
    public void testTwoColumns() {
        // grid = [[1,1],[1,1]]
        // topSum = 2
        // i=0: topSum=1, score=min(∞, max(1,0))=1, bottomSum=1
        // i=1: topSum=0, score=min(1, max(0,1))=1, bottomSum=2
        int[][] grid = {{1, 1}, {1, 1}};
        assertEquals(1L, solver.gridGame(grid));
    }

    @Test
    public void testSingleColumn() {
        // grid = [[1],[1]]
        // topSum = 1
        // i=0: topSum=0, score=min(∞, max(0,0))=0, bottomSum=1
        int[][] grid = {{1}, {1}};
        assertEquals(0L, solver.gridGame(grid));
    }

    @Test
    public void testAllZeros() {
        // grid = [[0,0],[0,0]]
        int[][] grid = {{0, 0}, {0, 0}};
        assertEquals(0L, solver.gridGame(grid));
    }

    @Test
    public void testLargeTopRow() {
        // grid = [[10,10,10],[1,1,1]]
        // topSum = 30
        // i=0: topSum=20, score=min(∞, max(20,0))=20, bottomSum=1
        // i=1: topSum=10, score=min(20, max(10,1))=10, bottomSum=2
        // i=2: topSum=0, score=min(10, max(0,2))=2, bottomSum=3
        int[][] grid = {{10, 10, 10}, {1, 1, 1}};
        assertEquals(2L, solver.gridGame(grid));
    }

    @Test
    public void testLargeBottomRow() {
        // grid = [[1,1,1],[10,10,10]]
        // topSum = 3
        // i=0: topSum=2, score=min(∞, max(2,0))=2, bottomSum=10
        // i=1: topSum=1, score=min(2, max(1,10))=2, bottomSum=20
        // i=2: topSum=0, score=min(2, max(0,20))=2, bottomSum=30
        int[][] grid = {{1, 1, 1}, {10, 10, 10}};
        assertEquals(2L, solver.gridGame(grid));
    }

    @Test
    public void testWideGrid() {
        // grid = [[1,2,3,4],[5,6,7,8]]
        // topSum = 10
        // i=0: topSum=9, score=9, bottomSum=5
        // i=1: topSum=7, score=min(9,max(7,5))=7, bottomSum=11
        // i=2: topSum=4, score=min(7,max(4,11))=7, bottomSum=18
        // i=3: topSum=0, score=min(7,max(0,18))=7, bottomSum=26
        int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        assertEquals(7L, solver.gridGame(grid));
    }

    @Test
    public void testSymmetricGrid() {
        // grid = [[1,2,1],[1,2,1]]
        // topSum = 4
        // i=0: topSum=3, score=3, bottomSum=1
        // i=1: topSum=1, score=min(3,max(1,1))=1, bottomSum=3
        // i=2: topSum=0, score=min(1,max(0,3))=1, bottomSum=4
        int[][] grid = {{1, 2, 1}, {1, 2, 1}};
        assertEquals(1L, solver.gridGame(grid));
    }
}
