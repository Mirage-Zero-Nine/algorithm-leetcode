package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Vector2D251Test {

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
}
