package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Unit tests for {@link ShortestToChar_821}.
 */
public class ShortestToChar_821Test {

    private final ShortestToChar_821 solver = new ShortestToChar_821();

    @Test
    public void testLeetCodeExample() {
        // s = "loveleetcode", c = 'e'
        // e at indices 3,5,6,11
        // l(0)->3, o(1)->2, v(2)->1, e(3)->0, l(4)->1, e(5)->0, e(6)->0,
        // t(7)->1, c(8)->2, o(9)->2, d(10)->1, e(11)->0
        String s = "loveleetcode";
        char c = 'e';
        int[] expected = {3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }

    @Test
    public void testSingleCharString() {
        String s = "a";
        char c = 'a';
        int[] expected = {0};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }

    @Test
    public void testCharAtStart() {
        // s = "aba", c = 'a'
        // distances: [0,1,0]
        String s = "aba";
        char c = 'a';
        int[] expected = {0, 1, 0};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }

    @Test
    public void testCharAtEnd() {
        // s = "aba", c = 'b'
        // distances: [1,0,1]
        String s = "aba";
        char c = 'b';
        int[] expected = {1, 0, 1};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }

    @Test
    public void testMultipleOccurrences() {
        // s = "aaab", c = 'a'
        // distances: [0,0,0,1]
        String s = "aaab";
        char c = 'a';
        int[] expected = {0, 0, 0, 1};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }

    @Test
    public void testCharInMiddle() {
        // s = "abc", c = 'b'
        // distances: [1,0,1]
        String s = "abc";
        char c = 'b';
        int[] expected = {1, 0, 1};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }

    @Test
    public void testAllSameChar() {
        // s = "aaaa", c = 'a'
        // distances: [0,0,0,0]
        String s = "aaaa";
        char c = 'a';
        int[] expected = {0, 0, 0, 0};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }

    @Test
    public void testTwoCharString() {
        // s = "ab", c = 'b'
        // distances: [1,0]
        String s = "ab";
        char c = 'b';
        int[] expected = {1, 0};
        assertArrayEquals(expected, solver.shortestToChar(s, c));
    }
}
