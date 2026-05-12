package solution.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumMatchingSubseq792Test {
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
}
