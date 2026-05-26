package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LowestCommonAncestor_235Test {

    private final LowestCommonAncestor_235 test = new LowestCommonAncestor_235();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(6);
        TreeNode n2 = new TreeNode(2); TreeNode n8 = new TreeNode(8);
        root.left = n2; root.right = n8;
        n2.left = new TreeNode(0); n2.right = new TreeNode(4);
        assertEquals(6, test.lowestCommonAncestor(root, n2, n8).val);
    }

    @Test
    public void testEdgeCases() {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        root.left = n1;
        assertEquals(2, test.lowestCommonAncestor(root, root, n1).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(6);
        TreeNode n2 = new TreeNode(2); TreeNode n4 = new TreeNode(4);
        root.left = n2; n2.right = n4;
        assertEquals(2, test.lowestCommonAncestor(root, n2, n4).val);
    }

    @Test
    public void testBothNodesInLeftSubtree() {
        TreeNode root = new TreeNode(6);
        TreeNode n2 = new TreeNode(2); TreeNode n8 = new TreeNode(8);
        root.left = n2; root.right = n8;
        TreeNode n0 = new TreeNode(0); TreeNode n4 = new TreeNode(4);
        n2.left = n0; n2.right = n4;
        assertEquals(2, test.lowestCommonAncestor(root, n0, n4).val);
    }

    @Test
    public void testBothNodesInRightSubtree() {
        TreeNode root = new TreeNode(6);
        TreeNode n8 = new TreeNode(8);
        root.right = n8;
        TreeNode n7 = new TreeNode(7); TreeNode n9 = new TreeNode(9);
        n8.left = n7; n8.right = n9;
        assertEquals(8, test.lowestCommonAncestor(root, n7, n9).val);
    }

    @Test
    public void testOneNodeIsRoot() {
        TreeNode root = new TreeNode(5);
        TreeNode n3 = new TreeNode(3); TreeNode n7 = new TreeNode(7);
        root.left = n3; root.right = n7;
        assertEquals(5, test.lowestCommonAncestor(root, root, n7).val);
    }

    @Test
    public void testOneNodeIsAncestorOfOther() {
        TreeNode root = new TreeNode(10);
        TreeNode n5 = new TreeNode(5); TreeNode n15 = new TreeNode(15);
        root.left = n5; root.right = n15;
        TreeNode n3 = new TreeNode(3);
        n5.left = n3;
        assertEquals(5, test.lowestCommonAncestor(root, n5, n3).val);
    }

    @Test
    public void testSameNode() {
        TreeNode root = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        root.left = n2;
        assertEquals(2, test.lowestCommonAncestor(root, n2, n2).val);
    }

    @Test
    public void testTwoNodeTree() {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        root.left = n1;
        assertEquals(2, test.lowestCommonAncestor(root, n1, root).val);
    }

    @Test
    public void testDeepLeftAndDeepRight() {
        // BST: 20 -> 10, 30 -> 5, 15, 25, 35
        TreeNode root = new TreeNode(20);
        TreeNode n10 = new TreeNode(10); TreeNode n30 = new TreeNode(30);
        root.left = n10; root.right = n30;
        TreeNode n5 = new TreeNode(5); TreeNode n15 = new TreeNode(15);
        n10.left = n5; n10.right = n15;
        TreeNode n25 = new TreeNode(25); TreeNode n35 = new TreeNode(35);
        n30.left = n25; n30.right = n35;
        assertEquals(20, test.lowestCommonAncestor(root, n5, n35).val);
    }

    @Test
    public void testGiantBST() {
        // Build a balanced BST with values 1..127 (depth 7)
        TreeNode root = buildBST(1, 127);
        // LCA of 1 and 127 should be root (64)
        TreeNode n1 = findNode(root, 1);
        TreeNode n127 = findNode(root, 127);
        assertEquals(64, test.lowestCommonAncestor(root, n1, n127).val);
    }

    private TreeNode buildBST(int lo, int hi) {
        if (lo > hi) return null;
        int mid = (lo + hi) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = buildBST(lo, mid - 1);
        node.right = buildBST(mid + 1, hi);
        return node;
    }

    private TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (val < root.val) return findNode(root.left, val);
        return findNode(root.right, val);
    }
}
