package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class Codec_297Test {

    private final Codec_297 test = new Codec_297();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.right.left = new TreeNode(4); root.right.right = new TreeNode(5);
        TreeNode result = test.deserialize(test.serialize(root));
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(5, result.right.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deserialize(test.serialize(null)));
        assertEquals(1, test.deserialize(test.serialize(new TreeNode(1))).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        TreeNode result = test.deserialize(test.serialize(root));
        assertEquals(7, result.right.right.val);
    }

    @Test
    public void testSerializeNullTreeLiteral() {
        assertEquals("#,", test.serialize(null));
    }

    @Test
    public void testDeserializeHashLiteral() {
        assertNull(test.deserialize("#,"));
    }

    @Test
    public void testRoundTripWithNegativeValues() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-20);
        root.right = new TreeNode(-30);
        TreeNode result = test.deserialize(test.serialize(root));
        assertTrue(isSameTree(root, result));
    }

    @Test
    public void testRoundTripWithDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        TreeNode result = test.deserialize(test.serialize(root));
        assertTrue(isSameTree(root, result));
    }

    @Test
    public void testLeftSkewedTreeRoundTrip() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);
        TreeNode result = test.deserialize(test.serialize(root));
        assertTrue(isSameTree(root, result));
    }

    @Test
    public void testRightSkewedTreeRoundTrip() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        TreeNode result = test.deserialize(test.serialize(root));
        assertTrue(isSameTree(root, result));
    }

    @Test
    public void testGiantCompleteTreeRoundTrip() {
        TreeNode[] nodes = new TreeNode[1024];
        for (int i = 1; i <= 1023; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 1; i <= 511; i++) {
            nodes[i].left = nodes[2 * i];
            nodes[i].right = nodes[2 * i + 1];
        }
        TreeNode root = nodes[1];
        TreeNode result = test.deserialize(test.serialize(root));
        assertTrue(isSameTree(root, result));
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
