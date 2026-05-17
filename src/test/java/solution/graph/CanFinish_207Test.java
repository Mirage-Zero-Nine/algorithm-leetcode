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

    @Test
    public void testZeroCoursesNoPrereqs() {
        assertTrue(test.canFinish(0, new int[][]{}));
        assertTrue(test.intArray(0, new int[][]{}));
    }

    @Test
    public void testSelfLoop() {
        assertFalse(test.canFinish(1, new int[][]{{0, 0}}));
        assertFalse(test.intArray(1, new int[][]{{0, 0}}));
    }

    @Test
    public void testLinearChain() {
        // a->b->c->d: 0<-1<-2<-3
        assertTrue(test.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}));
        assertTrue(test.intArray(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}));
    }

    @Test
    public void testLongChain100NoCycle() {
        int n = 100;
        int[][] prereqs = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            prereqs[i] = new int[]{i + 1, i};
        }
        assertTrue(test.canFinish(n, prereqs));
        assertTrue(test.intArray(n, prereqs));
    }

    @Test
    public void testLongChainWithCycleAtEnd() {
        int n = 100;
        int[][] prereqs = new int[n][2];
        for (int i = 0; i < n - 1; i++) {
            prereqs[i] = new int[]{i + 1, i};
        }
        prereqs[n - 1] = new int[]{n - 2, n - 1}; // cycle at end: n-1 -> n-2 and n-2 -> n-1
        assertFalse(test.canFinish(n, prereqs));
        assertFalse(test.intArray(n, prereqs));
    }

    @Test
    public void testMultipleDisjointComponentsOneCyclic() {
        // Component 1: 0->1->2 (acyclic), Component 2: 3->4->5->3 (cyclic)
        assertFalse(test.canFinish(6, new int[][]{{1, 0}, {2, 1}, {4, 3}, {5, 4}, {3, 5}}));
        assertFalse(test.intArray(6, new int[][]{{1, 0}, {2, 1}, {4, 3}, {5, 4}, {3, 5}}));
    }

    @Test
    public void testEmptyPrereqsWithManyCourses() {
        assertTrue(test.canFinish(50, new int[][]{}));
        assertTrue(test.intArray(50, new int[][]{}));
    }

    @Test
    public void testDuplicatePrereqEdges() {
        // Duplicate edge [1,0] should not create a false cycle for canFinish (HashMap version)
        assertTrue(test.canFinish(2, new int[][]{{1, 0}, {1, 0}}));
    }

    @Test
    public void testDiamondDAG() {
        // 0->1, 0->2, 1->3, 2->3 (diamond, no cycle)
        assertTrue(test.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
        assertTrue(test.intArray(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }
}
