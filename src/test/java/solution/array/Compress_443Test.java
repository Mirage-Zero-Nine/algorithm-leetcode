package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link Compress_443}.
 */
public class Compress_443Test {

    private final Compress_443 solver = new Compress_443();

    @Test
    public void testSingleChar() {
        char[] chars = {'a'};
        assertEquals(1, solver.compress(chars));
        assertEquals("a", new String(chars, 0, 1));
    }

    @Test
    public void testTwoSameChars() {
        char[] chars = {'a', 'a'};
        assertEquals(2, solver.compress(chars));
        assertEquals("a2", new String(chars, 0, 2));
    }

    @Test
    public void testTwoDiffChars() {
        char[] chars = {'a', 'b'};
        assertEquals(2, solver.compress(chars));
        assertEquals("ab", new String(chars, 0, 2));
    }

    @Test
    public void testClassicExample() {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        assertEquals(6, solver.compress(chars));
        assertEquals("a2b2c3", new String(chars, 0, 6));
    }

    @Test
    public void testNoCompressionNeeded() {
        char[] chars = {'a', 'b', 'c'};
        assertEquals(3, solver.compress(chars));
        assertEquals("abc", new String(chars, 0, 3));
    }

    @Test
    public void testAllSameChars() {
        char[] chars = {'a', 'a', 'a', 'a', 'a'};
        assertEquals(2, solver.compress(chars));
        assertEquals("a5", new String(chars, 0, 2));
    }

    @Test
    public void testMultiDigitCount() {
        char[] chars = new char[12];
        java.util.Arrays.fill(chars, 'a');
        assertEquals(3, solver.compress(chars));
        assertEquals("a12", new String(chars, 0, 3));
    }

    @Test
    public void testEmptyArray() {
        char[] chars = {};
        assertEquals(0, solver.compress(chars));
    }

    @Test
    public void testAlternatingChars() {
        char[] chars = {'a', 'b', 'a', 'b', 'a'};
        assertEquals(5, solver.compress(chars));
        assertEquals("ababa", new String(chars, 0, 5));
    }

    @Test
    public void testLongRunThenSingle() {
        char[] chars = {'a', 'a', 'a', 'b'};
        assertEquals(3, solver.compress(chars));
        assertEquals("a3b", new String(chars, 0, 3));
    }

    @Test
    public void testSingleRunThenLong() {
        char[] chars = new char[15];
        chars[0] = 'a';
        java.util.Arrays.fill(chars, 1, chars.length, 'b');
        assertEquals(4, solver.compress(chars));
        assertEquals("ab14", new String(chars, 0, 4));
    }

    @Test
    public void testComplexPattern() {
        char[] chars = {'a', 'a', 'a', 'b', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c'};
        assertEquals(6, solver.compress(chars));
        assertEquals("a3b4c5", new String(chars, 0, 6));
    }
}
