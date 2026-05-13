package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxSum_1537Test {

    private final MaxSum_1537 test = new MaxSum_1537();

    @Test
    public void testHappyCases() {
        assertEquals(30, test.maxSum(new int[]{2, 4, 5, 8, 10}, new int[]{4, 6, 8, 9}));
        assertEquals(109, test.maxSum(new int[]{1, 3, 5, 7, 9}, new int[]{3, 5, 100}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.maxSum(new int[]{1}, new int[]{2}));
        assertEquals(3, test.maxSum(new int[]{1, 2}, new int[]{3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(81, test.maxSum(new int[]{1, 4, 5, 8, 9, 11, 19, 20}, new int[]{2, 3, 4, 11, 12}));
    }

    @Test
    public void testNoCommonElements() {
        assertEquals(40, test.maxSum(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10}));
    }

    @Test
    public void testAllCommon() {
        assertEquals(15, test.maxSum(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testSingleElementEach() {
        assertEquals(5, test.maxSum(new int[]{5}, new int[]{5}));
    }

    @Test
    public void testSingleElementNoCommon() {
        assertEquals(10, test.maxSum(new int[]{10}, new int[]{5}));
    }

    @Test
    public void testCommonAtStart() {
        assertEquals(10, test.maxSum(new int[]{1, 2, 3}, new int[]{1, 4, 5}));
    }

    @Test
    public void testCommonAtEnd() {
        assertEquals(12, test.maxSum(new int[]{1, 2, 5}, new int[]{3, 4, 5}));
    }

    @Test
    public void testGiantCase() {
        int n = 100000;
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] = i * 2 + 1;
            nums2[i] = i * 2 + 2;
        }
        // No common elements, result is max of two sums mod 10^9+7
        // sum of nums2 = sum of (2,4,6,...,200000) = 2*(1+2+...+100000) = 2*100000*100001/2 = 10000100000
        // 10000100000 % (10^9+7) = 10000100000 - 9*(10^9+7) = 10000100000 - 9000000063 = 1000099937
        int result = test.maxSum(nums1, nums2);
        assertEquals((int)(10000100000L % 1000000007L), result);
    }

    @Test
    public void testMultipleCommonPoints() {
        assertEquals(30, test.maxSum(new int[]{2, 4, 5, 8, 10}, new int[]{4, 6, 8, 9}));
    }

    @Test
    public void testLargeValues() {
        assertEquals(200000004, test.maxSum(new int[]{100000001, 100000002}, new int[]{100000001, 100000003}));
    }
}
