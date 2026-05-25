package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestSubarray_1438Test {

    private final LongestSubarray_1438 solution = new LongestSubarray_1438();

    @Test
    public void testHappy_example1() {
        assertEquals(2, solution.longestSubarray(new int[]{8, 2, 4, 7}, 4));
    }

    @Test
    public void testHappy_example2() {
        assertEquals(4, solution.longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
    }

    @Test
    public void testHappy_example3() {
        assertEquals(3, solution.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
    }

    @Test
    public void testHappy_allSame() {
        assertEquals(5, solution.longestSubarray(new int[]{3, 3, 3, 3, 3}, 0));
    }

    @Test
    public void testHappy_largeLimitCoversAll() {
        assertEquals(5, solution.longestSubarray(new int[]{1, 100, 50, 20, 80}, 1000));
    }

    @Test
    public void testNegative_limitZeroDifferentElements() {
        assertEquals(1, solution.longestSubarray(new int[]{1, 2, 3, 4, 5}, 0));
    }

    @Test
    public void testEdge_singleElement() {
        assertEquals(1, solution.longestSubarray(new int[]{5}, 0));
    }

    @Test
    public void testEdge_twoElementsWithinLimit() {
        assertEquals(2, solution.longestSubarray(new int[]{1, 3}, 2));
    }

    @Test
    public void testEdge_twoElementsExceedLimit() {
        assertEquals(1, solution.longestSubarray(new int[]{1, 10}, 2));
    }

    @Test
    public void testEdge_decreasingArray() {
        assertEquals(3, solution.longestSubarray(new int[]{5, 4, 3, 2, 1}, 2));
    }

    @Test
    public void testGiant() {
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i % 100;
        }
        int result = solution.longestSubarray(nums, 50);
        assert result > 0;
    }
}
