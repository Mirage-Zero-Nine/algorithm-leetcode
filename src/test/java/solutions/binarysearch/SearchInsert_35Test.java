package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/** Tests the lower-bound contract of {@link SearchInsert_35}. */
public class SearchInsert_35Test {

    private final SearchInsert_35 test = new SearchInsert_35();

    @Test
    public void testOfficialExampleTargetFound() {
        assertEquals(2, test.searchInsert(new int[]{1, 3, 5, 6}, 5));
    }

    @Test
    public void testOfficialExampleInsertBetween() {
        assertEquals(1, test.searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    @Test
    public void testOfficialExampleInsertAfterLast() {
        assertEquals(4, test.searchInsert(new int[]{1, 3, 5, 6}, 7));
    }

    @Test
    public void testInsertBeforeFirst() {
        assertEquals(0, test.searchInsert(new int[]{1, 3, 5, 6}, 0));
        assertEquals(0, test.searchInsert(new int[]{10, 20, 30}, -100));
    }

    @Test
    public void testExactFirstElement() {
        assertEquals(0, test.searchInsert(new int[]{2, 4, 6, 8}, 2));
    }

    @Test
    public void testInsertIntoFirstGap() {
        assertEquals(1, test.searchInsert(new int[]{2, 4, 6, 8}, 3));
    }

    @Test
    public void testExactInteriorElement() {
        assertEquals(2, test.searchInsert(new int[]{2, 4, 6, 8}, 6));
    }

    @Test
    public void testInsertIntoInteriorGap() {
        assertEquals(3, test.searchInsert(new int[]{2, 4, 6, 8}, 7));
    }

    @Test
    public void testExactLastElement() {
        assertEquals(3, test.searchInsert(new int[]{2, 4, 6, 8}, 8));
    }

    @Test
    public void testInsertAfterLast() {
        assertEquals(4, test.searchInsert(new int[]{2, 4, 6, 8}, 9));
        assertEquals(4, test.searchInsert(new int[]{1, 3, 5, 6}, Integer.MAX_VALUE));
    }

    @Test
    public void testSingletonFound() {
        assertEquals(0, test.searchInsert(new int[]{8}, 8));
    }

    @Test
    public void testSingletonInsertBefore() {
        assertEquals(0, test.searchInsert(new int[]{8}, 3));
    }

    @Test
    public void testSingletonInsertAfter() {
        assertEquals(1, test.searchInsert(new int[]{8}, 10));
    }

    @Test
    public void testTwoElementsMiddleInsert() {
        assertEquals(1, test.searchInsert(new int[]{2, 6}, 4));
    }

    @Test
    public void testTwoElementsBothExactPositions() {
        int[] values = {2, 6};
        assertEquals(0, test.searchInsert(values, 2));
        assertEquals(1, test.searchInsert(values, 6));
    }

    @Test
    public void testNegativeAndPositiveValues() {
        int[] values = {-20, -10, -1, 0, 7, 18};
        assertEquals(0, test.searchInsert(values, -21));
        assertEquals(2, test.searchInsert(values, -5));
        assertEquals(3, test.searchInsert(values, 0));
        assertEquals(5, test.searchInsert(values, 10));
    }

    @Test
    public void testIntegerExtremesAndInternalGaps() {
        int[] values = {Integer.MIN_VALUE, -1, 1, Integer.MAX_VALUE};
        int[] targets = {
            Integer.MIN_VALUE, Integer.MIN_VALUE + 1, -1, 0, 1, 2, Integer.MAX_VALUE
        };
        int[] expected = {0, 1, 1, 2, 2, 3, 3};
        for (int i = 0; i < targets.length; i++) {
            assertEquals(expected[i], test.searchInsert(values, targets[i]),
                    "target=" + targets[i]);
        }
    }

    @Test
    public void testOfficialValueBoundaries() {
        int[] values = {-10_000, -5_000, 0, 5_000, 10_000};
        assertEquals(0, test.searchInsert(values, -10_000));
        assertEquals(1, test.searchInsert(values, -9_999));
        assertEquals(2, test.searchInsert(values, -1));
        assertEquals(4, test.searchInsert(values, 9_999));
        assertEquals(4, test.searchInsert(values, 10_000));
    }

    @Test
    public void testLargeInternalGaps() {
        int[] values = {Integer.MIN_VALUE, -1_000_000, 0, 1_000_000, Integer.MAX_VALUE};
        assertEquals(1, test.searchInsert(values, Integer.MIN_VALUE + 1));
        assertEquals(2, test.searchInsert(values, -999_999));
        assertEquals(3, test.searchInsert(values, 1));
        assertEquals(4, test.searchInsert(values, 1_000_001));
    }

    @Test
    public void testExhaustiveSmallDistinctSortedArrays() {
        int minValue = -3;
        int maxValue = 3;

        for (int mask = 1; mask < (1 << (maxValue - minValue + 1)); mask++) {
            int[] values = valuesForMask(mask, minValue, maxValue);
            for (int target = minValue - 1; target <= maxValue + 1; target++) {
                assertEquals(linearInsertionIndex(values, target), test.searchInsert(values, target),
                        "values=" + Arrays.toString(values) + ", target=" + target);
            }
        }
    }

    @Test
    public void testEveryGapInModerateSortedArray() {
        int[] values = new int[1_001];
        for (int i = 0; i < values.length; i++) {
            values[i] = 2 * i - 1_000;
        }

        for (int target = -1_001; target <= 1_001; target++) {
            assertEquals(linearInsertionIndex(values, target), test.searchInsert(values, target),
                    "target=" + target);
        }
    }

    @Test
    public void testOfficialMaximumLength() {
        int[] values = new int[10_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = -10_000 + i;
        }

        assertEquals(0, test.searchInsert(values, -10_000));
        assertEquals(1, test.searchInsert(values, -9_999));
        assertEquals(5_000, test.searchInsert(values, -5_000));
        assertEquals(10_000, test.searchInsert(values, 0));
        assertEquals(10_000, test.searchInsert(values, 10_000));
    }

    @Test
    public void testMaximumLengthEverySampledGapAgainstOracle() {
        int[] values = new int[10_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = -10_000 + 2 * i;
        }

        for (int target = -10_000; target <= 10_000; target += 137) {
            assertEquals(linearInsertionIndex(values, target), test.searchInsert(values, target),
                    "target=" + target);
        }
    }

    @Test
    public void testInputIsNotModified() {
        int[] values = {-19, -7, 4, 11, 40};
        int[] original = values.clone();

        assertEquals(2, test.searchInsert(values, -5));
        assertArrayEquals(original, values);
    }

    @Test
    public void testRepeatedCallsAreIndependent() {
        assertEquals(2, test.searchInsert(new int[]{1, 3, 5, 7}, 5));
        assertEquals(0, test.searchInsert(new int[]{10, 20, 30}, -4));
        assertEquals(3, test.searchInsert(new int[]{10, 20, 30}, 31));
        assertEquals(1, test.searchInsert(new int[]{-5, 0, 5}, -1));
    }

    @Test
    public void testSeededRandomArraysAgainstIndependentLowerBound() {
        Random random = new Random(35_2026L);
        for (int caseNumber = 0; caseNumber < 300; caseNumber++) {
            int length = 1 + random.nextInt(100);
            int[] values = new int[length];
            int current = -10_000 + random.nextInt(16_001);
            for (int i = 0; i < values.length; i++) {
                values[i] = current;
                current += 1 + random.nextInt(40);
            }

            for (int query = 0; query < 12; query++) {
                int target = -10_000 + random.nextInt(20_001);
                assertEquals(linearInsertionIndex(values, target), test.searchInsert(values, target),
                        "case=" + caseNumber + ", values=" + Arrays.toString(values)
                                + ", target=" + target);
            }
        }
    }

    @Test
    public void testLowerBoundInvariantAtEachPosition() {
        int[] values = {-100, -50, -10, 0, 10, 50, 100};
        for (int target = -110; target <= 110; target++) {
            int actual = test.searchInsert(values, target);
            assertEquals(linearInsertionIndex(values, target), actual, "target=" + target);
            if (actual > 0) {
                org.junit.jupiter.api.Assertions.assertTrue(values[actual - 1] < target,
                        "predecessor must be less than target");
            }
            if (actual < values.length) {
                org.junit.jupiter.api.Assertions.assertTrue(values[actual] >= target,
                        "insertion element must not be less than target");
            }
        }
    }

    private int[] valuesForMask(int mask, int minValue, int maxValue) {
        int[] values = new int[Integer.bitCount(mask)];
        int index = 0;
        for (int value = minValue; value <= maxValue; value++) {
            if ((mask & (1 << (value - minValue))) != 0) {
                values[index++] = value;
            }
        }
        return values;
    }

    private int linearInsertionIndex(int[] values, int target) {
        for (int index = 0; index < values.length; index++) {
            if (values[index] >= target) {
                return index;
            }
        }
        return values.length;
    }
}
