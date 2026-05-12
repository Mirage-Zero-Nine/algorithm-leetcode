package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestDecomposition_1147Test {
    private final LongestDecomposition_1147 solver = new LongestDecomposition_1147();

    @Test public void testBasic() {
        // "ghiabcdefhelloadamhelloabcdefghi"
        // (ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi) => 7
        assertEquals(7, solver.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    }

    @Test public void testMerryChristmas() {
        // "aaaa" decomposes into (a)(a)(a)(a) => 4
        assertEquals(4, solver.longestDecomposition("aaaa"));
    }

    @Test public void testSingle() {
        assertEquals(1, solver.longestDecomposition("a"));
    }

    @Test public void testRepeat() {
        // "aaa" decomposes into (a)(a)(a) => 3
        assertEquals(3, solver.longestDecomposition("volvo"));
    }

    @Test public void testComplexOdd() {
        assertEquals(11, solver.longestDecomposition("antaprezatepzapreanta"));
    }
}
