package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

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
    @Test public void testPrivateConstantSpaceApproachViaContractCases() throws Exception {
        Method method = VerifyPreorder_255.class.getDeclaredMethod("constantSpace", int[].class);
        method.setAccessible(true);
        int[][] cases = {{}, {1}, {2, 1}, {1, 2}, {5, 2, 1, 3, 6},
                {5, 2, 6, 1, 3}, {10, 5, 2, 7, 15, 12, 20},
                {8, 5, 1, 7, 10, 12}, {8, 5, 9, 7}, {0, -1, -2, 1},
                {Integer.MIN_VALUE, Integer.MAX_VALUE}};
        boolean[] expected = {true, true, true, true, true, false, true,
                true, false, true, true};
        for (int i = 0; i < cases.length; i++) {
            Object actual = method.invoke(solver, (Object) cases[i].clone());
            assertEquals(expected[i], actual);
        }
    }
    @Test public void testValidBalanced() { assertTrue(solver.verifyPreorder(new int[]{8,4,2,6,12,10,14})); }
    @Test public void testInvalidAncestorLowerBound() { assertFalse(solver.verifyPreorder(new int[]{8,4,2,10,6})); }
    @Test public void testValidNegativeTree() { assertTrue(solver.verifyPreorder(new int[]{0,-3,-5,-1,4,2,6})); }
    @Test public void testRootRightOnlyValid() { assertTrue(solver.verifyPreorder(new int[]{5,7})); }
    @Test public void testValidRightChain() { assertTrue(solver.verifyPreorder(new int[]{-3,-2,-1,0,1})); }
    @Test public void testInvalidDeepRightInLeft() { assertFalse(solver.verifyPreorder(new int[]{10,5,1,7,12,6})); }
    @Test public void testValidTwoBranches() { assertTrue(solver.verifyPreorder(new int[]{7,3,1,5,11,9,13})); }
    @Test public void testInvalidAfterSwitch() { assertFalse(solver.verifyPreorder(new int[]{7,3,5,1})); }
    @Test public void testIntegerBoundsValid() { assertTrue(solver.verifyPreorder(new int[]{0,Integer.MIN_VALUE,Integer.MAX_VALUE})); }
    @Test public void testRepeatedCall() { solver.verifyPreorder(new int[]{2,1}); assertFalse(solver.verifyPreorder(new int[]{2,3,1})); }
}
