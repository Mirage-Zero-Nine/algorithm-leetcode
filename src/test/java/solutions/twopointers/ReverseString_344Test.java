package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ReverseString_344Test {

    private final ReverseString_344 test = new ReverseString_344();

    @Test
    public void testHappyCases() {
        char[] arr = {'h', 'e', 'l', 'l', 'o'};
        test.reverseString(arr);
        assertArrayEquals(new char[]{'o', 'l', 'l', 'e', 'h'}, arr);
    }

    @Test
    public void testEdgeCases() {
        char[] arr = {'a'};
        test.reverseString(arr);
        assertArrayEquals(new char[]{'a'}, arr);
    }

    @Test
    public void testLargeCase() {
        char[] arr = {'a', 'b', 'c', 'd', 'e', 'f'};
        test.reverseString(arr);
        assertArrayEquals(new char[]{'f', 'e', 'd', 'c', 'b', 'a'}, arr);
    }

    @Test
    public void testAdditionalHappyCases() {
        char[] even = {'a', 'b', 'c', 'd'};
        test.reverseString(even);
        assertArrayEquals(new char[]{'d', 'c', 'b', 'a'}, even);

        char[] odd = {'x', 'y', 'z'};
        test.reverseString(odd);
        assertArrayEquals(new char[]{'z', 'y', 'x'}, odd);
    }

    @Test
    public void testAdditionalEdgeCases() {
        char[] empty = {};
        test.reverseString(empty);
        assertArrayEquals(new char[]{}, empty);

        char[] repeated = {'q', 'q', 'q'};
        test.reverseString(repeated);
        assertArrayEquals(new char[]{'q', 'q', 'q'}, repeated);

        char[] spaces = {'a', ' ', 'b'};
        test.reverseString(spaces);
        assertArrayEquals(new char[]{'b', ' ', 'a'}, spaces);
    }

    @Test
    public void testAdditionalGiantCase() {
        char[] giant = "abcdefghijklmnopqrstuvwxyz".repeat(20).toCharArray();
        char[] expected = new StringBuilder(new String(giant)).reverse().toString().toCharArray();
        test.reverseString(giant);
        assertArrayEquals(expected, giant);
    }

    @Test
    public void testTwoChars() {
        char[] arr = {'a', 'b'};
        test.reverseString(arr);
        assertArrayEquals(new char[]{'b', 'a'}, arr);
    }

    @Test
    public void testPalindrome() {
        char[] arr = {'r', 'a', 'c', 'e', 'c', 'a', 'r'};
        test.reverseString(arr);
        assertArrayEquals(new char[]{'r', 'a', 'c', 'e', 'c', 'a', 'r'}, arr);
    }

    @Test
    public void testSpecialChars() {
        char[] arr = {'!', '@', '#', '$', '%'};
        test.reverseString(arr);
        assertArrayEquals(new char[]{'%', '$', '#', '@', '!'}, arr);
    }

    @Test
    public void testDigits() {
        char[] arr = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        test.reverseString(arr);
        assertArrayEquals(new char[]{'0', '9', '8', '7', '6', '5', '4', '3', '2', '1'}, arr);
    }
}
