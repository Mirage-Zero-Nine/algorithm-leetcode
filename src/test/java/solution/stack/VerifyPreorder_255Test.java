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
        // right-skewed BST preorder
        assertTrue(solver.verifyPreorder(new int[]{1, 2, 3, 4, 5}));
    }

    @Test public void testDecreasing() {
        // left-skewed BST preorder
        assertTrue(solver.verifyPreorder(new int[]{5, 4, 3, 2, 1}));
    }

    @Test public void testEmpty() {
        assertTrue(solver.verifyPreorder(new int[]{}));
    }
}
