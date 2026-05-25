package solution.palindrome;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongestPalindromicSubstring_5Test {
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

    // === New tests: tricky/happy/negative/large ===

    @Test public void testAllDistinctChars() {
        // All distinct characters -> longest palindrome is any single char (length 1)
        String result = solver.longestPalindrome("abcdefg");
        assertEquals(1, result.length());
        assertTrue("abcdefg".contains(result));
    }

    @Test public void testMultiplePalindromesReturnLongest() {
        // "abacdfgdcaba" contains "aba" (3) and "abacaba" is not present, but "aba" at start/end
        // Use a string with multiple palindromes of different lengths
        String input = "abcbaxyzttzyx";  // "abcba" (5) and "ttzyx" no, "xyzttzyx" no... let's check: "xyzttzy" no
        // "abcba" = 5, "tzzt" no... "tt" = 2, actually "xyzttzyx" is not palindrome
        // Let's use a clearer example
        String input2 = "abcbaxabba";  // "abcba" (5) and "abba" (4)
        String result = solver.longestPalindrome(input2);
        assertEquals(5, result.length());  // longest is 5
    }

    @Test public void testAllSameCharsLong() {
        // All same chars -> entire string is the palindrome
        String input = "a".repeat(50);
        assertEquals(input, solver.longestPalindrome(input));
        assertEquals(input, solver.longestPalindromeNaive(input));
        assertEquals(input, solver.longestPalindromeDynamicProgramming(input));
    }

    @Test public void testEvenPalindromeAbba() {
        assertEquals("abba", solver.longestPalindromeNaive("xabbay"));
        assertEquals("abba", solver.longestPalindromeDynamicProgramming("xabbay"));
    }

    @Test public void testOddPalindromeAba() {
        assertEquals("aba", solver.longestPalindromeNaive("xabay"));
        assertEquals("aba", solver.longestPalindromeDynamicProgramming("xabay"));
    }

    @Test public void testLargeRandom1000Seed42CrossCheck() {
        // Generate 1000-char random string, cross-check all 3 implementations agree on length
        Random rng = new Random(42L);
        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('a' + rng.nextInt(26)));
        }
        String input = sb.toString();

        String r1 = solver.longestPalindrome(input);
        String r2 = solver.longestPalindromeNaive(input);
        String r3 = solver.longestPalindromeDynamicProgramming(input);

        // All should find same length
        assertEquals(r1.length(), r2.length(), "Manacher vs Naive length mismatch");
        assertEquals(r1.length(), r3.length(), "Manacher vs DP length mismatch");
        assertTrue(r1.length() >= 1);
    }

    @Test public void testPropertyResultIsPalindrome() {
        // Property: result of each method is always a palindrome
        String[] inputs = {"", "a", "ab", "abba", "racecar", "abcde", "aabbcc"};
        for (String input : inputs) {
            assertIsPalindrome(solver.longestPalindrome(input));
            assertIsPalindrome(solver.longestPalindromeNaive(input));
            assertIsPalindrome(solver.longestPalindromeDynamicProgramming(input));
        }
    }

    @Test public void testPropertyResultIsSubstring() {
        // Property: result is always a substring of the input
        String[] inputs = {"babad", "cbbd", "abcba", "xyzzy", "a", ""};
        for (String input : inputs) {
            assertTrue(input.contains(solver.longestPalindrome(input)));
            assertTrue(input.contains(solver.longestPalindromeNaive(input)));
            assertTrue(input.contains(solver.longestPalindromeDynamicProgramming(input)));
        }
    }

    @Test public void testLeetCodeBabad() {
        // All three methods should return a palindrome of length 3
        String r1 = solver.longestPalindrome("babad");
        String r2 = solver.longestPalindromeNaive("babad");
        String r3 = solver.longestPalindromeDynamicProgramming("babad");
        assertEquals(3, r1.length());
        assertEquals(3, r2.length());
        assertEquals(3, r3.length());
    }

    @Test public void testLeetCodeCbbd() {
        assertEquals("bb", solver.longestPalindrome("cbbd"));
        assertEquals("bb", solver.longestPalindromeNaive("cbbd"));
        assertEquals("bb", solver.longestPalindromeDynamicProgramming("cbbd"));
    }

    private void assertIsPalindrome(String s) {
        assertEquals(s, new StringBuilder(s).reverse().toString(),
                "Expected palindrome but got: " + s);
    }
}
