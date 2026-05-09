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
}
