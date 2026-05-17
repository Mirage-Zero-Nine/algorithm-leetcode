package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

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

    @Test
    public void testOneArrayEmpty() {
        // When nums2 is empty, result is sum of nums1 (no path switching possible)
        assertEquals(15, test.maxSum(new int[]{1, 2, 3, 4, 5}, new int[]{}));
        // When nums1 is empty, result is sum of nums2
        assertEquals(24, test.maxSum(new int[]{}, new int[]{5, 7, 12}));
    }

    @Test
    public void testBothArraysEmpty() {
        assertEquals(0, test.maxSum(new int[]{}, new int[]{}));
    }

    @Test
    public void testSingleCommonValueAtStart() {
        // Common at index 0: both start with 1, then diverge
        // Path through nums1: 1+2+3=6, Path through nums2: 1+8+9=18
        // At common point 1: max(0,0)+1=1, then sum1=2+3=5, sum2=8+9=17, result=1+max(5,17)=18
        assertEquals(18, test.maxSum(new int[]{1, 2, 3}, new int[]{1, 8, 9}));
    }

    @Test
    public void testSingleCommonValueAtEnd() {
        // Common only at end (10): sum1 before=1+2=3, sum2 before=8+9=17, result=max(3,17)+10=27
        assertEquals(27, test.maxSum(new int[]{1, 2, 10}, new int[]{8, 9, 10}));
    }

    @Test
    public void testLargeArraysSeed42() {
        // Generate large sorted distinct arrays with seed 42L
        Random rand = new Random(42L);
        int n = 1500;
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        nums1[0] = rand.nextInt(5) + 1;
        nums2[0] = rand.nextInt(5) + 1;
        for (int i = 1; i < n; i++) {
            nums1[i] = nums1[i - 1] + rand.nextInt(10) + 1;
            nums2[i] = nums2[i - 1] + rand.nextInt(10) + 1;
        }
        int result = test.maxSum(nums1, nums2);
        long MOD = 1_000_000_007L;
        // Property: result must be in [0, MOD)
        assertTrue(result >= 0 && result < MOD, "Result should be < 10^9+7, got: " + result);
    }

    @Test
    public void testLargeSumWithCommonValues() {
        // Large arrays with common values where sum exceeds 10^9+7
        int n = 50000;
        int[] nums1 = new int[n];
        int[] nums2 = new int[n];
        // nums1: 1,2,3,...,50000 and nums2: 1,2,3,...,50000 (all common)
        for (int i = 0; i < n; i++) {
            nums1[i] = i + 1;
            nums2[i] = i + 1;
        }
        int result = test.maxSum(nums1, nums2);
        long MOD = 1_000_000_007L;
        // All common: sum = n*(n+1)/2 = 1250025000
        long expectedSum = (long) n * (n + 1) / 2;
        assertEquals((int) (expectedSum % MOD), result);
        assertTrue(result >= 0 && result < MOD);
    }

    @Test
    public void testPropertyResultLessThanMod() {
        long MOD = 1_000_000_007L;
        // Test multiple inputs and verify result < MOD
        int[][] cases = {
            {1, 2, 3, 4, 5},
            {100000000, 200000000, 300000000},
            {999999999, 1000000000}
        };
        int[][] cases2 = {
            {6, 7, 8, 9, 10},
            {100000000, 400000000, 500000000},
            {999999998, 1000000001}
        };
        for (int i = 0; i < cases.length; i++) {
            int result = test.maxSum(cases[i], cases2[i]);
            assertTrue(result >= 0 && result < MOD,
                "Result should be in [0, MOD), got: " + result);
        }
    }

    @Test
    public void testPropertyResultGeMaxSumMod() {
        long MOD = 1_000_000_007L;
        int[] nums1 = {1, 3, 5, 7, 9};
        int[] nums2 = {2, 4, 6, 8, 10};
        int result = test.maxSum(nums1, nums2);
        // No common elements: result = max(sum(a), sum(b)) % MOD
        long sum1 = 25, sum2 = 30;
        long maxSum = Math.max(sum1, sum2) % MOD;
        assertTrue(result >= maxSum,
            "Result should be >= max(sum(a),sum(b)) %% MOD. Got " + result + " < " + maxSum);
    }

    @Test
    public void testMultipleCommonValuesComplex() {
        // nums1: [1, 3, 5, 7, 9, 11], nums2: [2, 3, 7, 10, 11]
        // Common: 3, 7, 11
        // Segment before 3: nums1=1, nums2=2 -> max=2
        // At 3: +3 -> 5
        // Segment between 3 and 7: nums1=5, nums2=empty -> max=5
        // At 7: +7 -> 17 (5+5+7)
        // Segment between 7 and 11: nums1=9, nums2=10 -> max=10
        // At 11: +11 -> 38 (17+10+11)
        // After 11: nums1=empty, nums2=empty -> max=0
        // Total: 38
        assertEquals(38, test.maxSum(new int[]{1, 3, 5, 7, 9, 11}, new int[]{2, 3, 7, 10, 11}));
    }
}
