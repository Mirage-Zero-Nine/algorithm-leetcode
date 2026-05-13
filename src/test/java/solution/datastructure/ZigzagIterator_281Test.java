package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ZigzagIterator_281Test {

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

    @Test
    public void testFirstListLonger() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(1, 2, 3, 4), List.of(5));
        assertEquals(1, zi.next());
        assertEquals(5, zi.next());
        assertEquals(2, zi.next());
        assertEquals(3, zi.next());
        assertEquals(4, zi.next());
        assertFalse(zi.hasNext());
    }

    @Test
    public void testSecondListLonger() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(1), List.of(2, 3, 4, 5));
        assertEquals(1, zi.next());
        assertEquals(2, zi.next());
        assertEquals(3, zi.next());
        assertEquals(4, zi.next());
        assertEquals(5, zi.next());
        assertFalse(zi.hasNext());
    }

    @Test
    public void testEqualLengthLists() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(1, 3, 5), List.of(2, 4, 6));
        assertEquals(1, zi.next());
        assertEquals(2, zi.next());
        assertEquals(3, zi.next());
        assertEquals(4, zi.next());
        assertEquals(5, zi.next());
        assertEquals(6, zi.next());
        assertFalse(zi.hasNext());
    }

    @Test
    public void testEmptyFirstList() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(), List.of(1, 2, 3));
        assertEquals(1, zi.next());
        assertEquals(2, zi.next());
        assertEquals(3, zi.next());
        assertFalse(zi.hasNext());
    }

    @Test
    public void testEmptySecondList() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(1, 2, 3), List.of());
        assertEquals(1, zi.next());
        assertEquals(2, zi.next());
        assertEquals(3, zi.next());
        assertFalse(zi.hasNext());
    }

    @Test
    public void testBothEmpty() {
        ZigzagIterator_281 zi = new ZigzagIterator_281(List.of(), List.of());
        assertFalse(zi.hasNext());
    }

    @Test
    public void testGiantCase() {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            l1.add(i);
            l2.add(i + 1000);
        }
        ZigzagIterator_281 zi = new ZigzagIterator_281(l1, l2);
        int count = 0;
        while (zi.hasNext()) {
            zi.next();
            count++;
        }
        assertEquals(2000, count);
    }
}
