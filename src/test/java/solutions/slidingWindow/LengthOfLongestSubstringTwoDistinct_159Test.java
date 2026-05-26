package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2024/12/01 11:09
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstringTwoDistinct_159Test {
    private final LengthOfLongestSubstringTwoDistinct_159 test = new LengthOfLongestSubstringTwoDistinct_159();

    @Test
    public void testEmpty() {
        assertEquals(0, test.lengthOfLongestSubstringTwoDistinct(null));
        assertEquals(0, test.lengthOfLongestSubstringTwoDistinct(""));
    }

    @Test
    public void testSingleCharacter() {
        assertEquals(1, test.lengthOfLongestSubstringTwoDistinct("a"));
        assertEquals(1, test.lengthOfLongestSubstringTwoDistinct("b"));
    }

    @Test
    public void testTwoCharacters() {
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("ab"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("ba"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("aa"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("bb"));
    }

    @Test
    public void testMoreThanTwoDistinctCharacters() {
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("abc"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("abcdef"));
        assertEquals(3, test.lengthOfLongestSubstringTwoDistinct("aab"));
        assertEquals(3, test.lengthOfLongestSubstringTwoDistinct("eceba"));
    }

    @Test
    public void testStringWithMultipleRepeatedSubstrings() {
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
        assertEquals(6, test.lengthOfLongestSubstringTwoDistinct("aabbaa"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("abcabcabc"));
        assertEquals(6, test.lengthOfLongestSubstringTwoDistinct("ababab"));
    }

    @Test
    public void testCornerCaseWithLongString() {
        assertEquals(12, test.lengthOfLongestSubstringTwoDistinct("aaaaaabbbbbb"));
        assertEquals(8, test.lengthOfLongestSubstringTwoDistinct("abababab"));
        assertEquals(16, test.lengthOfLongestSubstringTwoDistinct("abababababababab"));
    }

    @Test
    public void testAllSameCharacter() {
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("aaaaa"));
    }

    @Test
    public void testExactlyTwoDistinct() {
        assertEquals(10, test.lengthOfLongestSubstringTwoDistinct("aabbbbaaaa"));
    }

    @Test
    public void testThreeDistinctAtBoundary() {
        assertEquals(3, test.lengthOfLongestSubstringTwoDistinct("abcba"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) sb.append(i % 2 == 0 ? 'a' : 'b');
        assertEquals(50000, test.lengthOfLongestSubstringTwoDistinct(sb.toString()));
    }
}
