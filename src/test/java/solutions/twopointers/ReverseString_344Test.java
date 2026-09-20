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

    @Test public void testThreeChars() { char[] a={'a','b','c'};test.reverseString(a);assertArrayEquals(new char[]{'c','b','a'},a); }
    @Test public void testFourSymbols() { char[] a={'!','@','#','$'};test.reverseString(a);assertArrayEquals(new char[]{'$','#','@','!'},a); }
    @Test public void testUnicode() { char[] a={'λ','β','α'};test.reverseString(a);assertArrayEquals(new char[]{'α','β','λ'},a); }
    @Test public void testWhitespace() { char[] a={' ','x',' '};test.reverseString(a);assertArrayEquals(new char[]{' ','x',' '},a); }
    @Test public void testTwoDifferent() { char[] a={'x','y'};test.reverseString(a);assertArrayEquals(new char[]{'y','x'},a); }
    @Test public void testAlreadyReverse() { char[] a={'c','b','a'};test.reverseString(a);assertArrayEquals(new char[]{'a','b','c'},a); }
    @Test public void testLongRun() { char[] a="0123456789".repeat(100).toCharArray();char[] e=new StringBuilder(new String(a)).reverse().toString().toCharArray();test.reverseString(a);assertArrayEquals(e,a); }
    @Test public void testRepeatedCall() { char[] a={'a','b'};test.reverseString(a);test.reverseString(a);assertArrayEquals(new char[]{'a','b'},a); }
    @Test public void testMixedCase() { char[] a={'A','b','C','d'};test.reverseString(a);assertArrayEquals(new char[]{'d','C','b','A'},a); }
    @Test public void testEmptyAgain() { char[] a={};test.reverseString(a);assertArrayEquals(new char[]{},a); }
}
