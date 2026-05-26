package solutions.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanConvert_1153Test {

    private final CanConvert_1153 test = new CanConvert_1153();

    @Test
    public void testHappyCases() {
        assertTrue(test.canConvert("aabcc", "ccdee"));
        assertFalse(test.canConvert("leetcode", "codeleet"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.canConvert("abc", "abc"));
        assertFalse(test.canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canConvert("aabbcc", "bbccdd"));
        assertTrue(test.canConvert("ab", "ba"));
    }

    @Test
    public void testIdenticalStrings() {
        assertTrue(test.canConvert("aaaa", "aaaa"));
    }

    @Test
    public void testSingleChar() {
        assertTrue(test.canConvert("a", "b"));
    }

    @Test
    public void testConflictingMapping() {
        // 'a' at index 0 maps to 'b', 'a' at index 1 maps to 'c' - impossible
        assertFalse(test.canConvert("aa", "bc"));
    }

    @Test
    public void testAllSameCharToSame() {
        assertTrue(test.canConvert("aaaa", "bbbb"));
    }

    @Test
    public void testAll26CharsUsedInTarget() {
        // All 26 chars used in target but not all in source - should be true
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            s1.append((char) ('a' + i));
            s2.append((char) ('b' + i));
        }
        s1.append('a');
        s2.append('b');
        // s1 has 25 unique chars, s2 has 25 unique chars => values size < 26
        assertTrue(test.canConvert(s1.toString(), s2.toString()));
    }

    @Test
    public void testCyclicMappingWithSpareChar() {
        // a->b, b->c, c->a: cyclic but if there's a spare char it works
        assertTrue(test.canConvert("abc", "bca"));
    }

    @Test
    public void testAll26CharsInSourceAndTarget() {
        // All 26 chars map to all 26 different chars => values.size() == 26 => false
        String s1 = "abcdefghijklmnopqrstuvwxyz";
        String s2 = "bcdefghijklmnopqrstuvwxyza";
        assertFalse(test.canConvert(s1, s2));
    }

    @Test
    public void testGiantCase() {
        // Large string with repeating pattern
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            s1.append('a');
            s2.append('b');
        }
        assertTrue(test.canConvert(s1.toString(), s2.toString()));
    }
}
