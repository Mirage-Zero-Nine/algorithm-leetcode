package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanPermutePalindrome_266Test {

    private final CanPermutePalindrome_266 test = new CanPermutePalindrome_266();

    @Test
    public void testHappyCases() {
        assertTrue(test.canPermutePalindrome("aab"));
        assertTrue(test.canPermutePalindrome("carerac"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canPermutePalindrome("code"));
        assertTrue(test.canPermutePalindrome("a"));
        assertTrue(test.canPermutePalindrome("aa"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canPermutePalindrome("aabbccddeeff"));
        assertTrue(test.canPermutePalindrome("aabbccddeeffg"));
        assertFalse(test.canPermutePalindrome("aabbccddeeffgh"));
    }

    @Test
    public void testEmptyString() {
        assertTrue(test.canPermutePalindrome(""));
    }

    @Test
    public void testSingleChar() {
        assertTrue(test.canPermutePalindrome("z"));
    }

    @Test
    public void testAllSameChars() {
        assertTrue(test.canPermutePalindrome("aaaa"));
        assertTrue(test.canPermutePalindrome("aaaaa"));
    }

    @Test
    public void testTwoDistinctCharsEvenLength() {
        assertTrue(test.canPermutePalindrome("abba"));
        assertTrue(test.canPermutePalindrome("aabb"));
    }

    @Test
    public void testNegativeAllDistinct() {
        assertFalse(test.canPermutePalindrome("abcd"));
        assertFalse(test.canPermutePalindrome("abcde"));
    }

    @Test
    public void testOddCountMiddleChar() {
        assertTrue(test.canPermutePalindrome("racecar"));
        assertTrue(test.canPermutePalindrome("aabbc"));
    }

    @Test
    public void testSpecialCharacters() {
        assertTrue(test.canPermutePalindrome("!!"));
        assertTrue(test.canPermutePalindrome("a!a"));
        assertFalse(test.canPermutePalindrome("a!b"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append((char) ('a' + (i % 26)));
            sb.append((char) ('a' + (i % 26)));
        }
        assertTrue(test.canPermutePalindrome(sb.toString()));
        sb.append('x');
        assertTrue(test.canPermutePalindrome(sb.toString()));
        sb.append('y');
        assertFalse(test.canPermutePalindrome(sb.toString()));
    }
}
