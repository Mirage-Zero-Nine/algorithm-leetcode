package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class PeekingIterator_284Test {

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

    @Test
    public void testPeekMultipleTimes() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(10, 20, 30).iterator());
        assertEquals(10, iter.peek());
        assertEquals(10, iter.peek());
        assertEquals(10, iter.peek());
        assertEquals(10, iter.next());
        assertEquals(20, iter.peek());
    }

    @Test
    public void testNextWithoutPeek() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(5, 6, 7).iterator());
        assertEquals(5, iter.next());
        assertEquals(6, iter.next());
        assertEquals(7, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextDoesNotAdvance() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2).iterator());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals(1, iter.peek());
    }

    @Test
    public void testEmptyIterator() {
        PeekingIterator_284 iter = new PeekingIterator_284(Collections.<Integer>emptyIterator());
        assertFalse(iter.hasNext());
        assertNull(iter.peek());
        assertNull(iter.next());
    }

    @Test
    public void testNegativeValues() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(-1, -2, -3).iterator());
        assertEquals(-1, iter.peek());
        assertEquals(-1, iter.next());
        assertEquals(-2, iter.next());
        assertEquals(-3, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testTwoElements() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(100, 200).iterator());
        assertEquals(100, iter.peek());
        assertEquals(100, iter.next());
        assertEquals(200, iter.peek());
        assertEquals(200, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testGiantCase() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) list.add(i);
        PeekingIterator_284 iter = new PeekingIterator_284(list.iterator());
        for (int i = 0; i < 10000; i++) {
            assertTrue(iter.hasNext());
            assertEquals(i, iter.peek());
            assertEquals(i, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testAlternatingPeekAndNext() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2, 3, 4).iterator());
        assertEquals(1, iter.peek());
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.peek());
        assertEquals(3, iter.peek());
        assertEquals(3, iter.next());
        assertEquals(4, iter.next());
        assertFalse(iter.hasNext());
    }
}
