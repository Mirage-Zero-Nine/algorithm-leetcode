package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author BorisMirage
 * Time: 2023/04/21 10:43
 * Created with IntelliJ IDEA
 */

public class AutocompleteSystem_642Test {
    private AutocompleteSystem_642 test;

    @Test
    public void testAutocomplete() {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        test = new AutocompleteSystem_642(sentences, times);

        assertEquals(Arrays.asList("i love you", "island", "i love leetcode"), test.input('i'));
        assertEquals(Arrays.asList("i love you", "i love leetcode"), test.input(' '));
        assertEquals(Collections.emptyList(), test.input('a'));
        assertEquals(Collections.emptyList(), test.input('#'));
        assertEquals(Arrays.asList("i love you", "island", "i love leetcode"), test.input('i'));
        assertEquals(Arrays.asList("i love you", "i love leetcode", "i a"), test.input(' '));
        assertEquals(Collections.singletonList("i a"), test.input('a'));
        assertEquals(Collections.emptyList(), test.input('#'));
        assertEquals(Arrays.asList("i love you", "island", "i a"), test.input('i'));
        assertEquals(Arrays.asList("i love you", "i a", "i love leetcode"), test.input(' '));
        assertEquals(Collections.singletonList("i a"), test.input('a'));
        assertEquals(Collections.emptyList(), test.input('#'));
    }

    @Test
    public void testEmpty() {
        test = new AutocompleteSystem_642(new String[]{}, new int[]{});
        assertEquals(Collections.emptyList(), test.input(' '));
    }

    @Test
    public void testInvalid() {
        test = new AutocompleteSystem_642(new String[]{"ss"}, new int[]{1});
        assertEquals(Collections.emptyList(), test.input(' '));
    }

    @Test
    public void testTieBreakByAsciiOrder() {
        test = new AutocompleteSystem_642(new String[]{"ab", "ac", "aa"}, new int[]{1, 1, 1});
        assertEquals(Arrays.asList("aa", "ab", "ac"), test.input('a'));
    }

    @Test
    public void testPrefixNoMatchThenResetAfterHash() {
        test = new AutocompleteSystem_642(new String[]{"hello"}, new int[]{2});
        assertEquals(Collections.emptyList(), test.input('x'));
        assertEquals(Collections.emptyList(), test.input('y'));
        assertEquals(Collections.emptyList(), test.input('#'));
        assertEquals(Collections.singletonList("hello"), test.input('h'));
    }

    @Test
    public void testInsertedSentenceAppearsAfterHash() {
        test = new AutocompleteSystem_642(new String[]{"abc"}, new int[]{1});
        assertEquals(Collections.singletonList("abc"), test.input('a'));
        assertEquals(Collections.emptyList(), test.input('d'));
        assertEquals(Collections.emptyList(), test.input('#'));
        assertEquals(Arrays.asList("abc", "ad"), test.input('a'));
    }

    @Test
    public void testFrequencyIncreaseChangesRanking() {
        test = new AutocompleteSystem_642(new String[]{"a x", "a y"}, new int[]{1, 1});
        assertEquals(Arrays.asList("a x", "a y"), test.input('a'));
        assertEquals(Arrays.asList("a x", "a y"), test.input(' '));
        assertEquals(Collections.singletonList("a y"), test.input('y'));
        assertEquals(Collections.emptyList(), test.input('#'));
        assertEquals(Arrays.asList("a y", "a x"), test.input('a'));
    }

    @Test
    public void testSpaceAsPrefixCharacter() {
        test = new AutocompleteSystem_642(new String[]{"i am", "i an", "i as"}, new int[]{2, 2, 1});
        assertEquals(Arrays.asList("i am", "i an", "i as"), test.input('i'));
        assertEquals(Arrays.asList("i am", "i an", "i as"), test.input(' '));
    }

    @Test
    public void testTopThreeOnly() {
        test = new AutocompleteSystem_642(
            new String[]{"m1", "m2", "m3", "m4"},
            new int[]{10, 9, 8, 7});
        assertEquals(Arrays.asList("m1", "m2", "m3"), test.input('m'));
    }

    @Test
    public void testGiantInsertions() {
        String[] sentences = new String[200];
        int[] times = new int[200];
        for (int i = 0; i < 200; i++) {
            sentences[i] = "k" + i;
            times[i] = i + 1;
        }
        test = new AutocompleteSystem_642(sentences, times);
        assertEquals(Arrays.asList("k199", "k198", "k197"), test.input('k'));
    }
}
