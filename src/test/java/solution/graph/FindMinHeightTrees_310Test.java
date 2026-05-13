package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

/**
 * @author BorisMirage
 * Time: 2023/04/21 14:11
 * Created with IntelliJ IDEA
 */

public class FindMinHeightTrees_310Test {

    private final FindMinHeightTrees_310 test = new FindMinHeightTrees_310();

    @Test
    public void test1() {
        Set<Integer> expected = Sets.newHashSet(3);
        assertTrue(expected.containsAll(test.findMinHeightTrees(9, test1)));
    }

    @Test
    public void test2() {
        Set<Integer> expected = Sets.newHashSet(3, 2);
        assertTrue(expected.containsAll(test.findMinHeightTrees(8, test2)));
    }

    @Test
    public void test3() {
        Set<Integer> expected = Sets.newHashSet(3, 4);
        assertTrue(expected.containsAll(test.findMinHeightTrees(6, test3)));
    }

    @Test
    public void testSingleNode() {
        assertEquals(List.of(0), test.findMinHeightTrees(1, new int[][]{}));
    }

    @Test
    public void testTwoNodes() {
        List<Integer> result = test.findMinHeightTrees(2, new int[][]{{0, 1}});
        assertTrue(result.contains(0) && result.contains(1));
    }

    @Test
    public void testLinearThreeNodes() {
        // 0-1-2, center is 1
        List<Integer> result = test.findMinHeightTrees(3, new int[][]{{0, 1}, {1, 2}});
        assertEquals(1, result.size());
        assertTrue(result.contains(1));
    }

    @Test
    public void testLinearFourNodes() {
        // 0-1-2-3, centers are 1 and 2
        List<Integer> result = test.findMinHeightTrees(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        assertTrue(result.contains(1) && result.contains(2));
    }

    @Test
    public void testStarGraph() {
        // 0 connected to 1,2,3,4 - center is 0
        List<Integer> result = test.findMinHeightTrees(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}});
        assertEquals(1, result.size());
        assertTrue(result.contains(0));
    }

    @Test
    public void testBalancedTree() {
        // 0-1, 0-2, 1-3, 1-4, 2-5, 2-6 => root 0
        List<Integer> result = test.findMinHeightTrees(7, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}});
        assertEquals(1, result.size());
        assertTrue(result.contains(0));
    }

    @Test
    public void testGiantLinearChain() {
        int n = 100;
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i] = new int[]{i, i + 1};
        }
        List<Integer> result = test.findMinHeightTrees(n, edges);
        // Even chain: centers are 49 and 50
        assertTrue(result.contains(49) && result.contains(50));
    }

    @Test
    public void testGiantOddChain() {
        int n = 101;
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i] = new int[]{i, i + 1};
        }
        List<Integer> result = test.findMinHeightTrees(n, edges);
        assertEquals(1, result.size());
        assertTrue(result.contains(50));
    }

    private final static int[][] test1 = {{0, 1}, {6, 1}, {7, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 8}};
    private final static int[][] test2 = {{0, 1}, {6, 1}, {7, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}};
    private final static int[][] test3 = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
}