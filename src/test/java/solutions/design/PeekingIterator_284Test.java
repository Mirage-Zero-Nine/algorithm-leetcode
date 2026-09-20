package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;

/** Tests the state transitions and look-ahead contract of {@link PeekingIterator_284}. */
public class PeekingIterator_284Test {

    @Test
    public void testHappyCases() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2, 3).iterator());
        assertEquals(1, iter.peek());
        assertEquals(1, iter.next());
        assertEquals(2, iter.peek());
        assertTrue(iter.hasNext());
    }

    @Test
    public void testOfficialOperationSequence() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2, 3).iterator());
        assertEquals(1, iter.next());
        assertEquals(2, iter.peek());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testEdgeCases() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1).iterator());
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testSingletonPeekAndNext() {
        PeekingIterator_284 iter = new PeekingIterator_284(Collections.singletonList(42).iterator());
        assertEquals(42, iter.peek());
        assertEquals(42, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testTwoElementsAndLastElementTransition() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(100, 200).iterator());
        assertEquals(100, iter.peek());
        assertEquals(100, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(200, iter.peek());
        assertEquals(200, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testLargeCase() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2, 3, 4, 5).iterator());
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, iter.peek());
            assertEquals(i, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testPeekMultipleTimes() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(10, 20, 30).iterator());
        assertEquals(10, iter.peek());
        assertEquals(10, iter.peek());
        assertEquals(10, iter.peek());
        assertEquals(10, iter.next());
        assertEquals(20, iter.peek());
        assertEquals(20, iter.peek());
    }

    @Test
    public void testNextWithoutPeek() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(5, 6, 7).iterator());
        assertEquals(5, iter.next());
        assertEquals(6, iter.next());
        assertEquals(7, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testHasNextDoesNotAdvance() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2).iterator());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals(1, iter.peek());
        assertEquals(1, iter.next());
    }

    @Test
    public void testPeekNextAlternation() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(1, 2, 3, 4).iterator());
        assertEquals(1, iter.peek());
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.peek());
        assertEquals(3, iter.peek());
        assertEquals(3, iter.next());
        assertEquals(4, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testDuplicateValuesRemainDistinctInOrder() {
        PeekingIterator_284 iter = new PeekingIterator_284(
                Arrays.asList(7, 7, 7, 8, 7, 8).iterator());
        assertEquals(7, iter.peek());
        assertEquals(7, iter.next());
        assertEquals(7, iter.peek());
        assertEquals(7, iter.next());
        assertEquals(7, iter.next());
        assertEquals(8, iter.peek());
        assertEquals(8, iter.next());
        assertEquals(7, iter.next());
        assertEquals(8, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testNegativeZeroAndPositiveValues() {
        PeekingIterator_284 iter = new PeekingIterator_284(
                Arrays.asList(-3, -1, 0, 1, 3).iterator());
        assertEquals(-3, iter.peek());
        assertEquals(-3, iter.next());
        assertEquals(-1, iter.next());
        assertEquals(0, iter.peek());
        assertEquals(0, iter.next());
        assertEquals(1, iter.peek());
        assertEquals(1, iter.next());
        assertEquals(3, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIntegerBoundaryValues() {
        List<Integer> values = Arrays.asList(Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE);
        PeekingIterator_284 iter = new PeekingIterator_284(values.iterator());
        for (Integer value : values) {
            assertEquals(value, iter.peek());
            assertEquals(value, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testEmptyIteratorUsesImplementationSentinels() {
        PeekingIterator_284 iter = new PeekingIterator_284(Collections.<Integer>emptyIterator());
        assertFalse(iter.hasNext());
        assertNull(iter.peek());
        assertNull(iter.next());
    }

    @Test
    public void testOracleMixedSequence() {
        assertMatchesOracle(
                Arrays.asList(9, 1, 9, 2, 8),
                2, 2, 0, 1, 2, 0, 1, 1, 2, 0, 1, 1, 2);
    }

    @Test
    public void testOraclePeekHeavySequence() {
        assertMatchesOracle(
                Arrays.asList(4, 4, 5, 6),
                2, 0, 0, 2, 0, 1, 0, 2, 2, 1, 1, 0, 1, 2);
    }

    @Test
    public void testOracleHasNextHeavySequence() {
        assertMatchesOracle(
                Arrays.asList(-8, 0, 8),
                2, 2, 0, 2, 1, 2, 2, 1, 2, 0, 1, 2);
    }

    @Test
    public void testOracleSignedDuplicateSequence() {
        assertMatchesOracle(
                Arrays.asList(-1, -1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE),
                0, 0, 2, 1, 2, 0, 1, 1, 2, 0, 1, 2);
    }

    @Test
    public void testRepeatedStateTransitions() {
        PeekingIterator_284 iter = new PeekingIterator_284(Arrays.asList(11, 22, 33).iterator());
        assertTrue(iter.hasNext());
        assertEquals(11, iter.peek());
        assertEquals(11, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(22, iter.peek());
        assertEquals(22, iter.next());
        assertEquals(33, iter.peek());
        assertTrue(iter.hasNext());
        assertEquals(33, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testMaximumElementCountWithSequentialNextCalls() {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            values.add(i + 1);
        }
        PeekingIterator_284 iter = new PeekingIterator_284(values.iterator());
        for (Integer value : values) {
            assertEquals(value, iter.next());
        }
    }

    @Test
    public void testMaximumLegalOperationCount() {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 333; i++) {
            values.add(i % 11);
        }
        // Three valid calls per element give 999 calls, just below LeetCode's limit of 1000.
        PeekingIterator_284 iter = new PeekingIterator_284(values.iterator());
        for (Integer value : values) {
            assertTrue(iter.hasNext());
            assertEquals(value, iter.peek());
            assertEquals(value, iter.next());
        }
    }

    @Test
    public void testIndependentInstancesDoNotShareLookAhead() {
        PeekingIterator_284 first = new PeekingIterator_284(Arrays.asList(1, 2, 3).iterator());
        PeekingIterator_284 second = new PeekingIterator_284(Arrays.asList(1, 2, 3).iterator());
        assertEquals(1, first.next());
        assertEquals(1, second.peek());
        assertEquals(2, first.peek());
        assertEquals(1, second.next());
        assertEquals(2, second.next());
        assertEquals(2, first.next());
    }

    @Test
    public void testFreshInstancesCanReuseSameValues() {
        List<Integer> values = Arrays.asList(3, 1, 4, 1, 5, 9);
        PeekingIterator_284 first = new PeekingIterator_284(values.iterator());
        PeekingIterator_284 second = new PeekingIterator_284(values.iterator());
        for (Integer value : values) {
            assertEquals(value, first.next());
        }
        for (Integer value : values) {
            assertEquals(value, second.peek());
            assertEquals(value, second.next());
        }
    }

    @Test
    public void testOracleExhaustiveShortOperationPatterns() {
        List<Integer> values = Arrays.asList(2, 1, 0, -1);
        assertMatchesOracle(values, 0, 1, 0, 1, 2, 0, 1, 2);
        assertMatchesOracle(values, 2, 0, 0, 1, 2, 1, 0, 1, 2);
        assertMatchesOracle(values, 0, 2, 1, 2, 0, 1, 0, 1, 2);
    }

    @Test
    public void testInputListRemainsUnchanged() {
        List<Integer> values = new ArrayList<>(Arrays.asList(6, 2, 4, 1));
        List<Integer> snapshot = new ArrayList<>(values);
        PeekingIterator_284 iter = new PeekingIterator_284(values.iterator());
        assertEquals(6, iter.peek());
        assertEquals(6, iter.next());
        assertEquals(2, iter.next());
        assertEquals(snapshot, values);
    }

    @Test
    public void testLargeValuesWithRepeatedPeeks() {
        List<Integer> values = Arrays.asList(999, 1, 500, 1000, 2);
        PeekingIterator_284 iter = new PeekingIterator_284(values.iterator());
        for (Integer value : values) {
            assertEquals(value, iter.peek());
            assertEquals(value, iter.peek());
            assertEquals(value, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    private static void assertMatchesOracle(List<Integer> values, int... operations) {
        PeekingIterator_284 actual = new PeekingIterator_284(values.iterator());
        ListIterator<Integer> oracle = values.listIterator();
        Integer oracleNext = oracle.hasNext() ? oracle.next() : null;
        for (int operation : operations) {
            switch (operation) {
                case 0 -> assertEquals(oracleNext, actual.peek());
                case 1 -> {
                    assertEquals(oracleNext, actual.next());
                    oracleNext = oracle.hasNext() ? oracle.next() : null;
                }
                case 2 -> assertEquals(oracleNext != null, actual.hasNext());
                default -> throw new IllegalArgumentException("unknown operation: " + operation);
            }
        }
    }
}
