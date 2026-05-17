package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class ContainsNearbyAlmostDuplicate_220Test {

    private final ContainsNearbyAlmostDuplicate_220 test = new ContainsNearbyAlmostDuplicate_220();

    @Test
    public void testHappyCases() {
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        assertTrue(test.treeSet(new int[]{1, 0, 1, 1}, 1, 2));
    }

    @Test
    public void testNegativeCases() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
        assertFalse(test.treeSet(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 2}, 0, 1));
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 2}, 1, -1));
        assertFalse(test.treeSet(new int[0], 3, 1));
        assertTrue(test.treeSet(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 1}, 1, 1));
    }

    @Test
    public void testLargeCase() {
        int[] nums = new int[200];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i * 10;
        }
        nums[199] = nums[198] + 1;
        assertTrue(test.containsNearbyAlmostDuplicate(nums, 2, 1));
        assertTrue(test.treeSet(nums, 2, 1));
    }

    @Test
    public void testHappyDuplicateElements() {
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{1, 1}, 1, 0));
        assertTrue(test.treeSet(new int[]{1, 1}, 1, 0));
    }

    @Test
    public void testHappyExactDifferenceT() {
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{1, 4}, 1, 3));
        assertTrue(test.treeSet(new int[]{1, 4}, 1, 3));
    }

    @Test
    public void testNegativeKZero() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 1}, 0, 0));
        assertFalse(test.treeSet(new int[]{1, 1}, 0, 0));
    }

    @Test
    public void testNegativeTooFarApart() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 100, 200, 1}, 1, 0));
        assertFalse(test.treeSet(new int[]{1, 100, 200, 1}, 1, 0));
    }

    @Test
    public void testEdgeSingleElement() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{5}, 1, 1));
        assertFalse(test.treeSet(new int[]{5}, 1, 1));
    }

    @Test
    public void testEdgeIntegerOverflow() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, 1));
        assertFalse(test.treeSet(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, 1));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i * 100;
        }
        assertFalse(test.containsNearbyAlmostDuplicate(nums, 3, 5));
        assertFalse(test.treeSet(nums, 3, 5));
    }

    @Test
    public void testEmptyArray() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{}, 1, 1));
        assertFalse(test.treeSet(new int[]{}, 1, 1));
    }

    @Test
    public void testKZeroAlwaysFalse() {
        // k=0 means no two DIFFERENT indices can satisfy |i-j|<=0
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 1, 1}, 0, 100));
        assertFalse(test.treeSet(new int[]{1, 1, 1}, 0, 100));
    }

    @Test
    public void testTZeroRequiresExactDuplicate() {
        // t=0 degenerates to LeetCode 219: exact duplicate within k
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        assertTrue(test.treeSet(new int[]{1, 2, 3, 1}, 3, 0));
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 2, 0));
        assertFalse(test.treeSet(new int[]{1, 2, 3, 1}, 2, 0));
    }

    @Test
    public void testNegativeValues() {
        // [-3, -1] with k=1, t=2 -> |(-3)-(-1)|=2 <= t -> true
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{-3, -1}, 1, 2));
        assertTrue(test.treeSet(new int[]{-3, -1}, 1, 2));
        // [-3, -1] with k=1, t=1 -> |(-3)-(-1)|=2 > 1 -> false
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{-3, -1}, 1, 1));
        assertFalse(test.treeSet(new int[]{-3, -1}, 1, 1));
    }

    @Test
    public void testAdjacentDuplicatesTZeroK1() {
        // Adjacent duplicates with t=0, k=1 -> true
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{7, 7, 10}, 1, 0));
        assertTrue(test.treeSet(new int[]{7, 7, 10}, 1, 0));
    }

    @Test
    public void testSubtractionOverflowMinMax() {
        // |MIN_VALUE - MAX_VALUE| overflows int; with t=1 should be false
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, 1));
        assertFalse(test.treeSet(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, 1));
        // With t = (long)MAX - (long)MIN = 4294967295 > Integer.MAX_VALUE, use Integer.MAX_VALUE as t
        // |MIN_VALUE - MAX_VALUE| = 4294967295 > Integer.MAX_VALUE -> false with t=MAX_VALUE
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, Integer.MAX_VALUE));
        assertFalse(test.treeSet(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1, Integer.MAX_VALUE));
    }

    @Test
    public void testAllIdenticalElements() {
        // All elements identical, k>=1, t>=0 -> true
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{5, 5, 5, 5}, 1, 0));
        assertTrue(test.treeSet(new int[]{5, 5, 5, 5}, 1, 0));
    }

    @Test
    public void testNegativeT() {
        // t<0 should always return false (no pair can have negative absolute difference)
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 1, 1}, 2, -1));
        assertFalse(test.treeSet(new int[]{1, 1, 1}, 2, -1));
    }

    @Test
    public void testLargeDifferencesExceedingT() {
        // Elements far apart, t small
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 100, 200, 300}, 3, 5));
        assertFalse(test.treeSet(new int[]{1, 100, 200, 300}, 3, 5));
    }

    @Test
    public void testLargeArrayBruteForceReference() {
        // 1000 elements, seed 42L, cross-check against brute-force O(n*k)
        Random rng = new Random(42L);
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rng.nextInt();
        }
        int k = 5;
        int t = 100;

        // Brute-force reference
        boolean expected = false;
        for (int i = 0; i < nums.length && !expected; i++) {
            for (int j = Math.max(0, i - k); j < i; j++) {
                if (Math.abs((long) nums[i] - nums[j]) <= t) {
                    expected = true;
                    break;
                }
            }
        }

        if (expected) {
            assertTrue(test.containsNearbyAlmostDuplicate(nums, k, t));
            assertTrue(test.treeSet(nums, k, t));
        } else {
            assertFalse(test.containsNearbyAlmostDuplicate(nums, k, t));
            assertFalse(test.treeSet(nums, k, t));
        }
    }
}
