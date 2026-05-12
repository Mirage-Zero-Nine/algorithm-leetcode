package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit188Test {

    private final MaxProfit_188 test = new MaxProfit_188();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxProfit(2, new int[]{2, 4, 1}));
        assertEquals(5, test.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(2, new int[]{1}));
        assertEquals(0, test.maxProfit(0, new int[]{1, 2, 3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.maxProfit(3, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
