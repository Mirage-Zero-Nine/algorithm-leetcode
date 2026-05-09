package solution.backtracking;

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
}
