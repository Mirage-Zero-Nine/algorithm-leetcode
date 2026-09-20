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

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({"1,0,1", "0,0,0", "00111,1,4", "1010101,1,3", "110011,1,3", "000111,2,5", "100001,2,3", "111000111,2,5", "010101,3,6", "11111,0,5"})
    void additionalBoundaryCases(String values, int k, int expected) {
        int[] nums = values.chars().map(c -> c - '0').toArray();
        assertEquals(expected, test.longestOnes(nums, k));
    }
}
