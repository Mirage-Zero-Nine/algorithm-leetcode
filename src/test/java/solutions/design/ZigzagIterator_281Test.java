package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests for the two-vector round-robin iterator in {@link ZigzagIterator_281}. */
public class ZigzagIterator_281Test {

    @Test
    public void testOfficialExample() {
        assertIteratorMatchesOracle(List.of(1, 2), List.of(3, 4, 5, 6));
    }

    @Test
    public void testFirstVectorLonger() {
        assertIteratorMatchesOracle(List.of(1, 2, 3, 4), List.of(5));
    }

    @Test
    public void testSecondVectorLonger() {
        assertIteratorMatchesOracle(List.of(1), List.of(2, 3, 4, 5));
    }

    @Test
    public void testEqualLengthVectors() {
        assertIteratorMatchesOracle(List.of(1, 3, 5), List.of(2, 4, 6));
    }

    @Test
    public void testBothSingletonVectors() {
        assertIteratorMatchesOracle(List.of(10), List.of(20));
    }

    @Test
    public void testEmptyFirstVectorDrainsSecond() {
        assertIteratorMatchesOracle(List.of(), List.of(1, 2, 3));
    }

    @Test
    public void testEmptySecondVectorDrainsFirst() {
        assertIteratorMatchesOracle(List.of(1, 2, 3), List.of());
    }

