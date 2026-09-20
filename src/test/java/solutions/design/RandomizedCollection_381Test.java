package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class RandomizedCollection_381Test {

    @Test
    public void testHappyCases() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        assertTrue(rc.insert(1));
        assertFalse(rc.insert(1));
        assertTrue(rc.insert(2));
        assertTrue(rc.remove(1));
        int random = rc.getRandom();
        assertTrue(random == 1 || random == 2);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        assertFalse(rc.remove(1));
        rc.insert(1);
        assertTrue(rc.remove(1));
        assertFalse(rc.remove(1));
    }

    @Test
    public void testLargeCase() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        for (int i = 1; i <= 5; i++) rc.insert(i);
        for (int i = 1; i <= 5; i++) assertTrue(rc.remove(i));
        assertFalse(rc.remove(1));
    }

    @Test
    public void testInsertDuplicates() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        assertTrue(rc.insert(5));
        assertFalse(rc.insert(5));
        assertFalse(rc.insert(5));
        assertTrue(rc.remove(5));
        assertTrue(rc.remove(5));
        assertTrue(rc.remove(5));
        assertFalse(rc.remove(5));
    }

    @Test
    public void testGetRandomSingleElement() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(42);
        assertEquals(42, rc.getRandom());
    }

    @Test
    public void testRemoveLastElement() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(2);
        rc.insert(3);
        assertTrue(rc.remove(3));
        // getRandom should only return 1 or 2
        for (int i = 0; i < 20; i++) {
            int r = rc.getRandom();
            assertTrue(r == 1 || r == 2);
        }
    }

    @Test
    public void testInsertAfterRemove() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(2);
        rc.remove(1);
        assertTrue(rc.insert(1)); // 1 was fully removed, so insert returns true
        int r = rc.getRandom();
        assertTrue(r == 1 || r == 2);
    }

    @Test
    public void testMultipleDuplicatesRemoval() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(1);
        rc.insert(2);
        rc.insert(2);
        assertTrue(rc.remove(1));
        assertFalse(rc.insert(1)); // still has one 1
        assertTrue(rc.remove(2));
        assertTrue(rc.remove(2));
        assertFalse(rc.remove(2));
    }

    @Test
    public void testGetRandomCoversAllValues() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(2);
        rc.insert(3);
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 100; i++) seen.add(rc.getRandom());
        assertTrue(seen.contains(1));
        assertTrue(seen.contains(2));
        assertTrue(seen.contains(3));
    }

    @Test
    public void testGiantCase() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        for (int i = 0; i < 5000; i++) rc.insert(i % 100);
        for (int i = 0; i < 5000; i++) {
            int r = rc.getRandom();
            assertTrue(r >= 0 && r < 100);
        }
        for (int i = 0; i < 5000; i++) assertTrue(rc.remove(i % 100));
        assertFalse(rc.remove(0));
    }

    @Test
    public void testRemoveNonExistent() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        assertFalse(rc.remove(2));
        assertFalse(rc.remove(0));
        assertTrue(rc.remove(1));
    }

    @Test
    public void testOfficialExampleSequence() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();

        assertTrue(rc.insert(1));
        assertFalse(rc.insert(1));
        assertTrue(rc.insert(2));
        assertTrue(Set.of(1, 2).contains(rc.getRandom()));
        assertTrue(rc.remove(1));
        assertTrue(Set.of(1, 2).contains(rc.getRandom()));
    }

    @Test
    public void testInsertReturnOnlyFirstOccurrenceIsTrue() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();

        assertTrue(rc.insert(9));
        assertFalse(rc.insert(9));
        assertFalse(rc.insert(9));
        assertTrue(rc.remove(9));
        assertFalse(rc.insert(9));
        assertTrue(rc.remove(9));
        assertTrue(rc.remove(9));
        assertFalse(rc.insert(9));
        assertTrue(rc.remove(9));
        assertTrue(rc.remove(9));
        assertTrue(rc.insert(9));
    }

    @Test
    public void testRemoveDeletesExactlyOneDuplicate() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(4);
        rc.insert(4);
        rc.insert(4);

        assertTrue(rc.remove(4));
        assertFalse(rc.insert(4));
        assertTrue(rc.remove(4));
        assertTrue(rc.remove(4));
        assertTrue(rc.remove(4));
        assertFalse(rc.remove(4));
    }

    @Test
    public void testRemoveAbsentDoesNotChangeExistingValues() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(3);
        rc.insert(8);
        rc.insert(8);

        assertFalse(rc.remove(99));
        for (int i = 0; i < 100; i++) {
            assertTrue(Set.of(3, 8).contains(rc.getRandom()));
        }
        assertTrue(rc.remove(3));
        assertEquals(8, rc.getRandom());
    }

    @Test
    public void testRemoveFirstElementReplacesItWithDistinctTail() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(10);
        rc.insert(20);

        assertTrue(rc.remove(10));
        assertEquals(20, rc.getRandom());
        assertFalse(rc.remove(10));
        assertTrue(rc.remove(20));
    }

    @Test
    public void testRemoveWithDuplicateTailKeepsRemainingIndexesValid() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(1);
        rc.insert(2);
        rc.insert(1);

        assertTrue(rc.remove(2));
        assertTrue(rc.remove(1));
        assertEquals(1, rc.getRandom());
        assertTrue(rc.remove(1));
        assertFalse(rc.remove(1));
    }

    @Test
    public void testRemoveLastElementDoesNotDisturbOtherDuplicates() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(5);
        rc.insert(6);
        rc.insert(5);

        assertTrue(rc.remove(5));
        assertTrue(rc.remove(6));
        assertEquals(5, rc.getRandom());
        assertTrue(rc.remove(5));
        assertFalse(rc.remove(5));
    }

    @Test
    public void testReinsertAfterAllCopiesAreRemoved() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(12);
        rc.insert(12);
        assertTrue(rc.remove(12));
        assertTrue(rc.remove(12));

        assertTrue(rc.insert(12));
        assertEquals(12, rc.getRandom());
    }

    @Test
    public void testSignedAndIntegerBoundaryValues() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        int[] values = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};

        for (int value : values) {
            assertTrue(rc.insert(value));
        }
        for (int i = 0; i < 200; i++) {
            assertTrue(Set.of(
                    Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE).contains(rc.getRandom()));
        }
        for (int value : values) {
            assertTrue(rc.remove(value));
        }
        assertFalse(rc.remove(Integer.MAX_VALUE));
    }

    @Test
    public void testRandomSingleElementAfterManyRemovals() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        for (int value = 0; value < 20; value++) {
            rc.insert(value);
        }
        for (int value = 0; value < 19; value++) {
            assertTrue(rc.remove(value));
        }

        for (int i = 0; i < 200; i++) {
            assertEquals(19, rc.getRandom());
        }
    }

    @Test
    public void testGetRandomRespectsMultiplicityWithinBroadStatisticalBounds() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        rc.insert(7);
        rc.insert(7);
        rc.insert(7);
        rc.insert(-1);

        int samples = 30_000;
        int sevens = 0;
        for (int i = 0; i < samples; i++) {
            int value = rc.getRandom();
            assertTrue(value == 7 || value == -1);
            if (value == 7) {
                sevens++;
            }
        }

        // The expected proportion is 3/4; this intentionally leaves a wide margin for randomness.
        assertTrue(sevens >= 20_400 && sevens <= 24_600,
                "unexpected 7 proportion: " + sevens + "/" + samples);
    }

    @Test
    public void testGetRandomReturnsEveryValueInSmallCollection() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        for (int value = -4; value <= 4; value++) {
            rc.insert(value);
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 2_000; i++) {
            seen.add(rc.getRandom());
        }
        assertEquals(Set.of(-4, -3, -2, -1, 0, 1, 2, 3, 4), seen);
    }

    @Test
    public void testIndependentInstancesDoNotShareState() {
        RandomizedCollection_381 first = new RandomizedCollection_381();
        RandomizedCollection_381 second = new RandomizedCollection_381();

        first.insert(1);
        first.insert(1);
        second.insert(2);

        assertEquals(1, first.getRandom());
        assertEquals(2, second.getRandom());
        assertTrue(first.remove(1));
        assertTrue(first.remove(1));
        assertFalse(first.remove(1));
        assertEquals(2, second.getRandom());
    }

    @Test
    public void testRepeatedUseAfterCollectionBecomesEmpty() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        for (int cycle = 0; cycle < 50; cycle++) {
            assertTrue(rc.insert(cycle));
            assertEquals(cycle, rc.getRandom());
            assertTrue(rc.remove(cycle));
            assertFalse(rc.remove(cycle));
        }
    }

    @Test
    public void testSeededStatefulOperationsMatchMultisetOracle() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        Map<Integer, Integer> counts = new HashMap<>();
        Random random = new Random(381_2026L);

        for (int step = 0; step < 2_000; step++) {
            int value = random.nextInt(31) - 15;
            if (random.nextBoolean()) {
                int oldCount = counts.getOrDefault(value, 0);
                assertEquals(oldCount == 0, rc.insert(value));
                counts.put(value, oldCount + 1);
            } else {
                int oldCount = counts.getOrDefault(value, 0);
                assertEquals(oldCount > 0, rc.remove(value));
                if (oldCount == 1) {
                    counts.remove(value);
                } else if (oldCount > 1) {
                    counts.put(value, oldCount - 1);
                }
            }

            if (!counts.isEmpty()) {
                int result = rc.getRandom();
                assertTrue(counts.containsKey(result));
            }
        }
    }

    @Test
    public void testSeededOperationSequenceChecksPresenceTransitions() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();
        Map<Integer, Integer> counts = new HashMap<>();
        Random random = new Random(38L);
        List<Integer> values = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            values.add(random.nextInt(9) - 4);
        }
        for (int value : values) {
            int before = counts.getOrDefault(value, 0);
            assertEquals(before == 0, rc.insert(value));
            counts.put(value, before + 1);
        }
        for (int value : values) {
            int before = counts.get(value);
            assertTrue(rc.remove(value));
            if (before == 1) {
                counts.remove(value);
            } else {
                counts.put(value, before - 1);
            }
        }
        assertTrue(counts.isEmpty());
        for (int value = -4; value <= 4; value++) {
            assertFalse(rc.remove(value));
        }
    }

    @Test
    public void testExhaustiveShortOperationPatterns() {
        for (int mask = 0; mask < 256; mask++) {
            RandomizedCollection_381 rc = new RandomizedCollection_381();
            Map<Integer, Integer> counts = new HashMap<>();
            for (int step = 0; step < 8; step++) {
                int value = step % 3;
                if ((mask & (1 << step)) == 0) {
                    int before = counts.getOrDefault(value, 0);
                    assertEquals(before == 0, rc.insert(value));
                    counts.put(value, before + 1);
                } else {
                    int before = counts.getOrDefault(value, 0);
                    assertEquals(before > 0, rc.remove(value));
                    if (before <= 1) {
                        counts.remove(value);
                    } else {
                        counts.put(value, before - 1);
                    }
                }
                if (!counts.isEmpty()) {
                    assertTrue(counts.containsKey(rc.getRandom()));
                }
            }
        }
    }

    @Test
    public void testMaximumDocumentedCallCountWithValidOperations() {
        RandomizedCollection_381 rc = new RandomizedCollection_381();

        for (int i = 0; i < 100_000; i++) {
            assertEquals(i < 257, rc.insert(i % 257));
        }
        for (int i = 0; i < 50_000; i++) {
            int value = rc.getRandom();
            assertTrue(value >= 0 && value < 257);
        }
        for (int i = 0; i < 50_000; i++) {
            assertTrue(rc.remove(i % 257));
        }
    }
}
