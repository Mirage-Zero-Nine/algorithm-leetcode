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

    @Test
    public void testEmptyStringReturnsFlase() {
        // Implementation treats empty string as false (corner case guard)
        assertFalse(test.wordBreak("", List.of("a", "b", "c")));
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
    public void testCaseSensitive() {
        // Implementation is case-sensitive: "Apple" != "apple"
        assertFalse(test.wordBreak("Apple", List.of("apple")));
        assertTrue(test.wordBreak("Apple", List.of("Apple")));
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
}
