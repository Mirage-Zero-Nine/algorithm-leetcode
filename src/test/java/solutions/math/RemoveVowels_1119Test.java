package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/11 14:44
 * Created with IntelliJ IDEA
 */

public class RemoveVowels_1119Test {

    private final RemoveVowels_1119 test = new RemoveVowels_1119();

    @Test
    public void test() {
        assertEquals("ltcdscmmntyfrcdrs", test.removeVowels("leetcodeisacommunityforcoders"));
        assertEquals("bcdfghjklmn", test.removeVowels("abcdefghijklmn"));
    }

    @Test
    public void testEmpty() {
        assertEquals("", test.removeVowels("aeiou"));
        assertEquals("", test.removeVowels(""));
    }

    @Test
    public void testHappyCases() {
        assertEquals("bcdf", test.removeVowels("abcdef"));
        assertEquals("xyz", test.removeVowels("xyz"));
        assertEquals("ltcd", test.removeVowels("leetcode"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("bnn", test.removeVowels("banana"));
        assertEquals("qck brwn fx", test.removeVowels("quick brown fox"));
        assertEquals("12345", test.removeVowels("12345"));
    }

    @Test
    public void testGiantCase() {
        String input = "aeiou".repeat(100) + "leetcode".repeat(50);
        String expected = "ltcd".repeat(50);
        assertEquals(expected, test.removeVowels(input));
    }

    @Test
    public void testAllConsonants() {
        assertEquals("bcdfg", test.removeVowels("bcdfg"));
    }

    @Test
    public void testSingleVowel() {
        assertEquals("", test.removeVowels("a"));
        assertEquals("", test.removeVowels("e"));
        assertEquals("", test.removeVowels("i"));
        assertEquals("", test.removeVowels("o"));
        assertEquals("", test.removeVowels("u"));
    }

    @Test
    public void testSingleConsonant() {
        assertEquals("b", test.removeVowels("b"));
        assertEquals("z", test.removeVowels("z"));
    }

    @Test
    public void testMixedWithSpecialChars() {
        assertEquals("hll wrld!", test.removeVowels("hello world!"));
        assertEquals("123-456", test.removeVowels("123-456"));
    }

    @Test
    public void testUpperCaseNotRemoved() {
        // Implementation only removes lowercase vowels
        assertEquals("AEIOU", test.removeVowels("AEIOU"));
        assertEquals("Hll", test.removeVowels("Hello"));
    }
}
