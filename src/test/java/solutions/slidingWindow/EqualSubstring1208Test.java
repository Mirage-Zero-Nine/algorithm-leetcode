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
}
