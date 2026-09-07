package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EqualSubstring1208Test {

    private final EqualSubstring_1208 test = new EqualSubstring_1208();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.equalSubstring("abcd", "bcdf", 3));
        assertEquals(1, test.equalSubstring("abcd", "cdef", 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.equalSubstring("abcd", "acde", 0));
        assertEquals(1, test.equalSubstring("a", "b", 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.equalSubstring("krrgw", "zjxss", 19));
    }

    @Test
    public void testSameStrings() {
        assertEquals(5, test.equalSubstring("abcde", "abcde", 0));
    }

    @Test
    public void testZeroCost() {
        assertEquals(3, test.equalSubstring("aaabbb", "aaaccc", 0));
    }

    @Test
    public void testMaxCostCoversAll() {
        assertEquals(4, test.equalSubstring("abcd", "zyxw", 100));
    }

    @Test
    public void testSingleCharSameCost() {
        assertEquals(1, test.equalSubstring("a", "a", 0));
    }

    @Test
    public void testSingleCharExceedsCost() {
        assertEquals(0, test.equalSubstring("a", "z", 0));
    }

    @Test
    public void testAllDifferentHighCost() {
        assertEquals(3, test.equalSubstring("abc", "xyz", 100));
    }

    @Test
    public void testGiantCase() {
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            s.append('a');
            t.append('b');
        }
        // each char costs 1, so with maxCost=5000 we get 5000
        assertEquals(5000, test.equalSubstring(s.toString(), t.toString(), 5000));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            String s = random.ints(24, 'a', 'z' + 1).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            String t = random.ints(24, 'a', 'z' + 1).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            int budget = random.nextInt(100), expected = 0;
            for (int left = 0; left < s.length(); left++) {
                int cost = 0;
                for (int right = left; right < s.length(); right++) {
                    cost += Math.abs(s.charAt(right) - t.charAt(right));
                    if (cost <= budget) expected = Math.max(expected, right - left + 1);
                }
            }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new EqualSubstring_1208().equalSubstring(s, t, budget));
        }
    }
}
