package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindLongestWord524Test {

    private final FindLongestWord_524 test = new FindLongestWord_524();

    @Test
    public void testHappyCases() {
        assertEquals("apple", test.findLongestWord("abpcplea", List.of("ale", "apple", "monkey", "plea")));
        assertEquals("abc", test.findLongestWord("abce", List.of("abe", "abc")));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.findLongestWord("", List.of("a")));
        assertEquals("", test.findLongestWord("abc", List.of("xyz")));
    }

    @Test
    public void testLargeCase() {
        assertEquals("abc", test.findLongestWord("aabbcc", List.of("abc", "ab", "a")));
    }
}
