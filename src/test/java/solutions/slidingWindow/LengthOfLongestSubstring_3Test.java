package solutions.slidingwindow;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:18
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstring_3Test {
    private final LengthOfLongestSubstring_3 test = new LengthOfLongestSubstring_3();

    @Test
    public void test() {
        assertEquals(3, test.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(5, test.lengthOfLongestSubstring("tmmzuxt"));
        assertEquals(1, test.lengthOfLongestSubstring("a"));
        assertEquals(3, test.lengthOfLongestSubstring("dvdf"));
        assertEquals(2, test.lengthOfLongestSubstring("cdd"));
        assertEquals(4, test.lengthOfLongestSubstring("abcd"));
    }

    @Test
    public void testNullAndEmptyInput() {
        assertEquals(0, test.lengthOfLongestSubstring(""));
        assertEquals(0, test.lengthOfLongestSubstring(null));
    }

    @Test
    public void testAllSameCharacters() {
        assertEquals(1, test.lengthOfLongestSubstring("bbbbbb"));
    }

    @Test
    public void testAllUniqueCharacters() {
        assertEquals(10, test.lengthOfLongestSubstring("abcdefghij"));
    }

    @Test
    public void testDuplicateAtWindowStartBoundary() {
        assertEquals(2, test.lengthOfLongestSubstring("abba"));
    }

    @Test
    public void testDuplicateAfterLongUniqueSpan() {
        assertEquals(9, test.lengthOfLongestSubstring("abcdxefgha"));
    }

    @Test
    public void testWhitespaceCharacters() {
        assertEquals(3, test.lengthOfLongestSubstring("a b a"));
    }

    @Test
    public void testSpecialCharacters() {
        assertEquals(5, test.lengthOfLongestSubstring("!@#$%!"));
    }

    @Test
    public void testCaseSensitivity() {
        assertEquals(6, test.lengthOfLongestSubstring("aAbBcC"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50_000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        assertEquals(26, test.lengthOfLongestSubstring(sb.toString()));
    }

    @Test
    public void testTwoSameChars() {
        assertEquals(1, test.lengthOfLongestSubstring("aa"));
    }

    @Test
    public void testTwoDifferentChars() {
        assertEquals(2, test.lengthOfLongestSubstring("ab"));
    }

    @Test
    public void testPwwkew() {
        assertEquals(3, test.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    public void testUnicodeChars() {
        assertEquals(4, test.lengthOfLongestSubstring("你好世界"));
        assertEquals(2, test.lengthOfLongestSubstring("你好你"));
        assertEquals(3, test.lengthOfLongestSubstring("a你b你"));
    }

    @Test
    public void testEachCharAppearsTwice() {
        // "aabbccdd" — each char appears exactly twice in sequence
        assertEquals(2, test.lengthOfLongestSubstring("aabbccdd"));
        // interleaved: "abcabc" — each appears twice, longest unique is 3
        assertEquals(3, test.lengthOfLongestSubstring("abcabc"));
    }

    @Test
    public void testStress10000CharsRandomSeed42() {
        Random rng = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append((char) ('a' + rng.nextInt(26)));
        }
        String s = sb.toString();

        // The quadratic set-based oracle remains small because each candidate
        // range stops at its first repeated character.
        assertEquals(bruteForce(s), test.lengthOfLongestSubstring(s));
    }

    @Test
    public void testWhitespaceAndTabs() {
        assertEquals(3, test.lengthOfLongestSubstring(" \t\n"));
        assertEquals(1, test.lengthOfLongestSubstring("  "));
    }

    private int bruteForce(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (!seen.add(s.charAt(j))) break;
                max = Math.max(max, j - i + 1);
            }
        }
        return max;
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            String s = random.ints(30, 32, 40).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            org.junit.jupiter.api.Assertions.assertEquals(bruteForce(s), new LengthOfLongestSubstring_3().lengthOfLongestSubstring(s));
        }
    }
}
