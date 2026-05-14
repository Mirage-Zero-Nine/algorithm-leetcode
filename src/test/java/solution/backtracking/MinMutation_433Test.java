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

    @Test
    void testThreeSteps() {
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        assertEquals(3, solution.minMutation("AAAAACCC", "AACCCCCC", bank));
    }

    @Test
    void testNullStart() {
        String[] bank = {"AACCGGTA"};
        assertEquals(0, solution.minMutation(null, "AACCGGTA", bank));
    }

    @Test
    void testEmptyStart() {
        String[] bank = {"AACCGGTA"};
        assertEquals(0, solution.minMutation("", "AACCGGTA", bank));
    }

    @Test
    void testDifferentLengths() {
        String[] bank = {"AACCGGTA"};
        assertEquals(-1, solution.minMutation("AACCGG", "AACCGGTA", bank));
    }

    @Test
    void testEndNotInBank() {
        String[] bank = {"AACCGGTA", "AACCGGTC"};
        assertEquals(-1, solution.minMutation("AACCGGTT", "AACCGGTG", bank));
    }

    @Test
    void testMultiplePaths() {
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA", "AAACGCTA"};
        int result = solution.minMutation("AACCGGTT", "AAACGCTA", bank);
        assertTrue(result > 0);
    }

    @Test
    void testGiantBank() {
        // Large bank with many valid mutations
        String[] bank = new String[100];
        StringBuilder sb = new StringBuilder("AACCGGTT");
        bank[0] = "AACCGGTA";
        bank[1] = "AACCGGCA";
        for (int i = 2; i < 100; i++) {
            char[] gene = "AACCGGTT".toCharArray();
            gene[i % 8] = "ACGT".charAt(i % 4);
            bank[i] = new String(gene);
        }
        int result = solution.minMutation("AACCGGTT", "AACCGGTA", bank);
        assertEquals(1, result);
    }
}
