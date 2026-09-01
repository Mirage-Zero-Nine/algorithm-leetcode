package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
    public void testExhaustiveSmallArrays() {
        // The problem permits lengths from 1 to 500 and values from -1000 to 1000.
        // Exhaustively cover every array of lengths 1..3 over a smaller value domain.
        List<int[]> arrays = allArrays(new int[]{-2, -1, 0, 1, 2}, 3);

        for (int[] nums1 : arrays) {
            for (int[] nums2 : arrays) {
                assertEquals(bruteForceMaxDotProduct(nums1, nums2),
                        test.maxDotProduct(nums1, nums2),
                        "nums1=" + toString(nums1) + ", nums2=" + toString(nums2));
            }
        }
    }

    private List<int[]> allArrays(int[] values, int maxLength) {
        List<int[]> arrays = new ArrayList<>();
        for (int length = 1; length <= maxLength; length++) {
            addArrays(arrays, new int[length], 0, values);
        }
        return arrays;
    }

    private void addArrays(List<int[]> arrays, int[] current, int index, int[] values) {
        if (index == current.length) {
            arrays.add(current.clone());
            return;
        }
        for (int value : values) {
            current[index] = value;
            addArrays(arrays, current, index + 1, values);
        }
    }

    private int bruteForceMaxDotProduct(int[] nums1, int[] nums2) {
        int best = Integer.MIN_VALUE;
        int masks1 = 1 << nums1.length;
        int masks2 = 1 << nums2.length;

        for (int mask1 = 1; mask1 < masks1; mask1++) {
            int length1 = Integer.bitCount(mask1);
            for (int mask2 = 1; mask2 < masks2; mask2++) {
                if (length1 != Integer.bitCount(mask2)) {
                    continue;
                }

                int dotProduct = 0;
                int index1 = 0;
                int index2 = 0;
                for (int selected = 0; selected < length1; selected++) {
                    while ((mask1 & (1 << index1)) == 0) {
                        index1++;
                    }
                    while ((mask2 & (1 << index2)) == 0) {
                        index2++;
                    }
                    dotProduct += nums1[index1++] * nums2[index2++];
                }
                best = Math.max(best, dotProduct);
            }
        }
        return best;
    }

    private String toString(int[] values) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(values[i]);
        }
        return result.append(']').toString();
    }
}
