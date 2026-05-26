package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.BinaryMatrix;
import org.junit.jupiter.api.Test;

public class LeftMostColumnWithOne_1428Test {

    private final LeftMostColumnWithOne_1428 test = new LeftMostColumnWithOne_1428();

    private BinaryMatrix buildMatrix(int[][] mat) {
        return new BinaryMatrix() {
            @Override
            public int get(int row, int col) { return mat[row][col]; }
            @Override
            public List<Integer> dimensions() { return List.of(mat.length, mat[0].length); }
        };
    }

    @Test
    public void testHappyCases() {
        assertEquals(0, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0, 0}, {1, 1}})));
        assertEquals(1, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0, 0}, {0, 1}})));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0, 0}, {0, 0}})));
        assertEquals(0, test.leftMostColumnWithOne(buildMatrix(new int[][]{{1}})));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 1}})));
    }

    @Test
    public void testSingleColumnAllZeros() {
        assertEquals(-1, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0}, {0}, {0}})));
    }

    @Test
    public void testSingleColumnAllOnes() {
        assertEquals(0, test.leftMostColumnWithOne(buildMatrix(new int[][]{{1}, {1}, {1}})));
    }

    @Test
    public void testSingleRowAllZeros() {
        assertEquals(-1, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0, 0, 0, 0}})));
    }

    @Test
    public void testSingleRowOneAtEnd() {
        assertEquals(3, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0, 0, 0, 1}})));
    }

    @Test
    public void testAllOnesMatrix() {
        assertEquals(0, test.leftMostColumnWithOne(buildMatrix(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}})));
    }

    @Test
    public void testLeftMostInLastRow() {
        assertEquals(0, test.leftMostColumnWithOne(buildMatrix(new int[][]{{0, 0, 0}, {0, 0, 1}, {1, 1, 1}})));
    }

    @Test
    public void testGiantMatrix() {
        int rows = 100, cols = 100;
        int[][] mat = new int[rows][cols];
        // Each row i has 1s starting from column (cols - 1 - i) if i < cols, else all 1s
        for (int i = 0; i < rows; i++) {
            int start = Math.max(0, cols - 1 - i);
            for (int j = start; j < cols; j++) {
                mat[i][j] = 1;
            }
        }
        // Row 99 has 1s from column 0, so leftmost is 0
        assertEquals(0, test.leftMostColumnWithOne(buildMatrix(mat)));
    }
}
