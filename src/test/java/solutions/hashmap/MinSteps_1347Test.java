package solutions.hashmap;

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

    @Test
    void testOneCharDifferent() {
        assertEquals(1, solution.minSteps("a", "b"));
    }

    @Test
    void testCompletelyDifferent() {
        assertEquals(3, solution.minSteps("aaa", "bbb"));
    }

    @Test
    void testPartialOverlap() {
        assertEquals(4, solution.minSteps("friend", "family"));
    }

    @Test
    void testLeetcodeExample() {
        assertEquals(5, solution.minSteps("leetcode", "practice"));
    }

    @Test
    void testReversedStrings() {
        assertEquals(0, solution.minSteps("abcde", "edcba"));
    }

    @Test
    void testGiantStrings() {
        char[] s = new char[50000];
        char[] t = new char[50000];
        java.util.Arrays.fill(s, 'a');
        java.util.Arrays.fill(t, 'b');
        assertEquals(50000, solution.minSteps(new String(s), new String(t)));
    }
}
