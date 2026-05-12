package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit309Test {

    private final MaxProfit_309 test = new MaxProfit_309();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.maxProfit(new int[]{1, 2, 3, 0, 2}));
        assertEquals(3, test.maxProfit(new int[]{1, 2, 4}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(null));
        assertEquals(0, test.maxProfit(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.maxProfit(new int[]{6, 1, 3, 2, 4, 7}));
    }
}
