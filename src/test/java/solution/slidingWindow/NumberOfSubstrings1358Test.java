package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOfSubstrings1358Test {

    private final NumberOfSubstrings_1358 test = new NumberOfSubstrings_1358();

    @Test
    public void testHappyCases() {
        assertEquals(10, test.numberOfSubstrings("abcabc"));
        assertEquals(3, test.numberOfSubstrings("aaacb"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numberOfSubstrings("abc"));
        assertEquals(0, test.numberOfSubstrings("aab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(28, test.numberOfSubstrings("abcabcabc"));
    }
}
