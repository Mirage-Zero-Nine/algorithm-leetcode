package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Rob_213Test {

    private final Rob_213 test = new Rob_213();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.rob(new int[]{2, 3, 2}));
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.rob(new int[]{}));
        assertEquals(1, test.rob(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(12, test.rob(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(3, test.rob(new int[]{1, 3}));
        assertEquals(5, test.rob(new int[]{5, 2}));
    }

    @Test
    public void testAllSameValues() {
        assertEquals(4, test.rob(new int[]{2, 2, 2, 2}));
    }

    @Test
    public void testCircularConstraint() {
        // First and last are adjacent, so can't take both
        assertEquals(11, test.rob(new int[]{10, 1, 1, 10}));
    }

    @Test
    public void testSingleZero() {
        assertEquals(0, test.rob(new int[]{0}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.rob(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testThreeElements() {
        assertEquals(5, test.rob(new int[]{5, 1, 1}));
        assertEquals(5, test.rob(new int[]{1, 5, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i + 1;
        }
        int result = test.rob(nums);
        // Result should be positive and reasonable
        assertEquals(2550, result);
    }
}
