package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ZigzagIterator281Test {

    @Test
    public void testHappyCases() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(1, 2), List.of(3, 4, 5, 6));
        assertEquals(1, zi.next());
        assertEquals(3, zi.next());
        assertEquals(2, zi.next());
        assertEquals(4, zi.next());
        assertTrue(zi.hasNext());
    }

    @Test
    public void testEdgeCases() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(1), List.of(2));
        assertEquals(1, zi.next());
        assertEquals(2, zi.next());
        assertFalse(zi.hasNext());
    }

    @Test
    public void testLargeCase() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(1, 2, 3), List.of(4, 5, 6));
        for (int i = 0; i < 6; i++) zi.next();
        assertFalse(zi.hasNext());
    }
}
