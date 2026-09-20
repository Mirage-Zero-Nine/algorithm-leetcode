package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests the binary-search solution for LeetCode 540. */
public class SingleNonDuplicate_540Test {

    private final SingleNonDuplicate_540 solution = new SingleNonDuplicate_540();

    @Test
    public void testOfficialExampleWithSingletonNearBeginning() {
        verify(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8});
    }

    @Test
    public void testOfficialExampleWithSingletonInMiddle() {
        verify(new int[]{3, 3, 7, 7, 10, 11, 11});
    }

    @Test
    public void testSingletonOnlyElement() {
        verify(new int[]{1});
    }

    @Test
    public void testThreeElementsWithSingletonFirst() {
        verify(new int[]{-3, -2, -2});
    }

    @Test
    public void testThreeElementsWithSingletonMiddle() {
        verify(new int[]{0, 1, 1});
    }

    @Test
    public void testThreeElementsWithSingletonLast() {
        verify(new int[]{2, 2, 3});
    }

    @Test
    public void testSingletonAtBeginningOfSeveralPairs() {
        verify(new int[]{-20, -10, -10, 0, 0, 10, 10, 100, 100});
    }

    @Test
    public void testSingletonAtEndOfSeveralPairs() {
        verify(new int[]{-100, -100, -5, -5, 0, 0, 25, 25, 200});
    }

    @Test
    public void testSingletonAtEveryInteriorPairBoundary() {
        verify(new int[]{-9, -9, -4, -4, 3, 8, 8, 17, 17, 30, 30});
    }

    @Test
    public void testAllNegativeValues() {
        verify(new int[]{-100, -100, -50, -50, -5, -2, -2, -1, -1});
    }

    @Test
    public void testZeroAndValuesAroundZero() {
        verify(new int[]{-2, -2, -1, -1, 0, 1, 1, 2, 2});
    }

    @Test
    public void testLargeGapsBetweenPairs() {
        verify(new int[]{-1_000_000, -1_000_000, -7, -7, 4, 1_000_000, 1_000_000});
    }

    @Test
    public void testIntegerExtremeValues() {
        verify(new int[]{Integer.MIN_VALUE, -1, -1, 0, 0});
        verify(new int[]{-1, -1, 0, 0, Integer.MAX_VALUE});
        verify(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0, Integer.MAX_VALUE});
    }

    @Test
    public void testOfficialValueBoundaries() {
        verify(new int[]{0, 0, 1, 100_000, 100_000});
        verify(new int[]{0, 0, 99_999, 99_999, 100_000});
    }

    @Test
    public void testRepeatedPairsWithNonconsecutiveValues() {
        verify(new int[]{5, 5, 5_000, 6_000, 6_000, 100_000, 100_000});
    }

    @Test
    public void testMaximumLengthSingletonAtBeginning() {
        verify(buildSequentialArray(49_999, 0));
    }

    @Test
    public void testMaximumLengthSingletonInMiddle() {
        verify(buildSequentialArray(49_999, 24_999));
    }

    @Test
    public void testMaximumLengthSingletonAtEnd() {
        verify(buildSequentialArray(49_999, 49_999));
    }

    @Test
    public void testSingletonAtEveryPositionForSmallArrays() {
        for (int pairCount = 0; pairCount <= 80; pairCount++) {
            for (int singletonIndex = 0; singletonIndex <= pairCount; singletonIndex++) {
                int[] values = buildSequentialArray(pairCount, singletonIndex);
                verify(values, "pairs=" + pairCount + ", singleton=" + singletonIndex);
            }
        }
    }

    @Test
    public void testExhaustiveSmallArraysWithDifferentSpacing() {
        for (int pairCount = 0; pairCount <= 25; pairCount++) {
            for (int singletonIndex = 0; singletonIndex <= pairCount; singletonIndex++) {
                int[] values = buildSpacedArray(pairCount, singletonIndex, -500, 13);
                verify(values, "pairs=" + pairCount + ", singleton=" + singletonIndex);
            }
        }
    }

    @Test
    public void testSeededRandomValidArraysAgainstIndependentOracles() {
        Random random = new Random(540L);
        for (int caseNumber = 0; caseNumber < 300; caseNumber++) {
            int pairCount = random.nextInt(100);
            int singletonIndex = random.nextInt(pairCount + 1);
            int[] values = new int[pairCount + 1];
            int value = -100_000 + random.nextInt(1_000);
            for (int i = 0; i <= pairCount; i++) {
                value += 1 + random.nextInt(250);
                values[i] = value;
            }
            verify(buildFromUniqueValues(values, singletonIndex), "case=" + caseNumber);
        }
    }

    @Test
    public void testMaximumLengthUsesOnlyOfficialValueRange() {
        int[] values = buildSpacedArray(49_999, 37_000, 0, 2);
        assertEquals(74_000, solution.singleNonDuplicate(values));
    }

    @Test
    public void testInputIsNotMutated() {
        int[] values = {-30, -30, -5, 0, 0, 7, 7};
        int[] original = values.clone();

        assertEquals(-5, solution.singleNonDuplicate(values));
        assertArrayEquals(original, values);
    }

    @Test
    public void testRepeatedCallsOnSameInstanceAreIndependent() {
        int[] first = {1, 1, 4, 6, 6};
        int[] second = {-10, -10, -2, 3, 3, 9, 9};

        assertEquals(4, solution.singleNonDuplicate(first));
        assertEquals(-2, solution.singleNonDuplicate(second));
        assertEquals(4, solution.singleNonDuplicate(first));
    }

    @Test
    public void testXorAndCountOraclesAgreeOnBoundaryCases() {
        int[][] cases = {
            {Integer.MIN_VALUE, Integer.MIN_VALUE, -8, -8, 17},
            {-12, -12, 0, 0, 1, 1, Integer.MAX_VALUE},
            {-100_000, -100_000, -1, 0, 0, 1, 1},
            {0, 2, 2, 4, 4, 6, 6}
        };
        for (int[] values : cases) {
            verify(values);
            assertEquals(xorOracle(values), countOracle(values));
        }
    }

    private void verify(int[] values) {
        verify(values, "values");
    }

    private void verify(int[] values, String context) {
        int expectedFromXor = xorOracle(values);
        int expectedFromCount = countOracle(values);
        assertEquals(expectedFromXor, expectedFromCount, context + " oracle disagreement");
        assertEquals(expectedFromCount, solution.singleNonDuplicate(values), context);
    }

    private int xorOracle(int[] values) {
        int result = 0;
        for (int value : values) {
            result ^= value;
        }
        return result;
    }

    private int countOracle(int[] values) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : values) {
            counts.merge(value, 1, Integer::sum);
        }

        int singletonCount = 0;
        int singleton = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                singleton = entry.getKey();
                singletonCount++;
            }
        }
        assertEquals(1, singletonCount, "test fixture must contain exactly one singleton");
        return singleton;
    }

    private int[] buildSequentialArray(int pairCount, int singletonIndex) {
        return buildSpacedArray(pairCount, singletonIndex, 0, 1);
    }

    private int[] buildSpacedArray(int pairCount, int singletonIndex, int start, int step) {
        int[] uniqueValues = new int[pairCount + 1];
        for (int i = 0; i <= pairCount; i++) {
            uniqueValues[i] = start + i * step;
        }
        return buildFromUniqueValues(uniqueValues, singletonIndex);
    }

    private int[] buildFromUniqueValues(int[] uniqueValues, int singletonIndex) {
        int[] values = new int[uniqueValues.length * 2 - 1];
        int next = 0;
        for (int i = 0; i < uniqueValues.length; i++) {
            values[next++] = uniqueValues[i];
            if (i != singletonIndex) {
                values[next++] = uniqueValues[i];
            }
        }
        return values;
    }
}
