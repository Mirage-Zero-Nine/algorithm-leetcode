package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/06/30 20:39
 * Created with IntelliJ IDEA
 */

public class MaximumDifference_2016Test {
    private final MaximumDifference_2016 test = new MaximumDifference_2016();

    @Test
    public void test() {
        assertEquals(4, test.maximumDifference(new int[]{7, 1, 5, 4}));
        assertEquals(-1, test.maximumDifference(new int[]{9, 4, 3, 2}));
        assertEquals(9, test.maximumDifference(new int[]{1, 5, 2, 10}));
    }

    @Test
    public void testHappyCases() {
        assertEquals(8, test.maximumDifference(new int[]{2, 10}));
        assertEquals(99, test.maximumDifference(new int[]{1, 100}));
        assertEquals(5, test.maximumDifference(new int[]{3, 8, 1, 6}));
    }

    @Test
    public void testEdgeAndNegativeCases() {
        assertEquals(0, test.maximumDifference(null));
        assertEquals(0, test.maximumDifference(new int[]{5}));
        assertEquals(-1, test.maximumDifference(new int[]{5, 5, 5}));
        assertEquals(-1, test.maximumDifference(new int[]{10, 9}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 10_000 - i;
        }
        nums[900] = 1;
        nums[999] = 20_000;
        assertEquals(19_999, test.maximumDifference(nums));
    }

    @Test
    public void testTwoElementsDecreasing() {
        assertEquals(-1, test.maximumDifference(new int[]{10, 5}));
    }

    @Test
    public void testTwoElementsEqual() {
        assertEquals(-1, test.maximumDifference(new int[]{7, 7}));
    }

    @Test
    public void testIncreasingArray() {
        assertEquals(4, test.maximumDifference(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testMaxAtEnd() {
        assertEquals(99, test.maximumDifference(new int[]{50, 1, 30, 100}));
    }

    @Test
    public void testMinThenMax() {
        assertEquals(999, test.maximumDifference(new int[]{500, 1, 1000, 2, 3}));
    }

    @Test
    public void testLargeValues() {
        assertEquals(Integer.MAX_VALUE - 1, test.maximumDifference(new int[]{1, Integer.MAX_VALUE}));
    }
}
