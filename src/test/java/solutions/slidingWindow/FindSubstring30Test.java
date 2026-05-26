package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindSubstring30Test {

    @Test
    public void testHappyCases() {
        List<Integer> result = new FindSubstring_30().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        assertTrue(result.contains(0) && result.contains(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        assertEquals(List.of(), new FindSubstring_30().findSubstring("", new String[]{"a"}));
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = new FindSubstring_30().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        assertTrue(result.contains(8));
    }

    @Test
    public void testNullString() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring(null, new String[]{"a"}));
    }

    @Test
    public void testNullWords() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("abc", null));
    }

    @Test
    public void testEmptyWords() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("abc", new String[]{}));
    }

    @Test
    public void testSingleCharWords() {
        List<Integer> result = new FindSubstring_30().findSubstring("abab", new String[]{"a", "b"});
        assertTrue(result.contains(0));
        assertTrue(result.contains(2));
    }

    @Test
    public void testNoMatch() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("abcdef", new String[]{"xyz"}));
    }

    @Test
    public void testExactMatch() {
        List<Integer> result = new FindSubstring_30().findSubstring("foobar", new String[]{"foo", "bar"});
        assertEquals(List.of(0), result);
    }

    @Test
    public void testGiantCase() {
        String s = "ab".repeat(5000);
        List<Integer> result = new FindSubstring_30().findSubstring(s, new String[]{"ab", "ab"});
        // every even index from 0 to 9996 should be a valid start
        assertTrue(result.size() > 0);
    }
}
