package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxProduct_152Test {
    private final MaxProduct_152 solution = new MaxProduct_152();

    @Test
    void testBasic() {
        assertEquals(6, solution.maxProduct(new int[]{2, 3, -2, 4}));
    }

    @Test
    void testNegatives() {
        assertEquals(24, solution.maxProduct(new int[]{-2, 3, -4}));
    }

    @Test
    void testWithZero() {
        assertEquals(0, solution.maxProduct(new int[]{-2, 0, -1}));
    }

    @Test
    void testSingleElement() {
        assertEquals(5, solution.maxProduct(new int[]{5}));
    }

    @Test
    void testAllNegative() {
        assertEquals(6, solution.maxProduct(new int[]{-1, -2, -3}));
    }

    @Test
    void testAllPositive() {
        assertEquals(120, solution.maxProduct(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testSingleNegative() {
        assertEquals(-3, solution.maxProduct(new int[]{-3}));
    }

    @Test
    void testZeroInMiddle() {
        assertEquals(4, solution.maxProduct(new int[]{2, 0, 4}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0, solution.maxProduct(new int[]{}));
    }

    @Test
    void testTwoNegatives() {
        assertEquals(6, solution.maxProduct(new int[]{-2, -3}));
    }

    @Test
    void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = 1;
        }
        nums[500] = 2;
        assertEquals(2, solution.maxProduct(nums));
    }
}
