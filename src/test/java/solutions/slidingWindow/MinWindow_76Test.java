package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class MinWindow_76Test {

    private final MinWindow_76 solution = new MinWindow_76();

    @Test
    public void testBasicCase() {
        assertEquals("BANC", solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    public void testExactMatch() {
        assertEquals("a", solution.minWindow("a", "a"));
    }

    @Test
    public void testTIsEntireS() {
        assertEquals("abc", solution.minWindow("abc", "abc"));
    }

    @Test
    public void testDuplicateCharsInT() {
        assertEquals("aa", solution.minWindow("aa", "aa"));
    }

    @Test
    public void testNoValidWindow() {
        assertEquals("", solution.minWindow("a", "b"));
    }

    @Test
    public void testEmptyS() {
        assertEquals("", solution.minWindow("", "a"));
    }

    @Test
    public void testEmptyT() {
        assertEquals("", solution.minWindow("abc", ""));
    }

    @Test
    public void testTLongerThanS() {
        assertEquals("", solution.minWindow("a", "abc"));
    }

    @Test
    public void testWindowAtBeginning() {
        assertEquals("ab", solution.minWindow("abcdef", "ba"));
    }

    @Test
    public void testWindowAtEnd() {
        assertEquals("ef", solution.minWindow("abcdef", "fe"));
    }

    @Test
    public void testRepeatedChars() {
        assertEquals("ab", solution.minWindow("aabbbba", "ab"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            sb.append('a');
        }
        sb.append('b');
        // minimum window containing "ab" is "ab" at position 49999-50000
        assertEquals("ab", solution.minWindow(sb.toString(), "ab"));
    }

    @Test
    public void testTNotInS() {
        assertEquals("", solution.minWindow("abcdef", "xyz"));
    }

    @Test
    public void testMixedCaseSensitivity() {
        // 'a' and 'A' are different characters
        assertEquals("", solution.minWindow("aaa", "A"));
        assertEquals("AaBb", solution.minWindow("AaBbCc", "AaBb"));
    }

    @Test
    public void testLargeRandomSeed42CrossCheck() {
        Random rand = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append((char) ('a' + rand.nextInt(5))); // a-e
        }
        String s = sb.toString();
        String t = "abcde";

        String result = solution.minWindow(s, t);

        // Brute-force: find shortest window containing all chars of t
        String expected = bruteForceMinWindow(s, t);
        assertEquals(expected.length(), result.length());
    }

    @Test
    public void testPropertyResultContainsAllCharsOfT() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = solution.minWindow(s, t);

        int[] need = new int[256];
        for (char c : t.toCharArray()) need[c]++;
        for (char c : result.toCharArray()) need[c]--;
        for (int i = 0; i < 256; i++) {
            assertTrue(need[i] <= 0, "Result missing char: " + (char) i);
        }
    }

    @Test
    public void testPropertyResultIsShortestWindow() {
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        String result = solution.minWindow(s, t);

        // No shorter substring of s should contain all chars of t
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (j - i < result.length() && containsAll(s.substring(i, j), t)) {
                    throw new AssertionError("Found shorter window: " + s.substring(i, j));
                }
            }
        }
    }

    @Test
    public void testTWithDuplicatesMoreThanS() {
        assertEquals("", solution.minWindow("ab", "aab"));
    }

    @Test
    public void testMultipleValidWindowsPicksShortest() {
        assertEquals("ab", solution.minWindow("abba", "ab"));
    }

    @Test
    public void testSingleCharSAndT() {
        assertEquals("z", solution.minWindow("z", "z"));
        assertEquals("", solution.minWindow("z", "x"));
    }

    @Test
    public void testTDuplicatesScatteredInS() {
        assertEquals("adobecodeba", solution.minWindow("adobecodebanc", "aabc"));
    }

    private boolean containsAll(String window, String t) {
        int[] need = new int[256];
        for (char c : t.toCharArray()) need[c]++;
        for (char c : window.toCharArray()) need[c]--;
        for (int n : need) if (n > 0) return false;
        return true;
    }

    private String bruteForceMinWindow(String s, String t) {
        String best = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + t.length(); j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (containsAll(sub, t)) {
                    if (best.isEmpty() || sub.length() < best.length()) {
                        best = sub;
                    }
                    break; // no need to extend further from this i
                }
            }
        }
        return best;
    }
}
