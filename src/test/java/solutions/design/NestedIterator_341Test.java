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
import util.IsolatedTestProcess;

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

    /**
     * Independent recursive oracle; it deliberately does not use iterator state or a stack.
     */
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
    public void testOfficialExampleWithNestedSiblings() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioOfficialExampleWithNestedSiblings", 10);
    }

    private void scenarioOfficialExampleWithNestedSiblings() {
        List<NestedInteger> input = Arrays.asList(
                makeList(makeInt(1), makeInt(1)), makeInt(2),
                makeList(makeInt(1), makeInt(1)));
        assertEquals(List.of(1, 1, 2, 1, 1), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testOfficialExampleWithMixedDepth() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioOfficialExampleWithMixedDepth", 10);
    }

    private void scenarioOfficialExampleWithMixedDepth() {
        List<NestedInteger> input = Arrays.asList(
                makeInt(1), makeList(makeInt(4), makeList(makeInt(6))));
        assertEquals(List.of(1, 4, 6), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testEmptyTopLevelListIsExhausted() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioEmptyTopLevelListIsExhausted", 10);
    }

    private void scenarioEmptyTopLevelListIsExhausted() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    public void testSingletonInteger() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioSingletonInteger", 10);
    }

    private void scenarioSingletonInteger() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeInt(7)));
        assertTrue(iterator.hasNext());
        assertEquals(7, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testSingletonEmptyList() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioSingletonEmptyList", 10);
    }

    private void scenarioSingletonEmptyList() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeList()));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testOnlyEmptyListsAtSeveralDepths() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioOnlyEmptyListsAtSeveralDepths", 10);
    }

    private void scenarioOnlyEmptyListsAtSeveralDepths() {
        NestedInteger empty = makeList();
        List<NestedInteger> input = List.of(empty, makeList(makeList(empty)), makeList());
        assertEquals(List.of(), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testEmptyListsBetweenIntegersDoNotAffectOrder() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioEmptyListsBetweenIntegersDoNotAffectOrder", 10);
    }

    private void scenarioEmptyListsBetweenIntegersDoNotAffectOrder() {
        List<NestedInteger> input = List.of(
                makeList(), makeInt(1), makeList(makeList()), makeInt(2), makeList());
        assertEquals(List.of(1, 2), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testFlatInputPreservesOrder() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioFlatInputPreservesOrder", 10);
    }

    private void scenarioFlatInputPreservesOrder() {
        List<NestedInteger> input = List.of(makeInt(3), makeInt(1), makeInt(4), makeInt(1), makeInt(5));
        assertEquals(List.of(3, 1, 4, 1, 5), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testNestedInputPreservesDepthFirstOrder() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioNestedInputPreservesDepthFirstOrder", 10);
    }

    private void scenarioNestedInputPreservesDepthFirstOrder() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(1), makeList(makeInt(2), makeInt(3)), makeInt(4)),
                makeInt(5), makeList(makeInt(6), makeList(makeInt(7))));
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testNegativeZeroAndOfficialValueBounds() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioNegativeZeroAndOfficialValueBounds", 10);
    }

    private void scenarioNegativeZeroAndOfficialValueBounds() {
        List<NestedInteger> input = List.of(
                makeInt(-1_000_000), makeList(makeInt(-1), makeInt(0)), makeInt(1_000_000));
        assertEquals(List.of(-1_000_000, -1, 0, 1_000_000),
                consume(new NestedIterator_341(input)));
    }

    @Test
    public void testJavaIntegerBoundsAreReturnedWithoutConversion() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioJavaIntegerBoundsAreReturnedWithoutConversion", 10);
    }

    private void scenarioJavaIntegerBoundsAreReturnedWithoutConversion() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(Integer.MIN_VALUE)), makeInt(Integer.MAX_VALUE));
        assertEquals(List.of(Integer.MIN_VALUE, Integer.MAX_VALUE),
                consume(new NestedIterator_341(input)));
    }

    @Test
    public void testDuplicateValuesAreNotDeduplicated() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioDuplicateValuesAreNotDeduplicated", 10);
    }

    private void scenarioDuplicateValuesAreNotDeduplicated() {
        List<NestedInteger> input = List.of(
                makeInt(4), makeList(makeInt(4), makeList(makeInt(4), makeInt(4))), makeInt(4));
        assertEquals(List.of(4, 4, 4, 4, 4), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testDeepSingleValueNesting() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioDeepSingleValueNesting", 10);
    }

    private void scenarioDeepSingleValueNesting() {
        NestedInteger value = makeInt(42);
        for (int depth = 0; depth < 1_200; depth++) {
            value = makeList(value);
        }
        assertEquals(List.of(42), consume(new NestedIterator_341(List.of(value))));
    }

    @Test
    public void testDeepMixedNestingPreservesAllSiblingValues() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioDeepMixedNestingPreservesAllSiblingValues", 10);
    }

    private void scenarioDeepMixedNestingPreservesAllSiblingValues() {
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
    public void testRepeatedHasNextCallsAreIdempotent() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioRepeatedHasNextCallsAreIdempotent", 10);
    }

    private void scenarioRepeatedHasNextCallsAreIdempotent() {
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
    public void testRepeatedHasNextAfterExhaustionStaysFalse() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioRepeatedHasNextAfterExhaustionStaysFalse", 10);
    }

    private void scenarioRepeatedHasNextAfterExhaustionStaysFalse() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeInt(8)));
        assertEquals(8, iterator.next());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNextCanBeCalledWithoutAnInitialHasNext() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioNextCanBeCalledWithoutAnInitialHasNext", 10);
    }

    private void scenarioNextCanBeCalledWithoutAnInitialHasNext() {
        NestedIterator_341 iterator = new NestedIterator_341(
                List.of(makeList(makeList(makeInt(11))), makeInt(12)));
        assertEquals(11, iterator.next());
        assertEquals(12, iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void testNextAfterExhaustionUsesDocumentedNullSentinel() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioNextAfterExhaustionUsesDocumentedNullSentinel", 10);
    }

    private void scenarioNextAfterExhaustionUsesDocumentedNullSentinel() {
        NestedIterator_341 iterator = new NestedIterator_341(List.of(makeInt(-3)));
        assertEquals(-3, iterator.next());
        assertNull(iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void testIrregularShapeMatchesIndependentRecursiveOracle() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioIrregularShapeMatchesIndependentRecursiveOracle", 10);
    }

    private void scenarioIrregularShapeMatchesIndependentRecursiveOracle() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(9), makeList(), makeList(makeInt(8), makeList(makeInt(7)))),
                makeList(makeList(makeInt(6)), makeInt(5)), makeInt(4));
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testRecursiveOracleHandlesSignedDuplicatesAndEmptyBranches() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioRecursiveOracleHandlesSignedDuplicatesAndEmptyBranches", 10);
    }

    private void scenarioRecursiveOracleHandlesSignedDuplicatesAndEmptyBranches() {
        List<NestedInteger> input = List.of(
                makeList(makeInt(-2), makeList(makeInt(-2), makeList())),
                makeList(makeList(makeInt(0))), makeInt(10));
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testMaximumOfficialTopLevelSizeWithFlatValues() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioMaximumOfficialTopLevelSizeWithFlatValues", 10);
    }

    private void scenarioMaximumOfficialTopLevelSizeWithFlatValues() {
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
    public void testMaximumOfficialTopLevelSizeWithNestedValues() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioMaximumOfficialTopLevelSizeWithNestedValues", 10);
    }

    private void scenarioMaximumOfficialTopLevelSizeWithNestedValues() {
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
    public void testLargeFlatAndNestedInputMatchesRecursiveOracle() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioLargeFlatAndNestedInputMatchesRecursiveOracle", 10);
    }

    private void scenarioLargeFlatAndNestedInputMatchesRecursiveOracle() {
        List<NestedInteger> input = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            input.add(i % 3 == 0
                    ? makeList(makeInt(i), makeList(makeInt(i + 1), makeInt(-i)))
                    : makeInt(i));
        }
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testInputListAndNestedObjectsAreNotMutated() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioInputListAndNestedObjectsAreNotMutated", 10);
    }

    private void scenarioInputListAndNestedObjectsAreNotMutated() {
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
    public void testTwoIteratorsHaveIndependentPositions() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioTwoIteratorsHaveIndependentPositions", 10);
    }

    private void scenarioTwoIteratorsHaveIndependentPositions() {
        List<NestedInteger> input = List.of(makeList(makeInt(1), makeInt(2)), makeInt(3));
        NestedIterator_341 first = new NestedIterator_341(input);
        NestedIterator_341 second = new NestedIterator_341(input);

        assertEquals(1, first.next());
        assertEquals(1, second.next());
        assertEquals(List.of(2, 3), consume(first));
        assertEquals(List.of(2, 3), consume(second));
    }

    @Test
    public void testFreshIteratorCanTraverseInputAfterPriorIteratorExhaustion() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioFreshIteratorCanTraverseInputAfterPriorIteratorExhaustion", 10);
    }

    private void scenarioFreshIteratorCanTraverseInputAfterPriorIteratorExhaustion() {
        List<NestedInteger> input = List.of(makeInt(20), makeList(makeInt(21)));
        NestedIterator_341 first = new NestedIterator_341(input);
        assertEquals(List.of(20, 21), consume(first));
        assertEquals(List.of(20, 21), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testInterleavedCallsFollowIteratorContractExactly() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioInterleavedCallsFollowIteratorContractExactly", 10);
    }

    private void scenarioInterleavedCallsFollowIteratorContractExactly() {
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
    public void testManySmallBranchesMatchIndependentOracle() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioManySmallBranchesMatchIndependentOracle", 10);
    }

    private void scenarioManySmallBranchesMatchIndependentOracle() {
        List<NestedInteger> input = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            input.add(makeList(makeList(), makeInt(i), makeList(makeInt(i * -2))));
        }
        assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)));
    }

    @Test
    public void testExhaustiveShortTopLevelShapesAgainstIndependentOracle() throws Exception {
        IsolatedTestProcess.run(NestedIterator_341Test.class, "scenarioExhaustiveShortTopLevelShapesAgainstIndependentOracle", 10);
    }

    private void scenarioExhaustiveShortTopLevelShapesAgainstIndependentOracle() {
        // Enumerate sequences of length 0..4 over nine fixed shapes, including nonempty nested lists.
        // This exercises nested order and reversal through depth three; arbitrary widths, depths,
        // and values outside {-1, 0, 1} are covered only by the explicit cases elsewhere in this suite.
        int totalCases = 0;
        for (int length = 0; length <= 4; length++) {
            int caseCount = (int) Math.pow(9, length);
            for (int encoded = 0; encoded < caseCount; encoded++) {
                int remaining = encoded;
                List<NestedInteger> input = new ArrayList<>();
                for (int index = 0; index < length; index++) {
                    int symbol = remaining % 9;
                    remaining /= 9;
                    input.add(makeShape(symbol));
                }
                assertEquals(recursiveFlatten(input), consume(new NestedIterator_341(input)),
                        "input encoding (base 9)=" + encoded + ", length=" + length);
                totalCases++;
            }
        }
        assertEquals(7381, totalCases);
    }

    private NestedInteger makeShape(int symbol) {
        return switch (symbol) {
            case 0 -> makeInt(-1);
            case 1 -> makeInt(0);
            case 2 -> makeInt(1);
            case 3 -> makeList();
            case 4 -> makeList(makeInt(-1));
            case 5 -> makeList(makeInt(0));
            case 6 -> makeList(makeInt(-1), makeInt(0));
            case 7 -> makeList(makeInt(0), makeInt(-1));
            case 8 -> makeList(makeList(makeInt(1)));
            default -> throw new IllegalArgumentException("Unknown shape: " + symbol);
        };
    }
}
