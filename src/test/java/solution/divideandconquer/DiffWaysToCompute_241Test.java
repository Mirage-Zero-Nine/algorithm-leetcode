package solution.divideandconquer;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiffWaysToCompute_241Test {
    private final DiffWaysToCompute_241 solver = new DiffWaysToCompute_241();

    @Test public void testExample1() {
        List<Integer> result = solver.diffWaysToCompute("2-1-1");
        Set<Integer> set = result.stream().collect(Collectors.toSet());
        assertTrue(set.contains(0));
        assertTrue(set.contains(2));
    }

    @Test public void testExample2() {
        List<Integer> result = solver.diffWaysToCompute("2*3-4*5");
        Set<Integer> set = result.stream().collect(Collectors.toSet());
        assertTrue(set.contains(-34));
        assertTrue(set.contains(-14));
        assertTrue(set.contains(10));
    }

    @Test public void testSingleNumber() {
        List<Integer> result = solver.diffWaysToCompute("5");
        assertEquals(1, result.size());
        assertEquals(5, result.get(0));
    }

    @Test public void testAdditionOnly() {
        // (1+2)+3=6 and 1+(2+3)=6 → both return 6
        List<Integer> result = solver.diffWaysToCompute("1+2+3");
        Set<Integer> set = result.stream().collect(Collectors.toSet());
        assertEquals(1, set.size());
        assertTrue(set.contains(6));
    }

    @Test public void testMultiplicationOnly() {
        // (2*3)*4=24 and 2*(3*4)=24 → both return 24
        List<Integer> result = solver.diffWaysToCompute("2*3*4");
        Set<Integer> set = result.stream().collect(Collectors.toSet());
        assertEquals(1, set.size());
        assertTrue(set.contains(24));
    }

    @Test public void testSubtractionOnly() {
        // (5-3)-1=1 and 5-(3-1)=3 → two different results
        List<Integer> result = solver.diffWaysToCompute("5-3-1");
        Set<Integer> set = result.stream().collect(Collectors.toSet());
        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
    }

    @Test public void testMixedOps() {
        List<Integer> result = solver.diffWaysToCompute("1+2*3");
        Set<Integer> set = result.stream().collect(Collectors.toSet());
        assertTrue(set.contains(7));
        assertTrue(set.contains(9));
    }

    @Test public void testTwoNumbers() {
        List<Integer> result = solver.diffWaysToCompute("1+2");
        assertEquals(1, result.size());
        assertEquals(3, result.get(0));
    }

    @Test public void testNegativeResult() {
        List<Integer> result = solver.diffWaysToCompute("1-10");
        assertEquals(1, result.size());
        assertEquals(-9, result.get(0));
    }

    @Test public void testComplexExpression() {
        List<Integer> result = solver.diffWaysToCompute("3*4*5+6*7*8+9");
        assertFalse(result.isEmpty());
    }

    @Test public void testMultidigitNumbers() {
        List<Integer> result = solver.diffWaysToCompute("10+20");
        assertEquals(1, result.size());
        assertEquals(30, result.get(0));
    }

    @Test public void testNullInput() {
        List<Integer> result = solver.diffWaysToCompute(null);
        assertTrue(result.isEmpty());
    }

    @Test public void testEmptyInput() {
        List<Integer> result = solver.diffWaysToCompute("");
        assertTrue(result.isEmpty());
    }
}
