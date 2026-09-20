package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class FirstUnique_1429Test {

    @Test
    public void testHappyCases() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{2, 3, 5});
        assertEquals(2, fu.showFirstUnique());
        fu.add(5);
        assertEquals(2, fu.showFirstUnique());
        fu.add(2);
        assertEquals(3, fu.showFirstUnique());
        fu.add(3);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 1});
        assertEquals(-1, fu.showFirstUnique());
        FirstUnique_1429 fu2 = new FirstUnique_1429(new int[]{1});
        assertEquals(1, fu2.showFirstUnique());
    }

    @Test
    public void testLargeCase() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 2, 3, 4, 5});
        assertEquals(1, fu.showFirstUnique());
        fu.add(1); fu.add(2); fu.add(3); fu.add(4);
        assertEquals(5, fu.showFirstUnique());
    }

    @Test
    public void testEmptyInitialization() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{});
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testUniqueAfterAddingNewValue() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{7, 7, 8, 8});
        assertEquals(-1, fu.showFirstUnique());
        fu.add(9);
        assertEquals(9, fu.showFirstUnique());
    }

    @Test
    public void testFrontUniqueBecomesDuplicateThenNextAppears() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{10, 11, 12});
        assertEquals(10, fu.showFirstUnique());
        fu.add(10);
        assertEquals(11, fu.showFirstUnique());
    }

    @Test
    public void testQueueOrderPreservedForUniques() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{4, 5, 6});
        fu.add(4);
        assertEquals(5, fu.showFirstUnique());
        fu.add(5);
        assertEquals(6, fu.showFirstUnique());
    }

    @Test
    public void testNegativeNumbersHandled() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{-1, -2, -1});
        assertEquals(-2, fu.showFirstUnique());
        fu.add(-2);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testRepeatedShowDoesNotChangeResultWhenStable() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 2, 1, 3});
        assertEquals(2, fu.showFirstUnique());
        assertEquals(2, fu.showFirstUnique());
    }

    @Test
    public void testGiantCase() {
        int n = 100_000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        FirstUnique_1429 fu = new FirstUnique_1429(nums);
        assertEquals(0, fu.showFirstUnique());
        for (int i = 0; i < n - 1; i++) {
            fu.add(i);
        }
        assertEquals(n - 1, fu.showFirstUnique());
    }

    @Test
    public void testOfficialAllDuplicateInitializationThenNewValue() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{7, 7, 7, 7, 7, 7});
        assertEquals(-1, fu.showFirstUnique());
        fu.add(7);
        fu.add(3);
        fu.add(3);
        fu.add(7);
        fu.add(17);
        assertEquals(17, fu.showFirstUnique());
    }

    @Test
    public void testOfficialSingletonBecomesDuplicate() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{809});
        assertEquals(809, fu.showFirstUnique());
        fu.add(809);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testInitialOrderWinsOverNumericOrder() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{30, 10, 20});
        assertEquals(30, fu.showFirstUnique());
        fu.add(30);
        assertEquals(10, fu.showFirstUnique());
        fu.add(10);
        assertEquals(20, fu.showFirstUnique());
    }

    @Test
    public void testDuplicateAtFrontIsSkippedLazily() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{4, 4, 5, 6, 5, 7});
        assertEquals(6, fu.showFirstUnique());
        fu.add(6);
        assertEquals(7, fu.showFirstUnique());
        fu.add(7);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testAddingNewValueWhenThereAreNoUniques() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{2, 2, 3, 3});
        assertEquals(-1, fu.showFirstUnique());
        fu.add(4);
        assertEquals(4, fu.showFirstUnique());
        fu.add(5);
        assertEquals(4, fu.showFirstUnique());
    }

    @Test
    public void testAddingDuplicateDoesNotChangeEarlierUnique() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{8, 9, 10});
        fu.add(10);
        fu.add(10);
        assertEquals(8, fu.showFirstUnique());
        fu.add(8);
        assertEquals(9, fu.showFirstUnique());
    }

    @Test
    public void testSequentialUniquesBecomeDuplicatesInQueueOrder() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 2, 3, 4});
        fu.add(1);
        assertEquals(2, fu.showFirstUnique());
        fu.add(2);
        assertEquals(3, fu.showFirstUnique());
        fu.add(3);
        assertEquals(4, fu.showFirstUnique());
        fu.add(4);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testRepeatedShowsDoNotConsumeAUniqueValue() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{12, 13, 12, 14});
        assertEquals(13, fu.showFirstUnique());
        assertEquals(13, fu.showFirstUnique());
        assertEquals(13, fu.showFirstUnique());
        fu.add(13);
        assertEquals(14, fu.showFirstUnique());
    }

    @Test
    public void testZeroAndNegativeValues() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{0, -2, -1, 0});
        assertEquals(-2, fu.showFirstUnique());
        fu.add(-2);
        assertEquals(-1, fu.showFirstUnique());
        fu.add(-1);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testIntegerBoundaryValues() {
        int[] values = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        FirstUnique_1429 fu = new FirstUnique_1429(values);
        assertEquals(Integer.MIN_VALUE, fu.showFirstUnique());
        fu.add(Integer.MIN_VALUE);
        assertEquals(0, fu.showFirstUnique());
        fu.add(0);
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testDuplicateNegativeValuesAndBoundaryOrdering() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{-5, -5, Integer.MIN_VALUE, Integer.MAX_VALUE, -6});
        assertEquals(Integer.MIN_VALUE, fu.showFirstUnique());
        fu.add(Integer.MIN_VALUE);
        assertEquals(Integer.MAX_VALUE, fu.showFirstUnique());
        fu.add(Integer.MAX_VALUE);
        assertEquals(-6, fu.showFirstUnique());
    }

    @Test
    public void testInputArrayIsNotMutated() {
        int[] nums = {5, 5, 1, 2};
        int[] original = nums.clone();
        FirstUnique_1429 fu = new FirstUnique_1429(nums);
        fu.showFirstUnique();
        fu.add(1);
        fu.showFirstUnique();
        org.junit.jupiter.api.Assertions.assertArrayEquals(original, nums);
    }

    @Test
    public void testMultipleInstancesHaveIndependentState() {
        FirstUnique_1429 first = new FirstUnique_1429(new int[]{1, 2, 1});
        FirstUnique_1429 second = new FirstUnique_1429(new int[]{2, 3});
        assertEquals(2, first.showFirstUnique());
        assertEquals(2, second.showFirstUnique());
        first.add(2);
        assertEquals(-1, first.showFirstUnique());
        assertEquals(2, second.showFirstUnique());
        second.add(2);
        assertEquals(3, second.showFirstUnique());
    }

    @Test
    public void testFreshInstancesDoNotShareMutableState() {
        FirstUnique_1429 first = new FirstUnique_1429(new int[]{11});
        first.add(11);
        FirstUnique_1429 second = new FirstUnique_1429(new int[]{11});
        assertEquals(-1, first.showFirstUnique());
        assertEquals(11, second.showFirstUnique());
    }

    @Test
    public void testStatefulSequenceMatchesIndependentOracle() {
        int[] initial = {4, 4, 1, 9, 1, 2, 9, 8};
        FirstUnique_1429 actual = new FirstUnique_1429(initial);
        Oracle expected = new Oracle(initial);
        assertMatches(actual, expected);
        int[] additions = {2, 3, 3, 4, 8, 5, 1, 9, 6, 5, 7, 2, 6, 10};
        for (int value : additions) {
            actual.add(value);
            expected.add(value);
            assertMatches(actual, expected);
            assertMatches(actual, expected);
        }
    }

    @Test
    public void testSeededMixedOperationsMatchOracle() {
        Random random = new Random(1429L);
        int[] initial = new int[250];
        for (int i = 0; i < initial.length; i++) {
            initial[i] = random.nextInt(31) - 15;
        }
        FirstUnique_1429 actual = new FirstUnique_1429(initial);
        Oracle expected = new Oracle(initial);
        assertMatches(actual, expected);
        for (int operation = 0; operation < 2_000; operation++) {
            if (random.nextInt(4) == 0) {
                assertMatches(actual, expected);
            } else {
                int value = random.nextInt(41) - 20;
                actual.add(value);
                expected.add(value);
                assertMatches(actual, expected);
            }
        }
    }

    @Test
    public void testLargeDuplicatePrefixWithLateUniqueValue() {
        int[] initial = new int[10_000];
        for (int i = 0; i < initial.length - 1; i++) {
            initial[i] = 42;
        }
        initial[initial.length - 1] = 43;
        FirstUnique_1429 fu = new FirstUnique_1429(initial);
        assertEquals(43, fu.showFirstUnique());
        fu.add(43);
        fu.add(44);
        assertEquals(44, fu.showFirstUnique());
    }

    @Test
    public void testMaximumOperationCountWithRepeatedShows() {
        FirstUnique_1429 actual = new FirstUnique_1429(new int[]{1});
        Oracle expected = new Oracle(new int[]{1});
        for (int operation = 0; operation < 50_000; operation++) {
            if ((operation & 1) == 0) {
                assertMatches(actual, expected);
            } else {
                int value = operation % 101;
                actual.add(value);
                expected.add(value);
                assertMatches(actual, expected);
            }
        }
    }

    @Test
    public void testHighPositiveValuesWithinLeetCodeBounds() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{100_000_000, 99_999_999, 1});
        assertEquals(100_000_000, fu.showFirstUnique());
        fu.add(100_000_000);
        assertEquals(99_999_999, fu.showFirstUnique());
        fu.add(99_999_999);
        assertEquals(1, fu.showFirstUnique());
    }

    @Test
    public void testInterleavedInstancesAndRepeatedCalls() {
        FirstUnique_1429 left = new FirstUnique_1429(new int[]{6, 7, 6, 8});
        FirstUnique_1429 right = new FirstUnique_1429(new int[]{7, 8, 9});
        assertEquals(7, left.showFirstUnique());
        assertEquals(7, right.showFirstUnique());
        left.add(7);
        assertEquals(8, left.showFirstUnique());
        assertEquals(7, right.showFirstUnique());
        right.add(7);
        assertEquals(8, right.showFirstUnique());
        assertEquals(8, left.showFirstUnique());
    }

    @Test
    public void testEveryInitialElementCanBeMadeDuplicate() {
        int[] initial = {21, 22, 23, 24, 25};
        FirstUnique_1429 fu = new FirstUnique_1429(initial);
        for (int value : initial) {
            fu.add(value);
        }
        assertEquals(-1, fu.showFirstUnique());
    }

    @Test
    public void testUniqueValuesAddedAfterExistingDuplicateRemainOrdered() {
        FirstUnique_1429 fu = new FirstUnique_1429(new int[]{1, 1});
        fu.add(30);
        fu.add(20);
        fu.add(10);
        assertEquals(30, fu.showFirstUnique());
        fu.add(30);
        assertEquals(20, fu.showFirstUnique());
        fu.add(20);
        assertEquals(10, fu.showFirstUnique());
    }

    private static void assertMatches(FirstUnique_1429 actual, Oracle expected) {
        assertEquals(expected.showFirstUnique(), actual.showFirstUnique());
    }

    /** Simple count-plus-insertion-order model independent of the production queue implementation. */
    private static final class Oracle {
        private final Map<Integer, Integer> counts = new HashMap<>();
        private final Queue<Integer> values = new ArrayDeque<>();

        private Oracle(int[] initial) {
            for (int value : initial) {
                add(value);
            }
        }

        private void add(int value) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
            values.offer(value);
        }

        private int showFirstUnique() {
            for (int value : values) {
                if (counts.get(value) == 1) {
                    return value;
                }
            }
            return -1;
        }
    }
}
