package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsMatch_44Test {

    private final IsMatch_44 test = new IsMatch_44();

    @Test
    public void testHappyCases() {
        assertTrue(test.isMatch("aa", "*"));
        assertTrue(test.isMatch("cb", "?b"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isMatch("aa", "a"));
        assertFalse(test.isMatch("cb", "?a"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isMatch("adceb", "*a*b"));
        assertFalse(test.isMatch("acdcb", "a*c?b"));
    }

    @Test
    public void testNullInputs() {
        assertFalse(test.isMatch(null, "*"));
        assertFalse(test.isMatch("abc", null));
        assertFalse(test.isMatch(null, null));
    }

    @Test
    public void testEmptyStrings() {
        assertTrue(test.isMatch("", ""));
        assertTrue(test.isMatch("", "*"));
        assertFalse(test.isMatch("", "?"));
        assertFalse(test.isMatch("", "a"));
    }

    @Test
    public void testStarMatchesEmpty() {
        assertTrue(test.isMatch("abc", "abc*"));
        assertTrue(test.isMatch("abc", "*abc"));
    }

    @Test
    public void testQuestionMark() {
        assertTrue(test.isMatch("a", "?"));
        assertTrue(test.isMatch("abc", "???"));
        assertFalse(test.isMatch("ab", "???"));
    }

    @Test
    public void testMultipleStars() {
        assertTrue(test.isMatch("abc", "***"));
        assertTrue(test.isMatch("abc", "a**c"));
    }

    @Test
    public void testExactMatch() {
        assertTrue(test.isMatch("hello", "hello"));
        assertFalse(test.isMatch("hello", "hellp"));
    }

    @Test
    public void testComplexPattern() {
        assertTrue(test.isMatch("ab", "?*"));
        assertTrue(test.isMatch("aa", "*a"));
    }

    @Test
    public void testGiantCase() {
        assertTrue(test.isMatch(
            "aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba",
            "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
    }
}
