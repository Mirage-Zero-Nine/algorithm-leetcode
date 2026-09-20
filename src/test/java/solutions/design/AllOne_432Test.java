package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author BorisMirage
 * Time: 2023/04/20 00:18
 * Created with IntelliJ IDEA
 */

public class AllOne_432Test {
    private AllOne_432 test;

    @BeforeEach
    public void setUp() {
        test = new AllOne_432();
    }

    @Test
    public void testInvalid() {
        test.dec("sdsds");
        assertEquals("", test.getMinKey());
        assertEquals("", test.getMaxKey());
    }

    @Test
    public void test() {
        test.inc("a");
        assertEquals("a", test.getMinKey());
        assertEquals("a", test.getMaxKey());

        test.inc("b");
        test.inc("b");
        test.inc("c");
        test.inc("c");
        test.inc("c");
        assertEquals("a", test.getMinKey());
        assertEquals("c", test.getMaxKey());
        assertEquals("a", test.getMinKey());
        assertEquals("c", test.getMaxKey());

        test.dec("b");
        test.dec("b");
        assertEquals("a", test.getMinKey());
        test.dec("a");
        assertEquals("c", test.getMaxKey());
        assertEquals("c", test.getMinKey());
    }

    @Test
    public void testAllOne() {


        // Test inc() and getMaxKey() on empty list
        test.inc("hello");
        assertEquals("hello", test.getMaxKey());
        assertEquals("hello", test.getMinKey());

        // Test inc() and getMaxKey() on non-empty list
        test.inc("world");
        Set<String> set = Sets.newHashSet("hello", "world");
        assertTrue(set.contains(test.getMaxKey()));

        // Test inc() with existing key and getMaxKey()
        test.inc("hello");
        assertEquals("hello", test.getMaxKey());
        assertEquals("world", test.getMinKey());

        // Test dec() with existing key and getMinKey()
        test.dec("hello");
        assertTrue(set.contains(test.getMinKey()));

        // Test dec() and getMinKey() with key count of 1
        test.dec("hello");
        assertEquals("world", test.getMaxKey());
        assertEquals("world", test.getMinKey());

        // Test inc() with new key and getMinKey()
        test.inc("goodbye");
        set = Sets.newHashSet("world", "goodbye");
        assertTrue(set.contains(test.getMaxKey()));
        assertTrue(set.contains(test.getMinKey()));


        // Test dec() with non-existent key
        test.dec("missing_key");
        assertTrue(set.contains(test.getMaxKey()));
        assertTrue(set.contains(test.getMinKey()));
    }

    @Test
    public void testSingleKeyIncrementAndDecrementToEmpty() {
        test.inc("x");
        test.inc("x");
        assertEquals("x", test.getMaxKey());
        assertEquals("x", test.getMinKey());
        test.dec("x");
        test.dec("x");
        assertEquals("", test.getMaxKey());
        assertEquals("", test.getMinKey());
    }

    @Test
    public void testTieForMaxAndMin() {
        test.inc("a");
        test.inc("b");
        Set<String> tie = Sets.newHashSet("a", "b");
        assertTrue(tie.contains(test.getMaxKey()));
        assertTrue(tie.contains(test.getMinKey()));
    }

    @Test
    public void testMinAndMaxMoveAcrossCounts() {
        test.inc("a");
        test.inc("b");
        test.inc("b");
        test.inc("c");
        test.inc("c");
        test.inc("c");
        assertEquals("a", test.getMinKey());
        assertEquals("c", test.getMaxKey());
        test.dec("c");
        test.dec("c");
        Set<String> maxTie = Sets.newHashSet("b", "c");
        assertTrue(maxTie.contains(test.getMaxKey()));
    }

    @Test
    public void testRemovingMiddleFrequencyNode() {
        test.inc("a");
        test.inc("a");
        test.inc("b");
        test.inc("b");
        test.inc("c");
        test.inc("c");
        test.inc("c");
        test.dec("c");
        assertEquals("a", test.getMinKey());
        Set<String> maxTie = Sets.newHashSet("a", "b", "c");
        assertTrue(maxTie.contains(test.getMaxKey()));
    }

