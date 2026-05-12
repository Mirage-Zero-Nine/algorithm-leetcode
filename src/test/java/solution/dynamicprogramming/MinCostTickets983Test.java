package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCostTickets983Test {

    private final MinCostTickets_983 test = new MinCostTickets_983();

    @Test
    public void testHappyCases() {
        assertEquals(11, test.minCostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
        assertEquals(17, test.minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.minCostTickets(new int[]{1}, new int[]{2, 7, 15}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, test.minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 7, 30}));
    }
}
