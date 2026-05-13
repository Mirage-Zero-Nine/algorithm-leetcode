package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class isIsomorphic_205Test {

    private final isIsomorphic_205 test = new isIsomorphic_205();

    @Test
    public void testHappyCase1() {
        assertTrue(test.isIsomorphic("egg", "add"));
    }

    @Test
    public void testHappyCase2() {
        assertTrue(test.isIsomorphic("paper", "title"));
    }

    @Test
    public void testHappyCase3() {
        assertTrue(test.isIsomorphic("abc", "def"));
    }

    @Test
    public void testHappyCase4() {
        assertTrue(test.isIsomorphic("aba", "cdc"));
    }

    @Test
    public void testNegativeCase1() {
        assertFalse(test.isIsomorphic("foo", "bar"));
    }

    @Test
    public void testNegativeCase2() {
        // Different characters in s mapping to same character in t
        assertFalse(test.isIsomorphic("ab", "aa"));
    }

    @Test
    public void testEdgeCaseEmptyStrings() {
        assertTrue(test.isIsomorphic("", ""));
    }

    @Test
    public void testEdgeCaseSingleCharacter() {
        assertTrue(test.isIsomorphic("a", "b"));
        assertTrue(test.isIsomorphic("a", "a"));
    }

    @Test
    public void testEdgeCaseSameCharacters() {
        assertTrue(test.isIsomorphic("aaaa", "bbbb"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            s.append((char) (i % 128));
            t.append((char) ((i + 1) % 128));
        }
        assertTrue(test.isIsomorphic(s.toString(), t.toString()));

        // Break it at the end
        s.append('a');
        t.append('b');
        s.append('a');
        t.append('c');
        assertFalse(test.isIsomorphic(s.toString(), t.toString()));
    }
}
