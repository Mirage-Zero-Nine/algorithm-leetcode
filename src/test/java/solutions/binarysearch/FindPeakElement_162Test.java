package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/** Tests the single binary-search approach using the problem's peak invariant. */
public class FindPeakElement_162Test {

    private final FindPeakElement_162 test = new FindPeakElement_162();

    @Test
    public void testOfficialExampleWithOnePeak() {
        int[] values = {1, 2, 3, 1};
        assertEquals(2, test.findPeakElement(values));
    }

    @Test
    public void testOfficialExampleAllowsEitherPeak() {
        assertPeak(new int[]{1, 2, 1, 3, 5, 6, 4});
    }

    @Test
    public void testSingletonPositiveValue() {
        assertEquals(0, test.findPeakElement(new int[]{7}));
    }

    @Test
    public void testSingletonIntegerMinimum() {
        assertEquals(0, test.findPeakElement(new int[]{Integer.MIN_VALUE}));
    }

    @Test
    public void testTwoElementIncreasingArray() {
        assertEquals(1, test.findPeakElement(new int[]{-4, 9}));
    }

    @Test
    public void testTwoElementDecreasingArray() {
        assertEquals(0, test.findPeakElement(new int[]{9, -4}));
    }

    @Test
    public void testStrictlyIncreasingArray() {
        int[] values = {-8, -3, 0, 4, 12};
        assertEquals(values.length - 1, test.findPeakElement(values));
    }

    @Test
    public void testStrictlyDecreasingArray() {
        int[] values = {12, 4, 0, -3, -8};
        assertEquals(0, test.findPeakElement(values));
    }

    @Test
    public void testSingleInteriorPeak() {
        int[] values = {1, 6, 2};
        assertEquals(1, test.findPeakElement(values));
    }

    @Test
    public void testPeakAtLeftBoundary() {
        assertEquals(0, test.findPeakElement(new int[]{10, 2, 1}));
    }

    @Test
    public void testPeakAtRightBoundary() {
        int[] values = {1, 2, 10};
        assertEquals(values.length - 1, test.findPeakElement(values));
    }

    @Test
    public void testMultipleInteriorPeaksReturnAnyValidPeak() {
        assertPeak(new int[]{1, 3, 2, 4, 1});
    }

    @Test
    public void testPeaksAtBothBoundaries() {
        assertPeak(new int[]{5, 1, 2, 1, 5});
    }

    @Test
    public void testNegativeValuesAndInteriorValleys() {
        assertPeak(new int[]{-10, -3, -8, -1, -5});
    }

    @Test
    public void testRepeatedNonAdjacentValues() {
        assertPeak(new int[]{0, 4, 0, 4, 0, 3});
    }

    @Test
    public void testIntegerExtremeValues() {
        assertEquals(1, test.findPeakElement(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE,
                Integer.MIN_VALUE}));
        assertEquals(0, test.findPeakElement(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
        assertEquals(1, test.findPeakElement(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }

    @Test
    public void testSteepPeaksWithLargeGaps() {
        assertPeak(new int[]{Integer.MIN_VALUE, -1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE,
                Integer.MIN_VALUE + 2});
    }

    @Test
    public void testExhaustiveSmallArraysWithDistinctNeighbors() {
        // Enumerate every length-seven array over {-1, 0, 1} that meets nums[i] != nums[i+1].
        for (int encoded = 0; encoded < 2187; encoded++) {
            int[] values = new int[7];
            int remaining = encoded;
            boolean validInput = true;
            for (int i = 0; i < values.length; i++) {
                values[i] = remaining % 3 - 1;
                remaining /= 3;
                if (i > 0 && values[i] == values[i - 1]) {
                    validInput = false;
                }
            }
            if (validInput) {
                assertPeak(values);
            }
        }
    }

    @Test
    public void testSeededRandomArraysAgainstPeakOracle() {
        Random random = new Random(162L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int length = 1 + random.nextInt(1000);
            int[] values = new int[length];
            for (int i = 0; i < length; i++) {
                int candidate;
                do {
                    candidate = random.nextInt(2001) - 1000;
                } while (i > 0 && candidate == values[i - 1]);
                values[i] = candidate;
            }
            assertPeak(values);
        }
    }

    @Test
    public void testMaximumLengthStrictlyIncreasingArray() {
        int[] values = new int[1000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i - 500;
        }
        assertEquals(999, test.findPeakElement(values));
    }

    @Test
    public void testMaximumLengthStrictlyDecreasingArray() {
        int[] values = new int[1000];
        for (int i = 0; i < values.length; i++) {
            values[i] = 500 - i;
        }
        assertEquals(0, test.findPeakElement(values));
    }

    @Test
    public void testMaximumLengthUnimodalArray() {
        int[] values = new int[1000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i <= 500 ? i : 1000 - i;
        }
        assertEquals(500, test.findPeakElement(values));
    }

    @Test
    public void testMaximumLengthAlternatingArray() {
        int[] values = new int[1000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i % 2 == 0 ? -1_000_000 - i : 1_000_000 + i;
        }
        assertPeak(values);
    }

    @Test
    public void testInputIsNotMutated() {
        int[] values = {4, 1, 7, 2, 6, 0};
        int[] original = values.clone();
        assertPeak(values);
        assertArrayEquals(original, values);
    }

    @Test
    public void testSameInstanceCanBeReusedWithoutStateLeakage() {
        assertPeak(new int[]{1, 3, 2, 5, 4});
        assertEquals(0, test.findPeakElement(new int[]{9, 4, 2, 1}));
        assertEquals(4, test.findPeakElement(new int[]{-8, -4, -2, 0, 5}));
        assertPeak(new int[]{3, 1, 4, 2, 5, 0});
    }

    private void assertPeak(int[] values) {
        int index = test.findPeakElement(values);
        assertTrue(index >= 0 && index < values.length,
                () -> "index out of range: " + index + " for " + Arrays.toString(values));
        assertTrue(isPeak(values, index),
                () -> "index " + index + " is not a peak in " + Arrays.toString(values));
    }

    private boolean isPeak(int[] values, int index) {
        boolean greaterThanLeft = index == 0 || values[index] > values[index - 1];
        boolean greaterThanRight = index == values.length - 1 || values[index] > values[index + 1];
        return greaterThanLeft && greaterThanRight;
    }
}
