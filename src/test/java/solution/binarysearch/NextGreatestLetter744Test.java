package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NextGreatestLetter744Test {

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
}
