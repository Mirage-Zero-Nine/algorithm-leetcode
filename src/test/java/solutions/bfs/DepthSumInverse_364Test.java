package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import library.NestedInteger;
import org.junit.jupiter.api.Test;

/** Contract tests for the bottom-up weighted nested-list sum. */
public class DepthSumInverse_364Test {

    @Test
    public void nullInputIsZero() {
        assertEquals(0, solve(null));
    }

    @Test
    public void emptyInputIsZero() {
        assertEquals(0, solve(List.of()));
    }

    @Test
    public void oneIntegerHasWeightOne() {
        assertCase(List.of(integer(5)));
    }

    @Test
    public void flatPositiveValues() {
        assertCase(List.of(integer(1), integer(2), integer(3), integer(4)));
    }

    @Test
    public void originalFlatThreeValues() {
        assertCase(List.of(integer(1), integer(2), integer(3)));
    }

    @Test
    public void flatNegativeValues() {
        assertCase(List.of(integer(-7), integer(-2), integer(5)));
    }

    @Test
    public void flatZeros() {
        assertCase(List.of(integer(0), integer(0), integer(0)));
    }

    @Test
    public void canonicalMixedDepthExample() {
        assertCase(List.of(integer(1), listOf(integer(4), listOf(integer(6)))));
    }

    @Test
    public void valuesAtSeveralDepthsAndSiblings() {
        assertCase(List.of(integer(1), listOf(integer(4), listOf(integer(6))), integer(2)));
    }

    @Test
    public void originalFiveFlatValues() {
        assertCase(List.of(integer(1), integer(1), integer(2), integer(1), integer(1)));
    }

    @Test
    public void originalTwoNestedListsAndRootValue() {
        assertCase(List.of(
                listOf(integer(1), integer(1)),
                integer(2),
                listOf(integer(1), integer(1))));
    }

    @Test
    public void negativeValuesCanCancelAtDifferentDepths() {
        assertCase(List.of(integer(1), listOf(integer(-1), listOf(integer(2)))));
    }

    @Test
    public void negativeNestedValues() {
        assertCase(List.of(listOf(integer(-1), integer(-2)), integer(3)));
    }

    @Test
    public void originalNegativeNestedValues() {
        assertCase(List.of(listOf(integer(-1), integer(-2))));
    }

    @Test
    public void emptyNestedListsContributeNoInteger() {
        assertCase(List.of(new NestedInteger(), new NestedInteger(), integer(8)));
    }

    @Test
    public void originalTwoEmptyListsAreZero() {
        assertEquals(0, solve(List.of(new NestedInteger(), new NestedInteger())));
    }

    @Test
    public void emptyListsBeforeAndBetweenValuesDoNotChangeWeights() {
        List<NestedInteger> input = List.of(
                new NestedInteger(),
                integer(2),
                listOf(new NestedInteger()),
                integer(3));
        assertEquals(5, solve(input));
    }

    @Test
    public void trailingEmptyLevelsDoNotChangeTheAnswer() {
        assertEquals(7, solve(List.of(integer(7), listOf(listOf(new NestedInteger())))));
    }

    @Test
    public void zeroIntegerStillEstablishesItsDepth() {
        assertEquals(3, solve(List.of(integer(1), listOf(listOf(integer(0))))));
    }

    @Test
    public void emptyListsMayBeDeeplyNested() {
        NestedInteger empty = new NestedInteger();
        for (int i = 0; i < 49; i++) {
            empty = listOf(empty);
        }
        assertCase(List.of(empty, integer(7)));
    }

    @Test
    public void deeplyNestedIntegerStillHasBottomWeightOne() {
        NestedInteger current = integer(9);
        for (int i = 0; i < 49; i++) {
            current = listOf(current);
        }
        assertCase(List.of(current));
    }

    @Test
    public void originalDepthFourChain() {
        assertCase(List.of(listOf(listOf(listOf(integer(9))))));
    }

    @Test
    public void originalMixedDepthChain() {
        assertCase(List.of(integer(1), listOf(listOf(integer(2)))));
    }

    @Test
    public void deepChainWithValuesAtBothEnds() {
        NestedInteger current = integer(6);
        for (int i = 0; i < 10; i++) {
            current = listOf(current);
        }
        assertCase(List.of(integer(3), current));
    }

