package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MinEatingSpeed_875Test {

    private final MinEatingSpeed_875 test = new MinEatingSpeed_875();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        assertEquals(30, test.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minEatingSpeed(new int[]{1}, 1));
        assertEquals(1, test.minEatingSpeed(new int[]{1, 1}, 2));
        assertEquals(3, test.minEatingSpeed(new int[]{3}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(23, test.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
    }

    @Test
    public void testMoreHappyCases() {
        assertEquals(2, test.minEatingSpeed(new int[]{2, 2, 2, 2}, 4));
        assertEquals(1, test.minEatingSpeed(new int[]{2, 2, 2, 2}, 8));
    }

    @Test
    public void testHoursEqualsPileCount() {
        assertEquals(9, test.minEatingSpeed(new int[]{9, 7, 5}, 3));
    }

    @Test
    public void testSinglePileLargeHours() {
        assertEquals(1, test.minEatingSpeed(new int[]{100}, 100));
    }

    @Test
    public void testSinglePileTightHours() {
        assertEquals(34, test.minEatingSpeed(new int[]{100}, 3));
    }

    @Test
    public void testMixedPilesWithExactSpeedBoundary() {
        assertEquals(7, test.minEatingSpeed(new int[]{5, 6, 7, 8}, 5));
    }

    @Test
    public void testGiantCase() {
        int[] piles = new int[1000];
        for (int i = 0; i < piles.length; i++) {
            piles[i] = 100000000;
        }
        assertEquals(100000000, test.minEatingSpeed(piles, 1000));
    }

    @Test
    public void testAnotherBoundaryCase() {
        assertEquals(4, test.minEatingSpeed(new int[]{8, 8, 8}, 6));
    }

    @Test
    public void testDuplicatePilesWithExactDeadline() {
        assertEquals(5, test.minEatingSpeed(new int[]{5, 5, 5, 5}, 4));
    }

    @Test
    public void testCandidateBelowAnswerWouldMissDeadline() {
        // At speed 3 these piles require 2 + 2 + 3 = 7 hours, so speed 4 is minimal for h = 6.
        assertEquals(4, test.minEatingSpeed(new int[]{4, 5, 7}, 6));
    }

    @Test
    public void testMaximumConstraintCase() {
        int[] piles = new int[10_000];
        java.util.Arrays.fill(piles, 1_000_000_000);

        assertEquals(10_000, test.minEatingSpeed(piles, 1_000_000_000));
    }

    @Test
    public void testOfficialExampleWithIndependentOracle() {
        assertMatchesBruteForce(new int[]{3, 6, 7, 11}, 8);
    }

    @Test
    public void testOnePileAtEveryRoundingBoundary() {
        assertMatchesBruteForce(new int[]{1}, 1);
        assertMatchesBruteForce(new int[]{2}, 1);
        assertMatchesBruteForce(new int[]{3}, 2);
        assertMatchesBruteForce(new int[]{10}, 3);
        assertMatchesBruteForce(new int[]{10}, 10);
        assertMatchesBruteForce(new int[]{10}, 100);
    }

    @Test
    public void testHoursEqualPileCountRequiresLargestPileSpeed() {
        assertMatchesBruteForce(new int[]{1, 9, 3, 7}, 4);
        assertMatchesBruteForce(new int[]{12, 1, 12}, 3);
    }

    @Test
    public void testExtraHoursCanReduceSpeedToOne() {
        assertEquals(1, test.minEatingSpeed(new int[]{1, 2, 3, 4}, 1_000_000_000));
        assertEquals(1, test.minEatingSpeed(new int[]{1_000_000_000}, 1_000_000_000));
        assertEquals(1, test.minEatingSpeed(new int[]{1, 1, 1, 1}, 4));
    }

    @Test
    public void testExactCeilingDivisionTransitions() {
        assertMatchesBruteForce(new int[]{5, 5, 5}, 5);
        assertMatchesBruteForce(new int[]{5, 6, 7, 8}, 5);
        assertMatchesBruteForce(new int[]{2, 3, 4}, 5);
        assertMatchesBruteForce(new int[]{9, 10, 11}, 7);
    }

    @Test
    public void testUnevenDuplicatesAndZerosAreNotUsed() {
        // Zero piles are outside LeetCode's contract; this checks only positive duplicates.
        assertMatchesBruteForce(new int[]{1, 1, 2, 2, 3, 3}, 8);
        assertMatchesBruteForce(new int[]{7, 7, 7, 1}, 7);
        assertMatchesBruteForce(new int[]{2, 9, 2, 9, 2}, 9);
    }

    @Test
    public void testMinimumAndMaximumPileValues() {
        assertMatchesBinaryOracle(new int[]{1, 1_000_000_000}, 2);
        assertMatchesBinaryOracle(new int[]{1, 1_000_000_000}, 1_000_000_000);
        assertMatchesBinaryOracle(new int[]{999_999_999, 1_000_000_000, 1}, 3);
    }

    @Test
    public void testMaximumPileWithTightDeadline() {
        assertEquals(1_000_000_000,
                test.minEatingSpeed(new int[]{1_000_000_000, 1, 1}, 3));
    }

    @Test
    public void testLargeHoursUseLongOracleArithmetic() {
        int[] piles = {1_000_000_000, 999_999_999, 999_999_998};
        assertMatchesBruteForce(piles, 1_000_000_000);
    }

    @Test
    public void testManyPilesWithTinyCounts() {
        int[] piles = new int[10_000];
        Arrays.fill(piles, 1);
        assertEquals(1, test.minEatingSpeed(piles, 10_000));
        assertEquals(1, test.minEatingSpeed(piles, 1_000_000_000));
    }

    @Test
    public void testManyPilesWithMaximumCountsAndMinimumHours() {
        int[] piles = new int[10_000];
        Arrays.fill(piles, 1_000_000_000);
        assertEquals(1_000_000_000, test.minEatingSpeed(piles, 10_000));
    }

    @Test
    public void testManyPilesExerciseMaximumPermittedHourSum() {
        int[] piles = new int[10_000];
        Arrays.fill(piles, 1_000_000_000);
        // At speed 10,000 each pile needs 100,000 hours: exactly 10^9 total.
        assertEquals(10_000, test.minEatingSpeed(piles, 1_000_000_000));
    }

    @Test
    public void testAlternatingSmallAndLargePiles() {
        assertMatchesBruteForce(new int[]{1, 100, 2, 99, 3, 98}, 12);
        assertMatchesBruteForce(new int[]{10, 1, 10, 1, 10, 1}, 10);
    }

    @Test
    public void testMonotonicPileOrderingDoesNotMatter() {
        int[] ascending = {1, 4, 7, 10, 13};
        int[] descending = {13, 10, 7, 4, 1};
        assertEquals(test.minEatingSpeed(ascending, 9), test.minEatingSpeed(descending, 9));
        assertEquals(bruteForceMinimumSpeed(ascending, 9), test.minEatingSpeed(ascending, 9));
    }

    @Test
    public void testExhaustiveSmallPositiveArraysAgainstBruteForce() {
        for (int first = 1; first <= 3; first++) {
            for (int second = 1; second <= 3; second++) {
                for (int third = 1; third <= 3; third++) {
                    int[] piles = {first, second, third};
                    for (int hours = 3; hours <= 9; hours++) {
                        assertMatchesBruteForce(piles, hours);
                    }
                }
            }
        }
    }

    @Test
    public void testDeterministicMixedCasesAgainstBruteForce() {
        int[][] cases = {
                {1, 4, 6, 9}, {2, 2, 7}, {3, 8, 13, 21}, {4, 4, 5, 6},
                {6, 11, 16}, {8, 15, 22, 29}, {12, 1, 12, 1}, {17, 19, 23}
        };
        int[] hours = {10, 8, 13, 7, 9, 20, 8, 11};
        for (int i = 0; i < cases.length; i++) {
            assertMatchesBruteForce(cases[i], hours[i]);
        }
    }

    @Test
    public void testInputIsNotMutated() {
        int[] piles = {11, 3, 7, 19, 2};
        int[] original = piles.clone();
        assertEquals(bruteForceMinimumSpeed(piles, 12), test.minEatingSpeed(piles, 12));
        assertArrayEquals(original, piles);
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        int[] first = {3, 6, 7, 11};
        int[] second = {30, 11, 23, 4, 20};
        assertEquals(4, test.minEatingSpeed(first, 8));
        assertEquals(30, test.minEatingSpeed(second, 5));
        assertEquals(4, test.minEatingSpeed(first, 8));
    }

    @Test
    public void testAnswerIsFeasibleAndPredecessorIsNot() {
        int[] piles = {4, 5, 7};
        int hours = 6;
        int answer = test.minEatingSpeed(piles, hours);
        assertEquals(4, answer);
        assertEquals(false, requiresAtMostHours(piles, hours, answer - 1));
        assertEquals(true, requiresAtMostHours(piles, hours, answer));
    }

    @Test
    public void testAllSpeedsAroundAnswerForSmallCase() {
        int[] piles = {6, 10, 14};
        int hours = 8;
        int answer = bruteForceMinimumSpeed(piles, hours);
        assertEquals(answer, test.minEatingSpeed(piles, hours));
        for (int speed = 1; speed <= 20; speed++) {
            boolean expected = requiresAtMostHours(piles, hours, speed);
            if (speed < answer) {
                assertEquals(false, expected);
            }
        }
    }

    @Test
    public void testMaximumAllowedHoursAndPileCountCombination() {
        int[] piles = {1_000_000_000, 999_999_999, 999_999_998, 999_999_997};
        assertEquals(4, test.minEatingSpeed(piles, 1_000_000_000));
        assertEquals(1_000_000_000, test.minEatingSpeed(piles, 4));
    }

    /** Independently scans candidate speeds and uses long hours to avoid sharing implementation arithmetic. */
    private static int bruteForceMinimumSpeed(int[] piles, int hours) {
        for (int speed = 1; speed <= Arrays.stream(piles).max().orElseThrow(); speed++) {
            if (requiresAtMostHours(piles, hours, speed)) {
                return speed;
            }
        }
        throw new AssertionError("A valid LeetCode input always has a feasible speed");
    }

    /** Independent binary-search oracle; its feasibility sum is deliberately wider than int. */
    private static int binaryOracleMinimumSpeed(int[] piles, int hours) {
        int left = 1;
        int right = Arrays.stream(piles).max().orElseThrow();
        while (left < right) {
            int speed = left + (right - left) / 2;
            if (requiresAtMostHours(piles, hours, speed)) {
                right = speed;
            } else {
                left = speed + 1;
            }
        }
        return left;
    }

    private static boolean requiresAtMostHours(int[] piles, int hours, int speed) {
        long consumedHours = 0;
        for (int pile : piles) {
            consumedHours += (pile + (long) speed - 1) / speed;
            if (consumedHours > hours) {
                return false;
            }
        }
        return true;
    }

    private void assertMatchesBruteForce(int[] piles, int hours) {
        int[] original = piles.clone();
        int expected = bruteForceMinimumSpeed(piles, hours);
        assertEquals(expected, test.minEatingSpeed(piles, hours));
        assertArrayEquals(original, piles);
    }

    private void assertMatchesBinaryOracle(int[] piles, int hours) {
        int[] original = piles.clone();
        assertEquals(binaryOracleMinimumSpeed(piles, hours), test.minEatingSpeed(piles, hours));
        assertArrayEquals(original, piles);
    }
}
