package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link SubarraySum_560}.
 */
public class SubarraySum_560Test {

    private final SubarraySum_560 solver = new SubarraySum_560();

    @Test
    public void testLeetCodeExample1() {
        // nums = [1,1,1], k = 2
        // subarrays: [1,1] at indices 0-1, [1,1] at indices 1-2 → 2
        int[] nums = {1, 1, 1};
        assertEquals(2, solver.subarraySum(nums, 2));
    }

    @Test
    public void testLeetCodeExample2() {
        // nums = [1,2,3], k = 3
        // subarrays: [1,2], [3] → 2
        int[] nums = {1, 2, 3};
        assertEquals(2, solver.subarraySum(nums, 3));
    }

    @Test
    public void testSingleElementMatch() {
        int[] nums = {1};
        assertEquals(1, solver.subarraySum(nums, 1));
    }

    @Test
    public void testSingleElementNoMatch() {
        int[] nums = {1};
        assertEquals(0, solver.subarraySum(nums, 2));
    }

    @Test
    public void testWithNegativeNumbers() {
        // nums = [1,-1,-1,1], k = 0
        // subarrays with sum 0: [-1,1] at 1-2, [1,-1,-1,1] at 0-3, [-1,-1,1,1] doesn't work...
        // prefix sums: 1, 0, -1, 0
        // i=0: prefix=1, need 1-0=1, count=0, m={0:1, 1:1}
        // i=1: prefix=0, need 0-0=0, count+=1=1, m={0:2, 1:1}
        // i=2: prefix=-1, need -1-0=-1, count+=0=1, m={0:2, 1:1, -1:1}
        // i=3: prefix=0, need 0-0=0, count+=2=3, m={0:3, 1:1, -1:1}
        int[] nums = {1, -1, -1, 1};
        assertEquals(3, solver.subarraySum(nums, 0));
    }

    @Test
    public void testAllZeros() {
        // nums = [0,0,0], k = 0
        // all subarrays sum to 0: [0],[0],[0],[0,0],[0,0],[0,0,0] → 6
        int[] nums = {0, 0, 0};
        assertEquals(6, solver.subarraySum(nums, 0));
    }

    @Test
    public void testNoMatch() {
        int[] nums = {1, 2, 3};
        assertEquals(0, solver.subarraySum(nums, 7));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        assertEquals(0, solver.subarraySum(nums, 0));
    }

    @Test
    public void testLargeArray() {
        // nums = [4,1,3,7], k = 4
        // prefix sums: 4, 5, 8, 15
        // i=0: prefix=4, need 4-4=0, count+=1=1, m={0:1, 4:1}
        // i=1: prefix=5, need 5-4=1, count+=0=1, m={0:1, 4:1, 5:1}
        // i=2: prefix=8, need 8-4=4, count+=1=2, m={0:1, 4:1, 5:1, 8:1}
        // i=3: prefix=15, need 15-4=11, count+=0=2
        int[] nums = {4, 1, 3, 7};
        assertEquals(2, solver.subarraySum(nums, 4));
    }

    @Test
    public void testNegativeK() {
        // nums = [1,-1,0], k = -1
        // prefix sums: 1, 0, 0
        // i=0: prefix=1, need 1-(-1)=2, count+=0=0, m={0:1, 1:1}
        // i=1: prefix=0, need 0-(-1)=1, count+=1=1, m={0:2, 1:1}
        // i=2: prefix=0, need 0-(-1)=1, count+=1=2, m={0:3, 1:1}
        int[] nums = {1, -1, 0};
        assertEquals(2, solver.subarraySum(nums, -1));
    }

    @Test
    public void testKZeroWithMixedSignsCancelling() {
        // [1,-1,1,-1]: subarrays summing to 0:
        // [1,-1] at 0-1, [1,-1] at 2-3, [-1,1] at 1-2, [1,-1,1,-1] at 0-3, [-1,1,-1] doesn't...
        // prefix sums: 1, 0, 1, 0
        // i=0: prefix=1, need 1, count+=0, m={0:1,1:1}
        // i=1: prefix=0, need 0, count+=1=1, m={0:2,1:1}
        // i=2: prefix=1, need 1, count+=1=2, m={0:2,1:2}
        // i=3: prefix=0, need 0, count+=2=4, m={0:3,1:2}
        int[] nums = {1, -1, 1, -1};
        assertEquals(4, solver.subarraySum(nums, 0));
    }

    @Test
    public void testAllNegativesWithNegativeK() {
        // [-1,-1,-1], k=-2
        // prefix sums: -1, -2, -3
        // i=0: prefix=-1, need -1-(-2)=1, count+=0, m={0:1,-1:1}
        // i=1: prefix=-2, need -2-(-2)=0, count+=1=1, m={0:1,-1:1,-2:1}
        // i=2: prefix=-3, need -3-(-2)=-1, count+=1=2, m={0:1,-1:1,-2:1,-3:1}
        int[] nums = {-1, -1, -1};
        assertEquals(2, solver.subarraySum(nums, -2));
    }

    @Test
    public void testWholeArraySumsToK() {
        // [1,2,3,4], k=10: only the whole array sums to 10
        int[] nums = {1, 2, 3, 4};
        assertEquals(1, solver.subarraySum(nums, 10));
    }

    @Test
    public void testMultipleDisjointSubarrays() {
        // [3,1,3,1,3], k=3: subarrays [3] at 0, [3] at 2, [3] at 4, [1,3,1,3]? no=8
        // prefix: 3,4,7,8,11
        // i=0: prefix=3, need 0, count+=1=1, m={0:1,3:1}
        // i=1: prefix=4, need 1, count+=0=1, m={0:1,3:1,4:1}
        // i=2: prefix=7, need 4, count+=1=2, m={0:1,3:1,4:1,7:1}
        // i=3: prefix=8, need 5, count+=0=2, m={0:1,3:1,4:1,7:1,8:1}
        // i=4: prefix=11, need 8, count+=1=3, m={0:1,3:1,4:1,7:1,8:1,11:1}
        int[] nums = {3, 1, 3, 1, 3};
        assertEquals(3, solver.subarraySum(nums, 3));
    }

    @Test
    public void testOverlappingSubarrays() {
        // [1,1,1,1], k=2: overlapping subarrays [1,1] at 0-1, 1-2, 2-3 → 3
        int[] nums = {1, 1, 1, 1};
        assertEquals(3, solver.subarraySum(nums, 2));
    }

    @Test
    public void testVeryLargeKNoMatch() {
        // No subarray can sum to Integer.MIN_VALUE with small values
        int[] nums = {1, 2, 3, 4, 5};
        assertEquals(0, solver.subarraySum(nums, Integer.MIN_VALUE));
    }

    @Test
    public void testLargeRandomArrayBruteForceMatch() {
        Random rand = new Random(42L);
        int n = 1000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = rand.nextInt(201) - 100; // range [-100, 100]
        }
        int k = 50;

        int expected = bruteForce(nums, k);
        assertEquals(expected, solver.subarraySum(nums, k));
    }

    @Test
    public void testAllZerosLarger() {
        // [0,0,0,0] k=0 -> n*(n+1)/2 = 4*5/2 = 10
        int[] nums = {0, 0, 0, 0};
        assertEquals(10, solver.subarraySum(nums, 0));
    }

    private int bruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }
}
