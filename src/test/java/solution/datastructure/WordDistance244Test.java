package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WordDistance244Test {

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
}
