package solution.findkth;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindKthLargest_215Test {
    private final FindKthLargest_215 solver = new FindKthLargest_215();

    @Test public void testBasic() {
        assertEquals(5, solver.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @Test public void testDuplicates() {
        assertEquals(4, solver.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test public void testSingleElement() {
        assertEquals(7, solver.findKthLargest(new int[]{7}, 1));
    }

    @Test public void testKIsLength() {
        assertEquals(1, solver.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 6));
    }

    @Test public void testMinHeapApproach() {
        assertEquals(5, solver.findKthLargestMinHeap(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @Test public void testMinHeapWithDuplicates() {
        assertEquals(4, solver.findKthLargestMinHeap(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test public void testNegativeNumbers() {
        assertEquals(-1, solver.findKthLargest(new int[]{-1, -2, -3, -4}, 1));
    }

    @Test public void testAllSame() {
        assertEquals(5, solver.findKthLargest(new int[]{5, 5, 5, 5}, 2));
    }

    @Test public void testMinHeapSingle() {
        assertEquals(7, solver.findKthLargestMinHeap(new int[]{7}, 1));
    }

    @Test public void testMinHeapKIsLength() {
        assertEquals(1, solver.findKthLargestMinHeap(new int[]{3, 2, 1, 5, 6, 4}, 6));
    }

    @Test public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) nums[i] = i;
        assertEquals(9999, solver.findKthLargest(nums, 1));
        assertEquals(0, solver.findKthLargest(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 10));
    }

    @Test public void testMinHeapNegative() {
        assertEquals(-2, solver.findKthLargestMinHeap(new int[]{-1, -2, -3, -4}, 2));
    }

    @Test public void testKEqualsOneIsMaximum() {
        assertEquals(9, solver.findKthLargest(new int[]{1, 3, 5, 7, 9}, 1));
        assertEquals(9, solver.findKthLargestMinHeap(new int[]{1, 3, 5, 7, 9}, 1));
    }

    @Test public void testSortedAscending() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        assertEquals(6, solver.findKthLargest(nums.clone(), 3));
        assertEquals(6, solver.findKthLargestMinHeap(nums.clone(), 3));
    }

    @Test public void testSortedDescending() {
        int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
        assertEquals(6, solver.findKthLargest(nums.clone(), 3));
        assertEquals(6, solver.findKthLargestMinHeap(nums.clone(), 3));
    }

    @Test public void testMixPositiveNegative() {
        int[] nums = {-3, 2, -1, 5, 0, -4, 3};
        // sorted desc: 5, 3, 2, 0, -1, -3, -4 -> k=4 -> 0
        assertEquals(0, solver.findKthLargest(nums.clone(), 4));
        assertEquals(0, solver.findKthLargestMinHeap(nums.clone(), 4));
    }

    @Test public void testAllSameBothImpls() {
        int[] nums = {2, 2, 2, 2, 2};
        assertEquals(2, solver.findKthLargest(nums.clone(), 1));
        assertEquals(2, solver.findKthLargest(nums.clone(), 5));
        assertEquals(2, solver.findKthLargestMinHeap(nums.clone(), 1));
        assertEquals(2, solver.findKthLargestMinHeap(nums.clone(), 5));
    }

    @Test public void testLeetCodeDuplicateExample() {
        // LeetCode example: [3,2,3,1,2,4,5,5,6] k=4 -> 4
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        assertEquals(4, solver.findKthLargest(nums.clone(), 4));
        assertEquals(4, solver.findKthLargestMinHeap(nums.clone(), 4));
        // k=9 -> minimum = 1
        assertEquals(1, solver.findKthLargest(nums.clone(), 9));
        assertEquals(1, solver.findKthLargestMinHeap(nums.clone(), 9));
    }

    @Test public void testLargeArrayRandomSeed42() {
        Random rng = new Random(42L);
        int[] nums = new int[10000];
        for (int i = 0; i < nums.length; i++) nums[i] = rng.nextInt();
        int k = 500;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int expected = sorted[sorted.length - k]; // kth largest
        assertEquals(expected, solver.findKthLargest(nums.clone(), k));
        assertEquals(expected, solver.findKthLargestMinHeap(nums.clone(), k));
    }

    @Test public void testPropertySortedDescKMinus1() {
        int[] nums = {10, 4, 3, 8, 7, 1, 12, 6};
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for (int k = 1; k <= nums.length; k++) {
            int expected = sorted[sorted.length - k];
            assertEquals(expected, solver.findKthLargest(nums.clone(), k), "quickselect k=" + k);
            assertEquals(expected, solver.findKthLargestMinHeap(nums.clone(), k), "minheap k=" + k);
        }
    }

    @Test public void testAllNegativeValues() {
        int[] nums = {-10, -20, -30, -5, -15};
        // sorted desc: -5, -10, -15, -20, -30
        assertEquals(-5, solver.findKthLargest(nums.clone(), 1));
        assertEquals(-30, solver.findKthLargest(nums.clone(), 5));
        assertEquals(-5, solver.findKthLargestMinHeap(nums.clone(), 1));
        assertEquals(-30, solver.findKthLargestMinHeap(nums.clone(), 5));
    }

    @Test public void testMinHeapAllSameKIsLength() {
        assertEquals(3, solver.findKthLargestMinHeap(new int[]{3, 3, 3, 3}, 4));
    }
}
