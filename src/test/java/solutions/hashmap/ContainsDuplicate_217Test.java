package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for {@link ContainsDuplicate_217} using valid LeetCode 217 inputs.
 */
public class ContainsDuplicate_217Test {

    private final ContainsDuplicate_217 solution = new ContainsDuplicate_217();

    @ParameterizedTest(name = "{0}")
    @MethodSource("duplicateCases")
    void identifiesArraysContainingDuplicates(String description, int[] nums) {
        assertTrue(solution.containsDuplicate(nums), description);
    }

    private static Stream<Arguments> duplicateCases() {
        return Stream.of(
                Arguments.of("duplicate at the beginning", new int[]{7, 7, 1, 2, 3}),
                Arguments.of("duplicate at the end", new int[]{1, 2, 3, 4, 5, 1}),
                Arguments.of("non-adjacent duplicate", new int[]{4, 1, 9, 3, 1, 8}),
                Arguments.of("all values are identical", new int[]{5, 5, 5, 5, 5}),
                Arguments.of("negative duplicate", new int[]{-1, -2, -3, -1}),
                Arguments.of("duplicate zero", new int[]{0, 1, 2, 0}),
                Arguments.of(
                        "duplicates at the integer constraint boundaries",
                        new int[]{-1_000_000_000, 0, 1_000_000_000, -1_000_000_000}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("uniqueCases")
    void identifiesArraysWithoutDuplicates(String description, int[] nums) {
        assertFalse(solution.containsDuplicate(nums), description);
    }

    private static Stream<Arguments> uniqueCases() {
        return Stream.of(
                Arguments.of("single value", new int[]{1}),
                Arguments.of("positive values", new int[]{1, 2, 3, 4}),
                Arguments.of("negative values", new int[]{-1, -2, -3}),
                Arguments.of("mixed positive and negative values", new int[]{-1, 1, -2, 2}),
                Arguments.of("unordered values", new int[]{3, 1, 4, 2}),
                Arguments.of(
                        "distinct values at the integer constraint boundaries",
                        new int[]{-1_000_000_000, 0, 1_000_000_000}));
    }

    @Test
    void handlesMaximumInputSizeAndLateDuplicate() {
        int[] nums = IntStream.range(0, 100_000).toArray();

        assertFalse(solution.containsDuplicate(nums));

        // Changing only the last value checks that the full input is scanned.
        nums[nums.length - 1] = 0;
        assertTrue(solution.containsDuplicate(nums));
    }
}
