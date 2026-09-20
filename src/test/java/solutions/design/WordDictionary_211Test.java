package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    public void testEmptyPatternIsNeverAStoredWord() {
        WordDictionary_211 wd = new WordDictionary_211();

        assertFalse(wd.search(""));
        wd.addWord("a");
        assertFalse(wd.search(""));
        wd.addWord("longer");
        assertFalse(wd.search(""));
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
    public void testMultipleDotsAtDifferentPositions() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("abcd");
        wd.addWord("axyd");
        wd.addWord("mnop");

        assertTrue(wd.search("a..d"));
        assertTrue(wd.search(".b.d"));
        assertTrue(wd.search("..cd"));
        assertTrue(wd.search("...."));
        assertFalse(wd.search("a..e"));
        assertFalse(wd.search("...de"));
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
    public void testSeveralWildcardsMustFollowOneTriePath() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("abcd");
        wd.addWord("abef");
        wd.addWord("xycd");

        assertTrue(wd.search("a..d"));
        assertTrue(wd.search("..cd"));
        assertFalse(wd.search("a..c"));
        assertFalse(wd.search("x..e"));
        assertFalse(wd.search("a...x"));
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
    public void testMaximumLengthWordWithTwoWildcardsAndExactPositions() {
        WordDictionary_211 wd = new WordDictionary_211();
        String word = "abcdefghijklmnopqrstuvwxy";
        wd.addWord(word);

        assertTrue(wd.search("a.......................y"));
        assertTrue(wd.search(".......................xy"));
        assertTrue(wd.search("ab......................."));
        assertFalse(wd.search("a.......................x"));
        assertFalse(wd.search(".abcdefghijklmnopqrstuvwxy"));
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
    public void testDuplicateInsertionDoesNotChangeOtherMatches() {
        WordDictionary_211 wd = new WordDictionary_211();
        for (int i = 0; i < 20; i++) {
            wd.addWord("same");
        }

        assertTrue(wd.search("same"));
        assertTrue(wd.search("s..e"));
        assertFalse(wd.search("sam"));
        assertFalse(wd.search("....."));
    }

    @Test
    public void testExactLengthAndNearMisses() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("bad");
        wd.addWord("bake");
        wd.addWord("baker");

        assertTrue(wd.search("bad"));
        assertTrue(wd.search("b.."));
        assertTrue(wd.search("bake"));
        assertTrue(wd.search("b...."));
        assertTrue(wd.search("baker"));
        assertFalse(wd.search("ba"));
        assertFalse(wd.search("bb..."));
        assertFalse(wd.search("bakers"));
    }

    @Test
    public void testAddingPrefixAfterLongerWordPreservesBothLengths() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("application");
        assertFalse(wd.search("app"));
        wd.addWord("app");

        assertTrue(wd.search("app"));
        assertTrue(wd.search("a.."));
        assertTrue(wd.search("application"));
        assertFalse(wd.search("applicatio"));
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
    public void testSeparateInstancesDoNotShareWords() {
        WordDictionary_211 first = new WordDictionary_211();
        WordDictionary_211 second = new WordDictionary_211();
        first.addWord("shared");
        second.addWord("other");

        assertTrue(first.search("shared"));
        assertFalse(first.search("other"));
        assertTrue(second.search("other"));
        assertFalse(second.search("shared"));
    }

    @Test
    public void testRepeatedSearchesDoNotConsumeOrMutateDictionary() {
        WordDictionary_211 wd = new WordDictionary_211();
        wd.addWord("branch");
        wd.addWord("brink");

        for (int i = 0; i < 100; i++) {
            assertTrue(wd.search("br..."));
            assertTrue(wd.search("b....h"));
            assertFalse(wd.search("br....."));
            assertFalse(wd.search("zz..."));
        }

        assertTrue(wd.search("branch"));
        assertTrue(wd.search("brink"));
    }

    @Test
    public void testTenThousandContractOperationsWithIndependentOracle() {
        WordDictionary_211 wd = new WordDictionary_211();
        Map<Integer, Set<String>> wordsByLength = new HashMap<>();
        Random random = new Random(211_10_000L);

        for (int operation = 0; operation < 10_000; operation++) {
            if ((operation & 1) == 0) {
                String word = randomWord(random, 1 + random.nextInt(8));
                wordsByLength.computeIfAbsent(word.length(), ignored -> new HashSet<>()).add(word);
                wd.addWord(word);
            } else {
                String source = randomWord(random, 1 + random.nextInt(8));
                String pattern = wildcardPatternWithAtMostTwoDots(source, random);
                boolean actual = wd.search(pattern);
                if (operation % 25 == 1) {
                    assertEquals(matchesAny(wordsByLength, pattern), actual, pattern);
                }
            }
        }
    }

    @Test
    public void testFiftyThousandCallsAtSmallAlphabetBoundary() {
        WordDictionary_211 wd = new WordDictionary_211();
        Set<String> words = new HashSet<>();
        Random random = new Random(211_50_000L);
        String[] wordPool = new String[256];
        for (int i = 0; i < wordPool.length; i++) {
            wordPool[i] = base26Word(i, 8);
        }

        for (int operation = 0; operation < 50_000; operation++) {
            if (operation < 25_000) {
                String word = wordPool[random.nextInt(wordPool.length)];
                words.add(word);
                wd.addWord(word);
            } else {
                String source = wordPool[random.nextInt(wordPool.length)];
                String pattern = wildcardPatternWithAtMostTwoDots(source, random);
                assertEquals(matchesAny(words, pattern), wd.search(pattern), pattern);
            }
        }
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

    private static boolean matchesAny(Map<Integer, Set<String>> wordsByLength, String pattern) {
        Set<String> candidates = wordsByLength.get(pattern.length());
        return candidates != null && matchesAny(candidates, pattern);
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

    private static String base26Word(int value, int length) {
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char) ('a' + value % 26));
            value /= 26;
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

    private static String wildcardPatternWithAtMostTwoDots(String word, Random random) {
        StringBuilder pattern = new StringBuilder(word);
        int dots = random.nextInt(Math.min(2, pattern.length()) + 1);
        Set<Integer> positions = new HashSet<>();
        while (positions.size() < dots) {
            positions.add(random.nextInt(pattern.length()));
        }
        for (int position : positions) {
            pattern.setCharAt(position, '.');
        }
        return pattern.toString();
    }
}
