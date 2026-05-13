package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SearchMatrix_74Test {

    private final SearchMatrix_74 test = new SearchMatrix_74();

    @Test
    public void testHappyCases() {
        assertTrue(test.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        assertTrue(test.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 60));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        assertFalse(test.searchMatrix(new int[][]{}, 1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.searchMatrix(new int[][]{{1}, {3}, {5}, {7}, {9}}, 7));
        assertFalse(test.searchMatrix(new int[][]{{1}, {3}, {5}, {7}, {9}}, 6));
    }

    @Test
    public void testSingleElementFound() {
        assertTrue(test.searchMatrix(new int[][]{{42}}, 42));
    }

    @Test
    public void testSingleElementNotFound() {
        assertFalse(test.searchMatrix(new int[][]{{42}}, 41));
    }

    @Test
    public void testTargetBeforeMinimum() {
        assertFalse(test.searchMatrix(new int[][]{{5, 6}, {7, 8}}, 4));
    }

    @Test
    public void testTargetAfterMaximum() {
        assertFalse(test.searchMatrix(new int[][]{{5, 6}, {7, 8}}, 9));
    }

    @Test
    public void testEmptyRowMatrix() {
        assertFalse(test.searchMatrix(new int[][]{{}}, 1));
    }

    @Test
    public void testFindFirstElement() {
        assertTrue(test.searchMatrix(new int[][]{{2, 4, 6}, {8, 10, 12}}, 2));
    }

    @Test
    public void testGiantCase() {
        int rows = 50;
        int cols = 50;
        int[][] matrix = new int[rows][cols];
        int value = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }
        assertTrue(test.searchMatrix(matrix, 2500));
    }
}
