package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximalSquare221Test {

    private final MaximalSquare_221 test = new MaximalSquare_221();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maximalSquare(null));
        assertEquals(0, test.maximalSquare(new char[][]{{'0'}}));
        assertEquals(1, test.maximalSquare(new char[][]{{'1'}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.maximalSquare(new char[][]{{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}}));
    }
}
