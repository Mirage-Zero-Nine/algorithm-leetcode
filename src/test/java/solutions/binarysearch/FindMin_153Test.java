package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.Test;

/** Contract tests for the binary-search solution to LeetCode 153. */
public class FindMin_153Test {

    private final FindMin_153 solution = new FindMin_153();

    @Test
    public void testOfficialExampleOne() {
        assertMinimum(new int[]{3, 4, 5, 1, 2});
    }

    @Test
    public void testOfficialExampleTwo() {
        assertMinimum(new int[]{4, 5, 6, 7, 0, 1, 2});
    }

    @Test
    public void testOfficialExampleThreeAlreadySorted() {
        assertMinimum(new int[]{11, 13, 15, 17});
    }

    @Test
    public void testSingletonWithMinimumValue() {
        assertMinimum(new int[]{-5000});
    }

    @Test
    public void testSingletonWithMaximumValue() {
        assertMinimum(new int[]{5000});
    }

    @Test
    public void testTwoElementSortedArray() {
        assertMinimum(new int[]{1, 2});
    }

    @Test
    public void testTwoElementRotatedArray() {
        assertMinimum(new int[]{2, 1});
    }

    @Test
    public void testThreeElementRotationAtEveryPivot() {
        assertMinimum(new int[]{1, 2, 3});
        assertMinimum(new int[]{2, 3, 1});
        assertMinimum(new int[]{3, 1, 2});
    }

    @Test
    public void testAlreadySortedNegativeValues() {
        assertMinimum(new int[]{-5000, -100, -1, 0, 4999});
    }

    @Test
    public void testRotationByOnePlacesMinimumAtIndexOne() {
        assertMinimum(new int[]{5000, -5000, -4999, -1, 0, 4999});
    }

    @Test
    public void testRotationWithMinimumAtRightBoundary() {
        assertMinimum(new int[]{-4, -3, -2, -1, -5});
    }

    @Test
    public void testRotationWithMinimumAtLeftBoundary() {
        assertMinimum(new int[]{-5, -4, -3, -2, -1});
    }

    @Test
    public void testPivotImmediatelyBeforeMidpoint() {
        assertMinimum(new int[]{40, 50, 60, 70, 10, 20, 30});
    }

    @Test
    public void testPivotImmediatelyAfterMidpoint() {
        assertMinimum(new int[]{50, 60, 70, 10, 20, 30, 40});
    }

    @Test
    public void testValuesAroundZero() {
        assertMinimum(new int[]{-1, 0, 4, 9, -7, -3});
    }

    @Test
    public void testOfficialValueBoundariesAndLargeJump() {
        assertMinimum(new int[]{-1, 0, 1, 5000, -5000, -4999});
    }

    @Test
    public void testJavaIntegerBoundariesSupportedByClassContract() {
        assertMinimum(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, -1, 0, 1});
    }

    @Test
    public void testAllRotationsForLengthFour() {
        int[] sorted = {-8, -3, 2, 11};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            assertMinimum(leftRotation(sorted, pivot));
        }
    }

    @Test
    public void testAllRotationsAcrossSmallLengthsAgainstLinearOracle() {
        for (int length = 1; length <= 64; length++) {
            int[] sorted = new int[length];
            for (int i = 0; i < length; i++) {
                sorted[i] = -5000 + i * 3;
            }
            for (int pivot = 0; pivot < length; pivot++) {
                int[] rotated = leftRotation(sorted, pivot);
                assertEquals(linearMinimum(rotated), solution.findMin(rotated),
                        "length=" + length + ", pivot=" + pivot);
            }
        }
    }

    @Test
    public void testAdversarialRotationsAroundBinarySearchDecisions() {
        assertMinimum(new int[]{90, 100, 110, 120, 130, 10, 20, 30, 40, 50, 60, 70, 80});
        assertMinimum(new int[]{80, 90, 100, 110, 120, 130, 10, 20, 30, 40, 50, 60, 70});
        assertMinimum(new int[]{70, 80, 90, 100, 110, 120, 130, 10, 20, 30, 40, 50, 60});
    }

    @Test
    public void testMaximumSizeWithMinimumAtLastIndex() {
        int[] values = sortedValues(5000, -5000);
        int[] rotated = leftRotation(values, 1);
        assertEquals(-5000, solution.findMin(rotated));
    }

    @Test
    public void testMaximumSizeWithMiddlePivot() {
        int[] values = sortedValues(5000, -5000);
        int[] rotated = leftRotation(values, 2500);
        assertEquals(-5000, solution.findMin(rotated));
        assertEquals(-5000, linearMinimum(rotated));
    }

    @Test
    public void testMaximumSizeWithMinimumAtIndexOne() {
        int[] values = sortedValues(5000, -5000);
        int[] rotated = leftRotation(values, 4999);
        assertEquals(-5000, solution.findMin(rotated));
    }

    @Test
    public void testInputIsNotMutated() {
        int[] input = {7, 8, 10, 1, 3, 5};
        int[] original = input.clone();

        assertEquals(linearMinimum(input), solution.findMin(input));
        assertArrayEquals(original, input);
    }

    @Test
    public void testRepeatedCallsOnOneInstanceAreIndependent() {
        assertMinimum(new int[]{8, 9, 3, 4, 5, 6, 7});
        assertMinimum(new int[]{-1, 0, 2, -4, -3, -2});
        assertMinimum(new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5});
    }

    @Test
    public void testNullInputIsRejectedAsDocumented() {
        assertThrows(InvalidParameterException.class, () -> solution.findMin(null));
    }

    @Test
    public void testEmptyInputIsRejectedAsDocumented() {
        assertThrows(InvalidParameterException.class, () -> solution.findMin(new int[]{}));
    }

    private void assertMinimum(int[] values) {
        assertEquals(linearMinimum(values), solution.findMin(values),
                "input=" + java.util.Arrays.toString(values));
    }

    private static int linearMinimum(int[] values) {
        int minimum = values[0];
        for (int value : values) {
            minimum = Math.min(minimum, value);
        }
        return minimum;
    }

    private static int[] sortedValues(int length, int firstValue) {
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = firstValue + i;
        }
        return values;
    }

    /** Returns the left rotation used by the problem statement without mutating the source. */
    private static int[] leftRotation(int[] sorted, int pivot) {
        int[] rotated = new int[sorted.length];
        for (int i = 0; i < sorted.length; i++) {
            rotated[i] = sorted[(i + pivot) % sorted.length];
        }
        return rotated;
    }
}
