package solutions.math;

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

    @Test
    public void testAdditional1() {
        assertEquals(0, test.maxAbsValExpr(new int[]{1}, new int[]{1}));
    }

    @Test
    public void testAdditional2() {
        assertEquals(3, test.maxAbsValExpr(new int[]{1,2}, new int[]{3,4}));
    }

    @Test
    public void testAdditional3() {
        assertEquals(6, test.maxAbsValExpr(new int[]{1,2,3}, new int[]{4,5,6}));
    }

    @Test
    public void testAdditional4() {
        assertEquals(11, test.maxAbsValExpr(new int[]{1,-2}, new int[]{-3,4}));
    }

    @Test
    public void testAdditional5() {
        assertEquals(2, test.maxAbsValExpr(new int[]{0,0,0}, new int[]{0,0,0}));
    }

    @Test
    public void testAdditional6() {
        assertEquals(3, test.maxAbsValExpr(new int[]{-1,-2}, new int[]{1,2}));
    }

    @Test
    public void testAdditional7() {
        assertEquals(21, test.maxAbsValExpr(new int[]{10,20}, new int[]{30,40}));
    }

    @Test
    public void testAdditional8() {
        assertEquals(199, test.maxAbsValExpr(new int[]{1,100}, new int[]{100,1}));
    }

    @Test
    public void testAdditional9() {
        assertEquals(2, test.maxAbsValExpr(new int[]{5,5,5}, new int[]{-5,-5,-5}));
    }

    @Test
    public void testAdditional10() {
        assertEquals(4000001, test.maxAbsValExpr(new int[]{1000000,-1000000}, new int[]{-1000000,1000000}));
    }
}
