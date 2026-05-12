package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordBreak_140Test {
    private final WordBreak_140 solution = new WordBreak_140();

    @Test
    void testBasic() {
        List<String> result = solution.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        assertEquals(2, result.size());
    }

    @Test
    void testNoSolution() {
        List<String> result = solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        assertEquals(0, result.size());
    }

    @Test
    void testSingleWord() {
        List<String> result = solution.wordBreak("cat", Arrays.asList("cat"));
        assertEquals(1, result.size());
    }

    @Test
    void testMultipleSolutions() {
        List<String> result = solution.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        assertTrue(result.size() >= 3);
    }

    @Test
    void testEmpty() {
        List<String> result = solution.wordBreak("", Arrays.asList("cat", "dog"));
        assertTrue(result.size() <= 1);
    }
}
