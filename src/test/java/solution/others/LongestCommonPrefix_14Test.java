package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class LongestCommonPrefix_14Test {

    private final LongestCommonPrefix_14 test = new LongestCommonPrefix_14();

    @Test
    public void testHappyCases() {
        assertEquals("fl", test.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", test.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.longestCommonPrefix(null));
        assertEquals("abc", test.longestCommonPrefix(new String[]{"abc"}));
    }

    @Test
    public void testLargeCase() {
        assertEquals("a", test.longestCommonPrefix(new String[]{"abc", "abcd", "ab", "a"}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals("", test.longestCommonPrefix(new String[]{}));
    }

    @Test
    public void testEmptyStringInArray() {
        assertEquals("", test.longestCommonPrefix(new String[]{"", "abc"}));
    }

    @Test
    public void testAllSameStrings() {
        assertEquals("hello", test.longestCommonPrefix(new String[]{"hello", "hello", "hello"}));
    }

    @Test
    public void testNoCommonPrefix() {
        assertEquals("", test.longestCommonPrefix(new String[]{"abc", "xyz", "mno"}));
    }

    @Test
    public void testSingleCharStrings() {
        assertEquals("a", test.longestCommonPrefix(new String[]{"a", "a", "a"}));
    }

    @Test
    public void testFirstStringEmpty() {
        assertEquals("", test.longestCommonPrefix(new String[]{""}));
    }

    @Test
    public void testGiantCase() {
        String[] strs = new String[1000];
        for (int i = 0; i < 1000; i++) {
            strs[i] = "prefix" + "x".repeat(100) + i;
        }
        assertEquals("prefix" + "x".repeat(100), test.longestCommonPrefix(strs));
    }

    // --- NEW TESTS ---

    @Test
    public void testTwoStringsNoCommonPrefix() {
        assertEquals("", test.longestCommonPrefix(new String[]{"apple", "banana"}));
    }

    @Test
    public void testTwoStringsFullCommonPrefix() {
        assertEquals("hello", test.longestCommonPrefix(new String[]{"hello", "hello"}));
    }

    @Test
    public void testOneStringIsPrefixOfOthers() {
        assertEquals("ab", test.longestCommonPrefix(new String[]{"ab", "abc", "abcd", "abcde"}));
    }

    @Test
    public void testOneEmptyStringInMiddle() {
        assertEquals("", test.longestCommonPrefix(new String[]{"abc", "", "abcd"}));
    }

    @Test
    public void testStringsStartingDifferently() {
        assertEquals("", test.longestCommonPrefix(new String[]{"xray", "yellow", "zebra"}));
    }

    @Test
    public void testLongStrings1000Chars() {
        String base = "a".repeat(1000);
        String[] strs = {base, base + "b", base + "cd"};
        assertEquals(base, test.longestCommonPrefix(strs));
    }

    @Test
    public void testThousandStringsSeed42SharedPrefix() {
        Random rng = new Random(42L);
        String prefix = "commonprefix_";
        String[] strs = new String[1000];
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder(prefix);
            int suffixLen = rng.nextInt(50) + 1;
            for (int j = 0; j < suffixLen; j++) {
                sb.append((char) ('a' + rng.nextInt(26)));
            }
            strs[i] = sb.toString();
        }
        assertEquals(prefix, test.longestCommonPrefix(strs));
    }

    @Test
    public void testPropertyResultIsPrefixOfEveryInput() {
        String[] strs = {"interstellar", "internet", "internal", "inter"};
        String result = test.longestCommonPrefix(strs);
        for (String s : strs) {
            assertTrue(s.startsWith(result), "Result '" + result + "' should be prefix of '" + s + "'");
        }
    }

    @Test
    public void testSingleStringReturnsItself() {
        assertEquals("lonelyelement", test.longestCommonPrefix(new String[]{"lonelyelement"}));
    }

    @Test
    public void testAllIdenticalStrings() {
        assertEquals("same", test.longestCommonPrefix(new String[]{"same", "same", "same", "same", "same"}));
    }
}
