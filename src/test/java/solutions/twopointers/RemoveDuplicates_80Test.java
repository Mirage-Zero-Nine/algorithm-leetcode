package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveDuplicates_80Test {

    private final RemoveDuplicates_80 test = new RemoveDuplicates_80();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
        assertEquals(7, test.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.removeDuplicates(new int[]{1}));
        assertEquals(2, test.removeDuplicates(new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.removeDuplicates(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
    }

    @Test
    public void testNoDuplicates() {
        assertEquals(5, test.removeDuplicates(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testAllSame() {
        assertEquals(2, test.removeDuplicates(new int[]{4, 4, 4, 4, 4}));
    }

    @Test
    public void testExactlyTwoDuplicates() {
        assertEquals(4, test.removeDuplicates(new int[]{1, 1, 2, 2}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.removeDuplicates(new int[]{}));
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums = new int[]{-3, -3, -3, -1, -1, 0, 0, 0};
        assertEquals(6, test.removeDuplicates(nums));
        assertEquals(-3, nums[0]);
        assertEquals(-3, nums[1]);
        assertEquals(-1, nums[2]);
        assertEquals(-1, nums[3]);
        assertEquals(0, nums[4]);
        assertEquals(0, nums[5]);
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) {
            nums[i] = i / 5;
        }
        // Each value appears 5 times, kept at most 2 => 2000 distinct values * 2 = 4000
        assertEquals(4000, test.removeDuplicates(nums));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.removeDuplicates(new int[]{1, 2}));
    }

    @Test
    public void testThreeSameElements() {
        assertEquals(2, test.removeDuplicates(new int[]{1, 1, 1}));
    }

    @Test
    public void testKeepsTheSortedPrefixInPlace() {
        int[] nums = {1, 1, 1, 2, 2, 2, 3, 4, 4, 4};
        int length = test.removeDuplicates(nums);
        assertEquals(7, length);
        assertEquals(1, nums[0]);
        assertEquals(1, nums[1]);
        assertEquals(2, nums[2]);
        assertEquals(2, nums[3]);
        assertEquals(3, nums[4]);
        assertEquals(4, nums[5]);
        assertEquals(4, nums[6]);
    }

    @Test
    public void testNegativeValuesAtTheLowerContractBoundary() {
        int[] nums = {-10000, -10000, -10000, -9999, -9999};
        int length = test.removeDuplicates(nums);
        assertEquals(4, length);
        assertEquals(-10000, nums[0]);
        assertEquals(-10000, nums[1]);
        assertEquals(-9999, nums[2]);
        assertEquals(-9999, nums[3]);
    }

    @Test
    public void testValuesAtTheUpperContractBoundary() {
        int[] nums = {9998, 9998, 9998, 9999, 9999, 10000, 10000, 10000};
        int length = test.removeDuplicates(nums);
        assertEquals(6, length);
        assertEquals(9998, nums[0]);
        assertEquals(9998, nums[1]);
        assertEquals(9999, nums[2]);
        assertEquals(9999, nums[3]);
        assertEquals(10000, nums[4]);
        assertEquals(10000, nums[5]);
    }

    @Test
    public void testAlreadyValidDuplicateMultiplicityIsPreserved() {
        int[] nums = {-2, -2, -1, 0, 0, 1, 1, 2};
        int[] original = nums.clone();
        int length = test.removeDuplicates(nums);
        assertEquals(original.length, length);
        org.junit.jupiter.api.Assertions.assertArrayEquals(original, nums);
    }

    @Test
    public void testFourCopiesKeepOnlyTheFirstTwo() {
        int[] nums = {5, 5, 5, 5, 6, 6, 6, 6};
        int length = test.removeDuplicates(nums);
        assertEquals(4, length);
        org.junit.jupiter.api.Assertions.assertArrayEquals(new int[]{5, 5, 6, 6},
                java.util.Arrays.copyOf(nums, length));
    }

    @Test
    public void testManyValuesWithExactlyTwoCopiesAreAllRetained() {
        int[] nums = {-3, -3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3};
        int length = test.removeDuplicates(nums);
        assertEquals(nums.length, length);
        org.junit.jupiter.api.Assertions.assertArrayEquals(
                new int[]{-3, -3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3}, nums);
    }

    @Test
    public void testSingleMinimumAndMaximumValues() {
        assertEquals(1, test.removeDuplicates(new int[]{-10000}));
        assertEquals(1, test.removeDuplicates(new int[]{10000}));
    }

    @Test
    public void testMaximumLegalInputSize() {
        int[] nums = new int[30000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -10000 + i / 3;
        }
        assertEquals(20000, test.removeDuplicates(nums));
        assertEquals(-10000, nums[0]);
        assertEquals(-10000, nums[1]);
        assertEquals(-1, nums[19998]);
        assertEquals(-1, nums[19999]);
    }

    @Test
    public void testRepeatedInvocationWithFreshArrays() {
        assertEquals(4, test.removeDuplicates(new int[]{-1, -1, -1, 0, 0}));
        assertEquals(5, test.removeDuplicates(new int[]{1, 1, 2, 2, 3}));
    }

    @Test
    public void testLongRunsAndChangingRunLengths() {
        int[] nums = {0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3};
        int length = test.removeDuplicates(nums);
        assertEquals(8, length);
        org.junit.jupiter.api.Assertions.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2, 3, 3},
                java.util.Arrays.copyOf(nums, length));
    }
}
