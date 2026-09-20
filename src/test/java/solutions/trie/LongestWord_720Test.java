package solutions.trie;

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

    @Test public void testOfficialTieExample() {
        assertEquals("apple", solver.longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    }

    @Test public void testOnlyOneCharacterChains() {
        assertEquals("x", solver.longestWord(new String[]{"z", "x", "y"}));
    }

    @Test public void testSeveralIndependentCompleteChains() {
        assertEquals("cat", solver.longestWord(new String[]{"c", "ca", "cat", "d", "do", "dog"}));
    }

    @Test public void testPrefixMissingAtFirstCharacter() {
        assertEquals("", solver.longestWord(new String[]{"aa", "aaa", "bcd"}));
    }

    @Test public void testPrefixMissingInMiddle() {
        assertEquals("ac", solver.longestWord(new String[]{"a", "ac", "acde"}));
    }

    @Test public void testLexicographicTieAtDeepLevel() {
        assertEquals("abca", solver.longestWord(new String[]{"a", "ab", "abc", "abca", "abd", "abde"}));
    }

    @Test public void testWordsMayBePresentedInAnyOrder() {
        assertEquals("world", solver.longestWord(new String[]{"world", "worl", "wo", "w", "world", "wor"}));
    }

    @Test public void testRepeatedCallsDoNotRetainTrieState() {
        assertEquals("abc", solver.longestWord(new String[]{"a", "ab", "abc"}));
        assertEquals("x", solver.longestWord(new String[]{"x", "xyx"}));
    }

    @Test public void testLengthThirtyChain() {
        String[] words = new String[30];
        for (int i = 0; i < words.length; i++) {
            words[i] = "a".repeat(i + 1);
        }
        assertEquals("a".repeat(30), solver.longestWord(words));
    }

    @Test public void testManyDictionaryEntriesWithOneValidChain() {
        String[] words = new String[1000];
        for (int i = 0; i < words.length; i++) {
            if (i < 20) {
                words[i] = "b".repeat(i + 1);
            } else {
                char[] unrelated = new char[30];
                unrelated[0] = 'd';
                int value = i;
                for (int j = 1; j < unrelated.length; j++) {
                    unrelated[j] = (char) ('a' + value % 26);
                    value /= 26;
                }
                words[i] = new String(unrelated);
            }
        }
        assertEquals("b".repeat(20), solver.longestWord(words));
    }

    @Test public void testEmptyAndUnusableWordsTogether() {
        assertEquals("a", solver.longestWord(new String[]{"a", "qwerty", "mnop", "zzzz"}));
    }
}
