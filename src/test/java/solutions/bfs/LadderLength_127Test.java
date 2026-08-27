package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    private String wordFromNumber(int number, int length) {
        char[] word = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            word[i] = (char) ('a' + number % 26);
            number /= 26;
        }
        return new String(word);
    }
}
