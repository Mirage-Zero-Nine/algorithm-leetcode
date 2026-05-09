package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FindPeakElement162Test {

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
}
