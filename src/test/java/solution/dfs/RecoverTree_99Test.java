package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class RecoverTree_99Test {

    private final RecoverTree_99 test = new RecoverTree_99();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3); root.left.right = new TreeNode(2);
        test.recoverTree(root);
        assertEquals(3, root.val);
        assertEquals(1, root.left.val);
    }

    @Test
    public void testEdgeCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        test.recoverTree(root);
        assertEquals(3, root.right.left.val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3); root.right = new TreeNode(1);
        test.recoverTree(root);
        assertEquals(1, root.left.val);
        assertEquals(3, root.right.val);
    }

    @Test
    public void testSingleNode() {
        TreeNode root = new TreeNode(1);
        test.recoverTree(root);
        assertEquals(1, root.val);
    }

    @Test
    public void testTwoNodesSwapped() {
        // BST should be [1,2] but swapped to [2,1]
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        // inorder: 1,2 - already correct? No: for BST root=2, left=1 is valid
        // Let's swap: root should be 1, right=2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        // swap them: root=2, right=1
        TreeNode swapped = new TreeNode(2);
        swapped.right = new TreeNode(1);
        test.recoverTree(swapped);
        assertEquals(1, swapped.val);
        assertEquals(2, swapped.right.val);
    }

    @Test
    public void testAdjacentNodesSwapped() {
        // Correct BST: 1,2,3,4,5 inorder. Swap adjacent 2 and 3
        // Tree: root=3, left=2, right=5, left.left=1, left.right=4 -- wait let me build properly
        // Correct: root=4, left=2, right=5, left.left=1, left.right=3
        // Swap 3 and 2: root=4, left=3, right=5, left.left=1, left.right=2
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        test.recoverTree(root);
        assertEquals(2, root.left.val);
        assertEquals(3, root.left.right.val);
    }

    @Test
    public void testNonAdjacentNodesSwapped() {
        // Correct BST inorder: 1,2,3,4,5. Swap 1 and 5.
        // Correct tree: root=3, left=2, right=4, left.left=1, right.right=5
        // Swapped: root=3, left=2, right=4, left.left=5, right.right=1
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(5);
        root.right.right = new TreeNode(1);
        test.recoverTree(root);
        assertEquals(1, root.left.left.val);
        assertEquals(5, root.right.right.val);
    }

    @Test
    public void testRootSwappedWithLeaf() {
        // Correct: root=2, left=1, right=3. Swap root(2) and left(1): root=1, left=2, right=3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        test.recoverTree(root);
        assertEquals(2, root.val);
        assertEquals(1, root.left.val);
    }

    @Test
    public void testRootSwappedWithRightChild() {
        // Correct: root=2, left=1, right=3. Swap root(2) and right(3): root=3, left=1, right=2
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        test.recoverTree(root);
        assertEquals(2, root.val);
        assertEquals(3, root.right.val);
    }

    @Test
    public void testLargerTree() {
        // Correct BST: root=6, left subtree has 1,2,3,4,5, right subtree has 7,8,9
        // Swap 2 and 8
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(8); // swapped (should be 2)
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(2); // swapped (should be 8)
        test.recoverTree(root);
        assertEquals(2, root.left.left.val);
        assertEquals(8, root.right.right.left.val);
    }

    @Test
    public void testNegativeValues() {
        // Correct: root=0, left=-1, right=1. Swap -1 and 1.
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(-1);
        test.recoverTree(root);
        assertEquals(-1, root.left.val);
        assertEquals(1, root.right.val);
    }
}
