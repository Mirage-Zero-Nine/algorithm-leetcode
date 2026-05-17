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

    @Test
    void testRepeatedWord() {
        List<String> result = solution.wordBreak("catcat", Arrays.asList("cat"));
        assertEquals(1, result.size());
        assertEquals("cat cat", result.get(0));
    }

    @Test
    void testSingleCharDict() {
        List<String> result = solution.wordBreak("aaa", Arrays.asList("a"));
        assertEquals(1, result.size());
        assertEquals("a a a", result.get(0));
    }

    @Test
    void testOverlappingWords() {
        List<String> result = solution.wordBreak("ab", Arrays.asList("a", "b", "ab"));
        assertEquals(2, result.size());
    }

    @Test
    void testNoMatchAtAll() {
        List<String> result = solution.wordBreak("xyz", Arrays.asList("a", "b", "c"));
        assertEquals(0, result.size());
    }

    @Test
    void testGiantCase() {
        // "aaa...a" (20 a's) with dict ["a","aa","aaa"]
        String s = "a".repeat(15);
        List<String> result = solution.wordBreak(s, Arrays.asList("a", "aa", "aaa"));
        // should produce many combinations, just verify non-empty and completes quickly
        assertTrue(result.size() > 100);
    }

    // --- NEW TESTS ---

    @Test
    void testEmptyStringReturnsEmpty() {
        List<String> result = solution.wordBreak("", Arrays.asList("a", "b", "c"));
        assertTrue(result.isEmpty());
    }

    @Test
    void testCatsanddogExactResults() {
        Set<String> result = new HashSet<>(solution.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        assertEquals(Set.of("cats and dog", "cat sand dog"), result);
    }

    @Test
    void testPineapplepenapppleExactResults() {
        Set<String> result = new HashSet<>(solution.wordBreak("pineapplepenapple",
                Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        assertEquals(Set.of(
                "pine apple pen apple",
                "pineapple pen apple",
                "pine applepen apple"), result);
    }

    @Test
    void testCatsandog_NoBreak() {
        List<String> result = solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        assertEquals(Set.of(), new HashSet<>(result));
    }

    @Test
    void testSingleDictWordMatchesExactly() {
        List<String> result = solution.wordBreak("hello", Arrays.asList("hello"));
        assertEquals(Set.of("hello"), new HashSet<>(result));
    }

    @Test
    void testRepeatedWordAllDecompositions() {
        Set<String> result = new HashSet<>(solution.wordBreak("aaaa", Arrays.asList("a", "aa")));
        Set<String> expected = Set.of("a a a a", "aa a a", "a aa a", "a a aa", "aa aa");
        assertEquals(expected, result);
    }

    @Test
    void testLargeExplosiveCountOnly() {
        String s = "a".repeat(20);
        List<String> result = solution.wordBreak(s, Arrays.asList("a", "aa", "aaa"));
        // Fibonacci-like growth; just verify count is large and completes
        assertTrue(result.size() > 1000);
    }

    @Test
    void testPropertyEveryResultConcatenatesBackToS() {
        String s = "catsanddog";
        List<String> result = solution.wordBreak(s, Arrays.asList("cat", "cats", "and", "sand", "dog"));
        for (String sentence : result) {
            assertEquals(s, sentence.replace(" ", ""));
        }
    }

    @Test
    void testPropertyEveryWordInResultIsInDict() {
        Set<String> dict = Set.of("apple", "pen", "applepen", "pine", "pineapple");
        List<String> result = solution.wordBreak("pineapplepenapple", new ArrayList<>(dict));
        for (String sentence : result) {
            for (String word : sentence.split(" ")) {
                assertTrue(dict.contains(word), "Word not in dict: " + word);
            }
        }
    }
}
