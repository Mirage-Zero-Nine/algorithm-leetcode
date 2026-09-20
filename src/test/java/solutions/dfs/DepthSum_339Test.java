package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import library.NestedInteger;
import org.junit.jupiter.api.Test;

/** Contract tests for the top-down weighted sum of a nested integer list. */
public class DepthSum_339Test {

    private final DepthSum_339 solution = new DepthSum_339();

    @Test
    public void nullTopLevelListIsZero() {
        assertEquals(0, solution.depthSum(null));
    }

    @Test
    public void emptyTopLevelListIsZero() {
        assertEquals(0, solution.depthSum(List.of()));
    }

    @Test
    public void oneTopLevelIntegerHasWeightOne() {
        assertCase(List.of(integer(5)));
    }

    @Test
    public void topLevelZeroContributesZero() {
        assertCase(List.of(integer(0)));
    }

    @Test
    public void topLevelNegativeIntegerKeepsItsSign() {
        assertCase(List.of(integer(-7)));
    }

    @Test
    public void flatValuesUseWeightOne() {
        assertEquals(7, solution.depthSum(List.of(integer(-3), integer(0), integer(10))));
    }

    @Test
    public void officialTwoNestedListsExample() {
        assertEquals(10, solution.depthSum(List.of(
                listOf(integer(1), integer(1)),
                integer(2),
                listOf(integer(1), integer(1)))));
    }

    @Test
    public void officialMixedDepthExample() {
        assertEquals(27, solution.depthSum(List.of(
                integer(1), listOf(integer(4), listOf(integer(6))))));
    }

    @Test
    public void officialZeroExample() {
        assertEquals(0, solution.depthSum(List.of(integer(0))));
    }

    @Test
    public void emptyNestedListContributesNothing() {
        assertCase(List.of(new NestedInteger(), integer(8), new NestedInteger()));
    }

    @Test
    public void onlyEmptyNestedListsAreZero() {
        assertEquals(0, solution.depthSum(List.of(new NestedInteger(), new NestedInteger())));
    }

    @Test
    public void emptyListsDoNotIncreaseSiblingWeights() {
        assertEquals(5, solution.depthSum(List.of(
                new NestedInteger(), integer(2), listOf(new NestedInteger()), integer(3))));
    }

    @Test
    public void siblingsHaveIndependentDepths() {
        assertCase(List.of(
                listOf(integer(1), listOf(integer(2))),
                listOf(listOf(integer(3)), integer(4)),
                integer(5)));
    }

    @Test
    public void equalValuesAtDifferentDepthsReceiveDifferentWeights() {
        assertEquals(12, solution.depthSum(List.of(
                integer(2), listOf(integer(2)), listOf(listOf(integer(2))))));
    }

    @Test
    public void negativeValuesCanCancelAcrossDepths() {
        assertEquals(0, solution.depthSum(List.of(
                integer(2), listOf(integer(-1), listOf(integer(0))))));
    }

    @Test
    public void mixedSignedValuesAtSeveralDepths() {
        assertCase(List.of(
                integer(-5),
                listOf(integer(4), listOf(integer(-3), integer(2))),
                listOf(listOf(integer(1), integer(-1)))));
    }

    @Test
    public void officialMagnitudeBoundsAtDepthOne() {
        assertEquals(0, solution.depthSum(List.of(integer(-100), integer(100))));
    }

    @Test
    public void officialMagnitudeBoundsAtDepthTwo() {
        assertEquals(-100, solution.depthSum(List.of(
                listOf(integer(-100)), listOf(integer(100)), integer(-100))));
    }

    @Test
    public void zeroAtAnyDepthRemainsZero() {
        assertEquals(4, solution.depthSum(List.of(integer(4), listOf(listOf(integer(0))))));
    }

    @Test
    public void fourLevelChainHasCorrectWeight() {
        assertEquals(36, solution.depthSum(List.of(
                listOf(listOf(listOf(integer(9)))))));
    }

    @Test
    public void maximumDocumentedDepthHasWeightFifty() {
        NestedInteger current = integer(100);
        for (int i = 0; i < 49; i++) {
            current = listOf(current);
        }
        assertEquals(5_000, solution.depthSum(List.of(current)));
    }

    @Test
    public void maximumDocumentedDepthSupportsNegativeValues() {
        NestedInteger current = integer(-100);
        for (int i = 0; i < 49; i++) {
            current = listOf(current);
        }
        assertEquals(-5_000, solution.depthSum(List.of(current)));
    }

    @Test
    public void deeplyNestedEmptyBranchesDoNotAffectValues() {
        NestedInteger empty = new NestedInteger();
        for (int i = 0; i < 49; i++) {
            empty = listOf(empty);
        }
        assertEquals(7, solution.depthSum(List.of(empty, integer(7))));
    }

