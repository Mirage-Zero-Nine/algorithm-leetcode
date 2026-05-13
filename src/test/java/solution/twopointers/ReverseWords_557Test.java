package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseWords_557Test {

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

    @Test
    public void testAdditionalHappyCases() {
        assertEquals("ba dc", test.reverseWords("ab cd"));
        assertEquals("cba fed", test.reverseWords("abc def"));
        assertEquals("!iH ?ereht", test.reverseWords("Hi! there?"));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(null, test.reverseWords(null));
        assertEquals("elgnis", test.reverseWords("single"));
        assertEquals("  ", test.reverseWords("  "));
    }

    @Test
    public void testAdditionalGiantCase() {
        String input = "alpha beta gamma delta epsilon zeta eta theta iota kappa lambda mu";
        String expected = "ahpla ateb ammag atled nolispe atez ate ateht atoi appak adbmal um";
        assertEquals(expected, test.reverseWords(input));
    }

    @Test
    public void testPalindrome() {
        assertEquals("abcba xyz", test.reverseWords("abcba zyx"));
    }

    @Test
    public void testSingleCharWords() {
        assertEquals("a b c d", test.reverseWords("a b c d"));
    }

    @Test
    public void testGiantRepeatedWords() {
        String word = "abcdefghij";
        String reversed = "jihgfedcba";
        StringBuilder sb = new StringBuilder();
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            if (i > 0) { sb.append(" "); expected.append(" "); }
            sb.append(word);
            expected.append(reversed);
        }
        assertEquals(expected.toString(), test.reverseWords(sb.toString()));
    }

    @Test
    public void testNumbersAndSpecialChars() {
        assertEquals("321 654", test.reverseWords("123 456"));
    }
}
