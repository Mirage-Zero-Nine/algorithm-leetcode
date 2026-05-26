package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class Codec_449Test {

    private final Codec_449 test = new Codec_449();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        TreeNode result = test.deserialize(test.serialize(root));
        assertEquals(4, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.left.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deserialize(test.serialize(null)));
        assertEquals(1, test.deserialize(test.serialize(new TreeNode(1))).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.right.right = new TreeNode(9);
        TreeNode result = test.deserialize(test.serialize(root));
        assertEquals(9, result.right.right.val);
    }

    @Test
    public void testSerializeNullAsEmptyString() {
        assertEquals("", test.serialize(null));
    }

    @Test
    public void testDeserializeEmptyString() {
        assertNull(test.deserialize(""));
    }

    @Test
    public void testRoundTripWithNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-10);
        root.left.right = new TreeNode(-1);
        root.right = new TreeNode(10);
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testRoundTripWithMinMaxIntegerValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testLeftSkewedBstRoundTrip() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testRightSkewedBstRoundTrip() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testGiantBalancedBstRoundTrip() {
        TreeNode root = buildBalancedBst(1, 2047);
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    private TreeNode buildBalancedBst(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = buildBalancedBst(left, mid - 1);
        node.right = buildBalancedBst(mid + 1, right);
        return node;
    }

    private boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.val == b.val && isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }
}
