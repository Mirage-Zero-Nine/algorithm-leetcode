package solution.slidingwindow;

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
}
