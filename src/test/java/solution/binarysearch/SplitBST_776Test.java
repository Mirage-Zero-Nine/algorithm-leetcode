package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SplitBST_776Test {

    private final SplitBST_776 test = new SplitBST_776();

    private void inorder(TreeNode node, List<Integer> out) {
        if (node == null) {
            return;
        }
        inorder(node.left, out);
        out.add(node.val);
        inorder(node.right, out);
    }

    private List<Integer> inorder(TreeNode node) {
        List<Integer> out = new ArrayList<>();
        inorder(node, out);
        return out;
    }

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        TreeNode[] result = test.splitBST(root, 2);
        assertEquals(2, result[0].val);
        assertEquals(4, result[1].val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeNode[] result = test.splitBST(null, 1);
        assertNull(result[0]);
        assertNull(result[1]);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode[] result = test.splitBST(root, 4);
        assertEquals(3, result[0].val);
        assertEquals(5, result[1].val);
    }

    @Test
    public void testTargetGreaterThanAllValues() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode[] result = test.splitBST(root, 10);
        assertEquals(List.of(1, 2, 3), inorder(result[0]));
        assertNull(result[1]);
    }

    @Test
    public void testTargetSmallerThanAllValues() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode[] result = test.splitBST(root, 0);
        assertNull(result[0]);
        assertEquals(List.of(1, 2, 3), inorder(result[1]));
    }

    @Test
    public void testSplitOnExistingNodeValue() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        TreeNode[] result = test.splitBST(root, 6);
        assertEquals(List.of(2, 4, 6), inorder(result[0]));
        assertEquals(List.of(8, 12), inorder(result[1]));
    }

    @Test
    public void testIterativeMatchesRecursive() {
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2); root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1); root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(5); root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2); root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(1); root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(5); root2.right.right = new TreeNode(7);

        TreeNode[] recursive = test.splitBST(root1, 4);
        TreeNode[] iterative = test.iterative(root2, 4);
        assertEquals(inorder(recursive[0]), inorder(iterative[0]));
        assertEquals(inorder(recursive[1]), inorder(iterative[1]));
    }

    @Test
    public void testSingleNodeTree() {
        TreeNode root = new TreeNode(5);
        TreeNode[] result = test.splitBST(root, 4);
        assertNull(result[0]);
        assertEquals(5, result[1].val);
    }

    @Test
    public void testPartitionProperty() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(18);

        int target = 12;
        TreeNode[] result = test.splitBST(root, target);
        for (Integer n : inorder(result[0])) {
            assertTrue(n <= target);
        }
        for (Integer n : inorder(result[1])) {
            assertTrue(n > target);
        }
    }

    @Test
    public void testGiantCase() {
        TreeNode root = null;
        for (int i = 200; i >= 1; i--) {
            TreeNode node = new TreeNode(i);
            node.right = root;
            root = node;
        }
        TreeNode[] result = test.splitBST(root, 120);
        assertEquals(120, inorder(result[0]).size());
        assertEquals(80, inorder(result[1]).size());
    }
}
