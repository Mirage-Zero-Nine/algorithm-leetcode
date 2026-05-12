package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanJump_55Test {
    private final CanJump_55 solver = new CanJump_55();

    @Test public void testBasic() {
        assertTrue(solver.canJump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test public void testStuck() {
        assertFalse(solver.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test public void testSingle() {
        assertTrue(solver.canJump(new int[]{0}));
    }

    @Test public void testFromEndBasic() {
        assertTrue(solver.canJumpFromEnd(new int[]{2, 3, 1, 1, 4}));
    }

    @Test public void testFromEndStuck() {
        assertFalse(solver.canJumpFromEnd(new int[]{3, 2, 1, 0, 4}));
    }
}
