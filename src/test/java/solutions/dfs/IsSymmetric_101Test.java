package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsSymmetric_101Test {

    private final IsSymmetric_101 test = new IsSymmetric_101();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4); root.right.right = new TreeNode(3);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isSymmetric(null));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.right = new TreeNode(3); root.right.right = new TreeNode(3);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testSingleNode() {
        assertTrue(test.isSymmetric(new TreeNode(1)));
    }

    @Test
    public void testAsymmetricValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testOnlyLeftChild() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testOnlyRightChild() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testDeepSymmetric() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.right.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4); root.right.right.right = new TreeNode(4);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testDeepAsymmetric() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.right.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4); root.right.right.right = new TreeNode(5);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testSymmetricWithNulls() {
        // symmetric: left has right child, right has left child with same value
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.right = new TreeNode(3); root.right.left = new TreeNode(3);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testGiantSymmetricTree() {
        // Build a symmetric tree of depth 5
        TreeNode root = buildSymmetric(5);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testNullRoot() {
        assertTrue(test.isSymmetric(null));
    }

    @Test
    public void testStructurallyAsymmetricLeftLeftNull() {
        // left.left == null but right.right != null
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testValuesMirrorButStructureDoesNot() {
        // [1, 2, 2, null, 3, null, 3] - left has right child 3, right has right child 3
        TreeNode root = TreeParser.deserialize("1, 2, 2, null, 3, null, 3");
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testAllSameValueSymmetric() {
        // Perfect tree with all values = 1
        TreeNode root = TreeParser.deserialize("1, 1, 1, 1, 1, 1, 1");
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testSingleChildOnOneSideOnly() {
        // root -> left -> left (chain on left only)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testDeepSymmetricTree10Levels() {
        TreeNode root = buildMirrorSymmetric(10, 1);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testLargeSymmetricTreeProgrammatic() {
        // Build a symmetric tree of depth 12 (4095 nodes)
        TreeNode root = buildSymmetric(12);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testAsymmetricByPerturbingOneLeaf() {
        // Start with symmetric tree, then change one leaf value
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4); root.right.right = new TreeNode(3);
        // Perturb one leaf
        root.right.right.val = 99;
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testSymmetricViaTreeParser() {
        // [1, 2, 2, 3, 4, 4, 3]
        TreeNode root = TreeParser.deserialize("1, 2, 2, 3, 4, 4, 3");
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testAsymmetricMirrorStructureWithDifferentValues() {
        // Structure is mirrored but values don't match: left subtree [2,3,4], right subtree [2,4,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4); root.right.right = new TreeNode(5);
        assertFalse(test.isSymmetric(root));
    }

    private TreeNode buildMirrorSymmetric(int depth, int level) {
        if (level > depth) return null;
        TreeNode node = new TreeNode(level);
        node.left = buildMirrorSymmetric(depth, level + 1);
        node.right = buildMirrorSymmetric(depth, level + 1);
        return node;
    }

    private TreeNode buildSymmetric(int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(depth);
        node.left = buildSymmetric(depth - 1);
        node.right = buildSymmetric(depth - 1);
        return node;
    }
}
