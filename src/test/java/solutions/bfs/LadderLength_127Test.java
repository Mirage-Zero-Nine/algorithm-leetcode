package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * @author BorisMirage
 * Time: 2022/10/07 00:05
 * Created with IntelliJ IDEA
 */

public class LadderLength_127Test {

    private final LadderLength_127 test = new LadderLength_127();

    @Test
    public void testLeetCodeExample() {
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testEndWordNotInWordList() {
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log");
        assertEquals(0, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testSingleLetterWords() {
        List<String> wordList = List.of("a", "b", "c");
        assertEquals(2, test.ladderLength("a", "c", wordList));
    }

    @Test
    public void testDirectTransformation() {
        List<String> wordList = List.of("dot");
        assertEquals(2, test.ladderLength("hot", "dot", wordList));
    }

    @Test
    public void testBeginWordDoesNotNeedToBeInWordList() {
        List<String> wordList = List.of("hot", "dot", "dog", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testMissingEndWordEvenWhenItIsOneChangeAway() {
        // "hut" is one change from reachable word "hot", but it is not in the dictionary.
        List<String> wordList = List.of("hot");
        assertEquals(0, test.ladderLength("hit", "hut", wordList));
    }

    @Test
    public void testDisconnectedGraph() {
        List<String> wordList = List.of("aab", "abb", "bbb", "ccc");
        assertEquals(0, test.ladderLength("aaa", "ccc", wordList));
    }

    @Test
    public void testBeginWordMayAlsoAppearInWordList() {
        List<String> wordList = List.of("hit", "hot", "dot", "dog", "lot", "log", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testCycleAvoidance() {
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testLongerChain() {
        List<String> wordList = List.of("hot", "hat", "bat", "bad");
        assertEquals(4, test.ladderLength("hot", "bad", wordList));
    }

    @Test
    public void testShortestPathIsSelected() {
        List<String> wordList = List.of("hig", "hog", "hot", "dot", "dog", "cog");
        // hit -> hig -> hog -> cog is shorter than hit -> hot -> dot -> dog -> cog.
        assertEquals(4, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testMultipleEquallyShortPaths() {
        // hit->hot->dot->dog->cog AND hit->hot->lot->log->cog both length 5
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testEveryCharacterCanBeChanged() {
        List<String> wordList = List.of("baaa", "bbaa", "bbba", "bbbb");
        assertEquals(5, test.ladderLength("aaaa", "bbbb", wordList));
    }

    @Test
    public void testAllNeighborsAreFilteredByDictionary() {
        List<String> wordList = List.of("z");
        assertEquals(2, test.ladderLength("a", "z", wordList));
    }

    @Test
    public void testTargetInDisconnectedComponent() {
        List<String> wordList = List.of("aab", "abb", "bbb", "ccc", "ccd", "cdd");
        assertEquals(0, test.ladderLength("aaa", "ccc", wordList));
    }

    @Test
    public void testIrrelevantWordsDoNotChangeResult() {
        List<String> wordList = List.of(
                "hot", "dot", "dog", "cog", "aaa", "bbb", "ccc", "ddd");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testTenCharacterWords() {
        List<String> wordList = List.of("abcdefghik");
        assertEquals(2, test.ladderLength("abcdefghij", "abcdefghik", wordList));
    }

    @Test
    public void testMaximumWordListSize() {
        // 4,999 unique distractors plus the target, matching the 5,000-word limit.
        List<String> wordList = new ArrayList<>();
        for (int i = 1; i <= 4_999; i++) {
            wordList.add(wordFromNumber(i, 10));
        }
        wordList.add("baaaaaaaaa");

        assertEquals(2, test.ladderLength("aaaaaaaaaa", "baaaaaaaaa", wordList));
    }

    @Test
    public void testNullInputs() {
        List<String> wordList = List.of("cog");
        assertEquals(0, test.ladderLength(null, "cog", wordList));
        assertEquals(0, test.ladderLength("hit", null, wordList));
        assertEquals(0, test.ladderLength("hit", "cog", null));
    }

    @Test
    public void testEmptyWordList() {
        assertEquals(0, test.ladderLength("hit", "cog", List.of()));
    }

    @Test
    public void testEmptyWords() {
        List<String> wordList = List.of("a");
        assertEquals(0, test.ladderLength("", "a", wordList));
        assertEquals(0, test.ladderLength("a", "", wordList));
    }

    @Test
    public void testEndWordAloneCannotBridgeTheGap() {
        // The target is present, but no valid intermediate word is available.
        assertEquals(0, test.ladderLength("hit", "cog", List.of("cog")));
    }

    @Test
    public void testChangesCanBeMadeInAnyPositionOrder() {
        List<String> wordList = List.of("bbc", "bba", "cba");
        // abc -> bbc -> bba -> cba
        assertEquals(4, test.ladderLength("abc", "cba", wordList));
    }

    @Test
    public void testSharedIntermediateIsVisitedOnlyOnce() {
        List<String> wordList = List.of("baa", "aba", "bba", "bbb");
        // Both baa and aba lead to bba; bba must remain at the correct level.
        assertEquals(4, test.ladderLength("aaa", "bbb", wordList));
    }

    @Test
    public void testWordListOrderDoesNotAffectResult() {
        List<String> wordList = List.of("cog", "log", "lot", "dog", "dot", "hot");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testDuplicateWordListEntriesAreHarmless() {
        List<String> wordList = new ArrayList<>(List.of("hot", "hot", "dot", "dog", "cog"));
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testInputWordListIsNotModified() {
        List<String> wordList = new ArrayList<>(List.of("hot", "dot", "dog", "cog"));
        List<String> original = new ArrayList<>(wordList);

        test.ladderLength("hit", "cog", wordList);

        assertEquals(original, wordList);
    }

    @Test
    public void testBeginEqualsEndUsesDocumentedImplementationGuard() {
        // LeetCode excludes this input (beginWord != endWord).  The class-level
        // contract also says the words are different, and this implementation
        // rejects equality because it removes beginWord before checking endWord.
        assertEquals(0, test.ladderLength("same", "same", List.of("same")));
    }

    @Test
    public void testLengthMismatchIsRejectedBeforeSearching() {
        assertEquals(0, test.ladderLength("a", "bb", List.of("bb")));
        assertEquals(0, test.ladderLength("abc", "ab", List.of("ab", "abc")));
    }

    @Test
    public void testNullDictionaryEntriesAreIgnored() {
        List<String> wordList = new ArrayList<>();
        wordList.add(null);
        wordList.add("hot");
        wordList.add(null);
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("cog");

        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testTenCharacterWordsCanRequireEveryPosition() {
        String begin = "aaaaaaaaaa";
        String end = "bbbbbbbbbb";
        List<String> wordList = new ArrayList<>();
        StringBuilder current = new StringBuilder(begin);
        for (int position = 0; position < begin.length(); position++) {
            current.setCharAt(position, 'b');
            wordList.add(current.toString());
        }

        assertEquals(11, test.ladderLength(begin, end, wordList));
    }

    @Test
    public void testRepeatedCallsDoNotShareVisitedDictionaryState() {
        List<String> first = new ArrayList<>(List.of("hot", "dot", "dog", "cog"));
        List<String> second = new ArrayList<>(List.of("hot", "cot"));

        assertEquals(5, test.ladderLength("hit", "cog", first));
        assertEquals(3, test.ladderLength("hit", "cot", second));
        assertEquals(List.of("hot", "dot", "dog", "cog"), first);
        assertEquals(List.of("hot", "cot"), second);
    }

    @Test
    public void testMaximumLengthDictionaryCanBeUnreachable() {
        // Keep every distractor at least two positions away from beginWord so
        // this exercises a full unsuccessful search at the official 5,000-word
        // dictionary boundary without relying on an invalid word.
        List<String> wordList = new ArrayList<>(5_000);
        for (int number = 0; number < 4_999; number++) {
            String suffix = wordFromNumber(number, 8);
            wordList.add("bb" + suffix);
        }
        wordList.add("zzzzzzzzzz");

        assertEquals(0, test.ladderLength("aaaaaaaaaa", "zzzzzzzzzz", wordList));
    }

    @Test
    public void testExhaustiveTwoLetterDictionariesMatchIndependentOracle() {
        List<String> universe = allWords("abc", 2);
        String begin = "aa";
        String end = "cc";
        List<String> optional = new ArrayList<>(universe);
        optional.remove(end);

        // Every subset containing end is a valid small dictionary.  Scanning
        // all 256 graphs catches level-counting, visited-state, and shortest
        // path errors without sharing the production mutation-generation code.
        for (int mask = 0; mask < (1 << optional.size()); mask++) {
            List<String> dictionary = new ArrayList<>();
            dictionary.add(end);
            for (int bit = 0; bit < optional.size(); bit++) {
                if ((mask & (1 << bit)) != 0) {
                    dictionary.add(optional.get(bit));
                }
            }
            assertEquals(oracleDistance(begin, end, dictionary),
                    test.ladderLength(begin, end, new ArrayList<>(dictionary)),
                    "dictionary mask " + mask);
        }
    }

    @Test
    public void testSeededRandomDictionariesMatchIndependentOracle() {
        List<String> universe = allWords("abc", 3);
        Random random = new Random(127_2026L);

        for (int trial = 0; trial < 200; trial++) {
            String begin = universe.get(random.nextInt(universe.size()));
            String end = universe.get(random.nextInt(universe.size()));
            while (begin.equals(end)) {
                end = universe.get(random.nextInt(universe.size()));
            }

            List<String> dictionary = new ArrayList<>();
            for (String word : universe) {
                if (word.equals(end) || random.nextBoolean()) {
                    dictionary.add(word);
                }
            }
            int expected = oracleDistance(begin, end, dictionary);
            assertEquals(expected, test.ladderLength(begin, end, new ArrayList<>(dictionary)),
                    "random trial " + trial + " begin=" + begin + " end=" + end);
        }
    }

    private int oracleDistance(String beginWord, String endWord, List<String> wordList) {
        Set<String> allowed = new HashSet<>();
        for (String word : wordList) {
            if (word != null && !word.equals(beginWord)) {
                allowed.add(word);
            }
        }
        if (!allowed.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        Map<String, Integer> distance = new HashMap<>();
        queue.offer(beginWord);
        distance.put(beginWord, 1);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDistance = distance.get(current);
            for (String candidate : allowed) {
                if (!distance.containsKey(candidate) && differsByOne(current, candidate)) {
                    if (candidate.equals(endWord)) {
                        return currentDistance + 1;
                    }
                    distance.put(candidate, currentDistance + 1);
                    queue.offer(candidate);
                }
            }
        }
        return 0;
    }

    private boolean differsByOne(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        int differences = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i) && ++differences > 1) {
                return false;
            }
        }
        return differences == 1;
    }

    private List<String> allWords(String alphabet, int length) {
        List<String> words = new ArrayList<>();
        collectWords(alphabet, length, new StringBuilder(), words);
        return words;
    }

    private void collectWords(String alphabet, int remaining, StringBuilder current, List<String> words) {
        if (remaining == 0) {
            words.add(current.toString());
            return;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            current.append(alphabet.charAt(i));
            collectWords(alphabet, remaining - 1, current, words);
            current.deleteCharAt(current.length() - 1);
        }
    }

    private String wordFromNumber(int number, int length) {
        char[] word = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            word[i] = (char) ('a' + number % 26);
            number /= 26;
        }
        return new String(word);
    }
}
