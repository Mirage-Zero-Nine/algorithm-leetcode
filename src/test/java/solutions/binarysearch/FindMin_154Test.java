package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMin_154Test {

    private final FindMin_154 test = new FindMin_154();

    @Test
    public void testHappyCases() {
        assertEquals(0, test.findMin(new int[]{2, 2, 2, 0, 1}));
        assertEquals(0, test.findMin(new int[]{2, 5, 6, 7, 0, 0, 1, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.findMin(new int[]{1}));
        assertEquals(0, test.findMin(new int[]{0, 0, 0, 0, 0, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.findMin(new int[]{3, 3, 3, 3, 1, 3, 3}));
    }

    @Test
    public void testAllElementsEqual() {
        assertEquals(5, test.findMin(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testSortedWithDuplicates() {
        assertEquals(1, test.findMin(new int[]{1, 1, 2, 2, 3, 3}));
    }

    @Test
    public void testRotationByOneWithDuplicates() {
        assertEquals(1, test.findMin(new int[]{3, 1, 1, 2, 2, 3}));
    }

    @Test
    public void testPivotNearEnd() {
        assertEquals(0, test.findMin(new int[]{1, 1, 1, 1, 0, 1}));
    }

    @Test
    public void testTwoElementsDuplicate() {
        assertEquals(2, test.findMin(new int[]{2, 2}));
    }

    @Test
    public void testNegativeValuesWithDuplicates() {
        assertEquals(-5, test.findMin(new int[]{-2, -1, 0, -5, -5, -3}));
    }

    @Test
    public void testGiantArrayWithDuplicates() {
        int n = 200;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i / 2;
        }
        int pivot = 133;
        int[] rotated = new int[n];
        int idx = 0;
        for (int i = pivot; i < n; i++) {
            rotated[idx++] = arr[i];
        }
        for (int i = 0; i < pivot; i++) {
            rotated[idx++] = arr[i];
        }
        assertEquals(0, test.findMin(rotated));
    }
}
