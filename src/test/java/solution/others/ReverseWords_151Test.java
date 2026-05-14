package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReverseWords_151Test {

    private final ReverseWords_151 test = new ReverseWords_151();

    @Test
    public void testHappyCases() {
        assertEquals("blue is sky the", test.reverseWords("the sky is blue"));
        assertEquals("world hello", test.reverseWords("  hello world  "));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.reverseWords(" "));
        assertEquals("a", test.reverseWords("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("example good a", test.reverseWords("a good   example"));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", test.reverseWords(""));
    }

    @Test
    public void testSingleWord() {
        assertEquals("hello", test.reverseWords("hello"));
    }

    @Test
    public void testMultipleSpacesBetweenWords() {
        assertEquals("c b a", test.reverseWords("a   b   c"));
    }

    @Test
    public void testLeadingTrailingSpaces() {
        assertEquals("world hello", test.reverseWords("   hello   world   "));
    }

    @Test
    public void testTwoPointers() {
        assertEquals("blue is sky the", test.twoPointers("the sky is blue"));
        assertEquals("world hello", test.twoPointers("  hello world  "));
    }

    @Test
    public void testTwoPointersEdge() {
        assertEquals("a", test.twoPointers("a"));
        assertEquals("", test.twoPointers(" "));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append("word").append(i).append(" ");
        }
        String input = sb.toString().trim();
        String result = test.reverseWords(input);
        // First word in result should be last word of input
        assertTrue(result.startsWith("word499"));
        assertTrue(result.endsWith("word0"));
    }
}
