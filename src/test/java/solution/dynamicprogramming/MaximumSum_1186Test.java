package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSum_1186Test {

    private final MaximumSum_1186 test = new MaximumSum_1186();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.maximumSum(new int[]{1, -2, 0, 3}));
        assertEquals(3, test.maximumSum(new int[]{1, -2, -2, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maximumSum(new int[]{-1, -1, -1, -1}));
        assertEquals(1, test.maximumSum(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(14, test.maximumSum(new int[]{1, 2, 3, -4, 5, -6, 7}));
    }

    @Test
    public void testAllPositive() {
        assertEquals(15, test.maximumSum(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testSingleNegativeInMiddle() {
        assertEquals(9, test.maximumSum(new int[]{3, -100, 2, 4}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(4, test.maximumSum(new int[]{1, -2, 3}));
    }

    @Test
    public void testDeleteMakesLargerSum() {
        assertEquals(10, test.maximumSum(new int[]{1, 2, 3, -50, 4}));
    }

    @Test
    public void testAllNegativePickLeast() {
        assertEquals(-1, test.maximumSum(new int[]{-5, -1, -3}));
    }

    @Test
    public void testSingleElement() {
        assertEquals(-5, test.maximumSum(new int[]{-5}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = 1;
        }
        arr[5000] = -100;
        // Delete the -100, sum = 9999
        assertEquals(9999, test.maximumSum(arr));
    }
}
