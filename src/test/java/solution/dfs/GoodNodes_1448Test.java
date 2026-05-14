package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class GoodNodes_1448Test {

    private final GoodNodes_1448 test = new GoodNodes_1448();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(5);
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.goodNodes(null));
        assertEquals(1, test.goodNodes(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(3); root.right = new TreeNode(4);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(2);
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testAllGood() {
        // Increasing path: all nodes are good
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, test.goodNodes(root));
    }

    @Test
    public void testOnlyRootGood() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        assertEquals(1, test.goodNodes(root));
    }

    @Test
    public void testNegativeNodeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(0);
        // root(-1) is good, left(-2) not good, right(0) is good
        assertEquals(2, test.goodNodes(root));
    }

    @Test
    public void testEqualValues() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        // All equal to max -> all good
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testMixedPaths() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4); root.right = new TreeNode(1);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(2); root.right.right = new TreeNode(6);
        // Good: root(2), left(4), left.right(5), right.left(2), right.right(6) = 5
        assertEquals(5, test.goodNodes(root));
    }

    @Test
    public void testGiantTree() {
        // Complete binary tree with all same values -> all good
        TreeNode[] nodes = new TreeNode[1024];
        for (int i = 1; i <= 1023; i++) nodes[i] = new TreeNode(1);
        for (int i = 1; i <= 511; i++) {
            nodes[i].left = nodes[2 * i];
            nodes[i].right = nodes[2 * i + 1];
        }
        assertEquals(1023, test.goodNodes(nodes[1]));
    }
}
