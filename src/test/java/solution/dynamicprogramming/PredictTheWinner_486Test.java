package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PredictTheWinner_486Test {

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

    @Test
    public void testEvenLength() {
        assertTrue(test.predictTheWinner(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testEqualScores() {
        assertTrue(test.predictTheWinner(new int[]{1, 1, 1}));
    }

    @Test
    public void testPlayer1Loses() {
        assertFalse(test.predictTheWinner(new int[]{1, 5, 2}));
    }

    @Test
    public void testAllSameValues() {
        assertTrue(test.predictTheWinner(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testDfsWithPruning() {
        assertFalse(test.predictTheWinnerDFSWithPruning(new int[]{1, 5, 2}));
        assertTrue(test.predictTheWinnerDFSWithPruning(new int[]{1, 5, 233, 7}));
    }

    @Test
    public void testDfs() {
        assertFalse(test.predictTheWinnerDFS(new int[]{1, 5, 2}));
        assertTrue(test.predictTheWinnerDFS(new int[]{1, 5, 233, 7}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200};
        assertTrue(test.predictTheWinner(nums));
    }
}
