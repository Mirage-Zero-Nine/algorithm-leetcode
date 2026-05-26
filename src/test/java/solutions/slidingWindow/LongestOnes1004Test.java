package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestOnes1004Test {

    private final LongestOnes_1004 test = new LongestOnes_1004();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        assertEquals(10, test.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestOnes(new int[]{0}, 0));
        assertEquals(1, test.longestOnes(new int[]{1}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(11, test.longestOnes(new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1}, 1));
    }

    @Test
    public void testAllOnesKZero() {
        assertEquals(5, test.longestOnes(new int[]{1, 1, 1, 1, 1}, 0));
    }

    @Test
    public void testAllZerosKEqualsLength() {
        assertEquals(5, test.longestOnes(new int[]{0, 0, 0, 0, 0}, 5));
    }

    @Test
    public void testAllZerosKLessThanLength() {
        assertEquals(3, test.longestOnes(new int[]{0, 0, 0, 0, 0}, 3));
    }

    @Test
    public void testKGreaterThanZeros() {
        // k=10 but only 2 zeros, can flip all -> entire array
        assertEquals(7, test.longestOnes(new int[]{1, 0, 1, 1, 0, 1, 1}, 10));
    }

    @Test
    public void testSingleZeroKOne() {
        assertEquals(1, test.longestOnes(new int[]{0}, 1));
    }

    @Test
    public void testAlternatingKTwo() {
        // [0,1,0,1,0,1,0] k=2 -> best window: 1,0,1,0,1 = 5
        assertEquals(5, test.longestOnes(new int[]{0, 1, 0, 1, 0, 1, 0}, 2));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[100000];
        for (int i = 0; i < 100000; i++) {
            nums[i] = (i % 2 == 0) ? 1 : 0;
        }
        // alternating 1,0,1,0... with k=50000 zeros flippable
        // total zeros = 50000, k=50000 -> can flip all -> entire array
        assertEquals(100000, test.longestOnes(nums, 50000));
    }
}
