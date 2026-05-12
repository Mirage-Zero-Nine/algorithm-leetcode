package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxEnvelopes354Test {

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
}
