package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for {@link TwoSum_1} using only the input contract from LeetCode 1:
 * arrays contain between 2 and 10,000 integers, values and targets are within
 * [-1,000,000,000, 1,000,000,000], and every case has exactly one solution.
 *
 * @see <a href="https://leetcode.com/problems/two-sum/">LeetCode 1: Two Sum</a>
 */
class TwoSum_1Test {

    private final TwoSum_1 twoSum = new TwoSum_1();

    @ParameterizedTest(name = "case {index}: target={1}, expected indices={2}")
    @MethodSource("validCases")
    void findsTheUniquePair(int[] nums, int target, int[] expectedIndices) {
        assertPairEquals(expectedIndices, twoSum.twoSum(nums, target));
    }

    private static Stream<Arguments> validCases() {
        return Stream.of(
                // Official examples.
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1}),

                // Pair positions and input ordering.
                Arguments.of(new int[]{5, 75, 25, 10, 100}, 125, new int[]{2, 4}),
                Arguments.of(new int[]{8, 7, 2, 15}, 9, new int[]{1, 2}),
                Arguments.of(new int[]{10, 20, 30, 40}, 70, new int[]{2, 3}),
                Arguments.of(new int[]{1, 5, 6, 7, 9}, 10, new int[]{0, 4}),
                Arguments.of(new int[]{4, 9, 1, 6, 12, 20}, 15, new int[]{1, 3}),
                Arguments.of(new int[]{12, -4, 8, 21, 3}, 17, new int[]{1, 3}),

                // Duplicate values, including a pair made from equal values.
                Arguments.of(new int[]{1, 1, 3, 5}, 2, new int[]{0, 1}),
                Arguments.of(new int[]{6, 1, 6, 4}, 12, new int[]{0, 2}),
                Arguments.of(new int[]{7, 7, 1, 3, 7, 10}, 4, new int[]{2, 3}),
                Arguments.of(new int[]{0, 4, 3, 0}, 0, new int[]{0, 3}),

                // Negative numbers and zero.
                Arguments.of(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2}),
                Arguments.of(new int[]{-5, -3, 4}, -8, new int[]{0, 1}),
                Arguments.of(new int[]{-10, -5, -3, 0, 7, 12}, 9, new int[]{2, 5}),
                Arguments.of(new int[]{-8, 1, -2, 5}, -7, new int[]{0, 1}),
                Arguments.of(new int[]{-10, 8, 15, -3}, 12, new int[]{2, 3}),
                Arguments.of(new int[]{0, 0}, 0, new int[]{0, 1}),

                // Boundary values allowed by the problem constraints.
                Arguments.of(new int[]{-1_000_000_000, 1_000_000_000}, 0, new int[]{0, 1}),
                Arguments.of(new int[]{-1_000_000_000, 999_999_999, 123}, -1, new int[]{0, 1}),
                Arguments.of(new int[]{1_000_000_000, -999_999_999, -1}, 1, new int[]{0, 1}),
                Arguments.of(new int[]{999_999_999, 1}, 1_000_000_000, new int[]{0, 1}),
                Arguments.of(new int[]{-999_999_999, -1}, -1_000_000_000, new int[]{0, 1}),
                Arguments.of(new int[]{-500_000_000, -500_000_000}, -1_000_000_000, new int[]{0, 1}),
                Arguments.of(new int[]{500_000_000, 500_000_000}, 1_000_000_000, new int[]{0, 1})
        );
    }

    @Test
    void handlesTheMaximumAllowedArrayLength() {
        int[] nums = new int[10_000];
        for (int i = 0; i < nums.length; i++) {
            // All filler values are negative, so they cannot form a zero-sum pair.
            nums[i] = -10_000 - i;
        }

        int firstIndex = 123;
        int secondIndex = 9_876;
        nums[firstIndex] = 500_000_000;
        nums[secondIndex] = -500_000_000;

        assertPairEquals(new int[]{firstIndex, secondIndex}, twoSum.twoSum(nums, 0));
    }

    @Test
    void handlesPairsAtDifferentPositions() {
        int[][] positions = {
                {0, 1},
                {0, 127},
                {63, 64},
                {126, 127}
        };

        for (int[] position : positions) {
            int[] nums = new int[128];
            for (int i = 0; i < nums.length; i++) {
                // Filler values are all negative and distinct from the pair.
                nums[i] = -1_000 - i;
            }
            nums[position[0]] = 600_000_000;
            nums[position[1]] = -600_000_000;

            assertPairEquals(position, twoSum.twoSum(nums, 0));
        }
    }

    private static void assertPairEquals(int[] expected, int[] actual) {
        assertNotNull(actual, "LeetCode guarantees that a valid pair exists");
        assertEquals(2, actual.length, "the result must contain exactly two indices");
        assertArrayEquals(
                Arrays.stream(expected).sorted().toArray(),
                Arrays.stream(actual).sorted().toArray()
        );
    }
}
