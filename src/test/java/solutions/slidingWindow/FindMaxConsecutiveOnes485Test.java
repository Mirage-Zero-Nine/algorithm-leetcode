package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxConsecutiveOnes485Test {

    private final FindMaxConsecutiveOnes_485 test = new FindMaxConsecutiveOnes_485();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        assertEquals(2, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0}));
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testAlternating() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1, 0}));
    }

    @Test
    public void testConsecutiveAtEnd() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{0, 0, 1, 1, 1}));
    }

    @Test
    public void testConsecutiveAtStart() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 0}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) arr[i] = 1;
        arr[50000] = 0;
        assertEquals(50000, test.findMaxConsecutiveOnes(arr));
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
