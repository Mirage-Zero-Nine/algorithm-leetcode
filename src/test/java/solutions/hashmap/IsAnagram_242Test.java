package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

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
        // Build actual anagram by reversing s.
        String s = sb1.toString();
        char[] arr = s.toCharArray();
        char[] rev = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            rev[arr.length - 1 - i] = arr[i];
        }
        String t = new String(rev);
        assertTrue(test.isAnagram(s, t));
    }

    /**
     * Table-driven anagram pairs (lowercase a-z only, since the int[]-based
     * isAnagram requires that constraint). Both implementations must agree.
     */
    @ParameterizedTest(name = "anagram: \"{0}\" vs \"{1}\"")
    @CsvSource({
            "'',         ''",
            "a,          a",
            "ab,         ba",
            "abc,        cba",
            "anagram,    nagaram",
            "listen,     silent",
            "rail,       liar",
            "evil,       vile",
            "elbow,      below",
            "study,      dusty",
            "aabbcc,     ccbbaa",
            "aabbcc,     bcabca",
            "abcdefghij, jihgfedcba",
            "racecar,    aaccerr"
    })
    public void testAnagramPairsBothImpls(String s, String t) {
        assertTrue(test.isAnagram(s, t),
                "isAnagram should accept anagram: " + s + " / " + t);
        assertTrue(test.isAnagramWithHashMap(s, t),
                "isAnagramWithHashMap should accept anagram: " + s + " / " + t);
    }

    /**
     * Table-driven non-anagrams. Same length but different multisets.
     */
    @ParameterizedTest(name = "not anagram: \"{0}\" vs \"{1}\"")
    @CsvSource({
            "a,         b",
            "ab,        cd",
            "rat,       car",
            "hello,     world",
            "anagram,   nagarom",
            "aabb,      abbb",
            "aaaa,      aaab",
            "abcdef,    abcdee",
            "aabbcc,    aabbcd"
    })
    public void testNonAnagramPairsBothImpls(String s, String t) {
        assertFalse(test.isAnagram(s, t),
                "isAnagram should reject non-anagram: " + s + " / " + t);
        assertFalse(test.isAnagramWithHashMap(s, t),
                "isAnagramWithHashMap should reject non-anagram: " + s + " / " + t);
    }

    /**
     * Strings of different lengths can never be anagrams.
     */
    @ParameterizedTest(name = "different lengths: \"{0}\" vs \"{1}\"")
    @CsvSource({
            "a,           ab",
            "ab,          abc",
            "abc,         abcd",
            "anagram,     nagaramx",
            "listen,      silently"
    })
    public void testDifferentLengthsNeverAnagram(String s, String t) {
        assertFalse(test.isAnagram(s, t));
        assertFalse(test.isAnagramWithHashMap(s, t));
        // Order-swapped variant of the same pair.
        assertFalse(test.isAnagram(t, s));
        assertFalse(test.isAnagramWithHashMap(t, s));
    }

    /**
     * isAnagramWithHashMap supports any character set (it doesn't assume
     * a-z). Verify that with a few unicode/uppercase pairs.
     */
    @ParameterizedTest(name = "unicode anagram: \"{0}\" vs \"{1}\"")
    @ValueSource(strings = {
            "ABCdef",
            "Hello!",
            "12345",
            "你好世界界世好你"
    })
    public void testHashMapImplHandlesUnicode(String s) {
        // Build a known anagram by reversing.
        String reversed = new StringBuilder(s).reverse().toString();
        assertTrue(test.isAnagramWithHashMap(s, reversed));
        if (s.length() > 1) {
            // Modify the last char to break the multiset; should now fail.
            char[] arr = reversed.toCharArray();
            arr[arr.length - 1] = arr[arr.length - 1] == 'X' ? 'Y' : 'X';
            assertFalse(test.isAnagramWithHashMap(s, new String(arr)));
        }
    }

    /**
     * Property test: a random shuffled lowercase string is always an
     * anagram of the original. Reproducible via fixed seed. Cross-checks
     * both implementations.
     */
    @Test
    public void testRandomShufflesAreAlwaysAnagrams() {
        Random rng = new Random(42L);
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int trial = 0; trial < 50; trial++) {
            int len = rng.nextInt(500) + 1;
            char[] arr = new char[len];
            for (int i = 0; i < len; i++) {
                arr[i] = alphabet[rng.nextInt(26)];
            }
            String s = new String(arr);
            // Fisher-Yates shuffle a copy.
            char[] shuffled = arr.clone();
            for (int i = shuffled.length - 1; i > 0; i--) {
                int j = rng.nextInt(i + 1);
                char tmp = shuffled[i];
                shuffled[i] = shuffled[j];
                shuffled[j] = tmp;
            }
            String t = new String(shuffled);
            assertTrue(test.isAnagram(s, t),
                    "trial " + trial + " (isAnagram): " + s + " vs " + t);
            assertTrue(test.isAnagramWithHashMap(s, t),
                    "trial " + trial + " (isAnagramWithHashMap): " + s + " vs " + t);
        }
    }

    /**
     * Both implementations must agree on every input (shared oracle).
     */
    @Test
    public void testBothImplementationsAgreeRandomized() {
        Random rng = new Random(7L);
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int trial = 0; trial < 100; trial++) {
            int len = rng.nextInt(50) + 1;
            char[] a = new char[len];
            char[] b = new char[len];
            for (int i = 0; i < len; i++) {
                a[i] = alphabet[rng.nextInt(26)];
                b[i] = alphabet[rng.nextInt(26)];
            }
            String s = new String(a), t = new String(b);
            assertEquals(test.isAnagram(s, t), test.isAnagramWithHashMap(s, t),
                    "implementations disagree on: " + s + " vs " + t);
        }
    }
}
