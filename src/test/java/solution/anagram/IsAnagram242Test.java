package solution.anagram;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    }
}