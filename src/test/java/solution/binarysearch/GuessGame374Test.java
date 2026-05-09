package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GuessGame374Test {

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
}
