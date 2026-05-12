package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MCTFromLeafValues_1130Test {
    private final MCTFromLeafValues_1130 solver = new MCTFromLeafValues_1130();

    @Test public void testExample() {
        assertEquals(32, solver.mctFromLeafValues(new int[]{6, 2, 4}));
    }

    @Test public void testTwoElements() {
        assertEquals(6, solver.mctFromLeafValues(new int[]{2, 3}));
    }

    @Test public void testFourElements() {
        // [4,11] -> non-leaf nodes sum minimized
        assertEquals(44, solver.mctFromLeafValues(new int[]{4, 11}));
    }

    @Test public void testIncreasing() {
        // [1,2,3] -> combine 1*2 first, then 2*3 = 2+6=8
        assertEquals(8, solver.mctFromLeafValues(new int[]{1, 2, 3}));
    }

    @Test public void testDecreasing() {
        // [3,2,1] -> combine 2*1 first=2, then 3*2=6, total=8
        assertEquals(8, solver.mctFromLeafValues(new int[]{3, 2, 1}));
    }
}
