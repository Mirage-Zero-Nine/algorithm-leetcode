package solution.slidingWindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import solution.slidingwindow.LengthOfLongestSubstring_3;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:18
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstring3Test {
    private final LengthOfLongestSubstring_3 test = new LengthOfLongestSubstring_3();

    @Test
    public void test() {
        Assertions.assertEquals(3, test.lengthOfLongestSubstring("abcabcbb"));
        Assertions.assertEquals(5, test.lengthOfLongestSubstring("tmmzuxt"));
        Assertions.assertEquals(1, test.lengthOfLongestSubstring("a"));
        Assertions.assertEquals(3, test.lengthOfLongestSubstring("dvdf"));
        Assertions.assertEquals(2, test.lengthOfLongestSubstring("cdd"));
        Assertions.assertEquals(4, test.lengthOfLongestSubstring("abcd"));
        Assertions.assertEquals(0, test.lengthOfLongestSubstring(""));
        Assertions.assertEquals(0, test.lengthOfLongestSubstring(null));
    }
}