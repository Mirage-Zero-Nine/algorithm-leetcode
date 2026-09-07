package solutions.slidingwindow;

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

    @Test
    public void testNoMatch() {
        assertEquals(List.of(), test.findAnagrams("abcdef", "xyz"));
    }

    @Test
    public void testExactMatch() {
        assertEquals(List.of(0), test.findAnagrams("abc", "abc"));
        assertEquals(List.of(0), test.findAnagrams("cba", "abc"));
    }

    @Test
    public void testSingleCharPattern() {
        assertEquals(List.of(0, 2, 4), test.findAnagrams("ababa", "a"));
    }

    @Test
    public void testPatternLongerThanString() {
        assertEquals(List.of(), test.findAnagrams("ab", "abc"));
    }

    @Test
    public void testNullInputs() {
        assertEquals(List.of(), test.findAnagrams(null, "a"));
        assertEquals(List.of(), test.findAnagrams("a", null));
    }

    @Test
    public void testSameLength() {
        assertEquals(List.of(0), test.findAnagrams("bca", "abc"));
        assertEquals(List.of(), test.findAnagrams("bcd", "abc"));
    }

    @Test
    public void testGiantCase() {
        // 100000 'a's, pattern "aa" -> every position from 0 to 99998
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append('a');
        List<Integer> result = test.findAnagrams(sb.toString(), "aa");
        assertEquals(99999, result.size());
        assertEquals(0, result.get(0));
        assertEquals(99998, result.get(result.size() - 1));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            String s = random.ints(30, 'a', 'e').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            String p = random.ints(1 + random.nextInt(6), 'a', 'e').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            char[] pattern = p.toCharArray();
            java.util.Arrays.sort(pattern);
            java.util.List<Integer> expected = new java.util.ArrayList<>();
            for (int left = 0; left + p.length() <= s.length(); left++) {
                char[] candidate = s.substring(left, left + p.length()).toCharArray();
                java.util.Arrays.sort(candidate);
                if (java.util.Arrays.equals(pattern, candidate)) expected.add(left);
            }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new FindAnagrams_438().findAnagrams(s, p), s + ", p=" + p);
        }
    }
}
