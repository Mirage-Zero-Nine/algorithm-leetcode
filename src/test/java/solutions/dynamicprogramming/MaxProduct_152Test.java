package solutions.dynamicprogramming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "product array {0}")
    @CsvSource({"'1,2,3',6", "'-1,-2',2", "'-1,0,-2',0", "'2,-5,-2',20", "'-2,0,3,-4',3", "'0,0,0',0", "'3,-1,4,-1,2',24", "'-3,-2,-1,0',6", "'1,-2,3,-4,5',120", "'-1,2,-3,4,-5',120"})
    void testAdditionalSignAndZeroPatterns(String encoded, int expected) {
        String[] values = encoded.split(","); int[] nums = new int[values.length];
        for (int i = 0; i < values.length; i++) nums[i] = Integer.parseInt(values[i]);
        assertEquals(expected, solution.maxProduct(nums));
    }
}
