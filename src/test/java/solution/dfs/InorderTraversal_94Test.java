package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InorderTraversal_94Test {

    private final InorderTraversal_94 test = new InorderTraversal_94();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2); root.right.left = new TreeNode(3);
        assertEquals(List.of(1, 3, 2), test.inorderTraversal(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.inorderTraversal(null));
        assertEquals(List.of(1), test.inorderTraversal(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), test.inorderTraversal(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        assertEquals(List.of(1, 2, 3), test.inorderTraversal(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.inorderTraversal(root));
    }

    @Test
    public void testTwoNodesLeft() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertEquals(List.of(1, 2), test.inorderTraversal(root));
    }

    @Test
    public void testTwoNodesRight() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        assertEquals(List.of(1, 2), test.inorderTraversal(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1); root.right = new TreeNode(1);
        assertEquals(List.of(-1, 0, 1), test.inorderTraversal(root));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        assertEquals(List.of(1, 1, 1), test.inorderTraversal(root));
    }

    @Test
    public void testGiantTree() {
        // Build a left-skewed tree with 50 nodes: 50, 49, ..., 1
        TreeNode root = new TreeNode(50);
        TreeNode cur = root;
        for (int i = 49; i >= 1; i--) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        List<Integer> result = test.inorderTraversal(root);
        assertEquals(50, result.size());
        for (int i = 0; i < 50; i++) {
            assertEquals(i + 1, result.get(i));
        }
    }

    @Test
    public void testLeftSkewedChainReversed() {
        // Left-skewed: 5->4->3->2->1, inorder should be [1,2,3,4,5]
        TreeNode root = new TreeNode(5);
        TreeNode cur = root;
        for (int i = 4; i >= 1; i--) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertEquals(List.of(1, 2, 3, 4, 5), test.inorderTraversal(root));
    }

    @Test
    public void testRightSkewedChainOriginalOrder() {
        // Right-skewed: 1->2->3->4->5, inorder should be [1,2,3,4,5]
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 5; i++) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        assertEquals(List.of(1, 2, 3, 4, 5), test.inorderTraversal(root));
    }

    @Test
    public void testBSTPropertySorted() {
        // BST: inorder traversal must produce sorted ascending order
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5); root.right = new TreeNode(15);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12); root.right.right = new TreeNode(20);
        List<Integer> result = test.inorderTraversal(root);
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i) < result.get(i + 1), "Inorder of BST must be sorted");
        }
    }

    @Test
    public void testPerfectTreeDepth3() {
        //        4
        //      /   \
        //     2     6
        //    / \   / \
        //   1   3 5   7
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        List<Integer> result = test.inorderTraversal(root);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), result);
        assertEquals(7, result.size());
    }

    @Test
    public void testPropertySizeMatchesNodeCount() {
        // 15-node perfect tree
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4); root.right = new TreeNode(12);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(10); root.right.right = new TreeNode(14);
        root.left.left.left = new TreeNode(1); root.left.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(5); root.left.right.right = new TreeNode(7);
        root.right.left.left = new TreeNode(9); root.right.left.right = new TreeNode(11);
        root.right.right.left = new TreeNode(13); root.right.right.right = new TreeNode(15);
        assertEquals(15, test.inorderTraversal(root).size());
    }

    @Test
    public void testLargeNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-100); root.right = new TreeNode(100);
        root.left.left = new TreeNode(Integer.MIN_VALUE);
        root.right.right = new TreeNode(Integer.MAX_VALUE);
        List<Integer> result = test.inorderTraversal(root);
        assertEquals(List.of(Integer.MIN_VALUE, -100, 0, 100, Integer.MAX_VALUE), result);
    }

    @Test
    public void testTreeWithAllDuplicates() {
        // All nodes have same value, size should still be correct
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5); root.right = new TreeNode(5);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(5);
        List<Integer> result = test.inorderTraversal(root);
        assertEquals(List.of(5, 5, 5, 5, 5), result);
    }

    @Test
    public void testLargeTree200Nodes() {
        // Right-skewed tree with 200 nodes
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 200; i++) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        List<Integer> result = test.inorderTraversal(root);
        assertEquals(200, result.size());
        for (int i = 0; i < 200; i++) {
            assertEquals(i + 1, result.get(i));
        }
    }
}
