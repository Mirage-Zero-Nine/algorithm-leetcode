package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumSubmatrixSumTarget_1074Test {
    private final NumSubmatrixSumTarget_1074 solution = new NumSubmatrixSumTarget_1074();

    @Test
    void testBasic() {
        assertEquals(4, solution.numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
    }

    @Test
    void testSingleCell() {
        assertEquals(1, solution.numSubmatrixSumTarget(new int[][]{{1}}, 1));
    }

    @Test
    void testNoMatch() {
        assertEquals(1, solution.numSubmatrixSumTarget(new int[][]{{1, 2}, {3, 4}}, 10));
    }

    @Test
    void testNegatives() {
        assertEquals(5, solution.numSubmatrixSumTarget(new int[][]{{1, -1}, {-1, 1}}, 0));
    }

    @Test
    void testLargeTarget() {
        assertEquals(0, solution.numSubmatrixSumTarget(new int[][]{{904}}, 0));
    }
}
