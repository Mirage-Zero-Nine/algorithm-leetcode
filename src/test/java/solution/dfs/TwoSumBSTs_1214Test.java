package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class TwoSumBSTs_1214Test {

    private final TwoSumBSTs_1214 test = new TwoSumBSTs_1214();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(2); t1.left = new TreeNode(1); t1.right = new TreeNode(4);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(0); t2.right = new TreeNode(3);
        assertTrue(test.twoSumBSTs(t1, t2, 5));
        assertFalse(test.twoSumBSTs(t1, t2, 10));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.twoSumBSTs(null, null, 1));
        assertFalse(test.twoSumBSTs(new TreeNode(1), new TreeNode(2), 5));
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(5); t1.left = new TreeNode(3); t1.right = new TreeNode(7);
        TreeNode t2 = new TreeNode(4); t2.left = new TreeNode(2); t2.right = new TreeNode(6);
        assertTrue(test.twoSumBSTs(t1, t2, 9));
    }

    @Test
    public void testNullFirstTree() {
        assertFalse(test.twoSumBSTs(null, new TreeNode(5), 5));
    }

    @Test
    public void testNullSecondTree() {
        assertFalse(test.twoSumBSTs(new TreeNode(5), null, 5));
    }

    @Test
    public void testSingleNodeTrees() {
        assertTrue(test.twoSumBSTs(new TreeNode(3), new TreeNode(7), 10));
        assertFalse(test.twoSumBSTs(new TreeNode(3), new TreeNode(7), 11));
    }

    @Test
    public void testTargetZero() {
        TreeNode t1 = new TreeNode(0);
        t1.left = new TreeNode(-2); t1.right = new TreeNode(2);
        TreeNode t2 = new TreeNode(0);
        t2.left = new TreeNode(-1); t2.right = new TreeNode(1);
        assertTrue(test.twoSumBSTs(t1, t2, 0)); // -2 + 2 or 0 + 0
    }

    @Test
    public void testNegativeTarget() {
        TreeNode t1 = new TreeNode(-5);
        t1.left = new TreeNode(-10); t1.right = new TreeNode(-1);
        TreeNode t2 = new TreeNode(-3);
        t2.left = new TreeNode(-7); t2.right = new TreeNode(0);
        assertTrue(test.twoSumBSTs(t1, t2, -8)); // -1 + -7 = -8
    }

    @Test
    public void testHashSetMethod() {
        TreeNode t1 = new TreeNode(2); t1.left = new TreeNode(1); t1.right = new TreeNode(4);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(0); t2.right = new TreeNode(3);
        assertTrue(test.hashSet(t1, t2, 5));
        assertFalse(test.hashSet(t1, t2, 10));
    }

    @Test
    public void testHashSetNullTrees() {
        assertFalse(test.hashSet(null, new TreeNode(1), 1));
        assertFalse(test.hashSet(new TreeNode(1), null, 1));
    }

    @Test
    public void testGiantCase() {
        // Use hashSet method for large skewed trees (naive method is too slow)
        TreeNode t1 = new TreeNode(50);
        TreeNode curr = t1;
        for (int i = 49; i >= 1; i--) { curr.left = new TreeNode(i); curr = curr.left; }
        curr = t1;
        for (int i = 51; i <= 100; i++) { curr.right = new TreeNode(i); curr = curr.right; }

        TreeNode t2 = new TreeNode(50);
        curr = t2;
        for (int i = 49; i >= 1; i--) { curr.left = new TreeNode(i); curr = curr.left; }
        curr = t2;
        for (int i = 51; i <= 100; i++) { curr.right = new TreeNode(i); curr = curr.right; }

        assertTrue(test.hashSet(t1, t2, 200)); // 100 + 100
        assertFalse(test.hashSet(t1, t2, 201)); // max is 200
    }
}
