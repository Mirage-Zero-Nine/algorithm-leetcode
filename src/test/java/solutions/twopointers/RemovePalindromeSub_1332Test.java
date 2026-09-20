package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemovePalindromeSub_1332Test {

    private final RemovePalindromeSub_1332 test = new RemovePalindromeSub_1332();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.removePalindromeSub("ababa"));
        assertEquals(2, test.removePalindromeSub("abb"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.removePalindromeSub(""));
        assertEquals(1, test.removePalindromeSub("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.removePalindromeSub("baabb"));
        assertEquals(1, test.removePalindromeSub("abba"));
    }

    @Test
    public void testSingleCharB() {
        assertEquals(1, test.removePalindromeSub("b"));
    }

    @Test
    public void testTwoSameChars() {
        assertEquals(1, test.removePalindromeSub("aa"));
        assertEquals(1, test.removePalindromeSub("bb"));
    }

    @Test
    public void testTwoDifferentChars() {
        assertEquals(2, test.removePalindromeSub("ab"));
        assertEquals(2, test.removePalindromeSub("ba"));
    }

    @Test
    public void testAllSameChar() {
        assertEquals(1, test.removePalindromeSub("aaaa"));
        assertEquals(1, test.removePalindromeSub("bbbbb"));
    }

    @Test
    public void testPalindromeStrings() {
        assertEquals(1, test.removePalindromeSub("aba"));
        assertEquals(1, test.removePalindromeSub("abba"));
        assertEquals(1, test.removePalindromeSub("aabaa"));
    }

    @Test
    public void testNonPalindromeStrings() {
        assertEquals(2, test.removePalindromeSub("aab"));
        assertEquals(2, test.removePalindromeSub("abab"));
        assertEquals(2, test.removePalindromeSub("abba" + "b"));
    }

    @Test
    public void testGiantCase() {
        // Large non-palindrome: "aaa...bbb..."
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append('a');
        for (int i = 0; i < 500; i++) sb.append('b');
        // first='a', last='b' -> not palindrome
        assertEquals(2, test.removePalindromeSub(sb.toString()));
    }

    @Test public void testAlternatingLong() { assertEquals(2,test.removePalindromeSub("abababab")); }
    @Test public void testSingleBRun() { assertEquals(1,test.removePalindromeSub("bbbbbbbb")); }
    @Test public void testPalindromeWithMixedRuns() { assertEquals(1,test.removePalindromeSub("aabbbbaa")); }
    @Test public void testTwoCharsAtEnds() { assertEquals(2,test.removePalindromeSub("aabb")); }
    @Test public void testReverseAlternating() { assertEquals(2,test.removePalindromeSub("baba")); }
    @Test public void testSingleCharacterRepeatedCall() { assertEquals(1,test.removePalindromeSub("a")); assertEquals(0,test.removePalindromeSub("")); }
    @Test public void testLongPalindrome() { assertEquals(1,test.removePalindromeSub("a"+"b".repeat(20)+"a")); }
    @Test public void testLongNonPalindrome() { assertEquals(2,test.removePalindromeSub("a".repeat(20)+"b")); }
    @Test public void testBinaryPalindrome() { assertEquals(1,test.removePalindromeSub("abababa")); }
    @Test public void testBinaryNonPalindrome() { assertEquals(2,test.removePalindromeSub("abbab")); }
}
