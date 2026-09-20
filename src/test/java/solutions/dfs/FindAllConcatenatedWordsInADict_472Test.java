package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for LeetCode 472, Concatenated Words.
 *
 * <p>The expected result is compared as a set because the problem allows any output order. The
 * oracle in this class removes the candidate word from the dictionary and runs an independent
 * prefix dynamic program, so a word is accepted only when it uses at least two other words.
 */
public class FindAllConcatenatedWordsInADict_472Test {

    private final FindAllConcatenatedWordsInADict_472 solution =
        new FindAllConcatenatedWordsInADict_472();

    @Test
    void officialExampleOne() {
        assertWordSet(
            new String[] {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"},
            "catsdogcats", "dogcatsdog", "ratcatdogcat"
        );
    }

    @Test
    void officialExampleTwo() {
        assertWordSet(new String[] {"cat", "dog", "catdog"}, "catdog");
    }

    @Test
    void unrelatedWordsProduceNoResults() {
        assertWordSet(new String[] {"apple", "orange", "pear", "plum"});
    }

    @Test
    void twoPieceWordsCanBeFoundInBothDirections() {
        assertWordSet(new String[] {"a", "b", "ab", "ba"}, "ab", "ba");
    }

    @Test
    void threeOrMorePiecesMayReuseTheSameShortWord() {
        assertWordSet(new String[] {"a", "aa", "aaa", "aaaaa"}, "aa", "aaa", "aaaaa");
    }

    @Test
    void officialExampleRequiresPrefixChoicesAndReuse() {
        assertWordSet(
            new String[] {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "b", "bb", "bbb"},
            "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "bb", "bbb"
        );
    }

    @Test
    void prefixTrapMustNotTreatAWholeCandidateAsItsOwnComponent() {
        assertWordSet(new String[] {"cat", "cats", "catsdog", "dog", "catsdogcats"}, "catsdog", "catsdogcats");
    }

    @Test
    void matchingPrefixesWithoutACompleteSegmentationAreRejected() {
        assertWordSet(new String[] {"ab", "abc", "abcd", "abcde", "bc"});
    }

    @Test
    void chainedWordsAreAllEvaluatedAfterShorterWords() {
        assertWordSet(
            new String[] {"sun", "flower", "sunflower", "sunflowers", "sunflowersun", "s", "un"},
            "sun", "sunflower", "sunflowers", "sunflowersun"
        );
    }

    @Test
    void independentWordFamiliesDoNotInterfere() {
        assertWordSet(
            new String[] {"car", "pet", "carpet", "dog", "house", "doghouse", "carpetdoghouse", "zzz"},
            "carpet", "doghouse", "carpetdoghouse"
        );
    }

    @Test
    void aSingleWordCannotBeConcatenated() {
        assertWordSet(new String[] {"alone"});
    }

    @Test
    void anEmptyArrayIsAnEmptyResultForTheImplementationSupportedEdge() {
        assertWordSet(new String[0]);
    }

    @Test
    void emptyStringDoesNotByItselfMakeAWordConcatenated() {
        assertWordSet(new String[] {"", "a"});
    }

    @Test
    void emptyStringCanRemainInTheDictionaryForARealTwoPieceWord() {
        assertWordSet(new String[] {"", "a", "aa"}, "aa");
    }

    @Test
    void duplicateInputIsHandledWithoutThrowing() {
        List<String> output = solution.findAllConcatenatedWordsInADict(
            new String[] {"cat", "dog", "catdog", "catdog"}
        );
        assertFalse(output.isEmpty());
        assertEquals(Set.of("catdog"), new HashSet<>(output));
    }

    @Test
    void resultDoesNotDependOnInputOrder() {
        String[] firstOrder = {"catsdogcats", "rat", "dog", "cat", "cats", "dogcatsdog"};
        String[] secondOrder = {"cat", "dogcatsdog", "cats", "dog", "rat", "catsdogcats"};
        assertMatchesOracle(firstOrder);
        assertMatchesOracle(secondOrder);
    }

    @Test
    void overlappingPrefixesUseAValidLaterSplit() {
        assertMatchesOracle(new String[] {"a", "ab", "abc", "bc", "c", "abca", "abcab", "caba"});
    }

    @Test
    void onlyWordsWithAtLeastTwoComponentsAreReturned() {
        String[] words = {"a", "ab", "abc", "abcd", "b", "bc", "cd", "abcde"};
        assertMatchesOracle(words);
        assertFalse(new HashSet<>(solution.findAllConcatenatedWordsInADict(words)).contains("a"));
    }

    @Test
    void repeatedCharactersHaveManyPossibleSegmentations() {
        assertMatchesOracle(new String[] {"a", "aa", "aaaa", "aaaaaaaa", "aaaaaaaaaaaa"});
    }

    @Test
    void repeatedCallsOnTheSameInstanceDoNotLeakState() {
        String[] first = {"a", "b", "ab", "aba", "bab"};
        String[] second = {"cat", "dog", "catdog"};
        assertMatchesOracle(first);
        assertMatchesOracle(second);
        assertMatchesOracle(first);
    }

    @Test
    void freshInputArraysKeepCallsIndependent() {
        String[] words = {"red", "blue", "redblue", "green", "redbluegreen"};
        Set<String> expected = independentOracle(words);
        List<String> first = solution.findAllConcatenatedWordsInADict(words.clone());
        List<String> second = new FindAllConcatenatedWordsInADict_472().findAllConcatenatedWordsInADict(words.clone());
        assertEquals(expected, new HashSet<>(first));
        assertEquals(expected, new HashSet<>(second));
    }

    @Test
    void maximumAllowedWordLengthIsSupported() {
        String thirtyAs = "a".repeat(30);
        assertWordSet(new String[] {"a", "aa", "b", thirtyAs}, thirtyAs, "aa");
    }

    @Test
    void lowercaseBoundaryCharactersRemainOrdinaryWordCharacters() {
        assertMatchesOracle(new String[] {"a", "z", "az", "za", "aza", "zaz", "zz"});
    }

    @Test
    void aLargeAllSameCharacterFamilyRemainsWithinTheRuntimeBudget() {
        List<String> words = new ArrayList<>();
        for (int length = 1; length <= 30; length++) {
            words.add("a".repeat(length));
        }
        assertMatchesOracle(words.toArray(String[]::new));
    }

    @Test
    void maximumWordCountWithEqualLengthNoiseHasOnlyTheExplicitCompound() {
        String[] words = new String[10_000];
        for (int i = 0; i < 9_997; i++) {
            words[i] = "c" + encodeLowercase(i + 100, 4);
        }
        words[9_997] = "a";
        words[9_998] = "b";
        words[9_999] = "ab";
        assertWordSet(words, "ab");
    }

    @Test
    void maximumTotalCharacterCountWithNoShortWordsProducesNoResults() {
        String[] words = new String[3_332];
        for (int i = 0; i < words.length; i++) {
            words[i] = encodeLowercase(i + 1_000, 30);
        }
        assertWordSet(words);
    }

    @Test
    void seededRandomDictionariesMatchAnIndependentOracle() {
        Random random = new Random(472_2026L);
        for (int trial = 0; trial < 100; trial++) {
            Set<String> words = new HashSet<>();
            int size = 1 + random.nextInt(12);
            while (words.size() < size) {
                int length = 1 + random.nextInt(7);
                StringBuilder word = new StringBuilder(length);
                for (int i = 0; i < length; i++) {
                    word.append((char) ('a' + random.nextInt(3)));
                }
                words.add(word.toString());
            }
            assertMatchesOracle(words.toArray(String[]::new));
        }
    }

    @Test
    void exhaustiveSmallBinaryDictionariesMatchAnIndependentOracle() {
        List<String> universe = new ArrayList<>();
        for (int length = 1; length <= 3; length++) {
            addBinaryWords(universe, new StringBuilder(), length);
        }
        for (int mask = 0; mask < (1 << universe.size()); mask += 7) {
            List<String> selected = new ArrayList<>();
            for (int bit = 0; bit < universe.size(); bit++) {
                if ((mask & (1 << bit)) != 0) {
                    selected.add(universe.get(bit));
                }
            }
            assertMatchesOracle(selected.toArray(String[]::new));
        }
    }

    private void assertWordSet(String[] words, String... expected) {
        List<String> actual = solution.findAllConcatenatedWordsInADict(words);
        Set<String> expectedSet = Set.of(expected);
        assertEquals(expectedSet, new HashSet<>(actual));
        assertEquals(expectedSet.size(), actual.size(), "unique LeetCode input must yield unique output");
    }

    private void assertMatchesOracle(String[] words) {
        Set<String> expected = independentOracle(words);
        List<String> actual = solution.findAllConcatenatedWordsInADict(words);
        assertEquals(expected, new HashSet<>(actual));
        assertEquals(expected.size(), actual.size());
    }

    private static Set<String> independentOracle(String[] words) {
        Set<String> allWords = new HashSet<>(Arrays.asList(words));
        Set<String> result = new HashSet<>();
        for (String candidate : words) {
            Set<String> dictionary = new HashSet<>(allWords);
            dictionary.remove(candidate);
            boolean[] reachable = new boolean[candidate.length() + 1];
            reachable[0] = true;
            for (int end = 1; end <= candidate.length(); end++) {
                for (int start = 0; start < end; start++) {
                    if (reachable[start] && dictionary.contains(candidate.substring(start, end))) {
                        reachable[end] = true;
                        break;
                    }
                }
            }
            if (reachable[candidate.length()]) {
                result.add(candidate);
            }
        }
        return result;
    }

    private static void addBinaryWords(List<String> words, StringBuilder prefix, int remaining) {
        if (remaining == 0) {
            words.add(prefix.toString());
            return;
        }
        prefix.append('a');
        addBinaryWords(words, prefix, remaining - 1);
        prefix.setLength(prefix.length() - 1);
        prefix.append('b');
        addBinaryWords(words, prefix, remaining - 1);
        prefix.setLength(prefix.length() - 1);
    }

    private static String encodeLowercase(int value, int length) {
        char[] encoded = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            encoded[i] = (char) ('a' + value % 26);
            value /= 26;
        }
        return new String(encoded);
    }
}
