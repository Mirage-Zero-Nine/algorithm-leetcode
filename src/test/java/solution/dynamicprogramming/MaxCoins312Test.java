package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxCoins312Test {

    private final MaxCoins_312 test = new MaxCoins_312();

    @Test
    public void testHappyCases() {
        assertEquals(167, test.maxCoins(new int[]{3, 1, 5, 8}));
        assertEquals(10, test.maxCoins(new int[]{1, 5}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.maxCoins(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3630, test.maxCoins(new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5}));
    }
}
