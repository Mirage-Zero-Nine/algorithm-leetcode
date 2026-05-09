package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseVowels345Test {

    private final ReverseVowels_345 test = new ReverseVowels_345();

    @Test
    public void testHappyCases() {
        assertEquals("holle", test.reverseVowels("hello"));
        assertEquals("leotcede", test.reverseVowels("leetcode"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("a", test.reverseVowels("a"));
        assertEquals("b", test.reverseVowels("b"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("aeiou", test.reverseVowels("uoiea"));
    }
}
