package solutions.design;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link SubrectangleQueries_1476}.
 */
public class SubrectangleQueries_1476Test {

    @Test
    public void testInitialValues() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        assertEquals(1, sq.getValue(0, 0));
        assertEquals(2, sq.getValue(0, 1));
        assertEquals(3, sq.getValue(0, 2));
        assertEquals(4, sq.getValue(1, 0));
        assertEquals(5, sq.getValue(1, 1));
        assertEquals(6, sq.getValue(1, 2));
        assertEquals(7, sq.getValue(2, 0));
        assertEquals(8, sq.getValue(2, 1));
        assertEquals(9, sq.getValue(2, 2));
    }

    @Test
    public void testSingleUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 2, 2, 5);
        assertEquals(5, sq.getValue(0, 0));
        assertEquals(5, sq.getValue(1, 1));
        assertEquals(5, sq.getValue(2, 2));
    }

    @Test
    public void testPartialUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 1, 1, 10);
        assertEquals(10, sq.getValue(0, 0));
        assertEquals(10, sq.getValue(1, 1));
        assertEquals(3, sq.getValue(0, 2));  // not updated
        assertEquals(7, sq.getValue(2, 0));  // not updated
    }

    @Test
    public void testMultipleUpdates() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 1, 1, 10);
        sq.updateSubrectangle(1, 1, 2, 2, 20);
        assertEquals(10, sq.getValue(0, 0));  // first update
        assertEquals(20, sq.getValue(1, 1));  // second update overrides
        assertEquals(20, sq.getValue(2, 2));  // second update
        assertEquals(3, sq.getValue(0, 2));   // not updated
    }

    @Test
    public void testUpdateOverlapping() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 2, 2, 1);
        sq.updateSubrectangle(0, 0, 1, 1, 2);
        assertEquals(2, sq.getValue(0, 0));  // latest update
        assertEquals(1, sq.getValue(2, 2));  // only in first update
    }

    @Test
    public void testSingleCellUpdate() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 0, 0, 99);
        assertEquals(99, sq.getValue(0, 0));
        assertEquals(2, sq.getValue(0, 1));
        assertEquals(3, sq.getValue(1, 0));
        assertEquals(4, sq.getValue(1, 1));
    }

    @Test
    public void testRowUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 0, 2, 7);
        assertEquals(7, sq.getValue(0, 0));
        assertEquals(7, sq.getValue(0, 1));
        assertEquals(7, sq.getValue(0, 2));
        assertEquals(4, sq.getValue(1, 0));  // not updated
    }

    @Test
    public void testColumnUpdate() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 1, 1, 1, 8);
        assertEquals(1, sq.getValue(0, 0));  // not updated (col 0)
        assertEquals(8, sq.getValue(0, 1));  // updated
        assertEquals(3, sq.getValue(0, 2));  // not updated (col 2)
        assertEquals(4, sq.getValue(1, 0));  // not updated (col 0)
        assertEquals(8, sq.getValue(1, 1));  // updated
        assertEquals(6, sq.getValue(1, 2));  // not updated (col 2)
    }

    @Test
    public void testGetValueAfterNoUpdate() {
        int[][] matrix = {
            {10, 20},
            {30, 40}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        assertEquals(10, sq.getValue(0, 0));
        assertEquals(40, sq.getValue(1, 1));
    }

    @Test
    public void testMultipleOverlappingUpdates() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        SubrectangleQueries_1476 sq = new SubrectangleQueries_1476(matrix);
        sq.updateSubrectangle(0, 0, 1, 1, 1);
        sq.updateSubrectangle(0, 0, 0, 0, 2);
        sq.updateSubrectangle(1, 1, 1, 1, 3);
        assertEquals(2, sq.getValue(0, 0));
        assertEquals(1, sq.getValue(0, 1));
        assertEquals(1, sq.getValue(1, 0));
        assertEquals(3, sq.getValue(1, 1));
    }
}
