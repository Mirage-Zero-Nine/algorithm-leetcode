package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class CustomStack_1381Test {

    @Test
    public void testHappyCases() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(1); stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(2); stack.push(3); stack.push(4);
        stack.increment(5, 100);
        stack.increment(2, 100);
        assertEquals(103, stack.pop());
        assertEquals(202, stack.pop());
        assertEquals(201, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        CustomStack_1381 stack = new CustomStack_1381(1);
        assertEquals(-1, stack.pop());
        stack.push(1); stack.push(2);
        assertEquals(1, stack.pop());
    }

    @Test
    public void testLargeCase() {
        CustomStack_1381 stack = new CustomStack_1381(5);
        for (int i = 1; i <= 5; i++) stack.push(i);
        stack.increment(3, 10);
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(13, stack.pop());
    }

    @Test
    public void testIncrementOnEmptyStackDoesNothing() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.increment(2, 10);
        assertEquals(-1, stack.pop());
    }

    @Test
    public void testIncrementWithZeroKDoesNothing() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(10);
        stack.increment(0, 100);
        assertEquals(10, stack.pop());
    }

    @Test
    public void testIncrementWithNegativeKDoesNothing() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(10);
        stack.increment(-2, 100);
        assertEquals(10, stack.pop());
    }

    @Test
    public void testIncrementTopOnly() {
        CustomStack_1381 stack = new CustomStack_1381(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.increment(1, 50);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(51, stack.pop());
    }

    @Test
    public void testNegativeIncrementValue() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(5);
        stack.push(6);
        stack.increment(2, -2);
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
    }

    @Test
    public void testPushBeyondCapacityIgnored() {
        CustomStack_1381 stack = new CustomStack_1381(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    public void testGiantSequenceOperations() {
        CustomStack_1381 stack = new CustomStack_1381(1000);
        for (int i = 1; i <= 1000; i++) {
            stack.push(i);
        }
        stack.increment(1000, 7);
        stack.increment(500, 3);
        for (int i = 1000; i >= 501; i--) {
            assertEquals(i + 7, stack.pop());
        }
        for (int i = 500; i >= 1; i--) {
            assertEquals(i + 10, stack.pop());
        }
        assertEquals(-1, stack.pop());
    }

    @Test
    public void repeatedEmptyPopsRemainMinusOneAndDoNotCorruptLaterPushes() {
        CustomStack_1381 stack = new CustomStack_1381(2);

        assertEquals(-1, stack.pop());
        assertEquals(-1, stack.pop());
        stack.push(7);
        assertEquals(7, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    public void popReclaimsCapacityForLaterPushes() {
        CustomStack_1381 stack = new CustomStack_1381(2);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    public void incrementExactlyCurrentSizeUpdatesEveryElement() {
        CustomStack_1381 stack = new CustomStack_1381(4);
        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.increment(3, 6);
        assertEquals(36, stack.pop());
        assertEquals(26, stack.pop());
        assertEquals(16, stack.pop());
    }

    @Test
    public void incrementLargerThanSizeUpdatesEveryElement() {
        CustomStack_1381 stack = new CustomStack_1381(5);
        stack.push(4);
        stack.push(5);

        stack.increment(1000, 9);
        assertEquals(14, stack.pop());
        assertEquals(13, stack.pop());
    }

    @Test
    public void incrementsAccumulateBeforeAnyPop() {
        CustomStack_1381 stack = new CustomStack_1381(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.increment(4, 10);
        stack.increment(2, 5);
        stack.increment(1, 7);
        assertEquals(14, stack.pop());
        assertEquals(13, stack.pop());
        assertEquals(17, stack.pop());
        assertEquals(23, stack.pop());
    }

    @Test
    public void lazyIncrementMovesToTheRemainingBottomElementsWhenPopping() {
        CustomStack_1381 stack = new CustomStack_1381(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.increment(3, 10);

        assertEquals(4, stack.pop());
        stack.increment(2, 1);
        assertEquals(13, stack.pop());
        assertEquals(13, stack.pop());
        assertEquals(12, stack.pop());
    }

    @Test
    public void incrementAfterPartialPopUsesTheNewBottom() {
        CustomStack_1381 stack = new CustomStack_1381(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.increment(1, 20);

        assertEquals(2, stack.pop());
        assertEquals(21, stack.pop());
    }

    @Test
    public void negativeValuesAndNegativeIncrementsArePreserved() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(-10);
        stack.push(-5);
        stack.increment(2, -7);

        assertEquals(-12, stack.pop());
        assertEquals(-17, stack.pop());
    }

    @Test
    public void mixedPositiveAndNegativeIncrementsFollowOperationOrder() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(-2);
        stack.push(8);
        stack.push(-4);
        stack.increment(3, -10);
        stack.increment(2, 25);

        assertEquals(-14, stack.pop());
        assertEquals(23, stack.pop());
        assertEquals(13, stack.pop());
    }

    @Test
    public void incrementWithZeroValueDoesNotChangeLaterPops() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(6);
        stack.push(7);
        stack.increment(2, 0);
        assertEquals(7, stack.pop());
        assertEquals(6, stack.pop());
    }

    @Test
    public void pushesBeyondCapacityNeverReplaceExistingValues() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(99);
        stack.push(-99);
        stack.increment(3, 10);

        assertEquals(13, stack.pop());
        assertEquals(12, stack.pop());
        assertEquals(11, stack.pop());
        assertEquals(-1, stack.pop());
    }

    @Test
    public void duplicateValuesRemainDistinctStackEntries() {
        CustomStack_1381 stack = new CustomStack_1381(5);
        stack.push(4);
        stack.push(4);
        stack.push(4);
        stack.increment(2, 3);

        assertEquals(4, stack.pop());
        assertEquals(7, stack.pop());
        assertEquals(7, stack.pop());
    }

    @Test
    public void capacityOneSupportsRepeatedPushIncrementPopCycles() {
        CustomStack_1381 stack = new CustomStack_1381(1);
        for (int i = 0; i < 8; i++) {
            stack.push(i);
            stack.push(1000 + i);
            stack.increment(1, -i);
            assertEquals(i - i, stack.pop());
            assertEquals(-1, stack.pop());
        }
    }

    @Test
    public void valuesAtJavaIntegerBoundariesCanBePoppedWithoutIncrement() {
        CustomStack_1381 stack = new CustomStack_1381(2);
        stack.push(Integer.MIN_VALUE);
        stack.push(Integer.MAX_VALUE);

        assertEquals(Integer.MAX_VALUE, stack.pop());
        assertEquals(Integer.MIN_VALUE, stack.pop());
    }

    @Test
    public void independentInstancesDoNotShareLazyIncrementState() {
        CustomStack_1381 first = new CustomStack_1381(3);
        CustomStack_1381 second = new CustomStack_1381(3);
        first.push(1);
        first.push(2);
        first.increment(2, 50);
        second.push(10);

        assertEquals(10, second.pop());
        assertEquals(-1, second.pop());
        assertEquals(52, first.pop());
        assertEquals(51, first.pop());
    }

    @Test
    public void interleavingTwoStacksKeepsEachOperationOnItsOwnInstance() {
        CustomStack_1381 first = new CustomStack_1381(2);
        CustomStack_1381 second = new CustomStack_1381(2);
        first.push(3);
        second.push(4);
        first.increment(1, 6);
        second.increment(1, -1);
        first.push(5);

        assertEquals(5, first.pop());
        assertEquals(9, first.pop());
        assertEquals(3, second.pop());
    }

    @Test
    public void incrementBoundsZeroNegativeAndAboveCapacityAreHandledTogether() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(2);
        stack.push(4);
        stack.increment(0, 100);
        stack.increment(-1, 100);
        stack.increment(99, 1);

        assertEquals(5, stack.pop());
        assertEquals(3, stack.pop());
    }

    @Test
    public void repeatedIncrementAndPopSequenceMatchesSimpleStackOracle() {
        assertMatchesOracle(4, List.of(
                Operation.push(5), Operation.push(-3), Operation.increment(2, 8),
                Operation.pop(), Operation.push(9), Operation.increment(1, -4),
                Operation.pop(), Operation.increment(9, 2), Operation.pop(),
                Operation.pop(), Operation.pop()));
    }

    @Test
    public void seededOfficialRangeOperationStreamMatchesOracle() {
        CustomStack_1381 actual = new CustomStack_1381(37);
        ReferenceStack expected = new ReferenceStack(37);
        Random random = new Random(1381L);

        int pushes = 0;
        int pops = 0;
        int increments = 0;
        while (pushes < 1000 || pops < 1000 || increments < 1000) {
            int operation = random.nextInt(3);
            if (operation == 0 && pushes < 1000) {
                int value = random.nextInt(2001) - 1000;
                actual.push(value);
                expected.push(value);
                pushes++;
            } else if (operation == 1 && pops < 1000) {
                assertEquals(expected.pop(), actual.pop());
                pops++;
            } else if (increments < 1000) {
                int k = 1 + random.nextInt(1200);
                int value = random.nextInt(101);
                actual.increment(k, value);
                expected.increment(k, value);
                increments++;
            }
        }
        while (!expected.isEmpty()) {
            assertEquals(expected.pop(), actual.pop());
        }
        assertEquals(-1, actual.pop());
    }

    @Test
    public void maximumCapacityCanBeFilledAndDrainedAfterManyIncrements() {
        CustomStack_1381 actual = new CustomStack_1381(1000);
        ReferenceStack expected = new ReferenceStack(1000);
        for (int i = 0; i < 1000; i++) {
            actual.push(i - 500);
            expected.push(i - 500);
        }
        for (int i = 1; i <= 1000; i++) {
            int k = (i * 37) % 1200;
            int value = (i % 2 == 0) ? i : -i;
            actual.increment(k, value);
            expected.increment(k, value);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(expected.pop(), actual.pop());
        }
        assertEquals(-1, actual.pop());
    }

    @Test
    public void reusedCapacityDoesNotRetainAnOldIncrement() {
        CustomStack_1381 stack = new CustomStack_1381(3);
        stack.push(1);
        stack.push(2);
        stack.increment(2, 100);
        assertEquals(102, stack.pop());
        assertEquals(101, stack.pop());

        stack.push(3);
        assertEquals(3, stack.pop());
    }

    private void assertMatchesOracle(int maxSize, List<Operation> operations) {
        CustomStack_1381 actual = new CustomStack_1381(maxSize);
        ReferenceStack expected = new ReferenceStack(maxSize);
        for (Operation operation : operations) {
            if (operation.type() == 'p') {
                actual.push(operation.first());
                expected.push(operation.first());
            } else if (operation.type() == 'o') {
                assertEquals(expected.pop(), actual.pop());
            } else {
                actual.increment(operation.first(), operation.second());
                expected.increment(operation.first(), operation.second());
            }
        }
    }

    private record Operation(char type, int first, int second) {
        private static Operation push(int value) {
            return new Operation('p', value, 0);
        }

        private static Operation pop() {
            return new Operation('o', 0, 0);
        }

        private static Operation increment(int k, int value) {
            return new Operation('i', k, value);
        }
    }

    private static final class ReferenceStack {
        private final int maxSize;
        private final List<Integer> values = new ArrayList<>();

        private ReferenceStack(int maxSize) {
            this.maxSize = maxSize;
        }

        private void push(int value) {
            if (values.size() < maxSize) {
                values.add(value);
            }
        }

        private int pop() {
            if (values.isEmpty()) {
                return -1;
            }
            return values.remove(values.size() - 1);
        }

        private void increment(int k, int value) {
            int count = Math.min(Math.max(k, 0), values.size());
            for (int i = 0; i < count; i++) {
                values.set(i, values.get(i) + value);
            }
        }

        private boolean isEmpty() {
            return values.isEmpty();
        }
    }
}
