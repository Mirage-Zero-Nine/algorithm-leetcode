package solution.slidingwindow;


import org.junit.jupiter.api.Test;
import solution.slidingwindow.LengthOfLongestSubstring_3;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:18
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstring_3Test {
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

    @Test
    public void testAllSameCharacters() {
        assertEquals(1, test.lengthOfLongestSubstring("bbbbbb"));
    }

    @Test
    public void testAllUniqueCharacters() {
        assertEquals(10, test.lengthOfLongestSubstring("abcdefghij"));
    }

    @Test
    public void testDuplicateAtWindowStartBoundary() {
        assertEquals(2, test.lengthOfLongestSubstring("abba"));
    }

    @Test
    public void testDuplicateAfterLongUniqueSpan() {
        assertEquals(9, test.lengthOfLongestSubstring("abcdxefgha"));
    }

    @Test
    public void testWhitespaceCharacters() {
        assertEquals(3, test.lengthOfLongestSubstring("a b a"));
    }

    @Test
    public void testSpecialCharacters() {
        assertEquals(5, test.lengthOfLongestSubstring("!@#$%!"));
    }

    @Test
    public void testCaseSensitivity() {
        assertEquals(6, test.lengthOfLongestSubstring("aAbBcC"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        assertEquals(26, test.lengthOfLongestSubstring(sb.toString()));
    }
}
