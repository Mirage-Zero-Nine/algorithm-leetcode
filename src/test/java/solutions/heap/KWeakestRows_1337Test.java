package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class KWeakestRows_1337Test {

    private final KWeakestRows_1337 test = new KWeakestRows_1337();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{2, 0, 3}, test.kWeakestRows(new int[][]{
            {1, 1, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1}
        }, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.kWeakestRows(new int[][]{{0, 0}, {1, 1}}, 1));
        assertArrayEquals(new int[]{0, 1}, test.kWeakestRows(new int[][]{{1}, {1}}, 2));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 1, 2, 3}, test.kWeakestRows(new int[][]{
            {0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1}
        }, 4));
    }

    @Test
    public void testAllZeros() {
        assertArrayEquals(new int[]{0, 1, 2}, test.kWeakestRows(new int[][]{
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }, 3));
    }

    @Test
    public void testAllOnes() {
        assertArrayEquals(new int[]{0, 1}, test.kWeakestRows(new int[][]{
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        }, 2));
    }

    @Test
    public void testSingleRow() {
        assertArrayEquals(new int[]{0}, test.kWeakestRows(new int[][]{{1, 0, 0}}, 1));
    }

    @Test
    public void testKEqualsRowCount() {
        assertArrayEquals(new int[]{1, 0, 2}, test.kWeakestRows(new int[][]{
            {1, 0, 0},
            {0, 0, 0},
            {1, 1, 0}
        }, 3));
    }

    @Test
    public void testTieBreakByIndex() {
        // Rows 0 and 1 have same soldiers, row 0 is weaker (lower index)
        assertArrayEquals(new int[]{0, 1}, test.kWeakestRows(new int[][]{
            {1, 0},
            {1, 0},
            {1, 1}
        }, 2));
    }

    @Test
    public void testSingleColumn() {
        assertArrayEquals(new int[]{1, 3}, test.kWeakestRows(new int[][]{
            {1},
            {0},
            {1},
            {0}
        }, 2));
    }

    @Test
    public void testGiantCase() {
        int[][] mat = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < i && j < 100; j++) {
                mat[i][j] = 1;
            }
        }
        int[] result = test.kWeakestRows(mat, 5);
        // Row 0 has 0 soldiers, row 1 has 1, etc.
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, result);
    }

    @Test
    public void testMixedPattern() {
        assertArrayEquals(new int[]{0, 2}, test.kWeakestRows(new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 1, 1, 0}
        }, 2));
    }
}
