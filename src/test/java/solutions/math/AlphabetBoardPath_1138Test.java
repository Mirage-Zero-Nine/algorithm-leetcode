package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AlphabetBoardPath_1138Test {

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

    @Test
    public void testSingleCharF() {
        // 'f' is at position (0,1) in the board
        String result = test.alphabetBoardPath("f");
        assertEquals(1, result.chars().filter(c -> c == '!').count());
    }

    @Test
    public void testMovingToZ() {
        // moving from 'a' to 'z': z is at (0,5)
        String result = test.alphabetBoardPath("az");
        assertEquals(2, result.chars().filter(c -> c == '!').count());
    }

    @Test
    public void testMovingFromZ() {
        // moving from 'z' back to 'a'
        String result = test.alphabetBoardPath("za");
        assertEquals(2, result.chars().filter(c -> c == '!').count());
    }

    @Test
    public void testSameCharRepeated() {
        // 'f' is at (0,1), from 'a' at (0,0) move R once, then stay for repeats
        assertEquals("R!!!!!", test.alphabetBoardPath("fffff"));
    }

    @Test
    public void testNegativeCaseEmptyString() {
        // empty string should produce empty result
        assertEquals("", test.alphabetBoardPath(""));
    }

    @Test
    public void testZToZ() {
        // 'zz' - go to z then stay
        String result = test.alphabetBoardPath("zz");
        assertEquals(2, result.chars().filter(c -> c == '!').count());
    }

    @Test
    public void testFullAlphabetSubset() {
        String result = test.alphabetBoardPath("abcde");
        assertEquals(5, result.chars().filter(c -> c == '!').count());
    }

    @Test
    public void testGiantCase() {
        // 100 character target
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        String result = test.alphabetBoardPath(sb.toString());
        assertEquals(100, result.chars().filter(c -> c == '!').count());
        assertTrue(result.length() > 100);
    }
}
