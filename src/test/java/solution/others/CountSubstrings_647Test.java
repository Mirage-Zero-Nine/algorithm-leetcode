package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSubstrings_647Test {

    private final CountSubstrings_647 test = new CountSubstrings_647();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.countSubstrings("abc"));
        assertEquals(6, test.countSubstrings("aaa"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countSubstrings(""));
        assertEquals(1, test.countSubstrings("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, test.countSubstrings("abcba"));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, test.countSubstrings(null));
    }

    @Test
    public void testTwoSameChars() {
        assertEquals(3, test.countSubstrings("aa"));
    }

    @Test
    public void testEvenPalindrome() {
        assertEquals(6, test.countSubstrings("abba"));
    }

    @Test
    public void testAllSameChars() {
        // "aaaa" -> a(4) + aa(3) + aaa(2) + aaaa(1) = 10
        assertEquals(10, test.countSubstrings("aaaa"));
    }

    @Test
    public void testNoPalindromesBeyondSingle() {
        assertEquals(4, test.countSubstrings("abcd"));
    }

    @Test
    public void testDPMethod() {
        assertEquals(3, test.countSubstringsDP("abc"));
        assertEquals(6, test.countSubstringsDP("aaa"));
        assertEquals(7, test.countSubstringsDP("abcba"));
    }

    @Test
    public void testManacherMethod() {
        assertEquals(3, test.countSubstringsManacher("abc"));
        assertEquals(6, test.countSubstringsManacher("aaa"));
        assertEquals(7, test.countSubstringsManacher("abcba"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) sb.append('a');
        // n*(n+1)/2 = 100*101/2 = 5050
        assertEquals(5050, test.countSubstrings(sb.toString()));
    }
}
