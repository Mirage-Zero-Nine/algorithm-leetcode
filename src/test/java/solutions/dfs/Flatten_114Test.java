package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class Flatten_114Test {

    private final Flatten_114 test = new Flatten_114();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(5);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        test.flatten(root);
        assertEquals(1, root.val);
        assertEquals(2, root.right.val);
        assertNull(root.left);
    }

    @Test
    public void testEdgeCases() {
        test.flatten(null);
        TreeNode root = new TreeNode(1);
        test.flatten(root);
        assertEquals(1, root.val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        test.flatten(root);
        assertEquals(2, root.right.val);
        assertEquals(3, root.right.right.val);
    }

    @Test
    public void testFullPreorderSequence() {
        // [1,2,5,3,4,null,6] -> flattened: 1->2->3->4->5->6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(5);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        test.flatten(root);
        int[] expected = {1, 2, 3, 4, 5, 6};
        TreeNode cur = root;
        for (int val : expected) {
            assertEquals(val, cur.val);
            assertNull(cur.left);
            cur = cur.right;
        }
        assertNull(cur);
    }

    @Test
    public void testOnlyLeftChildren() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        test.flatten(root);
        assertEquals(1, root.val);
        assertEquals(2, root.right.val);
        assertEquals(3, root.right.right.val);
        assertNull(root.left);
    }

    @Test
    public void testOnlyRightChildren() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        test.flatten(root);
        assertEquals(1, root.val);
        assertEquals(2, root.right.val);
        assertEquals(3, root.right.right.val);
    }

    @Test
    public void testWithStackMethod() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(5);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        test.flattenWithStack(root);
        int[] expected = {1, 2, 3, 4, 5, 6};
        TreeNode cur = root;
        for (int val : expected) {
            assertEquals(val, cur.val);
            assertNull(cur.left);
            cur = cur.right;
        }
    }

    @Test
    public void testWithStackSingleNode() {
        TreeNode root = new TreeNode(42);
        test.flattenWithStack(root);
        assertEquals(42, root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    public void testWithStackNull() {
        assertDoesNotThrow(() -> test.flattenWithStack(null));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2); root.right = new TreeNode(-3);
        test.flatten(root);
        assertEquals(-1, root.val);
        assertEquals(-2, root.right.val);
        assertEquals(-3, root.right.right.val);
    }

    @Test
    public void testGiantTree() {
        // Build a balanced tree of depth 10 and flatten it
        TreeNode root = buildTree(1, 10);
        test.flatten(root);
        // Verify it's a right-skewed linked list with no left children
        TreeNode cur = root;
        int count = 0;
        while (cur != null) {
            assertNull(cur.left);
            cur = cur.right;
            count++;
        }
        assertEquals(1023, count); // 2^10 - 1
    }

    private TreeNode buildTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildTree(val * 2, depth - 1);
        node.right = buildTree(val * 2 + 1, depth - 1);
        return node;
    }
}
