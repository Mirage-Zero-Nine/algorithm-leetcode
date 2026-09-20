package solutions.twopointers;

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
    @Test public void testAnotherSingleWord() { assertEquals(5, test.lengthOfLastWord("hello")); }
    @Test public void testLeadingSpacesShortWord() { assertEquals(3, test.lengthOfLastWord("   cat")); }
    @Test public void testManyTrailingSpaces() { assertEquals(4, test.lengthOfLastWord("word     ")); }
    @Test public void testMultipleInternalSpaces() { assertEquals(3, test.lengthOfLastWord("one  two   cat")); }
    @Test public void testOneCharacter() { assertEquals(1, test.lengthOfLastWord("x")); }
    @Test public void testAllSpaces() { assertEquals(0, test.lengthOfLastWord("     ")); }
    @Test public void testPunctuationWord() { assertEquals(4, test.lengthOfLastWord("hi !wow")); }
    @Test public void testNumericWord() { assertEquals(6, test.lengthOfLastWord("id 123456")); }
    @Test public void testAlternatingSpaces() { assertEquals(1, test.lengthOfLastWord("a b c")); }
    @Test public void testLongLastWord() { assertEquals(1000, test.lengthOfLastWord("prefix " + "z".repeat(1000))); }
}
