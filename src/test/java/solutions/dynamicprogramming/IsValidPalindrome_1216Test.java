package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsValidPalindrome_1216Test {

    private final IsValidPalindrome_1216 test = new IsValidPalindrome_1216();

    @Test
    public void testHappyCases() {
        assertTrue(test.isValidPalindrome("abcdeca", 2));
        assertTrue(test.isValidPalindrome("abbababa", 1));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.isValidPalindrome("a", 0));
        assertTrue(test.isValidPalindrome("ab", 1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isValidPalindrome("abcdefedcba", 0));
    }

    @Test
    public void testAlreadyPalindrome() {
        assertTrue(test.isValidPalindrome("racecar", 0));
    }

    @Test
    public void testNotPalindromeKZero() {
        assertFalse(test.isValidPalindrome("abcdef", 0));
    }

    @Test
    public void testKEqualsLength() {
        assertTrue(test.isValidPalindrome("abcdef", 6));
    }

    @Test
    public void testEmptyString() {
        assertTrue(test.isValidPalindrome("", 0));
    }

    @Test
    public void testKTooSmall() {
        assertFalse(test.isValidPalindrome("abcdef", 2));
    }

    @Test
    public void testTwoCharsNoRemoval() {
        assertTrue(test.isValidPalindrome("aa", 0));
        assertFalse(test.isValidPalindrome("ab", 0));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append((char) ('a' + i % 26));
        // removing all but 1 char makes it a palindrome, so k = length - 1 always works
        assertTrue(test.isValidPalindrome(sb.toString(), 499));
    }
}
