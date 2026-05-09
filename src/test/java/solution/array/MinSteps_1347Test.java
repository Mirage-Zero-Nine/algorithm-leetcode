package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinSteps_1347Test {
    private final MinSteps_1347 solution = new MinSteps_1347();

    @Test
    void testBasic() {
        assertEquals(1, solution.minSteps("bab", "aba"));
    }

    @Test
    void testNoChange() {
        assertEquals(0, solution.minSteps("leetcode", "codeleet"));
    }

    @Test
    void testAllDifferent() {
        assertEquals(0, solution.minSteps("anagram", "mangaar"));
    }

    @Test
    void testSameString() {
        assertEquals(0, solution.minSteps("abc", "abc"));
    }

    @Test
    void testOneChar() {
        assertEquals(0, solution.minSteps("a", "a"));
    }
}
