package solutions.design;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinStack_155Test {
    private MinStack_155 minStack;

    @BeforeEach
    void setUp() {
        minStack = new MinStack_155();
    }

    @Test
    public void valid() {
        minStack.push(10);
        minStack.push(20);
        minStack.push(30);
        minStack.push(40);
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(10, minStack.getMin());
        minStack.pop();
    }

    @Test
    public void valid1() {
        minStack.push(40);
        minStack.push(30);
        minStack.push(20);
        minStack.push(10);
        assertEquals(10, minStack.getMin());
        minStack.pop();
        assertEquals(20, minStack.getMin());
        minStack.pop();
        assertEquals(30, minStack.getMin());
        minStack.pop();
        assertEquals(40, minStack.getMin());
        minStack.pop();
    }

    @Test
    public void valid2() {
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        assertEquals(0, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.getMin());
    }

    @Test
    public void valid3() {
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        assertEquals(-1024, minStack.getMin());
        minStack.pop();
        assertEquals(-1024, minStack.getMin());
        minStack.pop();
        assertEquals(512, minStack.getMin());
    }

    @Test
    public void testEmptyStackCanBeConstructed() {
        minStack.push(7);
        assertEquals(7, minStack.top());
        assertEquals(7, minStack.getMin());
    }

    @Test
    public void testPopOnEmpty() {
        minStack.pop(); // should not throw
        minStack.push(4);
        assertEquals(4, minStack.top());
        assertEquals(4, minStack.getMin());
    }

    @Test
    public void testSingleElement() {
        minStack.push(42);
        assertEquals(42, minStack.top());
        assertEquals(42, minStack.getMin());
        minStack.pop();
        minStack.push(-1);
        assertEquals(-1, minStack.top());
        assertEquals(-1, minStack.getMin());
    }

    @Test
    public void testAllSameValues() {
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        assertEquals(5, minStack.getMin());
        minStack.pop();
        assertEquals(5, minStack.getMin());
        minStack.pop();
        assertEquals(5, minStack.getMin());
    }

    @Test
    public void testMinUpdatesCorrectly() {
        minStack.push(3);
        minStack.push(1);
        minStack.push(2);
        assertEquals(1, minStack.getMin());
        assertEquals(2, minStack.top());
        minStack.pop();
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
    }

    @Test
    @Timeout(10)
    public void testGiantCase() {
        for (int i = 1000; i >= 1; i--) {
            minStack.push(i);
            assertEquals(i, minStack.getMin());
        }
        for (int i = 1; i <= 1000; i++) {
            assertEquals(i, minStack.getMin());
            minStack.pop();
        }
        minStack.push(0);
        assertEquals(0, minStack.getMin());
    }

    @Test
    public void testPushThenTopEqualsPushed() {
        minStack.push(99);
        assertEquals(99, minStack.top());
        minStack.push(-7);
        assertEquals(-7, minStack.top());
    }

    @Test
    public void testPushDuplicatesPopOneGetMinStillCorrect() {
        minStack.push(1);
        minStack.push(1);
        minStack.push(1);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.getMin());
    }

    @Test
    public void testMixedSequenceGetMin() {
        minStack.push(3);
        minStack.push(2);
        minStack.push(5);
        minStack.push(1);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(2, minStack.getMin());
        minStack.pop();
        assertEquals(2, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
    }

    @Test
    public void testNegativeValues() {
        minStack.push(-3);
        minStack.push(-1);
        minStack.push(-5);
        assertEquals(-5, minStack.getMin());
        assertEquals(-5, minStack.top());
        minStack.pop();
        assertEquals(-3, minStack.getMin());
        assertEquals(-1, minStack.top());
    }

    @Test
    public void testIntMaxAndIntMinValues() {
        minStack.push(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, minStack.getMin());
        minStack.push(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        assertEquals(Integer.MIN_VALUE, minStack.top());
        minStack.pop();
        assertEquals(Integer.MAX_VALUE, minStack.getMin());
    }

    @Test
    public void testTopReturnsLastPushedNotLastPopped() {
        minStack.push(10);
        minStack.push(20);
        minStack.push(30);
        assertEquals(30, minStack.top());
        minStack.pop();
        assertEquals(20, minStack.top());
        minStack.pop();
        assertEquals(10, minStack.top());
    }

    @Test
    public void testManyPushesThenManyPops() {
        for (int i = 0; i < 500; i++) {
            minStack.push(i);
        }
        assertEquals(0, minStack.getMin());
        assertEquals(499, minStack.top());
        for (int i = 0; i < 500; i++) {
            minStack.pop();
        }
        minStack.push(-1);
        assertEquals(-1, minStack.getMin());
        assertEquals(-1, minStack.top());
    }

    @Test
    public void testStress1000RandomOpsSeed42() {
        Random rand = new Random(42L);
        List<Integer> reference = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (reference.isEmpty() || rand.nextBoolean()) {
                int val = rand.nextInt(20001) - 10000;
                minStack.push(val);
                reference.add(val);
            } else {
                minStack.pop();
                reference.removeLast();
            }
            if (!reference.isEmpty()) {
                int expectedMin = Collections.min(reference);
                assertEquals(expectedMin, minStack.getMin(), "Mismatch at op " + i);
                assertEquals(reference.getLast(), minStack.top(), "Top mismatch at op " + i);
            }
        }
    }

    @Test
    public void testOfficialLeetCodeSequence() {
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.top());
        assertEquals(-2, minStack.getMin());
    }

    @Test
    public void testPoppingCurrentMinimumRevealsPreviousMinimum() {
        minStack.push(8);
        minStack.push(3);
        minStack.push(6);
        minStack.push(1);
        minStack.push(4);
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(1, minStack.top());
        assertEquals(1, minStack.getMin());
        minStack.pop();
        assertEquals(3, minStack.getMin());
        assertEquals(6, minStack.top());
    }

    @Test
    public void testSeparatedDuplicateMinimaSurviveOneAtATime() {
        int[] values = {4, 2, 7, 2, 9, 2};
        for (int value : values) {
            minStack.push(value);
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(2, minStack.getMin());
            minStack.pop();
        }
        assertEquals(2, minStack.getMin());
        assertEquals(7, minStack.top());
        minStack.pop();
        assertEquals(2, minStack.top());
        assertEquals(2, minStack.getMin());
        minStack.pop();
        assertEquals(4, minStack.getMin());
    }

    @Test
    public void testSignedValuesDoNotRequireArithmeticOrOverflow() {
        int[] values = {Integer.MAX_VALUE, 0, -1, Integer.MIN_VALUE, 1, Integer.MAX_VALUE};
        for (int value : values) {
            minStack.push(value);
        }
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        assertEquals(Integer.MAX_VALUE, minStack.top());
        minStack.pop();
        minStack.pop();
        assertEquals(Integer.MIN_VALUE, minStack.getMin());
        minStack.pop();
        assertEquals(-1, minStack.getMin());
        assertEquals(-1, minStack.top());
    }

    @Test
    public void testReuseAfterDrainingStack() {
        minStack.push(4);
        minStack.push(2);
        minStack.pop();
        minStack.pop();

        minStack.push(-9);
        assertEquals(-9, minStack.top());
        assertEquals(-9, minStack.getMin());
    }

    @Test
    public void testRepeatedEmptyPopOperationsAreNoOps() {
        for (int i = 0; i < 5; i++) {
            minStack.pop();
        }
        minStack.push(11);
        assertEquals(11, minStack.top());
        assertEquals(11, minStack.getMin());
    }

    @Test
    public void testIndependentInstancesDoNotShareValuesOrMinimums() {
        MinStack_155 second = new MinStack_155();
        minStack.push(-10);
        minStack.push(5);
        second.push(20);
        second.push(-2);

        assertEquals(5, minStack.top());
        assertEquals(-10, minStack.getMin());
        assertEquals(-2, second.top());
        assertEquals(-2, second.getMin());

        minStack.pop();
        assertEquals(-10, minStack.top());
        assertEquals(-2, second.top());
    }

    @Test
    @Timeout(10)
    public void testExactlyMaximumLegalCallCountWithValidOperations() {
        for (int i = 0; i < 9998; i++) {
            minStack.push(i - 4999);
        }
        assertEquals(-4999, minStack.getMin());
        assertEquals(4998, minStack.top());
        for (int i = 0; i < 9998; i++) {
            minStack.pop();
        }
        for (int i = 0; i < 10000; i++) {
            minStack.push(i - 10000);
        }
        assertEquals(-10000, minStack.getMin());
        assertEquals(-1, minStack.top());
    }

    @Test
    @Timeout(10)
    public void testSeededStatefulOracleAcrossFiveThousandOperations() {
        Random random = new Random(155L);
        List<Integer> reference = new ArrayList<>();
        for (int operation = 0; operation < 5000; operation++) {
            if (reference.isEmpty() || random.nextInt(4) != 0) {
                int value = random.nextInt();
                minStack.push(value);
                reference.add(value);
            } else {
                minStack.pop();
                reference.removeLast();
            }
            assertStackMatches(reference, operation);
        }
    }

    @Test
    @Timeout(10)
    public void testExhaustiveShortOperationSequencesAgainstOracle() {
        // Enumerate every six-operation sequence over pop and pushes of -1, 0, and 1.
        // Empty top/getMin calls are intentionally omitted because LeetCode requires a nonempty stack.
        for (int encoded = 0; encoded < 4 * 4 * 4 * 4 * 4 * 4; encoded++) {
            MinStack_155 candidate = new MinStack_155();
            List<Integer> reference = new ArrayList<>();
            int sequence = encoded;
            for (int operation = 0; operation < 6; operation++) {
                int action = sequence % 4;
                sequence /= 4;
                if (action == 0) {
                    candidate.pop();
                    if (!reference.isEmpty()) {
                        reference.removeLast();
                    }
                } else {
                    int value = action - 2;
                    candidate.push(value);
                    reference.add(value);
                }
                if (!reference.isEmpty()) {
                    assertEquals(reference.getLast(), candidate.top(),
                            "top mismatch for sequence " + encoded + " at operation " + operation);
                    assertEquals(Collections.min(reference), candidate.getMin(),
                            "minimum mismatch for sequence " + encoded + " at operation " + operation);
                }
            }
        }
    }

    private void assertStackMatches(List<Integer> reference, int operation) {
        if (!reference.isEmpty()) {
            assertEquals(reference.getLast(), minStack.top(), "top mismatch at operation " + operation);
            assertEquals(Collections.min(reference), minStack.getMin(),
                    "minimum mismatch at operation " + operation);
        }
    }
}
