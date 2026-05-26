package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/18 17:33
 * Created with IntelliJ IDEA
 */

public class Trie_208Test {

    private Trie_208 test;

    @BeforeEach
    public void setUp() {
        test = new Trie_208();
    }

    @Test
    public void test() {
        test.insert("apple");
        assertTrue(test.search("apple"));
        test.insert("application");
        assertTrue(test.search("application"));
        test.insert("app");
        assertTrue(test.search("app"));
    }


    @Test
    public void testSearch() {
        test.insert("apple");
        assertTrue(test.search("apple"));
        assertFalse(test.search("banana"));
        assertFalse(test.search("app"));
        test.insert("application");
        assertTrue(test.search("application"));
    }

    @Test
    public void testStartsWith() {
        test.insert("hello");
        assertTrue(test.startsWith("he"));
        assertFalse(test.startsWith("hi"));
    }

    @Test
    public void testSearchNotFound() {
        test.insert("cat");
        assertFalse(test.search("car"));
        assertFalse(test.search("cats"));
        assertFalse(test.search("ca"));
    }

    @Test
    public void testStartsWithMultipleWords() {
        test.insert("apple");
        test.insert("application");
        test.insert("appetite");
        assertTrue(test.startsWith("app"));
        assertTrue(test.startsWith("appl"));
        assertTrue(test.startsWith("a"));
        assertFalse(test.startsWith("b"));
    }

    @Test
    public void testSingleCharacter() {
        test.insert("a");
        assertTrue(test.search("a"));
        assertTrue(test.startsWith("a"));
        assertFalse(test.search("b"));
    }

    @Test
    public void testPrefixIsNotWord() {
        test.insert("apple");
        assertFalse(test.search("app"));
        assertTrue(test.startsWith("app"));
    }

    @Test
    public void testInsertDuplicate() {
        test.insert("word");
        test.insert("word");
        assertTrue(test.search("word"));
    }

    @Test
    public void testEmptyTrieSearch() {
        assertFalse(test.search("anything"));
        assertFalse(test.startsWith("a"));
    }

    @Test
    public void testOverlappingWords() {
        test.insert("abc");
        test.insert("ab");
        test.insert("a");
        assertTrue(test.search("a"));
        assertTrue(test.search("ab"));
        assertTrue(test.search("abc"));
        assertFalse(test.search("abcd"));
    }

    @Test
    public void testGiantCase() {
        // Insert 1000 words: "a", "aa", "aaa", ..., up to length 1000
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('a' + (i % 26)));
            test.insert(sb.toString());
        }
        assertTrue(test.search("a"));
        assertTrue(test.startsWith("a"));
        assertTrue(test.search(sb.toString()));
        assertFalse(test.search(sb.toString() + "z"));
    }

    @Test
    public void testEmptyStringInsertAndSearch() {
        // Empty string: insert marks root as end-of-word
        test.insert("");
        assertTrue(test.search(""));
        assertTrue(test.startsWith(""));
    }

    @Test
    public void testEmptyTrieStartsWithEmpty() {
        // Empty prefix on empty trie — root node always exists
        assertTrue(test.startsWith(""));
        assertFalse(test.search(""));
    }

    @Test
    public void testWordsSharingPrefix() {
        test.insert("app");
        test.insert("apple");
        assertTrue(test.search("app"));
        assertTrue(test.search("apple"));
        assertTrue(test.startsWith("app"));
        assertTrue(test.startsWith("appl"));
        assertFalse(test.search("ap"));
    }

    @Test
    public void testStartsWithFullWord() {
        test.insert("hello");
        // startsWith the full word should return true
        assertTrue(test.startsWith("hello"));
    }

    @Test
    public void testLongWord1000Chars() {
        String longWord = "a".repeat(1000);
        test.insert(longWord);
        assertTrue(test.search(longWord));
        assertTrue(test.startsWith(longWord));
        assertFalse(test.search(longWord + "a"));
        assertTrue(test.startsWith("a".repeat(500)));
        assertFalse(test.search("a".repeat(999)));
    }

    @Test
    public void testManyRandomWords() {
        Random rng = new Random(42L);
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int len = rng.nextInt(20) + 1;
            StringBuilder sb = new StringBuilder(len);
            for (int j = 0; j < len; j++) {
                sb.append((char) ('a' + rng.nextInt(26)));
            }
            words.add(sb.toString());
            test.insert(sb.toString());
        }
        for (String w : words) {
            assertTrue(test.search(w), "Should find: " + w);
            assertTrue(test.startsWith(w.substring(0, 1)), "Prefix should match: " + w);
        }
    }

    @Test
    public void testSearchAfterPartialInsertSequence() {
        // Insert words one by one, verify intermediate state
        assertFalse(test.search("ant"));
        test.insert("ant");
        assertTrue(test.search("ant"));
        assertFalse(test.search("ante"));
        test.insert("ante");
        assertTrue(test.search("ante"));
        assertTrue(test.search("ant"));
        assertFalse(test.search("anti"));
    }

    @Test
    public void testPrefixOfAnotherWordNotSearchable() {
        // Insert only the longer word; shorter prefix should not be searchable
        test.insert("application");
        assertFalse(test.search("app"));
        assertFalse(test.search("appli"));
        assertFalse(test.search("applicatio"));
        assertTrue(test.startsWith("app"));
        assertTrue(test.startsWith("applicatio"));
        assertTrue(test.search("application"));
    }
}