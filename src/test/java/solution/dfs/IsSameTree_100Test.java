package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsSameTree_100Test {

    private final IsSameTree_100 test = new IsSameTree_100();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2); t2.right = new TreeNode(3);
        assertTrue(test.isSameTree(t1, t2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isSameTree(null, null));
        assertFalse(test.isSameTree(new TreeNode(1), null));
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2);
        TreeNode t2 = new TreeNode(1); t2.right = new TreeNode(2);
        assertFalse(test.isSameTree(t1, t2));
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(4); t1.left.right = new TreeNode(5);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2); t2.right = new TreeNode(3);
        t2.left.left = new TreeNode(4); t2.left.right = new TreeNode(5);
        assertTrue(test.isSameTree(t1, t2));
    }

    @Test
    public void testNullVsNonNull() {
        assertFalse(test.isSameTree(null, new TreeNode(1)));
    }

    @Test
    public void testSingleNodesSameValue() {
        assertTrue(test.isSameTree(new TreeNode(5), new TreeNode(5)));
    }

    @Test
    public void testSingleNodesDifferentValue() {
        assertFalse(test.isSameTree(new TreeNode(1), new TreeNode(2)));
    }

    @Test
    public void testDifferentDepths() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.left.left = new TreeNode(3);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2);
        assertFalse(test.isSameTree(t1, t2));
    }

    @Test
    public void testSameStructureDifferentValues() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2); t2.right = new TreeNode(4);
        assertFalse(test.isSameTree(t1, t2));
    }

    @Test
    public void testMirrorTrees() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(3); t2.right = new TreeNode(2);
        assertFalse(test.isSameTree(t1, t2));
    }

    @Test
    public void testNegativeValues() {
        TreeNode t1 = new TreeNode(-1); t1.left = new TreeNode(-2);
        TreeNode t2 = new TreeNode(-1); t2.left = new TreeNode(-2);
        assertTrue(test.isSameTree(t1, t2));
    }

    @Test
    public void testGiantIdenticalTrees() {
        TreeNode t1 = buildTree(1, 10);
        TreeNode t2 = buildTree(1, 10);
        assertTrue(test.isSameTree(t1, t2));
    }

    private TreeNode buildTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildTree(val * 2, depth - 1);
        node.right = buildTree(val * 2 + 1, depth - 1);
        return node;
    }
}
