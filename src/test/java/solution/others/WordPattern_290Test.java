package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordPattern_290Test {

    private final WordPattern_290 test = new WordPattern_290();

    @Test
    public void testHappyCases() {
        assertTrue(test.wordPattern("abba", "dog cat cat dog"));
        assertFalse(test.wordPattern("abba", "dog cat cat fish"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.wordPattern("aaaa", "dog cat cat dog"));
        assertFalse(test.wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.wordPattern("aabb", "dog dog cat cat"));
        assertFalse(test.wordPattern("aabb", "dog cat dog cat"));
    }

    @Test
    public void testSingleChar() {
        assertTrue(test.wordPattern("a", "dog"));
    }

    @Test
    public void testLengthMismatch() {
        assertFalse(test.wordPattern("ab", "dog"));
    }

    @Test
    public void testAllUnique() {
        assertTrue(test.wordPattern("abcd", "one two three four"));
    }

    @Test
    public void testDuplicateValueDifferentKey() {
        // pattern "ab" but both words are same -> b maps to same word as a
        assertFalse(test.wordPattern("ab", "dog dog"));
    }

    @Test
    public void testLongerPattern() {
        assertTrue(test.wordPattern("abcabc", "x y z x y z"));
    }

    @Test
    public void testEmptyPattern() {
        assertFalse(test.wordPattern("", "dog"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder pattern = new StringBuilder();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            pattern.append((char) ('a' + i));
            if (i > 0) str.append(" ");
            str.append("word").append(i);
        }
        assertTrue(test.wordPattern(pattern.toString(), str.toString()));
    }
}
