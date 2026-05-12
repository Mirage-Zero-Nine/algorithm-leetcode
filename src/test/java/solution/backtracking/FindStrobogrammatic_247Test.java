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

class FindStrobogrammatic_247Test {
    private final FindStrobogrammatic_247 solution = new FindStrobogrammatic_247();

    @Test
    void testOne() {
        List<String> result = solution.findStrobogrammatic(1);
        assertEquals(3, result.size());
    }

    @Test
    void testTwo() {
        List<String> result = solution.findStrobogrammatic(2);
        assertEquals(4, result.size());
    }

    @Test
    void testThree() {
        List<String> result = solution.findStrobogrammatic(3);
        assertTrue(result.size() >= 12);
    }

    @Test
    void testFour() {
        List<String> result = solution.findStrobogrammatic(4);
        assertTrue(result.size() >= 16);
    }

    @Test
    void testZero() {
        List<String> result = solution.findStrobogrammatic(0);
        assertTrue(result.size() <= 1);
    }
}