    @Test
    public void maximumTopLevelLengthUsesWeightOne() {
        List<NestedInteger> values = new ArrayList<>();
        for (int value = 1; value <= 50; value++) {
            values.add(integer(value));
        }
        assertEquals(1_275, solution.depthSum(values));
    }

    @Test
    public void wideNestedLevelCountsEveryChild() {
        List<NestedInteger> children = new ArrayList<>();
        for (int value = 1; value <= 50; value++) {
            children.add(integer(value));
        }
        assertEquals(2_550, solution.depthSum(List.of(listOf(children.toArray(new NestedInteger[0])))));
    }

    @Test
    public void multipleWideBranchesUseTheirOwnDepth() {
        List<NestedInteger> first = new ArrayList<>();
        List<NestedInteger> second = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            first.add(integer(1));
            second.add(integer(-1));
        }
        assertEquals(0, solution.depthSum(List.of(listOf(first.toArray(new NestedInteger[0])),
                listOf(second.toArray(new NestedInteger[0])))));
    }

    @Test
    public void iterativeOracleChecksAnIrregularTree() {
        List<NestedInteger> input = List.of(
                integer(6),
                listOf(integer(-2), listOf(integer(3), integer(4))),
                listOf(listOf(integer(5)), integer(-1)));
        assertEquals(iterativeOracle(input), solution.depthSum(input));
    }

    @Test
    public void recursiveOracleChecksADeepAndWideTree() {
        List<NestedInteger> children = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            NestedInteger branch = integer(i % 7 - 3);
            for (int depth = 0; depth < i % 6; depth++) {
                branch = listOf(branch);
            }
            children.add(branch);
        }
        List<NestedInteger> input = List.of(listOf(children.toArray(new NestedInteger[0])), integer(-9));
        assertEquals(recursiveOracle(input, 1), solution.depthSum(input));
    }

    @Test
    public void resultIsIndependentOfSiblingOrder() {
        List<NestedInteger> first = List.of(integer(1), listOf(integer(2), integer(3)), integer(-4));
        List<NestedInteger> second = List.of(integer(-4), listOf(integer(3), integer(2)), integer(1));
        assertEquals(solution.depthSum(first), solution.depthSum(second));
    }

    @Test
    public void inputStructureIsNotMutated() {
        NestedInteger nested = listOf(integer(2), listOf(integer(3)));
        List<NestedInteger> input = new ArrayList<>(List.of(integer(1), nested));
        NestedInteger first = input.get(0);
        NestedInteger second = input.get(1);
        assertEquals(14, solution.depthSum(input));
        assertEquals(14, solution.depthSum(input));
        assertSame(first, input.get(0));
        assertSame(second, input.get(1));
        assertEquals(2, nested.getList().size());
    }

    @Test
    public void sameInstanceCanBeReusedWithIndependentInputs() {
        assertEquals(27, solution.depthSum(List.of(integer(1), listOf(integer(4), listOf(integer(6))))));
        assertEquals(-5, solution.depthSum(List.of(integer(-5))));
        assertEquals(10, solution.depthSum(List.of(listOf(integer(1), integer(1)), integer(2),
                listOf(integer(1), integer(1)))));
    }

    private void assertCase(List<NestedInteger> input) {
        assertEquals(recursiveOracle(input, 1), solution.depthSum(input));
    }

    private static NestedInteger integer(int value) {
        return new NestedInteger(value);
    }

    private static NestedInteger listOf(NestedInteger... values) {
        NestedInteger list = new NestedInteger();
        for (NestedInteger value : values) {
            list.add(value);
        }
        return list;
    }

    private static int recursiveOracle(List<NestedInteger> values, int depth) {
        if (values == null) {
            return 0;
        }
        int result = 0;
        for (NestedInteger value : values) {
            if (value.isInteger()) {
                result += value.getInteger() * depth;
            } else {
                result += recursiveOracle(value.getList(), depth + 1);
            }
        }
        return result;
    }

    private static int iterativeOracle(List<NestedInteger> values) {
        if (values == null) {
            return 0;
        }
        record Work(NestedInteger value, int depth) {}
        Deque<Work> pending = new ArrayDeque<>();
        for (int i = values.size() - 1; i >= 0; i--) {
            pending.push(new Work(values.get(i), 1));
        }
        int result = 0;
        while (!pending.isEmpty()) {
            Work work = pending.pop();
            if (work.value().isInteger()) {
                result += work.value().getInteger() * work.depth();
            } else {
                List<NestedInteger> children = work.value().getList();
                for (int i = children.size() - 1; i >= 0; i--) {
                    pending.push(new Work(children.get(i), work.depth() + 1));
                }
            }
        }
        return result;
    }
}
