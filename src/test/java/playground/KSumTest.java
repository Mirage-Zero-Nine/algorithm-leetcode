package playground;

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
 * Tests for the generalized exact-{@code k} combination search in {@link KSum}.
 *
 * <p>Combinations may be returned in any order, so these tests sort each
 * combination and then sort the result list before comparing it. The
 * normalized result remains a list rather than a set, allowing duplicate
 * combinations in the implementation to be detected.</p>
 */
class KSumTest {

    private static final Comparator<List<Integer>> COMBINATION_COMPARATOR =
            KSumTest::compareCombinations;

    private final KSum solution = new KSum();

    @ParameterizedTest(name = "valid case {index}: k={1}, target={2}")
    @MethodSource("validCases")
    void findsExpectedCombinations(int[] input, int k, int target, List<List<Integer>> expected) {
        assertCombinationsEqual(expected, solution.kSum(input.clone(), k, target));
    }

    private static Stream<Arguments> validCases() {
        return Stream.of(
                // Examples from KSum.main(...).
                Arguments.of(
                        new int[]{1, 3, 4, 7, 4, 5, 4, 6, 7, 2, 3, 4, 6, 7, 8, 7, 2, 2},
                        4,
                        7,
                        List.of(List.of(1, 2, 2, 2))
                ),
                Arguments.of(
                        new int[]{1, 0, -1, 0, -2, 2},
                        4,
                        0,
                        List.of(
                                List.of(-2, -1, 1, 2),
                                List.of(-2, 0, 0, 2),
                                List.of(-1, 0, 0, 1)
                        )
                ),
                Arguments.of(
                        new int[]{1, -2, -5, -4, -3, 3, 3, 5},
                        4,
                        -11,
                        List.of(List.of(-5, -4, -3, 1))
                ),

                // k = 1 returns a value at most once, even if it appears
                // multiple times in the input.
                Arguments.of(
                        new int[]{5, -1, 5, 0, -1},
                        1,
                        -1,
                        List.of(List.of(-1))
                ),

                // k = 2 exercises the generalized two-sum case.
                Arguments.of(
                        new int[]{-3, -1, 0, 2, 4, 5},
                        2,
                        1,
                        List.of(List.of(-3, 4), List.of(-1, 2))
                ),
                Arguments.of(
                        new int[]{1, 1, 2, 2, 3, 3},
                        2,
                        4,
                        List.of(List.of(1, 3), List.of(2, 2))
                ),

                // k = 3 with negative, zero, and positive values.
                Arguments.of(
                        new int[]{-2, -1, 0, 1, 2},
                        3,
                        0,
                        List.of(List.of(-2, 0, 2), List.of(-1, 0, 1))
                ),

                // Repeated values are usable only as many times as they occur.
                Arguments.of(
                        new int[]{-2, -2, -1, 0, 0, 1, 2, 2},
                        3,
                        0,
                        List.of(List.of(-2, 0, 2), List.of(-1, 0, 1))
                ),
                Arguments.of(
                        new int[]{0, 0, 0, 0, 0},
                        4,
                        0,
                        List.of(List.of(0, 0, 0, 0))
                ),

                // All values can be used when k equals the input length.
                Arguments.of(
                        new int[]{3, 1, 2},
                        3,
                        6,
                        List.of(List.of(1, 2, 3))
                ),

                // A target may be positive even when the input contains
                // negative values.
                Arguments.of(
                        new int[]{-5, -2, 1, 4, 6},
                        3,
                        5,
                        List.of(List.of(-5, 4, 6), List.of(-2, 1, 6))
                ),

                // Use long arithmetic internally so a valid result is not lost
                // when an intermediate remaining target exceeds int range.
                Arguments.of(
                        new int[]{Integer.MIN_VALUE, 1, Integer.MAX_VALUE},
                        3,
                        0,
                        List.of(List.of(Integer.MIN_VALUE, 1, Integer.MAX_VALUE))
                )
        );
    }

    @ParameterizedTest(name = "no-solution case {index}: k={1}, target={2}")
    @MethodSource("noSolutionCases")
    void returnsAnEmptyListWhenNoCombinationExists(int[] input, int k, int target) {
        assertEquals(List.of(), solution.kSum(input.clone(), k, target));
    }

