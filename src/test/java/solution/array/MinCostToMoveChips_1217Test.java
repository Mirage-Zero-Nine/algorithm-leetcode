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

    @Test
    void testAllOddPositions() {
        assertEquals(0, solution.minCostToMoveChips(new int[]{1, 3, 5, 7}));
    }

    @Test
    void testAllEvenPositions() {
        assertEquals(0, solution.minCostToMoveChips(new int[]{2, 4, 6, 8}));
    }

    @Test
    void testTwoChips() {
        assertEquals(1, solution.minCostToMoveChips(new int[]{1, 2}));
    }

    @Test
    void testLargeEvenPositions() {
        assertEquals(1, solution.minCostToMoveChips(new int[]{2, 4, 6, 8, 10, 3}));
    }

    @Test
    void testGiantArray() {
        int[] chips = new int[100000];
        for (int i = 0; i < 100000; i++) chips[i] = i + 1;
        // 50000 odd, 50000 even
        assertEquals(50000, solution.minCostToMoveChips(chips));
    }
}
