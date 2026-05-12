package solution.palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongestPalindromicSubstring5Test {
    private final LongestPalindromicSubstring_5 solver = new LongestPalindromicSubstring_5();

    // === longestPalindrome (Manacher) ===

    @Test public void testManacherExample1() {
        String result = solver.longestPalindrome("babad");
        assertTrue(result.equals("bab") || result.equals("aba"));
    }

    @Test public void testManacherExample2() {
        assertEquals("bb", solver.longestPalindrome("cbbd"));
    }

    @Test public void testManacherSingleChar() {
        assertEquals("a", solver.longestPalindrome("a"));
    }

    @Test public void testManacherEmptyString() {
        assertEquals("", solver.longestPalindrome(""));
    }

    @Test public void testManacherAllSame() {
        assertEquals("aaaa", solver.longestPalindrome("aaaa"));
    }

    @Test public void testManacherNoPalindrome() {
        String result = solver.longestPalindrome("abc");
        assertEquals(1, result.length());
    }

    @Test public void testManacherEvenLength() {
        assertEquals("abba", solver.longestPalindrome("abba"));
    }

    @Test public void testManacherOddLength() {
        assertEquals("racecar", solver.longestPalindrome("racecar"));
    }

    @Test public void testManacherCenterExpansion() {
        String result = solver.longestPalindrome("ac");
        assertEquals(1, result.length());
    }

    @Test public void testManacherLongPalindrome() {
        assertEquals("abcba", solver.longestPalindrome("xabcbay"));
    }

    // === longestPalindromeNaive ===

    @Test public void testNaiveExample1() {
        String result = solver.longestPalindromeNaive("babad");
        assertTrue(result.equals("bab") || result.equals("aba"));
    }

    @Test public void testNaiveExample2() {
        assertEquals("bb", solver.longestPalindromeNaive("cbbd"));
    }

    @Test public void testNaiveSingleChar() {
        assertEquals("a", solver.longestPalindromeNaive("a"));
    }

    @Test public void testNaiveEmptyString() {
        assertEquals("", solver.longestPalindromeNaive(""));
    }

    @Test public void testNaiveAllSame() {
        assertEquals("aaaa", solver.longestPalindromeNaive("aaaa"));
    }

    @Test public void testNaiveNoPalindrome() {
        String result = solver.longestPalindromeNaive("abc");
        assertEquals(1, result.length());
    }

    // === longestPalindromeDynamicProgramming ===

    @Test public void testDPExample1() {
        String result = solver.longestPalindromeDynamicProgramming("babad");
        assertTrue(result.equals("bab") || result.equals("aba"));
    }

    @Test public void testDPExample2() {
        assertEquals("bb", solver.longestPalindromeDynamicProgramming("cbbd"));
    }

    @Test public void testDPSingleChar() {
        assertEquals("a", solver.longestPalindromeDynamicProgramming("a"));
    }

    @Test public void testDPEmptyString() {
        assertEquals("", solver.longestPalindromeDynamicProgramming(""));
    }

    @Test public void testDPAllSame() {
        assertEquals("aaaa", solver.longestPalindromeDynamicProgramming("aaaa"));
    }

    @Test public void testDPNoPalindrome() {
        String result = solver.longestPalindromeDynamicProgramming("abc");
        assertEquals(1, result.length());
    }

    // === preProcess ===

    @Test public void testPreProcessEmpty() {
        assertEquals("^$", solver.preProcess(""));
    }

    @Test public void testPreProcessSingleChar() {
        assertEquals("^#a#$", solver.preProcess("a"));
    }

    @Test public void testPreProcessMultipleChars() {
        assertEquals("^#c#b#c#b#c#$", solver.preProcess("cbcbc"));
    }
}
