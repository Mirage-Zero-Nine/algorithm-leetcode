package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class LongestArithSeqLength_1027Test {

    private final LongestArithSeqLength_1027 test = new LongestArithSeqLength_1027();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.dp(new int[]{3, 6, 9, 12}));
        assertEquals(3, test.dp(new int[]{9, 4, 7, 2, 10}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.dp(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.dp(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));
    }

    @Test
    public void testHappyAllSame() {
        // All elements same -> diff=0, length = n
        assertEquals(5, test.dp(new int[]{7, 7, 7, 7, 7}));
    }

    @Test
    public void testHappyDecreasing() {
        // Decreasing sequence with diff -2
        assertEquals(5, test.dp(new int[]{10, 8, 6, 4, 2}));
    }

    @Test
    public void testHappyMixed() {
        assertEquals(4, test.dp(new int[]{20, 1, 15, 3, 10, 5, 8}));
    }

    @Test
    public void testNegativeValues() {
        // Negative numbers
        assertEquals(5, test.dp(new int[]{-3, -1, 1, 3, 5}));
    }

    @Test
    public void testEdgeTwoElements() {
        assertEquals(2, test.dp(new int[]{100, 200}));
    }

    @Test
    public void testEdgeDuplicates() {
        assertEquals(4, test.dp(new int[]{1, 2, 3, 2, 3, 4}));
    }

    @Test
    public void testGiantCase() {
        // Array of 200 elements: 0,1,2,...,199 -> longest arith subseq = 200
        int[] arr = new int[200];
        for (int i = 0; i < 200; i++) arr[i] = i;
        assertEquals(200, test.dp(arr));
    }

    @Test
    public void testBacktrackingMatchesKnownExamples() {
        assertBothImplementations(4, new int[]{3, 6, 9, 12});
        assertBothImplementations(3, new int[]{9, 4, 7, 2, 10});
        assertBothImplementations(2, new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24});
    }

    @Test
    public void testPositiveDifference() {
        assertBothImplementations(6, new int[]{2, 5, 8, 11, 14, 17});
    }

    @Test
    public void testNegativeDifference() {
        assertBothImplementations(6, new int[]{30, 25, 20, 15, 10, 5});
    }

    @Test
    public void testZeroDifference() {
        assertBothImplementations(6, new int[]{7, 7, 7, 7, 7, 7});
    }

    @Test
    public void testNoThreeTermArithmeticSequence() {
        assertBothImplementations(2, new int[]{1, 2, 4, 8, 16});
    }

    @Test
    public void testUnsortedArrayWithNonContiguousSequence() {
        // 1, 3, 5, 7 is a subsequence, not a contiguous subarray.
        assertBothImplementations(4, new int[]{1, 10, 3, 5, 7, 2});
    }

    @Test
    public void testMixedNegativeAndPositiveValues() {
        assertBothImplementations(7, new int[]{-9, -6, -3, 0, 3, 6, 9});
    }

    @Test
    public void testDuplicateValuesDoNotReuseAnIndex() {
        assertBothImplementations(3, new int[]{1, 1, 2, 3});
        assertBothImplementations(3, new int[]{1, 3, 3, 5});
    }

    @Test
    public void testCompetingArithmeticSubsequences() {
        // The longest sequence is 2, 5, 8, 11 with difference 3.
        assertBothImplementations(4, new int[]{1, 4, 7, 2, 5, 8, 11});
    }

    @Test
    public void testSequenceMustRespectOriginalOrder() {
        // 5, 7, 9 is valid; 1, 3, 5 is not because 5 appears first.
        assertBothImplementations(3, new int[]{5, 1, 3, 7, 9});
    }

    @Test
    public void testLongSequenceHiddenAmongNoise() {
        assertBothImplementations(5, new int[]{100, 1, 4, 7, 10, 13, 2, 200});
    }

    @Test
    public void testValuesNearIntegerBounds() {
        assertBothImplementations(3, new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 2});
        assertBothImplementations(3, new int[]{Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE});
    }

    @Test
    public void testTwoElementsAlwaysFormAnArithmeticSubsequence() {
        assertBothImplementations(2, new int[]{-100, 100});
        assertBothImplementations(2, new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE});
    }

    @Test
    public void testExhaustiveReferenceOnRandomSmallArrays() {
        Random random = new Random(1027L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int[] values = new int[2 + random.nextInt(9)];
            for (int i = 0; i < values.length; i++) {
                values[i] = random.nextInt(15) - 7;
            }

            int expected = exhaustiveLongestArithmeticSubsequence(values);
            assertEquals(expected, test.dp(values.clone()), "DP mismatch for case " + caseNumber);
            assertEquals(expected,
                    new LongestArithSeqLength_1027().longestArithSeqLength(values.clone()),
                    "Backtracking mismatch for case " + caseNumber);
        }
    }

    @Test
    public void testDynamicProgrammingResultIsBoundedByInputLength() {
        int[] values = {8, 1, 5, 3, 7, 9, 2, 11};
        int result = test.dp(values);
        assertEquals(4, result);
        assertTrue(result >= 2 && result <= values.length);
    }

    @Test
    public void testBacktrackingResetsStateBetweenCalls() {
        LongestArithSeqLength_1027 solver = new LongestArithSeqLength_1027();

        assertEquals(4, solver.longestArithSeqLength(new int[]{1, 3, 5, 7}));
        assertEquals(2, solver.longestArithSeqLength(new int[]{1, 2, 4}));
    }

    private void assertBothImplementations(int expected, int[] values) {
        assertEquals(expected, test.dp(values.clone()), "DP implementation");
        assertEquals(expected,
                new LongestArithSeqLength_1027().longestArithSeqLength(values.clone()),
                "Backtracking implementation");
    }

    /**
     * Exhaustively checks every subsequence. This deliberately independent reference is practical
     * only for the small arrays used by the randomized test.
     */
    private int exhaustiveLongestArithmeticSubsequence(int[] values) {
        int best = 2;
        int totalSubsets = 1 << values.length;

        for (int mask = 0; mask < totalSubsets; mask++) {
            if (Integer.bitCount(mask) <= best) {
                continue;
            }

            long previous = 0;
            long difference = 0;
            int selected = 0;
            boolean arithmetic = true;
            for (int i = 0; i < values.length; i++) {
                if ((mask & (1 << i)) == 0) {
                    continue;
                }

                if (selected == 0) {
                    previous = values[i];
                } else if (selected == 1) {
                    difference = (long) values[i] - previous;
                    previous = values[i];
                } else {
                    long currentDifference = (long) values[i] - previous;
                    if (currentDifference != difference) {
                        arithmetic = false;
                        break;
                    }
                    previous = values[i];
                }
                selected++;
            }

            if (arithmetic) {
                best = selected;
            }
        }
        return best;
    }
}
