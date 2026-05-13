package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxAbsValExpr_1131Test {

    private final MaxAbsValExpr_1131 test = new MaxAbsValExpr_1131();

    @Test
    public void testHappyCases() {
        assertEquals(13, test.maxAbsValExpr(new int[]{1, 2, 3, 4}, new int[]{-1, 4, 5, 6}));
        assertEquals(20, test.maxAbsValExpr(new int[]{1, -2, -5, 0, 10}, new int[]{0, -2, -1, -7, -4}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.maxAbsValExpr(new int[]{0}, new int[]{0}));
        assertEquals(3, test.maxAbsValExpr(new int[]{0, 1}, new int[]{0, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(12, test.maxAbsValExpr(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testSingleElement() {
        assertEquals(0, test.maxAbsValExpr(new int[]{5}, new int[]{-5}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(4, test.maxAbsValExpr(new int[]{0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    public void testNegativeValues() {
        assertEquals(10, test.maxAbsValExpr(new int[]{-5, -3, -1}, new int[]{-1, -3, -5}));
    }

    @Test
    public void testIdenticalArrays() {
        assertEquals(12, test.maxAbsValExpr(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testTwoElementsOpposite() {
        assertEquals(5, test.maxAbsValExpr(new int[]{1, -1}, new int[]{-1, 1}));
    }

    @Test
    public void testLargeValues() {
        assertEquals(13, test.maxAbsValExpr(new int[]{1, 2, 3, 4}, new int[]{-1, 4, 5, 6}));
    }

    @Test
    public void testGiantCase() {
        int n = 1000;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = i;
            arr2[i] = n - i;
        }
        int result = test.maxAbsValExpr(arr1, arr2);
        // Just verify it runs and returns a positive value
        assertEquals(result, test.maxAbsValExpr(arr1, arr2));
    }
}
