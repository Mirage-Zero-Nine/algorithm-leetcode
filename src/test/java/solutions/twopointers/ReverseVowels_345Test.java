package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseVowels_345Test {

    private final ReverseVowels_345 test = new ReverseVowels_345();

    @Test
    public void testHappyCases() {
        assertEquals("holle", test.reverseVowels("hello"));
        assertEquals("leotcede", test.reverseVowels("leetcode"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("a", test.reverseVowels("a"));
        assertEquals("b", test.reverseVowels("b"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("aeiou", test.reverseVowels("uoiea"));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", test.reverseVowels(""));
    }

    @Test
    public void testNoVowels() {
        assertEquals("bcdfg", test.reverseVowels("bcdfg"));
    }

    @Test
    public void testAllVowels() {
        assertEquals("uoiea", test.reverseVowels("aeiou"));
    }

    @Test
    public void testUpperCaseVowels() {
        assertEquals("hOlle", test.reverseVowels("hellO"));
    }

    @Test
    public void testMixedCaseVowels() {
        assertEquals("Ai", test.reverseVowels("iA"));
    }

    @Test
    public void testSingleConsonant() {
        assertEquals("z", test.reverseVowels("z"));
    }

    @Test
    public void testTwoCharVowels() {
        assertEquals("oa", test.reverseVowels("ao"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append("ab");
        String input = sb.toString(); // "ababab..." - vowels are all 'a' so reversing them gives same string
        assertEquals(input, test.reverseVowels(input));
    }

    @Test public void testUppercaseAll() { assertEquals("UOIEA",test.reverseVowels("AEIOU")); }
    @Test public void testVowelsAtEnds() { assertEquals("uiebcdaoa",test.reverseVowels("aoabcdeiu")); }
    @Test public void testYIsConsonant() { assertEquals("yay",test.reverseVowels("yay")); }
    @Test public void testConsonantsPreserved() { assertEquals("bacef",test.reverseVowels("becaf")); }
    @Test public void testSingleUpperVowel() { assertEquals("A",test.reverseVowels("A")); }
    @Test public void testAlternatingVowels() { assertEquals("uoiea",test.reverseVowels("aeiou")); }
    @Test public void testPunctuation() { assertEquals("!e?A",test.reverseVowels("!A?e")); }
    @Test public void testLongVowelString() { assertEquals("u".repeat(100),test.reverseVowels("u".repeat(100))); }
    @Test public void testRepeatedCall() { assertEquals("holle",test.reverseVowels("hello")); assertEquals("world",test.reverseVowels("world")); }
}
