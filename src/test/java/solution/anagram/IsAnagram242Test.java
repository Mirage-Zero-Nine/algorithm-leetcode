package solution.anagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:05
 * Created with IntelliJ IDEA
 */

public class IsAnagram242Test {
    private final IsAnagram_242 test = new IsAnagram_242();

    @Test
    public void test() {
        assertTrue(test.isAnagram("anagram", "nagaram"));
        assertFalse(test.isAnagram("rat", "car"));
        assertFalse(test.isAnagram("test", ""));
        assertTrue(test.isAnagram("", ""));
    }

    @Test
    public void testMethodWithHashMap() {
        assertTrue(test.isAnagramWithHashMap("anagram", "nagaram"));
        assertFalse(test.isAnagramWithHashMap("rat", "car"));
        assertFalse(test.isAnagramWithHashMap("test", ""));
        assertTrue(test.isAnagram("", ""));
    }
}