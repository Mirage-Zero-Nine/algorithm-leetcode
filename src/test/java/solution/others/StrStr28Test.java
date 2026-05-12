package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StrStr28Test {

    private final StrStr_28 test = new StrStr_28();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.strStr("hello", "ll"));
        assertEquals(0, test.strStr("hello", "hello"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.strStr("hello", ""));
        assertEquals(-1, test.strStr("", "a"));
        assertEquals(-1, test.strStr("aaaaa", "bba"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.strStr("mississippi", "issip"));
    }
}
