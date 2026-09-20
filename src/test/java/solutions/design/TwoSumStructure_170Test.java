package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class TwoSumStructure_170Test {

    @Test
    public void testHappyCases() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(1); ts.add(3); ts.add(5);
        assertTrue(ts.find(4));
        assertFalse(ts.find(7));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        assertFalse(ts.find(0));
        ts.add(0);
        assertFalse(ts.find(0));
        ts.add(0);
        assertTrue(ts.find(0));
    }

    @Test
    public void testLargeCase() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        for (int i = 1; i <= 10; i++) ts.add(i);
        assertTrue(ts.find(11));
        assertTrue(ts.find(3));
        assertFalse(ts.find(21));
    }

    @Test
    public void testEmptyStructure() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        assertFalse(ts.find(0));
        assertFalse(ts.find(1));
        assertFalse(ts.find(-1));
    }

    @Test
    public void testSingleElement() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(5);
        assertFalse(ts.find(10)); // need two 5s
        assertFalse(ts.find(5));  // only one element
    }

    @Test
    public void testDuplicateElements() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(3); ts.add(3);
        assertTrue(ts.find(6));
        assertFalse(ts.find(3)); // no pair sums to 3 with only 3s
    }

    @Test
    public void testNegativeNumbers() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(-1); ts.add(-2); ts.add(-3);
        assertTrue(ts.find(-3)); // -1 + -2
        assertTrue(ts.find(-5)); // -2 + -3
        assertFalse(ts.find(0));
    }

    @Test
    public void testMixedPositiveNegative() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(-1); ts.add(1);
        assertTrue(ts.find(0));
        assertFalse(ts.find(2)); // no pair
    }

    @Test
    public void testFindAfterMultipleAdds() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(1);
        assertFalse(ts.find(2));
        ts.add(1);
        assertTrue(ts.find(2));
    }

    @Test
    public void testLargeValues() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(Integer.MAX_VALUE / 2);
        ts.add(Integer.MAX_VALUE / 2);
        assertTrue(ts.find(Integer.MAX_VALUE / 2 * 2));
    }

    @Test
    public void testGiantCase() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        for (int i = 0; i < 5000; i++) ts.add(i);
        assertTrue(ts.find(9997)); // 4999 + 4998
        assertTrue(ts.find(9995)); // 4999 + 4996
        assertTrue(ts.find(1)); // 0 + 1
        assertFalse(ts.find(10000));
    }

    @Test
    public void testOfficialSequenceAndRepeatedQueries() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(1);
        ts.add(3);
        ts.add(5);

        assertTrue(ts.find(4));
        assertFalse(ts.find(7));
        assertTrue(ts.find(4));
        assertFalse(ts.find(9));
    }

    @Test
    public void testDistinctPairOnlyAppearsAfterSecondAdd() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(8);
        assertFalse(ts.find(11));
        ts.add(3);
        assertTrue(ts.find(11));
        ts.add(-3);
        assertTrue(ts.find(0));
    }

    @Test
    public void testDuplicateCountsNeedTwoOccurrences() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(7);
        assertFalse(ts.find(14));
        ts.add(7);
        assertTrue(ts.find(14));
        ts.add(7);
        assertTrue(ts.find(14));
        assertFalse(ts.find(15));
    }

    @Test
    public void testDuplicateValuesCanPairWithDifferentComplement() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(4);
        ts.add(4);
        ts.add(9);
        assertTrue(ts.find(13));
        assertTrue(ts.find(8));
        assertFalse(ts.find(12));
    }

    @Test
    public void testZeroAndSignedPairs() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(0);
        ts.add(-4);
        ts.add(4);
        assertTrue(ts.find(0));
        assertTrue(ts.find(-4));
        assertTrue(ts.find(4));
        assertFalse(ts.find(1));
    }

    @Test
    public void testAllNegativeValues() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(-10);
        ts.add(-2);
        ts.add(-7);
        ts.add(-3);
        assertTrue(ts.find(-12));
        assertTrue(ts.find(-10));
        assertTrue(ts.find(-5));
        assertFalse(ts.find(-20));
        assertFalse(ts.find(0));
    }

    @Test
    public void testMixedSignsWithUnorderedInsertion() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(12);
        ts.add(-5);
        ts.add(1);
        ts.add(-12);
        assertTrue(ts.find(7));
        assertTrue(ts.find(-11));
        assertTrue(ts.find(0));
        assertFalse(ts.find(20));
    }

    @Test
    public void testAddingAfterAFindChangesOnlyRelevantAnswers() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(2);
        ts.add(10);
        assertFalse(ts.find(9));
        assertTrue(ts.find(12));
        ts.add(7);
        assertTrue(ts.find(9));
        assertTrue(ts.find(12));
        assertFalse(ts.find(100));
    }

    @Test
    public void testPairAtJavaIntegerBoundaries() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(Integer.MIN_VALUE);
        ts.add(Integer.MAX_VALUE);
        assertTrue(ts.find(-1));
        assertFalse(ts.find(0));

        ts.add(1);
        assertTrue(ts.find(Integer.MIN_VALUE + 1));
    }

    @Test
    public void testOutOfRangeMathematicalSumsDoNotWrap() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(Integer.MAX_VALUE);
        ts.add(1);
        assertFalse(ts.find(Integer.MIN_VALUE));

        ts.add(Integer.MIN_VALUE);
        assertFalse(ts.find(-2));
    }

    @Test
    public void testBoundaryComplementsUseLongArithmetic() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(Integer.MIN_VALUE);
        ts.add(1);
        ts.add(Integer.MAX_VALUE);
        ts.add(-1);
        assertTrue(ts.find(Integer.MIN_VALUE + 1));
        assertTrue(ts.find(Integer.MAX_VALUE - 1));
        assertTrue(ts.find(-1));
        assertFalse(ts.find(Integer.MAX_VALUE));
    }

    @Test
    public void testFindDoesNotConsumeOrMutateMultiset() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(6);
        ts.add(6);
        ts.add(10);
        assertTrue(ts.find(12));
        assertTrue(ts.find(12));
        assertTrue(ts.find(16));
        assertTrue(ts.find(16));
    }

    @Test
    public void testIndependentInstancesDoNotShareState() {
        TwoSumStructure_170 first = new TwoSumStructure_170();
        TwoSumStructure_170 second = new TwoSumStructure_170();
        first.add(2);
        first.add(9);
        second.add(4);
        second.add(8);
        assertTrue(first.find(11));
        assertFalse(first.find(12));
        assertTrue(second.find(12));
        assertFalse(second.find(11));
    }

    @Test
    public void testSameInstanceCanBeReusedAfterQueries() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        ts.add(-100);
        ts.add(100);
        assertTrue(ts.find(0));
        ts.add(50);
        assertTrue(ts.find(-50));
        ts.add(-50);
        assertTrue(ts.find(0));
        assertFalse(ts.find(200));
    }

    @Test
    public void testSparseLargeValues() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        int[] values = {-1_000_000, -1, 0, 1, 1_000_000};
        for (int value : values) {
            ts.add(value);
        }
        assertTrue(ts.find(-1_000_001));
        assertTrue(ts.find(1_000_001));
        assertTrue(ts.find(0));
        assertFalse(ts.find(2_000_000));
        assertFalse(ts.find(-2_000_000));
    }

    @Test
    public void testDenseRangeAgainstIndependentOracle() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value = -40; value <= 40; value++) {
            addToBoth(ts, counts, value);
        }
        for (int target = -82; target <= 82; target++) {
            assertEquals(oracleFind(counts, target), ts.find(target), "target=" + target);
        }
    }

    @Test
    public void testSeededStatefulOracleSequence() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        Map<Integer, Integer> counts = new HashMap<>();
        Random random = new Random(170_2026L);
        for (int step = 0; step < 2_000; step++) {
            if (random.nextInt(4) != 0) {
                int value = random.nextInt(-1_000, 1_001);
                addToBoth(ts, counts, value);
            } else {
                int target = random.nextInt(-2_000, 2_001);
                assertEquals(oracleFind(counts, target), ts.find(target), "step=" + step);
            }
        }
    }

    @Test
    public void testSeededFullIntOracleSequence() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        Map<Integer, Integer> counts = new HashMap<>();
        int[] values = {
            Integer.MIN_VALUE, Integer.MIN_VALUE + 1, -1_000_000_000, -1, 0,
            1, 1_000_000_000, Integer.MAX_VALUE - 1, Integer.MAX_VALUE
        };
        for (int value : values) {
            addToBoth(ts, counts, value);
            for (int target : values) {
                assertEquals(oracleFind(counts, target), ts.find(target),
                        "value=" + value + ", target=" + target);
            }
        }
        Random random = new Random(17L);
        for (int step = 0; step < 500; step++) {
            int target = random.nextInt();
            assertEquals(oracleFind(counts, target), ts.find(target), "target=" + target);
        }
    }

    @Test
    public void testExhaustiveSmallSequences() {
        int[] alphabet = {-2, -1, 0, 1, 2};
        for (int code = 0; code < 1 + 5 + 25 + 125 + 625 + 3_125; code++) {
            int length = sequenceLength(code);
            int encoded = sequenceOffsetCode(code, length);
            TwoSumStructure_170 ts = new TwoSumStructure_170();
            Map<Integer, Integer> counts = new HashMap<>();
            for (int position = 0; position < length; position++) {
                int value = alphabet[encoded % alphabet.length];
                encoded /= alphabet.length;
                addToBoth(ts, counts, value);
            }
            for (int target = -5; target <= 5; target++) {
                assertEquals(oracleFind(counts, target), ts.find(target),
                        "sequence code=" + code + ", target=" + target);
            }
        }
    }

    @Test
    public void testManyFindsAfterManyAdds() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < 10_000; i++) {
            int value = (i % 2 == 0) ? i : -i;
            addToBoth(ts, counts, value);
        }
        for (int target = -20_000; target <= 20_000; target += 37) {
            assertEquals(oracleFind(counts, target), ts.find(target), "target=" + target);
        }
    }

    @Test
    public void testRepeatedDuplicateAndComplementTransitions() {
        TwoSumStructure_170 ts = new TwoSumStructure_170();
        Map<Integer, Integer> counts = new HashMap<>();
        int[] additions = {5, 5, -5, -5, 5, -5, 10, -10, 10};
        for (int value : additions) {
            addToBoth(ts, counts, value);
            for (int target = -20; target <= 20; target++) {
                assertEquals(oracleFind(counts, target), ts.find(target),
                        "after add=" + value + ", target=" + target);
            }
        }
    }

    private static void addToBoth(TwoSumStructure_170 ts, Map<Integer, Integer> counts, int value) {
        ts.add(value);
        counts.put(value, counts.getOrDefault(value, 0) + 1);
    }

    private static boolean oracleFind(Map<Integer, Integer> counts, int target) {
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int first = entry.getKey();
            long complement = (long) target - first;
            if (complement < Integer.MIN_VALUE || complement > Integer.MAX_VALUE) {
                continue;
            }
            int second = (int) complement;
            if (first == second) {
                if (entry.getValue() > 1) {
                    return true;
                }
            } else if (counts.containsKey(second)) {
                return true;
            }
        }
        return false;
    }

    private static int sequenceLength(int code) {
        int count = 0;
        int width = 1;
        while (code >= width) {
            code -= width;
            width *= 5;
            count++;
        }
        return count;
    }

    private static int sequenceOffsetCode(int code, int length) {
        int offset = 0;
        int width = 1;
        for (int currentLength = 0; currentLength < length; currentLength++) {
            offset += width;
            width *= 5;
        }
        return code - offset;
    }
}
