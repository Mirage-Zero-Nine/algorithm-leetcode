package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class PathSum_113Test {

    private final PathSum_113 test = new PathSum_113();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7); root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13); root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        List<List<Integer>> result = test.pathSum(root, 22);
        assertEquals(1, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.pathSum(null, 1).size());
        assertEquals(0, test.pathSum(new TreeNode(1), 2).size());
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, test.pathSum(root, 3).size());
    }

    @Test
    public void testSingleNodeMatchesSum() {
        List<List<Integer>> result = test.pathSum(new TreeNode(5), 5);
        assertEquals(1, result.size());
        assertEquals(List.of(5), result.get(0));
    }

    @Test
    public void testMultiplePaths() {
        // Tree:     1
        //         2   2
        //        3     3
        // Paths: 1->2->3=6, 1->2->3=6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.right.right = new TreeNode(3);
        assertEquals(2, test.pathSum(root, 6).size());
    }

    @Test
    public void testNoMatchingPath() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(0, test.pathSum(root, 100).size());
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(-3);
        List<List<Integer>> result = test.pathSum(root, -5);
        assertEquals(1, result.size());
        assertEquals(List.of(-2, -3), result.get(0));
    }

    @Test
    public void testZeroSum() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1); root.right = new TreeNode(-1);
        // Path 0->-1 = -1, path 0->1 = 1, neither equals 0
        assertEquals(0, test.pathSum(root, 0).size());
    }

    @Test
    public void testPathContentCorrect() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        List<List<Integer>> result = test.pathSum(root, 6);
        assertEquals(1, result.size());
        assertEquals(List.of(1, 2, 3), result.get(0));
    }

    @Test
    public void testGiantTree() {
        // Build a left-skewed tree of depth 10: values 1,1,1,...,1
        // Sum of path = 10
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 1; i < 10; i++) {
            cur.left = new TreeNode(1);
            cur = cur.left;
        }
        List<List<Integer>> result = test.pathSum(root, 10);
        assertEquals(1, result.size());
        assertEquals(10, result.get(0).size());
        // No path sums to 5
        assertEquals(0, test.pathSum(root, 5).size());
    }

    @Test
    public void testNullTree() {
        assertEquals(List.of(), test.pathSum(null, 0));
    }

    @Test
    public void testSingleNodeNotEqualTarget() {
        assertEquals(0, test.pathSum(new TreeNode(3), 7).size());
    }

    @Test
    public void testInternalNodeSumNotReturned() {
        // Tree:    1
        //        2   3
        // Target=3: path 1->2=3 is root-to-leaf, but internal node 1 alone (sum=1!=3) should not count
        // Only 1->2 sums to 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        List<List<Integer>> result = test.pathSum(root, 3);
        // 1->2=3 is valid, 1->3=4 is not
        assertEquals(1, result.size());
        assertEquals(List.of(1, 2), result.get(0));
    }

    @Test
    public void testTargetZeroWithPositivesAndNegatives() {
        // Tree:      1
        //         2     -3
        //       -3       2
        // Paths: 1->2->-3=0, 1->-3->2=0
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-3);
        root.right.right = new TreeNode(2);
        List<List<Integer>> result = test.pathSum(root, 0);
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(List.of(1, 2, -3), List.of(1, -3, 2));
        assertEquals(expected, actual);
    }

    @Test
    public void testLongChain100Nodes() {
        // Left-skewed chain of 100 nodes, each value=1, target=100
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 1; i < 100; i++) {
            cur.left = new TreeNode(1);
            cur = cur.left;
        }
        List<List<Integer>> result = test.pathSum(root, 100);
        assertEquals(1, result.size());
        assertEquals(100, result.get(0).size());
        // Every element is 1
        assertTrue(result.get(0).stream().allMatch(v -> v == 1));
    }

    @Test
    public void testDuplicateValuesMultiplePaths() {
        // Tree:      5
        //         5     5
        // target=10: both paths 5->5=10
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        List<List<Integer>> result = test.pathSum(root, 10);
        assertEquals(2, result.size());
        for (List<Integer> path : result) {
            assertEquals(List.of(5, 5), path);
        }
    }

    @Test
    public void testEveryReturnedPathSumsToTarget() {
        // Property test: all returned paths must sum exactly to target
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        int target = 22;
        List<List<Integer>> result = test.pathSum(root, target);
        for (List<Integer> path : result) {
            assertEquals(target, path.stream().mapToInt(Integer::intValue).sum());
            // Every path starts at root
            assertEquals(5, path.get(0));
        }
    }

    @Test
    public void testNegativeValuesInPath() {
        // Tree:     10
        //        -5    -3
        //       8       6
        // Paths: 10->-5->8=13, 10->-3->6=13
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(-5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(8);
        root.right.right = new TreeNode(6);
        List<List<Integer>> result = test.pathSum(root, 13);
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(List.of(10, -5, 8), List.of(10, -3, 6));
        assertEquals(expected, actual);
    }

    @Test
    public void testTargetNotReachable() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertEquals(0, test.pathSum(root, 999).size());
    }
}
