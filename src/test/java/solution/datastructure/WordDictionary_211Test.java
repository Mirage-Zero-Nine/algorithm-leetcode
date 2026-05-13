package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordDictionary_211Test {

    @Test
    public void testHappyCases() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("bad"); wd.addWord("dad"); wd.addWord("mad");
        assertFalse(wd.search("pad"));
        assertTrue(wd.search("bad"));
        assertTrue(wd.search(".ad"));
        assertTrue(wd.search("b.."));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        WordDictionary_211 wd = new WordDictionary_211();
        assertFalse(wd.search("a"));
        wd.addWord("a");
        assertTrue(wd.search("a"));
        assertTrue(wd.search("."));
    }

    @Test
    public void testLargeCase() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("hello"); wd.addWord("world");
        assertTrue(wd.search("h...."));
        assertTrue(wd.search("....."));
        assertFalse(wd.search("......"));
    }

    @Test
    public void testAllDots() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("abc");
        assertTrue(wd.search("..."));
        assertFalse(wd.search(".."));
        assertFalse(wd.search("...."));
    }

    @Test
    public void testPrefixNotWord() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("apple");
        assertFalse(wd.search("app"));
        assertFalse(wd.search("appl"));
        assertTrue(wd.search("apple"));
    }

    @Test
    public void testEmptyDictionary() {
        WordDictionary_211 wd = new WordDictionary_211();
        assertFalse(wd.search("anything"));
        assertFalse(wd.search("."));
        assertFalse(wd.search("..."));
    }

    @Test
    public void testDotAtDifferentPositions() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("cat");
        assertTrue(wd.search(".at"));
        assertTrue(wd.search("c.t"));
        assertTrue(wd.search("ca."));
        assertFalse(wd.search(".."));
    }

    @Test
    public void testMultipleWordsOverlapping() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("a");
        wd.addWord("ab");
        wd.addWord("abc");
        assertTrue(wd.search("a"));
        assertTrue(wd.search("ab"));
        assertTrue(wd.search("abc"));
        assertTrue(wd.search("."));
        assertTrue(wd.search(".."));
        assertTrue(wd.search("..."));
        assertFalse(wd.search("...."));
    }

    @Test
    public void testDuplicateWords() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("test");
        wd.addWord("test");
        assertTrue(wd.search("test"));
        assertTrue(wd.search("...."));
    }

    @Test
    public void testGiantCase() {
        WordDictionary_211 wd = new WordDictionary_211();
        // Add 1000 words of length 5
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            int val = i;
            for (int j = 0; j < 5; j++) {
                sb.append((char) ('a' + val % 26));
                val /= 26;
            }
            wd.addWord(sb.toString());
        }
        // Search for the first word added: "aaaaa" (i=0)
        assertTrue(wd.search("aaaaa"));
        // Search with dots
        assertTrue(wd.search("a...."));
        // Search for something that doesn't exist
        assertFalse(wd.search("zzzzz"));
    }
}
