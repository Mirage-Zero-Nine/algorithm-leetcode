package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsIsomorphic205Test {

    private final isIsomorphic_205 test = new isIsomorphic_205();

    @Test
    public void testHappyCases() {
        assertTrue(test.isIsomorphic("egg", "add"));
        assertFalse(test.isIsomorphic("foo", "bar"));
        assertTrue(test.isIsomorphic("paper", "title"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isIsomorphic("ab", "aa"));
        assertTrue(test.isIsomorphic("a", "a"));
        assertTrue(test.isIsomorphic("", ""));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isIsomorphic("abcdefg", "hijklmn"));
        assertFalse(test.isIsomorphic("abcdefg", "hijklmm"));
    }

    @Test
    public void testSameCharacters() {
        assertTrue(test.isIsomorphic("aaa", "bbb"));
    }

    @Test
    public void testSelfMapping() {
        assertTrue(test.isIsomorphic("abc", "abc"));
    }

    @Test
    public void testReverseMapping() {
        assertTrue(test.isIsomorphic("ab", "ba"));
    }

    @Test
    public void testTwoCharsMappedToSame() {
        assertFalse(test.isIsomorphic("ab", "aa"));
    }

    @Test
    public void testLongerStrings() {
        assertTrue(test.isIsomorphic("abab", "cdcd"));
        assertFalse(test.isIsomorphic("abab", "cddc"));
    }

    @Test
    public void testSpecialCharacters() {
        assertTrue(test.isIsomorphic("12", "ab"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            s.append((char) ('a' + (i % 26)));
            t.append((char) ('A' + (i % 26)));
        }
        assertTrue(test.isIsomorphic(s.toString(), t.toString()));
    }
}
