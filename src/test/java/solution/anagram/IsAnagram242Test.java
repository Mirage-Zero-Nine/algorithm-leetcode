package solution.anagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsAnagram242Test {

    private final IsAnagram_242 test = new IsAnagram_242();

    @Test
    public void testHappyCases() {
        assertTrue(test.isAnagram("anagram", "nagaram"));
        assertTrue(test.isAnagramWithHashMap("listen", "silent"));
    }

    @Test
    public void testNegativeCases() {
        assertFalse(test.isAnagram("rat", "car"));
        assertFalse(test.isAnagramWithHashMap("hello", "world"));
    }

    @Test
    public void testInvalidCases() {
        assertThrows(NullPointerException.class, () -> test.isAnagram(null, "a"));
        assertThrows(NullPointerException.class, () -> test.isAnagramWithHashMap("a", null));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.isAnagram("", ""));
        assertTrue(test.isAnagramWithHashMap("a", "a"));
        assertFalse(test.isAnagram("ab", "a"));
    }

    @Test
    public void testLargeCase() {
        String s = "a".repeat(50) + "b".repeat(40) + "c".repeat(30);
        String t = "c".repeat(30) + "b".repeat(40) + "a".repeat(50);
        assertTrue(test.isAnagram(s, t));
        assertTrue(test.isAnagramWithHashMap(s, t));
    }
}
