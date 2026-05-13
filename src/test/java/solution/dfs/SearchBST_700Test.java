package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SearchBST_700Test {

    private final SearchBST_700 test = new SearchBST_700();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        assertEquals(2, test.searchBST(root, 2).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.searchBST(null, 1));
        assertNull(test.searchBST(new TreeNode(1), 2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        assertEquals(7, test.searchBST(root, 7).val);
    }

    @Test
    public void testFindRoot() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        assertEquals(4, test.searchBST(root, 4).val);
    }

    @Test
    public void testFindLeftmostNode() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        assertEquals(1, test.searchBST(root, 1).val);
    }

    @Test
    public void testFindRightmostNode() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(20);
        assertEquals(20, test.searchBST(root, 20).val);
    }

    @Test
    public void testNotFoundInLargeTree() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4); root.right = new TreeNode(12);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(10); root.right.right = new TreeNode(14);
        assertNull(test.searchBST(root, 9));
    }

    @Test
    public void testSingleNodeFound() {
        assertEquals(5, test.searchBST(new TreeNode(5), 5).val);
    }

    @Test
    public void testSingleNodeNotFound() {
        assertNull(test.searchBST(new TreeNode(5), 3));
    }

    @Test
    public void testSubtreeReturned() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        TreeNode result = test.searchBST(root, 2);
        assertEquals(2, result.val);
        assertEquals(1, result.left.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testGiantBST() {
        // Build a deep left-skewed BST with 100 nodes
        TreeNode root = new TreeNode(100);
        TreeNode cur = root;
        for (int i = 99; i >= 1; i--) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertEquals(1, test.searchBST(root, 1).val);
        assertEquals(50, test.searchBST(root, 50).val);
        assertNull(test.searchBST(root, 101));
    }
}
