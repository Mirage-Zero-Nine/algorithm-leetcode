package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxSumDivThree_1262Test {

    private final MaxSumDivThree_1262 test = new MaxSumDivThree_1262();

    @Test
    public void testHappyCases() {
        assertEquals(18, test.maxSumDivThree(new int[]{3, 6, 5, 1, 8}));
        assertEquals(12, test.maxSumDivThree(new int[]{1, 2, 3, 4, 4}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(6, test.maxSumDivThree(new int[]{1, 2, 4}));
        assertEquals(3, test.maxSumDivThree(new int[]{3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(21, test.maxSumDivThree(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void testAllDivisibleByThree() {
        assertEquals(18, test.maxSumDivThree(new int[]{3, 6, 9}));
    }

    @Test
    public void testSingleElementNotDivisible() {
        assertEquals(0, test.maxSumDivThree(new int[]{1}));
    }

    @Test
    public void testSingleElementDivisible() {
        assertEquals(9, test.maxSumDivThree(new int[]{9}));
    }

    @Test
    public void testTwoElementsSumDivisible() {
        assertEquals(3, test.maxSumDivThree(new int[]{1, 2}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxSumDivThree(new int[]{}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(9, test.maxSumDivThree(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) nums[i] = i + 1;
        // sum 1..100 = 5050, 5050 % 3 = 2, need to remove smallest with remainder 2 => remove 2, result = 5048
        assertEquals(5049, test.maxSumDivThree(nums));
    }
}
