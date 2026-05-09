package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindAnagrams438Test {

    private final FindAnagrams_438 test = new FindAnagrams_438();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(0, 6), test.findAnagrams("cbaebabacd", "abc"));
        assertEquals(List.of(0, 1, 2), test.findAnagrams("abab", "ab"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.findAnagrams("aa", "bb"));
        assertEquals(List.of(), test.findAnagrams("", "a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8), test.findAnagrams("aaaaaaaaaa", "aa"));
    }
}
