package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the binary-search solution to LeetCode 1011.
 *
 * <p>Small cases use an independent linear capacity oracle. It simulates the
 * required contiguous, in-order loading for every capacity from the largest
 * package through the total weight; it does not reuse the solution's binary
 * search or day-count implementation.</p>
 */
public class ShipWithinDays_1011Test {

    private final ShipWithinDays_1011 solution = new ShipWithinDays_1011();

    @Test
    void testOfficialExamples() {
        assertEquals(15, solution.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        assertEquals(6, solution.shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3));
        assertEquals(3, solution.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
    }

    @Test
    void testSinglePackageAndSingletonDays() {
        assertEquals(1, solution.shipWithinDays(new int[]{1}, 1));
        assertEquals(7, solution.shipWithinDays(new int[]{7}, 1));
        assertEquals(500, solution.shipWithinDays(new int[]{500}, 1));
        assertEquals(37, solution.shipWithinDays(new int[]{37}, 1));
    }

    @Test
    void testOneDayRequiresTheTotalWeight() {
        assertEquals(6, solution.shipWithinDays(new int[]{1, 2, 3}, 1));
        assertMatchesOracle(new int[]{5, 1, 9, 2, 7}, 1);
        assertMatchesOracle(new int[]{500, 500, 500}, 1);
        assertEquals(25_000_000, solution.shipWithinDays(maximumWeights(50_000), 1));
    }

    @Test
    void testDaysEqualPackageCountUsesLargestPackage() {
        assertEquals(10, solution.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
        assertMatchesOracle(new int[]{5, 9, 2, 4}, 4);
        assertMatchesOracle(new int[]{1, 500, 2, 499, 3}, 5);
        assertEquals(500, solution.shipWithinDays(maximumWeights(50_000), 50_000));
    }

    @Test
    void testContiguousOrderChangesTheMinimum() {
        int[] weights = {1, 2, 3, 4, 5, 6};
        // Capacity 8 still needs four contiguous shipments; capacity 9 permits
        // [1,2,3], [4,5], [6], so three days is possible only at 9.
        assertEquals(9, solution.shipWithinDays(weights, 3));
        assertEquals(6, solution.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6}, 4));
    }

    @Test
    void testGreedyPackingAtExactBoundaries() {
        assertMatchesOracle(new int[]{5, 6, 2, 3}, 2);
        assertMatchesOracle(new int[]{5, 6, 2, 3}, 3);
        assertMatchesOracle(new int[]{2, 2, 2, 2, 2}, 3);
        assertMatchesOracle(new int[]{10, 1, 1, 10, 1}, 3);
    }

    @Test
    void testRemaindersAndCapacityTransitions() {
        assertMatchesOracle(new int[]{2, 3, 4}, 2);
        assertMatchesOracle(new int[]{4, 4, 4}, 2);
        assertMatchesOracle(new int[]{6, 10, 14}, 3);
        assertMatchesOracle(new int[]{7, 1, 7, 1, 7}, 3);
    }

    @Test
    void testDuplicateWeightsAndRuns() {
        assertMatchesOracle(new int[]{4, 4, 4, 4}, 2);
        assertMatchesOracle(new int[]{9, 9, 9, 9, 9}, 3);
        assertMatchesOracle(new int[]{1, 1, 1, 2, 2, 2}, 3);
        assertMatchesOracle(new int[]{500, 500, 1, 500, 1}, 4);
    }

    @Test
    void testAscendingAndDescendingOrderings() {
        assertMatchesOracle(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3);
        assertMatchesOracle(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, 3);
        assertMatchesOracle(new int[]{1, 10, 2, 9, 3, 8}, 3);
    }

    @Test
    void testMinimumAndMaximumPackageWeights() {
        assertMatchesOracle(new int[]{1, 1, 1, 1, 1}, 2);
        assertMatchesOracle(new int[]{500, 1, 500, 1, 500}, 2);
        assertMatchesOracle(new int[]{1, 500, 1, 500, 1, 500}, 4);
    }

