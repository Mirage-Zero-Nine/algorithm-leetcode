package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordBreak_140Test {
    private final WordBreak_140 solution = new WordBreak_140();

    @Test
    void testBasic() {
        List<String> result = solution.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
        assertEquals(Set.of("cats and dog", "cat sand dog"), new HashSet<>(result));
        assertEquals(2, result.size());
    }

    @Test
    void testNoSolution() {
        List<String> result = solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        assertEquals(0, result.size());
    }

    @Test
    void testSingleWord() {
        assertSentences("cat", List.of("cat"), Set.of("cat"));
    }

    @Test
    void testMultipleSolutions() {
        List<String> result = solution.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        assertEquals(3, result.size());
        assertEquals(Set.of(
                "pine apple pen apple",
                "pineapple pen apple",
                "pine applepen apple"), new HashSet<>(result));
    }

    @Test
    void testEmpty() {
        List<String> result = solution.wordBreak("", Arrays.asList("cat", "dog"));
        assertTrue(result.isEmpty());
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
        // Fifteen a's with dict ["a","aa","aaa"] exercises many memoized suffix combinations.
        String s = "a".repeat(15);
        Set<String> expected = cutMaskOracle(s, Set.of("a", "aa", "aaa"));
        assertSentences(s, Arrays.asList("a", "aa", "aaa"), expected);
        assertEquals(5_768, expected.size());
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
        List<String> result = solution.wordBreak(s, Arrays.asList("a", "aa"));
        // 20 characters with pieces of length 1 or 2 has 10,946 results,
        // staying within the problem's answer-size guarantee.
        assertEquals(10_946, result.size());
        assertEquals(result.size(), new HashSet<>(result).size());
        for (String sentence : result) {
            assertEquals(s, sentence.replace(" ", ""));
            assertTrue(Arrays.stream(sentence.split(" ")).allMatch(word -> word.equals("a") || word.equals("aa")));
        }
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
    void testDistinctDictionaryWordsProduceDistinctDecompositions() {
        List<String> result = solution.wordBreak("aa", List.of("a", "aa"));

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

    @Test
    void testMaximumInputLengthWithSafeAnswerSize() {
        String s = "abcdefghijklmnopqrst";
        assertSentences(s, List.of("abcdefghij", "klmnopqrst"), Set.of("abcdefghij klmnopqrst"));
    }

    @Test
    void testDictionaryBoundaryOfOneThousandWords() {
        List<String> dictionary = new ArrayList<>(List.of("a", "aa", "aaa"));
        IntStream.range(0, 997)
                .mapToObj(i -> "b" + letters(i))
                .forEach(dictionary::add);

        assertEquals(1_000, dictionary.size());
        Set<String> expected = cutMaskOracle("aaaaaa", Set.of("a", "aa", "aaa"));
        assertEquals(24, expected.size());
        assertSentences("aaaaaa", dictionary, expected);
    }

    @Test
    void testIndependentCutMaskOracleCoversSmallSuccessAndFailureCases() {
        List<TestCase> cases = List.of(
                new TestCase("a", List.of("a")),
                new TestCase("ab", List.of("a", "b", "ab")),
                new TestCase("aaaa", List.of("a", "aa", "aaa", "aaaa")),
                new TestCase("abab", List.of("a", "ab", "ba", "b")),
                new TestCase("abc", List.of("a", "bc", "abc")),
                new TestCase("abc", List.of("ab", "a")),
                new TestCase("cars", List.of("car", "ca", "r")),
                new TestCase("aaaaab", List.of("a", "aa", "aaa", "aaaa", "aaaaa")),
                new TestCase("leetcode", List.of("leet", "code")),
                new TestCase("catsandog", List.of("cats", "dog", "sand", "and", "cat")));

        for (TestCase testCase : cases) {
            Set<String> expected = cutMaskOracle(testCase.s(), new HashSet<>(testCase.dictionary()));
            List<String> actual = solution.wordBreak(testCase.s(), testCase.dictionary());
            assertEquals(expected, new HashSet<>(actual), testCase.s());
            assertEquals(actual.size(), new HashSet<>(actual).size(), "Duplicate sentence returned");
        }
    }

    @Test
    void testNoMatchAtPrefixAndSuffixNeverReturnsPartialSentences() {
        assertSentences("xabc", List.of("a", "ab", "bc", "abc"), Set.of());
        assertSentences("abcx", List.of("a", "ab", "bc", "abc"), Set.of());
        assertSentences("abcc", List.of("a", "ab", "bc", "abc"), Set.of());
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

    private Set<String> cutMaskOracle(String s, Set<String> dictionary) {
        Set<String> expected = new HashSet<>();
        int cutCount = s.length() - 1;
        int maskCount = 1 << cutCount;
        for (int mask = 0; mask < maskCount; mask++) {
            List<String> words = new ArrayList<>();
            int start = 0;
            boolean valid = true;
            for (int position = 0; position <= s.length(); position++) {
                if (position == s.length()
                        || (position > 0 && (position < s.length()) && (mask & (1 << (position - 1))) != 0)) {
                    String word = s.substring(start, position);
                    if (!dictionary.contains(word)) {
                        valid = false;
                        break;
                    }
                    words.add(word);
                    start = position;
                }
            }
            if (valid) {
                expected.add(String.join(" ", words));
            }
        }
        return expected;
    }

    private String letters(int value) {
        char[] encoded = new char[3];
        for (int index = encoded.length - 1; index >= 0; index--) {
            encoded[index] = (char) ('c' + (value % 23));
            value /= 23;
        }
        return new String(encoded);
    }

    private record TestCase(String s, List<String> dictionary) {
    }
}
