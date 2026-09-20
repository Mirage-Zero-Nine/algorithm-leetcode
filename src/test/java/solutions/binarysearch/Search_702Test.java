package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import library.ArrayReaderHelper;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for searching a strictly increasing array through the unknown-size reader API.
 */
public class Search_702Test {

    private static final int OUT_OF_BOUNDS = Integer.MAX_VALUE;

    private final Search_702 test = new Search_702();

    @Test
    public void officialExampleFindsInteriorTarget() {
        assertSearch(new int[]{-1, 0, 3, 5, 9, 12}, 9, 4);
    }

    @Test
    public void officialExampleReturnsMinusOneForMissingTarget() {
        assertSearch(new int[]{-1, 0, 3, 5, 9, 12}, 2, -1);
    }

    @Test
    public void findsFirstElementWithoutReadingAKnownLength() {
        assertSearch(new int[]{-10_000, -1, 0, 17, 10_000}, -10_000, 0);
    }

    @Test
    public void findsElementAtInitialExpansionEndpoint() {
        assertSearch(new int[]{-8, -3, 0, 6, 11}, -3, 1);
    }

    @Test
    public void findsElementAtPowerOfTwoIndex() {
        int[] values = IntStream.range(0, 32).map(i -> i * 3 - 40).toArray();
        assertSearch(values, values[16], 16);
    }

    @Test
    public void findsElementJustAfterPowerOfTwoIndex() {
        int[] values = IntStream.range(0, 40).map(i -> i * 5 - 100).toArray();
        assertSearch(values, values[17], 17);
    }

    @Test
    public void findsLastElementOfShortArray() {
        assertSearch(new int[]{1, 4, 9, 16, 25, 36, 49}, 49, 6);
    }

    @Test
    public void returnsMinusOneForTargetBelowMinimum() {
        assertSearch(new int[]{3, 6, 9}, 1, -1);
    }

    @Test
    public void returnsMinusOneForTargetAboveMaximumAndBeyondSentinelBoundary() {
        assertSearch(new int[]{3, 6, 9}, 10_000, -1);
    }

    @Test
    public void findsOnlyElement() {
        assertSearch(new int[]{42}, 42, 0);
    }

    @Test
    public void rejectsMissingTargetInSingleton() {
        assertSearch(new int[]{42}, 41, -1);
    }

    @Test
    public void handlesNegativeValuesAndNegativeMissingTarget() {
        int[] values = {-10_000, -9999, -500, -1};
        assertSearch(values, -500, 2);
        assertSearch(values, -501, -1);
    }

    @Test
    public void handlesBothOfficialValueBoundaries() {
        int[] values = {-10_000, -1, 0, 1, 10_000};
        assertSearch(values, -10_000, 0);
        assertSearch(values, 10_000, 4);
    }

    @Test
    public void distinguishesAValueGapFromOutOfBounds() {
        int[] values = {-100, -10, 0, 10, 100};
        assertSearch(values, 1, -1);
        assertSearch(values, 99, -1);
    }

    @Test
    public void expandsAcrossSeveralEmptyReaderRanges() {
        int[] values = IntStream.range(0, 257).map(i -> i - 128).toArray();
        assertSearch(values, values[127], 127);
        assertSearch(values, values[128], 128);
        assertSearch(values, values[256], 256);
    }

    @Test
    public void searchesMaximumLengthArrayAtFirstIndex() {
        int[] values = increasingValues(10_000);
        assertSearch(values, values[0], 0);
    }

    @Test
    public void searchesMaximumLengthArrayAtMiddleIndex() {
        int[] values = increasingValues(10_000);
        assertSearch(values, values[5_432], 5_432);
    }

    @Test
    public void searchesMaximumLengthArrayAtLastIndex() {
        int[] values = increasingValues(10_000);
        assertSearch(values, values[9_999], 9_999);
    }

    @Test
    public void rejectsTargetsOutsideMaximumLengthArrayOnEitherSide() {
        int[] values = IntStream.range(0, 10_000).toArray();
        assertSearch(values, -1, -1);
        assertSearch(values, 10_000, -1);
    }

