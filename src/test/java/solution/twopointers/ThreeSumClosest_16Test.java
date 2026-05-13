package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ThreeSumClosest_16Test {

    private final ThreeSumClosest_16 test = new ThreeSumClosest_16();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        assertEquals(0, test.threeSumClosest(new int[]{0, 0, 0}, 1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.threeSumClosest(new int[]{1, 1, -2}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.threeSumClosest(new int[]{-1, 0, 1, 2, -1, -4}, 3));
    }

    @Test
    public void testExactMatch() {
        assertEquals(6, test.threeSumClosest(new int[]{1, 2, 3, 4, 5}, 6));
    }

    @Test
    public void testAllNegative() {
        assertEquals(-6, test.threeSumClosest(new int[]{-3, -2, -1}, -6));
    }

    @Test
    public void testAllPositive() {
        assertEquals(9, test.threeSumClosest(new int[]{1, 2, 3, 4}, 10));
    }

    @Test
    public void testNegativeTarget() {
        assertEquals(-3, test.threeSumClosest(new int[]{-1, -2, -3, 1, 2}, -3));
    }

    @Test
    public void testLessThanThreeElements() {
        assertEquals(0, test.threeSumClosest(new int[]{1, 2}, 3));
    }

    @Test
    public void testDuplicateElements() {
        assertEquals(3, test.threeSumClosest(new int[]{1, 1, 1, 1}, 3));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = i - 500;
        }
        assertEquals(1000, test.threeSumClosest(nums, 1000));
    }

    @Test
    public void testLargeTarget() {
        assertEquals(12, test.threeSumClosest(new int[]{1, 2, 3, 4, 5}, 100));
    }
}