    private static Stream<Arguments> noSolutionCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 2, 100),
                Arguments.of(new int[]{1, 2, 3}, 2, 2),
                Arguments.of(new int[]{-3, -2, -1}, 2, 0),
                Arguments.of(new int[]{-5, -4, 1, 2}, 3, 0),
                Arguments.of(new int[]{1, 1, 1}, 2, 3),
                Arguments.of(new int[]{0, 0, 1}, 3, 0)
        );
    }

    @ParameterizedTest(name = "invalid input case {index}: k={1}, target={2}")
    @MethodSource("invalidInputCases")
    void returnsAnEmptyListForUnsupportedInput(int[] input, int k, int target) {
        assertEquals(List.of(), solution.kSum(input, k, target));
    }

    private static Stream<Arguments> invalidInputCases() {
        return Stream.of(
                Arguments.of((int[]) null, 1, 0),
                Arguments.of(new int[]{}, 1, 0),
                Arguments.of(new int[]{1, 2, 3}, 0, 0),
                Arguments.of(new int[]{1, 2, 3}, -1, 0),
                Arguments.of(new int[]{1, 2, 3}, 4, 6),
                Arguments.of(new int[]{1, 2}, 3, 3)
        );
    }

    @Test
    void crossChecksKSumAgainstBruteForceForSmallInputs() {
        Random random = new Random(42L);

        // The reference enumerator is exponential, so small inputs provide a
        // useful correctness check without turning this test into a benchmark.
        for (int caseNumber = 0; caseNumber < 300; caseNumber++) {
            int[] input = new int[1 + random.nextInt(8)];
            for (int i = 0; i < input.length; i++) {
                input[i] = random.nextInt(11) - 5;
            }

            int k = 1 + random.nextInt(input.length);
            int target = random.nextInt(21) - 10;

            assertCombinationsEqual(
                    bruteForceCombinations(input, k, target),
                    solution.kSum(input.clone(), k, target)
            );
        }
    }

    @Test
    void preservesUniquenessWhenManyInputValuesAreDuplicated() {
        int[] input = new int[200];
        Arrays.fill(input, 0, 50, -2);
        Arrays.fill(input, 50, 100, -1);
        Arrays.fill(input, 100, 150, 1);
        Arrays.fill(input, 150, 200, 2);

        List<List<Integer>> expected = List.of(
                List.of(-2, -2, 2, 2),
                List.of(-2, -1, 1, 2),
                List.of(-1, -1, 1, 1)
        );

        assertCombinationsEqual(expected, solution.kSum(input, 4, 0));
    }

    @Test
    void returnsOnlyUniqueCombinationsWithTheRequestedSizeAndSum() {
        int[] input = {-4, -2, -1, 0, 1, 2, 4, 6};
        List<List<Integer>> result = solution.kSum(input, 4, 3);

        List<List<Integer>> normalized = normalizeCombinations(result);
        for (List<Integer> combination : normalized) {
            assertEquals(4, combination.size());
            assertEquals(3L, combination.stream().mapToLong(Integer::longValue).sum());
        }
        assertEquals(normalized.size(), new HashSet<>(normalized).size());
    }

    private static List<List<Integer>> bruteForceCombinations(int[] input, int k, int target) {
        Set<List<Integer>> combinations = new HashSet<>();
        enumerate(input, 0, k, target, new ArrayList<>(), combinations);
        return new ArrayList<>(combinations);
    }

    private static void enumerate(
            int[] input,
            int position,
            int k,
            long target,
            List<Integer> current,
            Set<List<Integer>> combinations
    ) {
        if (k == 0) {
            if (target == 0) {
                List<Integer> combination = new ArrayList<>(current);
                combination.sort(Comparator.naturalOrder());
                combinations.add(combination);
            }
            return;
        }

        for (int i = position; i <= input.length - k; i++) {
            current.add(input[i]);
            enumerate(input, i + 1, k - 1, target - input[i], current, combinations);
            current.remove(current.size() - 1);
        }
    }

    private static void assertCombinationsEqual(
            List<List<Integer>> expected,
            List<List<Integer>> actual
    ) {
        assertEquals(normalizeCombinations(expected), normalizeCombinations(actual));
    }

    private static List<List<Integer>> normalizeCombinations(List<List<Integer>> combinations) {
        List<List<Integer>> normalized = new ArrayList<>();
        for (List<Integer> combination : combinations) {
            List<Integer> sortedCombination = new ArrayList<>(combination);
            sortedCombination.sort(Comparator.naturalOrder());
            normalized.add(sortedCombination);
        }
        normalized.sort(COMBINATION_COMPARATOR);
        return normalized;
    }

    private static int compareCombinations(List<Integer> left, List<Integer> right) {
        for (int i = 0; i < Math.min(left.size(), right.size()); i++) {
            int comparison = Integer.compare(left.get(i), right.get(i));
            if (comparison != 0) {
                return comparison;
            }
        }
        return Integer.compare(left.size(), right.size());
    }
}
