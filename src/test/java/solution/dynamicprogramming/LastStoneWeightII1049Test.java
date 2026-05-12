package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastStoneWeightII1049Test {

    private final LastStoneWeightII_1049 test = new LastStoneWeightII_1049();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
        assertEquals(5, test.lastStoneWeightII(new int[]{31, 26, 33, 21, 40}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.lastStoneWeightII(new int[]{1}));
        assertEquals(0, test.lastStoneWeightII(new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.lastStoneWeightII(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
