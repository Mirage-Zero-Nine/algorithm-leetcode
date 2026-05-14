package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StreamChecker_1032Test {

    @Test
    public void testHappyCases() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"cd", "f", "kl"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertFalse(sc.query('c'));
        assertTrue(sc.query('d'));
        assertFalse(sc.query('e'));
        assertTrue(sc.query('f'));
    }

    @Test
    public void testEdgeCases() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"a"});
        assertTrue(sc.query('a'));
        assertFalse(sc.query('b'));
    }

    @Test
    public void testLargeCase() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"abc", "de"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertTrue(sc.query('c'));
        assertFalse(sc.query('d'));
        assertTrue(sc.query('e'));
    }

    @Test
    public void testOverlappingWords() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"ab", "ba", "aba"});
        assertFalse(sc.query('a'));
        assertTrue(sc.query('b'));  // "ab" matches
        assertTrue(sc.query('a')); // "ba" matches
    }

    @Test
    public void testSingleCharWords() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"a", "b", "c"});
        assertTrue(sc.query('a'));
        assertTrue(sc.query('b'));
        assertTrue(sc.query('c'));
        assertFalse(sc.query('d'));
    }

    @Test
    public void testNoMatchEver() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"xyz"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertFalse(sc.query('c'));
        assertFalse(sc.query('d'));
    }

    @Test
    public void testRepeatedPattern() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"aa"});
        assertFalse(sc.query('a'));
        assertTrue(sc.query('a'));  // "aa"
        assertTrue(sc.query('a')); // "aa" again
    }

    @Test
    public void testLongWordMatch() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"abcde"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertFalse(sc.query('c'));
        assertFalse(sc.query('d'));
        assertTrue(sc.query('e'));  // "abcde" matches
    }

    @Test
    public void testWordAndPrefix() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"ab", "abc"});
        assertFalse(sc.query('a'));
        assertTrue(sc.query('b'));  // "ab" matches
        assertTrue(sc.query('c')); // "abc" matches
    }

    @Test
    public void testGiantCase() {
        // Build 100 words of length 10
        String[] words = new String[100];
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                sb.append((char) ('a' + (i + j) % 26));
            }
            words[i] = sb.toString();
        }
        StreamChecker_1032 sc = new StreamChecker_1032(words);
        // Query first word char by char
        String firstWord = words[0];
        for (int i = 0; i < firstWord.length() - 1; i++) {
            sc.query(firstWord.charAt(i)); // may or may not match other words
        }
        assertTrue(sc.query(firstWord.charAt(firstWord.length() - 1)));
    }
}
