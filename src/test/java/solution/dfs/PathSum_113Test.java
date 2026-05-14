package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
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
}
