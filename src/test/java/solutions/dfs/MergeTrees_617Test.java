package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MergeTrees_617Test {

    private final MergeTrees_617 test = new MergeTrees_617();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(3); t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);
        TreeNode t2 = new TreeNode(2); t2.left = new TreeNode(1); t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4); t2.right.right = new TreeNode(7);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(3, result.val);
        assertEquals(4, result.left.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.mergeTrees(null, null));
        assertEquals(1, test.mergeTrees(new TreeNode(1), null).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(4); t2.left = new TreeNode(5); t2.right = new TreeNode(6);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(5, result.val);
    }

    @Test
    public void testSecondTreeNull() {
        TreeNode t1 = new TreeNode(5);
        t1.left = new TreeNode(3);
        TreeNode result = test.mergeTrees(t1, null);
        assertEquals(5, result.val);
        assertEquals(3, result.left.val);
    }

    @Test
    public void testFirstTreeNull() {
        TreeNode t2 = new TreeNode(7);
        t2.right = new TreeNode(9);
        TreeNode result = test.mergeTrees(null, t2);
        assertEquals(7, result.val);
        assertEquals(9, result.right.val);
    }

    @Test
    public void testBothSingleNodes() {
        TreeNode result = test.mergeTrees(new TreeNode(3), new TreeNode(4));
        assertEquals(7, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    public void testNonOverlappingStructures() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
        t2.right = new TreeNode(3);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(2, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testNegativeValues() {
        TreeNode t1 = new TreeNode(-1); t1.left = new TreeNode(-2);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(0, result.val);
        assertEquals(0, result.left.val);
    }

    @Test
    public void testDeepMerge() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.left.left = new TreeNode(3);
        t1.left.left.left = new TreeNode(4);
        TreeNode t2 = new TreeNode(10);
        t2.left = new TreeNode(20);
        t2.left.left = new TreeNode(30);
        t2.left.left.left = new TreeNode(40);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(11, result.val);
        assertEquals(22, result.left.val);
        assertEquals(33, result.left.left.val);
        assertEquals(44, result.left.left.left.val);
    }

    @Test
    public void testMergeWithZeroValues() {
        TreeNode t1 = new TreeNode(0); t1.left = new TreeNode(0); t1.right = new TreeNode(0);
        TreeNode t2 = new TreeNode(0); t2.left = new TreeNode(0); t2.right = new TreeNode(0);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(0, result.val);
        assertEquals(0, result.left.val);
        assertEquals(0, result.right.val);
    }

    @Test
    public void testGiantTree() {
        // Merge two balanced trees of depth 8
        TreeNode t1 = buildTree(1, 8);
        TreeNode t2 = buildTree(1, 8);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(2, result.val);
        assertEquals(4, result.left.val);
        assertEquals(6, result.right.val);
    }

    @Test
    public void testBothNullReturnsNull() {
        assertNull(test.mergeTrees(null, null));
    }

    @Test
    public void testOneNullOtherComplexTree() {
        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(8);
        t2.left.left = new TreeNode(1);
        TreeNode result = test.mergeTrees(null, t2);
        assertEquals(5, result.val);
        assertEquals(3, result.left.val);
        assertEquals(8, result.right.val);
        assertEquals(1, result.left.left.val);
    }

    @Test
    public void testDifferentShapesMissingNodesFilled() {
        // t1 has only left subtree, t2 has only right subtree with deeper structure
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.left.left = new TreeNode(4);

        TreeNode t2 = new TreeNode(1);
        t2.right = new TreeNode(3);
        t2.right.right = new TreeNode(7);

        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(2, result.val);
        assertEquals(2, result.left.val);
        assertEquals(4, result.left.left.val);
        assertEquals(3, result.right.val);
        assertEquals(7, result.right.right.val);
        assertNull(result.left.right);
        assertNull(result.right.left);
    }

    @Test
    public void testNegativeValuesSumCorrect() {
        TreeNode t1 = new TreeNode(-5);
        t1.left = new TreeNode(-10);
        t1.right = new TreeNode(3);

        TreeNode t2 = new TreeNode(-3);
        t2.left = new TreeNode(10);
        t2.right = new TreeNode(-7);

        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(-8, result.val);
        assertEquals(0, result.left.val);
        assertEquals(-4, result.right.val);
    }

    @Test
    public void testDeepLeftSkewedTrees() {
        // Build depth-10 left-skewed trees
        TreeNode t1 = buildLeftSkew(10, 1);
        TreeNode t2 = buildLeftSkew(10, 100);
        TreeNode result = test.mergeTrees(t1, t2);
        TreeNode cur = result;
        for (int i = 0; i < 10; i++) {
            assertEquals((i + 1) + (i + 100), cur.val);
            assertNull(cur.right);
            cur = cur.left;
        }
        assertNull(cur);
    }

    @Test
    public void testCompleteOverlapStructure() {
        // Both trees are complete binary trees of depth 3
        TreeNode t1 = buildTree(1, 3);
        TreeNode t2 = buildTree(10, 3);
        TreeNode result = test.mergeTrees(t1, t2);
        // t1: root=1, left=2, right=3; t2: root=10, left=20, right=21
        assertEquals(11, result.val);
        assertEquals(22, result.left.val);
        assertEquals(24, result.right.val);
    }

    @Test
    public void testPropertyResultHasNodesWhereverEitherHas() {
        // t1: root with left child; t2: root with right child and right.left
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);

        TreeNode t2 = new TreeNode(3);
        t2.right = new TreeNode(4);
        t2.right.left = new TreeNode(5);

        TreeNode result = test.mergeTrees(t1, t2);
        // Result must have: root, left, right, right.left
        assertEquals(4, result.val);
        assertEquals(2, result.left.val);   // only in t1
        assertEquals(4, result.right.val);  // only in t2
        assertEquals(5, result.right.left.val); // only in t2
        assertNull(result.left.left);
        assertNull(result.left.right);
        assertNull(result.right.right);
    }

    @Test
    public void testPropertyValueSumAtEveryNode() {
        // Verify result.val = t1.val + t2.val at every overlapping node
        TreeNode t1 = new TreeNode(7);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(9);
        t1.left.right = new TreeNode(6);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(8);
        t2.right = new TreeNode(1);
        t2.right.left = new TreeNode(4);

        TreeNode result = test.mergeTrees(t1, t2);
        // root: 7+2=9, left: 3+8=11, right: 9+1=10, left.right: 6+0=6, right.left: 0+4=4
        assertEquals(9, result.val);
        assertEquals(11, result.left.val);
        assertEquals(10, result.right.val);
        assertEquals(6, result.left.right.val);
        assertEquals(4, result.right.left.val);
        assertNull(result.left.left);
        assertNull(result.right.right);
    }

    private TreeNode buildLeftSkew(int depth, int startVal) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(startVal);
        node.left = buildLeftSkew(depth - 1, startVal + 1);
        return node;
    }

    private TreeNode buildTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildTree(val * 2, depth - 1);
        node.right = buildTree(val * 2 + 1, depth - 1);
        return node;
    }
}
