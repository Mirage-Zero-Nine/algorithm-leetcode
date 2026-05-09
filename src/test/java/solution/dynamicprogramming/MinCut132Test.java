package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCut132Test {

    private final MinCut_132 test = new MinCut_132();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minCut("aab"));
        assertEquals(0, test.minCut("a"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minCut(null));
        assertEquals(0, test.minCut("aa"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.minCut("abcba"));
    }
}
