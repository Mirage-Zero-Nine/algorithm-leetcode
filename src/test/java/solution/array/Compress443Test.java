package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Compress443Test {

    private final Compress_443 solution = new Compress_443();

    @Test
    void testCompress_normalCase() {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int expected = 6; // After compression: ['a', '2', 'b', '2', 'c', '3']
        int actual = solution.compress(chars);
        assertEquals(expected, actual);
    }

    @Test
    void testCompress_singleChar() {
        char[] chars = {'a'};
        int expected = 1; // After compression: ['a']
        int actual = solution.compress(chars);
        assertEquals(expected, actual);
    }

    @Test
    void testCompress_emptyArray() {
        char[] chars = {};
        int expected = 0; // No characters to compress
        int actual = solution.compress(chars);
        assertEquals(expected, actual);
    }

    @Test
    void testCompress_allUniqueChars() {
        char[] chars = {'a', 'b', 'c', 'd'};
        int expected = 4; // After compression: ['a', 'b', 'c', 'd']
        int actual = solution.compress(chars);
        assertEquals(expected, actual);
    }

    @Test
    void testCompress_allSameChars() {
        char[] chars = {'a', 'a', 'a', 'a'};
        int expected = 2; // After compression: ['a', '4']
        int actual = solution.compress(chars);
        assertEquals(expected, actual);
    }

    @Test
    void testCompress_mixedChars() {
        char[] chars = {'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c'};
        int expected = 6; // After compression: ['a', '2', 'b', '3', 'c', '4']
        int actual = solution.compress(chars);
        assertEquals(expected, actual);
    }
}
