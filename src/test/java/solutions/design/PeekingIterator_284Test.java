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
import util.IsolatedTestProcess;

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
    public void testMaximumElementCountWithSequentialNextCalls() throws Exception {
        IsolatedTestProcess.run(PeekingIterator_284Test.class,
                "scenarioMaximumElementCountWithSequentialNextCalls", 10);
    }

    private void scenarioMaximumElementCountWithSequentialNextCalls() {
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
    public void testMaximumLegalOperationCount() throws Exception {
        IsolatedTestProcess.run(PeekingIterator_284Test.class,
                "scenarioMaximumLegalOperationCount", 10);
    }

    private void scenarioMaximumLegalOperationCount() {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 333; i++) {
            values.add(1 + i % 11);
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
    public void testOracleAllShortOperationSequences() throws Exception {
        IsolatedTestProcess.run(PeekingIterator_284Test.class,
                "scenarioOracleAllShortOperationSequences", 10);
    }

    private void scenarioOracleAllShortOperationSequences() {
        // Enumerate every array of length 1..4 over {1, 1000}, then every valid operation string
        // of length 0..6. The array/index oracle independently checks each operation result.
        int sequenceCount = 0;
        for (int valueCount = 1; valueCount <= 4; valueCount++) {
            int arrayCount = 1 << valueCount;
            for (int arrayEncoding = 0; arrayEncoding < arrayCount; arrayEncoding++) {
                List<Integer> values = new ArrayList<>();
                for (int i = 0; i < valueCount; i++) {
                    values.add((arrayEncoding & (1 << i)) == 0 ? 1 : 1000);
                }
                for (int length = 0; length <= 6; length++) {
                    int total = (int) Math.pow(3, length);
                    for (int encoded = 0; encoded < total; encoded++) {
                        int[] operations = new int[length];
                        int remaining = encoded;
                        int oracleIndex = 0;
                        boolean valid = true;
                        for (int i = 0; i < length; i++) {
                            operations[i] = remaining % 3;
                            if (operations[i] != 2) {
                                if (oracleIndex == values.size()) {
                                    valid = false;
                                    break;
                                }
                                if (operations[i] == 1) {
                                    oracleIndex++;
                                }
                            }
                            remaining /= 3;
                        }
                        if (!valid) {
                            continue;
                        }
                        assertMatchesOracle(values, operations);
                        sequenceCount++;
                    }
                }
            }
        }
        assertEquals(27754, sequenceCount);
    }

    @Test
    public void testCachedFinalValueIsAvailableAfterSourceRunsOut() {
        ListIterator<Integer> source = Collections.singletonList(73).listIterator();
        PeekingIterator_284 iter = new PeekingIterator_284(source);
        assertFalse(source.hasNext());
        assertTrue(iter.hasNext());
        assertEquals(73, iter.peek());
        assertEquals(73, iter.next());
        assertFalse(iter.hasNext());
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
        int oracleIndex = 0;
        for (int step = 0; step < operations.length; step++) {
            int operation = operations[step];
            Integer oracleNext = oracleIndex < values.size() ? values.get(oracleIndex) : null;
            String context = "values=" + values + ", operations=" + Arrays.toString(operations)
                    + ", step=" + step;
            switch (operation) {
                case 0 -> assertEquals(oracleNext, actual.peek(), context);
                case 1 -> {
                    assertEquals(oracleNext, actual.next(), context);
                    if (oracleIndex < values.size()) {
                        oracleIndex++;
                    }
                }
                case 2 -> assertEquals(oracleNext != null, actual.hasNext(), context);
                default -> throw new IllegalArgumentException("unknown operation: " + operation);
            }
        }
    }
}
