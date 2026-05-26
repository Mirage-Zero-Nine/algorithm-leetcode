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
}
