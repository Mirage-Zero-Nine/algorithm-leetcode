package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ReverseString344Test {

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
}
