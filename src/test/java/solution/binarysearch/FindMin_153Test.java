package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMin_153Test {

    private final FindMin_153 test = new FindMin_153();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.findMin(new int[]{3, 4, 5, 1, 2}));
        assertEquals(0, test.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.findMin(new int[]{1}));
        assertEquals(1, test.findMin(new int[]{1, 2, 3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.findMin(new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5}));
    }

    @Test
    public void testAlreadySortedArray() {
        assertEquals(2, test.findMin(new int[]{2, 3, 4, 5, 6}));
    }

    @Test
    public void testRotationByOne() {
        assertEquals(1, test.findMin(new int[]{5, 1, 2, 3, 4}));
    }

    @Test
    public void testRotationAtEndEquivalentSorted() {
        assertEquals(1, test.findMin(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    @Test
    public void testTwoElementsRotated() {
        assertEquals(1, test.findMin(new int[]{2, 1}));
    }

    @Test
    public void testTwoElementsNotRotated() {
        assertEquals(1, test.findMin(new int[]{1, 2}));
    }

    @Test
    public void testNegativeValues() {
        assertEquals(-10, test.findMin(new int[]{0, 3, 5, -10, -4, -1}));
    }

    @Test
    public void testGiantRotatedArray() {
        int n = 200;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        int pivot = 137;
        int[] rotated = new int[n];
        int idx = 0;
        for (int i = pivot; i < n; i++) {
            rotated[idx++] = arr[i];
        }
        for (int i = 0; i < pivot; i++) {
            rotated[idx++] = arr[i];
        }
        assertEquals(1, test.findMin(rotated));
    }
}
