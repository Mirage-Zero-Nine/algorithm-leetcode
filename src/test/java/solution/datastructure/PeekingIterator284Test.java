package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class PeekingIterator284Test {

    @Test
    public void testHappyCases() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2, 3).iterator());
        assertEquals(1, iter.peek());
        assertEquals(1, iter.next());
        assertEquals(2, iter.peek());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testEdgeCases() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1).iterator());
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testLargeCase() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2, 3, 4, 5).iterator());
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, iter.peek());
            assertEquals(i, iter.next());
        }
        assertFalse(iter.hasNext());
    }
}
