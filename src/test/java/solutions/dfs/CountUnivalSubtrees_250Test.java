package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testPropertyResultLeqNodeCount() {
        // Property: result <= number of nodes for any tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(1);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(1);
        int result = test.countUnivalSubtrees(root);
        assertTrue(result <= 7); // 7 nodes total
        assertTrue(result >= 0);
    }

    @Test
    public void testZeroValuedNodes() {
        // All zeros - ensure val=0 doesn't cause issues
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        assertEquals(3, test.countUnivalSubtrees(root));
    }

    @Test
    public void testChildrenSameButDifferentFromParent() {
        // Both children are 2, parent is 1 -> children are unival leaves, parent is not
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        assertEquals(2, test.countUnivalSubtrees(root));
    }

    @Test
    public void testIntegerExtremeValues() {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE);
        root.right = new TreeNode(Integer.MIN_VALUE);
        // leaves are unival, root is not (children differ)
        assertEquals(2, test.countUnivalSubtrees(root));
    }

    @Test
    public void testDeepChainWithMismatchAtBottom() {
        // 1->1->1->1->2: the mismatch at bottom propagates up
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        root.left.left.left.left = new TreeNode(2);
        // only leaf(2) is unival, all ancestors have mismatching child
        assertEquals(1, test.countUnivalSubtrees(root));
    }

    @Test
    public void testOnlyRightSubtreeIsUnival() {
        // Left subtree has mismatch, right subtree is all same
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(3);
        // unival: left.left(2), right.left(3), right.right(3), right(3) = 4
        // left(1) has child 2 != 1, not unival; root has right=3 != 1, not unival
        assertEquals(4, test.countUnivalSubtrees(root));
    }

    @Test
    public void testRootMatchesOneChildOnly() {
        // root=5, left=5, right=3 -> left is unival leaf, right is unival leaf, root not unival
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5); root.right = new TreeNode(3);
        assertEquals(2, test.countUnivalSubtrees(root));
    }

    @Test
    public void testFullBinaryTreeMixed() {
        //        1
        //      /   \
        //     1     1
        //    / \   / \
        //   1   2 1   1
        // unival: leaves 1,2,1,1 = 4; left(1) has child 2!=1 not unival;
        // right(1) children both 1 -> unival; root left subtree not unival -> root not unival
        // total = 4 (leaves) + 1 (right subtree) = 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(1);
        assertEquals(5, test.countUnivalSubtrees(root));
    }
}
