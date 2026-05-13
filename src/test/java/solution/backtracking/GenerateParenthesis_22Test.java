package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateParenthesis_22Test {
    private final GenerateParenthesis_22 solution = new GenerateParenthesis_22();

    @Test
    void testOne() {
        List<String> result = solution.generateParenthesis(1);
        assertEquals(1, result.size());
        assertTrue(result.contains("()"));
    }

    @Test
    void testThree() {
        List<String> result = solution.generateParenthesis(3);
        assertEquals(5, result.size());
    }

    @Test
    void testTwo() {
        List<String> result = solution.generateParenthesis(2);
        assertEquals(2, result.size());
    }

    @Test
    void testFour() {
        List<String> result = solution.generateParenthesis(4);
        assertEquals(14, result.size());
    }

    @Test
    void testZero() {
        List<String> result = solution.generateParenthesis(0);
        assertTrue(result.size() <= 1);
    }

    @Test
    void testZeroReturnsEmpty() {
        List<String> result = solution.generateParenthesis(0);
        assertEquals(0, result.size());
    }

    @Test
    void testFive() {
        // Catalan number C(5) = 42
        List<String> result = solution.generateParenthesis(5);
        assertEquals(42, result.size());
    }

    @Test
    void testSix() {
        // Catalan number C(6) = 132
        List<String> result = solution.generateParenthesis(6);
        assertEquals(132, result.size());
    }

    @Test
    void testAllResultsCorrectLength() {
        List<String> result = solution.generateParenthesis(4);
        for (String s : result) {
            assertEquals(8, s.length());
        }
    }

    @Test
    void testAllResultsBalanced() {
        List<String> result = solution.generateParenthesis(3);
        for (String s : result) {
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') count++;
                else count--;
                assertTrue(count >= 0, "Unbalanced: " + s);
            }
            assertEquals(0, count);
        }
    }

    @Test
    void testNoDuplicates() {
        List<String> result = solution.generateParenthesis(4);
        Set<String> set = new HashSet<>(result);
        assertEquals(result.size(), set.size());
    }

    @Test
    void testThreeContainsExpected() {
        List<String> result = solution.generateParenthesis(3);
        assertTrue(result.contains("((()))"));
        assertTrue(result.contains("(()())"));
        assertTrue(result.contains("(())()"));
        assertTrue(result.contains("()(())"));
        assertTrue(result.contains("()()()"));
    }

    @Test
    void testGiantCase() {
        // Catalan number C(10) = 16796
        List<String> result = solution.generateParenthesis(10);
        assertEquals(16796, result.size());
    }
}
