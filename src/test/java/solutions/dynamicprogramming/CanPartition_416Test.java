package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/20 23:52
 * Created with IntelliJ IDEA
 */

public class CanPartition_416Test {
    private final CanPartition_416 test = new CanPartition_416();

    @Test
    public void test() {
        assertTrue(test.canPartition(new int[]{1, 5, 11, 5}));
    }

    @Test
    public void test1() {
        assertTrue(test.canPartition(new int[]{6, 4, 4, 3, 1}));
        assertTrue(test.canPartitionDFS(new int[]{6, 4, 4, 3, 1}));
    }

    @Test
    public void test2() {
        assertTrue(test.canPartition(new int[]{1, 3, 4, 4, 6}));
        assertTrue(test.canPartitionDFS(new int[]{1, 3, 4, 4, 6}));
    }

    @Test
    public void test3() {
        assertTrue(test.canPartition(new int[]{0, 0, 0, 0}));
        assertTrue(test.canPartitionDFS(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void test4() {
        assertTrue(test.canPartition(new int[]{10, 5, 4, 1}));
        assertTrue(test.canPartitionDFS(new int[]{10, 5, 4, 1}));
    }

    @Test
    public void test5() {
        assertFalse(test.canPartition(new int[]{1, 2, 3, 5}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2, 3, 5}));
    }

    @Test
    public void testEmpty() {
        assertFalse(test.canPartition(new int[]{}));
        assertFalse(test.canPartitionDFS(new int[]{}));
        assertFalse(test.canPartition(null));
        assertFalse(test.canPartitionDFS(null));
    }

    @Test
    public void testSingleElement() {
        assertFalse(test.canPartition(new int[]{5}));
        assertFalse(test.canPartitionDFS(new int[]{5}));
    }

    @Test
    public void testOddSum() {
        assertFalse(test.canPartition(new int[]{1, 2, 4}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2, 4}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[200];
        for (int i = 0; i < 200; i++) nums[i] = 1;
        assertTrue(test.canPartition(nums));
    }

    @Test
    public void testTwoEqualElements() {
        assertTrue(test.canPartition(new int[]{3, 3}));
        assertTrue(test.canPartitionDFS(new int[]{3, 3}));
    }

    @Test
    public void testTwoDifferentElements() {
        assertFalse(test.canPartition(new int[]{1, 2}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2}));
    }

    @Test
    public void testAllSameEvenCount() {
        // 6 elements of value 5, sum=30, each half=15 (three 5s)
        assertTrue(test.canPartition(new int[]{5, 5, 5, 5, 5, 5}));
        assertTrue(test.canPartitionDFS(new int[]{5, 5, 5, 5, 5, 5}));
    }

    @Test
    public void testAllSameOddCount() {
        // 5 elements of value 4, sum=20 (even), but need subset summing to 10
        // 10/4 = 2.5 -> cannot pick exact subset summing to 10
        assertFalse(test.canPartition(new int[]{4, 4, 4, 4, 4}));
        assertFalse(test.canPartitionDFS(new int[]{4, 4, 4, 4, 4}));
    }

    @Test
    public void testLargeArraySeed42() {
        Random rand = new Random(42L);
        int[] nums = new int[200];
        int sum = 0;
        for (int i = 0; i < 199; i++) {
            nums[i] = rand.nextInt(10) + 1; // 1-10
            sum += nums[i];
        }
        // make sum even by choosing last element
        if (sum % 2 == 0) {
            nums[199] = 2;
        } else {
            nums[199] = 1;
        }
        sum += nums[199];
        // sum is now even; verify partitionable via DP
        assertTrue(sum % 2 == 0);
        // with 200 elements of values 1-10 and even sum, partition is almost certainly possible
        assertTrue(test.canPartition(nums));
    }

    @Test
    public void testPropertyOddSumAlwaysFalse() {
        // any array with odd sum must return false
        assertFalse(test.canPartition(new int[]{1, 2, 4}));       // sum=7
        assertFalse(test.canPartition(new int[]{3, 7, 1}));       // sum=11
        assertFalse(test.canPartition(new int[]{1}));             // sum=1
        assertFalse(test.canPartition(new int[]{100, 1}));        // sum=101
    }

    @Test
    public void testPropertyPairKKAlwaysTrue() {
        // [k, k] is always partitionable for any positive k
        for (int k = 1; k <= 50; k++) {
            assertTrue(test.canPartition(new int[]{k, k}), "Failed for k=" + k);
        }
    }

    @Test
    public void testLeetCodeExamples() {
        // LeetCode example 1: [1,5,11,5] -> true (subset [1,5,5] and [11])
        assertTrue(test.canPartition(new int[]{1, 5, 11, 5}));
        assertTrue(test.canPartitionDFS(new int[]{1, 5, 11, 5}));
        // LeetCode example 2: [1,2,3,5] -> false
        assertFalse(test.canPartition(new int[]{1, 2, 3, 5}));
        assertFalse(test.canPartitionDFS(new int[]{1, 2, 3, 5}));
    }

    @Test
    public void testAllZeros() {
        // edge: all zeros, sum=0, even, dp[0]=true
        assertTrue(test.canPartition(new int[]{0, 0}));
        assertTrue(test.canPartitionDFS(new int[]{0, 0}));
    }
}