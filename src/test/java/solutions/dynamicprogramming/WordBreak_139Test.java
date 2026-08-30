package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
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
    public void testNegativeCase() {
        assertFalse(test.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    }

    @Test
    public void testEmptyStringCornerCase() {
        // Supported explicitly by the implementation, although outside the LeetCode constraints.
        assertFalse(test.wordBreak("", List.of("a", "b", "c")));
    }

    @Test
    public void testEmptyDictionaryCornerCase() {
        // Supported explicitly by the implementation, although outside the LeetCode constraints.
        assertFalse(test.wordBreak("hello", List.of()));
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

    @Test
    public void testOverlappingDictWordsCatsanddog() {
        // 'catsanddog' can be split as 'cats'+'and'+'dog' or 'cat'+'sand'+'dog'
        assertTrue(test.wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog")));
    }

    @Test
    public void testDictWordLongerThanString() {
        // Dict contains a word longer than s; should be ignored, match via shorter words
        assertTrue(test.wordBreak("ab", List.of("ab", "abcdefgh")));
        assertFalse(test.wordBreak("ab", List.of("abcdefgh")));
    }

    @Test
    public void testRepeatedSingleChar() {
        // 'aaaa' with dict=['a'] -> true (reuse allowed)
        assertTrue(test.wordBreak("aaaa", List.of("a")));
    }

    @Test
    public void testTrapCaseSplitRequired() {
        // 'aaaaaaa' (7 a's) with dict=['aaaa','aaa'] -> true (4+3 or 3+4)
        assertTrue(test.wordBreak("aaaaaaa", List.of("aaaa", "aaa")));
    }

    @Test
    public void testTLECase300Chars() {
        // Large input that requires memoization/DP to avoid TLE
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) sb.append('a');
        assertTrue(test.wordBreak(sb.toString(), List.of("a", "aa", "aaa")));
    }

    @Test
    public void testWordInDictButStringDiffers() {
        // Dict has "abc" but s="ab" -> false (partial match not allowed)
        assertFalse(test.wordBreak("ab", List.of("abc")));
    }

    @Test
    public void testMultipleWordsNeededNotJustOne() {
        // s="abcabc", dict=["abc"] -> true (word reused)
        assertTrue(test.wordBreak("abcabc", List.of("abc")));
        // s="abcab", dict=["abc"] -> false (can't complete)
        assertFalse(test.wordBreak("abcab", List.of("abc")));
    }

    @Test
    public void testAdditionalValidSegmentations() {
        assertTrue(test.wordBreak("pineapplepenapple",
                List.of("apple", "pen", "applepen", "pine", "pineapple")));
        assertTrue(test.wordBreak("abcd", List.of("a", "bc", "d")));
        assertTrue(test.wordBreak("aab", List.of("a", "aa", "b")));
        assertTrue(test.wordBreak("abababab", List.of("ab")));
    }

    @Test
    public void testAdditionalImpossibleSegmentations() {
        assertFalse(test.wordBreak("aaaaaaa", List.of("aaaa", "aa")));
        assertFalse(test.wordBreak("abcc", List.of("a", "bc")));
        assertFalse(test.wordBreak("aab", List.of("aa", "a")));
        assertFalse(test.wordBreak("aaaaab", List.of("a", "aa", "aaa", "aaaa", "aaaaa")));
    }

    @Test
    public void testBacktrackingAcrossCompetingPrefixes() {
        assertTrue(test.wordBreak("cars", List.of("car", "ca", "rs")));
        assertTrue(test.wordBreak("aaaaaaa", List.of("aaaa", "aaa")));
        assertTrue(test.wordBreak("abcd", List.of("abc", "ab", "cd")));
    }

    @Test
    public void testUnusablePrefixAndRemainder() {
        assertFalse(test.wordBreak("abx", List.of("ab")));
        assertFalse(test.wordBreak("abcab", List.of("abc")));
        assertFalse(test.wordBreak("abcdef", List.of("abc", "ab", "cd")));
    }

    @Test
    public void testDictionaryNoise() {
        assertTrue(test.wordBreak("leetcode", List.of(
                "a", "leet", "lee", "code", "cod", "x", "xyz")));
        assertFalse(test.wordBreak("leetcodex", List.of(
                "leet", "code", "a", "lee", "cod", "xxy")));
    }

    @Test
    public void testMinimumStringLength() {
        assertTrue(test.wordBreak("a", List.of("a")));
        assertFalse(test.wordBreak("a", List.of("b")));
        assertTrue(test.wordBreak("z", List.of("z", "zz")));
    }

    @Test
    public void testMaximumWordLength() {
        String word = "a".repeat(20);
        assertTrue(test.wordBreak(word, List.of(word)));
        assertTrue(test.wordBreak("a".repeat(40), List.of(word)));
        assertFalse(test.wordBreak(word + "b", List.of(word)));
    }

    @Test
    public void testMaximumStringLength() {
        assertTrue(test.wordBreak("a".repeat(300), List.of("a")));
        assertFalse(test.wordBreak("a".repeat(299) + "b", List.of("a", "aa", "aaa")));
    }

    @Test
    public void testMaximumDictionarySize() {
        List<String> dictionary = lowercaseWords(998);
        dictionary.add("leet");
        dictionary.add("code");

        assertTrue(test.wordBreak("leetcode", dictionary));
    }

    private List<String> lowercaseWords(int count) {
        List<String> words = new ArrayList<>(count);
        for (int value = 0; value < count; value++) {
            int current = value;
            StringBuilder word = new StringBuilder();
            do {
                word.append((char) ('a' + current % 26));
                current /= 26;
            } while (current > 0);
            words.add(word.reverse().toString());
        }
        return words;
    }
}
