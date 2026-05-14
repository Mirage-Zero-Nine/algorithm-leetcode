package solution.dynamicprogramming;

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
}
