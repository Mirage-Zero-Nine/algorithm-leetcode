package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaximalRectangle_85Test {
    private final MaximalRectangle_85 solver = new MaximalRectangle_85();

    @Test public void testExample() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        assertEquals(6, solver.maximalRectangle(matrix));
    }

    @Test public void testAllOnes() {
        char[][] matrix = {{'1', '1'}, {'1', '1'}};
        assertEquals(4, solver.maximalRectangle(matrix));
    }

    @Test public void testAllZeros() {
        char[][] matrix = {{'0', '0'}, {'0', '0'}};
        assertEquals(0, solver.maximalRectangle(matrix));
    }

    @Test public void testSingleRow() {
        char[][] matrix = {{'1', '1', '1', '0', '1'}};
        assertEquals(3, solver.maximalRectangle(matrix));
    }

    @Test public void testEmpty() {
        assertEquals(0, solver.maximalRectangle(new char[][]{}));
    }

    @Test public void testSingleCell() {
        assertEquals(1, solver.maximalRectangle(new char[][]{{'1'}}));
    }

    // Additional happy cases
    @Test public void testSingleColumn() {
        char[][] matrix = {{'1'}, {'1'}, {'1'}, {'0'}, {'1'}};
        assertEquals(3, solver.maximalRectangle(matrix));
    }

    @Test public void testLShape() {
        char[][] matrix = {
                {'1', '0'},
                {'1', '0'},
                {'1', '1'}
        };
        assertEquals(3, solver.maximalRectangle(matrix));
    }

    // Negative case: single zero cell
    @Test public void testSingleZeroCell() {
        assertEquals(0, solver.maximalRectangle(new char[][]{{'0'}}));
    }

    // Edge case: empty columns
    @Test public void testEmptyColumns() {
        assertEquals(0, solver.maximalRectangle(new char[][]{new char[0]}));
    }

    // Giant test case
    @Test public void testGiant() {
        int n = 100;
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = '1';
        assertEquals(10000, solver.maximalRectangle(matrix));
    }
}
