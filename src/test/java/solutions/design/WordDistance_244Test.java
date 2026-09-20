package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests the indexed word-distance data structure against an independent scan oracle. */
public class WordDistance_244Test {

    @Test
    public void officialExample() {
        WordDistance_244 distance = new WordDistance_244(
                new String[]{"practice", "makes", "perfect", "coding", "makes"});

        assertEquals(3, distance.shortest("coding", "practice"));
        assertEquals(1, distance.shortest("makes", "coding"));
    }

    @Test
    public void minimumDictionaryHasOneAdjacentPair() {
        WordDistance_244 distance = new WordDistance_244(new String[]{"a", "b"});

        assertEquals(1, distance.shortest("a", "b"));
    }

    @Test
    public void adjacentWordsAtTheBeginning() {
        String[] words = {"left", "right", "filler", "filler"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("left", "right"));
    }

    @Test
    public void adjacentWordsAtTheEnd() {
        String[] words = {"filler", "filler", "left", "right"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("left", "right"));
    }

    @Test
    public void wordsAtOppositeEndsHaveMaximumDistance() {
        String[] words = {"first", "x", "x", "x", "x", "last"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(5, distance.shortest("first", "last"));
    }

    @Test
    public void closestPairIsNotBetweenFirstOccurrences() {
        String[] words = {"a", "x", "x", "x", "b", "x", "a", "b"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("a", "b"));
    }

    @Test
    public void repeatedOccurrencesOnBothSidesAreCompared() {
        String[] words = {"a", "b", "c", "a", "b", "c", "a", "b"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("a", "b"));
        assertEquals(1, distance.shortest("b", "c"));
        assertEquals(1, distance.shortest("a", "c"));
    }

    @Test
    public void reversedQueriesAreSymmetric() {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(distance.shortest("coding", "practice"), distance.shortest("practice", "coding"));
        assertEquals(distance.shortest("makes", "coding"), distance.shortest("coding", "makes"));
        assertEquals(3, distance.shortest("practice", "coding"));
        assertEquals(1, distance.shortest("coding", "makes"));
    }

    @Test
    public void multipleCallsReuseTheSameInstance() {
        String[] words = {"a", "b", "c", "d", "e"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("a", "b"));
        assertEquals(2, distance.shortest("a", "c"));
        assertEquals(3, distance.shortest("a", "d"));
        assertEquals(4, distance.shortest("a", "e"));
        assertEquals(1, distance.shortest("d", "e"));
    }

    @Test
    public void duplicateWordsDoNotChangeDistanceToAThirdWord() {
        String[] words = {"a", "c", "b", "a", "c", "b", "a"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("a", "b"));
        assertEquals(1, distance.shortest("a", "c"));
        assertEquals(1, distance.shortest("b", "c"));
    }

    @Test
    public void allOccurrencesOfOneWordCanBeFarApart() {
        String[] words = {"a", "x", "x", "x", "x", "x", "b", "x", "x", "a"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(3, distance.shortest("a", "b"));
        assertEquals(3, distance.shortest("b", "a"));
    }

    @Test
    public void interleavedWordsFindTheNearestBoundary() {
        String[] words = {"alpha", "gap", "beta", "gap", "gap", "alpha", "beta", "gap"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("alpha", "beta"));
        assertEquals(1, distance.shortest("beta", "alpha"));
    }

    @Test
    public void longWordsAtTheAllowedLengthAreHandled() {
        String[] words = {"abcdefghij", "middle", "klmnopqrst", "middle"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(2, distance.shortest("abcdefghij", "klmnopqrst"));
    }

    @Test
    public void distanceUsesIndicesRatherThanCharacterContent() {
        String[] words = {"a", "aa", "aaa", "aaaa", "aaaaa"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(4, distance.shortest("a", "aaaaa"));
        assertEquals(2, distance.shortest("aa", "aaaa"));
    }

    @Test
    public void constructorDoesNotMutateTheCallerArray() {
        String[] words = {"red", "blue", "green", "red"};
        String[] original = words.clone();
        WordDistance_244 distance = new WordDistance_244(words);

        assertArrayEquals(original, words);
        assertEquals(1, distance.shortest("red", "blue"));
    }

    @Test
    public void independentInstancesKeepSeparateDictionaries() {
        WordDistance_244 first = new WordDistance_244(new String[]{"a", "x", "b"});
        WordDistance_244 second = new WordDistance_244(new String[]{"a", "b", "x", "x", "b"});

        assertEquals(2, first.shortest("a", "b"));
        assertEquals(1, second.shortest("a", "b"));
        assertEquals(2, first.shortest("a", "b"));
    }

    @Test
    public void exhaustiveQueriesOnSmallDictionaryMatchBruteForce() {
        String[] words = {"a", "b", "c", "a", "d", "c", "b", "d", "a"};
        WordDistance_244 distance = new WordDistance_244(words);
        String[] distinct = {"a", "b", "c", "d"};

        for (int i = 0; i < distinct.length; i++) {
            for (int j = i + 1; j < distinct.length; j++) {
                assertEquals(bruteForce(words, distinct[i], distinct[j]),
                        distance.shortest(distinct[i], distinct[j]));
            }
        }
    }

    @Test
    public void seededRandomQueriesMatchIndependentBruteForceOracle() {
        Random random = new Random(244_2026L);
        String[] vocabulary = new String[32];
        for (int i = 0; i < vocabulary.length; i++) {
            vocabulary[i] = wordFor(100 + i);
        }

        String[] words = new String[4_000];
        System.arraycopy(vocabulary, 0, words, 0, vocabulary.length);
        for (int i = vocabulary.length; i < words.length; i++) {
            words[i] = vocabulary[random.nextInt(vocabulary.length)];
        }

        WordDistance_244 distance = new WordDistance_244(words);
        for (int query = 0; query < 250; query++) {
            int first = random.nextInt(vocabulary.length);
            int second = random.nextInt(vocabulary.length - 1);
            if (second >= first) {
                second++;
            }
            assertEquals(bruteForce(words, vocabulary[first], vocabulary[second]),
                    distance.shortest(vocabulary[first], vocabulary[second]));
        }
    }

    @Test
    public void maximumDictionarySizeFindsTheExtremePair() {
        String[] words = new String[30_000];
        for (int i = 0; i < words.length; i++) {
            words[i] = wordFor(i + 1);
        }
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(29_999, distance.shortest(words[0], words[words.length - 1]));
        assertEquals(1, distance.shortest(words[12_345], words[12_346]));
    }

    @Test
    public void maximumDictionarySizeWithRepeatedWordsFindsAdjacentCopies() {
        String[] words = new String[30_000];
        Arrays.fill(words, "filler");
        words[0] = "first";
        words[words.length - 1] = "last";
        words[14_998] = "targeta";
        words[14_999] = "targetb";
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("targeta", "targetb"));
        assertEquals(29_999, distance.shortest("first", "last"));
    }

    @Test
    public void fiveThousandLegalQueriesAreSupported() {
        String[] words = new String[10_000];
        for (int i = 0; i < 5_000; i++) {
            words[2 * i] = "a" + wordFor(i + 1);
            words[2 * i + 1] = "b" + wordFor(i + 1);
        }
        WordDistance_244 distance = new WordDistance_244(words);

        for (int i = 0; i < 5_000; i++) {
            assertEquals(1, distance.shortest(words[2 * i], words[2 * i + 1]));
        }
    }

    @Test
    public void closestDistanceCanBeAtTheLastTwoOccurrences() {
        String[] words = {"a", "x", "x", "x", "b", "x", "x", "a", "b"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(1, distance.shortest("a", "b"));
        assertEquals(bruteForce(words, "a", "b"), distance.shortest("a", "b"));
    }

    @Test
    public void queryResultsRemainStableAfterManyOtherQueries() {
        String[] words = {"a", "b", "c", "a", "d", "b", "e", "c", "a", "e"};
        WordDistance_244 distance = new WordDistance_244(words);

        int expected = bruteForce(words, "a", "e");
        for (int i = 0; i < 20; i++) {
            distance.shortest("b", "d");
            distance.shortest("c", "e");
            assertEquals(expected, distance.shortest("a", "e"));
        }
    }

    @Test
    public void singleOccurrencesUseTheOnlyPossiblePair() {
        String[] words = {"one", "filler", "filler", "two"};
        WordDistance_244 distance = new WordDistance_244(words);

        assertEquals(3, distance.shortest("one", "two"));
    }

    private static int bruteForce(String[] words, String first, String second) {
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals(first)) {
                continue;
            }
            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(second)) {
                    minimum = Math.min(minimum, Math.abs(i - j));
                }
            }
        }
        return minimum;
    }

    /** Encodes a positive integer as a short lowercase-only word for valid problem data. */
    private static String wordFor(int value) {
        StringBuilder word = new StringBuilder();
        while (value > 0) {
            value--;
            word.append((char) ('a' + value % 26));
            value /= 26;
        }
        return word.reverse().toString();
    }
}
