package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.BinaryMatrix;
import org.junit.jupiter.api.Test;

public class LeftMostColumnWithOne1428Test {

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
}
