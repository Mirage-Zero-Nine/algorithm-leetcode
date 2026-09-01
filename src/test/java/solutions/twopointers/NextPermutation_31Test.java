package solutions.twopointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NextPermutation_31Test {
    private final NextPermutation_31 solution = new NextPermutation_31();

    @Test
    void testBasic() {
        int[] nums = {1, 2, 3};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 3, 2}, nums);
    }

    @Test
    void testDescending() {
        int[] nums = {3, 2, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void testSingleElement() {
        int[] nums = {1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void testTwoElements() {
        int[] nums = {1, 2};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1}, nums);
    }

    @Test
    void testComplex() {
        int[] nums = {1, 3, 2};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1, 3}, nums);
    }

    @Test
    void testDuplicates() {
        int[] nums = {1, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 1}, nums);
    }

    @Test
    void testAllSame() {
        int[] nums = {2, 2, 2};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 2, 2}, nums);
    }

    @Test
    void testLongerArray() {
        int[] nums = {1, 2, 3, 4};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 4, 3}, nums);
    }

    @Test
    void testMiddleSwap() {
        int[] nums = {2, 3, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{3, 1, 2}, nums);
    }

    @Test
    void testMaximumLengthCase() {
        int[] nums = new int[100];
        for (int i = 0; i < nums.length; i++) nums[i] = nums.length - i;
        solution.nextPermutation(nums);
        // descending => wraps to ascending
        int[] expected = new int[100];
        for (int i = 0; i < expected.length; i++) expected[i] = i + 1;
        assertArrayEquals(expected, nums);
    }

    @Test
    void testExhaustiveShortArraysWithDuplicates() {
        // 3^1 + ... + 3^8 = 9,840 deterministic cases, including duplicates.
        for (int length = 1; length <= 8; length++) {
            assertAllArrays(new int[length], 0);
        }
    }

    private void assertAllArrays(int[] nums, int index) {
        if (index == nums.length) {
            int[] original = nums.clone();
            int[] expected = nextPermutationByEnumeration(original);
            solution.nextPermutation(nums);
            assertArrayEquals(expected, nums,
                    "input=" + Arrays.toString(original));
            return;
        }

        for (int value = 0; value <= 2; value++) {
            nums[index] = value;
            assertAllArrays(nums, index + 1);
        }
    }

    /** Independent oracle: enumerate and sort all distinct permutations. */
    private int[] nextPermutationByEnumeration(int[] input) {
        List<int[]> permutations = new ArrayList<>();
        int[] sortedInput = input.clone();
        Arrays.sort(sortedInput);
        collectPermutations(sortedInput, new boolean[input.length], new int[input.length], 0, permutations);
        // Compare numerically so the oracle follows integer lexicographic order.
        permutations.sort(NextPermutation_31Test::compareLexicographically);
        for (int i = 0; i < permutations.size(); i++) {
            if (Arrays.equals(permutations.get(i), input)) {
                return permutations.get((i + 1) % permutations.size()).clone();
            }
        }
        throw new AssertionError("Permutation was not enumerated: " + Arrays.toString(input));
    }

    private void collectPermutations(int[] values, boolean[] used, int[] permutation,
                                     int index, List<int[]> permutations) {
        if (index == permutation.length) {
            permutations.add(permutation.clone());
            return;
        }

        for (int i = 0; i < values.length; i++) {
            if (used[i] || (i > 0 && values[i] == values[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            permutation[index] = values[i];
            collectPermutations(values, used, permutation, index + 1, permutations);
            used[i] = false;
        }
    }

    private static int compareLexicographically(int[] first, int[] second) {
        for (int i = 0; i < first.length; i++) {
            int comparison = Integer.compare(first[i], second[i]);
            if (comparison != 0) return comparison;
        }
        return 0;
    }

}
