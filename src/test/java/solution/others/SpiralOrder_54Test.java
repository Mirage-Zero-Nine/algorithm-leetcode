package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SpiralOrder_54Test {

    private final SpiralOrder_54 test = new SpiralOrder_54();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5),
            test.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.spiralOrder(new int[][]{}));
        assertEquals(List.of(1), test.spiralOrder(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
            test.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(List.of(1, 2, 3, 4), test.spiralOrder(new int[][]{{1, 2, 3, 4}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(List.of(1, 2, 3), test.spiralOrder(new int[][]{{1}, {2}, {3}}));
    }

    @Test
    public void testTwoByTwo() {
        assertEquals(List.of(1, 2, 4, 3), test.spiralOrder(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testTallMatrix() {
        assertEquals(List.of(1, 2, 4, 6, 5, 3),
            test.spiralOrder(new int[][]{{1, 2}, {3, 4}, {5, 6}}));
    }

    @Test
    public void testFourByFour() {
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10),
            test.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}));
    }

    @Test
    public void testOneByTwo() {
        assertEquals(List.of(1, 2), test.spiralOrder(new int[][]{{1, 2}}));
    }

    @Test
    public void testGiantMatrix() {
        int n = 50;
        int[][] mat = new int[n][n];
        int val = 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = val++;
        List<Integer> result = test.spiralOrder(mat);
        assertEquals(n * n, result.size());
        assertEquals(1, result.get(0));
        assertEquals(n, result.get(n - 1));
    }
}
