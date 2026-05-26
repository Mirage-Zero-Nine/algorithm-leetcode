package solutions.monotonicstack;

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
        assertEquals(44, solver.mctFromLeafValues(new int[]{4, 11}));
    }

    @Test public void testIncreasing() {
        assertEquals(8, solver.mctFromLeafValues(new int[]{1, 2, 3}));
    }

    @Test public void testDecreasing() {
        assertEquals(8, solver.mctFromLeafValues(new int[]{3, 2, 1}));
    }

    // Additional happy cases
    @Test public void testFourLeaves() {
        // [1,2,3,4]: combine 1*2=2, then 2*3=6, then 3*4=12 -> total=20
        assertEquals(20, solver.mctFromLeafValues(new int[]{1, 2, 3, 4}));
    }

    @Test public void testSymmetric() {
        // [2,4,2]: combine 2*4=8 (left), then 4*2=8 -> pick min: 2*4 + 4*2 = not right
        // Actually: stack approach: push MAX, push 2, then 4>2 so pop 2, total+=2*min(MAX,4)=2*4=8, push 4, then 2<4 push 2
        // cleanup: pop 2, total+=2*4=8, total=16
        assertEquals(16, solver.mctFromLeafValues(new int[]{2, 4, 2}));
    }

    // Negative case: two equal elements
    @Test public void testEqualElements() {
        assertEquals(25, solver.mctFromLeafValues(new int[]{5, 5}));
    }

    // Edge cases
    @Test public void testAllOnes() {
        // [1,1,1,1]: combine 1*1=1 three times = 3
        assertEquals(3, solver.mctFromLeafValues(new int[]{1, 1, 1, 1}));
    }

    @Test public void testLargeValues() {
        assertEquals(30, solver.mctFromLeafValues(new int[]{5, 6}));
    }

    // Giant test case
    @Test public void testGiant() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) arr[i] = i + 1;
        int result = solver.mctFromLeafValues(arr);
        assertTrue(result > 0);
    }
}
