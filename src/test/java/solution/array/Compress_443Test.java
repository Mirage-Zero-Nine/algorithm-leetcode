package solution.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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

    @Test
    public void testRunOfExactly10() {
        char[] chars = new char[10];
        Arrays.fill(chars, 'z');
        assertEquals(3, solver.compress(chars));
        assertEquals("z10", new String(chars, 0, 3));
    }

    @Test
    public void testRunOf100() {
        char[] chars = new char[100];
        Arrays.fill(chars, 'x');
        assertEquals(4, solver.compress(chars));
        assertEquals("x100", new String(chars, 0, 4));
    }

    @Test
    public void testLargeAlternating() {
        char[] chars = new char[1000];
        for (int i = 0; i < 1000; i++) {
            chars[i] = (char) ('a' + (i % 3));
        }
        char[] original = chars.clone();
        int len = solver.compress(chars);
        // alternating pattern of 3 chars: no compression possible
        assertTrue(len <= original.length);
        // decompress and verify
        assertEquals(new String(original), decompress(chars, len));
    }

    @Test
    public void testLargeAllSame() {
        char[] chars = new char[1000];
        Arrays.fill(chars, 'q');
        int len = solver.compress(chars);
        assertEquals(5, len); // 'q','1','0','0','0'
        assertEquals("q1000", new String(chars, 0, len));
    }

    @Test
    public void testLargeMixedPattern() {
        // 500 'a' + 300 'b' + 200 'c'
        char[] chars = new char[1000];
        Arrays.fill(chars, 0, 500, 'a');
        Arrays.fill(chars, 500, 800, 'b');
        Arrays.fill(chars, 800, 1000, 'c');
        char[] original = chars.clone();
        int len = solver.compress(chars);
        assertTrue(len <= original.length);
        assertEquals(new String(original), decompress(chars, len));
    }

    @Test
    public void testPropertyReturnedLengthNeverExceedsOriginal() {
        // Various sizes: compression never makes it longer
        for (int size = 1; size <= 50; size++) {
            char[] chars = new char[size];
            for (int i = 0; i < size; i++) {
                chars[i] = (char) ('a' + (i % 26));
            }
            int len = solver.compress(chars);
            assertTrue(len <= size, "Failed for size " + size);
        }
    }

    @Test
    public void testPropertyDecompressionReconstructsOriginal() {
        // Runs of varying lengths
        char[] chars = new char[50];
        Arrays.fill(chars, 0, 5, 'a');
        Arrays.fill(chars, 5, 20, 'b');
        Arrays.fill(chars, 20, 21, 'c');
        Arrays.fill(chars, 21, 50, 'd');
        char[] original = chars.clone();
        int len = solver.compress(chars);
        assertEquals(new String(original), decompress(chars, len));
    }

    /** Helper: decompress chars[0..len) back to original string. */
    private String decompress(char[] chars, int len) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < len) {
            char c = chars[i++];
            StringBuilder numBuf = new StringBuilder();
            while (i < len && Character.isDigit(chars[i])) {
                numBuf.append(chars[i++]);
            }
            int count = numBuf.length() == 0 ? 1 : Integer.parseInt(numBuf.toString());
            sb.append(String.valueOf(c).repeat(count));
        }
        return sb.toString();
    }
}
