package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestValidParentheses32Test {

    private final LongestValidParentheses_32 test = new LongestValidParentheses_32();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.longestValidParentheses("(()"));
        assertEquals(4, test.longestValidParentheses(")()())"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestValidParentheses(""));
        assertEquals(0, test.longestValidParentheses(")"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.longestValidParentheses("()(()()"));
    }
}
