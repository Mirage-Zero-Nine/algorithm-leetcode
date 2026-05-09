package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MergeSortedArray88Test {

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
}
