package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the binary-search solution to LeetCode 1482.
 *
 * <p>The small and randomized cases use an independent linear-day oracle. It
 * counts disjoint runs of k bloomed, adjacent flowers directly for every
 * candidate day, rather than reusing the solution's binary-search logic.</p>
 */
class MinDays_1482Test {
    private final MinDays_1482 solution = new MinDays_1482();

    @Test
    void testOfficialExampleOne() {
        assertEquals(3, solution.minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
    }

    @Test
    void testOfficialExampleTwoImpossible() {
        assertEquals(-1, solution.minDays(new int[]{1, 10, 3, 10, 2}, 3, 2));
    }

    @Test
    void testOfficialExampleThreeRequiresLateFlower() {
        assertEquals(12, solution.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    @Test
    void testSingleFlowerAndSingleBouquet() {
        assertEquals(1, solution.minDays(new int[]{1}, 1, 1));
    }

    @Test
    void testOneFlowerPerBouquetUsesOrderStatistic() {
        assertEquals(5, solution.minDays(new int[]{5, 1, 9, 2, 7}, 3, 1));
    }

    @Test
    void testSingleBouquetNeedsAdjacentFlowers() {
        assertEquals(10, solution.minDays(new int[]{1, 10, 3, 10, 2}, 1, 2));
    }

    @Test
    void testNonAdjacentFlowersCannotBeCombined() {
        assertEquals(10, solution.minDays(new int[]{1, 10, 1}, 1, 2));
    }

    @Test
    void testAHighFlowerResetsAnExistingRun() {
        assertEquals(1, solution.minDays(new int[]{1, 1, 10, 1, 1}, 2, 2));
    }

    @Test
    void testRunLeavesCannotBeReusedAcrossBouquets() {
        assertEquals(1, solution.minDays(new int[]{1, 1, 1, 1, 1}, 2, 2));
    }

    @Test
    void testUnsortedBloomDaysAndDuplicates() {
        assertEquals(4, solution.minDays(new int[]{4, 4, 2, 4, 2, 4}, 2, 2));
    }

    @Test
    void testAllFlowersRequiredInOneBouquet() {
        assertEquals(9, solution.minDays(new int[]{9, 1, 5}, 1, 3));
    }

    @Test
    void testExactFitOfSeveralBouquets() {
        assertEquals(3, solution.minDays(new int[]{1, 2, 3, 1, 2, 3}, 2, 3));
    }

    @Test
    void testTooFewFlowersForRequiredBouquets() {
        assertEquals(-1, solution.minDays(new int[]{1, 2, 3}, 2, 2));
    }

    @Test
    void testAllEqualBloomDays() {
        assertEquals(5, solution.minDays(new int[]{5, 5, 5, 5, 5}, 2, 2));
    }

    @Test
    void testMinimumPossibleAnswer() {
        assertEquals(2, solution.minDays(new int[]{2, 2, 3}, 1, 2));
    }

    @Test
    void testMaximumPossibleAnswer() {
        assertEquals(1_000_000_000,
                solution.minDays(new int[]{1_000_000_000, 1_000_000_000}, 1, 2));
    }

    @Test
    void testDifferentRunsAtTheSameDay() {
        assertEquals(4, solution.minDays(new int[]{4, 4, 9, 4, 4, 4}, 2, 2));
    }

    @Test
    void testLaterRunCanSupplyTheRequiredBouquets() {
        assertEquals(6, solution.minDays(new int[]{8, 8, 1, 6, 6, 6, 2, 2}, 2, 3));
    }

    @Test
    void testDuplicateThresholdsAroundAnAdjacencyBreak() {
        assertEquals(3, solution.minDays(new int[]{3, 3, 8, 3, 3, 8, 3}, 2, 2));
    }

    @Test
    void testMaximumBouquetCountWithUnitK() {
        assertEquals(1, solution.minDays(new int[]{1, 1, 1, 1, 1}, 5, 1));
    }

    @Test
    void testMaximumMWithinOfficialBoundsWhenGardenIsTooSmall() {
        assertEquals(-1, solution.minDays(new int[]{1, 2, 3}, 1_000_000, 1));
    }

    @Test
    void testMultiplicationOverflowInFlowerRequirement() {
        int[] bloomDay = new int[3_000];
        Arrays.fill(bloomDay, 1);

        // m*k = 3,000,000,000, which is larger than n but overflows a Java int.
        assertEquals(-1, solution.minDays(bloomDay, 1_000_000, 3_000));
    }

    @Test
    void testMaximumKAtMaximumGardenLength() {
        int[] bloomDay = new int[100_000];
        Arrays.fill(bloomDay, 17);
        assertEquals(17, solution.minDays(bloomDay, 1, 100_000));
    }

    @Test
    void testInputIsNotMutated() {
        int[] bloomDay = {8, 2, 7, 2, 9, 2};
        int[] original = bloomDay.clone();

        assertEquals(8, solution.minDays(bloomDay, 2, 2));
        assertArrayEquals(original, bloomDay);
    }

    @Test
    void testRepeatedCallsOnOneInstanceAreIndependent() {
        assertEquals(3, solution.minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
        assertEquals(12, solution.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
        assertEquals(-1, solution.minDays(new int[]{1, 2, 3}, 2, 2));
        assertEquals(1, solution.minDays(new int[]{1, 1, 1}, 3, 1));
    }

    @Test
    void testEverySmallBloomArrayAndValidMAndKAgainstLinearOracle() {
        for (int length = 1; length <= 5; length++) {
            enumerateArrays(new int[length], 0);
        }
    }

    @Test
    void testSeededRandomSmallArraysAgainstLinearOracle() {
        Random random = new Random(1_482L);
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int length = 1 + random.nextInt(20);
            int[] bloomDay = new int[length];
            for (int i = 0; i < length; i++) {
                bloomDay[i] = 1 + random.nextInt(12);
            }
            int m = 1 + random.nextInt(length);
            int k = 1 + random.nextInt(length);
            assertEquals(linearDayOracle(bloomDay, m, k), solution.minDays(bloomDay, m, k),
                    "random case " + caseNumber + ": " + Arrays.toString(bloomDay)
                            + ", m=" + m + ", k=" + k);
        }
    }

    @Test
    void testMaximumLengthAscendingGardenWithOneFullBouquet() {
        int[] bloomDay = new int[100_000];
        for (int i = 0; i < bloomDay.length; i++) {
            bloomDay[i] = i + 1;
        }
        assertEquals(100_000, solution.minDays(bloomDay, 1, 100_000));
    }

    @Test
    void testMaximumLengthAllBloomedGardenWithManyBouquets() {
        int[] bloomDay = new int[100_000];
        Arrays.fill(bloomDay, 1);
        assertEquals(1, solution.minDays(bloomDay, 50_000, 2));
    }

    private void enumerateArrays(int[] bloomDay, int position) {
        if (position == bloomDay.length) {
            for (int m = 1; m <= bloomDay.length; m++) {
                for (int k = 1; k <= bloomDay.length; k++) {
                    assertEquals(linearDayOracle(bloomDay, m, k), solution.minDays(bloomDay, m, k),
                            "array=" + Arrays.toString(bloomDay) + ", m=" + m + ", k=" + k);
                }
            }
            return;
        }
        for (int day = 1; day <= 3; day++) {
            bloomDay[position] = day;
            enumerateArrays(bloomDay, position + 1);
        }
    }

    /**
     * Independent oracle: try every possible day and greedily count disjoint
     * adjacent groups in each bloomed run. The long product deliberately
     * avoids reproducing the implementation's potential int overflow.
     */
    private static int linearDayOracle(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) {
            return -1;
        }
        int minimumDay = Arrays.stream(bloomDay).min().orElseThrow();
        int maximumDay = Arrays.stream(bloomDay).max().orElseThrow();
        for (int day = minimumDay; day <= maximumDay; day++) {
            int bouquets = countBouquets(bloomDay, day, k);
            if (bouquets >= m) {
                return day;
            }
        }
        return -1;
    }

    private static int countBouquets(int[] bloomDay, int day, int k) {
        int bouquets = 0;
        int consecutive = 0;
        for (int bloom : bloomDay) {
            if (bloom > day) {
                consecutive = 0;
            } else if (++consecutive == k) {
                bouquets++;
                consecutive = 0;
            }
        }
        return bouquets;
    }
}
