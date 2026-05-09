package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumber_137Test {
    private final SingleNumber_137 solver = new SingleNumber_137();

    @Test public void testBasic() {
        assertEquals(3, solver.singleNumber(new int[]{2, 2, 3, 2}));
    }

    @Test public void testOneLonely() {
        assertEquals(99, solver.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    @Test public void testSingleElement() {
        assertEquals(7, solver.singleNumber(new int[]{7}));
    }

    @Test public void testNegative() {
        assertEquals(-5, solver.singleNumber(new int[]{3, 3, 3, -5}));
    }

    @Test public void testLargerArray() {
        assertEquals(4, solver.singleNumber(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4}));
    }
}
