package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/22 14:07
 * Created with IntelliJ IDEA
 */

public class MaximumCount_2529Test {

    private final MaximumCount_2529 test = new MaximumCount_2529();

    @Test
    public void test() {
        assertEquals(3, test.maximumCount(new int[]{-2, -1, -1, 1, 2, 3}));
        assertEquals(3, test.maximumCount(new int[]{-3, -2, -1, 0, 0, 1, 2}));
        assertEquals(4, test.maximumCount(new int[]{5, 20, 66, 1314}));
        assertEquals(4, test.maximumCount(new int[]{-52, -20, -6, -1}));
    }

    @Test
    public void testZero() {
        assertEquals(0, test.maximumCount(new int[]{}));
        assertEquals(0, test.maximumCount(new int[]{0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, test.maximumCount(null));
    }

    @Test
    public void testMixedWithMoreNegatives() {
        assertEquals(5, test.maximumCount(new int[]{-10, -8, -6, -4, -2, 0, 1}));
    }

    @Test
    public void testMixedWithMorePositives() {
        assertEquals(6, test.maximumCount(new int[]{-1, 0, 2, 3, 4, 5, 6, 7}));
    }

    @Test
    public void testBoundaryAroundZero() {
        assertEquals(1, test.maximumCount(new int[]{-1, 0, 0, 0}));
        assertEquals(1, test.maximumCount(new int[]{0, 0, 0, 1}));
    }

    @Test
    public void testAllNegativeSingleElement() {
        assertEquals(1, test.maximumCount(new int[]{-1}));
    }

    @Test
    public void testAllPositiveSingleElement() {
        assertEquals(1, test.maximumCount(new int[]{1}));
    }

    @Test
    public void testGiantBalancedArray() {
        int[] nums = new int[2001];
        for (int i = 0; i < 1000; i++) {
            nums[i] = -2000 + i;
        }
        nums[1000] = 0;
        for (int i = 1001; i < nums.length; i++) {
            nums[i] = i - 1000;
        }
        assertEquals(1000, test.maximumCount(nums));
    }

    @Test
    public void testGiantMorePositives() {
        int[] nums = new int[3000];
        for (int i = 0; i < 900; i++) {
            nums[i] = -1000 + i;
        }
        for (int i = 900; i < 1000; i++) {
            nums[i] = 0;
        }
        for (int i = 1000; i < nums.length; i++) {
            nums[i] = i - 999;
        }
        assertEquals(2000, test.maximumCount(nums));
    }
}
