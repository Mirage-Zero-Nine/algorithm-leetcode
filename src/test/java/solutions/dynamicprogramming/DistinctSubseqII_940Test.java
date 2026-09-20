package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;

public class DistinctSubseqII_940Test {

    private final DistinctSubseqII_940 test = new DistinctSubseqII_940();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.distinctSubseqII("abc"));
        assertEquals(6, test.distinctSubseqII("aba"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.distinctSubseqII("a"));
        assertEquals(3, test.distinctSubseqII("aaa"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(15, test.distinctSubseqII("abcd"));
    }

    @Test
    public void testTwoDistinctChars() {
        assertEquals(3, test.distinctSubseqII("ab"));
    }

    @Test
    public void testRepeatedPair() {
        assertEquals(5, test.distinctSubseqII("aab"));
    }

    @Test
    public void testAllSameChar() {
        assertEquals(4, test.distinctSubseqII("aaaa"));
    }

    @Test
    public void testPalindrome() {
        assertEquals(26, test.distinctSubseqII("abcba"));
    }

    @Test
    public void testLongerString() {
        assertEquals(31, test.distinctSubseqII("abcde"));
    }

    @Test
    public void testNegativeDuplicateHeavy() {
        assertEquals(3, test.distinctSubseqII("aaa"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        int result = test.distinctSubseqII(sb.toString());
        // Just verify it runs and returns a non-negative value (modulo result)
        assertEquals(true, result >= 0);
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctStrings() {
        return Stream.of(
                DynamicTest.dynamicTest("empty", () -> assertEquals(0, test.distinctSubseqII(""))),
                DynamicTest.dynamicTest("abca", () -> assertEquals(14, test.distinctSubseqII("abca"))),
                DynamicTest.dynamicTest("aabb", () -> assertEquals(8, test.distinctSubseqII("aabb"))),
                DynamicTest.dynamicTest("abab", () -> assertEquals(11, test.distinctSubseqII("abab"))),
                DynamicTest.dynamicTest("single b", () -> assertEquals(1, test.distinctSubseqII("b"))),
                DynamicTest.dynamicTest("two same b", () -> assertEquals(2, test.distinctSubseqII("bb"))),
                DynamicTest.dynamicTest("abcabc", () -> assertEquals(51, test.distinctSubseqII("abcabc"))),
                DynamicTest.dynamicTest("descending", () -> assertEquals(7, test.distinctSubseqII("cba"))),
                DynamicTest.dynamicTest("four same", () -> assertEquals(4, test.distinctSubseqII("zzzz"))),
                DynamicTest.dynamicTest("a b c with repeat", () -> assertEquals(11, test.distinctSubseqII("abcc"))),
                DynamicTest.dynamicTest("long repeated pair", () -> assertEquals(26, test.distinctSubseqII("aabbcc"))));
    }
}
