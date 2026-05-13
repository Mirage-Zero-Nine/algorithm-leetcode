package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}