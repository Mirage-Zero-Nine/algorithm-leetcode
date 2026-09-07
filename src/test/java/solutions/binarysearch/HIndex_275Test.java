package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HIndex_275Test {

    private final HIndex_275 test = new HIndex_275();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.hIndex(new int[]{0, 1, 3, 5, 6}));
        assertEquals(2, test.hIndex(new int[]{1, 2, 100}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.hIndex(new int[]{}));
        assertEquals(0, test.hIndex(new int[]{0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.hIndex(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testSinglePositiveCitation() {
        assertEquals(1, test.hIndex(new int[]{10}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.hIndex(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testStrictlyIncreasingSmall() {
        assertEquals(2, test.hIndex(new int[]{0, 1, 2, 3}));
    }

    @Test
    public void testDuplicateValues() {
        assertEquals(3, test.hIndex(new int[]{1, 3, 3, 3, 10}));
    }

    @Test
    public void testHighTailValues() {
        assertEquals(4, test.hIndex(new int[]{0, 1, 4, 4, 4, 100}));
    }

    @Test
    public void testLargeFlatLowValues() {
        assertEquals(1, test.hIndex(new int[]{0, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testGiantIncreasingArray() {
        int n = 300;
        int[] citations = new int[n];
        for (int i = 0; i < n; i++) {
            citations[i] = i;
        }
        assertEquals(150, test.hIndex(citations));
    }
@Test
    public void testRandomCitationsAgainstDefinition() {
        java.util.Random random = new java.util.Random(2752026L);
        for (int trial = 0; trial < 300; trial++) {
            int[] citations = new int[random.nextInt(60)];
            for (int i = 0; i < citations.length; i++) citations[i] = random.nextInt(80);
            java.util.Arrays.sort(citations);
            int expected = 0;
            for (int h = 1; h <= citations.length; h++) {
                int papers = 0;
                for (int citation : citations) if (citation >= h) papers++;
                if (papers >= h) expected = h;
            }
            assertEquals(expected, test.hIndex(citations), "trial=" + trial);
        }
    }

    @Test
    public void testCitationCountCannotExceedNumberOfPapers() {
        assertEquals(3, test.hIndex(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}));
    }

    @Test
    public void testGiantZeroPrefixWithExactlyEnoughCitedPapers() {
        int[] citations = new int[100000];
        java.util.Arrays.fill(citations, 87655, citations.length, 12345);
        assertEquals(12345, test.hIndex(citations));
    }
}
