package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveDuplicates_80Test {

    private final RemoveDuplicates_80 test = new RemoveDuplicates_80();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
        assertEquals(7, test.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.removeDuplicates(new int[]{1}));
        assertEquals(2, test.removeDuplicates(new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.removeDuplicates(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
    }

    @Test
    public void testNoDuplicates() {
        assertEquals(5, test.removeDuplicates(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testAllSame() {
        assertEquals(2, test.removeDuplicates(new int[]{4, 4, 4, 4, 4}));
    }

    @Test
    public void testExactlyTwoDuplicates() {
        assertEquals(4, test.removeDuplicates(new int[]{1, 1, 2, 2}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.removeDuplicates(new int[]{}));
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums = new int[]{-3, -3, -3, -1, -1, 0, 0, 0};
        assertEquals(6, test.removeDuplicates(nums));
        assertEquals(-3, nums[0]);
        assertEquals(-3, nums[1]);
        assertEquals(-1, nums[2]);
        assertEquals(-1, nums[3]);
        assertEquals(0, nums[4]);
        assertEquals(0, nums[5]);
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) {
            nums[i] = i / 5;
        }
        // Each value appears 5 times, kept at most 2 => 2000 distinct values * 2 = 4000
        assertEquals(4000, test.removeDuplicates(nums));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.removeDuplicates(new int[]{1, 2}));
    }

    @Test
    public void testThreeSameElements() {
        assertEquals(2, test.removeDuplicates(new int[]{1, 1, 1}));
    }
}