    @Test
    public void testBothEmptyVectorsHaveNoNext() {
        ZigzagIterator_281 iterator = new ZigzagIterator_281(List.of(), List.of());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testDuplicateValuesAreNotCollapsed() {
        assertIteratorMatchesOracle(List.of(7, 7, 7, 7), List.of(7, 7));
    }

    @Test
    public void testZerosAndSignedValues() {
        assertIteratorMatchesOracle(List.of(-3, 0, 3), List.of(-2, 0, 2, 4));
    }

    @Test
    public void testJavaIntegerBoundaries() {
        assertIteratorMatchesOracle(
                List.of(Integer.MIN_VALUE, -1, Integer.MAX_VALUE),
                List.of(Integer.MAX_VALUE, 0, Integer.MIN_VALUE, 1));
    }

    @Test
    public void testAlternationStopsOnlyWhenOneVectorIsExhausted() {
        ZigzagIterator_281 iterator = new ZigzagIterator_281(List.of(1, 2, 3), List.of(4, 5));
        assertEquals(1, iterator.next());
        assertEquals(4, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRepeatedHasNextDoesNotAdvance() {
        ZigzagIterator_281 iterator = new ZigzagIterator_281(List.of(1, 2), List.of(3));
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRepeatedHasNextAfterExhaustionIsStable() {
        ZigzagIterator_281 iterator = new ZigzagIterator_281(List.of(1), List.of(2));
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testHasNextAndNextOperationPattern() {
        ZigzagIterator_281 iterator = new ZigzagIterator_281(List.of(1, 2), List.of(3, 4));
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(4, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testInputVectorsAreNotMutated() {
        List<Integer> first = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> second = new ArrayList<>(List.of(4, 5));
        List<Integer> firstBefore = List.copyOf(first);
        List<Integer> secondBefore = List.copyOf(second);

        assertIteratorMatchesOracle(first, second);
        assertEquals(firstBefore, first);
        assertEquals(secondBefore, second);
    }

    @Test
    public void testIndependentIteratorsOverSameVectors() {
        List<Integer> first = List.of(1, 2, 3);
        List<Integer> second = List.of(4, 5);
        ZigzagIterator_281 left = new ZigzagIterator_281(first, second);
        ZigzagIterator_281 right = new ZigzagIterator_281(first, second);

        assertEquals(1, left.next());
        assertEquals(1, right.next());
        assertEquals(4, right.next());
        assertEquals(4, left.next());
        assertEquals(2, left.next());
        assertEquals(2, right.next());
        assertEquals(5, right.next());
        assertEquals(5, left.next());
        assertEquals(3, left.next());
        assertEquals(3, right.next());
        assertFalse(left.hasNext());
        assertFalse(right.hasNext());
    }

    @Test
    public void testConsumingOneIteratorDoesNotChangeAnother() {
        List<Integer> first = List.of(-2, -1, 0);
        List<Integer> second = List.of(1, 2, 3);
        ZigzagIterator_281 consumed = new ZigzagIterator_281(first, second);
        ZigzagIterator_281 untouched = new ZigzagIterator_281(first, second);

        while (consumed.hasNext()) {
            consumed.next();
        }
        assertIteratorMatchesOracle(untouched, first, second);
    }

    @Test
    public void testDifferentInstancesWithDifferentLengthsAreIsolated() {
        ZigzagIterator_281 shortIterator = new ZigzagIterator_281(List.of(1), List.of(2, 3));
        ZigzagIterator_281 longIterator = new ZigzagIterator_281(List.of(10, 11, 12), List.of(20, 21, 22));

        assertEquals(1, shortIterator.next());
        assertEquals(10, longIterator.next());
        assertEquals(2, shortIterator.next());
        assertEquals(20, longIterator.next());
        assertEquals(3, shortIterator.next());
        assertEquals(11, longIterator.next());
        assertFalse(shortIterator.hasNext());
        assertEquals(21, longIterator.next());
        assertEquals(12, longIterator.next());
        assertEquals(22, longIterator.next());
        assertFalse(longIterator.hasNext());
    }

    @Test
    public void testManyValuesWithAlternatingDuplicates() {
        List<Integer> first = List.of(0, 1, 0, 1, 0, 1, 0);
        List<Integer> second = List.of(1, 0, 1, 0, 1);
        assertIteratorMatchesOracle(first, second);
    }

    @Test
    public void testUnequalVectorsWithAllNegativeValues() {
        assertIteratorMatchesOracle(List.of(-9, -8), List.of(-7, -6, -5, -4));
    }

    @Test
    public void testExhaustiveSmallVectorsAgainstIndependentOracle() {
        List<Integer> values = List.of(-1, 0, 1);
        for (int firstLength = 0; firstLength <= 5; firstLength++) {
            for (int secondLength = 0; secondLength <= 5; secondLength++) {
                List<Integer> first = generatedValues(values, firstLength, 17 + firstLength);
                List<Integer> second = generatedValues(values, secondLength, 31 + secondLength);
                assertIteratorMatchesOracle(first, second);
            }
        }
    }

    @Test
    public void testSeededRandomVectorsAgainstIndependentOracle() {
        Random random = new Random(281L);
        for (int caseNumber = 0; caseNumber < 120; caseNumber++) {
            int firstLength = random.nextInt(31);
            int secondLength = random.nextInt(31);
            List<Integer> first = new ArrayList<>(firstLength);
            List<Integer> second = new ArrayList<>(secondLength);
            for (int i = 0; i < firstLength; i++) {
                first.add(random.nextInt(2_001) - 1_000);
            }
            for (int i = 0; i < secondLength; i++) {
                second.add(random.nextInt(2_001) - 1_000);
            }
            assertIteratorMatchesOracle(first, second);
        }
    }

    @Test
    public void testMaximumOfficialLengthVectorsPreserveOrder() {
        List<Integer> first = new ArrayList<>(1_000);
        List<Integer> second = new ArrayList<>(1_000);
        for (int i = 0; i < 1_000; i++) {
            first.add(i);
            second.add(10_000 - i);
        }
        assertIteratorMatchesOracle(first, second);
    }

    @Test
    public void testMaximumFirstVectorAndEmptySecondVector() {
        List<Integer> first = new ArrayList<>(1_000);
        for (int i = 0; i < 1_000; i++) {
            first.add(i - 500);
        }
        assertIteratorMatchesOracle(first, List.of());
    }

    @Test
    public void testMaximumSecondVectorAndEmptyFirstVector() {
        List<Integer> second = new ArrayList<>(1_000);
        for (int i = 0; i < 1_000; i++) {
            second.add(500 - i);
        }
        assertIteratorMatchesOracle(List.of(), second);
    }

    @Test
    public void testFreshIteratorCanBeCreatedAfterPriorIteratorExhaustion() {
        List<Integer> first = List.of(1, 2);
        List<Integer> second = List.of(3, 4, 5);
        ZigzagIterator_281 prior = new ZigzagIterator_281(first, second);
        while (prior.hasNext()) {
            prior.next();
        }

        assertIteratorMatchesOracle(first, second);
    }

    @Test
    public void testRepeatedCallsOnSameInstanceUseRemainingElementsOnly() {
        ZigzagIterator_281 iterator = new ZigzagIterator_281(List.of(1, 2, 3), List.of(4));
        assertEquals(1, iterator.next());
        assertEquals(4, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    private static void assertIteratorMatchesOracle(List<Integer> first, List<Integer> second) {
        assertIteratorMatchesOracle(new ZigzagIterator_281(first, second), first, second);
    }

    private static void assertIteratorMatchesOracle(
            ZigzagIterator_281 iterator, List<Integer> first, List<Integer> second) {
        List<Integer> expected = independentRoundRobin(first, second);
        List<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            assertTrue(iterator.hasNext(), "hasNext must be idempotent before next");
            actual.add(iterator.next());
        }
        assertEquals(expected, actual);
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    /** Models the problem's alternating rule using independent per-vector cursors. */
    private static List<Integer> independentRoundRobin(List<Integer> first, List<Integer> second) {
        List<Integer> expected = new ArrayList<>(first.size() + second.size());
        int firstIndex = 0;
        int secondIndex = 0;
        boolean takeFirst = true;
        while (firstIndex < first.size() || secondIndex < second.size()) {
            if (firstIndex < first.size() && (takeFirst || secondIndex >= second.size())) {
                expected.add(first.get(firstIndex++));
                takeFirst = false;
            } else {
                expected.add(second.get(secondIndex++));
                takeFirst = true;
            }
        }
        return expected;
    }

    private static List<Integer> generatedValues(List<Integer> values, int length, int offset) {
        List<Integer> generated = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            generated.add(values.get(Math.floorMod(offset + i, values.size())));
        }
        return generated;
    }
}
