package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountLetters1180Test {

    private final CountLetters_1180 test = new CountLetters_1180();

    @Test
    public void testHappyCases() {
        assertEquals(8, test.countLetters("aaaba"));
        assertEquals(10, test.countLetters("aaaa"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.countLetters("a"));
        assertEquals(2, test.countLetters("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.countLetters("aaaaaaaaaa"));
    }
}
