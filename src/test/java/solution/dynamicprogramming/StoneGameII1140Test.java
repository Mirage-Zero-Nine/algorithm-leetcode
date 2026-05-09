package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StoneGameII1140Test {

    private final StoneGameII_1140 test = new StoneGameII_1140();

    @Test
    public void testHappyCases() {
        assertEquals(10, test.stoneGameII(new int[]{2, 7, 9, 4, 4}));
        assertEquals(1, test.stoneGameII(new int[]{1}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.stoneGameII(new int[]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(26, test.stoneGameII(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
