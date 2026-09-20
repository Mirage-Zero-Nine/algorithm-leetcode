package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link LFUCache_460}.
 *
 * <p>The small examples document individual LFU/LRU transitions. The seeded
 * tests compare every operation with an independent model whose eviction
 * order is determined by frequency and a monotonically increasing access
 * timestamp; this avoids using the implementation's data structures as an
 * oracle.</p>
 */
public class LFUCache_460Test {

    @Test
    public void testOfficialSequence() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

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
    public void testCapacityZeroNeverStoresValues() {
        LFUCache_460 cache = new LFUCache_460(0);
        cache.put(1, 1);
        cache.put(1, 2);
        assertEquals(-1, cache.get(1));
        cache.put(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(-1, cache.get(Integer.MIN_VALUE));
    }

    @Test
    public void testNegativeCapacityBehavesAsDisabledCache() {
        LFUCache_460 cache = new LFUCache_460(-7);
        cache.put(1, 1);
        assertEquals(-1, cache.get(1));
    }

    @Test
    public void testGetMissingDoesNotChangeRecency() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(-1, cache.get(99));
        assertEquals(-1, cache.get(99));
        cache.put(3, 3);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testUpdateExistingValueAlsoIncrementsFrequency() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(1, 11);
        cache.put(3, 30);
        assertEquals(11, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    public void testUpdateExistingValueMakesItMostRecentOnTie() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10);
        cache.put(3, 3);
        assertEquals(10, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testEvictLeastRecentlyUsedOnFrequencyTie() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testGetNonExistentKey() {
        LFUCache_460 cache = new LFUCache_460(2);
        assertEquals(-1, cache.get(99));
        cache.put(1, 1);
        assertEquals(-1, cache.get(99));
        assertEquals(1, cache.get(1));
    }

    @Test
    public void testCapacityOneAlwaysKeepsNewestKey() {
        LFUCache_460 cache = new LFUCache_460(1);
        cache.put(1, 1);
        assertEquals(1, cache.get(1));
        cache.put(2, 2);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        cache.put(2, 22);
        assertEquals(22, cache.get(2));
    }

    @Test
    public void testFrequencyIncreasePreventsEviction() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testMultipleEvictions() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testNoEvictionBelowCapacity() {
        LFUCache_460 cache = new LFUCache_460(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        assertEquals(1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testFrequencyBucketsWithGapsChooseLowestFrequency() {
        LFUCache_460 cache = new LFUCache_460(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.put(4, 4);
        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testAllKeysSameFrequencyEvictsOldestAccess() {
        LFUCache_460 cache = new LFUCache_460(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testNegativeAndExtremeIntegerKeysAndValues() {
        LFUCache_460 cache = new LFUCache_460(4);
        cache.put(Integer.MIN_VALUE, Integer.MAX_VALUE);
        cache.put(-1, Integer.MIN_VALUE);
        cache.put(0, -1);
        cache.put(Integer.MAX_VALUE, 0);
        assertEquals(Integer.MAX_VALUE, cache.get(Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, cache.get(-1));
        assertEquals(-1, cache.get(0));
        assertEquals(0, cache.get(Integer.MAX_VALUE));
    }

    @Test
    public void testZeroAndNegativeValuesAreStoredByTheJavaImplementation() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 0);
        cache.put(2, -1);
        assertEquals(0, cache.get(1));
        assertEquals(-1, cache.get(2));
        cache.put(3, Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, cache.get(3));
    }

    @Test
    public void testIndependentInstancesDoNotShareState() {
        LFUCache_460 first = new LFUCache_460(1);
        LFUCache_460 second = new LFUCache_460(1);
        first.put(1, 10);
        second.put(1, 20);
        first.put(2, 30);
        assertEquals(-1, first.get(1));
        assertEquals(20, second.get(1));
    }

    @Test
    public void testCacheCanBeReusedAfterEveryEntryIsEvicted() {
        LFUCache_460 cache = new LFUCache_460(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.put(5, 5);
        assertEquals(-1, cache.get(3));
        assertEquals(4, cache.get(4));
        assertEquals(5, cache.get(5));
        cache.put(6, 6);
        assertEquals(-1, cache.get(4));
        assertEquals(5, cache.get(5));
        assertEquals(6, cache.get(6));
    }

    @Test
    public void testLargeCapacityBoundary() {
        LFUCache_460 cache = new LFUCache_460(10_000);
        for (int key = 0; key < 10_000; key++) {
            cache.put(key, key * 3);
        }
        assertEquals(0, cache.get(0));
        assertEquals(29_997, cache.get(9_999));
        cache.put(10_000, 10_000);
        assertEquals(-1, cache.get(1));
        assertEquals(10_000, cache.get(10_000));
    }

    @Test
    public void testSeededOracleCapacityOne() {
        assertRandomOperationsMatchOracle(1, 3_000, 460_001L);
    }

    @Test
    public void testSeededOracleCapacityTwo() {
        assertRandomOperationsMatchOracle(2, 8_000, 460_002L);
    }

    @Test
    public void testSeededOracleCapacitySeven() {
        assertRandomOperationsMatchOracle(7, 12_000, 460_007L);
    }

    @Test
    public void testMaximumOperationCountWithinProblemConstraint() {
        assertRandomOperationsMatchOracle(17, 200_000, 460_200_000L);
    }

    @Test
    public void testGiantCase() {
        LFUCache_460 cache = new LFUCache_460(100);
        for (int i = 0; i < 100; i++) {
            cache.put(i, i);
        }
        for (int i = 0; i < 50; i++) {
            cache.get(i);
        }
        for (int i = 100; i < 150; i++) {
            cache.put(i, i);
        }
        assertEquals(0, cache.get(0));
        assertEquals(-1, cache.get(50));
        assertEquals(100, cache.get(100));
    }

    private static void assertRandomOperationsMatchOracle(int capacity, int operationCount, long seed) {
        LFUCache_460 cache = new LFUCache_460(capacity);
        Oracle oracle = new Oracle(capacity);
        Random random = new Random(seed);
        int[] keyPool = {
            Integer.MIN_VALUE, -10_000, -1, 0, 1, 2, 3, 7, 42, 10_000, Integer.MAX_VALUE
        };

        for (int step = 0; step < operationCount; step++) {
            int key = random.nextInt(10) == 0
                ? keyPool[random.nextInt(keyPool.length)]
                : random.nextInt(31) - 15;
            if (random.nextBoolean()) {
                int expected = oracle.get(key);
                int actual = cache.get(key);
                assertEquals(expected, actual, "get mismatch at step " + step + " for key " + key);
            } else {
                int value;
                switch (random.nextInt(8)) {
                    case 0 -> value = Integer.MIN_VALUE;
                    case 1 -> value = Integer.MAX_VALUE;
                    case 2 -> value = -1;
                    default -> value = random.nextInt();
                }
                oracle.put(key, value);
                cache.put(key, value);
            }
        }

        // Probe every possible key after the stream, with both models observing
        // the same gets so their frequency/tie state remains synchronized.
        for (int key : keyPool) {
            assertEquals(oracle.get(key), cache.get(key), "final key mismatch: " + key);
        }
    }

    private static final class Oracle {
        private final int capacity;
        private final Map<Integer, Entry> entries = new HashMap<>();
        private long clock;

        private Oracle(int capacity) {
            this.capacity = capacity;
        }

        private int get(int key) {
            Entry entry = entries.get(key);
            if (entry == null) {
                return -1;
            }
            entry.frequency++;
            entry.lastAccess = ++clock;
            return entry.value;
        }

        private void put(int key, int value) {
            if (capacity < 1) {
                return;
            }
            Entry existing = entries.get(key);
            if (existing != null) {
                existing.value = value;
                existing.frequency++;
                existing.lastAccess = ++clock;
                return;
            }
            if (entries.size() >= capacity) {
                Integer victim = entries.entrySet().stream()
                    .min(Comparator.<Map.Entry<Integer, Entry>>comparingInt(entry -> entry.getValue().frequency)
                        .thenComparingLong(entry -> entry.getValue().lastAccess))
                    .orElseThrow()
                    .getKey();
                entries.remove(victim);
            }
            entries.put(key, new Entry(value, 1, ++clock));
        }

        private static final class Entry {
            private int value;
            private int frequency;
            private long lastAccess;

            private Entry(int value, int frequency, long lastAccess) {
                this.value = value;
                this.frequency = frequency;
                this.lastAccess = lastAccess;
            }
        }
    }
}
