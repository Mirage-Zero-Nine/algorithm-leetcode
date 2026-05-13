package solution.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LongestWord_720Test {
    private final LongestWord_720 solver = new LongestWord_720();

    @Test public void testExample1() {
        String[] words = {"w", "wo", "wor", "worl", "world"};
        assertEquals("world", solver.longestWord(words));
    }

    @Test public void testExample2() {
        // "band" needs "ba" (not in dict), "ana" needs "an" (not in dict)
        // Only "a" has all prefixes (none needed)
        String[] words = {"a", "banana", "ana", "ban", "band", "b"};
        assertEquals("a", solver.longestWord(words));
    }

    @Test public void testSingleChar() {
        String[] words = {"a"};
        assertEquals("a", solver.longestWord(words));
    }

    @Test public void testNoValidWord() {
        String[] words = {"abc", "abcd"};
        assertEquals("", solver.longestWord(words));
    }

    @Test public void testEmptyArray() {
        String[] words = {};
        assertEquals("", solver.longestWord(words));
    }

    @Test public void testLexicographicalTie() {
        String[] words = {"a", "ab", "abb", "b", "ba", "bab"};
        assertEquals("abb", solver.longestWord(words));
    }

    @Test public void testSameLengthLexicographic() {
        // "abc" needs "ab"+"a" (both in dict) ✓
        // "bac" needs "ba" (in dict) + "b" (in dict) ✓
        // Both length 3, "abc" < "bac" lexicographically
        String[] words = {"a", "ab", "abc", "b", "ba", "bac"};
        assertEquals("abc", solver.longestWord(words));
    }

    @Test public void testGapsInChain() {
        String[] words = {"a", "ab", "abc", "abcd", "abcde"};
        assertEquals("abcde", solver.longestWord(words));
    }

    @Test public void testMultipleStarts() {
        String[] words = {"a", "b", "c", "ab", "bc", "abc"};
        assertEquals("abc", solver.longestWord(words));
    }

    @Test public void testNonConsecutivePrefixes() {
        // "abc" needs "ab"+"a" (both in dict) ✓
        // "abd" needs "ab"+"a" (both in dict) ✓
        // Both length 3, "abc" < "abd" lexicographically
        String[] words = {"abc", "ab", "a", "abd", "ab"};
        assertEquals("abc", solver.longestWord(words));
    }
}
