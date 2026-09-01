package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Permute_46Test {
    private final Permute_46 solution = new Permute_46();

    @Test
    void returnsEveryPermutationForThreeDistinctValues() {
        List<List<Integer>> actual = solution.permute(new int[]{1, 2, 3});

        Set<List<Integer>> expected = Set.of(
                List.of(1, 2, 3),
                List.of(1, 3, 2),
                List.of(2, 1, 3),
                List.of(2, 3, 1),
                List.of(3, 1, 2),
                List.of(3, 2, 1));

        assertPermutationSet(expected, actual);
    }

    @Test
    void exhaustivelyValidatesDistinctInputsFromAConstrainedDomain() {
        // Every generated input satisfies LeetCode 46's constraints. Testing
        // all ordered, distinct inputs from this six-value domain gives full
        // coverage for lengths 1 through 6 without making the unit test huge.
        int[] domain = {-10, -9, -8, -7, -6, -5};

        for (int length = 1; length <= domain.length; length++) {
            assertAllInputs(domain, new int[length], new boolean[domain.length], 0);
        }
    }

    @Test
    void exhaustivelyValidatesAllShortInputsAcrossTheAllowedValueRange() {
        // Exhausting the full value range is practical for lengths 1 through
        // 3: there are only 21P1 + 21P2 + 21P3 = 8,421 inputs. Longer lengths
        // are covered exhaustively by the smaller domain above.
        int[] domain = IntStream.rangeClosed(-10, 10).toArray();

        for (int length = 1; length <= 3; length++) {
            assertAllInputs(domain, new int[length], new boolean[domain.length], 0);
        }
    }

    @Test
    void validatesMaximumLengthWithBoundaryValues() {
        // LeetCode 46 allows at most six distinct values and values from -10
        // through 10. Validate the complete 6! result set at both boundaries.
        assertPermutationResult(new int[]{-10, -1, 0, 1, 9, 10});
    }

    @Test
    void preservesTheInputAndEachResultContainsExactlyTheInputValues() {
        int[] input = {10, -10, 0, 7};
        int[] original = input.clone();

        List<List<Integer>> actual = solution.permute(input);

        assertArrayEquals(original, input, "permute must not modify the input array");
        assertEquals(factorial(input.length), actual.size());
        assertEquals(actual.size(), new HashSet<>(actual).size(),
                "all generated permutations must be unique");

        List<Integer> expectedElements = Arrays.stream(original).boxed().sorted().toList();
        for (List<Integer> permutation : actual) {
            assertEquals(input.length, permutation.size(),
                    "every result must have the same length as the input");
            assertEquals(expectedElements, permutation.stream().sorted().toList(),
                    "every result must contain exactly the input values");
        }
    }

    @Test
    void handlesSingleValue() {
        assertEquals(List.of(List.of(42)), solution.permute(new int[]{42}));
    }

    @Test
    void returnsEmptyListForNullAndEmptyInput() {
        assertTrue(solution.permute(null).isEmpty());
        assertTrue(solution.permute(new int[]{}).isEmpty());
    }

    private void assertAllInputs(int[] domain, int[] input, boolean[] selected, int position) {
        if (position == input.length) {
            assertPermutationResult(input);
            return;
        }

        for (int i = 0; i < domain.length; i++) {
            if (!selected[i]) {
                selected[i] = true;
                input[position] = domain[i];
                assertAllInputs(domain, input, selected, position + 1);
                selected[i] = false;
            }
        }
    }

    private void assertPermutationResult(int[] input) {
        int[] original = input.clone();
        List<List<Integer>> actual = solution.permute(input);
        Set<List<Integer>> expected = expectedPermutations(original);
        String inputDescription = Arrays.toString(original);

        assertArrayEquals(original, input,
                "permute must not modify the input array: " + inputDescription);
        assertEquals(expected, new HashSet<>(actual),
                "the complete permutation set must be returned for " + inputDescription);
        assertEquals(expected.size(), actual.size(),
                "the result must not contain duplicate permutations for " + inputDescription);
    }

    /**
     * Independent oracle: starts with sorted values and enumerates each next
     * lexicographical permutation. It does not reuse the solution's algorithm.
     */
    private Set<List<Integer>> expectedPermutations(int[] input) {
        List<Integer> permutation = new ArrayList<>();
        for (int value : input) {
            permutation.add(value);
        }
        Collections.sort(permutation);

        Set<List<Integer>> expected = new HashSet<>();
        do {
            expected.add(new ArrayList<>(permutation));
        } while (nextPermutation(permutation));
        return expected;
    }

    private boolean nextPermutation(List<Integer> permutation) {
        int i = permutation.size() - 2;
        while (i >= 0 && permutation.get(i) >= permutation.get(i + 1)) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = permutation.size() - 1;
        while (permutation.get(j) <= permutation.get(i)) {
            j--;
        }
        Collections.swap(permutation, i, j);
        Collections.reverse(permutation.subList(i + 1, permutation.size()));
        return true;
    }

    private void assertPermutationSet(Set<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(expected.size(), actual.size(),
                "the result must contain exactly n! permutations");
        assertEquals(expected, new HashSet<>(actual),
                "the result must contain the expected permutation values");
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
