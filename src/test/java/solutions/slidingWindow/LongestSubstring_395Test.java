package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestSubstring_395Test {

    private final LongestSubstring_395 solution = new LongestSubstring_395();

    @Test
    public void testHappy_example1() {
        assertEquals(3, solution.longestSubstring("aaabb", 3));
    }

    @Test
    public void testHappy_example2() {
        assertEquals(5, solution.longestSubstring("ababbc", 2));
    }

    @Test
    public void testHappy_entireString() {
        assertEquals(6, solution.longestSubstring("aabbcc", 2));
    }

    @Test
    public void testHappy_singleCharRepeated() {
        assertEquals(5, solution.longestSubstring("aaaaa", 1));
    }

    @Test
    public void testNegative_kLargerThanLength() {
        assertEquals(0, solution.longestSubstring("abc", 4));
    }

    @Test
    public void testNegative_noCharMeetsK() {
        assertEquals(0, solution.longestSubstring("abcde", 2));
    }

    @Test
    public void testEdge_emptyString() {
        assertEquals(0, solution.longestSubstring("", 1));
    }

    @Test
    public void testEdge_nullString() {
        assertEquals(0, solution.longestSubstring(null, 1));
    }

    @Test
    public void testEdge_singleChar() {
        assertEquals(1, solution.longestSubstring("a", 1));
    }

    @Test
    public void testEdge_kEqualsOne() {
        assertEquals(5, solution.longestSubstring("abcde", 1));
    }

    @Test
    public void testGiant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("aabb");
        }
        assertEquals(40000, solution.longestSubstring(sb.toString(), 2));
    }
}
