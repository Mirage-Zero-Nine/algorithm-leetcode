package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LFUCache460Test {

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
}
