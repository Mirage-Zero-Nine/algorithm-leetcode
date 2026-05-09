package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSquares279Test {

    private final NumSquares_279 test = new NumSquares_279();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.numSquares(12));
        assertEquals(2, test.numSquares(13));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.numSquares(1));
        assertEquals(2, test.numSquares(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.numSquares(100));
        assertEquals(3, test.numSquares(99));
    }
}
