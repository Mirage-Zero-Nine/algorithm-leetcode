package solutions.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaximumProduct_628Test {
    private final MaximumProduct_628 solution = new MaximumProduct_628();

    @Test
    void testBasic() {
        assertEquals(6, solution.maximumProduct(new int[]{1, 2, 3}));
    }

    @Test
    void testWithNegatives() {
        assertEquals(24, solution.maximumProduct(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testTwoNegatives() {
        assertEquals(300, solution.maximumProduct(new int[]{-10, -10, 1, 3, 2}));
    }

    @Test
    void testAllNegative() {
        assertEquals(-6, solution.maximumProduct(new int[]{-1, -2, -3}));
    }

    @Test
    void testMixed() {
        assertEquals(150, solution.maximumProduct(new int[]{-10, -5, 1, 2, 3}));
    }

    @Test
    void testAllZeros() {
        assertEquals(0, solution.maximumProduct(new int[]{0, 0, 0, 0}));
    }

    @Test
    void testThreeElements() {
        assertEquals(6, solution.maximumProduct(new int[]{1, 2, 3}));
    }

    @Test
    void testLargeNegatives() {
        assertEquals(200, solution.maximumProduct(new int[]{-10, -10, -1, 2}));
    }

    @Test
    void testAllSame() {
        assertEquals(8, solution.maximumProduct(new int[]{2, 2, 2, 2}));
    }

    @Test
    void testWithZeroAndNegatives() {
        assertEquals(0, solution.maximumProduct(new int[]{-3, -2, -1, 0}));
    }

    @Test
    void testGiantArray() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i - 50;
        }
        // max product is max(min1*min2*max1, max1*max2*max3)
        // min1=-50, min2=-49, max1=49 => (-50)*(-49)*49 = 120050
        // max1=49, max2=48, max3=47 => 49*48*47 = 110544
        assertEquals(120050, solution.maximumProduct(nums));
    }
}
