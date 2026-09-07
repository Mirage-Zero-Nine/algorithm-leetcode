package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link CheckSubarraySum_523}.
 */
public class CheckSubarraySum_523Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void bothMethodsMatchDirectSubarrayEnumeration(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int trial = 0; trial < 100; trial++) {
            int[] nums = random.ints(1 + random.nextInt(12), 0, 21).toArray();
            int k = random.nextInt(12);
            boolean expected = false;
            for (int left = 0; left < nums.length; left++) {
                int sum = 0;
                for (int right = left; right < nums.length; right++) {
                    sum += nums[right];
                    if (right > left && (k == 0 ? sum == 0 : sum % k == 0)) expected = true;
                }
            }
            assertEquals(expected, solver.checkSubarraySum(nums.clone(), k), java.util.Arrays.toString(nums) + ", k=" + k);
            assertEquals(expected, solver.checkSubarraySumByTraverseArray(nums.clone(), k));
        }
    }

    @Test
    void largeArrayDistinguishesWholeArrayFromUnreachableMultiple() {
        int[] nums = new int[100_000];
        java.util.Arrays.fill(nums, 1);
        assertFalse(solver.checkSubarraySum(nums, 100_001));
        assertTrue(solver.checkSubarraySum(nums, 100_000));
    }


    private final CheckSubarraySum_523 solver = new CheckSubarraySum_523();

    // === checkSubarraySum tests ===

    @Test
    public void testClassicMultipleOfK() {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testClassicMultipleOfK2() {
        int[] nums = {23, 2, 6, 4, 7};
        int k = 6;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testNoValidSubarray() {
        int[] nums = {23, 2, 6, 4, 7};
        int k = 13;
        assertFalse(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testTwoZerosMultipleOfK() {
        int[] nums = {0, 0};
        int k = 0;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testSingleElement() {
        int[] nums = {1};
        int k = 1;
        assertFalse(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testTwoElementsSumToK() {
        int[] nums = {1, 2};
        int k = 3;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testTwoElementsNoMatch() {
        int[] nums = {1, 2};
        int k = 4;
        assertFalse(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testLargeArrayWithValidSubarray() {
        int[] nums = {5, 0, 0, 0};
        int k = 3;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testAllZeros() {
        int[] nums = {0, 0, 0};
        int k = 1;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testSubarrayAtEnd() {
        int[] nums = {1, 0, 0};
        int k = 1;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    @Test
    public void testNoValidSubarrayFixed() {
        // [1,2,3] sum=6, not divisible by 5; [2,3]=5 divisible by 5 -> true
        int[] nums = {1, 2, 3};
        int k = 5;
        assertTrue(solver.checkSubarraySum(nums, k));
    }

    // === checkSubarraySumByTraverseArray tests ===

    @Test
    public void testTraverseClassicMultipleOfK() {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        assertTrue(solver.checkSubarraySumByTraverseArray(nums, k));
    }

    @Test
    public void testTraverseTwoZeros() {
        // Double zero at start triggers early return true
        int[] nums = {0, 0};
        int k = 0;
        assertTrue(solver.checkSubarraySumByTraverseArray(nums, k));
    }

    @Test
    public void testTraverseShortArray() {
        int[] nums = {1};
        int k = 1;
        assertFalse(solver.checkSubarraySumByTraverseArray(nums, k));
    }

    @Test
    public void testTraverseWithDoubleZero() {
        int[] nums = {1, 0, 0};
        int k = 1;
        assertTrue(solver.checkSubarraySumByTraverseArray(nums, k));
    }
}
