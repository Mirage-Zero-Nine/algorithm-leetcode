package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import library.NestedInteger;
import library.NestedIntegerHelper;
import org.junit.jupiter.api.Test;

/** Tests the lazy depth-first iterator for LeetCode 341. */
public class NestedIterator_341Test {

    private NestedInteger makeInt(int value) {
        return new NestedInteger(value);
    }

    private NestedInteger makeList(NestedInteger... items) {
        return new NestedIntegerHelper(items);
    }

    private List<Integer> consume(NestedIterator_341 iterator) {
        List<Integer> values = new ArrayList<>();
        while (iterator.hasNext()) {
            values.add(iterator.next());
        }
        return values;
    }

    /** Independent recursive oracle; it deliberately does not use iterator state or a stack. */
    private List<Integer> recursiveFlatten(List<NestedInteger> nestedList) {
        List<Integer> values = new ArrayList<>();
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                values.add(nestedInteger.getInteger());
            } else {
                values.addAll(recursiveFlatten(nestedInteger.getList()));
            }
        }
        return values;
    }

    @Test
    public void testOfficialExampleWithNestedSiblings() {
        List<NestedInteger> input = Arrays.asList(
                makeList(makeInt(1), makeInt(1)), makeInt(2),
                makeList(makeInt(1), makeInt(1)));
        assertEquals(List.of(1, 1, 2, 1, 1), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testOfficialExampleWithMixedDepth() {
        List<NestedInteger> input = Arrays.asList(
                makeInt(1), makeList(makeInt(4), makeList(makeInt(6))));
        assertEquals(List.of(1, 4, 6), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testEmptyTopLevelListIsExhausted() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void testSingletonInteger() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeInt(7)));
        assertTrue(iterator.hasNext());
        assertEquals(7, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSingletonEmptyList() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeList()));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testOnlyEmptyListsAtSeveralDepths() {
        NestedInteger empty = makeList();
        List<NestedInteger> input = List.of(empty, makeList(makeList(empty)), makeList());
        assertEquals(List.of(), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testEmptyListsBetweenIntegersDoNotAffectOrder() {
        List<NestedInteger> input = List.of(
                makeList(), makeInt(1), makeList(makeList()), makeInt(2), makeList());
        assertEquals(List.of(1, 2), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testFlatInputPreservesOrder() {
        List<NestedInteger> input = List.of(makeInt(3), makeInt(1), makeInt(4), makeInt(1), makeInt(5));
        assertEquals(List.of(3, 1, 4, 1, 5), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testNestedInputPreservesDepthFirstOrder() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(1), makeList(makeInt(2), makeInt(3)), makeInt(4)),
                makeInt(5), makeList(makeInt(6), makeList(makeInt(7))));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testNegativeZeroAndOfficialValueBounds() {
        List<NestedInteger> input = List.of(
                makeInt(-1_000_000), makeList(makeInt(-1), makeInt(0)), makeInt(1_000_000));
        assertEquals(List.of(-1_000_000, -1, 0, 1_000_000),
                consume(new NestedIterator_341(input)));
    }

    @Test
    public void testJavaIntegerBoundsAreReturnedWithoutConversion() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(Integer.MIN_VALUE)), makeInt(Integer.MAX_VALUE));
        assertEquals(List.of(Integer.MIN_VALUE, Integer.MAX_VALUE),
                consume(new NestedIterator_341(input)));
    }

    @Test
    public void testDuplicateValuesAreNotDeduplicated() {
        List<NestedInteger> input = List.of(
                makeInt(4), makeList(makeInt(4), makeList(makeInt(4), makeInt(4))), makeInt(4));
        assertEquals(List.of(4, 4, 4, 4, 4), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testDeepSingleValueNesting() {
        NestedInteger value = makeInt(42);
        for (int depth = 0; depth < 1_200; depth++) {
            value = makeList(value);
        }
        assertEquals(List.of(42), consume(new NestedIterator_341(List.of(value))));
    }

    @Test
    public void testDeepMixedNestingPreservesAllSiblingValues() {
        NestedInteger tail = makeInt(999);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        for (int depth = 1; depth <= 300; depth++) {
            tail = makeList(makeInt(depth), tail);
            expected.add(1, depth);
        }
        expected.add(999);
        List<Integer> actual = consume(new NestedIterator_341(List.of(makeList(makeInt(0), tail))));
        assertEquals(expected, actual);
    }

    @Test
    public void testRepeatedHasNextCallsAreIdempotent() {
        NestedIterator_341 iterator = new NestedIterator_341(
                List.of(makeList(makeInt(1), makeInt(2)), makeInt(3)));
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRepeatedHasNextAfterExhaustionStaysFalse() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeInt(8)));
        assertEquals(8, iterator.next());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNextCanBeCalledWithoutAnInitialHasNext() {
        NestedIterator_341 iterator = new NestedIterator_341(
                List.of(makeList(makeList(makeInt(11))), makeInt(12)));
        assertEquals(11, iterator.next());
        assertEquals(12, iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void testNextAfterExhaustionUsesDocumentedNullSentinel() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeInt(-3)));
        assertEquals(-3, iterator.next());
        assertNull(iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void testIrregularShapeMatchesIndependentRecursiveOracle() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(9), makeList(), makeList(makeInt(8), makeList(makeInt(7)))),
                makeList(makeList(makeInt(6)), makeInt(5)), makeInt(4));
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testRecursiveOracleHandlesSignedDuplicatesAndEmptyBranches() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(-2), makeList(makeInt(-2), makeList())),
                makeList(makeList(makeInt(0))), makeInt(10));
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testMaximumOfficialTopLevelSizeWithFlatValues() {
        List<NestedInteger> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            int value = i - 250;
            input.add(makeInt(value));
            expected.add(value);
        }
        assertEquals(expected, consume(new NestedIterator_341(input)));
    }

    @Test
    public void testMaximumOfficialTopLevelSizeWithNestedValues() {
        List<NestedInteger> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            input.add(makeList(makeInt(i), makeList(makeInt(-i))));
            expected.add(i);
            expected.add(-i);
        }
        assertEquals(expected, consume(new NestedIterator_341(input)));
    }

    @Test
    public void testLargeFlatAndNestedInputMatchesRecursiveOracle() {
        List<NestedInteger> input = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            input.add(i % 3 == 0
                    ? makeList(makeInt(i), makeList(makeInt(i + 1), makeInt(-i)))
                    : makeInt(i));
        }
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testInputListAndNestedObjectsAreNotMutated() {
        NestedInteger first = makeList(makeInt(1), makeList(makeInt(2)));
        NestedInteger second = makeInt(3);
        List<NestedInteger> input = new ArrayList<>(List.of(first, second));
        List<NestedInteger> originalElements = new ArrayList<>(input);
        List<NestedInteger> originalChildren = new ArrayList<>(first.getList());

        assertEquals(List.of(1, 2, 3), consume(new NestedIterator_341(input)));
        assertEquals(originalElements, input);
        assertSame(originalChildren.get(0), first.getList().get(0));
        assertSame(originalChildren.get(1), first.getList().get(1));
        assertEquals(originalChildren.size(), first.getList().size());
    }

    @Test
    public void testTwoIteratorsHaveIndependentPositions() {
        List<NestedInteger> input = List.of(makeList(makeInt(1), makeInt(2)), makeInt(3));
        NestedIterator_341 first = new NestedIterator_341(input);
        NestedIterator_341 second = new NestedIterator_341(input);

        assertEquals(1, first.next());
        assertEquals(1, second.next());
        assertEquals(List.of(2, 3), consume(first));
        assertEquals(List.of(2, 3), consume(second));
    }

    @Test
    public void testFreshIteratorCanTraverseInputAfterPriorIteratorExhaustion() {
        List<NestedInteger> input = List.of(makeInt(20), makeList(makeInt(21)));
        NestedIterator_341 first = new NestedIterator_341(input);
        assertEquals(List.of(20, 21), consume(first));
        assertEquals(List.of(20, 21), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testInterleavedCallsFollowIteratorContractExactly() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(
                makeList(makeInt(1), makeList(makeInt(2))), makeInt(3)));
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testManySmallBranchesMatchIndependentOracle() {
        List<NestedInteger> input = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            input.add(makeList(makeList(), makeInt(i), makeList(makeInt(i * -2))));
        }
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }
}
