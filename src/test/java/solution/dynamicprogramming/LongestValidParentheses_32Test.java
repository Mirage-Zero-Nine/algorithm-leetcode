package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestValidParentheses_32Test {

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

    @Test
    public void testAllOpen() {
        assertEquals(0, test.longestValidParentheses("(((("));
    }

    @Test
    public void testAllClose() {
        assertEquals(0, test.longestValidParentheses("))))"));
    }

    @Test
    public void testPerfectMatch() {
        assertEquals(6, test.longestValidParentheses("((()))"));
    }

    @Test
    public void testSingleChar() {
        assertEquals(0, test.longestValidParentheses("("));
    }

    @Test
    public void testConsecutivePairs() {
        assertEquals(6, test.longestValidParentheses("()()()"));
    }

    @Test
    public void testNestedAndConsecutive() {
        assertEquals(6, test.longestValidParentheses("()(())"));
    }

    @Test
    public void testGiantCase() {
        assertEquals(10, test.longestValidParentheses(")))))(((()()()()()((((("));
    }
}
