package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.TreeMap;

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

    @Test
    public void testQueryRangeOnEmpty() {
        RangeModule_715 rm = new RangeModule_715();
        assertFalse(rm.queryRange(0, 1));
        assertFalse(rm.queryRange(1, 1000000));
        assertFalse(rm.queryRange(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testAddRangeThenQueryExact() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(10, 20);
        assertTrue(rm.queryRange(10, 20));
        rm.addRange(100, 200);
        assertTrue(rm.queryRange(100, 200));
    }

    @Test
    public void testQuerySmallerWithinRange() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 100);
        assertTrue(rm.queryRange(10, 20));
        assertTrue(rm.queryRange(1, 2));
        assertTrue(rm.queryRange(99, 100));
        assertTrue(rm.queryRange(50, 51));
    }

    @Test
    public void testQueryLargerThanRange() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(10, 20);
        assertFalse(rm.queryRange(9, 20));
        assertFalse(rm.queryRange(10, 21));
        assertFalse(rm.queryRange(5, 25));
    }

    @Test
    public void testRemoveRangeSplitsExisting() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 100);
        rm.removeRange(40, 60);
        assertTrue(rm.queryRange(1, 40));
        assertTrue(rm.queryRange(60, 100));
        assertFalse(rm.queryRange(39, 61));
        assertFalse(rm.queryRange(40, 41));
        assertFalse(rm.queryRange(59, 60));
    }

    @Test
    public void testRemoveRangeTouchesBoundary() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(10, 20);
        rm.removeRange(10, 15);
        assertFalse(rm.queryRange(10, 11));
        assertTrue(rm.queryRange(15, 20));
        rm.removeRange(18, 20);
        assertTrue(rm.queryRange(15, 18));
        assertFalse(rm.queryRange(18, 19));
    }

    @Test
    public void testAdjacentAddRangesMerge() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 5);
        rm.addRange(5, 10);
        rm.addRange(10, 15);
        assertTrue(rm.queryRange(1, 15));
        assertTrue(rm.queryRange(4, 11));
    }

    @Test
    public void testMultipleDisjointAddRangesThenQueries() {
        RangeModule_715 rm = new RangeModule_715();
        rm.addRange(1, 3);
        rm.addRange(10, 12);
        rm.addRange(20, 22);
        rm.addRange(30, 32);
        assertTrue(rm.queryRange(1, 3));
        assertTrue(rm.queryRange(10, 12));
        assertTrue(rm.queryRange(20, 22));
        assertTrue(rm.queryRange(30, 32));
        assertFalse(rm.queryRange(3, 10));
        assertFalse(rm.queryRange(12, 20));
        assertFalse(rm.queryRange(1, 32));
    }

    @Test
    public void testRemoveRangeOnNeverAddedIsNoOp() {
        RangeModule_715 rm = new RangeModule_715();
        rm.removeRange(5, 10);
        assertFalse(rm.queryRange(5, 10));
        rm.addRange(20, 30);
        rm.removeRange(50, 60);
        assertTrue(rm.queryRange(20, 30));
        assertFalse(rm.queryRange(50, 60));
    }

    @Test
    public void testStressRandomCrossCheckWithTreeMap() {
        RangeModule_715 rm = new RangeModule_715();
        TreeMap<Integer, Integer> ref = new TreeMap<>();
        Random rng = new Random(42L);
        int bound = 200;

        for (int i = 0; i < 5000; i++) {
            int a = rng.nextInt(bound);
            int b = a + 1 + rng.nextInt(20);
            int op = rng.nextInt(3);
            if (op == 0) {
                rm.addRange(a, b);
                refAdd(ref, a, b);
            } else if (op == 1) {
                rm.removeRange(a, b);
                refRemove(ref, a, b);
            } else {
                boolean expected = refQuery(ref, a, b);
                if (expected) {
                    assertTrue(rm.queryRange(a, b), "query [" + a + "," + b + ") should be true at i=" + i);
                } else {
                    assertFalse(rm.queryRange(a, b), "query [" + a + "," + b + ") should be false at i=" + i);
                }
            }
        }
    }

    private void refAdd(TreeMap<Integer, Integer> map, int left, int right) {
        Integer s = map.floorKey(left), e = map.floorKey(right);
        if (s != null && map.get(s) >= left) left = s;
        if (e != null && map.get(e) > right) right = map.get(e);
        map.put(left, right);
        map.subMap(left, false, right, true).clear();
    }

    private void refRemove(TreeMap<Integer, Integer> map, int left, int right) {
        Integer s = map.floorKey(left), e = map.floorKey(right);
        if (e != null && map.get(e) > right) map.put(right, map.get(e));
        if (s != null && map.get(s) > left) map.put(s, left);
        map.subMap(left, true, right, false).clear();
    }

    private boolean refQuery(TreeMap<Integer, Integer> map, int left, int right) {
        Integer s = map.floorKey(left);
        return s != null && map.get(s) >= right;
    }
}
