package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthOfLongestSubstringTwoDistinct_159Test {

    private final LengthOfLongestSubstringTwoDistinct_159 test = new LengthOfLongestSubstringTwoDistinct_159();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.lengthOfLongestSubstringTwoDistinct("eceba"));
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.lengthOfLongestSubstringTwoDistinct("a"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.lengthOfLongestSubstringTwoDistinct("aabbccaabb"));
    }

    @Test
    public void testAllSameChars() {
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("aaaaa"));
    }

    @Test
    public void testTwoDistinctCharsOnly() {
        assertEquals(6, test.lengthOfLongestSubstringTwoDistinct("ababab"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, test.lengthOfLongestSubstringTwoDistinct(""));
    }

    @Test
    public void testThreeDistinctAlternating() {
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("abcabc"));
    }

    @Test
    public void testLongRunAtEnd() {
        assertEquals(8, test.lengthOfLongestSubstringTwoDistinct("abcbbbbbb"));
    }

    @Test
    public void testTwoCharsString() {
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("ac"));
    }

    @Test
    public void testGiantCase() {
        // 5000 'a' + 5000 'b' = 10000 chars, all two distinct
        String s = "a".repeat(5000) + "b".repeat(5000);
        assertEquals(10000, test.lengthOfLongestSubstringTwoDistinct(s));
    }
}
