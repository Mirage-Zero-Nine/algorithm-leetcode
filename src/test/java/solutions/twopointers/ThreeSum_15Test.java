package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for the two-pointer implementation in {@link ThreeSum_15}.
 *
 * <p>The problem permits the triplets and their values to be returned in any
 * order. Assertions therefore sort each triplet and then sort the result list
 * before comparing it. The canonical representation remains list-based so
 * duplicate result occurrences would not be hidden by a {@code Set}.</p>
 */
class ThreeSum_15Test {

    private static final Comparator<List<Integer>> TRIPLET_COMPARATOR = Comparator
            .comparing((List<Integer> triplet) -> triplet.get(0))
            .thenComparing(triplet -> triplet.get(1))
            .thenComparing(triplet -> triplet.get(2));

    private final ThreeSum_15 solution = new ThreeSum_15();

    @ParameterizedTest(name = "valid case {index}")
    @MethodSource("validCases")
    void findsTheExpectedUniqueTriplets(int[] input, List<List<Integer>> expected) {
        assertTripletsEqual(expected, solution.threeSum(input.clone()));
    }

    private static Stream<Arguments> validCases() {
        return Stream.of(
                // Official example.
                Arguments.of(
                        new int[]{-1, 0, 1, 2, -1, -4},
                        List.of(List.of(-1, -1, 2), List.of(-1, 0, 1))
                ),

                // Unsorted input with several overlapping answers.
                Arguments.of(
                        new int[]{3, -3, 0, 2, -2, 1, -1},
                        List.of(
                                List.of(-3, 0, 3),
                                List.of(-3, 1, 2),
                                List.of(-2, -1, 3),
                                List.of(-2, 0, 2),
                                List.of(-1, 0, 1)
                        )
                ),

                // A valid triplet may contain repeated values.
                Arguments.of(
                        new int[]{-2, 0, 1, 1, 2},
                        List.of(List.of(-2, 0, 2), List.of(-2, 1, 1))
                ),

                // Valid triplets do not need to contain zero.
                Arguments.of(
                        new int[]{-10, -4, -3, 7, 10, 13},
                        List.of(List.of(-10, -3, 13), List.of(-4, -3, 7))
                ),

                // Duplicate values must produce each value combination only once.
                Arguments.of(
                        new int[]{-2, -2, -2, 0, 0, 0, 2, 2, 2},
                        List.of(List.of(-2, 0, 2), List.of(0, 0, 0))
                ),
                Arguments.of(
                        new int[]{0, 0, 0, 0, 0},
                        List.of(List.of(0, 0, 0))
                ),
                Arguments.of(
                        new int[]{-1, -1, -1, 2, 2, 2},
                        List.of(List.of(-1, -1, 2))
                ),

                // Boundary values within the LeetCode input constraint.
                Arguments.of(
                        new int[]{-100_000, -99_999, -1, 0, 1, 99_999, 100_000},
                        List.of(
                                List.of(-100_000, 0, 100_000),
                                List.of(-100_000, 1, 99_999),
                                List.of(-99_999, -1, 100_000),
                                List.of(-99_999, 0, 99_999),
                                List.of(-1, 0, 1)
                        )
                )
        );
    }

    @ParameterizedTest(name = "no-solution case {index}")
    @MethodSource("noSolutionCases")
    void returnsNoTripletsWhenNoZeroSumExists(int[] input) {
        assertEquals(List.of(), solution.threeSum(input.clone()));
    }

    private static Stream<Arguments> noSolutionCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6}),
                Arguments.of(new int[]{-6, -5, -4, -3, -2, -1}),
                Arguments.of(new int[]{-5, -4, -2, 1, 2}),
                Arguments.of(new int[]{-2, 0, 1, 4}),
                Arguments.of(new int[]{0, 0, 1}),
                Arguments.of(new int[]{1, 1, 1, 1})
        );
    }

    @ParameterizedTest(name = "short/null input case {index}")
    @MethodSource("shortOrNullInputs")
    void returnsNoTripletsForNullOrTooSmallInput(int[] input) {
        assertEquals(List.of(), solution.threeSum(input));
    }

    private static Stream<Arguments> shortOrNullInputs() {
        return Stream.of(
                Arguments.of((int[]) null),
                Arguments.of(new int[]{}),
                Arguments.of(new int[]{0}),
                Arguments.of(new int[]{-1, 1}),
                Arguments.of(new int[]{1, 2})
        );
    }

    @Test
    void crossChecksTheTwoPointerApproachAgainstBruteForce() {
        Random random = new Random(42L);

        // Small arrays make the cubic reference implementation practical while
        // exercising duplicates, unsorted input, and positive/negative values.
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int[] input = new int[3 + random.nextInt(8)];
            for (int i = 0; i < input.length; i++) {
                input[i] = random.nextInt(21) - 10;
            }

            assertTripletsEqual(bruteForceTriplets(input), solution.threeSum(input.clone()));
        }
    }

    @Test
    void handlesTheMaximumAllowedInputSize() {
        // LeetCode 15 allows at most 300 values. This input has many duplicate
        // values and several distinct expected combinations.
        int[] input = new int[300];
        for (int i = 0; i < input.length; i++) {
            input[i] = (i % 21) - 10;
        }

        List<List<Integer>> result = solution.threeSum(input.clone());
        assertTripletsEqual(bruteForceTriplets(input), result);
        assertResultProperties(result);
    }

    private static List<List<Integer>> bruteForceTriplets(int[] input) {
        int[] sorted = input.clone();
        Arrays.sort(sorted);
        Set<List<Integer>> triplets = new HashSet<>();

        for (int i = 0; i < sorted.length - 2; i++) {
            for (int j = i + 1; j < sorted.length - 1; j++) {
                for (int k = j + 1; k < sorted.length; k++) {
                    if ((long) sorted[i] + sorted[j] + sorted[k] == 0) {
                        triplets.add(List.of(sorted[i], sorted[j], sorted[k]));
                    }
                }
            }
        }

        return new ArrayList<>(triplets);
    }

    private static void assertResultProperties(List<List<Integer>> result) {
        List<List<Integer>> normalized = normalizeTriplets(result);

        // Every returned candidate must contain exactly three values and sum to zero.
        for (List<Integer> triplet : normalized) {
            assertEquals(3, triplet.size());
            assertEquals(0L, (long) triplet.get(0) + triplet.get(1) + triplet.get(2));
        }

        // A value combination must not be returned more than once.
        assertEquals(normalized.size(), new HashSet<>(normalized).size());
    }

    private static void assertTripletsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(normalizeTriplets(expected), normalizeTriplets(actual));
    }

    private static List<List<Integer>> normalizeTriplets(List<List<Integer>> triplets) {
        List<List<Integer>> normalized = new ArrayList<>();
        for (List<Integer> triplet : triplets) {
            List<Integer> sortedTriplet = new ArrayList<>(triplet);
            sortedTriplet.sort(Comparator.naturalOrder());
            normalized.add(sortedTriplet);
        }
        normalized.sort(TRIPLET_COMPARATOR);
        return normalized;
    }
}
