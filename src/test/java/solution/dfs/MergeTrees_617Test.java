package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MergeTrees_617Test {

    private final MergeTrees_617 test = new MergeTrees_617();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(3); t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);
        TreeNode t2 = new TreeNode(2); t2.left = new TreeNode(1); t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4); t2.right.right = new TreeNode(7);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(3, result.val);
        assertEquals(4, result.left.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.mergeTrees(null, null));
        assertEquals(1, test.mergeTrees(new TreeNode(1), null).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(4); t2.left = new TreeNode(5); t2.right = new TreeNode(6);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(5, result.val);
    }

    @Test
    public void testSecondTreeNull() {
        TreeNode t1 = new TreeNode(5);
        t1.left = new TreeNode(3);
        TreeNode result = test.mergeTrees(t1, null);
        assertEquals(5, result.val);
        assertEquals(3, result.left.val);
    }

    @Test
    public void testFirstTreeNull() {
        TreeNode t2 = new TreeNode(7);
        t2.right = new TreeNode(9);
        TreeNode result = test.mergeTrees(null, t2);
        assertEquals(7, result.val);
        assertEquals(9, result.right.val);
    }

    @Test
    public void testBothSingleNodes() {
        TreeNode result = test.mergeTrees(new TreeNode(3), new TreeNode(4));
        assertEquals(7, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    public void testNonOverlappingStructures() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
        t2.right = new TreeNode(3);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(2, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testNegativeValues() {
        TreeNode t1 = new TreeNode(-1); t1.left = new TreeNode(-2);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(0, result.val);
        assertEquals(0, result.left.val);
    }

    @Test
    public void testDeepMerge() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.left.left = new TreeNode(3);
        t1.left.left.left = new TreeNode(4);
        TreeNode t2 = new TreeNode(10);
        t2.left = new TreeNode(20);
        t2.left.left = new TreeNode(30);
        t2.left.left.left = new TreeNode(40);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(11, result.val);
        assertEquals(22, result.left.val);
        assertEquals(33, result.left.left.val);
        assertEquals(44, result.left.left.left.val);
    }

    @Test
    public void testMergeWithZeroValues() {
        TreeNode t1 = new TreeNode(0); t1.left = new TreeNode(0); t1.right = new TreeNode(0);
        TreeNode t2 = new TreeNode(0); t2.left = new TreeNode(0); t2.right = new TreeNode(0);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(0, result.val);
        assertEquals(0, result.left.val);
        assertEquals(0, result.right.val);
    }

    @Test
    public void testGiantTree() {
        // Merge two balanced trees of depth 8
        TreeNode t1 = buildTree(1, 8);
        TreeNode t2 = buildTree(1, 8);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(2, result.val);
        assertEquals(4, result.left.val);
        assertEquals(6, result.right.val);
    }

    private TreeNode buildTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildTree(val * 2, depth - 1);
        node.right = buildTree(val * 2 + 1, depth - 1);
        return node;
    }
}
