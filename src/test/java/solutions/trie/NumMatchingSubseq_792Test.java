package solutions.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumMatchingSubseq_792Test {
    private final NumMatchingSubseq_792 solver = new NumMatchingSubseq_792();

    // === numMatchingSubseq ===

    @Test public void testExample1() {
        assertEquals(2, solver.numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}));
    }

    @Test public void testExample2() {
        assertEquals(3, solver.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}));
    }

    @Test public void testAllMatch() {
        assertEquals(3, solver.numMatchingSubseq("abc", new String[]{"a", "b", "c"}));
    }

    @Test public void testNoneMatch() {
        assertEquals(0, solver.numMatchingSubseq("abc", new String[]{"d", "e", "f"}));
    }

    @Test public void testEmptyWords() {
        assertEquals(0, solver.numMatchingSubseq("abc", new String[]{}));
    }

    @Test public void testEmptyString() {
        assertEquals(0, solver.numMatchingSubseq("", new String[]{"a", "b"}));
    }

    @Test public void testSingleCharMatch() {
        assertEquals(1, solver.numMatchingSubseq("a", new String[]{"a"}));
    }

    @Test public void testSingleCharNoMatch() {
        assertEquals(0, solver.numMatchingSubseq("a", new String[]{"b"}));
    }

    @Test public void testDuplicateWords() {
        assertEquals(4, solver.numMatchingSubseq("abc", new String[]{"a", "a", "a", "a"}));
    }

    @Test public void testLongerWordInShorterString() {
        assertEquals(0, solver.numMatchingSubseq("ab", new String[]{"abc"}));
    }

    // === numMatchingSubseqIterator ===

    @Test public void testIteratorExample1() {
        assertEquals(2, solver.numMatchingSubseqIterator("dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}));
    }

    @Test public void testIteratorExample2() {
        assertEquals(3, solver.numMatchingSubseqIterator("abcde", new String[]{"a", "bb", "acd", "ace"}));
    }

    @Test public void testIteratorAllMatch() {
        assertEquals(3, solver.numMatchingSubseqIterator("abc", new String[]{"a", "b", "c"}));
    }

    @Test public void testIteratorNoneMatch() {
        assertEquals(0, solver.numMatchingSubseqIterator("abc", new String[]{"d", "e", "f"}));
    }

    @Test public void testIteratorEmptyWords() {
        assertEquals(0, solver.numMatchingSubseqIterator("abc", new String[]{}));
    }

    private void assertBoth(String s, String[] words, int expected) {
        assertEquals(expected, solver.numMatchingSubseq(s, words));
        assertEquals(expected, solver.numMatchingSubseqIterator(s, words));
    }

    @Test public void testBothApproachesHandleInterleaving() {
        assertBoth("abracadabra", new String[]{"abra", "ada", "aaa", "bar", "cad", "ra"}, 6);
    }

    @Test public void testBothApproachesCountDuplicateAndRepeatedWords() {
        assertBoth("aaaa", new String[]{"a", "a", "aa", "aa", "aaa", "aaaa", "aaaaa"}, 6);
    }

    @Test public void testBothApproachesHandleOrderFailures() {
        assertBoth("abc", new String[]{"ac", "ba", "ca", "abc", "cba", "ab"}, 3);
    }

    @Test public void testBothApproachesHandleLongRepeatedSource() {
        String source = "a".repeat(50_000);
        assertBoth(source, new String[]{"a", "a".repeat(50), "a".repeat(50_000), "a".repeat(50_001), "b"}, 3);
    }

    @Test public void testBothApproachesHandleEverySingleLetter() {
        assertBoth("abcdefghijklmnopqrstuvwxyz", new String[]{"a", "m", "z", "aa", "za", "zy", "abc", "az"}, 5);
    }

    @Test public void testBothApproachesHandleRepeatedCalls() {
        String[] first = {"a", "abc", "d"};
        String[] second = {"x", "xx", "yx"};
        assertBoth("abcd", first, 3);
        assertBoth("xyz", second, 1);
    }
}
