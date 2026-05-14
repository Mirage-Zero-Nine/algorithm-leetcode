package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifyPreorder_255Test {
    private final VerifyPreorder_255 solver = new VerifyPreorder_255();

    @Test public void testValid() {
        assertTrue(solver.verifyPreorder(new int[]{5, 2, 1, 3, 6}));
    }

    @Test public void testInvalid() {
        assertFalse(solver.verifyPreorder(new int[]{5, 2, 6, 1, 3}));
    }

    @Test public void testSingleElement() {
        assertTrue(solver.verifyPreorder(new int[]{1}));
    }

    @Test public void testIncreasing() {
        assertTrue(solver.verifyPreorder(new int[]{1, 2, 3, 4, 5}));
    }

    @Test public void testDecreasing() {
        assertTrue(solver.verifyPreorder(new int[]{5, 4, 3, 2, 1}));
    }

    @Test public void testEmpty() {
        assertTrue(solver.verifyPreorder(new int[]{}));
    }

    // Additional happy cases
    @Test public void testValidLarger() {
        assertTrue(solver.verifyPreorder(new int[]{10, 5, 2, 7, 15, 12, 20}));
    }

    @Test public void testTwoElements() {
        assertTrue(solver.verifyPreorder(new int[]{2, 1}));
    }

    // Negative case: violates BST property
    @Test public void testInvalidRightSubtree() {
        assertFalse(solver.verifyPreorder(new int[]{5, 3, 1, 6, 2}));
    }

    // Edge case: root with only right child
    @Test public void testRootRightOnly() {
        assertTrue(solver.verifyPreorder(new int[]{1, 3}));
    }

    // Giant test case: valid increasing sequence (right-skewed BST)
    @Test public void testGiant() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i + 1;
        assertTrue(solver.verifyPreorder(arr));
    }
}
