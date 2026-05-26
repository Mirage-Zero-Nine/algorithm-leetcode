package solutions.twopointers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
    void testNegativeValues() {
        int[] nums = {-1, 0, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{-1, 1, 0}, nums);
    }

    @Test
    void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = 1000 - i; // descending
        solution.nextPermutation(nums);
        // descending => wraps to ascending
        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) expected[i] = i + 1;
        assertArrayEquals(expected, nums);
    }
}
