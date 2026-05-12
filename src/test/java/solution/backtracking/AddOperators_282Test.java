package solution.backtracking;

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
}
