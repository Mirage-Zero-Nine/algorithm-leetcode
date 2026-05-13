package solution.anagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsAnagram_242Test {

    private final IsAnagram_242 test = new IsAnagram_242();

    @Test
    public void testHappyCases() {
        assertTrue(test.isAnagram("anagram", "nagaram"));
        assertTrue(test.isAnagramWithHashMap("listen", "silent"));
    }

    @Test
    public void testNegativeCases() {
        assertFalse(test.isAnagram("rat", "car"));
        assertFalse(test.isAnagramWithHashMap("hello", "world"));
    }

    @Test
    public void testInvalidCases() {
        assertThrows(NullPointerException.class, () -> test.isAnagram(null, "a"));
        assertThrows(NullPointerException.class, () -> test.isAnagramWithHashMap("a", null));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.isAnagram("", ""));
        assertTrue(test.isAnagramWithHashMap("a", "a"));
        assertFalse(test.isAnagram("ab", "a"));
    }

    @Test
    public void testLargeCase() {
        String s = "a".repeat(50) + "b".repeat(40) + "c".repeat(30);
        String t = "c".repeat(30) + "b".repeat(40) + "a".repeat(50);
        assertTrue(test.isAnagram(s, t));
        assertTrue(test.isAnagramWithHashMap(s, t));
    }

    @Test
    public void testSameString() {
        assertTrue(test.isAnagram("abcdef", "abcdef"));
        assertTrue(test.isAnagramWithHashMap("abcdef", "abcdef"));
    }

    @Test
    public void testSingleCharDifferent() {
        assertFalse(test.isAnagram("a", "b"));
        assertFalse(test.isAnagramWithHashMap("a", "b"));
    }

    @Test
    public void testDifferentLengths() {
        assertFalse(test.isAnagram("abc", "abcd"));
        assertFalse(test.isAnagramWithHashMap("abc", "abcd"));
    }

    @Test
    public void testRepeatedChars() {
        assertTrue(test.isAnagram("aabb", "baba"));
        assertFalse(test.isAnagram("aabb", "abbb"));
    }

    @Test
    public void testAllSameChar() {
        assertTrue(test.isAnagram("aaaa", "aaaa"));
        assertFalse(test.isAnagram("aaaa", "aaab"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb1.append((char) ('a' + i % 26));
            sb2.append((char) ('a' + (25 - i % 26)));
        }
        // These are NOT anagrams since distribution differs
        // Build actual anagram
        String s = sb1.toString();
        char[] arr = s.toCharArray();
        // reverse to make anagram
        char[] rev = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            rev[arr.length - 1 - i] = arr[i];
        }
        String t = new String(rev);
        assertTrue(test.isAnagram(s, t));
    }
}
