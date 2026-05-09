package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberToWords273Test {

    private final NumberToWords_273 test = new NumberToWords_273();

    @Test
    public void testHappyCases() {
        assertEquals("One Hundred Twenty Three", test.numberToWords(123));
        assertEquals("Twelve Thousand Three Hundred Forty Five", test.numberToWords(12345));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("Zero", test.numberToWords(0));
        assertEquals("One", test.numberToWords(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals("One Billion", test.numberToWords(1000000000));
    }
}
