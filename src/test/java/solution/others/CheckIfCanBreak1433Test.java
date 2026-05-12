package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckIfCanBreak1433Test {

    private final CheckIfCanBreak_1433 test = new CheckIfCanBreak_1433();

    @Test
    public void testHappyCases() {
        assertTrue(test.checkIfCanBreak("abc", "xya"));
        assertFalse(test.checkIfCanBreak("abe", "acd"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.checkIfCanBreak(null, "abc"));
        assertTrue(test.checkIfCanBreak("a", "a"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.checkIfCanBreak("abcdefg", "abcdefg"));
        assertTrue(test.checkIfCanBreak("abcdefg", "bcdefgh"));
    }
}
