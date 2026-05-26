package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GuessGame_374Test {

    @Test
    public void testHappyCases() {
        assertEquals(6, new GuessGame_374(6).guessNumber(10));
        assertEquals(1, new GuessGame_374(1).guessNumber(1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new GuessGame_374(1).guessNumber(2));
        assertEquals(2, new GuessGame_374(2).guessNumber(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1702766719, new GuessGame_374(1702766719).guessNumber(2126753390));
    }

    @Test
    public void testTargetAtUpperBound() {
        assertEquals(100, new GuessGame_374(100).guessNumber(100));
    }

    @Test
    public void testTargetAtLowerBound() {
        assertEquals(1, new GuessGame_374(1).guessNumber(100));
    }

    @Test
    public void testTargetInMiddle() {
        assertEquals(50, new GuessGame_374(50).guessNumber(100));
    }

    @Test
    public void testSmallRangeValues() {
        assertEquals(3, new GuessGame_374(3).guessNumber(5));
        assertEquals(4, new GuessGame_374(4).guessNumber(5));
    }

    @Test
    public void testConsecutiveTargets() {
        for (int t = 1; t <= 20; t++) {
            assertEquals(t, new GuessGame_374(t).guessNumber(20));
        }
    }

    @Test
    public void testLargeRangeDifferentTargets() {
        assertEquals(999999999, new GuessGame_374(999999999).guessNumber(2000000000));
        assertEquals(1500000000, new GuessGame_374(1500000000).guessNumber(2000000000));
    }

    @Test
    public void testGiantBoundaryTarget() {
        int n = Integer.MAX_VALUE;
        assertEquals(n, new GuessGame_374(n).guessNumber(n));
    }
}
