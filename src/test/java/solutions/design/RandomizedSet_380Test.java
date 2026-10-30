package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Tests the set semantics and observable random-value contract of {@link RandomizedSet_380}.
 */
public class RandomizedSet_380Test {

    @Test
    public void officialExample() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(1));
        assertFalse(set.remove(2));
        assertTrue(set.insert(2));
        assertRandomValueIn(set, Set.of(1, 2));
        assertTrue(set.remove(1));
        assertFalse(set.insert(2));
        assertEquals(2, set.getRandom());
    }

    @Test
    public void emptySetRejectsRemoval() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertFalse(set.remove(0));
        assertFalse(set.remove(Integer.MIN_VALUE));
    }

    @Test
    public void duplicateInsertionDoesNotCreateAnotherElement() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(5));
        assertFalse(set.insert(5));
        assertFalse(set.insert(5));
        assertEquals(5, set.getRandom());
    }

    @Test
    public void singletonRandomAlwaysReturnsTheOnlyValue() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(99));
        for (int i = 0; i < 100; i++) {
            assertEquals(99, set.getRandom());
        }
    }

    @Test
    public void removingTheOnlyElementAllowsReinsertion() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(1));
        assertTrue(set.remove(1));
        assertFalse(set.remove(1));
        assertTrue(set.insert(1));
        assertEquals(1, set.getRandom());
    }

    @Test
    public void removingTheFirstElementPreservesTheOtherValues() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(10));
        assertTrue(set.insert(20));
        assertTrue(set.insert(30));
        assertTrue(set.remove(10));
        for (int i = 0; i < 100; i++) {
            assertRandomValueIn(set, Set.of(20, 30));
        }
    }

    @Test
    public void removingAMiddleElementExercisesSwapDelete() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int value = 1; value <= 5; value++) {
            assertTrue(set.insert(value));
        }
        assertTrue(set.remove(3));
        for (int i = 0; i < 100; i++) {
            assertRandomValueIn(set, Set.of(1, 2, 4, 5));
        }
        assertFalse(set.remove(3));
        assertTrue(set.insert(3));
        for (int i = 0; i < 500; i++) {
            assertRandomValueIn(set, Set.of(1, 2, 3, 4, 5));
        }
    }

    @Test
    public void removingTheLastElementDoesNotCorruptTheIndexMap() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(1));
        assertTrue(set.insert(2));
        assertTrue(set.insert(3));
        assertTrue(set.remove(3));
        assertTrue(set.remove(2));
        assertEquals(1, set.getRandom());
        assertFalse(set.remove(2));
    }

    @Test
    public void removingValuesInDifferentOrdersLeavesExactlyTheExpectedSet() {
        RandomizedSet_380 set = new RandomizedSet_380();
        Set<Integer> expected = new HashSet<>();
        for (int value = -10; value <= 10; value++) {
            assertEquals(expected.add(value), set.insert(value));
        }
        int[] removalOrder = {0, -10, 10, 4, -7, 8, -1, 1, 6, -3, 9, -9, 2, -2, 5, -5, 7, -8, 3, -4, -6};
        for (int value : removalOrder) {
            assertEquals(expected.remove(value), set.remove(value));
            if (!expected.isEmpty()) {
                assertRandomValueIn(set, expected);
            }
        }
        assertFalse(set.remove(0));
    }

    @Test
    public void negativeValuesAreDistinctAndReturned() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(-1));
        assertTrue(set.insert(-2));
        assertFalse(set.insert(-1));
        for (int i = 0; i < 100; i++) {
            assertRandomValueIn(set, Set.of(-1, -2));
        }
    }

    @Test
    public void integerBoundariesAreHandledAsOrdinaryValues() {
        RandomizedSet_380 set = new RandomizedSet_380();
        Set<Integer> expected = Set.of(Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE);
        for (int value : expected) {
            assertTrue(set.insert(value));
        }
        for (int i = 0; i < 250; i++) {
            assertRandomValueIn(set, expected);
        }
        assertFalse(set.insert(Integer.MAX_VALUE));
        assertTrue(set.remove(Integer.MIN_VALUE));
        assertRandomValueIn(set, Set.of(-1, 0, 1, Integer.MAX_VALUE));
    }

    @Test
    public void absentRemovalNeverChangesTheCurrentSet() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int value : new int[]{4, 8, 15, 16, 23, 42}) {
            assertTrue(set.insert(value));
        }
        Set<Integer> expected = Set.of(4, 8, 15, 16, 23, 42);
        for (int value : new int[]{-1, 0, 7, 24, 43, Integer.MIN_VALUE, Integer.MAX_VALUE}) {
            assertFalse(set.remove(value));
            assertRandomValueIn(set, expected);
        }
    }

    @Test
    public void removedValuesAreNeverReturnedAfterRepeatedSampling() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int value = 0; value < 10; value++) {
            assertTrue(set.insert(value));
        }
        assertTrue(set.remove(0));
        assertTrue(set.remove(4));
        assertTrue(set.remove(9));
        Set<Integer> expected = new HashSet<>();
        for (int value = 1; value < 9; value++) {
            if (value != 4) {
                expected.add(value);
            }
        }
        for (int i = 0; i < 2_000; i++) {
            assertRandomValueIn(set, expected);
        }
    }

    @Test
    public void getRandomDoesNotChangeInsertOrRemoveSemantics() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(10));
        assertTrue(set.insert(20));
        for (int i = 0; i < 500; i++) {
            assertRandomValueIn(set, Set.of(10, 20));
        }
        assertFalse(set.insert(10));
        assertTrue(set.remove(20));
        assertEquals(10, set.getRandom());
    }

    @Test
    public void samplingFourValuesLeavesEveryValueAvailableForRemoval() {
        RandomizedSet_380 set = new RandomizedSet_380();
        int[] values = {-3, -1, 2, 7};
        for (int value : values) {
            assertTrue(set.insert(value));
        }

        Set<Integer> expected = Set.of(-3, -1, 2, 7);
        // Every valid random sequence passes; frequency thresholds could reject a valid generator.
        for (int i = 0; i < 20_000; i++) {
            assertRandomValueIn(set, expected);
        }
        for (int value : values) {
            assertFalse(set.insert(value));
            assertTrue(set.remove(value));
            assertFalse(set.remove(value));
        }
    }

    @Test
    public void independentInstancesDoNotShareState() {
        RandomizedSet_380 first = new RandomizedSet_380();
        RandomizedSet_380 second = new RandomizedSet_380();
        assertTrue(first.insert(1));
        assertTrue(second.insert(2));
        assertEquals(1, first.getRandom());
        assertEquals(2, second.getRandom());
        assertTrue(first.remove(1));
        assertFalse(second.remove(1));
        assertTrue(second.insert(3));
        assertRandomValueIn(second, Set.of(2, 3));
    }

    @Test
    public void repeatedReuseAfterDrainingWorksForSeveralCycles() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int cycle = 0; cycle < 8; cycle++) {
            int first = cycle * 100;
            for (int offset = 0; offset < 25; offset++) {
                assertTrue(set.insert(first + offset));
            }
            for (int offset = 24; offset >= 0; offset--) {
                assertTrue(set.remove(first + offset));
            }
            assertFalse(set.remove(first));
        }
    }

    @Test
    public void reinsertionAfterMultipleSwapsDoesNotRetainStaleIndexes() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int value = 0; value < 8; value++) {
            assertTrue(set.insert(value));
        }
        for (int value : new int[]{2, 5, 0, 6}) {
            assertTrue(set.remove(value));
        }
        for (int value : new int[]{2, 5, 0, 6}) {
            assertTrue(set.insert(value));
        }
        Set<Integer> expected = new HashSet<>();
        for (int value = 0; value < 8; value++) {
            expected.add(value);
        }
        for (int i = 0; i < 1_000; i++) {
            assertRandomValueIn(set, expected);
        }
        for (int value = 0; value < 8; value++) {
            assertTrue(set.remove(value));
        }
    }

    @Test
    public void seededOperationStreamMatchesAnIndependentHashSetOracle() {
        RandomizedSet_380 actual = new RandomizedSet_380();
        Set<Integer> expected = new HashSet<>();
        Random random = new Random(380_2026L);
        int[] values = {
                Integer.MIN_VALUE, -1_000, -7, -1, 0, 1, 7, 1_000, 42, Integer.MAX_VALUE
        };

        for (int i = 0; i < 6_000; i++) {
            int value = values[random.nextInt(values.length)];
            int operation = expected.isEmpty() ? 0 : random.nextInt(3);
            if (operation == 0) {
                assertEquals(expected.add(value), actual.insert(value));
            } else if (operation == 1) {
                assertEquals(expected.remove(value), actual.remove(value));
            } else {
                assertRandomValueIn(actual, expected);
            }
            if (!expected.isEmpty() && random.nextInt(17) == 0) {
                assertRandomValueIn(actual, expected);
            }
        }
    }

    @Test
    public void exactDocumentedCallBudgetPreservesLargeState() {
        RandomizedSet_380 set = new RandomizedSet_380();
        Set<Integer> expected = new HashSet<>();
        for (int value = 0; value < 100_000; value++) {
            assertEquals(expected.add(value), set.insert(value));
        }
        for (int value = 0; value < 50_000; value++) {
            assertEquals(expected.remove(value), set.remove(value));
        }
        for (int i = 0; i < 50_000; i++) {
            assertRandomValueIn(set, expected);
        }
    }

    @Test
    public void smallExhaustiveInsertRemoveSequencesMatchTheOracle() {
        int[][] sequences = {
                {0, 1, 2},
                {2, 1, 0},
                {1, 1, 0, 2, 1},
                {-1, 0, -1, 1, 0},
                {Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, 0}
        };
        for (int[] sequence : sequences) {
            RandomizedSet_380 set = new RandomizedSet_380();
            Set<Integer> expected = new HashSet<>();
            for (int value : sequence) {
                assertEquals(expected.add(value), set.insert(value));
            }
            for (int i = sequence.length - 1; i >= 0; i--) {
                int value = sequence[i];
                assertEquals(expected.remove(value), set.remove(value));
                if (!expected.isEmpty()) {
                    assertRandomValueIn(set, expected);
                }
            }
        }
    }

    @Test
    public void zeroIsAnOrdinaryValueThroughRemovalAndReuse() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(0));
        assertTrue(set.insert(-1));
        assertTrue(set.remove(0));
        assertEquals(-1, set.getRandom());
        assertTrue(set.insert(0));
        assertFalse(set.insert(0));
        assertTrue(set.remove(-1));
        assertEquals(0, set.getRandom());
    }

    @Test
    public void removingTheMovedLastValueUsesItsUpdatedIndex() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int value : new int[]{4, 8, 12, 16, 20}) {
            assertTrue(set.insert(value));
        }
        assertTrue(set.remove(12));
        assertTrue(set.remove(20));
        assertFalse(set.remove(20));
        assertTrue(set.remove(4));
        assertTrue(set.remove(16));
        assertEquals(8, set.getRandom());
    }

    @Test
    public void repeatedlyRemovingTheReplacementPreservesTheOtherSlot() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int value = 1; value <= 8; value++) {
            assertTrue(set.insert(value));
        }
        // Each removal replaces the first slot with the next value removed.
        for (int value : new int[]{1, 8, 7, 6, 5, 4, 3}) {
            assertTrue(set.remove(value));
            assertFalse(set.remove(value));
        }
        assertEquals(2, set.getRandom());
    }

    @Test
    public void duplicateInsertionAfterRelocationDoesNotLeaveAnExtraCopy() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(11));
        assertTrue(set.insert(22));
        assertTrue(set.insert(33));
        assertTrue(set.remove(11));
        assertFalse(set.insert(33));
        assertTrue(set.remove(33));
        assertFalse(set.remove(33));
        assertEquals(22, set.getRandom());
    }

    @Test
    public void equalValuesOutsideTheBoxedIntegerCacheAreStillDuplicates() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(10_000));
        assertFalse(set.insert(10_000));
        assertTrue(set.insert(-10_000));
        assertTrue(set.remove(10_000));
        assertFalse(set.remove(10_000));
        assertEquals(-10_000, set.getRandom());
    }

    @Test
    public void reinsertingTheRemovedValueAfterASwapKeepsBothValuesIndependent() {
        RandomizedSet_380 set = new RandomizedSet_380();
        assertTrue(set.insert(13));
        assertTrue(set.insert(29));
        assertTrue(set.remove(13));
        assertTrue(set.insert(13));
        assertFalse(set.insert(29));
        assertTrue(set.remove(29));
        assertEquals(13, set.getRandom());
        assertTrue(set.remove(13));
        assertFalse(set.remove(29));
    }

    @Test
    public void repeatedBitPatternsRemainDistinctAcrossGrowthAndSwapRemoval() {
        RandomizedSet_380 set = new RandomizedSet_380();
        for (int value = 0; value < 128; value++) {
            assertTrue(set.insert((value << 16) | value));
        }
        for (int value = 0; value < 128; value += 2) {
            assertTrue(set.remove((value << 16) | value));
        }
        for (int value = 127; value >= 1; value -= 2) {
            int storedValue = (value << 16) | value;
            assertFalse(set.insert(storedValue));
            if (value == 1) {
                assertEquals(storedValue, set.getRandom());
            }
            assertTrue(set.remove(storedValue));
        }
        assertFalse(set.remove(0));
    }

    @Test
    public void maximumCallBudgetCanBeUsedEntirelyForDistinctInsertions() {
        RandomizedSet_380 set = new RandomizedSet_380();
        // Exactly 200,000 calls also reach the largest possible number of stored values.
        for (int value = 0; value < 200_000; value++) {
            assertTrue(set.insert(value));
        }
    }

    @Test
    public void everyFiveOperationSequenceOverThreeValuesMatchesTheOracle() {
        int[] values = {-1, 0, 1};
        int operationCount = 2 * values.length;
        int sequenceCount = 1;
        for (int step = 0; step < 5; step++) {
            sequenceCount *= operationCount;
        }

        // Six choices per step cover insert/remove for each value, including all shorter prefixes.
        for (int sequence = 0; sequence < sequenceCount; sequence++) {
            RandomizedSet_380 actual = new RandomizedSet_380();
            Set<Integer> expected = new HashSet<>();
            int remaining = sequence;
            for (int step = 0; step < 5; step++) {
                int operation = remaining % operationCount;
                remaining /= operationCount;
                int value = values[operation / 2];
                if (operation % 2 == 0) {
                    assertEquals(expected.add(value), actual.insert(value));
                } else {
                    assertEquals(expected.remove(value), actual.remove(value));
                }
                if (!expected.isEmpty()) {
                    assertRandomValueIn(actual, expected);
                }
            }
            // Draining all possible values checks retained membership independently of sampling.
            for (int value : values) {
                if (expected.size() == 1) {
                    assertEquals(expected.iterator().next().intValue(), actual.getRandom());
                }
                assertEquals(expected.remove(value), actual.remove(value));
            }
        }
    }

    private static void assertRandomValueIn(RandomizedSet_380 set, Set<Integer> expected) {
        int actual = set.getRandom();
        assertTrue(expected.contains(actual), () -> "unexpected random value: " + actual);
    }

}
