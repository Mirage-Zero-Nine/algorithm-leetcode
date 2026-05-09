package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountSubstrings647Test {

    private final CountSubstrings_647 test = new CountSubstrings_647();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.countSubstrings("abc"));
        assertEquals(6, test.countSubstrings("aaa"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countSubstrings(""));
        assertEquals(1, test.countSubstrings("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, test.countSubstrings("abcba"));
    }
}
