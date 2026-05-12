package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsIsomorphic205Test {

    private final isIsomorphic_205 test = new isIsomorphic_205();

    @Test
    public void testHappyCases() {
        assertTrue(test.isIsomorphic("egg", "add"));
        assertFalse(test.isIsomorphic("foo", "bar"));
        assertTrue(test.isIsomorphic("paper", "title"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isIsomorphic("ab", "aa"));
        assertTrue(test.isIsomorphic("a", "a"));
        assertTrue(test.isIsomorphic("", ""));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isIsomorphic("abcdefg", "hijklmn"));
        assertFalse(test.isIsomorphic("abcdefg", "hijklmm"));
    }
}