    @Test
    public void testDecrementNonExistingAfterRemovals() {
        test.inc("a");
        test.dec("a");
        test.dec("a");
        assertEquals("", test.getMaxKey());
        assertEquals("", test.getMinKey());
    }

    @Test
    public void testLargeOperationSequence() {
        for (int i = 0; i < 100; i++) {
            test.inc("k1");
        }
        for (int i = 0; i < 80; i++) {
            test.inc("k2");
        }
        for (int i = 0; i < 50; i++) {
            test.inc("k3");
        }
        assertEquals("k1", test.getMaxKey());
        assertEquals("k3", test.getMinKey());
        for (int i = 0; i < 50; i++) {
            test.dec("k3");
        }
        assertEquals("k1", test.getMaxKey());
        assertEquals("k2", test.getMinKey());
    }

    @Test
    public void testInterleavedUpdatesMaintainCorrectExtremes() {
        test.inc("p");
        test.inc("q");
        test.inc("q");
        test.inc("r");
        test.inc("r");
        test.inc("r");
        assertEquals("r", test.getMaxKey());
        assertEquals("p", test.getMinKey());
        test.dec("r");
        test.dec("r");
        Set<String> mins = Sets.newHashSet("p", "r");
        assertTrue(mins.contains(test.getMinKey()));
    }

    @Test
    public void testNewInstanceStartsEmptyAndRemainsEmptyAcrossReads() {
        for (int i = 0; i < 100; i++) {
            assertEquals("", test.getMinKey());
            assertEquals("", test.getMaxKey());
        }
    }

    @Test
    public void testOfficialExampleSequence() {
        test.inc("hello");
        test.inc("hello");
        assertEquals("hello", test.getMaxKey());
        assertEquals("hello", test.getMinKey());

        test.inc("leet");
        assertEquals("hello", test.getMaxKey());
        assertEquals("leet", test.getMinKey());
    }

    @Test
    public void testManyNewKeysShareTheOneBucket() {
        Map<String, Integer> expected = new HashMap<>();
        for (String key : new String[]{"a", "b", "c", "d", "e", "f", "g"}) {
            test.inc(key);
            expected.put(key, 1);
        }
        assertMatchesOracle(expected);
    }

    @Test
    public void testIncrementCreatesAndRemovesSuccessiveFrequencyBuckets() {
        Map<String, Integer> expected = new HashMap<>();
        for (int count = 1; count <= 8; count++) {
            test.inc("rising");
            expected.put("rising", count);
            assertMatchesOracle(expected);
        }

        for (int count = 7; count >= 0; count--) {
            test.dec("rising");
            if (count == 0) {
                expected.remove("rising");
            } else {
                expected.put("rising", count);
            }
            assertMatchesOracle(expected);
        }
    }

    @Test
    public void testDecrementCreatesLowerBucketBeforeRemovingOldBucket() {
        Map<String, Integer> expected = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            test.inc("high");
            expected.put("high", i + 1);
        }
        test.inc("low");
        expected.put("low", 1);
        assertMatchesOracle(expected);

