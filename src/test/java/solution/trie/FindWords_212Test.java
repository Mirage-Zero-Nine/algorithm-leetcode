package solution.trie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class FindWords_212Test {

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

    @Test
    public void testSingleCellNoMatch() {
        assertEquals(List.of(), test.findWords(new char[][]{{'z'}}, new String[]{"a", "b"}));
    }

    @Test
    public void testAllWordsFound() {
        char[][] board = {
            {'a', 'b'},
            {'c', 'd'}
        };
        assertEquals(
            Set.of("ab", "ac", "bd", "cd"),
            new HashSet<>(test.findWords(board, new String[]{"ab", "ac", "bd", "cd"}))
        );
    }

    @Test
    public void testEmptyWordsList() {
        assertEquals(List.of(), test.findWords(new char[][]{{'a', 'b'}, {'c', 'd'}}, new String[]{}));
    }

    @Test
    public void testWordLongerThanBoard() {
        assertEquals(List.of(), test.findWords(new char[][]{{'a', 'b'}, {'c', 'd'}}, new String[]{"abcde"}));
    }

    @Test
    public void testDuplicateLettersInBoard() {
        char[][] board = {
            {'a', 'a'},
            {'a', 'a'}
        };
        // "aaaa" can be formed by visiting all 4 cells
        assertEquals(Set.of("a", "aa", "aaa", "aaaa"),
            new HashSet<>(test.findWords(board, new String[]{"a", "aa", "aaa", "aaaa", "aaaaa"})));
    }

    @Test
    public void testGiantBoard() {
        // 10x10 board filled with 'a', search for words of various lengths
        char[][] board = new char[10][10];
        for (char[] row : board) {
            java.util.Arrays.fill(row, 'a');
        }
        List<String> result = test.findWords(board, new String[]{"a", "aa", "aaa"});
        assertTrue(result.contains("a"));
        assertTrue(result.contains("aa"));
        assertTrue(result.contains("aaa"));
    }
}
