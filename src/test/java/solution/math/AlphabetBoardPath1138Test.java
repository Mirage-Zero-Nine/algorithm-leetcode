package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AlphabetBoardPath1138Test {

    private final AlphabetBoardPath_1138 test = new AlphabetBoardPath_1138();

    @Test
    public void testHappyCases() {
        // "a" is at (0,0), just press !
        assertEquals("!", test.alphabetBoardPath("a"));
        // "b" is at (1,0), move right once then !
        assertEquals("D!", test.alphabetBoardPath("b"));
    }

    @Test
    public void testEdgeCases() {
        // "z" is at (0,5), move down 5 then !
        assertEquals("RRRRR!", test.alphabetBoardPath("z"));
        // "aa" - two presses at same position
        assertEquals("!!", test.alphabetBoardPath("aa"));
    }

    @Test
    public void testLargeCase() {
        String result = test.alphabetBoardPath("leet");
        // result must contain exactly 4 '!' characters
        assertEquals(4, result.chars().filter(c -> c == '!').count());
    }
}
