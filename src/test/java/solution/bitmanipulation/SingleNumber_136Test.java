package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumber_136Test {
    private final SingleNumber_136 solver = new SingleNumber_136();

    @Test public void testBasic() {
        assertEquals(1, solver.singleNumber(new int[]{2, 2, 1}));
    }

    @Test public void testFourPairs() {
        assertEquals(4, solver.singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    @Test public void testSingleElement() {
        assertEquals(1, solver.singleNumber(new int[]{1}));
    }

    @Test public void testNegative() {
        assertEquals(-5, solver.singleNumber(new int[]{-5, 1, 1, 2, 2}));
    }

    @Test public void testLargerArray() {
        assertEquals(7, solver.singleNumber(new int[]{3, 5, 3, 5, 7, 9, 9}));
    }
}