        test.dec("high");
        expected.put("high", 3);
        assertMatchesOracle(expected);
        test.dec("high");
        expected.put("high", 2);
        assertMatchesOracle(expected);
        test.dec("high");
        expected.put("high", 1);
        assertMatchesOracle(expected);
    }

    @Test
    public void testRemovingTheOnlyKeyReturnsToEmpty() {
        test.inc("only");
        test.dec("only");
        assertEquals("", test.getMinKey());
        assertEquals("", test.getMaxKey());
        test.dec("only");
        assertEquals("", test.getMinKey());
        assertEquals("", test.getMaxKey());
    }

    @Test
    public void testMissingDecrementIsNoOpForPopulatedState() {
        Map<String, Integer> expected = new HashMap<>();
        test.inc("present");
        test.inc("present");
        expected.put("present", 2);
        test.dec("missing");
        test.dec("another-missing");
        assertMatchesOracle(expected);
    }

    @Test
    public void testEqualMinAndMaxCountsMayReturnAnyKey() {
        Map<String, Integer> expected = new HashMap<>();
        for (String key : new String[]{"alpha", "beta", "gamma", "delta"}) {
            test.inc(key);
            expected.put(key, 1);
        }
        assertMatchesOracle(expected);
        assertMatchesOracle(expected);
        assertMatchesOracle(expected);
    }

    @Test
    public void testAllKeysCanMoveTogetherThroughTiedCounts() {
        Map<String, Integer> expected = new HashMap<>();
        for (String key : new String[]{"left", "right", "center"}) {
            test.inc(key);
            expected.put(key, 1);
        }
        for (int count = 2; count <= 6; count++) {
            for (String key : expected.keySet()) {
                test.inc(key);
                expected.put(key, count);
            }
            assertMatchesOracle(expected);
        }
    }

    @Test
    public void testSparseFrequenciesHaveCorrectExtremes() {
        Map<String, Integer> expected = new HashMap<>();
        addCount("one", 1, expected);
        addCount("three", 3, expected);
        addCount("five", 5, expected);
        addCount("seven", 7, expected);
        assertMatchesOracle(expected);

        test.dec("seven");
        expected.put("seven", 6);
        test.dec("one");
        expected.remove("one");
        assertMatchesOracle(expected);
    }

    @Test
    public void testMiddleFrequencyBucketCanDisappearWithoutChangingExtremes() {
        Map<String, Integer> expected = new HashMap<>();
        addCount("min", 1, expected);
        addCount("middle", 3, expected);
        addCount("max", 5, expected);
        assertMatchesOracle(expected);

        test.dec("middle");
        expected.put("middle", 2);
        test.dec("middle");
        expected.put("middle", 1);
        test.dec("middle");
        expected.remove("middle");
        assertMatchesOracle(expected);
    }

    @Test
    public void testDecrementOneOfTiedMinimumKeys() {
        Map<String, Integer> expected = new HashMap<>();
        for (String key : new String[]{"a", "b", "c"}) {
            test.inc(key);
            expected.put(key, 1);
        }
        test.dec("b");
        expected.remove("b");
        assertMatchesOracle(expected);
        assertEquals(1, expected.get(test.getMinKey()));
        assertEquals(1, expected.get(test.getMaxKey()));
    }

    @Test
    public void testKeyCanAlternateBetweenBeingMinimumAndMaximum() {
        Map<String, Integer> expected = new HashMap<>();
        test.inc("a");
        expected.put("a", 1);
        test.inc("b");
        expected.put("b", 1);

        for (int i = 0; i < 12; i++) {
            String key = i % 2 == 0 ? "a" : "b";
            test.inc(key);
            expected.put(key, expected.get(key) + 1);
            assertMatchesOracle(expected);
        }
        for (int i = 0; i < 12; i++) {
            String key = i % 2 == 0 ? "b" : "a";
            test.dec(key);
            int count = expected.get(key) - 1;
            if (count == 0) {
                expected.remove(key);
            } else {
                expected.put(key, count);
            }
            assertMatchesOracle(expected);
        }
    }

    @Test
    public void testKeysAtMaximumSupportedLengthAndAlphabetBoundaries() {
        Map<String, Integer> expected = new HashMap<>();
        String low = "aaaaaaaaaa";
        String high = "zzzzzzzzzz";
        test.inc(low);
        expected.put(low, 1);
        for (int i = 0; i < 3; i++) {
            test.inc(high);
            expected.put(high, i + 1);
        }
        assertMatchesOracle(expected);
        test.dec(high);
        expected.put(high, 2);
        assertMatchesOracle(expected);
    }

    @Test
    public void testSeparateInstancesDoNotShareState() {
        AllOne_432 other = new AllOne_432();
        test.inc("first");
        test.inc("first");
        other.inc("second");
        other.inc("second");
        other.inc("second");

        assertEquals("first", test.getMinKey());
        assertEquals("first", test.getMaxKey());
        assertEquals("second", other.getMinKey());
        assertEquals("second", other.getMaxKey());

        test.dec("first");
        assertEquals("first", test.getMinKey());
        assertEquals("second", other.getMaxKey());
    }

    @Test
    public void testRepeatedCallsDoNotCacheStaleExtremes() {
        Map<String, Integer> expected = new HashMap<>();
        for (int i = 0; i < 40; i++) {
            String key = "k" + i;
            addCount(key, (i % 9) + 1, expected);
        }
        assertMatchesOracle(expected);
        for (int i = 0; i < 40; i++) {
            String key = "k" + i;
            for (int j = 0; j < (i % 9) + 1; j++) {
                test.dec(key);
            }
            expected.remove(key);
            assertMatchesOracle(expected);
        }
    }

    @Test
    public void testSeededValidOperationSequenceMatchesIndependentOracle() {
        String[] keys = {"a", "b", "c", "d", "e", "f", "g", "h"};
        Random random = new Random(432L);
        Map<String, Integer> expected = new HashMap<>();

        for (int operation = 0; operation < 4_000; operation++) {
            String key = keys[random.nextInt(keys.length)];
            boolean increment = expected.isEmpty() || random.nextBoolean();
            if (increment || !expected.containsKey(key)) {
                test.inc(key);
                expected.put(key, expected.getOrDefault(key, 0) + 1);
            } else {
                test.dec(key);
                int next = expected.get(key) - 1;
                if (next == 0) {
                    expected.remove(key);
                } else {
                    expected.put(key, next);
                }
            }
            if (operation % 17 == 0) {
                assertMatchesOracle(expected);
            }
        }
        assertMatchesOracle(expected);
    }

    @Test
    public void testSeededInvalidDecrementsAreNoOpsAgainstOracle() {
        String[] keys = {"a", "b", "c", "d", "e"};
        Random random = new Random(4_320_432L);
        Map<String, Integer> expected = new HashMap<>();

        for (int operation = 0; operation < 1_500; operation++) {
            String key = keys[random.nextInt(keys.length)];
            if (random.nextInt(3) != 0) {
                test.inc(key);
                expected.put(key, expected.getOrDefault(key, 0) + 1);
            } else {
                test.dec(key);
                if (expected.containsKey(key)) {
                    int next = expected.get(key) - 1;
                    if (next == 0) {
                        expected.remove(key);
                    } else {
                        expected.put(key, next);
                    }
                }
            }
            if (operation % 23 == 0) {
                assertMatchesOracle(expected);
            }
        }
        assertMatchesOracle(expected);
    }

    @Test
    public void testMaximumLeetCodeCallBudgetWithReadsAndUpdates() {
        Map<String, Integer> expected = new HashMap<>();
        String[] keys = {"a", "b", "c", "d", "e"};

        for (int i = 0; i < 15_000; i++) {
            String key = keys[i % keys.length];
            test.inc(key);
            expected.put(key, expected.getOrDefault(key, 0) + 1);
        }
        for (int i = 0; i < 5_000; i++) {
            String key = keys[i % keys.length];
            test.dec(key);
            expected.put(key, expected.get(key) - 1);
        }
        for (int i = 0; i < 30_000; i++) {
            if ((i & 1) == 0) {
                assertTrue(expected.containsKey(test.getMinKey()));
            } else {
                assertTrue(expected.containsKey(test.getMaxKey()));
            }
        }
        assertMatchesOracle(expected);
    }

    @Test
    public void testFreshInstanceAfterPriorInstanceIsEmpty() {
        test.inc("stale");
        test.inc("stale");
        test = new AllOne_432();
        assertEquals("", test.getMinKey());
        assertEquals("", test.getMaxKey());
    }

    private void addCount(String key, int count, Map<String, Integer> expected) {
        for (int i = 0; i < count; i++) {
            test.inc(key);
        }
        expected.put(key, count);
    }

    private void assertMatchesOracle(Map<String, Integer> expected) {
        if (expected.isEmpty()) {
            assertEquals("", test.getMinKey());
            assertEquals("", test.getMaxKey());
            return;
        }

        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;
        for (int count : expected.values()) {
            minimum = Math.min(minimum, count);
            maximum = Math.max(maximum, count);
        }

        String minKey = test.getMinKey();
        String maxKey = test.getMaxKey();
        assertTrue(expected.containsKey(minKey), "minimum key must be present: " + minKey);
        assertTrue(expected.containsKey(maxKey), "maximum key must be present: " + maxKey);
        assertEquals(minimum, expected.get(minKey));
        assertEquals(maximum, expected.get(maxKey));
    }
}
