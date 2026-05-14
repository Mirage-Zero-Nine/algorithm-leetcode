package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class CountUnivalSubtrees_250Test {

    private final CountUnivalSubtrees_250 test = new CountUnivalSubtrees_250();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1); root.right = new TreeNode(5);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        assertEquals(4, test.countUnivalSubtrees(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countUnivalSubtrees(null));
        assertEquals(1, test.countUnivalSubtrees(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        assertEquals(5, test.countUnivalSubtrees(root));
    }

    @Test
    public void testAllSameValues() {
        // Complete binary tree with all same values - all subtrees are unival
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(3); root.right = new TreeNode(3);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(3);
        assertEquals(7, test.countUnivalSubtrees(root));
    }

    @Test
    public void testAllDifferentValues() {
        // No parent can form unival subtree with children, only leaves count
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(3, test.countUnivalSubtrees(root)); // only leaves: 4, 5, 3
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        assertEquals(4, test.countUnivalSubtrees(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        // leaf 3 is unival, but node 2->3 is not, root 2->2->3 is not
        assertEquals(1, test.countUnivalSubtrees(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-1); root.right = new TreeNode(-1);
        assertEquals(3, test.countUnivalSubtrees(root));
    }

    @Test
    public void testMixedUnivalSubtrees() {
        // root=5, left subtree all 1s, right=5 with right child 5
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1); root.right = new TreeNode(5);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(5);
        // unival: left.left(1), left.right(1), left(1), right.right(5), right(5) = 5
        assertEquals(5, test.countUnivalSubtrees(root));
    }

    @Test
    public void testSingleChildNotMatching() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        // leaf 2 is unival, root 1 != child 2 so not unival
        assertEquals(1, test.countUnivalSubtrees(root));
    }

    @Test
    public void testGiantTree() {
        // Build a large balanced tree with all same values
        TreeNode root = buildUniformTree(5, 10);
        // 2^10 - 1 = 1023 nodes, all same value -> all 1023 subtrees are unival
        assertEquals(1023, test.countUnivalSubtrees(root));
    }

    private TreeNode buildUniformTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildUniformTree(val, depth - 1);
        node.right = buildUniformTree(val, depth - 1);
        return node;
    }
}
