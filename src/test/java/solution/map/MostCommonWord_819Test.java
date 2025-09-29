package solution.map;

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
        String paragraph = "hello world! hello world!";
        String[] banned = {"hello", "world"};
        assertEquals("", test.mostCommonWord(paragraph, banned));
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
}

