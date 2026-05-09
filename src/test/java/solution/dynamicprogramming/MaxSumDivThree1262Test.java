package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxSumDivThree1262Test {

    private final MaxSumDivThree_1262 test = new MaxSumDivThree_1262();

    @Test
    public void testHappyCases() {
        assertEquals(18, test.maxSumDivThree(new int[]{3, 6, 5, 1, 8}));
        assertEquals(12, test.maxSumDivThree(new int[]{1, 2, 3, 4, 4}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(6, test.maxSumDivThree(new int[]{1, 2, 4}));
        assertEquals(3, test.maxSumDivThree(new int[]{3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(21, test.maxSumDivThree(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
