package solution.slidingWindow;


import org.junit.jupiter.api.Test;
import solution.slidingwindow.LengthOfLongestSubstring_3;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:18
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstring3Test {
    private final LengthOfLongestSubstring_3 test = new LengthOfLongestSubstring_3();

    @Test
    public void test() {
        assertEquals(3, test.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(5, test.lengthOfLongestSubstring("tmmzuxt"));
        assertEquals(1, test.lengthOfLongestSubstring("a"));
        assertEquals(3, test.lengthOfLongestSubstring("dvdf"));
        assertEquals(2, test.lengthOfLongestSubstring("cdd"));
        assertEquals(4, test.lengthOfLongestSubstring("abcd"));
        assertEquals(0, test.lengthOfLongestSubstring(""));
    }

    @Test
    public void testInvalid() {
        assertEquals(0, test.lengthOfLongestSubstring(""));
        assertEquals(0, test.lengthOfLongestSubstring(null));
    }
}