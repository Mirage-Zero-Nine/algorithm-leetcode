package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxAbsValExpr1131Test {

    private final MaxAbsValExpr_1131 test = new MaxAbsValExpr_1131();

    @Test
    public void testHappyCases() {
        assertEquals(13, test.maxAbsValExpr(new int[]{1, 2, 3, 4}, new int[]{-1, 4, 5, 6}));
        assertEquals(20, test.maxAbsValExpr(new int[]{1, -2, -5, 0, 10}, new int[]{0, -2, -1, -7, -4}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.maxAbsValExpr(new int[]{0}, new int[]{0}));
        assertEquals(3, test.maxAbsValExpr(new int[]{0, 1}, new int[]{0, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(12, test.maxAbsValExpr(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}));
    }
}
