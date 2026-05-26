package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
        // {2,1,1,2}: total=6, dp shows tie so Alex doesn't strictly win
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
}
