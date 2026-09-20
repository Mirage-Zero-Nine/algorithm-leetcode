package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StoneGameII_1140Test {

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

    @Test
    public void testTwoPiles() {
        assertEquals(3, test.stoneGameII(new int[]{1, 2}));
    }

    @Test
    public void testThreePiles() {
        assertEquals(3, test.stoneGameII(new int[]{1, 2, 3}));
    }

    @Test
    public void testEqualPiles() {
        assertEquals(9, test.stoneGameII(new int[]{3, 3, 3, 3, 3, 3}));
    }

    @Test
    public void testDescendingPiles() {
        assertEquals(9, test.stoneGameII(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(4, test.stoneGameII(new int[]{1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] piles = new int[50];
        for (int i = 0; i < 50; i++) piles[i] = i + 1;
        // Alex plays optimally; total sum = 1275
        int result = test.stoneGameII(piles);
        assertTrue(result > 0 && result <= 1275);
    }

    @Test
    public void testFourPiles() {
        assertEquals(5, test.stoneGameII(new int[]{1, 2, 3, 4}));
    }

    @ParameterizedTest(name = "two-pile game {0}")
    @CsvSource({"'1,1',2", "'1,2',3", "'2,5',7", "'4,1',5", "'7,3',10", "'10,10',20", "'1,100',101", "'8,2',10", "'6,9',15", "'12,4',16"})
    public void testAdditionalTwoPileTotals(String encoded, int expected) {
        String[] values = encoded.split(",");
        int[] piles = {Integer.parseInt(values[0]), Integer.parseInt(values[1])};
        assertEquals(expected, test.stoneGameII(piles));
    }
}
