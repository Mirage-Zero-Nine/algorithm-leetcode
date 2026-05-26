package solutions.greedy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckIfCanBreak_1433Test {

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

    @Test
    public void testNullSecond() {
        assertFalse(test.checkIfCanBreak("abc", null));
    }

    @Test
    public void testBothNull() {
        assertFalse(test.checkIfCanBreak(null, null));
    }

    @Test
    public void testDifferentLengths() {
        assertFalse(test.checkIfCanBreak("ab", "abc"));
    }

    @Test
    public void testIdenticalStrings() {
        assertTrue(test.checkIfCanBreak("zzz", "zzz"));
    }

    @Test
    public void testReverseBreak() {
        assertTrue(test.checkIfCanBreak("xya", "abc"));
    }

    @Test
    public void testCannotBreakEitherWay() {
        assertFalse(test.checkIfCanBreak("abe", "acd"));
    }

    @Test
    public void testAllSameChar() {
        assertTrue(test.checkIfCanBreak("aaa", "aaa"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb1.append('b');
            sb2.append('a');
        }
        assertTrue(test.checkIfCanBreak(sb1.toString(), sb2.toString()));
    }
}
