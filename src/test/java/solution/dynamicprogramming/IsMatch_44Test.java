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

    @Test
    public void testEmptyStringWithDoubleStar() {
        assertTrue(test.isMatch("", "**"));
        assertTrue(test.isMatch("", "***"));
    }

    @Test
    public void testSingleCharWithQuestionMark() {
        assertTrue(test.isMatch("a", "?"));
        assertTrue(test.isMatch("z", "?"));
    }

    @Test
    public void testStarMatchesMultipleChars() {
        assertTrue(test.isMatch("aa", "*"));
        assertTrue(test.isMatch("abcdef", "*"));
    }

    @Test
    public void testPartialPatternWithStar() {
        assertTrue(test.isMatch("aa", "a*"));
        assertTrue(test.isMatch("abc", "a*c"));
        assertFalse(test.isMatch("abc", "a*d"));
    }

    @Test
    public void testAllQuestionMarksMatchEqualLength() {
        assertTrue(test.isMatch("abcde", "?????"));
        assertFalse(test.isMatch("abcde", "????"));
        assertFalse(test.isMatch("abcde", "??????"));
    }

    @Test
    public void testCollapsingMultipleStars() {
        assertTrue(test.isMatch("abc", "a***b***c"));
        assertTrue(test.isMatch("abc", "**a**b**c**"));
        assertTrue(test.isMatch("x", "****"));
    }

    @Test
    public void testMixedWildcards() {
        assertTrue(test.isMatch("abcdef", "a?c*f"));
        assertFalse(test.isMatch("abcdef", "a?d*f"));
        assertTrue(test.isMatch("abcdef", "?*?"));
    }

    @Test
    public void testLargeStressCase() {
        String s = "a".repeat(1000) + "b";
        String p = "a*a*a*a*a*a*a*a*a*a*b";
        assertTrue(test.isMatch(s, p));

        String s2 = "a".repeat(1000);
        String p2 = "*".repeat(500) + "a" + "*".repeat(500);
        assertTrue(test.isMatch(s2, p2));
    }

    @Test
    public void testLargeNegativeStressCase() {
        String s = "a".repeat(1000);
        String p = "a*a*a*a*a*a*a*a*a*a*b";
        assertFalse(test.isMatch(s, p));
    }
}
