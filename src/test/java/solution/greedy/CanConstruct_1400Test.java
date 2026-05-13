package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanConstruct_1400Test {
    private final CanConstruct_1400 solver = new CanConstruct_1400();

    @Test public void testBasic() {
        assertTrue(solver.canConstruct("annabelle", 2));
    }

    @Test public void testTooFewPalindromes() {
        assertFalse(solver.canConstruct("leetcode", 3));
    }

    @Test public void testKEqualsLength() {
        assertTrue(solver.canConstruct("true", 4));
    }

    @Test public void testKGreaterThanLength() {
        // k=20 > length=15, so must be false
        assertFalse(solver.canConstruct("yzyzyzyzyzyzyzy", 20));
    }

    @Test public void testEmpty() {
        assertFalse(solver.canConstruct("", 1));
    }

    @Test public void testNull() {
        assertFalse(solver.canConstruct(null, 1));
    }

    @Test public void testSingleChar() {
        assertTrue(solver.canConstruct("a", 1));
    }

    @Test public void testAllSameChars() {
        // "aaaa" has 0 odd chars, k=2 <= 4
        assertTrue(solver.canConstruct("aaaa", 2));
    }

    @Test public void testOddCharsExceedK() {
        // "abc" has 3 odd chars, k=2 < 3 odd chars
        assertFalse(solver.canConstruct("abc", 2));
    }

    @Test public void testKEqualsOne() {
        // "aab" has 1 odd char, k=1 >= 1
        assertTrue(solver.canConstruct("aab", 1));
    }

    @Test public void testGiant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) sb.append((char) ('a' + i % 26));
        assertTrue(solver.canConstruct(sb.toString(), 5000));
    }
}
