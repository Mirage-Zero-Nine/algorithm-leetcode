package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit121Test {

    private final MaxProfit_121 test = new MaxProfit_121();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        assertEquals(0, test.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(null));
        assertEquals(0, test.maxProfit(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.maxProfit(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
