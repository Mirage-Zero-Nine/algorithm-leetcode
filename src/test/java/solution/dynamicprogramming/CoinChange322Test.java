package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CoinChange322Test {

    private final CoinChange_322 test = new CoinChange_322();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.coinChange(new int[]{1, 5, 10}, 11));
        assertEquals(3, test.coinChange(new int[]{1, 2, 5}, 11));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.coinChange(new int[]{2}, 3));
        assertEquals(0, test.coinChange(new int[]{1}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.coinChange(new int[]{1, 5, 10, 25}, 100));
    }
}
