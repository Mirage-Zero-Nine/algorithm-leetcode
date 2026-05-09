package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class Codec297Test {

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
}
