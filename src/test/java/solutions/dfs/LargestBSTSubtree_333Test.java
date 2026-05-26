package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LargestBSTSubtree_333Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5); root.right = new TreeNode(15);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);
        assertEquals(3, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new LargestBSTSubtree_333().largestBSTSubtree(null));
        assertEquals(1, new LargestBSTSubtree_333().largestBSTSubtree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        assertEquals(7, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testAllLeftSubtreeIsBST() {
        // Root violates BST but left subtree is valid BST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2); root.right = new TreeNode(1); // right < root violates
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3); // left subtree: 2 with left=1, right=3 -> but 3==root.val
        // Actually left subtree 2,1,3 is valid BST of size 3
        // But root 3 has right=1 which is not BST
        assertEquals(3, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testLinearLeftChain() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        assertEquals(3, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testLinearRightChain() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testNoSubtreeIsBSTBeyondLeaf() {
        // Every internal node violates BST property
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5); root.right = new TreeNode(0);
        root.left.left = new TreeNode(10); root.left.right = new TreeNode(0);
        // Each leaf is BST of size 1, internal nodes violate
        assertEquals(1, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertEquals(2, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testTwoNodesInvalid() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); // left > root, not BST
        assertEquals(1, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testGiantValidBST() {
        // Full BST of 15 nodes
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4); root.right = new TreeNode(12);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(10); root.right.right = new TreeNode(14);
        root.left.left.left = new TreeNode(1); root.left.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(5); root.left.right.right = new TreeNode(7);
        root.right.left.left = new TreeNode(9); root.right.left.right = new TreeNode(11);
        root.right.right.left = new TreeNode(13); root.right.right.right = new TreeNode(15);
        assertEquals(15, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }
}
