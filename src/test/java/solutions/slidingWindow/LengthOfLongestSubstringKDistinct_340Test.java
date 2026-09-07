package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2024/12/01 11:51
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstringKDistinct_340Test {
    private final LengthOfLongestSubstringKDistinct_340 test = new LengthOfLongestSubstringKDistinct_340();

    @Test
    public void testEmpty() {
        assertEquals(0, test.lengthOfLongestSubstringKDistinct(null, 100));
        assertEquals(0, test.lengthOfLongestSubstringKDistinct("", 100));
        assertEquals(0, test.lengthOfLongestSubstringKDistinctBucket(null, 100));
        assertEquals(0, test.lengthOfLongestSubstringKDistinctBucket("", 100));
    }

    @Test
    public void test() {
        assertEquals(3, test.lengthOfLongestSubstringKDistinctBucket("eceba", 2));
        assertEquals(2, test.lengthOfLongestSubstringKDistinctBucket("aa", 2));

        assertEquals(3, test.lengthOfLongestSubstringKDistinct("eceba", 2));
        assertEquals(2, test.lengthOfLongestSubstringKDistinct("aa", 2));
    }

    @Test
    public void testKZero() {
        assertBoth("abc", 0, 0);
    }

    @Test
    public void testKOne() {
        assertBoth("aaabbbcca", 1, 3);
    }

    @Test
    public void testKGreaterThanLength() {
        assertBoth("abcd", 10, 4);
    }

    @Test
    public void testAllSameCharacter() {
        assertBoth("zzzzzz", 2, 6);
    }

    @Test
    public void testAlternatingCharacters() {
        assertBoth("abababab", 2, 8);
    }

    @Test
    public void testThreeDistinctWithWindowShrink() {
        assertBoth("abcadcacacaca", 3, 11);
    }

    @Test
    public void testAsciiSpecialCharacters() {
        assertBoth("!!@@@##", 2, 5);
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append((char) ('a' + (i % 4)));
        }
        assertBoth(sb.toString(), 2, 2);
    }

    private void assertBoth(String s, int k, int expected) {
        assertEquals(expected, test.lengthOfLongestSubstringKDistinct(s, k));
        assertEquals(expected, test.lengthOfLongestSubstringKDistinctBucket(s, k));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            String s = random.ints(30, 32, 40).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            int k = random.nextInt(9), expected = 0;
            for (int left = 0; left < s.length(); left++) {
                java.util.Set<Character> seen = new java.util.HashSet<>();
                for (int right = left; right < s.length(); right++) {
                    seen.add(s.charAt(right));
                    if (seen.size() <= k) expected = Math.max(expected, right - left + 1);
                }
            }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new LengthOfLongestSubstringKDistinct_340().lengthOfLongestSubstringKDistinct(s, k));
            org.junit.jupiter.api.Assertions.assertEquals(expected, new LengthOfLongestSubstringKDistinct_340().lengthOfLongestSubstringKDistinctBucket(s, k));

        }
    }
}
