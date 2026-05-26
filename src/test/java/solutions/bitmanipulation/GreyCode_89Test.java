package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreyCode_89Test {
    private final GreyCode_89 solver = new GreyCode_89();

    @Test public void testN2() {
        List<Integer> res = solver.grayCode(2);
        assertEquals(4, res.size());
        assertEquals(0, res.get(0).intValue());
        assertGrayProperty(res);
    }

    @Test public void testN0() {
        List<Integer> res = solver.grayCode(0);
        assertEquals(1, res.size());
        assertEquals(0, res.get(0).intValue());
    }

    @Test public void testN1() {
        List<Integer> res = solver.grayCode(1);
        assertEquals(List.of(0, 1), res);
    }

    @Test public void testN3() {
        List<Integer> res = solver.grayCode(3);
        assertEquals(8, res.size());
        assertGrayProperty(res);
    }

    @Test public void testUnique() {
        List<Integer> res = solver.grayCode(4);
        Set<Integer> seen = new HashSet<>(res);
        assertEquals(16, seen.size());
    }

    @Test public void testN4Size() {
        List<Integer> res = solver.grayCode(4);
        assertEquals(16, res.size());
        assertEquals(0, res.get(0).intValue());
        assertGrayProperty(res);
    }

    @Test public void testN5() {
        List<Integer> res = solver.grayCode(5);
        assertEquals(32, res.size());
        assertGrayProperty(res);
    }

    @Test public void testN5Unique() {
        List<Integer> res = solver.grayCode(5);
        Set<Integer> seen = new HashSet<>(res);
        assertEquals(32, seen.size());
    }

    @Test public void testStartsWithZero() {
        for (int n = 0; n <= 5; n++) {
            assertEquals(0, solver.grayCode(n).get(0).intValue());
        }
    }

    @Test public void testGiantN10() {
        List<Integer> res = solver.grayCode(10);
        assertEquals(1024, res.size());
        assertGrayProperty(res);
        Set<Integer> seen = new HashSet<>(res);
        assertEquals(1024, seen.size());
    }

    private void assertGrayProperty(List<Integer> codes) {
        for (int i = 1; i < codes.size(); i++) {
            int diff = codes.get(i) ^ codes.get(i - 1);
            // diff must be a power of 2 (one bit difference)
            assertEquals(1, Integer.bitCount(diff));
        }
    }
}
