package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for LeetCode 354, Russian Doll Envelopes.
 *
 * <p>Expected values for ordinary cases come from an independent quadratic dynamic-programming
 * oracle. The oracle compares both dimensions directly, so it does not reproduce the solution's
 * width/height sort or its patience-sorting implementation.
 */
public class MaxEnvelopes_354Test {

    private final MaxEnvelopes_354 solution = new MaxEnvelopes_354();

    @Test
    void officialExamples() {
        assertExpected(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
        assertExpected(new int[][]{{1, 1}, {1, 1}, {1, 1}});
    }

    @Test
    void emptyInputUsesTheDocumentedImplementationGuard() {
        assertEquals(0, solution.maxEnvelopes(new int[][]{}));
    }

    @Test
    void singletonIsAlwaysOne() {
        assertExpected(new int[][]{{100_000, 1}});
    }

    @Test
    void strictlyIncreasingChainCanUseEveryEnvelope() {
        assertExpected(new int[][]{{4, 4}, {1, 1}, {3, 3}, {2, 2}, {5, 5}});
    }

    @Test
    void inputOrderDoesNotAffectTheAnswer() {
        assertExpected(new int[][]{{8, 9}, {1, 1}, {7, 8}, {2, 2}, {6, 7}, {3, 3}});
    }

    @Test
    void equalWidthsAreStrictlyNonNestable() {
        assertExpected(new int[][]{{2, 2}, {2, 3}, {2, 4}, {2, 1}});
    }

    @Test
    void equalHeightsAreStrictlyNonNestable() {
        assertExpected(new int[][]{{1, 7}, {2, 7}, {3, 7}, {4, 7}});
    }

    @Test
    void duplicateEnvelopesDoNotIncreaseTheChain() {
        assertExpected(new int[][]{{2, 3}, {2, 3}, {3, 4}, {3, 4}, {4, 5}, {4, 5}});
    }

    @Test
    void increasingWidthsAndDecreasingHeightsHaveNoTwoElementChain() {
        assertExpected(new int[][]{{1, 5}, {2, 4}, {3, 3}, {4, 2}, {5, 1}});
    }

    @Test
    void twoEnvelopesNeedBothDimensionsToIncrease() {
        assertExpected(new int[][]{{3, 4}, {4, 3}});
        assertExpected(new int[][]{{3, 4}, {3, 5}});
        assertExpected(new int[][]{{3, 4}, {4, 4}});
    }

    @Test
    void optimalChainMaySkipManyCompatibleLookingEnvelopes() {
        assertExpected(new int[][]{{1, 10}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 1}, {7, 6}});
    }

    @Test
    void duplicateWidthsAroundAValidChainRemainStrict() {
        assertExpected(new int[][]{{1, 1}, {2, 2}, {2, 3}, {3, 4}, {3, 5}, {4, 6}});
    }

    @Test
    void nestedChainCanHaveGapsInBothCoordinates() {
        assertExpected(new int[][]{{1, 1}, {10, 10}, {3, 4}, {20, 20}, {5, 7}, {30, 30}});
    }

    @Test
    void severalIndependentChainsChooseTheLongestOne() {
        assertExpected(new int[][]{{1, 100}, {2, 90}, {3, 80}, {10, 1}, {20, 2}, {30, 3},
                {40, 4}, {50, 5}});
    }

    @Test
    void minimumAndMaximumOfficialCoordinatesAreHandled() {
        assertExpected(new int[][]{{1, 1}, {100_000, 100_000}, {99_999, 99_998}, {2, 2}});
    }

    @Test
    void allMaximumCoordinateDuplicatesReturnOne() {
        assertExpected(new int[][]{{100_000, 100_000}, {100_000, 100_000}, {100_000, 100_000}});
    }

