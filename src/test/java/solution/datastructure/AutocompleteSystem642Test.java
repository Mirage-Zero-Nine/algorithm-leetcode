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

public class AutocompleteSystem642Test {
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
}