package solution.palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShortestPalindrome_214Test {
    private final ShortestPalindrome_214 solver = new ShortestPalindrome_214();

    @Test public void testExample1() {
        // "aacecaaa" + prepended chars → shortest palindrome
        String result = solver.shortestPalindrome("aacecaaa");
        assertTrue(isPalindrome(result));
        assertEquals("aaacecaaa", solver.shortestPalindrome("aacecaaa"));
    }

    @Test public void testExample2() {
        String result = solver.shortestPalindrome("abcd");
        assertTrue(isPalindrome(result));
        // "dcb" + "abcd" = "dcbabcd"
        assertEquals("dcbabcd", result);
    }

    @Test public void testSingleChar() {
        assertEquals("a", solver.shortestPalindrome("a"));
    }

    @Test public void testEmptyString() {
        assertEquals("", solver.shortestPalindrome(""));
    }

    @Test public void testAlreadyPalindrome() {
        assertEquals("aba", solver.shortestPalindrome("aba"));
    }

    @Test public void testAllSameChars() {
        assertEquals("aaaa", solver.shortestPalindrome("aaaa"));
    }

    @Test public void testReversed() {
        // "aaaa" reversed is "aaaa", so shortest is "aaaa"
        assertEquals("aaaa", solver.shortestPalindrome("aaaa"));
    }

    @Test public void testPalindromePrefix() {
        // "abac" → longest prefix palindrome is "aba" (len 3)
        // prepend "c" → "cabac"
        String result = solver.shortestPalindrome("abac");
        assertTrue(isPalindrome(result));
        assertEquals("cabac", result);
    }

    @Test public void testNoPalindromePrefix() {
        // "abcde" → longest prefix palindrome is "a" (len 1)
        // prepend "edcb" → "edcbabcde"
        String result = solver.shortestPalindrome("abcde");
        assertTrue(isPalindrome(result));
        assertEquals("edcbabcde", result);
    }

    @Test public void testLongerString() {
        String result = solver.shortestPalindrome("abcdcba");
        assertTrue(isPalindrome(result));
        assertEquals("abcdcba", result);
    }

    @Test public void testGetTable() {
        String temp = "abc" + "#" + "cba";
        int[] table = solver.getTable(temp);
        assertEquals(1, table[table.length - 1]);
    }

    @Test public void testGetTablePalindrome() {
        String temp = "aba" + "#" + "aba";
        int[] table = solver.getTable(temp);
        assertEquals(3, table[table.length - 1]);
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
