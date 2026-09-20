package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class NumSmallerByFrequency_1170Test {

    private final NumSmallerByFrequency_1170 test = new NumSmallerByFrequency_1170();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1}, test.numSmallerByFrequency(new String[]{"cbd"}, new String[]{"zaaaz"}));
        assertArrayEquals(new int[]{1, 2}, test.numSmallerByFrequency(new String[]{"bbb", "cc"}, new String[]{"a", "aa", "aaa", "aaaa"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.numSmallerByFrequency(new String[]{"z"}, new String[]{"a"}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{3, 2, 1, 0},
            test.numSmallerByFrequency(new String[]{"a", "aa", "aaa", "aaaa"}, new String[]{"aa", "aaa", "aaaa"}));
    }

    @Test
    public void testAllQueriesHigherOrEqual() {
        assertArrayEquals(new int[]{0, 0},
            test.numSmallerByFrequency(new String[]{"aaaa", "zzzz"}, new String[]{"a", "bb"}));
    }

    @Test
    public void testAllQueriesLower() {
        assertArrayEquals(new int[]{3, 3},
            test.numSmallerByFrequency(new String[]{"a", "b"}, new String[]{"cc", "ddd", "eeee"}));
    }

    @Test
    public void testMixedScores() {
        assertArrayEquals(new int[]{2, 1, 1},
            test.numSmallerByFrequency(new String[]{"ab", "aab", "aaab"}, new String[]{"aaaa", "b", "cc"}));
    }

    @Test
    public void testSingleLetterWords() {
        assertArrayEquals(new int[]{0, 0, 0},
            test.numSmallerByFrequency(new String[]{"a", "m", "z"}, new String[]{"b", "c"}));
    }

    @Test
    public void testFrequencyFromSmallestCharacterOnly() {
        assertArrayEquals(new int[]{2},
            test.numSmallerByFrequency(new String[]{"dcce"}, new String[]{"aaaa", "bbb"}));
    }

    @Test
    public void testRepeatedNonSmallestCharactersDoNotChangeScore() {
        assertArrayEquals(new int[]{0, 1, 0},
            test.numSmallerByFrequency(
                new String[]{"zzzz", "baaa", "cbbbbbbbb"},
                new String[]{"a", "bb", "cccc"}));
    }

    @Test
    public void testStrictComparisonExcludesEqualScores() {
        assertArrayEquals(new int[]{3, 2, 1},
            test.numSmallerByFrequency(
                new String[]{"a", "bb", "ccc"},
                new String[]{"x", "yy", "zzz", "wwww"}));
    }

    @Test
    public void testDuplicateWordsCountIndividually() {
        assertArrayEquals(new int[]{4, 0},
            test.numSmallerByFrequency(
                new String[]{"a", "aaaaa"},
                new String[]{"bb", "bb", "ccc", "ccc", "d"}));
    }

    @Test
    public void testDuplicateQueriesPreserveInputOrder() {
        String[] queries = {"ab", "aaaa", "ab", "zz", "aaaa"};
        String[] words = {"bbb", "cccc", "e", "ffffff"};
        assertArrayEquals(oracle(queries, words), test.numSmallerByFrequency(queries, words));
    }

    @Test
    public void testWordOrderDoesNotAffectCounts() {
        String[] queries = {"a", "bb", "ccc", "dddd"};
        assertArrayEquals(
            test.numSmallerByFrequency(queries, new String[]{"e", "ffff", "ggg", "hh"}),
            test.numSmallerByFrequency(queries, new String[]{"hh", "e", "ggg", "ffff"}));
    }

    @Test
    public void testEveryQueryScoreIsGreaterThanOrEqualToAllWords() {
        assertArrayEquals(new int[]{0, 0, 0},
            test.numSmallerByFrequency(
                new String[]{"aaaa", "zzzz", "mmmm"},
                new String[]{"a", "bb", "ccc"}));
    }

    @Test
    public void testEveryQueryScoreIsLessThanAllWords() {
        assertArrayEquals(new int[]{4, 4, 4},
            test.numSmallerByFrequency(
                new String[]{"a", "b", "z"},
                new String[]{"cc", "ddd", "eeee", "fffff"}));
    }

    @Test
    public void testSingleCharacterStringsHaveScoreOne() {
        String[] alphabet = new String[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = String.valueOf((char) ('a' + i));
        }
        assertArrayEquals(new int[26], test.numSmallerByFrequency(alphabet, alphabet.clone()));
    }

    @Test
    public void testMinimumAndMaximumAllowedStringLengths() {
        String[] queries = {"z", "aaaaaaaaaa", "bcdefghijk"};
        String[] words = {"a", "bbbbbbbbbb", "cccccccccc", "ddddddddd"};
        assertArrayEquals(oracle(queries, words), test.numSmallerByFrequency(queries, words));
    }

    @Test
    public void testMaximumScoreComesFromTenCopiesOfSmallestCharacter() {
        String[] queries = {"aaaaaaaaaa", "baaaaaaaaa", "zzzzzzzzzz"};
        String[] words = {"bbbbbbbbbb", "ccccccccc", "dddddddddd", "e"};
        assertArrayEquals(new int[]{0, 2, 0}, test.numSmallerByFrequency(queries, words));
    }

    @Test
    public void testAlphabetBoundaryCharactersAndInteriorMinimums() {
        String[] queries = {"zaaa", "yzzzz", "xwwww", "mnop"};
        String[] words = {"zzzz", "yyyyy", "xxxxx", "nnnnnn"};
        assertArrayEquals(oracle(queries, words), test.numSmallerByFrequency(queries, words));
    }

    @Test
    public void testWordsWithAllPossibleScores() {
        String[] queries = {"a", "bb", "ccc", "dddd", "eeeee"};
        String[] words = {"f", "gg", "hhh", "iiii", "jjjjj", "kkkkkk"};
        assertArrayEquals(new int[]{5, 4, 3, 2, 1},
            test.numSmallerByFrequency(queries, words));
    }

    @Test
    public void testEmptyQueryArray() {
        assertArrayEquals(new int[]{},
            test.numSmallerByFrequency(new String[]{}, new String[]{"a", "aa"}));
    }

    @Test
    public void testEmptyWordsArrayReturnsZeroForEveryQuery() {
        assertArrayEquals(new int[]{0, 0, 0},
            test.numSmallerByFrequency(new String[]{"a", "bb", "ccc"}, new String[]{}));
    }

    @Test
    public void testInputsAreNotMutated() {
        String[] queries = {"dcce", "aab", "zz"};
        String[] words = {"aaaa", "bbb", "c"};
        String[] originalQueries = queries.clone();
        String[] originalWords = words.clone();
        test.numSmallerByFrequency(queries, words);
        assertArrayEquals(originalQueries, queries);
        assertArrayEquals(originalWords, words);
    }

    @Test
    public void testRepeatedCallsUseIndependentScores() {
        assertArrayEquals(new int[]{2},
            test.numSmallerByFrequency(new String[]{"a"}, new String[]{"bb", "ccc"}));
        assertArrayEquals(new int[]{0, 1},
            test.numSmallerByFrequency(new String[]{"aaaa", "a"}, new String[]{"b", "cc"}));
    }

    @Test
    public void testMutatingReturnedArrayDoesNotAffectLaterCalls() {
        int[] first = test.numSmallerByFrequency(new String[]{"a", "bb"}, new String[]{"ccc"});
        first[0] = 99;
        assertArrayEquals(new int[]{1, 1},
            test.numSmallerByFrequency(new String[]{"a", "bb"}, new String[]{"ccc"}));
    }

    @Test
    public void testExhaustiveBinaryAlphabetStringsAgainstDirectOracle() {
        String[] values = new String[62];
        int index = 0;
        for (int length = 1; length <= 5; length++) {
            for (int mask = 0; mask < (1 << length); mask++) {
                StringBuilder value = new StringBuilder(length);
                for (int bit = 0; bit < length; bit++) {
                    value.append((mask & (1 << bit)) == 0 ? 'a' : 'b');
                }
                values[index++] = value.toString();
            }
        }
        assertArrayEquals(oracle(values, values), test.numSmallerByFrequency(values, values));
    }

    @Test
    public void testSeededRandomInputsAgainstDirectOracle() {
        Random random = new Random(1170L);
        for (int trial = 0; trial < 250; trial++) {
            String[] queries = randomStrings(random, 1 + random.nextInt(20));
            String[] words = randomStrings(random, 1 + random.nextInt(20));
            assertArrayEquals(oracle(queries, words), test.numSmallerByFrequency(queries, words),
                "random trial " + trial);
        }
    }

    @Test
    public void testOfficialMaximumArraySizesAndStringLengths() {
        String[] queries = new String[2000];
        String[] words = new String[2000];
        for (int i = 0; i < 2000; i++) {
            queries[i] = repeated((char) ('a' + (i % 26)), 1 + (i % 10));
            words[i] = repeated((char) ('z' - (i % 26)), 10 - (i % 10));
        }
        assertArrayEquals(oracle(queries, words), test.numSmallerByFrequency(queries, words));
    }

    @Test
    public void testLargeDuplicateGroupsAreCountedExactly() {
        String[] queries = {"a", "aa", "aaa", "aaaa", "aaaaa"};
        String[] words = new String[1000];
        Arrays.fill(words, "bbbbbbbbbb");
        assertArrayEquals(new int[]{1000, 1000, 1000, 1000, 1000},
            test.numSmallerByFrequency(queries, words));
    }

    @Test
    public void testGiantCase() {
        String[] queries = new String[500];
        String[] words = new String[500];
        for (int i = 0; i < 500; i++) {
            queries[i] = "a";
            words[i] = "aa";
        }
        int[] expected = new int[500];
        java.util.Arrays.fill(expected, 500);
        assertArrayEquals(expected, test.numSmallerByFrequency(queries, words));
    }

    private static int[] oracle(String[] queries, String[] words) {
        int[] expected = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int queryScore = frequencyOfSmallest(queries[i]);
            for (String word : words) {
                if (queryScore < frequencyOfSmallest(word)) {
                    expected[i]++;
                }
            }
        }
        return expected;
    }

    private static int frequencyOfSmallest(String value) {
        char smallest = value.charAt(0);
        for (int i = 1; i < value.length(); i++) {
            smallest = (char) Math.min(smallest, value.charAt(i));
        }
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == smallest) {
                count++;
            }
        }
        return count;
    }

    private static String[] randomStrings(Random random, int count) {
        String[] values = new String[count];
        for (int i = 0; i < count; i++) {
            values[i] = randomString(random, 1 + random.nextInt(10));
        }
        return values;
    }

    private static String randomString(Random random, int length) {
        StringBuilder value = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            value.append((char) ('a' + random.nextInt(26)));
        }
        return value.toString();
    }

    private static String repeated(char character, int length) {
        char[] value = new char[length];
        Arrays.fill(value, character);
        return new String(value);
    }
}
