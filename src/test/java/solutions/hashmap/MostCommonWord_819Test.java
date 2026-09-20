package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/09/28 21:55
 * Created with IntelliJ IDEA
 */

/**
 * This Javadoc explains the core idea of how the code solves the problem,
 * covering input handling, parsing logic, word counting, and final result selection.
 */
public class MostCommonWord_819Test {

    private final MostCommonWord_819 test = new MostCommonWord_819();

    @Test
    public void testBasicCase() {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        assertEquals("ball", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testCaseSensitivity() {
        String paragraph = "It was the best of times, it was the worst of times.";
        String[] banned = {"of"};
        assertEquals("it", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testEdgeCaseWithOnlyBannedWords() {
        // LeetCode guarantees that at least one non-banned word exists.
        String paragraph = "hello world! hello leetcode!";
        String[] banned = {"hello", "world"};
        assertEquals("leetcode", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testEdgeCaseWithSingleWordAndNoBannedWords() {
        String paragraph = "hello";
        String[] banned = {};
        assertEquals("hello", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testEmptyParagraph() {
        String paragraph = "";
        String[] banned = {};
        assertEquals("", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testNoBannedWordsAndAllWordsAppearOnce() {
        String paragraph = "dog cat rabbit";
        String[] banned = {};
        assertEquals("dog", test.mostCommonWord(paragraph, banned));  // First encountered word
    }

    @Test
    public void testSpecialCharactersAndPunctuation() {
        String paragraph = "Wow!!! Amazing... AMAZING! wow? WOW!!";
        String[] banned = {};
        assertEquals("wow", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testBannedWordsAreTheMostFrequentWords() {
        String paragraph = "apple apple orange banana banana apple";
        String[] banned = {"apple"};
        assertEquals("banana", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testSingleOccurrenceOfEachWord() {
        String paragraph = "apple orange banana";
        String[] banned = {"orange"};
        assertEquals("apple", test.mostCommonWord(paragraph, banned));  // First encountered
    }

    @Test
    public void testHandlingNumbersAndNonAlphabeticCharacters() {
        String paragraph = "The price is 100 dollars, 100 dollars!";
        String[] banned = {};
        assertEquals("dollars", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testMultipleBannedWordsWithMostCommonWordInParagraph() {
        String paragraph = "the quick brown fox jumped over the lazy dog";
        String[] banned = {"the", "dog"};
        assertEquals("quick", test.mostCommonWord(paragraph, banned));
    }

    @Test
    public void testParagraphWithMultiplePunctuationTypes() {
        String paragraph = "This, is a test! This is, a test.";
        String[] banned = {};
        assertEquals("this", test.mostCommonWord(paragraph, banned));
    }
    @Test void extra01() { assertEquals("a", test.mostCommonWord("a", new String[]{})); }
    @Test void extra02() { assertEquals("ball", test.mostCommonWord("Ball ball!", new String[]{})); }
    @Test void extra03() { assertEquals("dog", test.mostCommonWord("dog cat dog", new String[]{"cat"})); }
    @Test void extra04() { assertEquals("cat", test.mostCommonWord("dog cat", new String[]{"dog"})); }
    @Test void extra05() { assertEquals("hello", test.mostCommonWord("Hello, hello.", new String[]{})); }
    @Test void extra06() { assertEquals("a", test.mostCommonWord("a b a b a", new String[]{"b"})); }
    @Test void extra07() { assertEquals("world", test.mostCommonWord("world world test", new String[]{"test"})); }
    @Test void extra08() { assertEquals("one", test.mostCommonWord("one two three one", new String[]{})); }
    @Test void extra09() { assertEquals("quick", test.mostCommonWord("Quick brown quick fox", new String[]{"brown"})); }
    @Test void extra10() { assertEquals("x", test.mostCommonWord("x y x", new String[]{"y"})); }
}
