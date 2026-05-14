package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class RightSideView_199Test {

    private final RightSideView_199 test = new RightSideView_199();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(5); root.right.right = new TreeNode(4);
        assertEquals(List.of(1, 3, 4), test.rightSideView(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.rightSideView(null));
        assertEquals(List.of(1), test.rightSideView(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.rightSideView(root));
    }

    @Test
    public void testBfsMatchesDfsForBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(test.rightSideView(root), test.bfs(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.rightSideView(root));
        assertEquals(List.of(1, 2, 3), test.bfs(root));
    }

    @Test
    public void testSparseTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        assertEquals(List.of(1, 3, 5), test.rightSideView(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(-4);
        assertEquals(List.of(-1, -3, -4), test.rightSideView(root));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        assertEquals(List.of(1, 1), test.rightSideView(root));
    }

    @Test
    public void testBfsNullAndSingle() {
        assertEquals(List.of(), test.bfs(null));
        assertEquals(List.of(9), test.bfs(new TreeNode(9)));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 120; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        List<Integer> out = test.rightSideView(root);
        assertEquals(121, out.size());
        assertEquals(120, out.get(out.size() - 1));
    }
}
