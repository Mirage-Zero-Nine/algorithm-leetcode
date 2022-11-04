package solution.trie;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author BorisMirage
 * Time: 2022/11/04 11:20
 * Created with IntelliJ IDEA
 */

public class FindWords212Test {

    private final FindWords_212 test = new FindWords_212();

    @Test
    public void test() {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> expected = Lists.newArrayList("eat", "oath");

        assertTrue(expected.containsAll(test.findWords(board, words)));
    }

    @Test
    public void testEmptyOutput() {
        char[][] board = {{'a', 'b'}, {'c', 'd'}};
        String[] words = {"abcb"};

        List<String> expected = Lists.newArrayList();
        assertEquals(expected, test.findWords(board, words));
    }
}