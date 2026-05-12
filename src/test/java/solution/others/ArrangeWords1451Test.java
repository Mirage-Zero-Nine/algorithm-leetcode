package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ArrangeWords1451Test {

    private final ArrangeWords_1451 test = new ArrangeWords_1451();

    @Test
    public void testHappyCases() {
        assertEquals("To be or is not this", test.arrangeWords("To be or not this is"));
        assertEquals("Is cool leetcode", test.arrangeWords("Leetcode is cool"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("A", test.arrangeWords("A"));
        assertEquals("Hi", test.arrangeWords("Hi"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("I a am very good student", test.arrangeWords("I am a very good student"));
    }
}
