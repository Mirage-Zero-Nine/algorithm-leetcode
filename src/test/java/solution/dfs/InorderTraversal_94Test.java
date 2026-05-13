package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
