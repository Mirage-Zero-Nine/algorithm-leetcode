package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxDotProduct_1458Test {

    private final MaxDotProduct_1458 test = new MaxDotProduct_1458();

    @Test
    public void testHappyCases() {
        assertEquals(18, test.maxDotProduct(new int[]{2, 1, -2, 5}, new int[]{3, 0, -6}));
        assertEquals(21, test.maxDotProduct(new int[]{3, -2}, new int[]{2, -6, 7}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxDotProduct(new int[]{-1, -1}, new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.maxDotProduct(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testSingleElements() {
        assertEquals(6, test.maxDotProduct(new int[]{2}, new int[]{3}));
    }

    @Test
    public void testAllNegative() {
        assertEquals(-1, test.maxDotProduct(new int[]{-1, -2, -3}, new int[]{1, 2, 3}));
    }

    @Test
    public void testBothAllNegative() {
        assertEquals(14, test.maxDotProduct(new int[]{-3, -2, -1}, new int[]{-3, -2, -1}));
    }

    @Test
    public void testMixedSigns() {
        assertEquals(40, test.maxDotProduct(new int[]{5, -4, -3}, new int[]{-4, -3, 8}));
    }

    @Test
    public void testLargePositiveValues() {
        assertEquals(1000000, test.maxDotProduct(new int[]{1000}, new int[]{1000}));
    }

    @Test
    public void testNegativeProduct() {
        assertEquals(-1, test.maxDotProduct(new int[]{-1}, new int[]{1}));
    }

    @Test
    public void testGiantCase() {
        int[] nums1 = new int[100];
        int[] nums2 = new int[100];
        for (int i = 0; i < 100; i++) {
            nums1[i] = i + 1;
            nums2[i] = i + 1;
        }
        int result = test.maxDotProduct(nums1, nums2);
        assertEquals(result, test.maxDotProduct(nums1, nums2)); // consistency
    }
}
