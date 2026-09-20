package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class SearchRange_34Test {

    private final SearchRange_34 test = new SearchRange_34();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{3, 4}, test.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        assertArrayEquals(new int[]{0, 0}, test.searchRange(new int[]{1}, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(new int[]{}, 0));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{2, 7}, test.searchRange(new int[]{1, 2, 3, 3, 3, 3, 3, 3, 4, 5}, 3));
    }

    @Test
    public void testTargetAtStart() {
        assertArrayEquals(new int[]{0, 2}, test.searchRange(new int[]{2, 2, 2, 3, 4}, 2));
    }

    @Test
    public void testTargetAtEnd() {
        assertArrayEquals(new int[]{3, 4}, test.searchRange(new int[]{1, 2, 3, 9, 9}, 9));
    }

    @Test
    public void testTargetAbsentBeforeAndAfterArray() {
        int[] values = {4, 6, 8, 10};
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(values, 1));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(values, 12));
    }

    @Test
    public void testTargetAbsentBetweenDistinctValues() {
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(new int[]{-5, -2, 0, 4, 9}, 3));
    }

    @Test
    public void testRunsAtBothArrayBoundaries() {
        int[] values = {2, 2, 2, 3, 4, 4, 4};
        assertArrayEquals(new int[]{0, 2}, test.searchRange(values, 2));
        assertArrayEquals(new int[]{4, 6}, test.searchRange(values, 4));
    }

    @Test
    public void testTargetWithOneOccurrenceAmongDuplicates() {
        assertArrayEquals(new int[]{4, 4}, test.searchRange(new int[]{1, 1, 2, 2, 5, 7, 7, 9}, 5));
    }

    @Test
    public void testNegativeValuesAndZero() {
        int[] values = {-9, -9, -4, -1, -1, 0, 3, 3};
        assertArrayEquals(new int[]{0, 1}, test.searchRange(values, -9));
        assertArrayEquals(new int[]{3, 4}, test.searchRange(values, -1));
        assertArrayEquals(new int[]{5, 5}, test.searchRange(values, 0));
    }

    @Test
    public void testAllElementsAreTarget() {
        assertArrayEquals(new int[]{0, 4}, test.searchRange(new int[]{6, 6, 6, 6, 6}, 6));
    }

    @Test
    public void testSingleElementNotFound() {
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(new int[]{6}, 3));
    }

    @Test
    public void testTwoElementsTargetFirst() {
        assertArrayEquals(new int[]{0, 0}, test.searchRange(new int[]{3, 5}, 3));
    }

    @Test
    public void testTwoElementsTargetSecond() {
        assertArrayEquals(new int[]{1, 1}, test.searchRange(new int[]{3, 5}, 5));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i < 300 ? 1 : (i < 700 ? 2 : 3);
        }
        assertArrayEquals(new int[]{300, 699}, test.searchRange(arr, 2));
    }
    @Test
    public void testEverySmallThreeValueMultiplicity() {
        for (int negative = 0; negative <= 12; negative++) for (int zeros = 0; zeros <= 12; zeros++)
            for (int positive = 0; positive <= 12; positive++) {
                int[] values = new int[negative + zeros + positive];
                java.util.Arrays.fill(values, 0, negative, -1);
                java.util.Arrays.fill(values, negative + zeros, values.length, 1);
                assertArrayEquals(zeros == 0 ? new int[]{-1, -1} : new int[]{negative, negative + zeros - 1},
                        test.searchRange(values, 0));
            }
    }

    @Test
    public void testRepeatedIntegerExtremes() {
        int[] values = {Integer.MIN_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertArrayEquals(new int[]{0, 1}, test.searchRange(values, Integer.MIN_VALUE));
        assertArrayEquals(new int[]{3, 4}, test.searchRange(values, Integer.MAX_VALUE));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(values, -1));
    }

    @Test
    public void testGiantSingleRunBesideSingletons() {
        int[] values = new int[100000];
        Arrays.fill(values, 7);
        values[0] = 6;
        values[values.length - 1] = 8;
        assertArrayEquals(new int[]{1, 99998}, test.searchRange(values, 7));
        assertArrayEquals(new int[]{0, 0}, test.searchRange(values, 6));
        assertArrayEquals(new int[]{99999, 99999}, test.searchRange(values, 8));
    }

    @Test
    public void testNullArrayReturnsAbsentRange() {
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(null, 0));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(null, Integer.MAX_VALUE));
    }

    @Test
    public void testTargetAbsentAdjacentToDuplicateRuns() {
        int[] values = {-4, -4, -2, -2, 5, 5};
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(values, -3));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(values, 0));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(values, 6));
    }

    @Test
    public void testOfficialValueBoundaries() {
        int[] values = {-1_000_000_000, -1_000_000_000, 0, 1_000_000_000, 1_000_000_000};
        assertArrayEquals(new int[]{0, 1}, test.searchRange(values, -1_000_000_000));
        assertArrayEquals(new int[]{2, 2}, test.searchRange(values, 0));
        assertArrayEquals(new int[]{3, 4}, test.searchRange(values, 1_000_000_000));
    }

    @Test
    public void testRunsAroundOddAndEvenMidpoints() {
        int[] values = {-3, -3, -3, -2, -1, -1, 0, 1, 1, 1, 1, 2};
        assertArrayEquals(new int[]{0, 2}, test.searchRange(values, -3));
        assertArrayEquals(new int[]{4, 5}, test.searchRange(values, -1));
        assertArrayEquals(new int[]{7, 10}, test.searchRange(values, 1));
        assertArrayEquals(new int[]{11, 11}, test.searchRange(values, 2));
    }

    @Test
    public void testInputAndReturnedArrayAreIndependent() {
        int[] values = {-2, -1, -1, 0, 3};
        int[] original = values.clone();
        int[] result = test.searchRange(values, -1);
        result[0] = 999;
        result[1] = 999;

        assertArrayEquals(original, values);
        assertArrayEquals(new int[]{1, 2}, test.searchRange(values, -1));
    }

    @Test
    public void testRepeatedCallsDoNotLeakPreviousRange() {
        int[] values = {1, 1, 2, 3, 3, 3, 8};
        assertArrayEquals(new int[]{0, 1}, test.searchRange(values, 1));
        assertArrayEquals(new int[]{2, 2}, test.searchRange(values, 2));
        assertArrayEquals(new int[]{-1, -1}, test.searchRange(values, 7));
        assertArrayEquals(new int[]{3, 5}, test.searchRange(values, 3));
        assertArrayEquals(new int[]{6, 6}, test.searchRange(values, 8));
    }

    @Test
    public void testExhaustiveSmallSortedArraysAgainstDirectOracle() {
        for (int length = 0; length <= 7; length++) {
            enumerateSortedArrays(new int[length], 0, -2);
        }
    }

    @Test
    public void testSeededRandomSortedArraysAgainstDirectOracle() {
        Random random = new Random(34_2026L);
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int[] values = new int[random.nextInt(151)];
            for (int i = 0; i < values.length; i++) {
                values[i] = random.nextInt(41) - 20;
            }
            Arrays.sort(values);
            for (int target = -22; target <= 22; target++) {
                assertArrayEquals(expectedRange(values, target), test.searchRange(values, target),
                        "case=" + caseNumber + ", target=" + target);
            }
        }
    }

    /** Enumerates each non-decreasing array over {-2,-1,0,1,2} and checks every target. */
    private void enumerateSortedArrays(int[] values, int index, int nextValue) {
        if (index == values.length) {
            for (int target = -3; target <= 3; target++) {
                assertArrayEquals(expectedRange(values, target), test.searchRange(values, target));
            }
            return;
        }
        for (int value = nextValue; value <= 2; value++) {
            values[index] = value;
            enumerateSortedArrays(values, index + 1, value);
        }
    }

    /** Independent linear reference implementation used only to derive test expectations. */
    private int[] expectedRange(int[] values, int target) {
        int first = -1;
        int last = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return new int[]{first, last};
    }
}
