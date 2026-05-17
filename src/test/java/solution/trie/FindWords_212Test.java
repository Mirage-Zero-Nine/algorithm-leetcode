package solution.trie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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

    @Test
    public void testEmptyBoard() {
        assertEquals(List.of(), test.findWords(new char[][]{}, new String[]{"abc"}));
    }

    @Test
    public void testSingleCellSingleCharWord() {
        assertEquals(Set.of("x"), new HashSet<>(test.findWords(new char[][]{{'x'}}, new String[]{"x"})));
    }

    @Test
    public void testWordSpansFullRow() {
        // Board: single row "hello"
        char[][] board = {{'h', 'e', 'l', 'l', 'o'}};
        assertEquals(Set.of("hello"), new HashSet<>(test.findWords(board, new String[]{"hello", "world"})));
    }

    @Test
    public void testWordSpansFullColumn() {
        // Board: single column "world"
        char[][] board = {{'w'}, {'o'}, {'r'}, {'l'}, {'d'}};
        assertEquals(Set.of("world"), new HashSet<>(test.findWords(board, new String[]{"world", "hello"})));
    }

    @Test
    public void testCellReuseForbidden() {
        // "aba" requires reusing cell (0,0) on a board [a,b] — should NOT match
        char[][] board = {{'a', 'b'}};
        assertEquals(List.of(), test.findWords(board, new String[]{"aba"}));
    }

    @Test
    public void testMultipleWordsSharingPrefix() {
        // Words sharing prefix "ab": "ab", "abc", "abd", "abce"
        char[][] board = {
            {'a', 'b', 'c'},
            {'x', 'd', 'e'}
        };
        Set<String> result = new HashSet<>(test.findWords(board, new String[]{"ab", "abc", "abd", "abce", "abcx"}));
        assertTrue(result.contains("ab"));
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("abd"));
        assertTrue(result.contains("abce"));
        // "abcx" not possible — x is not adjacent to c
        assertTrue(!result.contains("abcx"));
    }

    @Test
    public void testDiagonalNotAllowed() {
        // 'a' at (0,0), 'b' at (1,1) — diagonal, not adjacent
        char[][] board = {
            {'a', 'x'},
            {'x', 'b'}
        };
        // "ab" requires diagonal move — should NOT be found
        assertEquals(List.of(), test.findWords(board, new String[]{"ab"}));
    }

    @Test
    public void testBacktrackingRequired() {
        // Path "abcde" exists but "abcdf" does not — requires backtracking
        char[][] board = {
            {'a', 'b', 'c'},
            {'f', 'e', 'd'}
        };
        Set<String> result = new HashSet<>(test.findWords(board, new String[]{"abcdef", "abcde", "abcdf"}));
        assertEquals(Set.of("abcdef", "abcde"), result);
    }

    @Test
    public void testLargeBoard10x10With50Words() {
        Random rng = new Random(42L);
        char[][] board = new char[10][10];
        for (char[] row : board) {
            for (int i = 0; i < row.length; i++) {
                row[i] = (char) ('a' + rng.nextInt(5)); // letters a-e for higher match chance
            }
        }

        // Generate 50 words: some constructible from board paths, some random
        String[] words = new String[50];
        for (int i = 0; i < 25; i++) {
            // Build word by random walk on board
            StringBuilder sb = new StringBuilder();
            int r = rng.nextInt(10), c = rng.nextInt(10);
            boolean[][] visited = new boolean[10][10];
            int len = 2 + rng.nextInt(4);
            for (int step = 0; step < len; step++) {
                if (r < 0 || r >= 10 || c < 0 || c >= 10 || visited[r][c]) break;
                visited[r][c] = true;
                sb.append(board[r][c]);
                int dir = rng.nextInt(4);
                int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                r += dirs[dir][0];
                c += dirs[dir][1];
            }
            words[i] = sb.toString();
        }
        for (int i = 25; i < 50; i++) {
            // Random words unlikely to be on board
            StringBuilder sb = new StringBuilder();
            int len = 3 + rng.nextInt(5);
            for (int j = 0; j < len; j++) {
                sb.append((char) ('f' + rng.nextInt(20))); // letters f-y, mostly not on board
            }
            words[i] = sb.toString();
        }

        List<String> result = test.findWords(board, words);
        Set<String> resultSet = new HashSet<>(result);
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        // Property: result is subset of input word list
        assertTrue(wordSet.containsAll(resultSet), "Result must be subset of input words");
        // Property: no duplicates in result
        assertEquals(result.size(), resultSet.size(), "Result should have no duplicates");
    }
}
