package solutions.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Combine_77Test {

    private final Combine_77 test = new Combine_77();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.combine(4, 2);
        assertEquals(6, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.combine(0, 1).size());
        assertEquals(1, test.combine(1, 1).size());
        assertEquals(0, test.combine(3, 4).size());
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.combine(5, 3).size());
        assertEquals(252, test.combine(10, 5).size());
    }

    @Test
    public void testCombineNEqualsK() {
        List<List<Integer>> result = test.combine(3, 3);
        assertEquals(1, result.size());
        assertTrue(result.get(0).containsAll(List.of(1, 2, 3)));
    }

    @Test
    public void testCombineKEquals1() {
        List<List<Integer>> result = test.combine(5, 1);
        assertEquals(5, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 1));
    }

    @Test
    public void testCombineN2K1() {
        List<List<Integer>> result = test.combine(2, 1);
        assertEquals(2, result.size());
    }

    @Test
    public void testCombineN5K2() {
        List<List<Integer>> result = test.combine(5, 2);
        assertEquals(10, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 2));
    }

    @Test
    public void testNegativeKZero() {
        assertEquals(0, test.combine(5, 0).size());
    }

    @Test
    public void testNegativeNLessThanK() {
        assertEquals(0, test.combine(2, 5).size());
    }

    @Test
    public void testGiantCase() {
        // C(20, 10) = 184756
        List<List<Integer>> result = test.combine(20, 10);
        assertEquals(184756, result.size());
        assertTrue(result.stream().allMatch(l -> l.size() == 10));
    }
}
