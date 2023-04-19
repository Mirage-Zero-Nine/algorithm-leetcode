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

public class Trie208Test {

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
}