package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StoneGame_877Test {

    private final StoneGame_877 test = new StoneGame_877();

    @Test
    public void testHappyCases() {
        assertTrue(test.stoneGame(new int[]{5, 3, 4, 5}));
        assertTrue(test.stoneGame(new int[]{3, 7, 2, 3}));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.stoneGame(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.stoneGame(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    @Test
    public void testTwoPilesLargeFirst() {
        assertTrue(test.stoneGame(new int[]{100, 1}));
    }

    @Test
    public void testTwoPilesSmallFirst() {
        assertTrue(test.stoneGame(new int[]{1, 100}));
    }

    @Test
    public void testFourPilesSymmetric() {
        // Optimal play on {3,1,1,2} gives Alex 4 stones versus Lee's 3.
        assertTrue(test.stoneGame(new int[]{3, 1, 1, 2}));
    }

    @Test
    public void testSixPiles() {
        assertTrue(test.stoneGame(new int[]{7, 8, 8, 10, 3, 5}));
    }

    @Test
    public void testAlternatingValues() {
        assertTrue(test.stoneGame(new int[]{1, 100, 1, 100}));
    }

    @Test
    public void testDescendingPiles() {
        assertTrue(test.stoneGame(new int[]{10, 8, 6, 4, 2, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] piles = new int[500];
        for (int i = 0; i < 500; i++) {
            piles[i] = i + 1;
        }
        assertTrue(test.stoneGame(piles));
    }

    @ParameterizedTest(name = "stone game {0}")
    @CsvSource({"'1,2',true", "'2,1',true", "'1,3,1,3',true", "'2,2,2,2',false", "'9,1,8,2',true", "'6,4,7,3',true", "'100,1,100,1',true", "'1,10,2,9,3,8',true", "'5,4,3,2,1,6',true", "'11,7,5,3,2,1',true"})
    public void testAdditionalEvenLengthGames(String encoded, boolean expected) {
        String[] values = encoded.split(",");
        int[] piles = new int[values.length];
        for (int i = 0; i < values.length; i++) piles[i] = Integer.parseInt(values[i]);
        assertEquals(expected, test.stoneGame(piles));
    }
}
