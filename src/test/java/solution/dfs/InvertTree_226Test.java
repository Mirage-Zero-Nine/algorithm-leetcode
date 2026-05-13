package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InvertTree_226Test {

    private final InvertTree_226 test = new InvertTree_226();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(7, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.invertTree(null));
        assertEquals(1, test.invertTree(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testFullBinaryTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(9);
        TreeNode result = test.invertTree(root);
        assertEquals(7, result.left.val);
        assertEquals(9, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(2, result.right.val);
        assertEquals(3, result.right.left.val);
        assertEquals(1, result.right.right.val);
    }

    @Test
    public void testLeftOnlyTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertNull(result.left);
        assertEquals(2, result.right.val);
        assertNull(result.right.left);
        assertEquals(3, result.right.right.val);
    }

    @Test
    public void testRightOnlyTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(2, result.left.val);
        assertEquals(3, result.left.left.val);
        assertNull(result.right);
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode result = test.invertTree(root);
        assertNull(result.left);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testInvertTwiceRestoresOriginal() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        TreeNode inverted = test.invertTree(root);
        TreeNode restored = test.invertTree(inverted);
        assertEquals(2, restored.left.val);
        assertEquals(3, restored.right.val);
    }

    @Test
    public void testAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        TreeNode result = test.invertTree(root);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(4, result.right.left.val);
        assertNull(result.right.right);
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2); root.right = new TreeNode(-3);
        TreeNode result = test.invertTree(root);
        assertEquals(-3, result.left.val);
        assertEquals(-2, result.right.val);
    }

    @Test
    public void testGiantTree() {
        // Build a balanced tree of depth 10 and invert it
        TreeNode root = buildBalancedTree(1, 10);
        TreeNode result = test.invertTree(root);
        // After inversion, left child should be what was right (val*2+1) and vice versa
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
    }

    private TreeNode buildBalancedTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildBalancedTree(val * 2, depth - 1);
        node.right = buildBalancedTree(val * 2 + 1, depth - 1);
        return node;
    }
}
