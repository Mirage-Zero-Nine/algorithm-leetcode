package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EqualSubstring1208Test {

    private final EqualSubstring_1208 test = new EqualSubstring_1208();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.equalSubstring("abcd", "bcdf", 3));
        assertEquals(1, test.equalSubstring("abcd", "cdef", 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.equalSubstring("abcd", "acde", 0));
        assertEquals(1, test.equalSubstring("a", "b", 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.equalSubstring("krrgw", "zjxss", 19));
    }
}
