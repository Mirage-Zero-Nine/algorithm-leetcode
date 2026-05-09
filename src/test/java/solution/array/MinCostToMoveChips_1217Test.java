package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinCostToMoveChips_1217Test {
    private final MinCostToMoveChips_1217 solution = new MinCostToMoveChips_1217();

    @Test
    void testBasic() {
        assertEquals(1, solution.minCostToMoveChips(new int[]{1, 2, 3}));
    }

    @Test
    void testAllEven() {
        assertEquals(2, solution.minCostToMoveChips(new int[]{2, 2, 2, 3, 3}));
    }

    @Test
    void testAllOdd() {
        assertEquals(1, solution.minCostToMoveChips(new int[]{1, 1000000000}));
    }

    @Test
    void testSingleChip() {
        assertEquals(0, solution.minCostToMoveChips(new int[]{1}));
    }

    @Test
    void testMixed() {
        assertEquals(2, solution.minCostToMoveChips(new int[]{2, 3, 4, 5}));
    }
}
