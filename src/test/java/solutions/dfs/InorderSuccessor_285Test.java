package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InorderSuccessor_285Test {

    private final InorderSuccessor_285 test = new InorderSuccessor_285();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1); TreeNode n3 = new TreeNode(3);
        root.left = n1; root.right = n3;
        assertEquals(2, test.inorderSuccessor(root, n1).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.inorderSuccessor(null, new TreeNode(1)));
        TreeNode root = new TreeNode(1);
        assertNull(test.inorderSuccessor(root, root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        TreeNode n3 = new TreeNode(3); TreeNode n6 = new TreeNode(6);
        root.left = n3; root.right = n6;
        n3.left = new TreeNode(2); n3.right = new TreeNode(4);
        assertEquals(4, test.inorderSuccessor(root, n3).val);
    }

    @Test
    public void testSuccessorOfLeftmostNode() {
        TreeNode root = new TreeNode(5);
        TreeNode n2 = new TreeNode(2);
        root.left = new TreeNode(3); root.left.left = n2;
        root.right = new TreeNode(7);
        assertEquals(3, test.inorderSuccessor(root, n2).val);
    }

    @Test
    public void testSuccessorOfRightmostNode() {
        TreeNode root = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        root.left = new TreeNode(3); root.right = n7;
        assertNull(test.inorderSuccessor(root, n7));
    }

    @Test
    public void testSuccessorOfRoot() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        root.right = n6;
        assertEquals(6, test.inorderSuccessor(root, root).val);
    }

    @Test
    public void testSuccessorWithRightSubtree() {
        TreeNode root = new TreeNode(10);
        TreeNode n5 = new TreeNode(5);
        root.left = n5;
        n5.left = new TreeNode(3);
        n5.right = new TreeNode(7);
        n5.right.left = new TreeNode(6);
        assertEquals(6, test.inorderSuccessor(root, n5).val);
    }

    @Test
    public void testSingleNodeNoSuccessor() {
        TreeNode root = new TreeNode(5);
        assertNull(test.inorderSuccessor(root, root));
    }

    @Test
    public void testTwoNodesLeftChild() {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        root.left = n1;
        assertEquals(2, test.inorderSuccessor(root, n1).val);
    }

    @Test
    public void testGiantBST() {
        // Build a larger BST: 20 at root
        TreeNode root = new TreeNode(20);
        TreeNode n10 = new TreeNode(10); TreeNode n30 = new TreeNode(30);
        root.left = n10; root.right = n30;
        n10.left = new TreeNode(5); n10.right = new TreeNode(15);
        n10.right.left = new TreeNode(12); n10.right.right = new TreeNode(18);
        n30.left = new TreeNode(25); n30.right = new TreeNode(35);
        // Successor of 18 should be 20
        assertEquals(20, test.inorderSuccessor(root, n10.right.right).val);
        // Successor of 15 should be 18
        assertEquals(18, test.inorderSuccessor(root, n10.right).val);
    }
}
