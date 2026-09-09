package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubarraySum_560Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void signedArraysAndNegativeTargetsMatchDirectSums(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int trial = 0; trial < 100; trial++) {
            int[] nums = random.ints(1 + random.nextInt(30), -3, 4).toArray();
            int target = random.nextInt(13) - 6;
            assertEquals(bruteForce(nums, target), solver.subarraySum(nums, target),
                    java.util.Arrays.toString(nums) + ", target=" + target);
        }
    }

    @Test
    void maximumLengthZerosCountEveryNonemptySubarray() {
        int size = 20_000;
        assertEquals(size * (size + 1) / 2, solver.subarraySum(new int[size], 0));
        assertEquals(0, solver.subarraySum(new int[size], 1));
    }


    private final SubarraySum_560 solver = new SubarraySum_560();

    @Test
    void leetCodeExample1() {
        // nums = [1,1,1], k = 2
        // subarrays: [1,1] at indices 0-1, [1,1] at indices 1-2 → 2
        int[] nums = {1, 1, 1};
        assertEquals(2, solver.subarraySum(nums, 2));
    }

    @Test
    void leetCodeExample2() {
        // nums = [1,2,3], k = 3
        // subarrays: [1,2], [3] → 2
        int[] nums = {1, 2, 3};
        assertEquals(2, solver.subarraySum(nums, 3));
    }

    @Test
    void singleElementMatch() {
        int[] nums = {1};
        assertEquals(1, solver.subarraySum(nums, 1));
    }

    @Test
    void singleElementNoMatch() {
        int[] nums = {1};
        assertEquals(0, solver.subarraySum(nums, 2));
    }

    @Test
    void handlesNegativeNumbers() {
        // nums = [1,-1,-1,1], k = 0
        // subarrays with sum 0: [1,-1] at 0-1, [-1,1] at 2-3, and the whole array at 0-3
        // prefix sums: 1, 0, -1, 0
        // i=0: prefix=1, need 1-0=1, count=0, m={0:1, 1:1}
        // i=1: prefix=0, need 0-0=0, count+=1=1, m={0:2, 1:1}
        // i=2: prefix=-1, need -1-0=-1, count+=0=1, m={0:2, 1:1, -1:1}
        // i=3: prefix=0, need 0-0=0, count+=2=3, m={0:3, 1:1, -1:1}
        int[] nums = {1, -1, -1, 1};
        assertEquals(3, solver.subarraySum(nums, 0));
    }

    @Test
    void allZeros() {
        // nums = [0,0,0], k = 0
        // all subarrays sum to 0: [0],[0],[0],[0,0],[0,0],[0,0,0] → 6
        int[] nums = {0, 0, 0};
        assertEquals(6, solver.subarraySum(nums, 0));
    }

    @Test
    void noMatch() {
        int[] nums = {1, 2, 3};
        assertEquals(0, solver.subarraySum(nums, 7));
    }

    @Test
    void emptyArrayReturnsZero() {
        int[] nums = {};
        assertEquals(0, solver.subarraySum(nums, 0));
    }

    @Test
    void nullArrayReturnsZero() {
        assertEquals(0, solver.subarraySum(null, 0));
    }

    @Test
    void subarrayCanStartAfterSeveralElements() {
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
    void handlesNegativeK() {
        // nums = [1,-1,0], k = -1
        // prefix sums: 1, 0, 0
        // i=0: prefix=1, need 1-(-1)=2, count+=0=0, m={0:1, 1:1}
        // i=1: prefix=0, need 0-(-1)=1, count+=1=1, m={0:2, 1:1}
        // i=2: prefix=0, need 0-(-1)=1, count+=1=2, m={0:3, 1:1}
        int[] nums = {1, -1, 0};
        assertEquals(2, solver.subarraySum(nums, -1));
    }

    @Test
    void zeroKWithMixedSignsCancelling() {
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
    void allNegativesWithNegativeK() {
        // [-1,-1,-1], k=-2
        // prefix sums: -1, -2, -3
        // i=0: prefix=-1, need -1-(-2)=1, count+=0, m={0:1,-1:1}
        // i=1: prefix=-2, need -2-(-2)=0, count+=1=1, m={0:1,-1:1,-2:1}
        // i=2: prefix=-3, need -3-(-2)=-1, count+=1=2, m={0:1,-1:1,-2:1,-3:1}
        int[] nums = {-1, -1, -1};
        assertEquals(2, solver.subarraySum(nums, -2));
    }

    @Test
    void wholeArraySumsToK() {
        // [1,2,3,4], k=10: only the whole array sums to 10
        int[] nums = {1, 2, 3, 4};
        assertEquals(1, solver.subarraySum(nums, 10));
    }

    @Test
    void multipleDisjointSubarrays() {
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
    void overlappingSubarrays() {
        // [1,1,1,1], k=2: overlapping subarrays [1,1] at 0-1, 1-2, 2-3 → 3
        int[] nums = {1, 1, 1, 1};
        assertEquals(3, solver.subarraySum(nums, 2));
    }

    @Test
    void veryLargeKNoMatch() {
        // No subarray can sum to Integer.MIN_VALUE with small values
        int[] nums = {1, 2, 3, 4, 5};
        assertEquals(0, solver.subarraySum(nums, Integer.MIN_VALUE));
    }

    @Test
    void largeRandomArrayMatchesBruteForce() {
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
    void largeAllZeroArrayMatchesClosedForm() {
        int[] nums = new int[20_000];
        assertEquals(200_010_000, solver.subarraySum(nums, 0));
    }

    @Test
    void inputIsNotModifiedAndSolverCanBeReused() {
        int[] nums = {2, -2, 2};
        int[] original = nums.clone();

        assertEquals(3, solver.subarraySum(nums, 2));
        assertArrayEquals(original, nums);
        assertEquals(2, solver.subarraySum(nums, 0));
    }

    @Test
    void fourZerosAllMatch() {
        // [0,0,0,0] k=0 -> n*(n+1)/2 = 4*5/2 = 10
        int[] nums = {0, 0, 0, 0};
        assertEquals(10, solver.subarraySum(nums, 0));
    }

    private int bruteForce(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
