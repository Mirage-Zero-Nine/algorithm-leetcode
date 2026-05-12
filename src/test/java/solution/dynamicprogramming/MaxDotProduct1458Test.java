package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxDotProduct1458Test {

    private final MaxDotProduct_1458 test = new MaxDotProduct_1458();

    @Test
    public void testHappyCases() {
        assertEquals(18, test.maxDotProduct(new int[]{2, 1, -2, 5}, new int[]{3, 0, -6}));
        assertEquals(21, test.maxDotProduct(new int[]{3, -2}, new int[]{2, -6, 7}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.maxDotProduct(new int[]{-1, -1}, new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.maxDotProduct(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));
    }
}
