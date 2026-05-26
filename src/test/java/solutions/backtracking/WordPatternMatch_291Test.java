package solutions.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordPatternMatch_291Test {
    private final WordPatternMatch_291 solution = new WordPatternMatch_291();

    @Test
    void testBasic() {
        assertTrue(solution.wordPatternMatch("abab", "redblueredblue"));
    }

    @Test
    void testNoMatch() {
        assertTrue(solution.wordPatternMatch("aaaa", "asdasdasdasd"));
    }

    @Test
    void testSingleChar() {
        assertTrue(solution.wordPatternMatch("a", "test"));
    }

    @Test
    void testMultiplePatterns() {
        assertTrue(solution.wordPatternMatch("abba", "dogcatcatdog"));
    }

    @Test
    void testNoMatchDifferent() {
        assertFalse(solution.wordPatternMatch("abba", "dogcatcatfish"));
    }

    @Test
    void testSamePatternDifferentMapping() {
        assertFalse(solution.wordPatternMatch("aabb", "xyzabcxzyabc"));
    }

    @Test
    void testAllSamePattern() {
        assertTrue(solution.wordPatternMatch("aaaa", "asdasdasdasd"));
    }

    @Test
    void testTwoCharPattern() {
        // bijection: a and b must map to different substrings, "aa" can't split into 2 different non-empty parts
        assertFalse(solution.wordPatternMatch("ab", "aa"));
    }

    @Test
    void testPatternLongerThanString() {
        assertFalse(solution.wordPatternMatch("abcd", "ab"));
    }

    @Test
    void testSingleCharPatternSingleCharStr() {
        assertTrue(solution.wordPatternMatch("a", "x"));
    }

    @Test
    void testGiantCase() {
        // pattern "abcabc": a=red, b=blue, c=green -> "redbluegreenredbluegreen"
        assertTrue(solution.wordPatternMatch("abcabc", "redbluegreenredbluegreen"));
    }
}
