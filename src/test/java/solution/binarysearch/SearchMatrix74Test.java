package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SearchMatrix74Test {

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
}
