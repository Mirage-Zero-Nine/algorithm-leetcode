package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxConsecutiveOnes_485Test {

    private final FindMaxConsecutiveOnes_485 test = new FindMaxConsecutiveOnes_485();

    @Test
    public void testHappyCase1() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
    }

    @Test
    public void testHappyCase2() {
        assertEquals(2, test.findMaxConsecutiveOnes(new int[]{1, 1}));
    }

    @Test
    public void testHappyCase3() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1}));
    }

    @Test
    public void testHappyCase4() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0, 0, 0}));
    }

    @Test
    public void testHappyCase5() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{0, 1, 1, 1, 1, 0}));
    }

    @Test
    public void testNegativeCase() {
        // All zeros
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testEdgeCaseEmpty() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{}));
    }

    @Test
    public void testEdgeCaseSingleElement() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1}));
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0}));
    }

    @Test
    public void testEdgeCaseAlternating() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1, 0}));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = 1;
        }
        assertEquals(n, test.findMaxConsecutiveOnes(nums));

        for (int i = 0; i < n; i++) {
            if (i % 500 == 0) nums[i] = 0;
        }
        assertEquals(499, test.findMaxConsecutiveOnes(nums));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            int[] nums = random.ints(1 + random.nextInt(40), 0, 2).toArray();
            int k = 0, expected = 0;
            for (int left = 0; left < nums.length; left++) {
                int zeros = 0;
                for (int right = left; right < nums.length; right++) {
                    if (nums[right] == 0) zeros++;
                    if (zeros <= k) expected = Math.max(expected, right - left + 1);
                }
            }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new FindMaxConsecutiveOnes_485().findMaxConsecutiveOnes(nums));

        }
    }
}
