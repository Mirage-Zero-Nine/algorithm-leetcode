package solution.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinMutation_433Test {
    private final MinMutation_433 solution = new MinMutation_433();

    @Test
    void testBasic() {
        String[] bank = {"AACCGGTA"};
        assertEquals(1, solution.minMutation("AACCGGTT", "AACCGGTA", bank));
    }

    @Test
    void testMultipleSteps() {
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        assertEquals(2, solution.minMutation("AACCGGTT", "AAACGGTA", bank));
    }

    @Test
    void testNoSolution() {
        String[] bank = {"AACCGGTA", "AACCGCTA"};
        assertEquals(-1, solution.minMutation("AACCGGTT", "AAACGGTA", bank));
    }

    @Test
    void testSameStartEnd() {
        String[] bank = {"AACCGGTA"};
        assertEquals(0, solution.minMutation("AACCGGTA", "AACCGGTA", bank));
    }

    @Test
    void testEmptyBank() {
        String[] bank = {};
        assertEquals(-1, solution.minMutation("AACCGGTT", "AACCGGTA", bank));
    }
}
