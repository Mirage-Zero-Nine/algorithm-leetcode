package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberToWords_273Test {

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

    @Test
    public void testTeens() {
        assertEquals("Ten", test.numberToWords(10));
        assertEquals("Fifteen", test.numberToWords(15));
        assertEquals("Nineteen", test.numberToWords(19));
    }

    @Test
    public void testTens() {
        assertEquals("Twenty", test.numberToWords(20));
        assertEquals("Fifty", test.numberToWords(50));
        assertEquals("Ninety Nine", test.numberToWords(99));
    }

    @Test
    public void testHundreds() {
        assertEquals("One Hundred", test.numberToWords(100));
        assertEquals("Nine Hundred Ninety Nine", test.numberToWords(999));
    }

    @Test
    public void testThousands() {
        assertEquals("One Thousand", test.numberToWords(1000));
        assertEquals("Ten Thousand", test.numberToWords(10000));
    }

    @Test
    public void testMillions() {
        assertEquals("One Million", test.numberToWords(1000000));
        assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", test.numberToWords(1234567));
    }

    @Test
    public void testMaxInt() {
        assertEquals("Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven", test.numberToWords(2147483647));
    }

    @Test
    public void testGiantMixed() {
        assertEquals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety", test.numberToWords(1234567890));
    }
}
