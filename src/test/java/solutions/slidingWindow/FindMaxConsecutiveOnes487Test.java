package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxConsecutiveOnes487Test {

    private final FindMaxConsecutiveOnes_487 test = new FindMaxConsecutiveOnes_487();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{0}));
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 1, 1, 1, 1, 0, 1}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(5, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testSingleZeroInMiddle() {
        assertEquals(5, test.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1}));
    }

    @Test
    public void testZeroAtStart() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{0, 1, 1, 1}));
    }

    @Test
    public void testZeroAtEnd() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 0}));
    }

    @Test
    public void testAlternating() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1, 0, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.findMaxConsecutiveOnes(new int[]{1, 0}));
        assertEquals(2, test.findMaxConsecutiveOnes(new int[]{0, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        // Fill with pattern: 99 ones then 1 zero, repeated
        for (int i = 0; i < 10000; i++) {
            nums[i] = (i % 100 == 99) ? 0 : 1;
        }
        // Two consecutive groups of 99 ones with one zero flipped = 199
        assertEquals(199, test.findMaxConsecutiveOnes(nums));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            int[] nums = random.ints(1 + random.nextInt(40), 0, 2).toArray();
            int k = 1, expected = 0;
            for (int left = 0; left < nums.length; left++) {
                int zeros = 0;
                for (int right = left; right < nums.length; right++) {
                    if (nums[right] == 0) zeros++;
                    if (zeros <= k) expected = Math.max(expected, right - left + 1);
                }
            }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new FindMaxConsecutiveOnes_487().findMaxConsecutiveOnes(nums));

        }
    }
}
