package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MergeSortedArray_88Test {

    private final MergeSortedArray_88 test = new MergeSortedArray_88();

    @Test
    public void testHappyCases() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        test.mergeSortedArray(nums1, 3, new int[]{2, 5, 6}, 3);
        assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        int[] nums1 = {1};
        test.mergeSortedArray(nums1, 1, new int[]{}, 0);
        assertArrayEquals(new int[]{1}, nums1);
        int[] nums2 = {0};
        test.mergeSortedArray(nums2, 0, new int[]{1}, 1);
        assertArrayEquals(new int[]{1}, nums2);
    }

    @Test
    public void testLargeCase() {
        int[] nums1 = {1, 3, 5, 7, 0, 0, 0, 0};
        test.mergeSortedArray(nums1, 4, new int[]{2, 4, 6, 8}, 4);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, nums1);
    }

    @Test
    public void testNums2AllSmaller() {
        int[] nums1 = {4, 5, 6, 0, 0, 0};
        test.mergeSortedArray(nums1, 3, new int[]{1, 2, 3}, 3);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, nums1);
    }

    @Test
    public void testNums2AllLarger() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        test.mergeSortedArray(nums1, 3, new int[]{7, 8, 9}, 3);
        assertArrayEquals(new int[]{1, 2, 3, 7, 8, 9}, nums1);
    }

    @Test
    public void testBothEmpty() {
        int[] nums1 = {};
        test.mergeSortedArray(nums1, 0, new int[]{}, 0);
        assertArrayEquals(new int[]{}, nums1);
    }

    @Test
    public void testNums1EmptyNums2Single() {
        int[] nums1 = {0};
        test.mergeSortedArray(nums1, 0, new int[]{5}, 1);
        assertArrayEquals(new int[]{5}, nums1);
    }

    @Test
    public void testDuplicateElements() {
        int[] nums1 = {1, 2, 2, 0, 0, 0};
        test.mergeSortedArray(nums1, 3, new int[]{2, 2, 3}, 3);
        assertArrayEquals(new int[]{1, 2, 2, 2, 2, 3}, nums1);
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums1 = {-3, -1, 0, 0, 0};
        test.mergeSortedArray(nums1, 2, new int[]{-2, 1, 2}, 3);
        assertArrayEquals(new int[]{-3, -2, -1, 1, 2}, nums1);
    }

    @Test
    public void testGiantCase() {
        int n = 500;
        int[] nums1 = new int[1000];
        int[] nums2 = new int[500];
        for (int i = 0; i < n; i++) { nums1[i] = i * 2; nums2[i] = i * 2 + 1; }
        test.mergeSortedArray(nums1, n, nums2, n);
        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) expected[i] = i;
        assertArrayEquals(expected, nums1);
    }
}
