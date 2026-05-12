package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseWords557Test {

    private final ReverseWords_557 test = new ReverseWords_557();

    @Test
    public void testHappyCases() {
        assertEquals("s'teL ekat edoCteeL tsetnoc", test.reverseWords("Let's take LeetCode contest"));
        assertEquals("doG gniD", test.reverseWords("God Ding"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("a", test.reverseWords("a"));
        assertEquals("", test.reverseWords(""));
    }

    @Test
    public void testLargeCase() {
        assertEquals("olleH dlroW", test.reverseWords("Hello World"));
    }
}
