package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SearchMatrix240Test {

    private final SearchMatrix_240 test = new SearchMatrix_240();

    @Test
    public void testHappyCases() {
        assertTrue(test.searchMatrix(new int[][]{{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}, {10, 13, 14, 17}}, 5));
        assertTrue(test.searchMatrix(new int[][]{{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}, {10, 13, 14, 17}}, 17));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.searchMatrix(new int[][]{{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}, {10, 13, 14, 17}}, 20));
        assertFalse(test.searchMatrix(new int[][]{}, 1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.searchMatrix(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}}, 13));
    }
}
