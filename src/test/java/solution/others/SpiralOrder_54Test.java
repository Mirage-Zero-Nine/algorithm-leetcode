package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    // --- NEW TESTS ---

    @Test
    public void testTwoByThree() {
        // 2 rows x 3 cols
        assertEquals(List.of(1, 2, 3, 6, 5, 4),
            test.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    @Test
    public void testFiveByFourMoreRowsThanCols() {
        // 5 rows x 4 cols
        int[][] mat = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20}
        };
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 16, 20, 19, 18, 17, 13, 9, 5, 6, 7, 11, 15, 14, 10),
            test.spiralOrder(mat));
    }

    @Test
    public void testFourByFiveMoreColsThanRows() {
        // 4 rows x 5 cols
        int[][] mat = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20}
        };
        assertEquals(List.of(1, 2, 3, 4, 5, 10, 15, 20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12),
            test.spiralOrder(mat));
    }

    @Test
    public void testNegativeValues() {
        int[][] mat = {{-1, -2, -3}, {-4, -5, -6}, {-7, -8, -9}};
        assertEquals(List.of(-1, -2, -3, -6, -9, -8, -7, -4, -5),
            test.spiralOrder(mat));
    }

    @Test
    public void testSingleColumnFiveRows() {
        // 5x1 matrix
        assertEquals(List.of(10, 20, 30, 40, 50),
            test.spiralOrder(new int[][]{{10}, {20}, {30}, {40}, {50}}));
    }

    @Test
    public void testSingleRowFiveCols() {
        // 1x5 matrix
        assertEquals(List.of(5, 4, 3, 2, 1),
            test.spiralOrder(new int[][]{{5, 4, 3, 2, 1}}));
    }

    @Test
    public void testThreeByThreeEndsInCenter() {
        // Verify last element is the center
        List<Integer> result = test.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertEquals(5, result.get(result.size() - 1));
    }

    @Test
    public void testPropertyResultLengthEqualsMTimesN() {
        // For various dimensions, result length must equal m * n
        int[][] sizes = {{3, 5}, {5, 3}, {1, 7}, {7, 1}, {4, 4}, {6, 2}};
        for (int[] size : sizes) {
            int m = size[0], n = size[1];
            int[][] mat = new int[m][n];
            int val = 0;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    mat[i][j] = val++;
            assertEquals(m * n, test.spiralOrder(mat).size(),
                "Failed for " + m + "x" + n);
        }
    }

    @Test
    public void testPropertyEveryValueAppearsExactlyOnce() {
        // 5x4 matrix with unique values: result must contain each exactly once
        int m = 5, n = 4;
        int[][] mat = new int[m][n];
        Set<Integer> allValues = new HashSet<>();
        int val = 1;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                mat[i][j] = val;
                allValues.add(val++);
            }
        List<Integer> result = test.spiralOrder(mat);
        assertEquals(allValues.size(), result.size());
        assertTrue(new HashSet<>(result).equals(allValues));
    }

    @Test
    public void testLarge50x50AllValuesPresent() {
        int n = 50;
        int[][] mat = new int[n][n];
        int val = 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = val++;
        List<Integer> result = test.spiralOrder(mat);
        assertEquals(2500, result.size());
        // Every value 1..2500 appears exactly once
        Set<Integer> resultSet = new HashSet<>(result);
        assertEquals(2500, resultSet.size());
        assertTrue(resultSet.contains(1));
        assertTrue(resultSet.contains(2500));
    }
}