    @Test
    public void multipleBranchesHaveIndependentDepths() {
        assertCase(List.of(
                listOf(integer(1), listOf(integer(2))),
                listOf(listOf(integer(3)), integer(4)),
                integer(5)));
    }

    @Test
    public void repeatedValuesAreCountedIndividually() {
        assertCase(List.of(
                listOf(integer(2), integer(2)),
                listOf(integer(2), listOf(integer(2), integer(2)))));
    }

    @Test
    public void singletonNestedListsAtDifferentDepths() {
        assertCase(List.of(
                listOf(integer(1)),
                listOf(listOf(integer(2))),
                listOf(listOf(listOf(integer(3))))));
    }

    @Test
    public void zeroAtAnUpperLevelDoesNotAffectTheSum() {
        assertCase(List.of(integer(0), listOf(integer(4), listOf(integer(-4)))));
    }

    @Test
    public void largeMagnitudeValuesRemainSigned() {
        assertCase(List.of(integer(100), listOf(integer(-100), listOf(integer(100)))));
    }

    @Test
    public void wideFlatListWithinTheProblemLimit() {
        List<NestedInteger> values = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            values.add(integer(i % 11 - 5));
        }
        assertCase(values);
    }

    @Test
    public void wideNestedLevelWithinTheProblemLimit() {
        List<NestedInteger> children = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            children.add(integer(i % 9 - 4));
        }
        assertCase(List.of(listOf(children.toArray(new NestedInteger[0])), integer(7)));
    }

    @Test
    public void deepestLevelMayContainSeveralIntegers() {
        assertCase(List.of(integer(10), listOf(integer(1), listOf(integer(2), integer(3)))));
    }

    @Test
    public void repeatedInvocationUsesNoStaleAccumulator() {
        DepthSumInverse_364 solution = new DepthSumInverse_364();
        List<NestedInteger> first = List.of(integer(1), listOf(integer(2)));
        List<NestedInteger> second = List.of(integer(10));
        assertEquals(reference(first), solution.depthSumInverse(first));
        assertEquals(reference(second), solution.depthSumInverse(second));
        assertEquals(reference(first), solution.depthSumInverse(first));
    }

    @Test
    public void inputStructureRemainsUsableAfterCall() {
        NestedInteger child = listOf(integer(4), listOf(integer(6)));
        List<NestedInteger> input = new ArrayList<>(List.of(integer(1), child));
        String before = shape(input);
        int expected = reference(input);

        assertEquals(expected, solve(input));
        assertEquals(before, shape(input));
        assertEquals(expected, solve(input));
        assertEquals(before, shape(input));
    }

    private void assertCase(List<NestedInteger> input) {
        assertEquals(reference(input), solve(input));
    }

    private int solve(List<NestedInteger> input) {
        return new DepthSumInverse_364().depthSumInverse(input);
    }

    /** Independent top-down oracle: find deepest integer level, then apply inverse weights. */
    private int reference(List<NestedInteger> input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        List<int[]> leaves = new ArrayList<>();
        collect(input, 1, leaves);
        int deepest = 0;
        for (int[] leaf : leaves) {
            deepest = Math.max(deepest, leaf[1]);
        }
        int answer = 0;
        for (int[] leaf : leaves) {
            answer += leaf[0] * (deepest - leaf[1] + 1);
        }
        return answer;
    }

    private void collect(List<NestedInteger> values, int depth, List<int[]> leaves) {
        for (NestedInteger value : values) {
            if (value.isInteger()) {
                leaves.add(new int[]{value.getInteger(), depth});
            } else if (value.getList() != null) {
                collect(value.getList(), depth + 1, leaves);
            }
        }
    }

    private String shape(List<NestedInteger> values) {
        StringBuilder result = new StringBuilder("[");
        for (NestedInteger value : values) {
            if (value.isInteger()) {
                result.append(value.getInteger());
            } else {
                result.append(shape(value.getList()));
            }
            result.append(',');
        }
        return result.append(']').toString();
    }

    private static NestedInteger integer(int value) {
        return new NestedInteger(value);
    }

    private static NestedInteger listOf(NestedInteger... values) {
        NestedInteger result = new NestedInteger();
        for (NestedInteger value : values) {
            result.add(value);
        }
        return result;
    }
}
