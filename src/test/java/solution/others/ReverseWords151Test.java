package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseWords151Test {

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
}
