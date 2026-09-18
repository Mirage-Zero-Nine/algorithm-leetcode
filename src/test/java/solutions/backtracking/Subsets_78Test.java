package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Subsets_78Test {
    private final Subsets_78 solution = new Subsets_78();

    private static Stream<Arguments> implementations() {
        Subsets_78 solution = new Subsets_78();
        return Stream.of(
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit)
        );
    }

    private static Stream<Arguments> exactCases() {
        Subsets_78 solution = new Subsets_78();
        Set<List<Integer>> oneElement = Set.of(List.of(), List.of(5));
        Set<List<Integer>> twoElements = Set.of(List.of(), List.of(1), List.of(2), List.of(1, 2));
        Set<List<Integer>> threeElements = Set.of(
                List.of(), List.of(1), List.of(2), List.of(3),
                List.of(1, 2), List.of(1, 3), List.of(2, 3), List.of(1, 2, 3));

        return Stream.of(
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets,
                        new int[]{}, Set.of(List.of())),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit,
                        new int[]{}, Set.of(List.of())),
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets,
                        new int[]{5}, oneElement),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit,
                        new int[]{5}, oneElement),
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets,
                        new int[]{1, 2}, twoElements),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit,
                        new int[]{1, 2}, twoElements),
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets,
                        new int[]{1, 2, 3}, threeElements),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit,
                        new int[]{1, 2, 3}, threeElements),
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets,
                        new int[]{3, 1, 2}, threeElements),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit,
                        new int[]{3, 1, 2}, threeElements),
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets,
                        new int[]{-1, 0, 1}, Set.of(
                                List.of(), List.of(-1), List.of(0), List.of(1),
                                List.of(-1, 0), List.of(-1, 1), List.of(0, 1), List.of(-1, 0, 1))),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit,
                        new int[]{-1, 0, 1}, Set.of(
                                List.of(), List.of(-1), List.of(0), List.of(1),
                                List.of(-1, 0), List.of(-1, 1), List.of(0, 1), List.of(-1, 0, 1))),
                Arguments.of("backtracking", (Function<int[], List<List<Integer>>>) solution::subsets,
                        new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, Set.of(
                                List.of(), List.of(Integer.MIN_VALUE), List.of(Integer.MAX_VALUE),
                                List.of(Integer.MIN_VALUE, Integer.MAX_VALUE))),
                Arguments.of("bit manipulation", (Function<int[], List<List<Integer>>>) solution::subsetsBit,
                        new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, Set.of(
                                List.of(), List.of(Integer.MIN_VALUE), List.of(Integer.MAX_VALUE),
                                List.of(Integer.MIN_VALUE, Integer.MAX_VALUE)))
        );
    }

    /**
     * Contract-oriented cases spanning the full LeetCode input range.  The expected values for
     * these cases are generated independently by {@link #bitmaskOracle(int[])} rather than by
     * either implementation under test.
     */
    private static Stream<Arguments> contractCoverageCases() {
        return Stream.of(
                Arguments.of("empty", new int[]{}),
                Arguments.of("zero", new int[]{0}),
                Arguments.of("minimum value", new int[]{-10}),
                Arguments.of("maximum value", new int[]{10}),
                Arguments.of("opposite extremes", new int[]{-10, 10}),
                Arguments.of("two negatives", new int[]{-9, -2}),
                Arguments.of("two positives", new int[]{2, 9}),
                Arguments.of("mixed three", new int[]{-1, 0, 1}),
                Arguments.of("reverse three", new int[]{7, 0, -7}),
                Arguments.of("mixed four", new int[]{10, -10, 3, -3}),
                Arguments.of("ordered five", new int[]{-5, -2, 0, 4, 8}),
                Arguments.of("permuted five", new int[]{8, 0, -5, 4, -2}),
                Arguments.of("six with zero", new int[]{6, -6, 1, -1, 10, 0}),
                Arguments.of("six descending", new int[]{9, 8, 7, 6, 5, 4}),
                Arguments.of("seven alternating", new int[]{-10, 10, -8, 8, -6, 6, 0}),
                Arguments.of("seven permuted", new int[]{3, -4, 9, -10, 0, 2, 7}),
                Arguments.of("eight broad range", new int[]{-10, -7, -3, -1, 1, 3, 7, 10}),
                Arguments.of("eight permuted", new int[]{4, -9, 0, 10, -2, 6, -6, 1}),
                Arguments.of("nine values", new int[]{-10, -8, -5, -1, 0, 2, 4, 7, 10}),
                Arguments.of("maximum ten", new int[]{-10, -9, -7, -4, -1, 0, 2, 5, 8, 10})
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void nullInputReturnsEmptyResult(String name, Function<int[], List<List<Integer>>> implementation) {
        assertTrue(implementation.apply(null).isEmpty());
    }

    @ParameterizedTest(name = "{0} - {1}")
    @MethodSource("exactCases")
    void representativeInputsProduceExactPowerSet(String name,
                                                   Function<int[], List<List<Integer>>> implementation,
                                                   int[] nums,
                                                   Set<List<Integer>> expected) {
        assertPowerSet(expected, implementation.apply(nums));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void subsetCountIsPowerOfTwo(String name, Function<int[], List<List<Integer>>> implementation) {
        for (int length = 0; length <= 10; length++) {
            int[] nums = new int[length];
            for (int index = 0; index < length; index++) {
                nums[index] = index;
            }

            List<List<Integer>> result = implementation.apply(nums);

            assertEquals(1 << length, result.size(), "Incorrect count for length " + length);
            assertEquals(result.size(), canonicalize(result).size(),
                    "Duplicate subset for length " + length);
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void resultContainsOnlyInputValuesAndNoRepeatedElements(
            String name, Function<int[], List<List<Integer>>> implementation) {
        int[] nums = {-5, 0, 7, 13};
        Set<Integer> inputValues = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        List<List<Integer>> result = implementation.apply(nums);

        for (List<Integer> subset : result) {
            assertTrue(inputValues.containsAll(subset), "Unexpected value in subset: " + subset);
            assertEquals(subset.size(), new HashSet<>(subset).size(),
                    "Repeated value in subset: " + subset);
        }
    }

    @ParameterizedTest(name = "{0} - {1}")
    @MethodSource("contractCoverageCases")
    void contractCasesMatchIndependentOracleAndContainNoDuplicateSubsets(
            String caseName, int[] nums) {
        Set<List<Integer>> expected = bitmaskOracle(nums);

        for (Function<int[], List<List<Integer>>> implementation : implementationFunctions()) {
            int[] input = nums.clone();
            List<List<Integer>> actual = implementation.apply(input);

            assertEquals(nums.length <= 10 ? 1 << nums.length : 0, actual.size(),
                    caseName + ": incorrect raw subset count");
            assertEquals(expected, canonicalize(actual), caseName + ": incorrect subset values");
            assertEquals(actual.size(), canonicalize(actual).size(),
                    caseName + ": duplicate subset was returned");
            assertArrayEquals(nums, input, caseName + ": input array was modified");

            Set<Integer> inputValues = Arrays.stream(nums).boxed().collect(Collectors.toSet());
            for (List<Integer> subset : actual) {
                assertTrue(inputValues.containsAll(subset),
                        caseName + ": subset contains a value outside the input: " + subset);
                assertEquals(subset.size(), new HashSet<>(subset).size(),
                        caseName + ": subset repeats an input value: " + subset);
            }
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void inputArrayIsNotModified(String name, Function<int[], List<List<Integer>>> implementation) {
        int[] nums = {3, -1, 2, 0};
        int[] original = nums.clone();

        implementation.apply(nums);

        assertArrayEquals(original, nums);
    }

    @Test
    void bothApproachesProduceTheSameSubsetsRegardlessOfOrdering() {
        int[] nums = {3, -1, 2, 0};

        Set<List<Integer>> backtrackingResult = canonicalize(solution.subsets(nums.clone()));
        Set<List<Integer>> bitResult = canonicalize(solution.subsetsBit(nums.clone()));

        assertEquals(backtrackingResult, bitResult);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void everyReturnedSubsetIsAnIndependentCopy(
            String name, Function<int[], List<List<Integer>>> implementation) {
        List<List<Integer>> result = implementation.apply(new int[]{1, 2, 3});

        assertNotSame(result.get(0), result.get(1));
        result.get(1).add(99);

        assertFalse(result.get(0).contains(99));
        assertFalse(result.get(2).contains(99));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementations")
    void repeatedCallsDoNotShareState(String name, Function<int[], List<List<Integer>>> implementation) {
        List<List<Integer>> first = implementation.apply(new int[]{1, 2, 3});
        List<List<Integer>> second = implementation.apply(new int[]{1, 2, 3});

        assertEquals(canonicalize(first), canonicalize(second));
    }

    @Test
    void bitApproachRejectsInputThatCannotBeRepresentedByAnIntMask() {
        assertThrows(IllegalArgumentException.class, () -> solution.subsetsBit(new int[Integer.SIZE - 1]));
    }

    private void assertPowerSet(Set<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, canonicalize(actual));
    }

    private Set<List<Integer>> canonicalize(List<List<Integer>> subsets) {
        return subsets.stream()
                .map(subset -> {
                    List<Integer> sorted = new ArrayList<>(subset);
                    sorted.sort(Integer::compareTo);
                    return sorted;
                })
                .collect(Collectors.toSet());
    }

    private static List<Function<int[], List<List<Integer>>>> implementationFunctions() {
        Subsets_78 solution = new Subsets_78();
        return List.of(solution::subsets, solution::subsetsBit);
    }

    /** Independent reference model: one mask selects each input position at most once. */
    private static Set<List<Integer>> bitmaskOracle(int[] nums) {
        Set<List<Integer>> expected = new HashSet<>();
        for (int mask = 0; mask < (1 << nums.length); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int index = 0; index < nums.length; index++) {
                if ((mask & (1 << index)) != 0) {
                    subset.add(nums[index]);
                }
            }
            subset.sort(Integer::compareTo);
            expected.add(subset);
        }
        return expected;
    }
}
