package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class WordDictionary_211Test {

    @Test
    public void testHappyCases() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("bad"); wd.addWord("dad"); wd.addWord("mad");
        assertFalse(wd.search("pad"));
        assertTrue(wd.search("bad"));
        assertTrue(wd.search(".ad"));
        assertTrue(wd.search("b.."));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        WordDictionary_211 wd = new WordDictionary_211();
        assertFalse(wd.search("a"));
        wd.addWord("a");
        assertTrue(wd.search("a"));
        assertTrue(wd.search("."));
    }

    @Test
    public void testLargeCase() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("hello"); wd.addWord("world");
        assertTrue(wd.search("h...."));
        assertTrue(wd.search("....."));
        assertFalse(wd.search("......"));
    }

    @Test
    public void testAllDots() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("abc");
        assertTrue(wd.search("..."));
        assertFalse(wd.search(".."));
        assertFalse(wd.search("...."));
    }

    @Test
    public void testPrefixNotWord() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("apple");
        assertFalse(wd.search("app"));
        assertFalse(wd.search("appl"));
        assertTrue(wd.search("apple"));
    }

    @Test
    public void testEmptyDictionary() {
        WordDictionary_211 wd = new WordDictionary_211();
        assertFalse(wd.search("anything"));
        assertFalse(wd.search("."));
        assertFalse(wd.search("..."));
    }

    @Test
    public void testNullAndEmptyWordsAreIgnored() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord(null);
        wd.addWord("");

        assertFalse(wd.search("a"));
        assertFalse(wd.search("."));

        wd.addWord("a");
        assertTrue(wd.search("a"));
        assertTrue(wd.search("."));
    }

    @Test
    public void testDotAtDifferentPositions() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("cat");
        assertTrue(wd.search(".at"));
        assertTrue(wd.search("c.t"));
        assertTrue(wd.search("ca."));
        assertFalse(wd.search(".."));
    }

    @Test
    public void testMultipleWordsOverlapping() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("a");
        wd.addWord("ab");
        wd.addWord("abc");
        assertTrue(wd.search("a"));
        assertTrue(wd.search("ab"));
        assertTrue(wd.search("abc"));
        assertTrue(wd.search("."));
        assertTrue(wd.search(".."));
        assertTrue(wd.search("..."));
        assertFalse(wd.search("...."));
    }

    @Test
    public void testPrefixAddedAfterLongerWord() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("abcd");
        wd.addWord("ab");

        assertTrue(wd.search("ab"));
        assertTrue(wd.search("a."));
        assertTrue(wd.search("abcd"));
    }

    @Test
    public void testSharedPrefixesAndDivergentBranches() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("car");
        wd.addWord("cat");
        wd.addWord("can");
        wd.addWord("canary");

        assertFalse(wd.search("ca"));
        assertTrue(wd.search("ca."));
        assertTrue(wd.search("c.r"));
        assertTrue(wd.search("c.."));
        assertTrue(wd.search("c....."));
        assertFalse(wd.search("c..."));
        assertFalse(wd.search("c.z"));
    }

    @Test
    public void testWildcardCannotCombineDifferentWords() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("abc");
        wd.addWord("xyz");

        assertTrue(wd.search("a.c"));
        assertTrue(wd.search(".yz"));
        assertFalse(wd.search("a.z"));
        assertFalse(wd.search("x.c"));
    }

    @Test
    public void testMaximumWordLengthAndAllLetters() {
        WordDictionary_211 wd = new WordDictionary_211();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String maxLengthWord = alphabet.substring(0, 25);

        for (int i = 0; i < alphabet.length(); i++) {
            wd.addWord(String.valueOf(alphabet.charAt(i)));
        }
        wd.addWord(maxLengthWord);

        assertTrue(wd.search("."));
        assertTrue(wd.search("a"));
        assertTrue(wd.search("z"));
        assertTrue(wd.search("........................."));
        assertFalse(wd.search(".........................."));
    }

    @Test
    public void testDuplicateWords() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("test");
        wd.addWord("test");
        assertTrue(wd.search("test"));
        assertTrue(wd.search("...."));
    }

    @Test
    public void testGiantCase() {
        WordDictionary_211 wd = new WordDictionary_211();
        // Add 1000 words of length 5
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            int val = i;
            for (int j = 0; j < 5; j++) {
                sb.append((char) ('a' + val % 26));
                val /= 26;
            }
            wd.addWord(sb.toString());
        }
        // Search for the first word added: "aaaaa" (i=0)
        assertTrue(wd.search("aaaaa"));
        // Search with dots
        assertTrue(wd.search("a...."));
        // Search for something that doesn't exist
        assertFalse(wd.search("zzzzz"));
    }

    @Test
    public void testAgainstReferenceMatcherWithInterleavedOperations() {
        WordDictionary_211 wd = new WordDictionary_211();
        Set<String> words = new HashSet<>();
        Random random = new Random(211L);

        for (int i = 0; i < 500; i++) {
            String word = randomWord(random, 1 + random.nextInt(25));
            words.add(word);
            wd.addWord(word);

            String matchingPattern = wildcardPattern(word, random);
            assertEquals(matchesAny(words, matchingPattern), wd.search(matchingPattern), matchingPattern);

            String randomPattern = wildcardPattern(randomWord(random, 1 + random.nextInt(25)), random);
            assertEquals(matchesAny(words, randomPattern), wd.search(randomPattern), randomPattern);
        }
    }

    private static boolean matchesAny(Set<String> words, String pattern) {
        return words.stream().anyMatch(word -> matches(word, pattern));
    }

    private static boolean matches(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            char patternChar = pattern.charAt(i);
            if (patternChar != '.' && patternChar != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String randomWord(Random random, int length) {
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }
        return word.toString();
    }

    private static String wildcardPattern(String word, Random random) {
        StringBuilder pattern = new StringBuilder(word);
        boolean hasWildcard = false;
        for (int i = 0; i < pattern.length(); i++) {
            if (random.nextInt(4) == 0) {
                pattern.setCharAt(i, '.');
                hasWildcard = true;
            }
        }
        if (!hasWildcard) {
            pattern.setCharAt(random.nextInt(pattern.length()), '.');
        }
        return pattern.toString();
    }
}
