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

class GeneratePalindromes_267Test {
    private final GeneratePalindromes_267 solution = new GeneratePalindromes_267();

    @Test
    void testBasic() {
        List<String> result = solution.generatePalindromes("aabb");
        assertEquals(2, result.size());
    }

    @Test
    void testSingleChar() {
        List<String> result = solution.generatePalindromes("a");
        assertEquals(1, result.size());
    }

    @Test
    void testNoSolution() {
        List<String> result = solution.generatePalindromes("abc");
        assertEquals(0, result.size());
    }

    @Test
    void testThreeSame() {
        List<String> result = solution.generatePalindromes("aaa");
        assertEquals(1, result.size());
    }

    @Test
    void testMultipleSolutions() {
        List<String> result = solution.generatePalindromes("aabbcc");
        assertTrue(result.size() >= 6);
    }
}
