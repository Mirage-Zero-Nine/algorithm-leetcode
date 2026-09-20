package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for {@link TwoEditWords_2452}.
 *
 * <p>Generated cases use an independent Hamming-distance oracle. The LeetCode contract guarantees
 * non-null arrays of lowercase words with equal lengths, so invalid-length and null inputs are
 * intentionally outside this test class's assertions.</p>
 *
 * @author BorisMirage
 * Time: 2022/10/30 11:39
 */
public class TwoEditWords_2452Test {

    private final TwoEditWords_2452 test = new TwoEditWords_2452();

    @Test
    public void officialExampleIncludesWordsWithinTwoEditsInQueryOrder() {
        String[] queries = {"word", "note", "ants", "wood"};
        String[] dictionary = {"wood", "joke", "moat"};
        assertIterableEquals(List.of("word", "note", "wood"), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void officialExampleWithNoMatchingWordReturnsEmptyList() {
        assertIterableEquals(List.of(), test.twoEditWords(new String[]{"yes"}, new String[]{"not"}));
    }

    @Test
    public void exactMatchRequiresNoEdits() {
        assertIterableEquals(List.of("abc", "xyz"),
                test.twoEditWords(new String[]{"abc", "xyz"}, new String[]{"xyz", "abc"}));
    }

    @Test
    public void oneDifferenceIsIncluded() {
        assertIterableEquals(List.of("abcd"),
                test.twoEditWords(new String[]{"abcd"}, new String[]{"abed"}));
    }

    @Test
    public void exactlyTwoDifferencesAreIncluded() {
        assertIterableEquals(List.of("abcd"),
                test.twoEditWords(new String[]{"abcd"}, new String[]{"abxy"}));
    }

    @Test
    public void threeDifferencesAreExcluded() {
        assertIterableEquals(List.of(),
                test.twoEditWords(new String[]{"abcd"}, new String[]{"wxyz"}));
    }

    @Test
    public void oneCharacterWordsCoverExactOneAndTwoEditBoundaries() {
        String[] queries = {"a", "b", "c", "z"};
        String[] dictionary = {"a", "z"};
        assertIterableEquals(List.of("a", "b", "c", "z"), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void repeatedCharactersUsePositionsNotDistinctLetterCounts() {
        String[] queries = {"aaaa", "abab", "bbbb", "abbb"};
        String[] dictionary = {"aaab", "baba"};
        assertIterableEquals(expectedByOracle(queries, dictionary), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void candidateCanMatchOnlyALaterDictionaryWord() {
        String[] queries = {"aaaa", "bbbb", "cccc"};
        String[] dictionary = {"zzzz", "xxbb", "aaab"};
        assertIterableEquals(List.of("aaaa", "bbbb"), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void noDictionaryCandidateWithinTwoDifferencesIsExcluded() {
        String[] queries = {"aaaa", "bbbb", "cccc"};
        String[] dictionary = {"zzzz", "yyyy"};
        assertIterableEquals(List.of(), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void queryOrderIsPreservedRegardlessOfDictionaryOrder() {
        String[] queries = {"aaaa", "cccc", "bbbb", "dddd"};
        String[] dictionary = {"bbba", "aaab", "dddx"};
        assertIterableEquals(List.of("aaaa", "bbbb", "dddd"), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void duplicateQueriesRemainDuplicatedInTheResult() {
        String[] queries = {"code", "code", "coda", "nope", "code"};
        String[] dictionary = {"coda"};
        assertIterableEquals(List.of("code", "code", "coda", "code"),
                test.twoEditWords(queries, dictionary));
    }

    @Test
    public void duplicateDictionaryEntriesDoNotDuplicateAQuery() {
        String[] queries = {"same", "zzzz"};
        String[] dictionary = {"same", "same", "sane", "same"};
        assertIterableEquals(List.of("same"), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void mixedQueriesAreCheckedAgainstEveryDictionaryWord() {
        String[] queries = {"stone", "money", "atone", "phony", "short"};
        String[] dictionary = {"money", "phone", "stone"};
        assertIterableEquals(expectedByOracle(queries, dictionary), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void everyQueryMayMatchTheSameDictionaryWord() {
        String[] queries = {"aaaa", "aaab", "aabb", "abbb", "bbbb"};
        String[] dictionary = {"aaaa"};
        assertIterableEquals(List.of("aaaa", "aaab", "aabb"),
                test.twoEditWords(queries, dictionary));
    }

    @Test
    public void emptyQueryArrayReturnsAnEmptyResult() {
        assertIterableEquals(List.of(), test.twoEditWords(new String[0], new String[]{"a"}));
    }

    @Test
    public void emptyDictionaryProducesNoMatches() {
        assertIterableEquals(List.of(), test.twoEditWords(new String[]{"a", "abc"}, new String[0]));
    }

    @Test
    public void maximumWordLengthChecksDifferencesAtBothEnds() {
        String base = "a".repeat(100);
        String oneAtStart = "b" + "a".repeat(99);
        String twoAtEnd = "a".repeat(98) + "cc";
        String threeAtStart = "bbb" + "a".repeat(97);
        String[] queries = {base, oneAtStart, twoAtEnd, threeAtStart};
        assertIterableEquals(List.of(base, oneAtStart, twoAtEnd),
                test.twoEditWords(queries, new String[]{base}));
    }

    @Test
    public void maximumArraySizesAreHandledWithAnIndependentOracle() {
        String[] queries = new String[100];
        String[] dictionary = new String[100];
        for (int i = 0; i < queries.length; i++) {
            queries[i] = wordFromIndex(i, 100, 'a');
            dictionary[i] = wordFromIndex(i * 7 + 3, 100, 'a');
        }
        assertIterableEquals(expectedByOracle(queries, dictionary), test.twoEditWords(queries, dictionary));
    }

    @Test
    public void deterministicSmallAlphabetCasesMatchIndependentOracle() {
        Random random = new Random(2452L);
        for (int scenario = 0; scenario < 80; scenario++) {
            int length = 1 + random.nextInt(8);
            String[] queries = randomWords(random, 1 + random.nextInt(12), length, 3);
            String[] dictionary = randomWords(random, 1 + random.nextInt(12), length, 3);
            assertIterableEquals(expectedByOracle(queries, dictionary),
                    test.twoEditWords(queries, dictionary), "scenario " + scenario);
        }
    }

    @Test
    public void inputsAreNotMutated() {
        String[] queries = {"word", "note", "ants"};
        String[] dictionary = {"wood", "joke", "moat"};
        String[] originalQueries = queries.clone();
        String[] originalDictionary = dictionary.clone();
        test.twoEditWords(queries, dictionary);
        assertArrayEquals(originalQueries, queries);
        assertArrayEquals(originalDictionary, dictionary);
    }

    @Test
    public void sameInstanceDoesNotLeakResultsBetweenCalls() {
        assertIterableEquals(List.of("abc"), test.twoEditWords(new String[]{"abc"}, new String[]{"abc"}));
        assertIterableEquals(List.of(), test.twoEditWords(new String[]{"xyz"}, new String[]{"abc"}));
        assertIterableEquals(List.of("xyz"), test.twoEditWords(new String[]{"xyz"}, new String[]{"xyz"}));
    }

    @Test
    public void returnedListIsIndependentFromSubsequentCalls() {
        List<String> first = test.twoEditWords(new String[]{"same", "sane"}, new String[]{"same"});
        List<String> second = test.twoEditWords(new String[]{"different"}, new String[]{"different"});
        first.clear();
        assertIterableEquals(List.of("different"), second);
        assertIterableEquals(List.of(), first);
    }

    @Test
    public void longWordsUseAllPositionsWhenFindingDifferences() {
        String base = "abcdefghij".repeat(10);
        String twoDifferences = "zbcdefghij" + "abcdefghij".repeat(8) + "abcdefghiY";
        String threeDifferences = "zbcdefghij" + "abcdefghij".repeat(8) + "abcdefgxyz";
        String[] queries = {base, twoDifferences, threeDifferences};
        assertIterableEquals(expectedByOracle(queries, new String[]{base}),
                test.twoEditWords(queries, new String[]{base}));
    }

    @Test
    public void allEligibleQueriesAreRetainedAtTheMaximumArrayBoundary() {
        String[] queries = new String[100];
        String[] dictionary = {"a".repeat(100)};
        for (int i = 0; i < queries.length; i++) {
            queries[i] = i % 4 == 0 ? dictionary[0]
                    : i % 4 == 1 ? "b" + "a".repeat(99)
                    : i % 4 == 2 ? "c" + "d" + "a".repeat(98)
                    : "e" + "f" + "g" + "a".repeat(97);
        }
        List<String> expected = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (i % 4 != 3) {
                expected.add(queries[i]);
            }
        }
        assertIterableEquals(expected, test.twoEditWords(queries, dictionary));
    }

    private static List<String> expectedByOracle(String[] queries, String[] dictionary) {
        List<String> expected = new ArrayList<>();
        for (String query : queries) {
            boolean matches = false;
            for (String word : dictionary) {
                int differences = 0;
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != word.charAt(i)) {
                        differences++;
                    }
                }
                if (differences <= 2) {
                    matches = true;
                    break;
                }
            }
            if (matches) {
                expected.add(query);
            }
        }
        return expected;
    }

    private static String[] randomWords(Random random, int count, int length, int alphabetSize) {
        String[] words = new String[count];
        for (int i = 0; i < count; i++) {
            StringBuilder word = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                word.append((char) ('a' + random.nextInt(alphabetSize)));
            }
            words[i] = word.toString();
        }
        return words;
    }

    private static String wordFromIndex(int index, int length, char firstCharacter) {
        char[] word = new char[length];
        Arrays.fill(word, firstCharacter);
        int position = index % length;
        word[position] = (char) ('a' + (index % 26));
        if (index % 5 == 0) {
            word[(position + 37) % length] = (char) ('a' + ((index + 9) % 26));
        }
        return new String(word);
    }
}
