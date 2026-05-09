package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumDistinct115Test {

    private final NumDistinct_115 test = new NumDistinct_115();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.numDistinct("rabbbit", "rabbit"));
        assertEquals(5, test.numDistinct("babgbag", "bag"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numDistinct("abc", "abcd"));
        assertEquals(1, test.numDistinct("abc", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.numDistinct("aabdbabd", "abd"));
    }
}
