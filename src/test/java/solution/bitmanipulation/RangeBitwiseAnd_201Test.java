package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangeBitwiseAnd_201Test {
    private final RangeBitwiseAnd_201 solver = new RangeBitwiseAnd_201();

    @Test public void testBasic() {
        assertEquals(4, solver.rangeBitwiseAnd(5, 7));
    }

    @Test public void testZero() {
        assertEquals(0, solver.rangeBitwiseAnd(0, 1));
    }

    @Test public void testEqual() {
        assertEquals(15, solver.rangeBitwiseAnd(15, 15));
    }

    @Test public void testLargeRange() {
        assertEquals(0, solver.rangeBitwiseAnd(1, 2147483647));
    }

    @Test public void testSmallRange() {
        assertEquals(12, solver.rangeBitwiseAnd(12, 13));
    }
}
