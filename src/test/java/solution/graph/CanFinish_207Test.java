package solution.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:11
 * Created with IntelliJ IDEA
 */

public class CanFinish_207Test {

    private final CanFinish_207 test = new CanFinish_207();

    @Test
    public void test() {
        int[][] prerequisites = new int[][]{{1, 0}};
        assertTrue(test.canFinish(2, prerequisites));
        assertTrue(test.intArray(2, prerequisites));

        prerequisites = new int[][]{{1, 0}, {0, 1}};
        assertFalse(test.canFinish(2, prerequisites));
        assertFalse(test.intArray(2, prerequisites));
    }

    @Test
    public void testHappyCases() {
        assertTrue(test.canFinish(3, new int[][]{{1, 0}, {2, 1}}));
        assertTrue(test.intArray(3, new int[][]{{1, 0}, {2, 1}}));
        assertTrue(test.canFinish(4, new int[][]{}));
        assertTrue(test.intArray(4, new int[][]{}));
        assertTrue(test.canFinish(5, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3}}));
        assertTrue(test.intArray(5, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}, {4, 3}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canFinish(1, new int[][]{{0, 0}}));
        assertFalse(test.intArray(1, new int[][]{{0, 0}}));
        assertFalse(test.canFinish(3, new int[][]{{1, 0}, {2, 1}, {0, 2}}));
        assertFalse(test.intArray(3, new int[][]{{1, 0}, {2, 1}, {0, 2}}));
    }

    @Test
    public void testGiantCase() {
        int[][] prerequisites = new int[199][2];
        for (int i = 1; i < 200; i++) {
            prerequisites[i - 1] = new int[]{i, i - 1};
        }
        assertTrue(test.canFinish(200, prerequisites));
        assertTrue(test.intArray(200, prerequisites));
    }

    @Test
    public void testSingleCourseNoPrereq() {
        assertTrue(test.canFinish(1, new int[][]{}));
        assertTrue(test.intArray(1, new int[][]{}));
    }

    @Test
    public void testDisconnectedGraph() {
        // Two separate chains: 0->1, 2->3
        assertTrue(test.canFinish(4, new int[][]{{1, 0}, {3, 2}}));
        assertTrue(test.intArray(4, new int[][]{{1, 0}, {3, 2}}));
    }

    @Test
    public void testDiamondShape() {
        // 0->1, 0->2, 1->3, 2->3
        assertTrue(test.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
        assertTrue(test.intArray(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }

    @Test
    public void testLargeCycle() {
        // 0->1->2->3->0 cycle
        assertFalse(test.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {0, 3}}));
        assertFalse(test.intArray(4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {0, 3}}));
    }

    @Test
    public void testMultiplePrereqsForOneCourse() {
        assertTrue(test.canFinish(4, new int[][]{{3, 0}, {3, 1}, {3, 2}}));
        assertTrue(test.intArray(4, new int[][]{{3, 0}, {3, 1}, {3, 2}}));
    }

    @Test
    public void testGiantCycleDetection() {
        int n = 500;
        int[][] prerequisites = new int[n][2];
        for (int i = 0; i < n - 1; i++) {
            prerequisites[i] = new int[]{i + 1, i};
        }
        prerequisites[n - 1] = new int[]{0, n - 1}; // creates cycle
        assertFalse(test.canFinish(n, prerequisites));
    }
}
