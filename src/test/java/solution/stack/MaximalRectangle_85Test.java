package solution.stack;

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
}
