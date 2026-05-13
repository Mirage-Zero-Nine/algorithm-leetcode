package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthOfLastWord_58Test {

    private final LengthOfLastWord_58 test = new LengthOfLastWord_58();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.lengthOfLastWord("Hello World"));
        assertEquals(4, test.lengthOfLastWord("   fly me   to   the moon  "));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.lengthOfLastWord(""));
        assertEquals(0, test.lengthOfLastWord("   "));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.lengthOfLastWord("luffy is still joyboy"));
    }

    @Test
    public void testSingleWord() {
        assertEquals(5, test.lengthOfLastWord("hello"));
    }

    @Test
    public void testSingleChar() {
        assertEquals(1, test.lengthOfLastWord("a"));
    }

    @Test
    public void testTrailingSpaces() {
        assertEquals(5, test.lengthOfLastWord("hello   "));
    }

    @Test
    public void testLeadingSpaces() {
        assertEquals(5, test.lengthOfLastWord("   hello"));
    }

    @Test
    public void testMultipleWords() {
        assertEquals(3, test.lengthOfLastWord("one two three four abc"));
    }

    @Test
    public void testSingleSpace() {
        assertEquals(0, test.lengthOfLastWord(" "));
    }

    @Test
    public void testGiantCase() {
        String word = "a".repeat(10000);
        assertEquals(10000, test.lengthOfLastWord("hello " + word));
    }
}
