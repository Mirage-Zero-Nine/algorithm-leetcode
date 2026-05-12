package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StoneGame877Test {

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
}
