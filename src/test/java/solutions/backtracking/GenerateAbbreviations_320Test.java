package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateAbbreviations_320Test {
    private final GenerateAbbreviations_320 solution = new GenerateAbbreviations_320();

    @Test
    void testWord() {
        List<String> result = solution.generateAbbreviations("word");
        assertEquals(16, result.size());
    }

    @Test
    void testSingleChar() {
        List<String> result = solution.generateAbbreviations("a");
        assertEquals(2, result.size());
    }

    @Test
    void testTwoChars() {
        List<String> result = solution.generateAbbreviations("ab");
        assertEquals(4, result.size());
    }

    @Test
    void testThreeChars() {
        List<String> result = solution.generateAbbreviations("abc");
        assertEquals(8, result.size());
    }

    @Test
    void testEmpty() {
        List<String> result = solution.generateAbbreviations("");
        assertTrue(result.size() <= 1);
    }

    @Test
    void testEmptyReturnsEmptyString() {
        List<String> result = solution.generateAbbreviations("");
        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }

    @Test
    void testSingleCharContainsOriginal() {
        List<String> result = solution.generateAbbreviations("a");
        assertTrue(result.contains("a"));
        assertTrue(result.contains("1"));
    }

    @Test
    void testWordContainsFullAbbreviation() {
        List<String> result = solution.generateAbbreviations("word");
        assertTrue(result.contains("4"));
        assertTrue(result.contains("word"));
    }

    @Test
    void testWordContainsPartialAbbreviations() {
        List<String> result = solution.generateAbbreviations("word");
        assertTrue(result.contains("w3"));
        assertTrue(result.contains("3d"));
        assertTrue(result.contains("1o1d"));
    }

    @Test
    void testFiveChars() {
        List<String> result = solution.generateAbbreviations("abcde");
        assertEquals(32, result.size());
    }

    @Test
    void testNoDuplicates() {
        List<String> result = solution.generateAbbreviations("word");
        Set<String> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size());
    }

    @Test
    void testGiantInput() {
        // 10 chars -> 2^10 = 1024 abbreviations
        List<String> result = solution.generateAbbreviations("abcdefghij");
        assertEquals(1024, result.size());
    }
}
