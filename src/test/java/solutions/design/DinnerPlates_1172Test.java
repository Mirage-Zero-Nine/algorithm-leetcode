package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class DinnerPlates_1172Test {

    @Test
    public void testHappyCases() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1); dp.push(2); dp.push(3); dp.push(4); dp.push(5);
        assertEquals(2, dp.popAtStack(0));
        dp.push(20); dp.push(21);
        assertEquals(20, dp.popAtStack(0));
        assertEquals(21, dp.popAtStack(2));
        assertEquals(5, dp.pop());
        assertEquals(4, dp.pop());
        assertEquals(3, dp.pop());
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(1);
        assertEquals(-1, dp.pop());
        assertEquals(-1, dp.popAtStack(0));
    }

    @Test
    public void testLargeCase() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(3);
        for (int i = 1; i <= 9; i++) dp.push(i);
        assertEquals(9, dp.pop());
        assertEquals(8, dp.pop());
    }

    @Test
    public void testPopAtMissingIndexReturnsNegativeOne() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        assertEquals(-1, dp.popAtStack(3));
    }

    @Test
    public void testPushFillsLeftmostAvailableStack() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        dp.push(4);
        assertEquals(2, dp.popAtStack(0));
        dp.push(5);
        assertEquals(5, dp.popAtStack(0));
    }

    @Test
    public void testPopReturnsRightmostNonEmptyStackValue() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        assertEquals(3, dp.pop());
        assertEquals(2, dp.pop());
    }

    @Test
    public void testPopAtStackCanEmptyMiddleStack() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(10);
        dp.push(11);
        dp.push(20);
        dp.push(21);
        assertEquals(21, dp.popAtStack(1));
        assertEquals(20, dp.popAtStack(1));
        assertEquals(-1, dp.popAtStack(1));
    }

    @Test
    public void testInterleavedPopAtAndPushAcrossSparseStacks() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int i = 1; i <= 6; i++) {
            dp.push(i);
        }
        assertEquals(4, dp.popAtStack(1));
        assertEquals(6, dp.popAtStack(2));
        dp.push(7);
        dp.push(8);
        assertEquals(7, dp.popAtStack(1));
        assertEquals(8, dp.popAtStack(2));
    }

    @Test
    public void testRepeatedPopOnEmptyStructure() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(3);
        assertEquals(-1, dp.pop());
        assertEquals(-1, dp.pop());
        dp.push(1);
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testGiantCase() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(5);
        for (int i = 1; i <= 5000; i++) {
            dp.push(i);
        }
        for (int i = 5000; i >= 4501; i--) {
            assertEquals(i, dp.pop());
        }
        for (int i = 0; i < 200; i++) {
            assertEquals(-1, dp.popAtStack(2000 + i));
        }
    }

    @Test
    public void testOfficialSequenceWithSparseStacks() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        dp.push(4);
        dp.push(5);

        assertEquals(2, dp.popAtStack(0));
        dp.push(20);
        dp.push(21);
        assertEquals(20, dp.popAtStack(0));
        assertEquals(21, dp.popAtStack(2));
        assertEquals(5, dp.pop());
        assertEquals(4, dp.pop());
        assertEquals(3, dp.pop());
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testCapacityOneAlwaysUsesLeftmostAvailableStack() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(1);
        for (int value = 1; value <= 5; value++) {
            dp.push(value);
        }
        assertEquals(2, dp.popAtStack(1));
        assertEquals(4, dp.popAtStack(3));
        dp.push(6);
        dp.push(7);
        assertEquals(6, dp.popAtStack(1));
        assertEquals(7, dp.popAtStack(3));
        assertEquals(5, dp.pop());
        assertEquals(3, dp.pop());
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testPushUsesTheLeftmostOfSeveralHoles() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int value = 1; value <= 8; value++) {
            dp.push(value);
        }
        assertEquals(4, dp.popAtStack(1));
        assertEquals(6, dp.popAtStack(2));
        assertEquals(2, dp.popAtStack(0));
        dp.push(20);
        dp.push(21);
        dp.push(22);
        assertEquals(20, dp.popAtStack(0));
        assertEquals(21, dp.popAtStack(1));
        assertEquals(22, dp.popAtStack(2));
    }

    @Test
    public void testPopSkipsEmptyTrailingAndMiddleStacks() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int value = 1; value <= 6; value++) {
            dp.push(value);
        }
        assertEquals(6, dp.popAtStack(2));
        assertEquals(4, dp.popAtStack(1));
        assertEquals(3, dp.popAtStack(1));
        assertEquals(5, dp.pop());
        assertEquals(2, dp.pop());
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testPopAtStackCanBeRepeatedAfterStackBecomesEmpty() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(3);
        dp.push(10);
        dp.push(11);
        dp.push(12);
        assertEquals(12, dp.popAtStack(0));
        assertEquals(11, dp.popAtStack(0));
        assertEquals(10, dp.popAtStack(0));
        assertEquals(-1, dp.popAtStack(0));
        assertEquals(-1, dp.popAtStack(1));
    }

    @Test
    public void testPushReusesAnEmptiedTrailingStackBeforeCreatingOne() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int value = 1; value <= 4; value++) {
            dp.push(value);
        }
        assertEquals(4, dp.popAtStack(1));
        assertEquals(3, dp.popAtStack(1));
        dp.push(9);
        dp.push(10);
        assertEquals(10, dp.popAtStack(1));
        assertEquals(9, dp.popAtStack(1));
        assertEquals(-1, dp.popAtStack(2));
    }

    @Test
    public void testPopThenPushKeepsTheSmallestAvailableIndex() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int value = 1; value <= 6; value++) {
            dp.push(value);
        }
        assertEquals(6, dp.pop());
        assertEquals(4, dp.popAtStack(1));
        dp.push(20);
        assertEquals(20, dp.popAtStack(1));
        assertEquals(5, dp.pop());
    }

    @Test
    public void testInvalidAndUnmaterializedIndicesReturnMinusOne() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        assertEquals(-1, dp.popAtStack(-1));
        assertEquals(-1, dp.popAtStack(0));
        dp.push(1);
        assertEquals(-1, dp.popAtStack(1));
        assertEquals(-1, dp.popAtStack(100_000));
        assertEquals(1, dp.popAtStack(0));
        assertEquals(-1, dp.popAtStack(0));
    }

    @Test
    public void testDuplicateValuesRemainIndividuallyObservable() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int i = 0; i < 7; i++) {
            dp.push(42);
        }
        for (int i = 0; i < 7; i++) {
            assertEquals(42, dp.pop());
        }
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testPositiveIntegerBoundaryValues() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        dp.push(20_000);
        dp.push(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, dp.pop());
        assertEquals(20_000, dp.pop());
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testLargeCapacityDoesNotSplitUntilCapacityIsReached() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(20_000);
        dp.push(1);
        for (int i = 2; i <= 20_000; i++) {
            dp.push(i);
        }
        assertEquals(-1, dp.popAtStack(1));
        dp.push(20_001);
        assertEquals(20_001, dp.popAtStack(1));
        assertEquals(20_000, dp.popAtStack(0));
    }

    @Test
    public void testIndependentInstancesDoNotShareStacks() {
        DinnerPlates_1172 first = new DinnerPlates_1172(2);
        DinnerPlates_1172 second = new DinnerPlates_1172(1);
        first.push(1);
        first.push(2);
        second.push(100);
        second.push(200);
        assertEquals(2, first.pop());
        assertEquals(1, first.pop());
        assertEquals(200, second.pop());
        assertEquals(100, second.pop());
        assertEquals(-1, first.pop());
        assertEquals(-1, second.pop());
    }

    @Test
    public void testSameInstanceCanBeReusedAfterBecomingEmpty() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(3);
        for (int value = 1; value <= 9; value++) {
            dp.push(value);
        }
        for (int value = 9; value >= 1; value--) {
            assertEquals(value, dp.pop());
        }
        assertEquals(-1, dp.pop());
        dp.push(50);
        dp.push(51);
        assertEquals(51, dp.pop());
        assertEquals(50, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testInterleavedOperationsWithManyPartiallyFilledStacks() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(3);
        for (int value = 1; value <= 12; value++) {
            dp.push(value);
        }
        assertEquals(6, dp.popAtStack(1));
        assertEquals(9, dp.popAtStack(2));
        assertEquals(12, dp.popAtStack(3));
        dp.push(100);
        dp.push(101);
        dp.push(102);
        assertEquals(100, dp.popAtStack(1));
        assertEquals(101, dp.popAtStack(2));
        assertEquals(102, dp.popAtStack(3));
        assertEquals(11, dp.pop());
        assertEquals(10, dp.pop());
        assertEquals(8, dp.pop());
    }

    @Test
    public void testOracleRandomizedCapacityOne() {
        assertRandomOperationsMatchOracle(1, 2_000, 1172L);
    }

    @Test
    public void testOracleRandomizedCapacityTwo() {
        assertRandomOperationsMatchOracle(2, 5_000, 2024L);
    }

    @Test
    public void testOracleRandomizedCapacityFive() {
        assertRandomOperationsMatchOracle(5, 8_000, 8675309L);
    }

    @Test
    public void testOracleRandomizedWithLargeInvalidIndices() {
        DinnerPlates_1172 actual = new DinnerPlates_1172(3);
        List<List<Integer>> expected = new ArrayList<>();
        for (int value = 1; value <= 30; value++) {
            push(expected, 3, value);
            actual.push(value);
        }
        int[] indices = {-10, -1, 30, 31, 100_000, 2, 2, 0, 29};
        for (int index : indices) {
            assertEquals(popAtStack(expected, index), actual.popAtStack(index));
        }
        while (hasValues(expected)) {
            assertEquals(pop(expected), actual.pop());
        }
        assertEquals(-1, actual.pop());
    }

    @Test
    public void testExhaustiveShortOperationPatternsAgainstOracle() {
        for (int capacity = 1; capacity <= 3; capacity++) {
            for (int mask = 0; mask < 512; mask++) {
                DinnerPlates_1172 actual = new DinnerPlates_1172(capacity);
                List<List<Integer>> expected = new ArrayList<>();
                int value = 1;
                for (int step = 0; step < 9; step++) {
                    int operation = (mask >>> step) & 3;
                    if (operation == 0 || operation == 1) {
                        push(expected, capacity, value);
                        actual.push(value++);
                    } else if (operation == 2) {
                        assertEquals(pop(expected), actual.pop());
                    } else {
                        int index = (step * 2) % 5;
                        assertEquals(popAtStack(expected, index), actual.popAtStack(index));
                    }
                }
            }
        }
    }

    @Test
    public void testTwoHundredThousandOperationsAtContractScale() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int value = 1; value <= 100_000; value++) {
            dp.push(value);
        }
        for (int stack = 0; stack < 25_000; stack++) {
            assertEquals(4 * stack + 2, dp.popAtStack(2 * stack));
        }
        for (int offset = 0; offset < 25_000; offset++) {
            dp.push(1_000_000 + offset);
        }
        for (int stack = 0; stack < 25_000; stack++) {
            assertEquals(1_000_000 + stack, dp.popAtStack(2 * stack));
        }
        int stackIndex = 49_999;
        int valuesRemaining = 2;
        for (int offset = 0; offset < 25_000; offset++) {
            if (stackIndex % 2 == 1) {
                assertEquals(2 * stackIndex + valuesRemaining, dp.pop());
                valuesRemaining--;
                if (valuesRemaining == 0) {
                    stackIndex--;
                    valuesRemaining = stackIndex % 2 == 1 ? 2 : 1;
                }
            } else {
                assertEquals(2 * stackIndex + 1, dp.pop());
                stackIndex--;
                valuesRemaining = stackIndex % 2 == 1 ? 2 : 1;
            }
        }
        for (int index = 100_000; index < 125_000; index++) {
            assertEquals(-1, dp.popAtStack(index));
        }
    }

    private void assertRandomOperationsMatchOracle(int capacity, int operationCount, long seed) {
        DinnerPlates_1172 actual = new DinnerPlates_1172(capacity);
        List<List<Integer>> expected = new ArrayList<>();
        Random random = new Random(seed);
        for (int step = 0; step < operationCount; step++) {
            int operation = random.nextInt(3);
            if (operation == 0) {
                int value = switch (random.nextInt(5)) {
                    case 0 -> 1;
                    case 1 -> 20_000;
                    case 2 -> 42;
                    case 3 -> Integer.MAX_VALUE;
                    default -> random.nextInt(20_000) + 1;
                };
                push(expected, capacity, value);
                actual.push(value);
            } else if (operation == 1) {
                assertEquals(pop(expected), actual.pop(), "operation " + step);
            } else {
                int index = random.nextInt(Math.max(8, expected.size() + 8)) - 2;
                assertEquals(popAtStack(expected, index), actual.popAtStack(index), "operation " + step);
            }
        }
    }

    private static void push(List<List<Integer>> stacks, int capacity, int value) {
        int index = 0;
        while (index < stacks.size() && stacks.get(index).size() == capacity) {
            index++;
        }
        if (index == stacks.size()) {
            stacks.add(new ArrayList<>());
        }
        stacks.get(index).add(value);
    }

    private static int pop(List<List<Integer>> stacks) {
        for (int index = stacks.size() - 1; index >= 0; index--) {
            List<Integer> stack = stacks.get(index);
            if (!stack.isEmpty()) {
                return stack.remove(stack.size() - 1);
            }
        }
        return -1;
    }

    private static int popAtStack(List<List<Integer>> stacks, int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }
        List<Integer> stack = stacks.get(index);
        return stack.remove(stack.size() - 1);
    }

    private static boolean hasValues(List<List<Integer>> stacks) {
        for (List<Integer> stack : stacks) {
            if (!stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
