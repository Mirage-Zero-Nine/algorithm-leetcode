package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Contract tests for the Boyer-Moore based implementation of LeetCode 1157.
 *
 * <p>For every valid query, {@code threshold} is a strict majority of the
 * queried range. Therefore an independent frequency oracle has at most one
 * possible answer, which lets these tests validate the implementation without
 * comparing two implementations of the same algorithm.</p>
 */
public class MajorityChecker_1157Test {

    @Test
    public void officialExampleQueries() {
        int[] values = {1, 1, 2, 2, 1, 1};
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        assertEquals(1, checker.query(0, 5, 4));
        assertEquals(-1, checker.query(0, 3, 3));
        assertEquals(2, checker.query(2, 3, 2));
    }

    @Test
    public void exactThresholdIsAccepted() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{2, 2, 1, 1, 2});

        assertEquals(2, checker.query(0, 4, 3));
        assertEquals(2, checker.query(0, 2, 2));
        assertEquals(1, checker.query(2, 3, 2));
    }

    @Test
    public void thresholdAboveActualFrequencyReturnsMinusOne() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{1, 2, 1, 2, 3, 4});

        assertEquals(-1, checker.query(0, 3, 3));
        assertEquals(-1, checker.query(1, 5, 4));
        assertEquals(-1, checker.query(0, 5, 4));
    }

    @Test
    public void singletonRangesAndThresholdBoundaries() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{5, 6, 7});

        assertEquals(5, checker.query(0, 0, 1));
        assertEquals(6, checker.query(1, 1, 1));
        assertEquals(7, checker.query(2, 2, 1));
        // This is outside the LeetCode contract but is defined by the implementation.
        assertEquals(-1, checker.query(0, 0, 2));
    }

    @Test
    public void allEqualValuesMeetEveryValidThreshold() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{3, 3, 3, 3, 3, 3, 3, 3});

        // threshold=1 is implementation-supported although LeetCode requires a strict majority.
        assertEquals(3, checker.query(0, 7, 1));
        assertEquals(3, checker.query(0, 7, 5));
        assertEquals(3, checker.query(0, 7, 8));
        assertEquals(3, checker.query(2, 5, 3));
    }

    @Test
    public void twoElementRangesRequireBothCopies() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{1, 1, 2, 2});

        assertEquals(1, checker.query(0, 1, 2));
        assertEquals(2, checker.query(2, 3, 2));
        assertEquals(-1, checker.query(1, 2, 2));
    }

    @Test
    public void rangeEndpointsAreInclusive() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{9, 1, 1, 2, 2, 2, 9});

        assertEquals(1, checker.query(1, 2, 2));
        assertEquals(2, checker.query(3, 5, 3));
        assertEquals(-1, checker.query(0, 6, 4));
        assertEquals(9, checker.query(0, 0, 1));
        assertEquals(9, checker.query(6, 6, 1));
    }

    @Test
    public void noCandidateAmongDistinctValues() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{1, 2, 3, 4, 5, 6, 7});

        assertEquals(-1, checker.query(0, 6, 4));
        assertEquals(-1, checker.query(1, 4, 3));
        assertEquals(-1, checker.query(2, 3, 2));
    }

    @Test
    public void valuesAtSupportedBoundsAndZeroAreComparedNormally() {
        int[] values = {0, 20_000, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        assertEquals(0, checker.query(0, 2, 2));
        assertEquals(Integer.MAX_VALUE, checker.query(3, 4, 2));
        assertEquals(Integer.MIN_VALUE, checker.query(5, 5, 1));
        assertEquals(-1, checker.query(0, 5, 4));
    }

    @Test
    public void negativeValuesAreHandledByTheIntegerImplementation() {
        int[] values = {-8, -8, 4, -8, 4, 4, 4};
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        assertEquals(-8, checker.query(0, 3, 3));
        assertEquals(4, checker.query(2, 6, 3));
        assertEquals(-1, checker.query(0, 5, 4));
    }

    @Test
    public void thresholdOneReturnsAnElementFromTheRange() {
        int[] values = {4, 5, 6, 7};
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        // The class handles this broader-than-LeetCode threshold as well.
        int result = checker.query(1, 3, 1);
        assertTrue(result == 5 || result == 6 || result == 7);
    }

    @Test
    public void candidateSurvivesInterleavedCancellations() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{2, 1, 2, 3, 2, 4, 2});

        assertEquals(2, checker.query(0, 6, 4));
        assertEquals(2, checker.query(2, 6, 3));
        assertEquals(-1, checker.query(0, 5, 4));
    }

    @Test
    public void candidateCanBeAtEitherEndOfRange() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{8, 1, 2, 2, 3, 8, 8});

        assertEquals(8, checker.query(0, 6, 3));
        assertEquals(2, checker.query(1, 3, 2));
        assertEquals(8, checker.query(5, 6, 2));
    }

    @Test
    public void overlappingQueriesDoNotShareCandidateState() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{1, 1, 2, 2, 2, 3, 3, 3, 3});

        assertEquals(1, checker.query(0, 1, 2));
        assertEquals(2, checker.query(1, 4, 3));
        assertEquals(3, checker.query(5, 8, 4));
        assertEquals(-1, checker.query(0, 8, 5));
        assertEquals(1, checker.query(0, 1, 2));
    }

    @Test
    public void repeatedQueriesOnDifferentInstancesAreIsolated() {
        MajorityChecker_1157 first = new MajorityChecker_1157(new int[]{10, 10, 20});
        MajorityChecker_1157 second = new MajorityChecker_1157(new int[]{20, 20, 20, 10});

        assertEquals(10, first.query(0, 1, 2));
        assertEquals(20, second.query(0, 3, 3));
        assertEquals(-1, first.query(0, 2, 3));
        assertEquals(10, second.query(3, 3, 1));
    }

    @Test
    public void queryDoesNotMutateCallerArray() {
        int[] values = {1, 2, 2, 3, 2, 4};
        int[] before = values.clone();
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        checker.query(0, 5, 3);
        checker.query(1, 4, 2);

        assertArrayEquals(before, values);
    }

    @Test
    public void validThresholdsAtEvenAndOddRangeLengths() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3});

        assertEquals(1, checker.query(0, 3, 3)); // length 4: minimum strict-majority threshold is 3
        assertEquals(2, checker.query(1, 5, 3)); // length 5: minimum strict-majority threshold is 3
        assertEquals(3, checker.query(5, 8, 3)); // length 4, exact threshold
    }

    @Test
    public void independentFrequencyOracleCoversAllSmallArrays() {
        int caseCount = 0;
        for (int length = 1; length <= 6; length++) {
            int combinations = 1;
            for (int i = 0; i < length; i++) {
                combinations *= 3;
            }
            for (int encoded = 0; encoded < combinations; encoded++) {
                int[] values = decodeTernaryArray(encoded, length);
                MajorityChecker_1157 checker = new MajorityChecker_1157(values);
                for (int left = 0; left < length; left++) {
                    for (int right = left; right < length; right++) {
                        int rangeLength = right - left + 1;
                        int threshold = rangeLength / 2 + 1;
                        assertEquals(expectedMajority(values, left, right, threshold),
                                checker.query(left, right, threshold),
                                "values=" + java.util.Arrays.toString(values)
                                        + ", left=" + left + ", right=" + right);
                        caseCount++;
                    }
                }
            }
        }
        assertEquals(19_956, caseCount);
    }

    @Test
    public void deterministicRandomQueriesMatchIndependentOracle() {
        Random random = new Random(1157L);
        int[] values = new int[240];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(9) + 1;
        }
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        for (int query = 0; query < 1_000; query++) {
            int left = random.nextInt(values.length);
            int right = left + random.nextInt(values.length - left);
            int threshold = (right - left + 1) / 2 + 1;
            assertEquals(expectedMajority(values, left, right, threshold),
                    checker.query(left, right, threshold), "query=" + query);
        }
    }

    @Test
    public void deterministicRandomArraysAndAllValidSubrangesMatchOracle() {
        Random random = new Random(2026L);
        for (int sample = 0; sample < 20; sample++) {
            int length = 1 + random.nextInt(30);
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = 1 + random.nextInt(20_000);
            }
            MajorityChecker_1157 checker = new MajorityChecker_1157(values);
            for (int left = 0; left < length; left++) {
                for (int right = left; right < length; right++) {
                    int threshold = (right - left + 1) / 2 + 1;
                    assertEquals(expectedMajority(values, left, right, threshold),
                            checker.query(left, right, threshold),
                            "sample=" + sample + ", left=" + left + ", right=" + right);
                }
            }
        }
    }

    @Test
    public void maximumArrayLengthAndQueryCountRemainBounded() {
        int[] values = new int[20_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i < 12_000 ? 7 : 8;
        }
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        assertEquals(7, checker.query(0, 19_999, 10_001));
        assertEquals(-1, checker.query(0, 19_999, 12_001));
        assertEquals(8, checker.query(12_000, 19_999, 8_000));

        // Exercise the stated 10,000-query limit with short ranges, avoiding a TLE-sized test workload.
        for (int query = 0; query < 10_000; query++) {
            int index = query % values.length;
            assertEquals(values[index], checker.query(index, index, 1));
        }
    }

    @Test
    public void maximumValueRangeWithManyDuplicateBlocks() {
        int[] values = new int[101];
        for (int i = 0; i < values.length; i++) {
            values[i] = i % 2 == 0 ? 1 : 20_000;
        }
        values[0] = 20_000;
        values[2] = 20_000;
        values[4] = 20_000;
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        assertEquals(20_000, checker.query(0, 4, 3));
        assertEquals(-1, checker.query(5, 100, 49));
        assertEquals(20_000, checker.query(0, 100, 51));
    }

    @Test
    public void thresholdGreaterThanRangeLengthReturnsMinusOne() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{4, 4, 4});

        // Invalid under LeetCode's threshold <= range-length constraint; class behavior is deterministic.
        assertEquals(-1, checker.query(0, 2, 4));
        assertEquals(-1, checker.query(1, 1, 2));
    }

    @Test
    public void exactCandidateThresholdWithDistractorsAtBothSides() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{5, 1, 2, 5, 3, 5, 4, 5, 5});

        assertEquals(5, checker.query(0, 8, 4));
        assertEquals(5, checker.query(1, 7, 3));
        assertEquals(-1, checker.query(0, 7, 5));
    }

    @Test
    public void candidateMustBePresentInRequestedRange() {
        MajorityChecker_1157 checker = new MajorityChecker_1157(new int[]{1, 1, 2, 2, 2, 3, 3});

        assertEquals(2, checker.query(2, 4, 3));
        assertEquals(3, checker.query(5, 6, 2));
        assertEquals(-1, checker.query(0, 1, 3));
    }

    @Test
    public void everySingleElementInLargeMixedArrayIsReturned() {
        int[] values = new int[257];
        for (int i = 0; i < values.length; i++) {
            values[i] = (i * 37) % 20_001;
        }
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        for (int index = 0; index < values.length; index++) {
            assertEquals(values[index], checker.query(index, index, 1));
        }
    }

    @Test
    public void sameInstanceSupportsQueriesAfterCallerMutatesAliasedArray() {
        int[] values = {1, 1, 2, 2, 2};
        MajorityChecker_1157 checker = new MajorityChecker_1157(values);

        assertEquals(2, checker.query(0, 4, 3));
        values[0] = 2;
        assertEquals(2, checker.query(0, 4, 3));
        values[1] = 9;
        assertEquals(2, checker.query(0, 4, 3));
    }

    private static int[] decodeTernaryArray(int encoded, int length) {
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = encoded % 3;
            encoded /= 3;
        }
        return values;
    }

    private static int expectedMajority(int[] values, int left, int right, int threshold) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = left; i <= right; i++) {
            counts.merge(values[i], 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() >= threshold) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
