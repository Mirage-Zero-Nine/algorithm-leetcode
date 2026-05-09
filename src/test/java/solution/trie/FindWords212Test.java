package solution.trie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class FindWords212Test {

    private final FindWords_212 test = new FindWords_212();

    @Test
    public void testHappyCases() {
        assertEquals(
            Set.of("eat", "oath"),
            new HashSet<>(test.findWords(
                new char[][]{
                    {'o', 'a', 'a', 'n'},
                    {'e', 't', 'a', 'e'},
                    {'i', 'h', 'k', 'r'},
                    {'i', 'f', 'l', 'v'}
                },
                new String[]{"oath", "pea", "eat", "rain"}
            ))
        );
    }

    @Test
    public void testNegativeCases() {
        assertEquals(List.of(), test.findWords(new char[][]{{'a', 'b'}, {'c', 'd'}}, new String[]{"abcb"}));
    }

    @Test
    public void testInvalidCases() {
        assertThrows(NullPointerException.class, () -> test.findWords(null, new String[]{"a"}));
        assertThrows(NullPointerException.class, () -> test.findWords(new char[][]{{'a'}}, null));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(Set.of("a"), new HashSet<>(test.findWords(new char[][]{{'a'}}, new String[]{"a", "aa"})));
    }

    @Test
    public void testLargeCase() {
        char[][] board = {
            {'a', 'b', 'c', 'e'},
            {'s', 'f', 'c', 's'},
            {'a', 'd', 'e', 'e'},
            {'x', 'y', 'z', 'w'}
        };
        assertEquals(
            Set.of("abcced", "see", "sfcs", "adee", "xyz"),
            new HashSet<>(test.findWords(board, new String[]{"abcced", "see", "abcb", "sfcs", "adee", "xyz"}))
        );
    }
}
