package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProfit714Test {

    private final MaxProfit_714 test = new MaxProfit_714();

    @Test
    public void testHappyCases() {
        assertEquals(8, test.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        assertEquals(6, test.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxProfit(null, 1));
        assertEquals(0, test.maxProfit(new int[]{1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.maxProfit(new int[]{1, 2, 3, 4, 5, 6}, 2));
    }
}
