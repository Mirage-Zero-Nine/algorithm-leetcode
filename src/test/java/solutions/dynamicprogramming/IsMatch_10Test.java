package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsMatch_10Test {

    private final IsMatch_10 test = new IsMatch_10();

    @Test
    public void testHappyCases() {
        assertTrue(test.isMatch("aa", "a*"));
        assertTrue(test.isMatch("ab", ".*"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isMatch("aa", "a"));
        assertFalse(test.isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
    }

    @Test
    public void testEmptyStringEmptyPattern() {
        assertTrue(test.isMatch("", ""));
    }

    @Test
    public void testEmptyStringWithStarPattern() {
        assertTrue(test.isMatch("", "a*"));
        assertTrue(test.isMatch("", "a*b*c*"));
    }

    @Test
    public void testDotStar() {
        assertTrue(test.isMatch("anything", ".*"));
    }

    @Test
    public void testExactMatch() {
        assertTrue(test.isMatch("abc", "abc"));
        assertFalse(test.isMatch("abc", "abd"));
    }

    @Test
    public void testNullInputs() {
        assertFalse(test.isMatch(null, "a"));
        assertFalse(test.isMatch("a", null));
    }

    @Test
    public void testStarMatchesMultiple() {
        assertTrue(test.isMatch("aaa", "a*"));
        assertTrue(test.isMatch("aaab", "a*b"));
    }

    @Test
    public void testDfsImplementation() {
        assertTrue(test.dfsImplementation("ab", ".*"));
        assertFalse(test.dfsImplementation("mississippi", "mis*is*p*."));
        assertTrue(test.dfsImplementation("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
    }

    @Test
    public void testGiantCase() {
        // Long string with repeating pattern
        String s = "a".repeat(20);
        String p = "a*a*a*a*a*a*a*a*a*a*";
        assertTrue(test.isMatch(s, p));
    }

    @Test
    public void testEmptyStringWithDotStar() {
        assertTrue(test.isMatch("", ".*"));
    }

    @Test
    public void testEmptyStringNonMatchingPattern() {
        assertFalse(test.isMatch("", "a"));
    }

    @Test
    public void testNonEmptyStringEmptyPattern() {
        assertFalse(test.isMatch("a", ""));
    }

    @Test
    public void testClassicAabCStarAStarB() {
        // c* matches zero 'c', a* matches two 'a's, b matches 'b'
        assertTrue(test.isMatch("aab", "c*a*b"));
    }

    @Test
    public void testMississippiFullMatch() {
        assertTrue(test.isMatch("mississippi", "mis*is*ip*."));
    }

    @Test
    public void testDotStarFollowedByChar() {
        // .* matches 'aabb', then 'c' matches 'c'
        assertTrue(test.isMatch("aabbc", ".*c"));
    }

    @Test
    public void testMultipleStarsOnSameChar() {
        // a*a*a* can match 'aaa' — each a* can consume some a's
        assertTrue(test.isMatch("aaa", "a*a*a*"));
        // also matches empty
        assertTrue(test.isMatch("", "a*a*a*"));
    }

    @Test
    public void testStarMatchesZeroOfPreceding() {
        // 'b*' matches zero 'b', then 'a' matches 'a'
        assertTrue(test.isMatch("a", "b*a"));
        // trailing star matches zero
        assertTrue(test.isMatch("a", "ab*"));
    }

    @Test
    public void testLargeBacktrackingCase() {
        // Worst-case for naive backtracking: s = "aaa...a", p = "a*a*a*...a*b"
        String s = "a".repeat(25);
        String p = "a*".repeat(25) + "b";
        assertFalse(test.isMatch(s, p));
    }
}
