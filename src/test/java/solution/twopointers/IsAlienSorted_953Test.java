package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsAlienSorted_953Test {

    private final IsAlienSorted_953 test = new IsAlienSorted_953();

    @Test
    public void testHappyCases() {
        assertTrue(test.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        assertFalse(test.isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"a"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isAlienSorted(new String[]{"kuvp", "q"}, "ngxlkthsjuoqcpavbfdermiywz"));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertTrue(test.isAlienSorted(new String[]{"app", "apple"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"aa", "ab", "b"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"z", "zx"}, "zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testAdditionalNegativeAndEdgeCases() {
        assertFalse(test.isAlienSorted(new String[]{"zoo", "ant"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"same", "same"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertTrue(test.isAlienSorted(
            new String[]{"aaa", "aab", "aac", "aad", "aae", "aaf", "aag", "aah", "aai", "aaj"},
            "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testReversedOrder() {
        assertTrue(test.isAlienSorted(new String[]{"z", "y", "x"}, "zyxwvutsrqponmlkjihgfedcba"));
        assertFalse(test.isAlienSorted(new String[]{"a", "b", "c"}, "zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testSingleCharWords() {
        assertTrue(test.isAlienSorted(new String[]{"a", "b"}, "abcdefghijklmnopqrstuvwxyz"));
        assertFalse(test.isAlienSorted(new String[]{"b", "a"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testAllSameWords() {
        assertTrue(test.isAlienSorted(new String[]{"abc", "abc", "abc"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testGiantWordList() {
        String[] words = new String[100];
        for (int i = 0; i < 100; i++) {
            words[i] = "a".repeat(i + 1);
        }
        assertTrue(test.isAlienSorted(words, "abcdefghijklmnopqrstuvwxyz"));
    }
}
