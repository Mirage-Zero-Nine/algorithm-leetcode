package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class StrStr_28Test {

    private final StrStr_28 test = new StrStr_28();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.strStr("hello", "ll"));
        assertEquals(0, test.strStr("hello", "hello"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.strStr("hello", ""));
        assertEquals(-1, test.strStr("", "a"));
        assertEquals(-1, test.strStr("aaaaa", "bba"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.strStr("mississippi", "issip"));
    }

    @Test
    public void testNeedleLongerThanHaystack() {
        assertEquals(-1, test.strStr("ab", "abc"));
    }

    @Test
    public void testSingleCharMatch() {
        assertEquals(0, test.strStr("a", "a"));
    }

    @Test
    public void testSingleCharNoMatch() {
        assertEquals(-1, test.strStr("a", "b"));
    }

    @Test
    public void testMatchAtEnd() {
        assertEquals(3, test.strStr("abcdef", "def"));
    }

    @Test
    public void testRepeatedPattern() {
        assertEquals(0, test.strStr("aaa", "a"));
    }

    @Test
    public void testBothEmpty() {
        assertEquals(0, test.strStr("", ""));
    }

    @Test
    public void testGiantCase() {
        String haystack = "a".repeat(10000) + "b";
        assertEquals(-1, test.strStr(haystack, "ab" + "b".repeat(100)));
        assertEquals(9999, test.strStr(haystack, "ab"));
    }

    @Test
    public void testNeedleEqualsHaystack() {
        assertEquals(0, test.strStr("abc", "abc"));
        assertEquals(0, test.strStr("x", "x"));
    }

    @Test
    public void testNeedleAtMiddle() {
        assertEquals(3, test.strStr("abcdefgh", "def"));
    }

    @Test
    public void testNeedleAtStart() {
        assertEquals(0, test.strStr("abcdef", "abc"));
    }

    @Test
    public void testOverlappingPrefixSuffix() {
        assertEquals(0, test.strStr("aabaabaab", "aabaab"));
    }

    @Test
    public void testAllSameCharFirstIndex() {
        assertEquals(0, test.strStr("aaaaaaa", "aaa"));
    }

    @Test
    public void testSingleCharHaystackNoMatch() {
        assertEquals(-1, test.strStr("a", "ab"));
    }

    @Test
    public void testLargeRandomHaystackWithKnownNeedle() {
        Random rng = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append((char) ('a' + rng.nextInt(26)));
        }
        String needle = "xyzxyz";
        int insertPos = 7777;
        sb.insert(insertPos, needle);
        String haystack = sb.toString();
        int result = test.strStr(haystack, needle);
        assertTrue(result >= 0 && result <= insertPos);
        assertEquals(needle, haystack.substring(result, result + needle.length()));
    }

    @Test
    public void testPropertyResultValid() {
        String[][] cases = {
            {"hello world", "world"}, {"abcabc", "cab"}, {"aaaa", "bba"},
            {"needle", "needle"}, {"short", "longneedle"}
        };
        for (String[] c : cases) {
            int result = test.strStr(c[0], c[1]);
            assertTrue(result == -1 || c[0].substring(result, result + c[1].length()).equals(c[1]),
                "Property violated for haystack='" + c[0] + "' needle='" + c[1] + "'");
        }
    }
}
