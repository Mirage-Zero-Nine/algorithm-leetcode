package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

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
@Test
    public void testAllRotationsAcrossSmallLengths() {
        for (int n = 1; n <= 64; n++) for (int pivot = 0; pivot < n; pivot++) {
            int[] values = new int[n];
            for (int i = 0; i < n; i++) values[i] = ((i + pivot) % n) / 3 - 100;
            assertEquals(-100, test.findMin(values), "length=" + n + ", pivot=" + pivot);
        }
    }

    @Test
    public void testRotationContainingIntegerExtremes() {
        assertEquals(Integer.MIN_VALUE, test.findMin(
                new int[]{0, 1, Integer.MAX_VALUE, Integer.MIN_VALUE, -1}));
    }

    @Test
    public void testGiantRotationAtPenultimatePosition() {
        int[] values = new int[100000];
        for (int i = 0; i < values.length; i++) values[i] = ((i + 99998) % values.length) / 5 - 50000;
        assertEquals(-50000, test.findMin(values));
    }

    @Test
    public void testOfficialExamples() {
        assertEquals(1, test.findMin(new int[]{1, 3, 5}));
        assertEquals(0, test.findMin(new int[]{2, 2, 2, 0, 1}));
    }

    @Test
    public void testUnrotatedDistinctArray() {
        assertEquals(-5, test.findMin(new int[]{-5, -2, 0, 4, 9}));
    }

    @Test
    public void testDistinctRotationsAroundEveryPivot() {
        int[] sorted = {-7, -2, 0, 3, 8, 11};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            assertMinimumFromOracle(rotate(sorted, pivot));
        }
    }

    @Test
    public void testTwoElementRotations() {
        assertEquals(1, test.findMin(new int[]{2, 1}));
        assertEquals(1, test.findMin(new int[]{1, 2}));
    }

    @Test
    public void testMinimumAtPivotWithRepeatedValues() {
        assertEquals(0, test.findMin(new int[]{4, 4, 4, 0, 0, 1, 4}));
    }

    @Test
    public void testRepeatedMinimumAtBothEndsOfRotation() {
        assertEquals(0, test.findMin(new int[]{1, 2, 4, 4, 0, 0, 0}));
    }

    @Test
    public void testDuplicatePlateauSpansPivot() {
        assertEquals(-3, test.findMin(new int[]{2, 2, -3, -3, -3, -3, -1}));
    }

    @Test
    public void testSignedValuesAtContractBoundaries() {
        assertEquals(-5000, test.findMin(new int[]{-1, 0, 5000, -5000, -4999}));
        assertEquals(-5000, test.findMin(new int[]{-5000, -5000, -1, 5000, 5000}));
    }

    @Test
    public void testIntegerExtremesWithDuplicates() {
        assertEquals(Integer.MIN_VALUE, test.findMin(new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE,
                Integer.MIN_VALUE, -1}));
    }

    @Test
    public void testMaximumContractLengthWithDistinctValues() {
        int[] sorted = new int[5000];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = -5000 + i * 2;
        }
        assertMinimumFromOracle(rotate(sorted, 4999));
    }

    @Test
    public void testMaximumContractLengthWithDuplicateValues() {
        int[] sorted = new int[5000];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = i < 2500 ? -5000 : 5000;
        }
        assertMinimumFromOracle(rotate(sorted, 2500));
    }

    @Test
    public void testWorstCaseDuplicateShrinking() {
        int[] sorted = new int[5000];
        Arrays.fill(sorted, 7);
        sorted[0] = 0;
        sorted[4999] = 9;
        assertMinimumFromOracle(rotate(sorted, 1));
    }

    @Test
    public void testSmallArraysExhaustiveRotationsAgainstOracle() {
        for (int length = 1; length <= 12; length++) {
            int[] sorted = new int[length];
            for (int i = 0; i < length; i++) {
                sorted[i] = (i / 2) - 4;
            }
            for (int pivot = 0; pivot < length; pivot++) {
                assertMinimumFromOracle(rotate(sorted, pivot));
            }
        }
    }

    @Test
    public void testInputIsNotModified() {
        int[] values = {5, 6, 7, 0, 1, 2, 2};
        int[] original = values.clone();
        assertEquals(0, test.findMin(values));
        assertArrayEquals(original, values);
    }

    @Test
    public void testSameInstanceCanBeReusedAcrossInputs() {
        assertEquals(3, test.findMin(new int[]{5, 6, 7, 3, 4}));
        assertEquals(-2, test.findMin(new int[]{-2, -1, 0, 1}));
        assertEquals(1, test.findMin(new int[]{3, 3, 3, 1, 2, 3}));
    }

    @Test
    public void testAllEqualAtContractValueBoundaries() {
        assertEquals(-5000, test.findMin(new int[]{-5000, -5000, -5000, -5000}));
        assertEquals(5000, test.findMin(new int[]{5000, 5000, 5000, 5000}));
    }

    @Test
    public void testPivotAtEveryPositionWithLargeDuplicateRuns() {
        int[] sorted = {-4, -4, -4, -1, 2, 2, 2, 2, 9, 9};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            assertMinimumFromOracle(rotate(sorted, pivot));
        }
    }

    @Test
    public void testMinimumOnlyAtEndOfDuplicateRun() {
        assertEquals(-8, test.findMin(new int[]{0, 0, 2, 2, -8, -8, -8}));
    }

    @Test
    public void testMinimumOnlyAtStartOfDuplicateRun() {
        assertEquals(-8, test.findMin(new int[]{2, 2, -8, -8, -8, 0, 0}));
    }

    @Test
    public void testIndependentOracleAcrossDeterministicRotations() {
        for (int seed = 0; seed < 40; seed++) {
            int length = 1 + (seed * 17 % 31);
            int[] sorted = new int[length];
            for (int i = 0; i < length; i++) {
                sorted[i] = -30 + ((seed + i) / 3);
            }
            assertMinimumFromOracle(rotate(sorted, (seed * 7) % length));
        }
    }

    private void assertMinimumFromOracle(int[] values) {
        int expected = Arrays.stream(values).min().orElseThrow();
        assertEquals(expected, test.findMin(values), "values=" + Arrays.toString(values));
    }

    private static int[] rotate(int[] sorted, int pivot) {
        int[] rotated = new int[sorted.length];
        System.arraycopy(sorted, pivot, rotated, 0, sorted.length - pivot);
        System.arraycopy(sorted, 0, rotated, sorted.length - pivot, pivot);
        return rotated;
    }
}
