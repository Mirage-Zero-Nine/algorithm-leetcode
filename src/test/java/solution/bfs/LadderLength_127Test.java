package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author BorisMirage
 * Time: 2022/10/07 00:05
 * Created with IntelliJ IDEA
 */

public class LadderLength_127Test {

    private final LadderLength_127 test = new LadderLength_127();

    @Test
    public void test() {
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
        wordList = Lists.newArrayList("a", "b", "c");
        assertEquals(2, test.ladderLength("a", "c", wordList));
    }

    @Test
    public void testImpossible() {
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "log");
        assertEquals(0, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testInvalidInput() {
        List<String> wordList = Lists.newArrayList();
        assertEquals(0, test.ladderLength("a", "c", wordList));
        wordList = Lists.newArrayList();
        assertEquals(0, test.ladderLength("hit", "cog", wordList));
        wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog", "hit");
        assertEquals(0, test.ladderLength("", "cog", wordList));
        wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog", "hit");
        assertEquals(0, test.ladderLength("aaa", "", wordList));
    }

    @Test
    public void testSingleLetterWords() {
        List<String> wordList = Lists.newArrayList("a", "b", "c");
        assertEquals(2, test.ladderLength("a", "c", wordList));
    }

    @Test
    public void testBeginEqualsEnd() {
        List<String> wordList = Lists.newArrayList("same", "lame", "lime");
        assertEquals(1, test.ladderLength("same", "same", wordList));
    }

    @Test
    public void testOneLetterChangeEach() {
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testCaseSensitiveFail() {
        List<String> wordList = Lists.newArrayList("Hot", "Dot", "Dog", "Lot", "Log", "Cog");
        assertEquals(0, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testDisconnectedGraph() {
        List<String> wordList = Lists.newArrayList("aaa", "aab", "abb", "bbb", "ccc");
        assertEquals(0, test.ladderLength("aaa", "ccc", wordList));
    }

    @Test
    public void testCycleAvoidance() {
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog", "hit");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testLongerChain() {
        List<String> wordList = Lists.newArrayList("hot", "hat", "bat", "bad");
        assertEquals(4, test.ladderLength("hot", "bad", wordList));
    }

    @Test
    public void testEndNotInWordList() {
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "log");
        assertEquals(0, test.ladderLength("hit", "xyz", wordList));
    }

    @Test
    public void testSingleStepTransform() {
        // "hot" -> "dot" is one character change, length = 2 (begin + end)
        List<String> wordList = Lists.newArrayList("dot");
        assertEquals(2, test.ladderLength("hot", "dot", wordList));
    }

    @Test
    public void testMultipleEquallyShortPaths() {
        // hit->hot->dot->dog->cog AND hit->hot->lot->log->cog both length 5
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog");
        assertEquals(5, test.ladderLength("hit", "cog", wordList));
    }

    @Test
    public void testBeginEqualsEndNotInWordList() {
        // begin == end but end not in wordList -> 0 (early return)
        List<String> wordList = Lists.newArrayList("abc", "def");
        assertEquals(0, test.ladderLength("xyz", "xyz", wordList));
    }

    @Test
    public void testNullWordList() {
        assertEquals(0, test.ladderLength("hit", "cog", null));
    }

    @Test
    public void testAllNeighborsFiltered() {
        // "a" has 25 single-char neighbors; only "z" is in list, path a->z = 2
        List<String> wordList = Lists.newArrayList("z");
        assertEquals(2, test.ladderLength("a", "z", wordList));
    }

    @Test
    public void testDisjointClustersNoPath() {
        // Two clusters: {aaa,aab,abb} and {zzz,zzy,zyy} — no bridge
        List<String> wordList = Lists.newArrayList("aab", "abb", "bbb", "zzz", "zzy", "zyy");
        assertEquals(0, test.ladderLength("aaa", "zyy", wordList));
    }

    @Test
    public void testLargeWordList() {
        // Generate 100+ 4-letter words from seed 42L, ensure result is reasonable
        Random random = new Random(42L);
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }
            wordList.add(sb.toString());
        }
        String begin = "aaaa";
        String end = wordList.get(10);
        wordList.add(end); // ensure end is in list
        int result = test.ladderLength(begin, end, wordList);
        // Result is either 0 (no path) or >= 2 (at least begin + end)
        assertTrue(result == 0 || result >= 2, "Result should be 0 or at least 2, got: " + result);
    }

    @Test
    public void testBeginEqualsEndInWordList() {
        // begin == end and end is in wordList -> 1 (found immediately in BFS)
        List<String> wordList = Lists.newArrayList("hot", "dot", "dog");
        assertEquals(1, test.ladderLength("hot", "hot", wordList));
    }
}