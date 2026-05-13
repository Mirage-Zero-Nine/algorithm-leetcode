package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WordDistance_244Test {

    @Test
    public void testHappyCases() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        assertEquals(3, wd.shortest("coding", "practice"));
        assertEquals(1, wd.shortest("makes", "coding"));
    }

    @Test
    public void testEdgeCases() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"a", "b"});
        assertEquals(1, wd.shortest("a", "b"));
    }

    @Test
    public void testLargeCase() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"a", "b", "c", "d", "e", "a"});
        assertEquals(1, wd.shortest("a", "b"));
        assertEquals(1, wd.shortest("a", "e"));
    }

    @Test
    public void testDuplicateWords() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"a", "c", "b", "a"});
        assertEquals(1, wd.shortest("a", "b"));
        assertEquals(1, wd.shortest("a", "c"));
    }

    @Test
    public void testAdjacentWords() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"hello", "world"});
        assertEquals(1, wd.shortest("hello", "world"));
    }

    @Test
    public void testFarApartWords() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"x", "a", "b", "c", "d", "y"});
        assertEquals(5, wd.shortest("x", "y"));
    }

    @Test
    public void testMultipleOccurrencesBothWords() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"a", "b", "c", "a", "b"});
        assertEquals(1, wd.shortest("a", "b"));
    }

    @Test
    public void testReversedOrder() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        assertEquals(3, wd.shortest("practice", "coding"));
        assertEquals(1, wd.shortest("coding", "makes"));
    }

    @Test
    public void testMultipleCallsSameInstance() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"a", "b", "c", "d", "e"});
        assertEquals(1, wd.shortest("a", "b"));
        assertEquals(2, wd.shortest("a", "c"));
        assertEquals(3, wd.shortest("a", "d"));
        assertEquals(4, wd.shortest("a", "e"));
        assertEquals(1, wd.shortest("d", "e"));
    }

    @Test
    public void testNegativeCaseWordsAtEnds() {
        WordDistance_244 wd = new WordDistance_244(new String[]{"first", "x", "x", "x", "x", "last"});
        assertEquals(5, wd.shortest("first", "last"));
    }

    @Test
    public void testGiantCase() {
        String[] words = new String[10000];
        for (int i = 0; i < 10000; i++) words[i] = "w" + (i % 100);
        WordDistance_244 wd = new WordDistance_244(words);
        assertEquals(1, wd.shortest("w0", "w1"));
        assertEquals(2, wd.shortest("w0", "w2"));
        // w0 at indices 0,100,200... w99 at indices 99,199,299...
        // closest: w99 at 99, w0 at 100 -> distance 1
        assertEquals(1, wd.shortest("w0", "w99"));
    }
}
