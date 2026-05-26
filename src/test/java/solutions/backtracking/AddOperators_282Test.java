package solutions.backtracking;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddOperators_282Test {
    private final AddOperators_282 solution = new AddOperators_282();

    @Test
    void testBasic() {
        List<String> result = solution.addOperators("123", 6);
        assertTrue(result.contains("1+2+3") || result.contains("1*2*3"));
    }

    @Test
    void testMultiplication() {
        List<String> result = solution.addOperators("232", 8);
        assertTrue(result.contains("2*3+2") || result.contains("2+3*2"));
    }

    @Test
    void testNoSolution() {
        List<String> result = solution.addOperators("3456237490", 9191);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleDigit() {
        List<String> result = solution.addOperators("3", 3);
        assertEquals(1, result.size());
        assertEquals("3", result.get(0));
    }

    @Test
    void testZero() {
        List<String> result = solution.addOperators("00", 0);
        assertEquals(Set.of("0+0", "0-0", "0*0"), new HashSet<>(result));
    }

    @Test
    void testEmptyString() {
        List<String> result = solution.addOperators("", 0);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleDigitNoMatch() {
        List<String> result = solution.addOperators("5", 3);
        assertTrue(result.isEmpty());
    }

    @Test
    void testLeadingZero() {
        List<String> result = solution.addOperators("105", 5);
        assertTrue(result.contains("1*0+5"));
    }

    @Test
    void testTwoDigits() {
        List<String> result = solution.addOperators("12", 3);
        assertTrue(result.contains("1+2"));
    }

    @Test
    void testLargeTarget() {
        List<String> result = solution.addOperators("123456", 123456);
        assertTrue(result.contains("123456"));
    }

    @Test
    void testGiantInput() {
        // "3456237490" with target 9191 - known to have no solution
        List<String> result = solution.addOperators("3456237490", 9191);
        assertTrue(result.isEmpty());
    }

    @Test
    void testAllSameDigits() {
        List<String> result = solution.addOperators("111", 3);
        assertTrue(result.contains("1+1+1"));
    }

    @Test
    void testNegativeTarget() {
        List<String> result = solution.addOperators("12", -1);
        assertTrue(result.contains("1-2"));
    }
}
