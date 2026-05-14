package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxEnvelopes_354Test {

    private final MaxEnvelopes_354 test = new MaxEnvelopes_354();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        assertEquals(1, test.maxEnvelopes(new int[][]{{1, 1}, {1, 1}, {1, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxEnvelopes(new int[][]{}));
        assertEquals(1, test.maxEnvelopes(new int[][]{{1, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.maxEnvelopes(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}}));
    }

    @Test
    public void testEqualWidthDifferentHeightCannotNest() {
        assertEquals(1, test.maxEnvelopes(new int[][]{{2, 2}, {2, 3}, {2, 4}, {2, 1}}));
    }

    @Test
    public void testStrictlyDecreasingDimensionsStillNestAfterSort() {
        assertEquals(4, test.maxEnvelopes(new int[][]{{4, 4}, {3, 3}, {2, 2}, {1, 1}}));
    }

    @Test
    public void testWidthIncreaseButHeightDecrease() {
        assertEquals(1, test.maxEnvelopes(new int[][]{{1, 5}, {2, 4}, {3, 3}, {4, 2}, {5, 1}}));
    }

    @Test
    public void testMixedWithDuplicates() {
        assertEquals(4, test.maxEnvelopes(new int[][]{{2, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 5}, {5, 6}}));
    }

    @Test
    public void testTwoEnvelopesNotNestable() {
        assertEquals(1, test.maxEnvelopes(new int[][]{{3, 4}, {4, 3}}));
    }

    @Test
    public void testUnsortedInputWithValidChain() {
        assertEquals(6, test.maxEnvelopes(new int[][]{{8, 9}, {1, 1}, {7, 8}, {2, 2}, {6, 7}, {3, 3}}));
    }

    @Test
    public void testGiantCaseLongChain() {
        int n = 1000;
        int[][] envelopes = new int[n][2];
        for (int i = 0; i < n; i++) {
            envelopes[i][0] = i + 1;
            envelopes[i][1] = i + 1;
        }
        assertEquals(1000, test.maxEnvelopes(envelopes));
    }
}
