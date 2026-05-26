package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NextGreatestLetter_744Test {

    private final NextGreatestLetter_744 test = new NextGreatestLetter_744();

    @Test
    public void testHappyCases() {
        assertEquals('c', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'a'));
        assertEquals('f', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c'));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals('c', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j'));
        assertEquals('c', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'z'));
    }

    @Test
    public void testLargeCase() {
        assertEquals('b', test.nextGreatestLetter(new char[]{'a', 'b', 'c', 'd', 'e'}, 'a'));
    }

    @Test
    public void testTargetBetweenLetters() {
        assertEquals('j', test.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'g'));
    }

    @Test
    public void testSingleElementArrayWrapsAlways() {
        assertEquals('m', test.nextGreatestLetter(new char[]{'m'}, 'a'));
    }

    @Test
    public void testDuplicateLetters() {
        assertEquals('h', test.nextGreatestLetter(new char[]{'a', 'a', 'd', 'h', 'h'}, 'd'));
    }

    @Test
    public void testTargetBeforeFirstLetter() {
        assertEquals('b', test.nextGreatestLetter(new char[]{'b', 'd', 'f'}, 'a'));
    }

    @Test
    public void testTargetEqualsMiddleDuplicate() {
        assertEquals('z', test.nextGreatestLetter(new char[]{'e', 'e', 'e', 'z'}, 'e'));
    }

    @Test
    public void testTargetJustBeforeLargest() {
        assertEquals('z', test.nextGreatestLetter(new char[]{'a', 'm', 'z'}, 'y'));
    }

    @Test
    public void testGiantCase() {
        char[] letters = new char[1000];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char) ('a' + (i % 26));
        }
        java.util.Arrays.sort(letters);
        assertEquals('z', test.nextGreatestLetter(letters, 'y'));
    }
}
