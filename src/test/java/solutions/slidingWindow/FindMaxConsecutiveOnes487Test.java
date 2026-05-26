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
}
