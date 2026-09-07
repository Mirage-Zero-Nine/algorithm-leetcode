package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EqualSubstring_1208Test {

    private final EqualSubstring_1208 test = new EqualSubstring_1208();

    @Test
    public void testHappyCase1() {
        assertEquals(3, test.equalSubstring("abcd", "bcdf", 3));
    }

    @Test
    public void testHappyCase2() {
        assertEquals(1, test.equalSubstring("abcd", "cdef", 3));
    }

    @Test
    public void testHappyCase3() {
        assertEquals(1, test.equalSubstring("abcd", "acde", 0));
    }

    @Test
    public void testHappyCase4() {
        assertEquals(4, test.equalSubstring("aaaa", "aaaa", 10));
    }

    @Test
    public void testHappyCase5() {
        assertEquals(2, test.equalSubstring("pxzz", "pzaa", 2));
    }

    @Test
    public void testNegativeCase() {
        assertEquals(0, test.equalSubstring("abcd", "zzzz", 0));
    }

    @Test
    public void testEdgeCaseEmpty() {
        assertEquals(0, test.equalSubstring("", "", 10));
    }

    @Test
    public void testEdgeCaseZeroMaxCost() {
        assertEquals(2, test.equalSubstring("aa", "aa", 0));
        assertEquals(0, test.equalSubstring("ab", "ba", 0));
    }

    @Test
    public void testEdgeCaseAllDifferentLargeCost() {
        assertEquals(0, test.equalSubstring("a", "z", 1));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < n; i++) {
            s.append('a');
            t.append('b'); // cost 1 per char
        }
        assertEquals(500, test.equalSubstring(s.toString(), t.toString(), 500));
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
