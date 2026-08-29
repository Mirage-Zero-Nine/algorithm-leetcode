package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        List<String> result = solution.wordBreak("cat", List.of("cat"));
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
        List<String> result = solution.wordBreak("catcat", List.of("cat"));
        assertEquals(1, result.size());
        assertEquals("cat cat", result.getFirst());
    }

    @Test
    void testSingleCharDict() {
        List<String> result = solution.wordBreak("aaa", List.of("a"));
        assertEquals(1, result.size());
        assertEquals("a a a", result.getFirst());
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
        List<String> result = solution.wordBreak("hello", List.of("hello"));
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

    @Test
    void testEmptyDictionaryReturnsEmpty() {
        assertSentences("a", List.of(), Set.of());
    }

    @Test
    void testNullStringReturnsEmpty() {
        assertTrue(solution.wordBreak(null, List.of("a")).isEmpty());
    }

    @Test
    void testNullDictionaryReturnsEmpty() {
        assertTrue(solution.wordBreak("a", null).isEmpty());
    }

    @Test
    void testEmptyStringWithEmptyDictionaryReturnsEmpty() {
        assertTrue(solution.wordBreak("", List.of()).isEmpty());
    }

    @Test
    void testEmptyDictionaryWordIsIgnored() {
        assertSentences("a", List.of("", "a"), Set.of("a"));
        assertSentences("a", List.of(""), Set.of());
    }

    @Test
    void testDictionaryWordsLongerThanInput() {
        assertSentences("cat", List.of("cats", "caterpillar", "dog"), Set.of());
    }

    @Test
    void testSingleCharacterInput() {
        assertSentences("a", List.of("a"), Set.of("a"));
        assertSentences("a", List.of("b"), Set.of());
    }

    @Test
    void testDirectWordAndSplitWordAreBothReturned() {
        assertSentences(
                "carpet",
                List.of("car", "pet", "carpet"),
                Set.of("carpet", "car pet"));
    }

    @Test
    void testAllCandidateWordLengthsAreConsidered() {
        assertSentences(
                "aaaa",
                List.of("a", "aa", "aaa", "aaaa"),
                Set.of(
                        "a a a a",
                        "a a aa",
                        "a aa a",
                        "a aaa",
                        "aa a a",
                        "aa aa",
                        "aaa a",
                        "aaaa"));
    }

    @Test
    void testMultiCharacterWordCanBeReused() {
        assertSentences("ababab", List.of("ab"), Set.of("ab ab ab"));
    }

    @Test
    void testMatchingPrefixWithDeadEndProducesNoPartialSentence() {
        assertSentences("cars", List.of("car", "ca", "r"), Set.of());
    }

    @Test
    void testRepeatedDictionaryEntriesDoNotDuplicateSentences() {
        List<String> result = solution.wordBreak("aa", List.of("a", "a", "aa"));

        assertEquals(Set.of("a a", "aa"), new HashSet<>(result));
        assertEquals(2, result.size());
    }

    @Test
    void testDictionaryOrderDoesNotChangePossibleSentences() {
        List<String> firstOrder = List.of("cat", "cats", "and", "sand", "dog");
        List<String> secondOrder = List.of("dog", "sand", "and", "cats", "cat");

        assertEquals(
                new HashSet<>(solution.wordBreak("catsanddog", firstOrder)),
                new HashSet<>(solution.wordBreak("catsanddog", secondOrder)));
    }

    @Test
    void testMaximumDictionaryWordLengthIsSupported() {
        assertSentences("abcdefghij", List.of("abcdefghij"), Set.of("abcdefghij"));
    }

    @Test
    void testRepeatedSubproblemsReturnAllCombinations() {
        // Number of compositions of six characters using pieces of length 1, 2, or 3 is 24.
        List<String> result = solution.wordBreak("aaaaaa", List.of("a", "aa", "aaa"));

        assertEquals(24, result.size());
        assertEquals(result.size(), new HashSet<>(result).size());
    }

    @Test
    void testUnrelatedDictionaryWordsAreIgnored() {
        assertSentences(
                "leetcode",
                List.of("leet", "code", "apple", "dog"),
                Set.of("leet code"));
    }

    @Test
    void testInputDictionaryIsNotModified() {
        List<String> dictionary = new ArrayList<>(List.of("cat", "cats", "and", "sand", "dog"));
        List<String> original = new ArrayList<>(dictionary);

        solution.wordBreak("catsanddog", dictionary);

        assertEquals(original, dictionary);
    }

    /**
     * Compares result sets because the problem allows sentences in any order.
     * It also verifies that the implementation does not emit duplicate sentences.
     */
    private void assertSentences(String s, List<String> dictionary, Set<String> expected) {
        List<String> actual = solution.wordBreak(s, dictionary);

        assertEquals(expected, new HashSet<>(actual));
        assertEquals(actual.size(), new HashSet<>(actual).size(), "Duplicate sentence returned");
    }
}
