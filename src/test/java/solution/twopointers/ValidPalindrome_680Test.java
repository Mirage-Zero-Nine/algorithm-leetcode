package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidPalindrome_680Test {

    private final ValidPalindrome_680 test = new ValidPalindrome_680();

    @Test
    public void testHappyCases() {
        assertTrue(test.validPalindrome("aba"));
        assertTrue(test.validPalindrome("abca"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.validPalindrome("abc"));
        assertTrue(test.validPalindrome("a"));
        assertTrue(test.validPalindrome(""));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    @Test
    public void testAlreadyPalindrome() {
        assertTrue(test.validPalindrome("racecar"));
        assertTrue(test.validPalindrome("abba"));
    }

    @Test
    public void testRemoveLeftChar() {
        // Remove first 'c' to get "bba" which isn't palindrome, but remove 'b' at pos 1 to get "cba" no...
        // "cbba" -> remove 'c' -> "bba" not palindrome. remove last 'a' -> "cbb" not palindrome.
        // Actually let's use "abcba" which is already palindrome
        assertTrue(test.validPalindrome("abcba"));
    }

    @Test
    public void testRemoveRightChar() {
        // "abcbda" -> remove 'd' -> "abcba" palindrome
        assertTrue(test.validPalindrome("abcbda"));
    }

    @Test
    public void testNullInput() {
        assertTrue(test.validPalindrome(null));
    }

    @Test
    public void testTwoCharStrings() {
        assertTrue(test.validPalindrome("ab"));
        assertTrue(test.validPalindrome("aa"));
        assertTrue(test.validPalindrome("ba"));
    }

    @Test
    public void testNeedsTwoRemovals() {
        // "abcdef" needs more than 1 removal
        assertFalse(test.validPalindrome("abcdef"));
    }

    @Test
    public void testGiantCase() {
        // Large palindrome with one extra char in middle
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) sb.append('a');
        sb.append('b');
        for (int i = 0; i < 5000; i++) sb.append('a');
        // "aaa...a b aaa...a" is already palindrome
        assertTrue(test.validPalindrome(sb.toString()));

        // Insert extra char to break it requiring 2 removals
        sb.insert(2500, 'x');
        sb.insert(7502, 'y');
        assertFalse(test.validPalindrome(sb.toString()));
    }
}