    @Test
    void repeatedCallsOnOneInstanceDoNotLeakState() {
        assertExpected(new int[][]{{1, 1}, {2, 2}, {3, 3}});
        assertExpected(new int[][]{{5, 5}, {5, 6}});
        assertExpected(new int[][]{{1, 9}, {2, 8}, {3, 7}, {4, 6}});
        assertExpected(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}});
    }

    @Test
    void exhaustiveSmallCoordinateInputsUseAnIndependentOracle() {
        int[] coordinates = {1, 2, 3};
        for (int length = 0; length <= 5; length++) {
            int cases = (int) Math.pow(coordinates.length * coordinates.length, length);
            for (int encoded = 0; encoded < cases; encoded++) {
                int value = encoded;
                int[][] envelopes = new int[length][2];
                for (int i = 0; i < length; i++) {
                    envelopes[i][0] = coordinates[value % coordinates.length];
                    value /= coordinates.length;
                    envelopes[i][1] = coordinates[value % coordinates.length];
                    value /= coordinates.length;
                }
                assertExpected(envelopes);
            }
        }
    }

    @Test
    void seededSmallInputsExerciseUnsortedDuplicatesAndMixedChains() {
        Random random = new Random(354_2026L);
        for (int caseNumber = 0; caseNumber < 300; caseNumber++) {
            int length = 1 + random.nextInt(9);
            int[][] envelopes = new int[length][2];
            for (int i = 0; i < length; i++) {
                envelopes[i][0] = 1 + random.nextInt(12);
                envelopes[i][1] = 1 + random.nextInt(12);
            }
            assertExpected(envelopes);
        }
    }

    @Test
    void comparatorOrdersWidthsAscendingAndEqualWidthsHeightsDescending() {
        MaxEnvelopes_354.EnvelopeComparator comparator = new MaxEnvelopes_354.EnvelopeComparator();
        assertEquals(-1, Integer.signum(comparator.compare(new int[]{1, 100_000}, new int[]{2, 1})));
        assertEquals(1, Integer.signum(comparator.compare(new int[]{2, 1}, new int[]{1, 100_000})));
        assertEquals(1, Integer.signum(comparator.compare(new int[]{7, 2}, new int[]{7, 3})));
        assertEquals(0, comparator.compare(new int[]{7, 3}, new int[]{7, 3}));
    }

    @Test
    void maximumLengthStrictChainIsWithinTheOfficialConstraint() {
        int[][] envelopes = new int[100_000][2];
        for (int i = 0; i < envelopes.length; i++) {
            envelopes[i][0] = i + 1;
            envelopes[i][1] = i + 1;
        }
        assertEquals(100_000, solution.maxEnvelopes(envelopes));
    }

    @Test
    void maximumLengthEqualWidthInputExercisesTheStrictTieSort() {
        int[][] envelopes = new int[100_000][2];
        for (int i = 0; i < envelopes.length; i++) {
            envelopes[i][0] = 50_000;
            envelopes[i][1] = (i % 100_000) + 1;
        }
        assertEquals(1, solution.maxEnvelopes(envelopes));
    }

    @Test
    void maximumLengthAdversarialHeightPatternHasTheExpectedLIS() {
        int n = 100_000;
        int[][] envelopes = new int[n][2];
        for (int i = 0; i < n; i++) {
            envelopes[i][0] = i + 1;
            envelopes[i][1] = n - i;
        }
        assertEquals(1, solution.maxEnvelopes(envelopes));
    }

    private void assertExpected(int[][] envelopes) {
        int expected = quadraticOracle(envelopes);
        assertEquals(expected, solution.maxEnvelopes(deepCopy(envelopes)),
                () -> "envelopes=" + Arrays.deepToString(envelopes));
    }

    /** Computes the longest strict two-dimensional chain without using the production ordering. */
    private static int quadraticOracle(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        int[][] ordered = deepCopy(envelopes);
        Arrays.sort(ordered, (left, right) -> {
            int widthComparison = Integer.compare(left[0], right[0]);
            return widthComparison != 0 ? widthComparison : Integer.compare(left[1], right[1]);
        });

        int[] longestEndingAt = new int[ordered.length];
        int answer = 0;
        for (int i = 0; i < ordered.length; i++) {
            longestEndingAt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (ordered[j][0] < ordered[i][0] && ordered[j][1] < ordered[i][1]) {
                    longestEndingAt[i] = Math.max(longestEndingAt[i], longestEndingAt[j] + 1);
                }
            }
            answer = Math.max(answer, longestEndingAt[i]);
        }
        return answer;
    }

    private static int[][] deepCopy(int[][] envelopes) {
        int[][] copy = new int[envelopes.length][];
        for (int i = 0; i < envelopes.length; i++) {
            copy[i] = envelopes[i].clone();
        }
        return copy;
    }
}