    @Test
    public void findsTargetAtTheLargestExpansionIndex() {
        int[] values = IntStream.range(0, 10_000).map(i -> i - 10_000).toArray();
        assertSearch(values, values[8_192], 8_192);
    }

    @Test
    public void repeatedQueriesOnTheSameReaderAreIndependent() {
        int[] values = {-10_000, -100, 0, 7, 100, 10_000};
        ArrayReaderHelper reader = new ArrayReaderHelper(values);

        assertEquals(5, test.search(reader, 10_000));
        assertEquals(-1, test.search(reader, 8));
        assertEquals(0, test.search(reader, -10_000));
        assertEquals(2, test.search(reader, 0));
    }

    @Test
    public void separateReadersDoNotShareState() {
        assertEquals(2, test.search(new ArrayReaderHelper(new int[]{1, 4, 9}), 9));
        assertEquals(0, test.search(new ArrayReaderHelper(new int[]{-8, -2}), -8));
        assertEquals(-1, test.search(new ArrayReaderHelper(new int[]{100, 200}), 9));
    }

    @Test
    public void doesNotModifyCallerArray() {
        int[] values = {-9, -2, 0, 6, 100};
        int[] before = values.clone();
        assertEquals(3, test.search(new ArrayReaderHelper(values), 6));
        assertArrayEquals(before, values);
    }

    @Test
    public void helperReturnsValueByIndexAndSentinelOutsideArray() {
        ArrayReaderHelper reader = new ArrayReaderHelper(new int[]{-4, 8, 15});

        assertEquals(-4, reader.get(0));
        assertEquals(15, reader.get(2));
        assertEquals(OUT_OF_BOUNDS, reader.get(-1));
        assertEquals(OUT_OF_BOUNDS, reader.get(3));
        assertEquals(OUT_OF_BOUNDS, reader.get(10_000));
    }

    @Test
    public void exhaustiveSmallArraysAgreeWithIndependentLinearOracle() {
        for (int length = 1; length <= 8; length++) {
            int[] values = IntStream.range(0, length).map(i -> i * 3 - 10).toArray();
            for (int target = -12; target <= 14; target++) {
                assertEquals(linearIndex(values, target),
                        test.search(new ArrayReaderHelper(values), target),
                        "length=" + length + ", target=" + target);
            }
        }
    }

    @Test
    public void irregularSmallArraysAgreeWithIndependentLinearOracle() {
        int[][] cases = {
                {-10_000, -9_000, -1, 0, 10_000},
                {-99, -50, -49, 7, 8, 100},
                {-5, 1, 2, 100, 101, 1_000},
                {0, 1, 10, 100, 1_000, 10_000}
        };
        for (int[] values : cases) {
            for (int target = -10_000; target <= 10_000; target += 137) {
                assertEquals(linearIndex(values, target),
                        test.search(new ArrayReaderHelper(values), target),
                        "values=" + Arrays.toString(values) + ", target=" + target);
            }
        }
    }

    @Test
    public void exactBoundaryTargetsOnMaximumLengthSequences() {
        int[] negativeValues = IntStream.range(0, 10_000).map(i -> i - 10_000).toArray();
        int[] positiveValues = increasingValues(10_000);

        assertEquals(0, test.search(new ArrayReaderHelper(negativeValues), -10_000));
        assertEquals(9_999, test.search(new ArrayReaderHelper(negativeValues), -1));
        assertEquals(9_999, test.search(new ArrayReaderHelper(positiveValues), 10_000));
    }

    private void assertSearch(int[] values, int target, int expectedIndex) {
        assertEquals(expectedIndex, test.search(new ArrayReaderHelper(values), target),
                "target=" + target + ", values=" + Arrays.toString(values));
    }

    private static int[] increasingValues(int length) {
        return IntStream.rangeClosed(1, length).toArray();
    }

    private static int linearIndex(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
