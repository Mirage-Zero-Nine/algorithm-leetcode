package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ReverseWords186Test {

    private final ReverseWords_186 test = new ReverseWords_186();

    @Test
    public void testHappyCases() {
        char[] arr = "the sky is blue".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("blue is sky the".toCharArray(), arr);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        char[] arr = "a".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("a".toCharArray(), arr);
    }

    @Test
    public void testLargeCase() {
        char[] arr = "hello world foo bar".toCharArray();
        test.reverseWords(arr);
        assertArrayEquals("bar foo world hello".toCharArray(), arr);
    }
}
