package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PredictTheWinner486Test {

    private final PredictTheWinner_486 test = new PredictTheWinner_486();

    @Test
    public void testHappyCases() {
        assertFalse(test.predictTheWinner(new int[]{1, 5, 2}));
        assertTrue(test.predictTheWinner(new int[]{1, 5, 233, 7}));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.predictTheWinner(new int[]{1}));
        assertTrue(test.predictTheWinner(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.predictTheWinner(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
