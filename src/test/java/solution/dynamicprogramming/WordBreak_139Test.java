package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WordBreak_139Test {

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

    @Test
    public void testSingleCharMatch() {
        assertTrue(test.wordBreak("a", List.of("a")));
    }

    @Test
    public void testSingleCharNoMatch() {
        assertFalse(test.wordBreak("b", List.of("a")));
    }

    @Test
    public void testOverlappingWords() {
        assertTrue(test.wordBreak("cars", List.of("car", "ca", "rs")));
    }

    @Test
    public void testEmptyDict() {
        assertFalse(test.wordBreak("hello", Collections.emptyList()));
    }

    @Test
    public void testRepeatedWord() {
        assertTrue(test.wordBreak("aaaaaaa", List.of("a", "aa", "aaa")));
    }

    @Test
    public void testNoSegmentation() {
        assertFalse(test.wordBreak("abcdef", List.of("ab", "cd")));
    }

    @Test
    public void testWholeStringIsOneWord() {
        assertTrue(test.wordBreak("banana", List.of("banana")));
    }

    @Test
    public void testGiantCase() {
        // s = "aaa...a" (150 chars), dict = ["a","aa","aaa",...,"a"*10]
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 150; i++) sb.append('a');
        List<String> dict = new ArrayList<>();
        StringBuilder w = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            w.append('a');
            dict.add(w.toString());
        }
        assertTrue(test.wordBreak(sb.toString(), dict));
    }
}
