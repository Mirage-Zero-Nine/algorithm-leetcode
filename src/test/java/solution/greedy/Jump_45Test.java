package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jump_45Test {
    private final Jump_45 solver = new Jump_45();

    @Test public void testBasic() {
        assertEquals(2, solver.jump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test public void testSingle() {
        assertEquals(0, solver.jump(new int[]{0}));
    }

    @Test public void testTwoElements() {
        assertEquals(1, solver.jump(new int[]{1, 1}));
    }

    @Test public void testLongest() {
        assertEquals(1, solver.jump(new int[]{10, 1, 1, 1, 1}));
    }

    @Test public void testDecreasing() {
        assertEquals(2, solver.jump(new int[]{2, 3, 1, 1, 1}));
    }
}
