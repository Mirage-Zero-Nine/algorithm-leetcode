package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the stateful LeetCode 642 autocomplete design.
 *
 * <p>The expected suggestions are calculated by a small independent frequency
 * model instead of by another trie implementation. This keeps ranking,
 * learning, and reset assertions independent from the production data
 * structure.</p>
 */
class AutocompleteSystem_642Test {

    @Test
    void officialExample() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"i love you", "island", "ironman", "i love leetcode"},
                new int[]{5, 3, 2, 2});

        assertEquals(Arrays.asList("i love you", "island", "i love leetcode"), system.input('i'));
        assertEquals(Arrays.asList("i love you", "i love leetcode"), system.input(' '));
        assertEquals(List.of(), system.input('a'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(Arrays.asList("i love you", "island", "i love leetcode"), system.input('i'));
        assertEquals(Arrays.asList("i love you", "i love leetcode", "i a"), system.input(' '));
        assertEquals(List.of("i a"), system.input('a'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(Arrays.asList("i love you", "island", "i a"), system.input('i'));
        assertEquals(Arrays.asList("i love you", "i a", "i love leetcode"), system.input(' '));
        assertEquals(List.of("i a"), system.input('a'));
        assertEquals(List.of(), system.input('#'));
    }

    @Test
    void emptyInitialStateSupportsTheClassBoundary() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{}, new int[]{});

        assertEquals(List.of(), system.input(' '));
        assertEquals(List.of(), system.input('#'));
        assertEquals(List.of(" "), system.input(' '));
    }

    @Test
    void noMatchRemainsEmptyUntilTheSessionEnds() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{"hello"}, new int[]{2});

        assertEquals(List.of(), system.input('x'));
        assertEquals(List.of(), system.input('h'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(List.of("hello"), system.input('h'));
    }

    @Test
    void aNoMatchSessionIsLearnedAndAvailableAfterHash() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{"abc"}, new int[]{1});

        assertEquals(List.of("abc"), system.input('a'));
        assertEquals(List.of(), system.input('d'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(Arrays.asList("abc", "ad"), system.input('a'));
    }

    @Test
    void learningAnExistingSentenceIncreasesItsRank() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"a x", "a y"}, new int[]{1, 1});

        assertEquals(Arrays.asList("a x", "a y"), system.input('a'));
        assertEquals(Arrays.asList("a x", "a y"), system.input(' '));
        assertEquals(List.of("a y"), system.input('y'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(Arrays.asList("a y", "a x"), system.input('a'));
    }

    @Test
    void duplicateInitialSentencesAccumulateTheirWeights() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"same", "other", "same"}, new int[]{2, 5, 3});

        assertEquals(List.of("same"), system.input('s'));
        assertEquals(List.of("same"), system.input('a'));
    }

    @Test
    void onlyTheThreeHottestMatchingSentencesAreReturned() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"m1", "m2", "m3", "m4"}, new int[]{10, 9, 8, 7});

        assertEquals(Arrays.asList("m1", "m2", "m3"), system.input('m'));
    }

    @Test
    void equalWeightsUseAscendingAsciiOrder() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"ab", "ac", "aa"}, new int[]{1, 1, 1});

        assertEquals(Arrays.asList("aa", "ab", "ac"), system.input('a'));
    }

    @Test
    void spacesParticipateInAsciiTieBreaking() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"i love", "i r", "i a"}, new int[]{1, 1, 1});

        assertEquals(Arrays.asList("i a", "i love", "i r"), system.input('i'));
        assertEquals(Arrays.asList("i a", "i love", "i r"), system.input(' '));
    }

    @Test
    void prefixFilteringIncludesOnlyCompleteHistoricalSentences() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"car", "cart", "cat", "dog"}, new int[]{1, 4, 3, 9});

        assertEquals(Arrays.asList("cart", "cat", "car"), system.input('c'));
        assertEquals(Arrays.asList("cart", "cat", "car"), system.input('a'));
        assertEquals(Arrays.asList("cart", "car"), system.input('r'));
        assertEquals(List.of(), system.input('z'));
    }

    @Test
    void internalSpacesAreOrdinaryPrefixCharacters() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"new york", "new jersey", "newark"}, new int[]{4, 3, 2});

        assertEquals(Arrays.asList("new york", "new jersey", "newark"), system.input('n'));
        assertEquals(Arrays.asList("new york", "new jersey", "newark"), system.input('e'));
        assertEquals(Arrays.asList("new york", "new jersey", "newark"), system.input('w'));
        assertEquals(Arrays.asList("new york", "new jersey"), system.input(' '));
        assertEquals(List.of("new york"), system.input('y'));
    }

    @Test
    void fewerThanThreeMatchesReturnAllMatches() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"apple", "application"}, new int[]{7, 6});

        assertEquals(Arrays.asList("apple", "application"), system.input('a'));
        assertEquals(Arrays.asList("apple", "application"), system.input('p'));
        assertEquals(Arrays.asList("apple", "application"), system.input('p'));
        assertEquals(Arrays.asList("apple", "application"), system.input('l'));
        assertEquals(List.of("apple"), system.input('e'));
    }

    @Test
    void hashReturnsEmptyAndStartsASeparateSession() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{"one"}, new int[]{1});

        assertEquals(List.of("one"), system.input('o'));
        assertEquals(List.of("one"), system.input('n'));
        assertEquals(List.of("one"), system.input('e'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(List.of("one"), system.input('o'));
        assertEquals(List.of(), system.input('#'));
    }

    @Test
    void learnedSentencesCanCreateAPreviouslyMissingPrefix() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{"dog"}, new int[]{2});

        assertEquals(List.of(), system.input('c'));
        assertEquals(List.of(), system.input('a'));
        assertEquals(List.of(), system.input('t'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(List.of("cat"), system.input('c'));
    }

    @Test
    void completedSentenceStillMatchesAtItsTerminalPrefix() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{"go"}, new int[]{4});

        assertEquals(List.of("go"), system.input('g'));
        assertEquals(List.of("go"), system.input('o'));
        assertEquals(List.of(), system.input('!'));
    }

    @Test
    void largeFrequencyValuesRemainOrdered() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"x low", "x high", "x middle"}, new int[]{1, 50, 25});

        assertEquals(Arrays.asList("x high", "x middle", "x low"), system.input('x'));
        assertEquals(Arrays.asList("x high", "x middle", "x low"), system.input(' '));
    }

    @Test
    void leadingAndTrailingSpacesAreRetainedByTheClass() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{" hello ", " hello", "hello "}, new int[]{3, 2, 1});

        assertEquals(Arrays.asList(" hello ", " hello"), system.input(' '));
        assertEquals(Arrays.asList(" hello ", " hello"), system.input('h'));
        assertEquals(Arrays.asList(" hello ", " hello"), system.input('e'));
        assertEquals(Arrays.asList(" hello ", " hello"), system.input('l'));
        assertEquals(Arrays.asList(" hello ", " hello"), system.input('l'));
        assertEquals(Arrays.asList(" hello ", " hello"), system.input('o'));
        assertEquals(List.of(" hello "), system.input(' '));
    }

    @Test
    void separateInstancesDoNotShareTrieOrHistory() {
        AutocompleteSystem_642 first = new AutocompleteSystem_642(new String[]{"alpha"}, new int[]{2});
        AutocompleteSystem_642 second = new AutocompleteSystem_642(new String[]{"beta"}, new int[]{3});

        assertEquals(List.of("alpha"), first.input('a'));
        assertEquals(List.of("beta"), second.input('b'));
        assertEquals(List.of(), first.input('b'));
        assertEquals(List.of(), second.input('a'));
    }

    @Test
    void repeatedCallsAfterResetDoNotRetainTheOldPointer() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(
                new String[]{"red", "blue"}, new int[]{1, 1});

        assertEquals(List.of("red"), system.input('r'));
        assertEquals(List.of("red"), system.input('e'));
        assertEquals(List.of("red"), system.input('d'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(List.of("blue"), system.input('b'));
        assertEquals(List.of("blue"), system.input('l'));
        assertEquals(List.of("blue"), system.input('u'));
        assertEquals(List.of("blue"), system.input('e'));
        assertEquals(List.of(), system.input('#'));
        assertEquals(List.of("red"), system.input('r'));
    }

    @Test
    void independentOracleChecksMixedLearningAndFiltering() {
        runWithOracle(
                new String[]{"a", "ab", "abc", "abd", "b", "b c", "b d"},
                new int[]{3, 2, 1, 4, 5, 2, 2},
                "a#abc#abd#abe#b#b c#b x#");
    }

    @Test
    void independentOracleChecksDuplicateInitialEntriesAndTies() {
        runWithOracle(
                new String[]{"aa", "ab", "aa", "ac", "ad", "ae"},
                new int[]{1, 2, 3, 2, 2, 2},
                "a#ab#a#ac#ae#az#");
    }

    @Test
    void independentOracleChecksSpacesAndNoMatchTransitions() {
        runWithOracle(
                new String[]{"i love", "i love lamp", "i like", "i 2"},
                new int[]{5, 4, 4, 3},
                "i 2#i love#i love lamp#i x#");
    }

    @Test
    void independentOracleChecksAFullTopThreeWorkload() {
        String[] sentences = new String[40];
        int[] times = new int[40];
        for (int i = 0; i < sentences.length; i++) {
            sentences[i] = "prefix " + (char) ('a' + i % 10) + i;
            times[i] = (i * 17) % 23 + 1;
        }

        runWithOracle(sentences, times, "prefix a0#prefix a10#prefix z#prefix b#");
    }

    @Test
    void maximumLengthSentenceCanBeTypedAndLearned() {
        String longSentence = "a".repeat(100);
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{}, new int[]{});

        for (int i = 0; i < longSentence.length(); i++) {
            assertEquals(List.of(), system.input(longSentence.charAt(i)));
        }
        assertEquals(List.of(), system.input('#'));
        for (int i = 0; i < longSentence.length() - 1; i++) {
            assertEquals(List.of(longSentence), system.input(longSentence.charAt(i)));
        }
    }

    @Test
    void aHundredHistoricalSentencesStillUseTheIndependentRankingRule() {
        String[] sentences = new String[100];
        int[] times = new int[100];
        for (int i = 0; i < 100; i++) {
            sentences[i] = "q" + String.format("%02d", i);
            times[i] = (i % 11) + 1;
        }

        Map<String, Integer> frequencies = frequencies(sentences, times);
        AutocompleteSystem_642 system = new AutocompleteSystem_642(sentences, times);
        assertEquals(suggestions(frequencies, "q"), system.input('q'));
        assertEquals(suggestions(frequencies, "q9"), system.input('9'));
    }

    @Test
    void sameSentenceCanBeLearnedAcrossManySessions() {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(new String[]{"go"}, new int[]{1});

        for (int i = 0; i < 5; i++) {
            assertEquals(List.of("go"), system.input('g'));
            assertEquals(List.of("go"), system.input('o'));
            assertEquals(List.of(), system.input('#'));
        }
        assertEquals(List.of("go"), system.input('g'));
    }

    private static void runWithOracle(String[] initialSentences, int[] initialTimes, String typedCharacters) {
        AutocompleteSystem_642 system = new AutocompleteSystem_642(initialSentences, initialTimes);
        Map<String, Integer> frequencies = frequencies(initialSentences, initialTimes);
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < typedCharacters.length(); i++) {
            char c = typedCharacters.charAt(i);
            if (c == '#') {
                assertEquals(List.of(), system.input(c));
                frequencies.merge(current.toString(), 1, Integer::sum);
                current.setLength(0);
            } else {
                current.append(c);
                assertEquals(suggestions(frequencies, current.toString()), system.input(c));
            }
        }
    }

    private static Map<String, Integer> frequencies(String[] sentences, int[] times) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            result.merge(sentences[i], times[i], Integer::sum);
        }
        return result;
    }

    private static List<String> suggestions(Map<String, Integer> frequencies, String prefix) {
        Comparator<Map.Entry<String, Integer>> ranking = Comparator
                .<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey);
        return frequencies.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .sorted(ranking)
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
