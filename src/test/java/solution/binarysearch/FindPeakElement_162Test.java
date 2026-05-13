package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FindPeakElement_162Test {

    private final FindPeakElement_162 test = new FindPeakElement_162();

    @Test
    public void testHappyCases() {
        int result = test.findPeakElement(new int[]{1, 2, 3, 1});
        assertEquals(2, result);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.findPeakElement(new int[]{1}));
        int result = test.findPeakElement(new int[]{1, 2});
        assertEquals(1, result);
    }

    @Test
    public void testLargeCase() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1};
        int result = test.findPeakElement(arr);
        assertTrue(result > 0 && result < arr.length - 1 && arr[result] > arr[result - 1] && arr[result] > arr[result + 1]);
    }

    @Test
    public void testStrictlyIncreasingArray() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        assertEquals(arr.length - 1, test.findPeakElement(arr));
    }

    @Test
    public void testStrictlyDecreasingArray() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        assertEquals(0, test.findPeakElement(arr));
    }

    @Test
    public void testMultiplePeaksReturnsAnyValidPeak() {
        int[] arr = new int[]{1, 3, 2, 4, 1};
        int idx = test.findPeakElement(arr);
        assertTrue(isPeak(arr, idx));
    }

    @Test
    public void testTwoElementsDescending() {
        assertEquals(0, test.findPeakElement(new int[]{2, 1}));
    }

    @Test
    public void testNegativeValues() {
        int[] arr = new int[]{-5, -2, -3, -1, -4};
        int idx = test.findPeakElement(arr);
        assertTrue(isPeak(arr, idx));
    }

    @Test
    public void testPeakAtBoundaryLeft() {
        int[] arr = new int[]{10, 2, 1};
        assertEquals(0, test.findPeakElement(arr));
    }

    @Test
    public void testGiantWaveArray() {
        int n = 201;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (i <= 100) ? i : 200 - i;
        }
        int idx = test.findPeakElement(arr);
        assertEquals(100, idx);
    }

    private boolean isPeak(int[] arr, int i) {
        boolean leftOk = (i == 0) || arr[i] > arr[i - 1];
        boolean rightOk = (i == arr.length - 1) || arr[i] > arr[i + 1];
        return leftOk && rightOk;
    }
}
