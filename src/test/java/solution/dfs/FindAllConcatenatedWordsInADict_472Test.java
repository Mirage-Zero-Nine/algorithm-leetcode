package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/31 00:29
 * Created with IntelliJ IDEA
 */

public class FindAllConcatenatedWordsInADict_472Test {

    private final FindAllConcatenatedWordsInADict_472 test = new FindAllConcatenatedWordsInADict_472();

    @Test
    public void test() {
        String[] words = {"cat", "dog", "catdog"};
        assertIterableEquals(Lists.newArrayList("catdog"), test.findAllConcatenatedWordsInADict(words));
    }

    @Test
    public void testClassicHappyCase() {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        assertIterableEquals(
            List.of("dogcatsdog", "catsdogcats", "ratcatdogcat"),
            test.findAllConcatenatedWordsInADict(words)
        );
    }

    @Test
    public void testNoConcatenatedWords() {
        String[] words = {"apple", "orange", "pear"};
        assertIterableEquals(List.of(), test.findAllConcatenatedWordsInADict(words));
    }

    @Test
    public void testTwoPieceConcatenation() {
        String[] words = {"a", "b", "ab", "ba"};
        assertIterableEquals(List.of("ab", "ba"), test.findAllConcatenatedWordsInADict(words));
    }

    @Test
    public void testThreePieceConcatenation() {
        String[] words = {"a", "aa", "aaa"};
        assertIterableEquals(List.of("aa", "aaa"), test.findAllConcatenatedWordsInADict(words));
    }

    @Test
    public void testEdgeCaseSingleWord() {
        String[] words = {"alone"};
        assertIterableEquals(List.of(), test.findAllConcatenatedWordsInADict(words));
    }

    @Test
    public void testEdgeCaseWithEmptyString() {
        String[] words = {"", "a", "aa"};
        assertIterableEquals(List.of("aa"), test.findAllConcatenatedWordsInADict(words));
    }

    @Test
    public void testDuplicateWordsInput() {
        String[] words = {"cat", "dog", "catdog", "catdog"};
        List<String> out = test.findAllConcatenatedWordsInADict(words);
        assertTrue(out.contains("catdog"));
        assertTrue(out.size() >= 1);
    }

    @Test
    public void testNegativeCaseWithUnrelatedLongWord() {
        String[] words = {"ab", "abc", "abcd", "zzz"};
        assertIterableEquals(List.of(), test.findAllConcatenatedWordsInADict(words));
    }

    @Test
    public void testGiantCase() {
        String[] words = {
            "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa",
            "aaaaaaaaaaa", "aaaaaaaaaaaa", "aaaaaaaaaaaaa", "aaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa",
            "b", "bb", "bbb", "bbbb", "bbbbb", "bbbbbb"
        };
        List<String> out = test.findAllConcatenatedWordsInADict(words);
        assertTrue(out.contains("aaaaaaaaaa"));
        assertTrue(out.contains("aaaaaaaaaaaaaaa"));
        assertTrue(out.contains("bbbbbb"));
    }
}
