package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountHomogenous1759Test {

    private final CountHomogenous_1759 test = new CountHomogenous_1759();

    @Test
    public void testHappyCases() {
        assertEquals(13, test.countHomogenous("abbcccaa"));
        assertEquals(2, test.countHomogenous("xy"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.countHomogenous("a"));
        assertEquals(6, test.countHomogenous("aaa"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.countHomogenous("aaaaaaaaaa"));
    }

    @Test
    public void testAllDistinct() {
        assertEquals(5, test.countHomogenous("abcde"));
    }

    @Test
    public void testTwoCharGroups() {
        // "aabb" -> a:1, aa:2, b:3, bb:4 -> wait: a(1), aa(2+1=3), b(1), bb(2+1=3) -> 3+3=6? No.
        // a->1, a->2, b->1, b->2 => sum = 1+2+1+2 = 6
        assertEquals(6, test.countHomogenous("aabb"));
    }

    @Test
    public void testSingleCharRepeated() {
        // "zzzz" -> 1+2+3+4 = 10
        assertEquals(10, test.countHomogenous("zzzz"));
    }

    @Test
    public void testAlternating() {
        // "ababab" -> each char is alone, 6 substrings
        assertEquals(6, test.countHomogenous("ababab"));
    }

    @Test
    public void testLongSameChar() {
        // "aaaaaaa" (7) -> 1+2+3+4+5+6+7 = 28
        assertEquals(28, test.countHomogenous("aaaaaaa"));
    }

    @Test
    public void testTwoChars() {
        assertEquals(2, test.countHomogenous("ab"));
        assertEquals(3, test.countHomogenous("aa"));
    }

    @Test
    public void testGiantCase() {
        // 100000 'a's -> sum = n*(n+1)/2 mod 10^9+7
        // 100000 * 100001 / 2 = 5000050000 mod 10^9+7 = 5000050000 - 4*1000000007 = 5000050000 - 4000000028 = 999999972 + 50000 = wait
        // 5000050000 % 1000000007 = 5000050000 - 4*1000000007 = 5000050000 - 4000000028 = 1000049972
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append('a');
        int expected = (int)((100000L * 100001L / 2) % 1_000_000_007);
        assertEquals(expected, test.countHomogenous(sb.toString()));
    }
}
