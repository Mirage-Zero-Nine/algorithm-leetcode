package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests for the binary-search implementation of H-Index II. */
public class HIndex_275Test {

    private final HIndex_275 test = new HIndex_275();

    @Test
    public void testOfficialExampleOne() {
        assertHIndex(3, new int[]{0, 1, 3, 5, 6});
    }

    @Test
    public void testOfficialExampleTwo() {
        assertHIndex(2, new int[]{1, 2, 100});
    }

    @Test
    public void testEmptyArrayImplementationGuard() {
        assertHIndex(0, new int[]{});
    }

    @Test
    public void testMinimumOnePaperWithNoCitations() {
        assertHIndex(0, new int[]{0});
    }

    @Test
    public void testOnePaperWithManyCitations() {
        assertHIndex(1, new int[]{10});
    }

    @Test
    public void testAllZeros() {
        assertHIndex(0, new int[]{0, 0, 0, 0});
    }

    @Test
    public void testAllOneCitations() {
        assertHIndex(1, new int[]{1, 1, 1, 1, 1});
    }

    @Test
    public void testAllPapersExactlyReachMaximumHIndex() {
        assertHIndex(5, new int[]{5, 5, 5, 5, 5});
    }

    @Test
    public void testDuplicateValuesAtExactBoundary() {
        assertHIndex(2, new int[]{0, 0, 2, 2});
    }

    @Test
    public void testStrictlyIncreasingValues() {
        assertHIndex(2, new int[]{0, 1, 2, 3});
    }

    @Test
    public void testDuplicateValuesAroundBoundary() {
        assertHIndex(3, new int[]{1, 3, 3, 3, 10});
    }

    @Test
    public void testHighCitationTail() {
        assertHIndex(4, new int[]{0, 1, 4, 4, 4, 100});
    }

    @Test
    public void testLargeFlatLowValues() {
        assertHIndex(1, new int[]{0, 1, 1, 1, 1, 1});
    }

    @Test
    public void testHIndexBelowLargestCitation() {
        assertHIndex(3, new int[]{0, 100, 100, 100});
    }

    @Test
    public void testNoExactCitationAtAnswer() {
        assertHIndex(1, new int[]{0, 0, 0, 0, 5});
    }

    @Test
    public void testAllPapersHaveTwoCitations() {
        assertHIndex(2, new int[]{2, 2, 2, 2});
    }

    @Test
    public void testCitationCountAtOfficialUpperBound() {
        assertHIndex(4, new int[]{0, 0, 1000, 1000, 1000, 1000});
    }

    @Test
    public void testIntegerMaximumCitationDoesNotOverflow() {
        assertHIndex(3, new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE});
    }

    @Test
    public void testMaximumCitationWithLowPrefix() {
        assertHIndex(2, new int[]{0, Integer.MAX_VALUE, Integer.MAX_VALUE});
    }

    @Test
    public void testInputRemainsSortedAndUnchanged() {
        int[] citations = {0, 1, 3, 5, 6};
        int[] original = citations.clone();

        assertHIndex(3, citations);
        assertArrayEquals(original, citations);
    }

    @Test
    public void testSameInstanceCanBeReusedWithoutStateLeakage() {
        assertHIndex(3, new int[]{0, 1, 3, 5, 6});
        assertHIndex(0, new int[]{});
        assertHIndex(1, new int[]{0, 0, 0, 9});
        assertHIndex(5, new int[]{5, 5, 5, 5, 5});
    }

    @Test
    public void testExhaustiveSmallSortedArraysAgainstDefinition() {
        for (int length = 0; length <= 6; length++) {
            int[] citations = new int[length];
            enumerateNondecreasing(citations, 0, 3, 0);
        }
    }

    @Test
    public void testSeededRandomSortedArraysAgainstDefinition() {
        Random random = new Random(2752026L);
        for (int trial = 0; trial < 500; trial++) {
            int[] citations = new int[random.nextInt(101)];
            for (int i = 0; i < citations.length; i++) {
                citations[i] = random.nextInt(1001);
            }
            Arrays.sort(citations);
            assertHIndex(hIndexByDefinition(citations), citations);
        }
    }

    @Test
    public void testMaximumLengthAllZeros() {
        int[] citations = new int[100_000];
        assertHIndex(0, citations);
    }

    @Test
    public void testMaximumLengthAllOfficialMaximumCitations() {
        int[] citations = new int[100_000];
        Arrays.fill(citations, 1000);
        assertHIndex(1000, citations);
    }

    @Test
    public void testMaximumLengthWithExactlyEnoughCitedPapers() {
        int[] citations = new int[100_000];
        Arrays.fill(citations, 87_655, citations.length, 12_345);
        assertHIndex(12_345, citations);
    }

    @Test
    public void testMaximumLengthIntegerCitationOverflowBoundary() {
        int[] citations = new int[100_000];
        Arrays.fill(citations, Integer.MAX_VALUE);
        assertHIndex(100_000, citations);
    }

    @Test
    public void testGiantIncreasingArray() {
        int[] citations = new int[300];
        for (int i = 0; i < citations.length; i++) {
            citations[i] = i;
        }
        assertHIndex(150, citations);
    }

    private void enumerateNondecreasing(int[] citations, int index, int maximumValue, int minimumValue) {
        if (index == citations.length) {
            assertHIndex(hIndexByDefinition(citations), citations);
            return;
        }
        for (int value = minimumValue; value <= maximumValue; value++) {
            citations[index] = value;
            enumerateNondecreasing(citations, index + 1, maximumValue, value);
        }
    }

    /** Independent linear definition: maximize h for which at least h papers have >=h citations. */
    private int hIndexByDefinition(int[] citations) {
        int hIndex = 0;
        for (int candidate = 1; candidate <= citations.length; candidate++) {
            int qualifyingPapers = 0;
            for (int citation : citations) {
                if (citation >= candidate) {
                    qualifyingPapers++;
                }
            }
            if (qualifyingPapers >= candidate) {
                hIndex = candidate;
            }
        }
        return hIndex;
    }

    private void assertHIndex(int expected, int[] citations) {
        assertEquals(expected, test.hIndex(citations), () -> "citations=" + Arrays.toString(citations));
    }
}
