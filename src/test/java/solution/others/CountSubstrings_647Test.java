package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class CountSubstrings_647Test {

    private final CountSubstrings_647 test = new CountSubstrings_647();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.countSubstrings("abc"));
        assertEquals(6, test.countSubstrings("aaa"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countSubstrings(""));
        assertEquals(1, test.countSubstrings("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, test.countSubstrings("abcba"));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, test.countSubstrings(null));
    }

    @Test
    public void testTwoSameChars() {
        assertEquals(3, test.countSubstrings("aa"));
    }

    @Test
    public void testEvenPalindrome() {
        assertEquals(6, test.countSubstrings("abba"));
    }

    @Test
    public void testAllSameChars() {
        // "aaaa" -> a(4) + aa(3) + aaa(2) + aaaa(1) = 10
        assertEquals(10, test.countSubstrings("aaaa"));
    }

    @Test
    public void testNoPalindromesBeyondSingle() {
        assertEquals(4, test.countSubstrings("abcd"));
    }

    @Test
    public void testDPMethod() {
        assertEquals(3, test.countSubstringsDP("abc"));
        assertEquals(6, test.countSubstringsDP("aaa"));
        assertEquals(7, test.countSubstringsDP("abcba"));
    }

    @Test
    public void testManacherMethod() {
        assertEquals(3, test.countSubstringsManacher("abc"));
        assertEquals(6, test.countSubstringsManacher("aaa"));
        assertEquals(7, test.countSubstringsManacher("abcba"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) sb.append('a');
        // n*(n+1)/2 = 100*101/2 = 5050
        assertEquals(5050, test.countSubstrings(sb.toString()));
    }

    @Test
    public void testTwoDifferentChars() {
        // "ab" -> 'a', 'b' = 2
        assertEquals(2, test.countSubstrings("ab"));
    }

    @Test
    public void testAllSameNFormula() {
        // N*(N+1)/2 for all-same strings of various lengths
        for (int n = 1; n <= 20; n++) {
            String s = "b".repeat(n);
            assertEquals(n * (n + 1) / 2, test.countSubstrings(s));
        }
    }

    @Test
    public void testLongPalindromeInterior() {
        // "racecar" length 7: single chars(7) + "aceca"(3) + "cec"(1) + "racecar"(1) + "aa" no...
        // brute force check
        String s = "racecar";
        assertEquals(bruteForceCount(s), test.countSubstrings(s));
    }

    @Test
    public void testLargeRandomCrossCheckBruteForce() {
        Random rng = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append((char) ('a' + rng.nextInt(26)));
        }
        String s = sb.toString();
        assertEquals(bruteForceCount(s), test.countSubstrings(s));
    }

    @Test
    public void testPropertyCountBounds() {
        // For any string: n <= count <= n*(n+1)/2
        Random rng = new Random(123L);
        for (int t = 0; t < 50; t++) {
            int n = rng.nextInt(50) + 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) sb.append((char) ('a' + rng.nextInt(26)));
            String s = sb.toString();
            int count = test.countSubstrings(s);
            assertTrue(count >= n, "count should be >= string length");
            assertTrue(count <= n * (n + 1) / 2, "count should be <= n*(n+1)/2");
        }
    }

    @Test
    public void testAllThreeMethodsAgree() {
        // Cross-check all three implementations on various inputs
        String[] inputs = {"", "a", "aa", "ab", "abc", "aaa", "abba", "abcba", "racecar", "abacaba"};
        for (String s : inputs) {
            int expand = test.countSubstrings(s);
            int dp = test.countSubstringsDP(s);
            int manacher = test.countSubstringsManacher(s);
            assertEquals(expand, dp, "DP disagrees on: " + s);
            assertEquals(expand, manacher, "Manacher disagrees on: " + s);
        }
    }

    @Test
    public void testRepeatingPattern() {
        // "ababab" - has palindromes like "aba", "bab", "ababa", "babab" etc.
        String s = "ababab";
        assertEquals(bruteForceCount(s), test.countSubstrings(s));
    }

    @Test
    public void testSingleCharRepeatedLarge() {
        // 50 chars of 'z' -> 50*51/2 = 1275
        assertEquals(1275, test.countSubstrings("z".repeat(50)));
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }

    private int bruteForceCount(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) count++;
            }
        }
        return count;
    }
}
