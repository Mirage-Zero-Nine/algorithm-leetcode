package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2024/12/02 14:42
 * Created with IntelliJ IDEA
 */

public class LRUCache_146Test {
    private LRUCache_146 test;

    @Test
    public void test() {
        test = new LRUCache_146(3);
        test.put(1, 1);
        test.put(2, 2);
        test.put(3, 3);
        test.put(4, 4);
        assertEquals(-1, test.get(1));
        assertEquals(2, test.get(2));
        assertEquals(3, test.get(3));
        assertEquals(4, test.get(4));
        test.put(5, 5);
        assertEquals(5, test.get(5));
    }

    @Test
    public void testInvalid() {
        test = new LRUCache_146(0);
        assertEquals(-1, test.get(1));
        assertEquals(-1, test.get(2));
        assertEquals(-1, test.get(3));
        assertEquals(-1, test.get(4));
        assertEquals(-1, test.get(5));
        test.put(1, 1);
        assertEquals(-1, test.get(1));
        assertEquals(-1, test.get(2));
        assertEquals(-1, test.get(3));
        assertEquals(-1, test.get(4));
        assertEquals(-1, test.get(5));
    }

    @Test
    public void test1() {
        test = new LRUCache_146(2);
        test.put(1, 0);
        test.put(2, 2);
        assertEquals(0, test.get(1));
        test.put(3, 3);
        assertEquals(-1, test.get(2));
        test.put(4, 4);
        assertEquals(-1, test.get(1));
        assertEquals(3, test.get(3));
        assertEquals(4, test.get(4));
    }

    @Test
    public void test2() {
        test = new LRUCache_146(2);
        test.put(2, 1);
        test.put(1, 1);
        test.put(2, 3);
        test.put(4, 1);
        assertEquals(-1, test.get(1));
        assertEquals(3, test.get(2));
    }

    @Test
    public void test3() {
        test = new LRUCache_146(2);
        assertEquals(-1, test.get(1));
        assertEquals(-1, test.get(2));
        test.put(2, 6);
        assertEquals(-1, test.get(1));
        test.put(1, 5);
        test.put(1, 2);
        assertEquals(2, test.get(1));
        assertEquals(6, test.get(2));
    }

    @Test
    public void testCapacityOne() {
        test = new LRUCache_146(1);
        test.put(1, 1);
        assertEquals(1, test.get(1));
        test.put(2, 2);
        assertEquals(-1, test.get(1));
        assertEquals(2, test.get(2));
    }

    @Test
    public void testGetRefreshesOrder() {
        test = new LRUCache_146(2);
        test.put(1, 1);
        test.put(2, 2);
        assertEquals(1, test.get(1)); // refreshes key 1
        test.put(3, 3); // evicts key 2
        assertEquals(-1, test.get(2));
        assertEquals(1, test.get(1));
        assertEquals(3, test.get(3));
    }

    @Test
    public void testUpdateExistingKey() {
        test = new LRUCache_146(2);
        test.put(1, 10);
        test.put(2, 20);
        test.put(1, 100); // update key 1, refreshes it
        test.put(3, 30); // evicts key 2
        assertEquals(-1, test.get(2));
        assertEquals(100, test.get(1));
        assertEquals(30, test.get(3));
    }

    @Test
    public void testNegativeKey() {
        test = new LRUCache_146(2);
        test.put(-1, 100);
        test.put(-2, 200);
        assertEquals(100, test.get(-1));
        assertEquals(200, test.get(-2));
        test.put(-3, 300);
        assertEquals(-1, test.get(-1)); // evicted since -2 was accessed more recently via get
    }

    @Test
    public void testGiantCase() {
        test = new LRUCache_146(1000);
        for (int i = 0; i < 2000; i++) {
            test.put(i, i * 10);
        }
        // first 1000 keys should be evicted
        for (int i = 0; i < 1000; i++) {
            assertEquals(-1, test.get(i));
        }
        // last 1000 keys should still be present
        for (int i = 1000; i < 2000; i++) {
            assertEquals(i * 10, test.get(i));
        }
    }

    @Test
    public void testRepeatedPutSameKey() {
        test = new LRUCache_146(2);
        test.put(1, 1);
        test.put(1, 2);
        test.put(1, 3);
        test.put(1, 4);
        assertEquals(4, test.get(1));
        test.put(2, 20);
        assertEquals(4, test.get(1));
        assertEquals(20, test.get(2));
    }
}