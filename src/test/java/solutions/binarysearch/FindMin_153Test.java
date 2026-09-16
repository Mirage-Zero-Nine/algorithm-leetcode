package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.Test;

public class FindMin_153Test {

    private final FindMin_153 test = new FindMin_153();

    @Test
    public void testTypicalRotatedArrays() {
        assertEquals(1, test.findMin(new int[]{3, 4, 5, 1, 2}));
        assertEquals(0, test.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    @Test
    public void testSingleElementArray() {
        assertEquals(1, test.findMin(new int[]{1}));
    }

    @Test
    public void testTwoElementRotatedArray() {
        assertEquals(1, test.findMin(new int[]{2, 1}));
    }

    @Test
    public void testTwoElementSortedArray() {
        assertEquals(1, test.findMin(new int[]{1, 2}));
    }

    @Test
    public void testAlreadySortedArray() {
        assertEquals(2, test.findMin(new int[]{2, 3, 4, 5, 6}));
        assertEquals(-5, test.findMin(new int[]{-5, -2, 0, 3, 9}));
    }

    @Test
    public void testRotationByOne() {
        assertEquals(1, test.findMin(new int[]{5, 1, 2, 3, 4}));
    }

    @Test
    public void testMinimumAtRightBoundary() {
        assertEquals(1, test.findMin(new int[]{2, 3, 4, 5, 1}));
    }

    @Test
    public void testLargerRotation() {
        assertEquals(1, test.findMin(new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5}));
    }

    @Test
    public void testNegativeValues() {
        assertEquals(-10, test.findMin(new int[]{0, 3, 5, -10, -4, -1}));
    }

    @Test
    public void testProblemBoundaryValues() {
        assertEquals(-5000, test.findMin(new int[]{4999, 5000, -5000, -4999, 0}));
    }

    @Test
    public void testDoesNotMutateInput() {
        int[] input = {7, 8, 10, 1, 3, 5};
        int[] original = input.clone();

        assertEquals(1, test.findMin(input));
        assertArrayEquals(original, input);
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        assertEquals(3, test.findMin(new int[]{8, 9, 3, 4, 5, 6, 7}));
        assertEquals(-4, test.findMin(new int[]{-1, 0, 2, -4, -3, -2}));
    }

    @Test
    public void testAllRotationsAcrossSmallLengths() {
        for (int n = 1; n <= 64; n++) {
            for (int pivot = 0; pivot < n; pivot++) {
                int[] values = new int[n];
                for (int i = 0; i < n; i++) {
                    values[i] = ((i + pivot) % n) * 5 - 100;
                }
                assertEquals(-100, test.findMin(values), "length=" + n + ", pivot=" + pivot);
            }
        }
    }

    @Test
    public void testDefensiveValidationForOutOfContractInputs() {
        assertThrows(InvalidParameterException.class, () -> test.findMin(null));
        assertThrows(InvalidParameterException.class, () -> test.findMin(new int[]{}));
    }

    @Test
    public void testMaximumSizeRotationWithMinimumNearStart() {
        int[] values = new int[5000];
        for (int i = 0; i < values.length; i++) {
            values[i] = ((i + values.length - 2) % values.length) - 5000;
        }

        assertEquals(-5000, test.findMin(values));
    }
}
