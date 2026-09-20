package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;

public class LongestPalindromeSubseq_516Test {

    private final LongestPalindromeSubseq_516 test = new LongestPalindromeSubseq_516();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.longestPalindromeSubseq("bbbab"));
        assertEquals(2, test.longestPalindromeSubseq("cbbd"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestPalindromeSubseq(null));
        assertEquals(1, test.longestPalindromeSubseq("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(28, test.longestPalindromeSubseq("euazbipzncptldueeuechubrcourfpftcebikrxhybkymgvldvzpxwsnyqzzlrygqptugmgwdlodgeyspvpqhcmqjjxfroqsyshgpqt"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, test.longestPalindromeSubseq(""));
    }

    @Test
    public void testAlreadyPalindrome() {
        assertEquals(5, test.longestPalindromeSubseq("abcba"));
        assertEquals(7, test.longestPalindromeSubseq("racecar"));
    }

    @Test
    public void testAllSameChars() {
        assertEquals(5, test.longestPalindromeSubseq("aaaaa"));
    }

    @Test
    public void testTwoChars() {
        assertEquals(1, test.longestPalindromeSubseq("ab"));
        assertEquals(2, test.longestPalindromeSubseq("aa"));
    }

    @Test
    public void testNoRepeatingChars() {
        assertEquals(1, test.longestPalindromeSubseq("abcdefg"));
    }

    @Test
    public void testReversedString() {
        assertEquals(3, test.longestPalindromeSubseq("abcda"));
    }

    @Test
    public void testGiantCase() {
        // 1000 chars of alternating 'a' and 'b'
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i % 2 == 0 ? 'a' : 'b');
        }
        assertEquals(999, test.longestPalindromeSubseq(sb.toString()));
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctStrings() {
        return Stream.of(
                DynamicTest.dynamicTest("two different", () -> assertEquals(1, test.longestPalindromeSubseq("xy"))),
                DynamicTest.dynamicTest("two equal", () -> assertEquals(2, test.longestPalindromeSubseq("xx"))),
                DynamicTest.dynamicTest("even palindrome", () -> assertEquals(6, test.longestPalindromeSubseq("abccba"))),
                DynamicTest.dynamicTest("inner palindrome", () -> assertEquals(3, test.longestPalindromeSubseq("aacab"))),
                DynamicTest.dynamicTest("alternating", () -> assertEquals(5, test.longestPalindromeSubseq("ababab"))),
                DynamicTest.dynamicTest("one repeated pair", () -> assertEquals(3, test.longestPalindromeSubseq("abca"))),
                DynamicTest.dynamicTest("long distinct", () -> assertEquals(1, test.longestPalindromeSubseq("hijkl"))),
                DynamicTest.dynamicTest("palindrome with tail", () -> assertEquals(5, test.longestPalindromeSubseq("abcdeca"))),
                DynamicTest.dynamicTest("repeated blocks", () -> assertEquals(2, test.longestPalindromeSubseq("aabbcc"))),
                DynamicTest.dynamicTest("single final", () -> assertEquals(1, test.longestPalindromeSubseq("q"))),
                DynamicTest.dynamicTest("mixed symmetry", () -> assertEquals(5, test.longestPalindromeSubseq("character"))));
    }
}
