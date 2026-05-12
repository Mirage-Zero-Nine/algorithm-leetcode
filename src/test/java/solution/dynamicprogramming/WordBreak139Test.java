package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WordBreak139Test {

    private final WordBreak_139 test = new WordBreak_139();

    @Test
    public void testHappyCases() {
        assertTrue(test.wordBreak("leetcode", List.of("leet", "code")));
        assertTrue(test.wordBreak("applepenapple", List.of("apple", "pen")));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
        assertFalse(test.wordBreak("", List.of("a")));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.wordBreak("callofduty", List.of("call", "of", "duty")));
    }
}
