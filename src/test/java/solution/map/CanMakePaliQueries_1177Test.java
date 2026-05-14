package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CanMakePaliQueries_1177Test {

    private final CanMakePaliQueries_1177 test = new CanMakePaliQueries_1177();

    @Test
    public void testHappyCases() {
        List<Boolean> result = test.canMakePaliQueries("abcda", new int[][]{{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}});
        assertEquals(List.of(true, false, false, true, true), result);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        List<Boolean> result = test.canMakePaliQueries("a", new int[][]{{0, 0, 0}});
        assertEquals(List.of(true), result);
        List<Boolean> result2 = test.canMakePaliQueries("ab", new int[][]{{0, 1, 0}});
        assertEquals(List.of(false), result2);
    }

    @Test
    public void testLargeCase() {
        List<Boolean> result = test.canMakePaliQueries("aaabbbccc", new int[][]{{0, 8, 4}, {0, 8, 0}});
        assertEquals(List.of(true, false), result);
    }

    @Test
    public void testSingleCharSubstring() {
        // Single char is always palindrome
        List<Boolean> result = test.canMakePaliQueries("abcdef", new int[][]{{0, 0, 0}, {3, 3, 0}, {5, 5, 0}});
        assertEquals(List.of(true, true, true), result);
    }

    @Test
    public void testAlreadyPalindrome() {
        // "aba" is already palindrome, no replacement needed
        List<Boolean> result = test.canMakePaliQueries("aba", new int[][]{{0, 2, 0}});
        assertEquals(List.of(true), result);
    }

    @Test
    public void testHighKValue() {
        // k > 13 always returns true
        List<Boolean> result = test.canMakePaliQueries("abcdefghij", new int[][]{{0, 9, 14}});
        assertEquals(List.of(true), result);
    }

    @Test
    public void testAllSameChars() {
        List<Boolean> result = test.canMakePaliQueries("aaaa", new int[][]{{0, 3, 0}, {0, 2, 0}, {1, 3, 0}});
        assertEquals(List.of(true, true, true), result);
    }

    @Test
    public void testTwoDistinctCharsNoReplacement() {
        // "ab" has 2 odd chars, need 1 replacement for palindrome
        List<Boolean> result = test.canMakePaliQueries("ab", new int[][]{{0, 1, 0}, {0, 1, 1}});
        assertEquals(List.of(false, true), result);
    }

    @Test
    public void testMultipleOddChars() {
        // "abcd" has 4 odd chars, need 2 replacements
        List<Boolean> result = test.canMakePaliQueries("abcd", new int[][]{{0, 3, 0}, {0, 3, 1}, {0, 3, 2}});
        assertEquals(List.of(false, false, true), result);
    }

    @Test
    public void testOddLengthSubstring() {
        // "abc" has 3 odd chars, odd length allows 1 center, so need 1 replacement
        List<Boolean> result = test.canMakePaliQueries("abc", new int[][]{{0, 2, 0}, {0, 2, 1}});
        assertEquals(List.of(false, true), result);
    }

    @Test
    public void testGiantCase() {
        // Large string with repeated pattern
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("abcde");
        }
        String s = sb.toString();
        // Full range: each of a-e appears 1000 times (even), so 0 odd chars -> palindrome possible with 0 replacements
        List<Boolean> result = test.canMakePaliQueries(s, new int[][]{{0, s.length() - 1, 0}});
        assertEquals(List.of(true), result);
    }
}
