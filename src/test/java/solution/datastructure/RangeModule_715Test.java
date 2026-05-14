package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RangeModule_715Test {

    @Test
    public void testHappyCases() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        assertFalse(rm.queryRange(13, 15));
        assertTrue(rm.queryRange(16, 17));
        assertTrue(rm.queryRange(10, 14));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        RangeModule_715 rm = new RangeModule_715();
        assertFalse(rm.queryRange(1, 10));
        rm.addRange(1, 10);
        assertTrue(rm.queryRange(1, 10));
        rm.removeRange(1, 10);
        assertFalse(rm.queryRange(1, 10));
    }

    @Test
    public void testLargeCase() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 100);
        assertTrue(rm.queryRange(1, 100));
        rm.removeRange(50, 60);
        assertFalse(rm.queryRange(45, 65));
        assertTrue(rm.queryRange(1, 50));
    }

    @Test
    public void testMergeOverlappingRanges() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 5);
        rm.addRange(3, 8);
        assertTrue(rm.queryRange(1, 8));
        assertTrue(rm.queryRange(4, 6));
    }

    @Test
    public void testAdjacentRanges() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 5);
        rm.addRange(5, 10);
        assertTrue(rm.queryRange(1, 10));
    }

    @Test
    public void testDisjointRanges() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 3);
        rm.addRange(5, 7);
        assertFalse(rm.queryRange(1, 7));
        assertTrue(rm.queryRange(1, 3));
        assertTrue(rm.queryRange(5, 7));
        assertFalse(rm.queryRange(3, 5));
    }

    @Test
    public void testRemovePartialRange() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 10);
        rm.removeRange(3, 7);
        assertTrue(rm.queryRange(1, 3));
        assertTrue(rm.queryRange(7, 10));
        assertFalse(rm.queryRange(1, 10));
        assertFalse(rm.queryRange(3, 7));
    }

    @Test
    public void testRemoveEntireRange() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(5, 15);
        rm.removeRange(5, 15);
        assertFalse(rm.queryRange(5, 15));
        assertFalse(rm.queryRange(5, 6));
    }

    @Test
    public void testQuerySinglePoint() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 10);
        assertTrue(rm.queryRange(5, 6));
        assertTrue(rm.queryRange(1, 2));
        assertTrue(rm.queryRange(9, 10));
    }

    @Test
    public void testAddAfterRemove() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 20);
        rm.removeRange(5, 15);
        rm.addRange(8, 12);
        assertTrue(rm.queryRange(8, 12));
        assertFalse(rm.queryRange(5, 8));
        assertFalse(rm.queryRange(12, 15));
    }

    @Test
    public void testGiantCase() {
        RangeModule_715 rm = new RangeModule_715();
        for (int i = 0; i < 1000; i++) {
            rm.addRange(i * 10, i * 10 + 5);
        }
        for (int i = 0; i < 1000; i++) {
            assertTrue(rm.queryRange(i * 10, i * 10 + 5));
            assertFalse(rm.queryRange(i * 10, i * 10 + 8));
        }
        for (int i = 0; i < 500; i++) {
            rm.removeRange(i * 10, i * 10 + 5);
        }
        for (int i = 0; i < 500; i++) {
            assertFalse(rm.queryRange(i * 10, i * 10 + 5));
        }
        for (int i = 500; i < 1000; i++) {
            assertTrue(rm.queryRange(i * 10, i * 10 + 5));
        }
    }
}
