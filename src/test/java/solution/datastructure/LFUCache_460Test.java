package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LFUCache_460Test {

    @Test
    public void testHappyCases() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        LFUCache_460 cache = new LFUCache_460(0);
        cache.put(1, 1);
        assertEquals(-1, cache.get(1));
    }

    @Test
    public void testLargeCase() {
        LFUCache_460 cache = new LFUCache_460(3);
        cache.put(1, 1); cache.put(2, 2); cache.put(3, 3);
        cache.get(1); cache.get(2);
        cache.put(4, 4);
        assertEquals(-1, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testUpdateExistingKey() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 10);
        cache.put(1, 20);
        assertEquals(20, cache.get(1));
    }

    @Test
    public void testEvictLRUOnTie() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        // both freq=1, evict LRU (key 1)
        cache.put(3, 3);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testGetNonExistentKey() {
        LFUCache_460 cache = new LFUCache_460(2);
        assertEquals(-1, cache.get(99));
    }

    @Test
    public void testCapacityOne() {
        LFUCache_460 cache = new LFUCache_460(1);
        cache.put(1, 1);
        assertEquals(1, cache.get(1));
        cache.put(2, 2);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @Test
    public void testFrequencyIncreasePreventsEviction() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1); // freq(1)=2, freq(2)=1
        cache.put(3, 3); // evicts key 2 (lowest freq)
        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testPutUpdatesValueAndFrequency() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10); // updates value, increases freq
        cache.put(3, 3);  // evicts key 2 (lower freq)
        assertEquals(-1, cache.get(2));
        assertEquals(10, cache.get(1));
    }

    @Test
    public void testMultipleEvictions() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3); // evicts 1
        cache.put(4, 4); // evicts 2
        assertEquals(-1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testGiantCase() {
        LFUCache_460 cache = new LFUCache_460(100);
        for (int i = 0; i < 100; i++) cache.put(i, i);
        // access first 50 keys to increase their frequency
        for (int i = 0; i < 50; i++) cache.get(i);
        // insert 50 new keys, should evict keys 50-99
        for (int i = 100; i < 150; i++) cache.put(i, i);
        assertEquals(0, cache.get(0));
        assertEquals(-1, cache.get(50));
        assertEquals(100, cache.get(100));
    }
}
