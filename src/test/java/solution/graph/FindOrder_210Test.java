package solution.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FindOrder_210Test {

    private final FindOrder_210 test = new FindOrder_210();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 1}, test.findOrder(2, new int[][]{{1, 0}}));
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, result.length);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{}, test.findOrder(2, new int[][]{{1, 0}, {0, 1}}));
        assertArrayEquals(new int[]{0}, test.findOrder(1, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        int[] result = test.findOrder(5, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}});
        assertEquals(5, result.length);
        assertEquals(0, result[0]);
        assertEquals(4, result[4]);
    }

    @Test
    public void testNoPrerequisites() {
        int[] result = test.findOrder(3, new int[][]{});
        assertEquals(3, result.length);
    }

    @Test
    public void testCycleDetection() {
        // 3-node cycle
        assertArrayEquals(new int[]{}, test.findOrder(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
    }

    @Test
    public void testLinearChain() {
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {2, 1}, {3, 2}});
        assertArrayEquals(new int[]{0, 1, 2, 3}, result);
    }

    @Test
    public void testTwoIndependentChains() {
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {3, 2}});
        assertEquals(4, result.length);
        // 0 before 1, 2 before 3
        assertTrue(indexOf(result, 0) < indexOf(result, 1));
        assertTrue(indexOf(result, 2) < indexOf(result, 3));
    }

    @Test
    public void testDiamondDependency() {
        // 0 -> 1, 0 -> 2, 1 -> 3, 2 -> 3
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, result.length);
        assertTrue(indexOf(result, 0) < indexOf(result, 1));
        assertTrue(indexOf(result, 0) < indexOf(result, 2));
        assertTrue(indexOf(result, 1) < indexOf(result, 3));
        assertTrue(indexOf(result, 2) < indexOf(result, 3));
    }

    @Test
    public void testSelfCycle() {
        // Self-loop
        assertArrayEquals(new int[]{}, test.findOrder(2, new int[][]{{0, 0}}));
    }

    @Test
    public void testZeroCourses() {
        assertArrayEquals(new int[]{}, test.findOrder(0, new int[][]{}));
    }

    @Test
    public void testGiantLinearChain() {
        int n = 500;
        int[][] prereqs = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            prereqs[i] = new int[]{i + 1, i};
        }
        int[] result = test.findOrder(n, prereqs);
        assertEquals(n, result.length);
        for (int i = 0; i < n; i++) {
            assertEquals(i, result[i]);
        }
    }

    private int indexOf(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) return i;
        }
        return -1;
    }
}
