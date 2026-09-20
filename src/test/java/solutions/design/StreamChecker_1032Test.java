package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class StreamChecker_1032Test {

    @Test
    public void testHappyCases() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"cd", "f", "kl"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertFalse(sc.query('c'));
        assertTrue(sc.query('d'));
        assertFalse(sc.query('e'));
        assertTrue(sc.query('f'));
    }

    @Test
    public void testEdgeCases() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"a"});
        assertTrue(sc.query('a'));
        assertFalse(sc.query('b'));
    }

    @Test
    public void testLargeCase() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"abc", "de"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertTrue(sc.query('c'));
        assertFalse(sc.query('d'));
        assertTrue(sc.query('e'));
    }

    @Test
    public void testOverlappingWords() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"ab", "ba", "aba"});
        assertFalse(sc.query('a'));
        assertTrue(sc.query('b'));  // "ab" matches
        assertTrue(sc.query('a')); // "ba" matches
    }

    @Test
    public void testSingleCharWords() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"a", "b", "c"});
        assertTrue(sc.query('a'));
        assertTrue(sc.query('b'));
        assertTrue(sc.query('c'));
        assertFalse(sc.query('d'));
    }

    @Test
    public void testNoMatchEver() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"xyz"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertFalse(sc.query('c'));
        assertFalse(sc.query('d'));
    }

    @Test
    public void testRepeatedPattern() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"aa"});
        assertFalse(sc.query('a'));
        assertTrue(sc.query('a'));  // "aa"
        assertTrue(sc.query('a')); // "aa" again
    }

    @Test
    public void testLongWordMatch() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"abcde"});
        assertFalse(sc.query('a'));
        assertFalse(sc.query('b'));
        assertFalse(sc.query('c'));
        assertFalse(sc.query('d'));
        assertTrue(sc.query('e'));  // "abcde" matches
    }

    @Test
    public void testWordAndPrefix() {
        StreamChecker_1032 sc = new StreamChecker_1032(new String[]{"ab", "abc"});
        assertFalse(sc.query('a'));
        assertTrue(sc.query('b'));  // "ab" matches
        assertTrue(sc.query('c')); // "abc" matches
    }

    @Test
    public void testGiantCase() {
        // Build 100 words of length 10
        String[] words = new String[100];
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                sb.append((char) ('a' + (i + j) % 26));
            }
            words[i] = sb.toString();
        }
        StreamChecker_1032 sc = new StreamChecker_1032(words);
        // Query first word char by char
        String firstWord = words[0];
        for (int i = 0; i < firstWord.length() - 1; i++) {
            sc.query(firstWord.charAt(i)); // may or may not match other words
        }
        assertTrue(sc.query(firstWord.charAt(firstWord.length() - 1)));
    }

    @Test
    public void testOnlyTheCurrentSuffixCanMatch() {
        assertStream(new String[]{"abc"}, "xabcx", false, false, false, true, false);
    }

    @Test
    public void testOfficialExampleIncludesLaterIndependentMatch() {
        assertStream(new String[]{"cd", "f", "kl"}, "abcdefghijkl",
                false, false, false, true, false, true, false, false, false, false, false, true);
    }

    @Test
    public void testNestedWordsMatchAtTheirOwnEnds() {
        assertStream(new String[]{"a", "ba", "cba", "dcba"}, "dcba",
                false, false, false, true);
    }

    @Test
    public void testOverlappingMatchesAndNonMatches() {
        assertStream(new String[]{"a", "aba", "bab"}, "abababxaba",
                true, false, true, true, true, true, false, true, false, true);
    }

    @Test
    public void testWordsSharingReversedTrieBranches() {
        assertStream(new String[]{"abc", "xbc", "ybc", "bc", "c"}, "zxbc",
                false, false, false, true);
    }

    @Test
    public void testDuplicateWordsDoNotChangeTheAnswer() {
        assertStream(new String[]{"ab", "ab", "ab", "a"}, "aababa",
                true, true, true, true, true, true);
    }

    @Test
    public void testSingleCharacterDictionaryAtAlphabetBoundaries() {
        assertStream(new String[]{"a", "m", "z"}, "azymz",
                true, true, false, true, true);
    }

    @Test
    public void testNoMatchAfterManyPartialPrefixes() {
        assertStream(new String[]{"abcd", "bcde", "cdef"}, "abcxybcdf",
                false, false, false, false, false, false, false, false, false);
    }

    @Test
    public void testRepeatedPatternMatchesEveryEligibleWindow() {
        assertStream(new String[]{"aa", "aaa"}, "aaaaaa",
                false, true, true, true, true, true);
    }

    @Test
    public void testLongWordAtTheDocumentedLengthBoundary() {
        String longWord = "a".repeat(199) + "z";
        String stream = "b" + longWord;
        boolean[] expected = new boolean[stream.length()];
        expected[stream.length() - 1] = true;
        assertStream(new String[]{longWord}, stream, expected);
    }

    @Test
    public void testLongWordDoesNotMatchWhenOneCharacterDiffers() {
        String word = "a".repeat(200);
        String stream = "a".repeat(199) + "b";
        assertStream(new String[]{word}, stream, new boolean[200]);
    }

    @Test
    public void testStreamLongerThanTheLongestWordDropsOldCharacters() {
        StreamChecker_1032 checker = new StreamChecker_1032(new String[]{"abc"});
        assertFalse(checker.query('a'));
        assertFalse(checker.query('b'));
        assertTrue(checker.query('c'));
        for (int i = 0; i < 250; i++) {
            assertFalse(checker.query('x'));
        }
        assertFalse(checker.query('a'));
        assertFalse(checker.query('b'));
        assertTrue(checker.query('c'));
    }

    @Test
    public void testMaximumNumberOfQueries() {
        StreamChecker_1032 checker = new StreamChecker_1032(new String[]{"a".repeat(200)});
        for (int i = 0; i < 40_000; i++) {
            assertEquals(i >= 199, checker.query('a'), "query index " + i);
        }
    }

    @Test
    public void testMaximumNumberOfWords() {
        String[] words = new String[2_000];
        for (int i = 0; i < words.length; i++) {
            words[i] = base26(i, 3);
        }
        String originalTarget = words[1_999];
        String[] snapshot = words.clone();
        StreamChecker_1032 checker = new StreamChecker_1032(words);
        assertStreamFromChecker(checker, words, originalTarget);
        assertArrayEquals(snapshot, words);
    }

    @Test
    public void testSeededIndependentSuffixSetOracle() {
        Random random = new Random(1_032_2026L);
        String[] words = new String[80];
        for (int i = 0; i < words.length; i++) {
            words[i] = randomWord(random, 1 + random.nextInt(10));
        }
        StringBuilder stream = new StringBuilder();
        for (int i = 0; i < 700; i++) {
            stream.append((char) ('a' + random.nextInt(26)));
        }
        assertStream(words, stream.toString());
    }

    @Test
    public void testSeededOracleWithManyOverlappingWords() {
        Random random = new Random(1032);
        String[] words = new String[100];
        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder();
            int length = 1 + random.nextInt(12);
            for (int j = 0; j < length; j++) {
                word.append((char) ('a' + random.nextInt(4)));
            }
            words[i] = word.toString();
        }
        StringBuilder stream = new StringBuilder();
        for (int i = 0; i < 1_000; i++) {
            stream.append((char) ('a' + random.nextInt(4)));
        }
        assertStream(words, stream.toString());
    }

    @Test
    public void testFreshInstancesHaveIndependentStreams() {
        StreamChecker_1032 first = new StreamChecker_1032(new String[]{"abc"});
        StreamChecker_1032 second = new StreamChecker_1032(new String[]{"xyz"});
        assertFalse(first.query('a'));
        assertFalse(second.query('x'));
        assertFalse(first.query('b'));
        assertFalse(second.query('y'));
        assertTrue(second.query('z'));
        assertTrue(first.query('c'));
    }

    @Test
    public void testConstructionAndQueriesDoNotMutateWordInput() {
        String[] words = {"dog", "cat", "a"};
        String[] snapshot = words.clone();
        StreamChecker_1032 checker = new StreamChecker_1032(words);
        assertStreamFromChecker(checker, words, "dogcatadog");
        assertArrayEquals(snapshot, words);
    }

    @Test
    public void testEmptyDictionaryIsImplementationDefinedButStable() {
        StreamChecker_1032 checker = new StreamChecker_1032(new String[0]);
        for (char letter : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            assertFalse(checker.query(letter));
        }
    }

    @Test
    public void testEmptyWordIsNotAValidNonEmptySuffix() {
        StreamChecker_1032 checker = new StreamChecker_1032(new String[]{""});
        assertFalse(checker.query('a'));
        assertFalse(checker.query('b'));
    }

    @Test
    public void testWordsWithDifferentLengthsUseTheSameStream() {
        assertStream(new String[]{"b", "abcd", "cde", "efghij"}, "abcdefghi",
                false, true, false, true, true, false, false, false, false);
    }

    @Test
    public void testSuffixMatchCanFollowAnUnrelatedCharacter() {
        assertStream(new String[]{"hello", "world"}, "helloworld",
                false, false, false, false, true, false, false, false, false, true);
    }

    @Test
    public void testLettersOutsideDictionaryRemainNegative() {
        assertStream(new String[]{"mnop", "qrst"}, "abcdefghijkl",
                false, false, false, false, false, false, false, false, false, false, false, false);
    }

    private static void assertStream(String[] words, String stream, boolean... expected) {
        assertEquals(stream.length(), expected.length, "expected one result per query");
        StreamChecker_1032 checker = new StreamChecker_1032(words);
        for (int i = 0; i < stream.length(); i++) {
            assertEquals(expected[i], checker.query(stream.charAt(i)), "query index " + i);
        }
    }

    private static void assertStream(String[] words, String stream) {
        Set<String> dictionary = new HashSet<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                dictionary.add(word);
            }
        }
        StreamChecker_1032 checker = new StreamChecker_1032(words);
        StringBuilder history = new StringBuilder();
        for (int i = 0; i < stream.length(); i++) {
            history.append(stream.charAt(i));
            boolean expected = false;
            for (int start = 0; start < history.length(); start++) {
                if (dictionary.contains(history.substring(start))) {
                    expected = true;
                    break;
                }
            }
            assertEquals(expected, checker.query(stream.charAt(i)), "query index " + i);
        }
    }

    private static void assertStreamFromChecker(StreamChecker_1032 checker, String[] words, String stream) {
        Set<String> dictionary = new HashSet<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                dictionary.add(word);
            }
        }
        StringBuilder history = new StringBuilder();
        for (int i = 0; i < stream.length(); i++) {
            history.append(stream.charAt(i));
            boolean expected = false;
            for (int start = 0; start < history.length(); start++) {
                if (dictionary.contains(history.substring(start))) {
                    expected = true;
                    break;
                }
            }
            assertEquals(expected, checker.query(stream.charAt(i)), "query index " + i);
        }
    }

    private static String randomWord(Random random, int length) {
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }
        return word.toString();
    }

    private static String base26(int value, int length) {
        char[] result = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            result[i] = (char) ('a' + value % 26);
            value /= 26;
        }
        return new String(result);
    }
}
