package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;

public class CountLetters_1180Test {

    private final CountLetters_1180 test = new CountLetters_1180();

    @Test
    public void testHappyCases() {
        assertEquals(8, test.countLetters("aaaba"));
        assertEquals(10, test.countLetters("aaaa"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.countLetters("a"));
        assertEquals(2, test.countLetters("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.countLetters("aaaaaaaaaa"));
    }

    @Test
    public void testAllDistinct() {
        assertEquals(3, test.countLetters("abc"));
        assertEquals(5, test.countLetters("abcde"));
    }

    @Test
    public void testAllSame() {
        assertEquals(6, test.countLetters("aaa"));   // 1+2+3
        assertEquals(10, test.countLetters("bbbb")); // 1+2+3+4
    }

    @Test
    public void testAlternating() {
        assertEquals(4, test.countLetters("abab"));
        assertEquals(5, test.countLetters("ababa"));
    }

    @Test
    public void testConsecutiveGroups() {
        assertEquals(6, test.countLetters("aabb"));  // 1+2+1+2=6
        assertEquals(9, test.countLetters("aaabb")); // 1+2+3+1+2=9
    }

    @Test
    public void testSingleRepeatedAtEnd() {
        assertEquals(4, test.countLetters("abb"));   // a(1)+b(1)+b(2) = 4
    }

    @Test
    public void testSingleRepeatedAtStart() {
        assertEquals(4, test.countLetters("aab"));   // a(1)+a(2)+b(1) = 4
    }

    @Test
    public void testGiantCase() {
        // 1000 'a' chars -> sum 1+2+...+1000 = 500500
        String s = "a".repeat(1000);
        assertEquals(500500, test.countLetters(s));
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctCases() {
        return Stream.of(
                DynamicTest.dynamicTest("single z", () -> assertEquals(1, test.countLetters("z"))),
                DynamicTest.dynamicTest("two equal", () -> assertEquals(3, test.countLetters("zz"))),
                DynamicTest.dynamicTest("three groups", () -> assertEquals(10, test.countLetters("abbccc"))),
                DynamicTest.dynamicTest("alternating length six", () -> assertEquals(6, test.countLetters("ababab"))),
                DynamicTest.dynamicTest("one long final run", () -> assertEquals(16, test.countLetters("abbbbb"))),
                DynamicTest.dynamicTest("one long initial run", () -> assertEquals(16, test.countLetters("aaaaab"))),
                DynamicTest.dynamicTest("two runs of four", () -> assertEquals(20, test.countLetters("aaaabbbb"))),
                DynamicTest.dynamicTest("distinct alphabet", () -> assertEquals(26, test.countLetters("abcdefghijklmnopqrstuvwxyz"))),
                DynamicTest.dynamicTest("mixed groups", () -> assertEquals(13, test.countLetters("aabcccdd"))),
                DynamicTest.dynamicTest("late alphabet letters", () -> assertEquals(3, test.countLetters("xyz"))),
                DynamicTest.dynamicTest("run split at end", () -> assertEquals(7, test.countLetters("xxxy"))));
    }
}
