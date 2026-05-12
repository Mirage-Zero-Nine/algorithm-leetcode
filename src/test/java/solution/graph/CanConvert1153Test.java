package solution.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanConvert1153Test {

    private final CanConvert_1153 test = new CanConvert_1153();

    @Test
    public void testHappyCases() {
        assertTrue(test.canConvert("aabcc", "ccdee"));
        assertFalse(test.canConvert("leetcode", "codeleet"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.canConvert("abc", "abc"));
        assertFalse(test.canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canConvert("aabbcc", "bbccdd"));
        assertTrue(test.canConvert("ab", "ba"));
    }
}
