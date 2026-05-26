package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestSubarray_1493Test {

    private final LongestSubarray_1493 solution = new LongestSubarray_1493();

    @Test
    public void testHappy_example1() {
        assertEquals(3, solution.longestSubarray(new int[]{1, 1, 0, 1}));
    }

    @Test
    public void testHappy_example2() {
        assertEquals(5, solution.longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testHappy_example3() {
        assertEquals(2, solution.longestSubarray(new int[]{1, 1, 1}));
    }

    @Test
    public void testHappy_deleteMiddleZero() {
        assertEquals(4, solution.longestSubarray(new int[]{1, 1, 0, 1, 1}));
    }

    @Test
    public void testNegative_allZeros() {
        assertEquals(0, solution.longestSubarray(new int[]{0, 0, 0}));
    }

    @Test
    public void testEdge_singleOne() {
        assertEquals(0, solution.longestSubarray(new int[]{1}));
    }

    @Test
    public void testEdge_singleZero() {
        assertEquals(0, solution.longestSubarray(new int[]{0}));
    }

    @Test
    public void testEdge_twoOnes() {
        assertEquals(1, solution.longestSubarray(new int[]{1, 1}));
    }

    @Test
    public void testEdge_zeroAtStart() {
        assertEquals(3, solution.longestSubarray(new int[]{0, 1, 1, 1}));
    }

    @Test
    public void testEdge_zeroAtEnd() {
        assertEquals(3, solution.longestSubarray(new int[]{1, 1, 1, 0}));
    }

    @Test
    public void testGiant() {
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 1;
        }
        nums[50000] = 0; // single zero in the middle
        assertEquals(99999, solution.longestSubarray(nums));
    }
}
