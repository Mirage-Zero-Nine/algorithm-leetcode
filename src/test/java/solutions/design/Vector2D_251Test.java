package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Vector2D_251Test {

    @Test
    public void testHappyCases() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1, 2, 3}, {4}, {5}});
        assertEquals(1, v.next());
        assertEquals(2, v.next());
        assertEquals(3, v.next());
        assertTrue(v.hasNext());
        assertEquals(4, v.next());
        assertEquals(5, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}});
        assertFalse(v.hasNext());
        Vector2D_251 v2 = new Vector2D_251(new int[][]{{1}});
        assertTrue(v2.hasNext());
        assertEquals(1, v2.next());
    }

    @Test
    public void testLargeCase() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        for (int i = 1; i <= 9; i++) assertEquals(i, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testEmptyArray() {
        Vector2D_251 v = new Vector2D_251(new int[][]{});
        assertFalse(v.hasNext());
    }

    @Test
    public void testMultipleEmptyRows() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}, {}, {1}, {}});
        assertTrue(v.hasNext());
        assertEquals(1, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testSingleElement() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{42}});
        assertTrue(v.hasNext());
        assertEquals(42, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testAllEmptyRows() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}, {}, {}});
        assertFalse(v.hasNext());
    }

    @Test
    public void testHasNextMultipleCalls() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{1, 2}});
        assertTrue(v.hasNext());
        assertTrue(v.hasNext());
        assertEquals(1, v.next());
        assertTrue(v.hasNext());
        assertTrue(v.hasNext());
        assertEquals(2, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testNegativeValues() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{-1, -2}, {-3}});
        assertEquals(-1, v.next());
        assertEquals(-2, v.next());
        assertEquals(-3, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testEmptyRowsAtStart() {
        Vector2D_251 v = new Vector2D_251(new int[][]{{}, {}, {5, 6}});
        assertTrue(v.hasNext());
        assertEquals(5, v.next());
        assertEquals(6, v.next());
        assertFalse(v.hasNext());
    }

    @Test
    public void testGiantCase() {
        int[][] data = new int[100][100];
        int val = 0;
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                data[i][j] = val++;
        Vector2D_251 v = new Vector2D_251(data);
        for (int i = 0; i < 10000; i++) {
            assertTrue(v.hasNext());
            assertEquals(i, v.next());
        }
        assertFalse(v.hasNext());
    }
}
