package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestValidParentheses_32Test {

    private final LongestValidParentheses_32 test = new LongestValidParentheses_32();

    @Test
    public void testHappyCases() {
        assertAllApproaches("(()", 2);
        assertAllApproaches(")()())", 4);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertAllApproaches("", 0);
        assertAllApproaches(")", 0);
    }

    @Test
    public void testLargeCase() {
        assertAllApproaches("()(()()", 4);
    }

    @Test
    public void testAllOpen() {
        assertAllApproaches("((((", 0);
    }

    @Test
    public void testAllClose() {
        assertAllApproaches(")", 0);
        assertAllApproaches("))))", 0);
    }

    @Test
    public void testPerfectMatch() {
        assertAllApproaches("((()))", 6);
    }

    @Test
    public void testSingleChar() {
        assertAllApproaches("(", 0);
    }

    @Test
    public void testConsecutivePairs() {
        assertAllApproaches("()()()", 6);
    }

    @Test
    public void testNestedAndConsecutive() {
        assertAllApproaches("()(())", 6);
    }

    @Test
    public void testGiantCase() {
        assertAllApproaches(")))))(((()()()()()(((((", 10);
    }

    @Test
    public void testValidSubstringAtDifferentPositions() {
        assertAllApproaches("()", 2);
        assertAllApproaches(")()()(", 4);
        assertAllApproaches("(()))(())", 4);
        assertAllApproaches("())((())", 4);
    }

    @Test
    public void testNestedParenthesesOfDifferentDepths() {
        assertAllApproaches("(())", 4);
        assertAllApproaches("((()))()", 8);
        assertAllApproaches("(((())))", 8);
        assertAllApproaches("()(((())))()", 12);
    }

    @Test
    public void testUnmatchedParenthesesDoNotCount() {
        assertAllApproaches("(()))(", 4);
        assertAllApproaches(")((())", 4);
        assertAllApproaches("())((()", 2);
        assertAllApproaches("((())(()", 4);
    }

    @Test
    public void testAdjacentValidComponentsAreCombined() {
        assertAllApproaches("()()", 4);
        assertAllApproaches("()(())()", 8);
        assertAllApproaches("(()())()", 8);
        assertAllApproaches("()(()())", 8);
    }

    @Test
    public void testLongBalancedInput() {
        String input = "()".repeat(5_000);
        assertAllApproaches(input, 10_000);
    }

    @Test
    public void testLongNestedInput() {
        String input = "(".repeat(2_000) + ")".repeat(2_000);
        assertAllApproaches(input, 4_000);
    }

    @Test
    public void testLongInputWithUnmatchedPrefixAndSuffix() {
        String input = ")".repeat(1_000) + "(".repeat(1_000) + ")".repeat(1_000)
                + "(".repeat(1_000);
        assertAllApproaches(input, 2_000);
    }

    @Test
    public void testStackAndDpApproachesOnSmallBoundaryPatterns() {
        assertEquals(2, test.longestValidParenthesesStack("()"));
        assertEquals(2, test.longestValidParenthesesDP("()"));
        assertEquals(4, test.longestValidParenthesesStack("(())"));
        assertEquals(4, test.longestValidParenthesesDP("(())"));
        assertEquals(2, test.longestValidParenthesesStack("())"));
        assertEquals(2, test.longestValidParenthesesDP("())"));
        assertEquals(2, test.longestValidParenthesesStack("(()"));
        assertEquals(2, test.longestValidParenthesesDP("(()"));
    }

    private void assertAllApproaches(String input, int expected) {
        assertEquals(expected, test.longestValidParentheses(input),
                "two-pass approach: " + input);
        assertEquals(expected, test.longestValidParenthesesStack(input),
                "stack approach: " + input);
        assertEquals(expected, test.longestValidParenthesesDP(input),
                "dynamic-programming approach: " + input);
    }
}
