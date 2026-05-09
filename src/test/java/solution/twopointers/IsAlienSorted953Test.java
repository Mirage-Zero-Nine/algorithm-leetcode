package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsAlienSorted953Test {

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
}
