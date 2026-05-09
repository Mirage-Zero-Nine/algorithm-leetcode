package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumWays276Test {

    private final NumWays_276 test = new NumWays_276();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.numWays(3, 2));
        assertEquals(66, test.numWays(4, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numWays(0, 3));
        assertEquals(3, test.numWays(1, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1344, test.numWays(7, 3));
    }
}
