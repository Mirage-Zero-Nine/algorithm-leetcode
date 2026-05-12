package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordPattern290Test {

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
}
