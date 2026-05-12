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
}