    @Test
    void testExtraDaysBeyondPackageCountAreHandledByTheImplementation() {
        // days > weights.length is outside the online-judge contract but is a
        // harmless extension of this implementation's greedy count.
        assertEquals(3, solution.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 10));
        assertEquals(9, solution.shipWithinDays(new int[]{5, 9, 2, 4}, 20));
        assertMatchesOracle(new int[]{8, 1, 8}, 5);
    }

    @Test
    void testOfficialMaximumWeightAndLengthBoundary() {
        int[] weights = maximumWeights(50_000);
        assertEquals(500, solution.shipWithinDays(weights, 50_000));
        assertEquals(1_000, solution.shipWithinDays(weights, 25_000));
        assertEquals(25_000_000, solution.shipWithinDays(weights, 1));
    }

    @Test
    void testLargeMixedInputAtOfficialLength() {
        int[] weights = new int[50_000];
        long expectedTotal = 0;
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 1 + (i * 37) % 500;
            expectedTotal += weights[i];
        }
        assertEquals(expectedTotal, solution.shipWithinDays(weights, 1));
        assertEquals(500, solution.shipWithinDays(weights, 50_000));
    }

    @Test
    void testInputIsNotMutated() {
        int[] weights = {11, 3, 7, 19, 2};
        int[] original = weights.clone();
        assertMatchesOracle(weights, 3);
        assertArrayEquals(original, weights);
    }

    @Test
    void testRepeatedCallsDoNotShareState() {
        int[] first = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] second = {3, 2, 2, 4, 1, 4};
        assertEquals(15, solution.shipWithinDays(first, 5));
        assertEquals(6, solution.shipWithinDays(second, 3));
        assertEquals(15, solution.shipWithinDays(first, 5));
    }

    @Test
    void testAnswerIsFeasibleButItsPredecessorIsNot() {
        int[] weights = {5, 6, 2, 3};
        int days = 2;
        int answer = solution.shipWithinDays(weights, days);
        assertEquals(11, answer);
        assertEquals(false, requiresAtMostDays(weights, answer - 1, days));
        assertEquals(true, requiresAtMostDays(weights, answer, days));
    }

    @Test
    void testEveryCapacityAroundAnswerForSmallCase() {
        int[] weights = {3, 1, 4, 1, 5};
        int days = 3;
        int answer = bruteForceCapacity(weights, days);
        assertEquals(answer, solution.shipWithinDays(weights, days));
        for (int capacity = 1; capacity <= 15; capacity++) {
            boolean feasible = requiresAtMostDays(weights, capacity, days);
            assertEquals(capacity >= answer, feasible, "capacity=" + capacity);
        }
    }

    @Test
    void testExhaustiveSmallPositiveArraysAgainstIndependentOracle() {
        for (int length = 1; length <= 4; length++) {
            enumerateArrays(new int[length], 0);
        }
    }

    @Test
    void testSeededRandomSmallArraysAgainstIndependentOracle() {
        Random random = new Random(1_011L);
        for (int caseNumber = 0; caseNumber < 300; caseNumber++) {
            int length = 1 + random.nextInt(12);
            int[] weights = new int[length];
            for (int i = 0; i < length; i++) {
                weights[i] = 1 + random.nextInt(20);
            }
            int days = 1 + random.nextInt(length);
            assertMatchesOracle(weights, days, "random case " + caseNumber);
        }
    }

    @Test
    void testSeededRandomCasesWithAdditionalDays() {
        Random random = new Random(20_260_911L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int length = 1 + random.nextInt(15);
            int[] weights = new int[length];
            for (int i = 0; i < length; i++) {
                weights[i] = 1 + random.nextInt(50);
            }
            int days = length + random.nextInt(10);
            assertMatchesOracle(weights, days, "extra-days case " + caseNumber);
        }
    }

    @Test
    void testManyEqualMinimumPackages() {
        int[] weights = new int[1_000];
        Arrays.fill(weights, 1);
        assertEquals(2, solution.shipWithinDays(weights, 500));
        assertEquals(1, solution.shipWithinDays(weights, 1_000));
        assertEquals(1_000, solution.shipWithinDays(weights, 1));
    }

    @Test
    void testManyEqualMaximumPackagesWithTightDeadline() {
        int[] weights = new int[1_000];
        Arrays.fill(weights, 500);
        assertEquals(500_000, solution.shipWithinDays(weights, 1));
        assertEquals(1_000, solution.shipWithinDays(weights, 500));
        assertEquals(500, solution.shipWithinDays(weights, 1_000));
    }

    @Test
    void testInterleavedLargeAndSmallPackages() {
        assertMatchesOracle(new int[]{500, 1, 499, 2, 498, 3, 497, 4}, 4);
        assertMatchesOracle(new int[]{1, 500, 1, 500, 1, 500, 1, 500}, 5);
        assertMatchesOracle(new int[]{250, 250, 250, 251, 249, 251, 249}, 3);
    }

    @Test
    void testLongTotalUsesLongOracleArithmetic() {
        int[] weights = new int[50_000];
        Arrays.fill(weights, 500);
        // The independent exhaustive oracle is intentionally reserved for
        // small totals; this legal maximum input directly exercises the
        // implementation's large sum without making the test quadratic in
        // the candidate-capacity range.
        assertEquals(25_000_000, solution.shipWithinDays(weights, 1));
    }

    @Test
    void testFreshInstancesAgreeWithTheIndependentOracle() {
        int[] weights = {12, 1, 8, 3, 6, 2};
        int expected = bruteForceCapacity(weights, 3);
        assertEquals(expected, new ShipWithinDays_1011().shipWithinDays(weights.clone(), 3));
        assertEquals(expected, new ShipWithinDays_1011().shipWithinDays(weights.clone(), 3));
    }

    private void enumerateArrays(int[] weights, int position) {
        if (position == weights.length) {
            for (int days = 1; days <= weights.length + 1; days++) {
                assertMatchesOracle(weights, days, "exhaustive " + Arrays.toString(weights)
                        + ", days=" + days);
            }
            return;
        }
        for (int weight = 1; weight <= 4; weight++) {
            weights[position] = weight;
            enumerateArrays(weights, position + 1);
        }
    }

    private void assertMatchesOracle(int[] weights, int days) {
        assertMatchesOracle(weights, days, Arrays.toString(weights) + ", days=" + days);
    }

    private void assertMatchesOracle(int[] weights, int days, String context) {
        assertEquals(bruteForceCapacity(weights, days), solution.shipWithinDays(weights.clone(), days), context);
    }

    private static int bruteForceCapacity(int[] weights, int days) {
        int largest = 0;
        long total = 0;
        for (int weight : weights) {
            largest = Math.max(largest, weight);
            total += weight;
        }
        for (long capacity = largest; capacity <= total; capacity++) {
            if (requiresAtMostDays(weights, capacity, days)) {
                return (int) capacity;
            }
        }
        throw new AssertionError("No capacity found");
    }

    private static boolean requiresAtMostDays(int[] weights, long capacity, int days) {
        int requiredDays = 1;
        long loaded = 0;
        for (int weight : weights) {
            if (loaded + weight > capacity) {
                requiredDays++;
                loaded = weight;
            } else {
                loaded += weight;
            }
        }
        return requiredDays <= days;
    }

    private static int[] maximumWeights(int length) {
        int[] weights = new int[length];
        Arrays.fill(weights, 500);
        return weights;
    }
}
