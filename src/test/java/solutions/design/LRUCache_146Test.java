package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

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

    @Test
    public void testEvictionOrderWithGetBump() {
        // put 1,2,3 with cap 2: 1 evicted; get 2 bumps it; put 4 evicts 3 (not 2)
        test = new LRUCache_146(2);
        test.put(1, 1);
        test.put(2, 2);
        test.put(3, 3); // evicts 1
        assertEquals(-1, test.get(1));
        assertEquals(2, test.get(2)); // bumps 2 to MRU
        test.put(4, 4); // evicts 3 (LRU), not 2
        assertEquals(-1, test.get(3));
        assertEquals(2, test.get(2));
        assertEquals(4, test.get(4));
    }

    @Test
    public void testCapacityOneMultipleEvictions() {
        test = new LRUCache_146(1);
        for (int i = 0; i < 100; i++) {
            test.put(i, i * 2);
            assertEquals(i * 2, test.get(i));
            if (i > 0) {
                assertEquals(-1, test.get(i - 1));
            }
        }
    }

    @Test
    public void testGetMissingKeyVariousStates() {
        test = new LRUCache_146(3);
        // empty cache
        assertEquals(-1, test.get(999));
        // after some puts
        test.put(1, 1);
        test.put(2, 2);
        assertEquals(-1, test.get(100));
        // after eviction, evicted key returns -1
        test.put(3, 3);
        test.put(4, 4); // evicts 1
        assertEquals(-1, test.get(1));
        // key that was never inserted
        assertEquals(-1, test.get(Integer.MAX_VALUE));
        assertEquals(-1, test.get(Integer.MIN_VALUE));
    }

    @Test
    public void testPutUpdateBumpsToMRU() {
        test = new LRUCache_146(3);
        test.put(1, 10);
        test.put(2, 20);
        test.put(3, 30);
        // update key 1 (currently LRU) -> bumps to MRU
        test.put(1, 100);
        // now order is 2(LRU), 3, 1(MRU). Adding 4 evicts 2
        test.put(4, 40);
        assertEquals(-1, test.get(2));
        assertEquals(100, test.get(1));
        assertEquals(30, test.get(3));
        assertEquals(40, test.get(4));
    }

    @Test
    public void testLeetCodeExample() {
        // LeetCode problem 146 example
        test = new LRUCache_146(2);
        test.put(1, 1);
        test.put(2, 2);
        assertEquals(1, test.get(1));
        test.put(3, 3); // evicts key 2
        assertEquals(-1, test.get(2));
        test.put(4, 4); // evicts key 1
        assertEquals(-1, test.get(1));
        assertEquals(3, test.get(3));
        assertEquals(4, test.get(4));
    }

    @Test
    public void testStress10000OpsAgainstLinkedHashMap() {
        int capacity = 100;
        test = new LRUCache_146(capacity);
        Map<Integer, Integer> reference = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };

        Random rng = new Random(42L);
        for (int i = 0; i < 10000; i++) {
            if (rng.nextBoolean()) {
                int key = rng.nextInt(200);
                int expected = reference.getOrDefault(key, -1);
                assertEquals(expected, test.get(key), "get(" + key + ") at op " + i);
            } else {
                int key = rng.nextInt(200);
                int value = rng.nextInt(1000);
                reference.put(key, value);
                test.put(key, value);
            }
        }
    }

    @Test
    public void testCacheSizeNeverExceedsCapacity() {
        int capacity = 5;
        test = new LRUCache_146(capacity);
        Random rng = new Random(42L);
        for (int i = 0; i < 1000; i++) {
            test.put(rng.nextInt(50), rng.nextInt(100));
        }
        // verify at most 'capacity' keys are present
        int count = 0;
        for (int k = 0; k < 50; k++) {
            if (test.get(k) != -1) {
                count++;
            }
        }
        assertTrue(count <= capacity, "Cache size " + count + " exceeds capacity " + capacity);
    }
}