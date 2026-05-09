package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordDictionary211Test {

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
}
